package com.example.nimbus.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nimbus.GlobalData
import com.example.nimbus.R
import com.example.nimbus.ui.components.BottomNavigation
import com.example.nimbus.ui.components.TopNavigation
import com.example.nimbus.ui.ui.theme.NimbusTheme
import com.example.nimbus.ui.viewmodels.GlobalViewModel
import com.example.nimbus.ui.viewmodels.RosterScreenViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_700)

            val pagerState = rememberPagerState(initialPage = 2) {5}
            val coroutineScope = rememberCoroutineScope()

            NimbusTheme {
                val globalViewModel by viewModels<GlobalViewModel>()
                val rosterViewModel by viewModels<RosterScreenViewModel>()
                val uiState by globalViewModel.uiState.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopNavigation(stringResource(id = R.string.home), subtext = uiState.selectedTeam?.name) },
                    bottomBar = {
                    BottomNavigation(
                        screen = stringResource(id = R.string.events),
                        selectedPage = pagerState.currentPage,
                        onItemClick = { page ->
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(page)
                            }
                        }
                    ) },
                ) { innerPadding ->

                    HorizontalPager(pagerState, Modifier.padding(innerPadding)) { page ->
                        when(page) {
                            0 -> Chat()
                            1 -> Roster(globalViewModel, rosterViewModel)
                            2 -> Dashboard()
                            3 -> Events()
                            4 -> Profile()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainActivity(modifier: Modifier = Modifier) {
    Text(text = "Olá")
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    NimbusTheme {
        MainActivity()
    }
}