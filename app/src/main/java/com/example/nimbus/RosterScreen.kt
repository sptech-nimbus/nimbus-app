package com.example.nimbus

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.nimbus.components.BottomNavigation
import com.example.nimbus.components.Container
import com.example.nimbus.components.CustomTextFieldWithIcon
import com.example.nimbus.components.MatchCard
import com.example.nimbus.components.TopNavigation
import com.example.nimbus.ui.theme.NimbusTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily

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
            )  { context.startActivity(Intent(context, PlayerInfomationScreen::class.java)) },
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

@Composable
fun PlayerCard(
    playerName: String = "João Pedro",
    playerPosition: String = "Pivô"
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF212121),
        ),
        modifier = Modifier
            .size(width = 165.dp, height = 220.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.jogador),
                contentDescription = "jogador",
                modifier = Modifier.clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = playerName,
                color = Color(0xFFFFEAE0),
                fontWeight = FontWeight.Bold,
                fontFamily = catamaranFontFamily
            )

            Text(
                fontSize = 12.sp,
                text = playerPosition,
                color = Color(0xFFd7d7d7),
                fontFamily = poppinsFontFamily
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RosterPreview() {
    NimbusTheme {
        Roster()
    }
}