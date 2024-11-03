package com.example.nimbus.ui.viewmodels

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nimbus.api.RetrofitService
import com.example.nimbus.model.Athlete
import com.example.nimbus.utils.SharedPreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

data class RosterScreenUiState(
    val searchText: String = "",
    val athletes: List<Athlete> = emptyList(),
    val filters: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val onSearchChange:(String) -> Unit = {}
) {
    fun isAthletesEmpty(): Boolean {
        if(athletes.isEmpty()) return true else return false
    }
}


class RosterScreenViewModel(
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModel() {
    private val _uiState = MutableStateFlow(RosterScreenUiState())
    val uiState: StateFlow<RosterScreenUiState> = _uiState.asStateFlow()

    init {
        fetchAthletes()
    }

    private fun fetchAthletes() {
        viewModelScope.launch {
            val teste: UUID = UUID.fromString("73832b8f-4bd3-48bf-ae32-299176fa36a8")
            try {
                val athleteApi = RetrofitService.getAthletesApi(sharedPreferencesManager.getAuthToken())
                val response = athleteApi.getAllByTeam(teste)
                Log.i("Resposta - Get All Athletes Team", "$response")

                if(response.isSuccessful && !response.body()?.data.isNullOrEmpty()) {
                    _uiState.value = response.body()?.data?.let {
                        _uiState.value.copy(
                            athletes = it
                        )
                    }!!
                    Log.i("Roster", "Dados de roster obtidos com sucesso: ${response.body()}")
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
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun onSearchChange(text: String) {
        _uiState.value = _uiState.value.copy(searchText = text)
    }

    val filteredAthletes: List<Athlete>
        get() {
            val searchText = _uiState.value.searchText
            return if (searchText.isBlank()) {
                _uiState.value.athletes
            } else {
                _uiState.value.athletes.filter { athlete ->
                    val athleteName = athlete.firstName + " " + athlete.lastName
                    athleteName.contains(searchText, ignoreCase = true)
                }
            }
        }
}

class RosterModelFactory(
    private val sharedPreferencesManager: SharedPreferencesManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RosterScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RosterScreenViewModel(sharedPreferencesManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

