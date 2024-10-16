package com.example.nimbus.ui.screens

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.nimbus.R
import com.example.nimbus.ui.components.BottomNavigation
import com.example.nimbus.ui.components.TopNavigation
import com.example.nimbus.ui.ui.theme.NimbusTheme
import com.example.nimbus.ui.viewmodels.AthleteInjuryScreenViewModel
import com.example.nimbus.ui.viewmodels.GlobalViewModel
import com.example.nimbus.ui.viewmodels.MyTeamsScreenViewModel
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
            val athletePagerState = rememberPagerState() {3}
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current

            NimbusTheme {
                val globalViewModel by viewModels<GlobalViewModel>()
                val rosterViewModel by viewModels<RosterScreenViewModel>()
                val athleteInjuryViewModel by viewModels<AthleteInjuryScreenViewModel>()
                val myTeamsViewModel by viewModels<MyTeamsScreenViewModel>()
                val sharedPref = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopNavigation(
                            selectedPage = pagerState.currentPage,
                            globalViewModel = globalViewModel,
                            athletePage = athletePagerState.currentPage,
                            onBackClick = { page ->
                                coroutineScope.launch {
                                    athletePagerState.animateScrollToPage(page)
                                }
                            }
                        )
                     },
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
                            1 -> {
                                HorizontalPager(
                                    state = athletePagerState,
                                    userScrollEnabled = false
                                ) { page2 ->
                                    when(page2) {
                                        0 -> Roster(
                                            globalViewModel,
                                            rosterViewModel,
                                            onAthleteClick = { athletePage ->
                                                coroutineScope.launch {
                                                    athletePagerState.animateScrollToPage(athletePage)
                                                }
                                            }
                                        )
                                        1 -> PlayerInformation(
                                            globalViewModel,
                                            onPageClick = { athletePage ->
                                                coroutineScope.launch {
                                                    athletePagerState.animateScrollToPage(athletePage)
                                                }
                                            })
                                        2 -> AthleteInjuryScreen(globalViewModel, athleteInjuryViewModel)
                                    }
                                }
                            }
                            2 -> Dashboard()
                            3 -> Events()
                            4 -> Profile(globalViewModel, myTeamsViewModel)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    NimbusTheme {
        MainActivity()
    }
}