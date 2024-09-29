package com.example.nimbus.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nimbus.GlobalData
import com.example.nimbus.R
import com.example.nimbus.api.RetrofitService
import com.example.nimbus.ui.components.AthleteCard
import com.example.nimbus.ui.components.BottomNavigation
import com.example.nimbus.ui.components.CustomLoading
import com.example.nimbus.ui.components.CustomTextFieldWithIcon
import com.example.nimbus.ui.components.TopNavigation
import com.example.nimbus.model.Athlete
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.ui.viewmodels.GlobalViewModel
import com.example.nimbus.ui.viewmodels.RosterScreenViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RosterScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_700)

            val viewModel by viewModels<RosterScreenViewModel>()
            val globalViewModel by viewModels<GlobalViewModel>()
            val globalUiState by globalViewModel.uiState.collectAsState()

            NimbusTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopNavigation(screen = stringResource(id = R.string.my_team), subtext = globalUiState.selectedTeam?.name) },
                    //bottomBar = { BottomNavigation(screen = stringResource(id = R.string.team)) },
                ) { innerPadding ->
                    Roster(
                        globalViewModel,
                        viewModel,
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Roster(
    globalViewModel: GlobalViewModel,
    viewModel: RosterScreenViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CustomTextFieldWithIcon(
            value = uiState.searchText,
            onValueChange = { viewModel.onSearchChange(it) },
            label = null,
            placeholder = stringResource(id = R.string.search_player),
            icon = R.drawable.search_icon,
            iconDescription = stringResource(id = R.string.search_icon_desc)
        )

        if(uiState.isLoading) {
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
                items(items = viewModel.filteredAthletes.toList().chunked(2)) { rowItems ->
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