package com.example.nimbus.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.R
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily
import com.example.nimbus.ui.viewmodels.GlobalViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigation(
    onMenuClick: () -> Unit = {},
    onBackClick: (page: Int) -> Unit = {},
    selectedPage: Int,
    globalViewModel: GlobalViewModel,
    athletePage: Int = 0
) {
    val uiState by globalViewModel.uiState.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .padding(20.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if(isSheetOpen) {
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { isSheetOpen = false },
                containerColor = Color(0xFF212121)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                }
            }
        }

        val interactionSource= remember { MutableInteractionSource() }
        val icon = if(selectedPage == 1 && athletePage > 0) R.drawable.arrow_left else R.drawable.hamburguer_menu_icon
        Image(
            painter = painterResource(id = icon),
            contentDescription = stringResource(id = R.string.menu_icon_desc),
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onBackClick(0)
            }
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var screen: String?
            var subtext: String? = ""
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            when(selectedPage) {
                0 -> { screen = "Chat"; subtext =  uiState.selectedTeam?.name }
                1 -> { screen = "Meu time"; subtext = uiState.selectedTeam?.name }
                2 -> { screen = "Dashboard"; subtext = LocalDate.now().format(formatter) }
                3 -> { screen = "Eventos"; subtext = uiState.selectedTeam?.name }
                4 -> { screen = "Perfil"; }
                else -> { screen = "Error"; subtext = "" }
            }

            Text(
                text = screen.toUpperCase(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.orange_100),
                fontFamily = poppinsFontFamily
            )

            if(!subtext.isNullOrEmpty()) {
                Text(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    text = subtext,
                    color = colorResource(id = R.color.gray_400),
                    fontFamily = poppinsFontFamily,
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.bell_icon),
            contentDescription = stringResource(id = R.string.notification_icon_desc),
            modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                isSheetOpen = true
            }
        )
    }
}