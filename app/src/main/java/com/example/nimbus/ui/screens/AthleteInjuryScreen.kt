package com.example.nimbus.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.nimbus.model.Injury
import com.example.nimbus.ui.components.Button
import com.example.nimbus.ui.components.Container
import com.example.nimbus.ui.components.CustomTextField
import com.example.nimbus.ui.components.DatePickerDocked
import com.example.nimbus.ui.components.InjuryCard
import com.example.nimbus.ui.viewmodels.AthleteInjuryScreenViewModel
import com.example.nimbus.ui.viewmodels.GlobalViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AthleteInjuryScreen(
    globalViewModel: GlobalViewModel,
    viewModel: AthleteInjuryScreenViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val globalUiState by globalViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize(1f)
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val sheetState = rememberModalBottomSheetState()
        var isSheetOpen by rememberSaveable { mutableStateOf(false) }

        if(isSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = { isSheetOpen = false },
                sheetState = sheetState,
                containerColor = Color(0xFF212121)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CustomTextField(
                        value = uiState.typeText,
                        onValueChange = { viewModel.onTypeChange(it) },
                        placeholder = "Lesão muscular"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        DatePickerDocked(
                            label = "Início",
                            fraction = .5f,
                            onValueChange = {}
                        )

                        DatePickerDocked(
                            label = "Final",
                            fraction = 1f,
                            onValueChange = {}
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(text = "Cadastrar lesão", fontSize = 20, onClick = { /*TODO*/ })
                }
            }
        }

        //grafico/kpi
        Container(
            modifier = Modifier.height(300.dp)
        ) {

        }

        if(uiState.injuries.isNullOrEmpty()) {
            NoContent()
        } else {
            ContentList(content = uiState.injuries)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            text = "Adicionar lesão",
            fontSize = 16,
            onClick = { isSheetOpen = true },
            modifier = Modifier.height(50.dp)
        )
    }
}

@Composable
fun ContentList(content: List<Injury>) {
    val scrollState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .drawFadingEdges(scrollState)
            .height(270.dp)
            .padding(top = 18.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        state = scrollState
    ) {
        items(items = content.toList()) {
            InjuryCard(injury = it)
        }
    }
}

@Composable
fun NoContent() {
    Column(
        modifier = Modifier
            .height(505.dp)
            .padding(top = 18.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Não foram encontradas lesões")
    }
}