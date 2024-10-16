package com.example.nimbus.ui.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbus.api.RetrofitService
import com.example.nimbus.dto.UserLoginDTO
import com.example.nimbus.dto.UserLoginResponseDTO
import com.example.nimbus.ui.screens.MyTeamsScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val userData: UserLoginResponseDTO? = null,
    val error: String? = null,
    val isLoading: Boolean = false
)

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun login(context: Context) {
        viewModelScope.launch {
            try {
                val loginDTO = UserLoginDTO(_uiState.value.email, _uiState.value.password)
                val userApi = RetrofitService.getUsersApi()
                val response = userApi.login(loginDTO)

                if(response.isSuccessful && response.body()?.data != null) {
                    _uiState.value = _uiState.value.copy(userData = response.body()?.data)

                    Log.i("Login", "Login realizado com sucesso: ${_uiState.value.userData}")

                    saveUserData(context, _uiState.value.userData)
                    context.startActivity(Intent(context, MyTeamsScreen::class.java))
                }
                else {
                    _uiState.value = _uiState.value.copy(
                        error = "Erro na resposta: ${response.errorBody()?.string()}"
                    )
                    Log.e("Login", "Erro na resposta: ${response.errorBody()?.string()}")
                }
            }
            catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Falha na requisição: ${e.message}"
                )
                Log.e("TeamsAPI", "Erro na requisição", e)
            }
            finally {

            }
        }
    }

    fun saveUserData(context: Context, userData: UserLoginResponseDTO?) {
        val sharedPref = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        Log.i("Login save","Na função de salvar: $userData")

        with(sharedPref.edit()) {
            putString("user_id", userData?.userId.toString())
            putString("persona_id", userData?.personaId.toString())
            putString("username", userData?.username)
            putString("token", userData?.token)
            putString("email", userData?.email)
            apply()
        }

        val username = sharedPref.getString("username", null) ?: "Indefinido"

        Log.i("Login pós save", "Valor do token $username")
    }

    fun onEmailChange(text: String) { _uiState.value = _uiState.value.copy( email = text ) }
    fun onPasswordChange(text: String) { _uiState.value = _uiState.value.copy( password = text ) }
}