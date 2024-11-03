package com.example.nimbus.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nimbus.R
import com.example.nimbus.model.getDrawerItems
import com.example.nimbus.ui.components.BottomNavigation
import com.example.nimbus.ui.components.TopNavigation
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily
import com.example.nimbus.ui.ui.theme.NimbusTheme
import com.example.nimbus.ui.viewmodels.AthleteINfoModelFactory
import com.example.nimbus.ui.viewmodels.AthleteInfoViewModel
import com.example.nimbus.ui.viewmodels.AthleteInjuryScreenViewModel
import com.example.nimbus.ui.viewmodels.GlobalViewModel
import com.example.nimbus.ui.viewmodels.MyTeamsScreenViewModel
import com.example.nimbus.ui.viewmodels.RosterModelFactory
import com.example.nimbus.ui.viewmodels.RosterScreenViewModel
import com.example.nimbus.utils.SharedPreferencesManager
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_700)

            val drawerItems = getDrawerItems()

            val pagerState = rememberPagerState(initialPage = 2) {5}
            val athletePagerState = rememberPagerState() {3}
            val coroutineScope = rememberCoroutineScope()

            val context = LocalContext.current
            val sharedPrefManager = SharedPreferencesManager(context)

            NimbusTheme {
                //val globalViewModel by viewModels<GlobalViewModel>()
                val globalViewModel: GlobalViewModel by viewModels {
                    ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                }
                val rosterViewModel: RosterScreenViewModel = viewModel(
                    factory = RosterModelFactory(sharedPrefManager)
                )
                val athleteInfoViewModel: AthleteInfoViewModel = viewModel(
                    factory = AthleteINfoModelFactory(sharedPrefManager, globalViewModel)
                )
                val athleteInjuryViewModel by viewModels<AthleteInjuryScreenViewModel>()

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }

                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet(
                            drawerContainerColor = colorResource(id = R.color.gray_900),
                            modifier = Modifier.width(300.dp)
                        ){
                            Spacer(modifier = Modifier.height(30.dp))

                            Column(
                                Modifier.padding(horizontal = 40.dp, vertical = 20.dp)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.logged_as, "treinador"),
                                    color = colorResource(id = R.color.orange_100),
                                    fontFamily = catamaranFontFamily,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = sharedPrefManager.getUsername(),
                                    color = colorResource(id = R.color.orange_500),
                                    fontFamily = catamaranFontFamily,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 30.sp
                                )

                                Text(
                                    text = sharedPrefManager.getEmail(),
                                    color = colorResource(id = R.color.gray_400),
                                    fontFamily = catamaranFontFamily
                                )
                            }

                            Spacer(modifier = Modifier.height(50.dp))

                            drawerItems.forEachIndexed { index, it ->
                                val brush = if(index == selectedItemIndex) {
                                    listOf(
                                        Color(0x42FF7425),
                                        Color(0x12994516)
                                    )
                                } else {
                                    listOf(
                                        Color(0xFF131313),
                                        Color(0xFF131313)
                                    )
                                }

                                Row {
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Box(
                                        Modifier
                                            .clip(CircleShape)
                                            .background(
                                                brush = Brush.horizontalGradient(
                                                    colors = brush
                                                )
                                            )
                                            .width(250.dp)
                                    ) {
                                        NavigationDrawerItem(
                                            label = {
                                                val color = if(index == selectedItemIndex) R.color.orange_500 else R.color.gray_400
                                                Text(
                                                    text = it.title,
                                                    fontFamily = catamaranFontFamily,
                                                    fontWeight = FontWeight.Bold,
                                                    color = colorResource(id = color)
                                                )
                                            },
                                            selected = index == selectedItemIndex,
                                            onClick = {
                                                selectedItemIndex = index
                                                scope.launch {
                                                    drawerState.close()
                                                }
                                            },
                                            icon = if (index == selectedItemIndex) it.selectedIcon else it.unselectedIcon,
                                            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                                            colors = NavigationDrawerItemDefaults.colors(
                                                selectedContainerColor = Color.Transparent,
                                                unselectedContainerColor = colorResource(id = R.color.gray_900)
                                            )
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(50.dp))
                        }
                    },
                    drawerState = drawerState,
                    gesturesEnabled = true
                ) {
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
                                },
                                onMenuClick = {
                                    scope.launch {
                                        drawerState.open()
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
                                                athleteInfoViewModel,
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
                                2 -> Dashboard(globalViewModel = globalViewModel)
                                3 -> Events()
                                4 -> Profile(globalViewModel)
                            }
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