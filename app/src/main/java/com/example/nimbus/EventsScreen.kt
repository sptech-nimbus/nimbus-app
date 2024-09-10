package com.example.nimbus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.components.BottomNavigation
import com.example.nimbus.components.CustomTextFieldWithIcon
import com.example.nimbus.components.Event
import com.example.nimbus.components.TopNavigation
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.ui.theme.catamaranFontFamily

class EventsScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_700)
            NimbusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Events(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Events(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopNavigation(screen = stringResource(id = R.string.my_team), subtext = "Golden State Warriors")

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.mipmap.calendar_image),
                contentDescription = "Imagem de calendario",
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Eventos do mês",
                fontFamily = catamaranFontFamily,
                color = Color(0xFFFFEAE0),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            val scrollState = rememberLazyListState()
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(505.dp)
                    .padding(top = 18.dp)
                    .weight(weight = 1f)
                    .drawFadingEdges(scrollState),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                state = scrollState
            ) {
                item {
                    Event(
                        type = "Treino",
                        secondText = "Rua Haddock Lobo",
                        challengerName = "Golden State Warriors",
                        date = "10/09/2024",
                        time = "20:30"
                    )
                }

                item {
                    Event(
                        type = "Partida",
                        secondText = "Chicago Bulls",
                        challengerName = "Golden State Warriors",
                        date = "10/09/2024",
                        time = "20:30"
                    )
                }

                item {
                    Event(
                        type = "Treino",
                        secondText = "Rua Haddock Lobo",
                        challengerName = "Golden State Warriors",
                        date = "10/09/2024",
                        time = "20:30"
                    )
                }

                item {
                    Event(
                        type = "Partida",
                        secondText = "Chicago Bulls",
                        challengerName = "Golden State Warriors",
                        date = "10/09/2024",
                        time = "20:30"
                    )
                }

                item {
                    Event(
                        type = "Treino",
                        secondText = "Rua Haddock Lobo",
                        challengerName = "Golden State Warriors",
                        date = "10/09/2024",
                        time = "20:30"
                    )
                }

                item {
                    Event(
                        type = "Partida",
                        secondText = "Chicago Bulls",
                        challengerName = "Golden State Warriors",
                        date = "10/09/2024",
                        time = "20:30"
                    )
                }
            }
        }

        BottomNavigation(screen = stringResource(id = R.string.events))
    }
}

@Preview(showBackground = true)
@Composable
fun EventsPreview() {
    NimbusTheme {
        Events()
    }
}