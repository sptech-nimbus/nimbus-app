package com.example.nimbus.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nimbus.R
import com.example.nimbus.api.game1
import com.example.nimbus.api.team1
import com.example.nimbus.ui.components.BottomNavigation
import com.example.nimbus.ui.components.Container
import com.example.nimbus.ui.components.MatchCard
import com.example.nimbus.ui.components.PendingResult
import com.example.nimbus.ui.components.StatCard
import com.example.nimbus.ui.components.TopNavigation
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DashboardScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_700)

            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val currentDate = LocalDate.now().format(formatter)

            NimbusTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    //bottomBar = { BottomNavigation(screen = stringResource(id = R.string.home)) },
                ) { innerPadding ->
                    Dashboard( modifier = Modifier.padding(innerPadding) )
                }
            }
         }
    }
}

data class StatItem(
    val label: String,
    val value: String,
    val subvalue: String? = null
)

@Composable
fun Dashboard(modifier: Modifier = Modifier) {
    val statsList = listOf(
        StatItem("Vitórias", "32"),
        StatItem("Pontos", "92.5"),
        StatItem("Rebotes", "30.2"),
        StatItem("Faltas", "12.3"),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AsyncImage(
                model = "https://upload.wikimedia.org/wikipedia/en/thumb/e/ed/Los_Angeles_Clippers_%282024%29.svg/1200px-Los_Angeles_Clippers_%282024%29.svg.png",
                contentDescription = stringResource(id = R.string.challenger_logo, "Golden State Warriors"),
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
            
            Column(
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Golden State Warriors",
                    fontSize = 22.sp,
                    color = colorResource(id = R.color.orange_100),
                    fontWeight = FontWeight.Bold,
                    fontFamily = catamaranFontFamily
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Conquistador",
                        color = colorResource(id = R.color.orange_100),
                        fontFamily = catamaranFontFamily
                    )

                    Image(
                        painter = painterResource(id = R.drawable.badge_3),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        val scrollState = rememberLazyListState()
        Row(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = "Estatísticas",
                    fontFamily = poppinsFontFamily,
                    color = colorResource(id = R.color.gray_400),
                    fontWeight = FontWeight.Medium
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    state = scrollState,
                    modifier = Modifier.drawFadingEdgesHorizontal(scrollState)
                ) {
                    items(items = statsList.toList()) {
                        StatCard(label = it.label, value = it.value)
                    }
                }
            }
        }

        MatchCard(
            adversaryName = "Los Angeles Clippers",
            adversaryLogo = "https://upload.wikimedia.org/wikipedia/en/thumb/e/ed/Los_Angeles_Clippers_%282024%29.svg/1200px-Los_Angeles_Clippers_%282024%29.svg.png",
            dateTime = "10/09/2024 - 20:30",
            place = "Rua Haddock Lobo"
        )

        PendingResult(
            adversaryTeam = team1,
            onConfirmClick = { /*TODO*/ },
            onDismissClick = { /*TODO*/ },
            isChallenger = true,
            game = game1
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Container(
                modifier = Modifier.width(160.dp).clip(RoundedCornerShape(20.dp)),
                color = colorResource(id = R.color.orange_500)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.basketball),
                        contentDescription = null,
                        tint = colorResource(id = R.color.orange_100)
                    )
                    Text(
                        text = "Jogar partida",
                        color = colorResource(id = R.color.orange_100),
                        fontFamily = catamaranFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Container(
                modifier = Modifier.width(160.dp).clip(RoundedCornerShape(20.dp)),
                color = colorResource(id = R.color.orange_500)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.robot),
                        contentDescription = null,
                        tint = colorResource(id = R.color.orange_100)
                    )
                    Text(
                        text = "Analise por IA",
                        color = colorResource(id = R.color.orange_100),
                        fontFamily = catamaranFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview4() {
    NimbusTheme {
        Dashboard()
    }
}