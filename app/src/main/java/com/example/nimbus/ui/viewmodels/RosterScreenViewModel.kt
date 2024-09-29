package com.example.nimbus.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbus.api.RetrofitService
import com.example.nimbus.model.Athlete
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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

class RosterScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RosterScreenUiState())
    val uiState: StateFlow<RosterScreenUiState> = _uiState.asStateFlow()

    init {
        fetchAthletes()
    }

    private fun fetchAthletes() {
        viewModelScope.launch {
            try {
                val athleteApi = RetrofitService.getAthletesApi()
                val response = athleteApi.getAllAthletes()

                if(response.isSuccessful && !response.body().isNullOrEmpty()) {
                    _uiState.value = response.body()?.let {
                        _uiState.value.copy(
                            athletes = it
                        )
                    }!!
                }
                else {
                    _uiState.value = _uiState.value.copy(
                        error = "Erro na resposta: ${response.errorBody()?.string()}"
                    )
                }
            }
            catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Falha na requisição: ${e.message}"
                )
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

