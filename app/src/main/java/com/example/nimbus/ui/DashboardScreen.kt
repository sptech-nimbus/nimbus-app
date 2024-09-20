package com.example.nimbus.ui

import com.example.nimbus.components.TopNavigation
import com.example.nimbus.components.BottomNavigation
import com.example.nimbus.components.MatchCard
import com.example.nimbus.components.Container
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nimbus.R
import com.example.nimbus.ui.theme.NimbusTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DashboardScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_700)
            NimbusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Dashboard(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
         }
    }
}

@Composable
fun Dashboard(modifier: Modifier = Modifier) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val currentDate = LocalDate.now().format(formatter)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopNavigation(screen = "Home", subtext = currentDate.toString())

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            MatchCard(
                challengerName = "Golden State Warriors",
                challengedName = "Corinthians",
                challengerLogo = "https://logodownload.org/wp-content/uploads/2019/06/golden-state-warriors-logo-2-1.png",
                challengedLogo = "https://logodownload.org/wp-content/uploads/2016/11/Corinthians-logo-escudo.png",
                dateTime = "10/09/2024 - 20:30",
                place = "Rua Haddock Lobo"
            )

            Container(
                title = "Partidas jogadas"
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.padding(50.dp, 0.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pie_chart),
                            contentDescription = ""
                        )
                    }
                }
            }
           Row(
                //modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Container (
                    title = "20 faltas p/ jogo",
                    //subtitle = "Nos últimos 9 jogos",
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.line_chart),
                        contentDescription = ""
                    )
                }
              Container(
                    title = "76 pontos p/ jogo",
                    //subtitle = "Nos últimos 9 jogos",
                    modifier = Modifier.weight(1f)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.line_chart2),
                        contentDescription = ""
                    )
                }
            }
           Container(
                title = "20 rebotes por jogo"
            ) {
                Image(
                    painter = painterResource(id = R.drawable.bar_chat),
                    contentDescription = ""
                )
            }
        }

        BottomNavigation(screen = stringResource(id = R.string.home))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview4() {
    NimbusTheme {
        Dashboard()
    }
}