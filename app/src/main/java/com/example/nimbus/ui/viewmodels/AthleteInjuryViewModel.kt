package com.example.nimbus.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.nimbus.model.Injury
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.util.UUID

data class AthleteInjuryUiState(
    val injuries: List<Injury> = listOf(
        Injury(
            id = UUID.randomUUID(),
            type = "Distensão muscular",
            initialDate = LocalDate.of(2023, 1, 15),
            finalDate = LocalDate.of(2023, 1, 30)
        ),
        Injury(
            id = UUID.randomUUID(),
            type = "Entorse de tornozelo",
            initialDate = LocalDate.of(2023, 3, 22),
            finalDate = LocalDate.of(2023, 4, 5)
        ),
        Injury(
            id = UUID.randomUUID(),
            type = "Fratura no braço",
            initialDate = LocalDate.of(2023, 5, 10),
            finalDate = LocalDate.of(2023, 6, 25)
        ),
        Injury(
            id = UUID.randomUUID(),
            type = "Luxação no ombro",
            initialDate = LocalDate.of(2023, 7, 1),
            finalDate = LocalDate.of(2023, 7, 20)
        ),
        Injury(
            id = UUID.randomUUID(),
            type = "Ruptura de ligamento cruzado",
            initialDate = LocalDate.of(2023, 8, 5),
            finalDate = LocalDate.of(2024, 1, 15)
        ),
        Injury(
            id = UUID.randomUUID(),
            type = "Concussão",
            initialDate = LocalDate.of(2023, 9, 10),
            finalDate = LocalDate.of(2023, 9, 20)
        ),
        Injury(
            id = UUID.randomUUID(),
            type = "Tendinite no joelho",
            initialDate = LocalDate.of(2023, 10, 2),
            finalDate = LocalDate.of(2023, 11, 15)
        )
    ),
    val typeText: String = "",
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now()
)

class AthleteInjuryScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AthleteInjuryUiState())
    val uiState: StateFlow<AthleteInjuryUiState> = _uiState.asStateFlow()

    init {
        fetchInjuries()
    }

    private fun fetchInjuries() {

    }

    fun onTypeChange(text: String) {
        _uiState.value = _uiState.value.copy(typeText = text)
    }

    fun onStartDateChange(newDate: LocalDate) {
        _uiState.value = _uiState.value.copy(startDate = newDate)
    }

    fun endDateChange(newDate: LocalDate) {
        _uiState.value = _uiState.value.copy(endDate = newDate)
    }
}