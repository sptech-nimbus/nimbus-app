package com.example.nimbus

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
        enableEdgeToEdge()
        setContent {
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

fun Modifier.drawFadingEdges(
    scrollableState: ScrollableState,
    topEdgeHeight: Dp = 18.dp,
    bottomEdgeHeight: Dp = 18.dp,
) = then(
    Modifier
        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()

            val topEdgeHeightPx = topEdgeHeight.toPx()
            val bottomEdgeHeightPx = bottomEdgeHeight.toPx()

            if (scrollableState.canScrollBackward && topEdgeHeightPx >= 1f) {
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = topEdgeHeightPx,
                    ),
                    blendMode = BlendMode.DstIn,
                )
            }

            if (scrollableState.canScrollForward && bottomEdgeHeightPx >= 1f) {
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Black, Color.Transparent),
                        startY = size.height - bottomEdgeHeightPx,
                        endY = size.height,
                    ),
                    blendMode = BlendMode.DstIn,
                )
            }
        }
)


@Composable
fun MyTeams() {
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
            val teamImage: Int,
            val players: Int,
            val badge: Int? = null
        )
        val teams = listOf(
            Team("Golden State Warriors", R.mipmap.gsw, 20, null),
            Team("Chigago Bulls", R.mipmap.chicago_bulls, 20, R.drawable.badge_2),
            Team("Los Angeles Lakers", R.mipmap.la_lakers, 20, R.drawable.badge_3),
            Team("Corinthians", R.mipmap.corinthians, 20, R.drawable.badge_5),
            Team("Flamengo", R.mipmap.flamengo, 20, R.drawable.badge_4)
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
                    badge = team.badge?.let { painterResource(id = it) }
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