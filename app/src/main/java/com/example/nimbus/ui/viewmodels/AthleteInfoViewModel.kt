package com.example.nimbus.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbus.model.Athlete
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AthleteInfoUiState(
    val selectedAthlete: Athlete? = null,
)

class AthleteInfoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AthleteInfoUiState())
    val uiState: StateFlow<AthleteInfoUiState> = _uiState.asStateFlow()

    init {
        fetchAthleteDesc()
    }

    private fun fetchAthleteDesc() {
        viewModelScope.launch {

        }
    }
}