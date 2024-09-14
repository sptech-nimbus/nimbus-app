package com.example.nimbus

import android.content.Intent
import com.example.nimbus.components.TeamCard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nimbus.ui.theme.NimbusTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily
import kotlin.math.min

class MyTeamsScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_900)
            NimbusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyTeamsScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MyTeams() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
            .padding(start = 25.dp, top = 80.dp, end = 25.dp, bottom = 31.dp)
    ) {
        Text(
            text = stringResource(id = R.string.logged_as, "treinador"),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFontFamily,
        )

        Text(
            text = "Yuri Oliveira",
            color = Color(0xFFFF7425),
            fontSize = 36.sp,
            fontWeight = FontWeight.Black,
            fontFamily = catamaranFontFamily,
        )

        Text(
            text = stringResource(id = R.string.my_teams),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 66.dp),
            fontFamily = catamaranFontFamily,
        )

        //usado apenas para exibição dos itens em lista ⬇️
        data class Team(
            val teamName: String,
            val teamImage: String,
            val players: Int,
            val badge: Int? = null
        )
        val teams = listOf(
            Team("Golden State Warriors", "https://logodownload.org/wp-content/uploads/2019/06/golden-state-warriors-logo-2-1.png", 20, null),
            Team("Chigago Bulls", "https://cdn.inspireuplift.com/uploads/images/seller_products/17516/1710752450_2.png", 20, R.drawable.badge_2),
            Team("Los Angeles Lakers", "https://static.vecteezy.com/system/resources/previews/027/127/539/non_2x/lakers-logo-lakers-icon-transparent-free-png.png", 20, R.drawable.badge_3),
            Team("Corinthians", "https://logodownload.org/wp-content/uploads/2016/11/Corinthians-logo-escudo.png", 20, R.drawable.badge_5),
            Team("Flamengo", "https://upload.wikimedia.org/wikipedia/commons/2/22/Logo_Flamengo_crest_1980-2018.png", 20, R.drawable.badge_4)
        )
        //usado apenas para exibição dos itens em lista ⬆️
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
            items(teams) { team ->
                TeamCard(
                    teamName = team.teamName,
                    teamImage = team.teamImage,
                    players = team.players,
                    badge = team.badge?.let { painterResource(id = it) },
                    onClick = { context.startActivity(Intent(context, DashboardScreen::class.java)) }
                )
            }
        }

        Text(
            text = "Sair",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            fontFamily = catamaranFontFamily,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 25.dp)
        )
    }
}

@Composable
fun MyTeamsScreen(name: String, modifier: Modifier = Modifier) {
    MyTeams()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    NimbusTheme {
        MyTeamsScreen("Android")
    }
}