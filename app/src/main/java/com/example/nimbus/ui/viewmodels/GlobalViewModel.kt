package com.example.nimbus.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.nimbus.model.Athlete
import com.example.nimbus.model.Team
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class GlobalUiState(
    val selectedTeam: Team? = null,
    val selectedAthlete: Athlete? = null
)

class GlobalViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(GlobalUiState())
    val uiState: StateFlow<GlobalUiState> = _uiState.asStateFlow()

    fun selectTeam(team: Team) { _uiState.value = _uiState.value.copy(selectedTeam = team) }
    fun getSelectedTeam(): Team? { return _uiState.value.selectedTeam }
    fun removeTeam() { _uiState.value = _uiState.value.copy(selectedTeam = null) }

    fun selectAthlete(athlete: Athlete) { _uiState.value = _uiState.value.copy(selectedAthlete = athlete) }
    fun getSelectedAthlete(): Athlete? { return _uiState.value.selectedAthlete }
    fun removeAthlete() { _uiState.value = _uiState.value.copy(selectedAthlete = null) }
}