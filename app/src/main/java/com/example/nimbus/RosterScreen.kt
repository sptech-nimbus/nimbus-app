package com.example.nimbus

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nimbus.components.BottomNavigation
import com.example.nimbus.components.CustomTextFieldWithIcon
import com.example.nimbus.components.TopNavigation
import com.example.nimbus.ui.theme.NimbusTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalContext
import com.example.nimbus.components.PlayerCard

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
    val context = LocalContext.current
    val interactionSource= remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
            .padding(top = 10.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { context.startActivity(Intent(context, PlayerInfomationScreen::class.java)) },
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopNavigation(screen = stringResource(id = R.string.my_team), subtext = "Golden State Warriors")

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
                placeholder = "Pesquisar um jogador...",
                icon = R.drawable.search_icon,
                iconDescription = stringResource(id = R.string.search_icon_desc)
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
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PlayerCard()
                        PlayerCard()
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PlayerCard()
                        PlayerCard()
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PlayerCard()
                        PlayerCard()
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