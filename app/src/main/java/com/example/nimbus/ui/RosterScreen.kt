package com.example.nimbus.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nimbus.components.BottomNavigation
import com.example.nimbus.components.CustomTextFieldWithIcon
import com.example.nimbus.components.TopNavigation
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.R
import com.example.nimbus.components.AthleteCard
import com.example.nimbus.GlobalData
import com.example.nimbus.RetrofitService
import com.example.nimbus.components.CustomLoading
import com.example.nimbus.model.Athlete
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RosterScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_700)
            NimbusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Roster(
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
fun Roster(modifier: Modifier = Modifier) {
    var searchPlayer by remember { mutableStateOf("") }
    val athletes = remember { mutableStateListOf<Athlete>() }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        GlobalScope.launch {
            val athleteApi = RetrofitService.getAthletesApi()
            try {
                val getAthletes = athleteApi.getAllAthletes()

                if(getAthletes.isSuccessful) {
                    if(!getAthletes.body().isNullOrEmpty()) {
                        athletes.clear()
                        athletes.addAll(getAthletes.body()!!)
                    }
                }
                else {
                    Log.e("api", "Erro na resposta: ${getAthletes.errorBody()!!.string()}")
                }
            }
            catch (e: Exception) {
                //tratamento de excessão
            }
            finally { isLoading = false }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopNavigation(screen = stringResource(id = R.string.my_team), subtext = GlobalData.selectedTeam?.name)

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextFieldWithIcon(
                value = searchPlayer,
                onValueChange = { searchPlayer= it },
                label = null,
                placeholder = stringResource(id = R.string.search_player),
                icon = R.drawable.search_icon,
                iconDescription = stringResource(id = R.string.search_icon_desc)
            )

            if(isLoading) {
                CustomLoading()
            } else {
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
                    items(items = athletes.toList().chunked(2)) { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            rowItems.forEach { AthleteCard(it) }
                        }
                    }
                }
            }
        }

        BottomNavigation(screen = stringResource(id = R.string.team))
    }
}

@Preview(showBackground = true)
@Composable
fun RosterPreview() {
    NimbusTheme {
        Roster()
    }
}