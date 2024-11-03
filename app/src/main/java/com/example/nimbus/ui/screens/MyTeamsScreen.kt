package com.example.nimbus.ui.screens

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nimbus.R
import com.example.nimbus.api.RetrofitService
import com.example.nimbus.ui.components.CustomLoading
import com.example.nimbus.ui.components.TeamCard
import com.example.nimbus.model.Team
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily
import com.example.nimbus.ui.viewmodels.GlobalViewModel
import com.example.nimbus.ui.viewmodels.LoginViewModel
import com.example.nimbus.ui.viewmodels.MyTeamsModelFactory
import com.example.nimbus.ui.viewmodels.MyTeamsScreenViewModel
import com.example.nimbus.utils.SharedPreferencesManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyTeamsScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {

            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_900)

            val globalViewModel: GlobalViewModel by viewModels {
                ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            }

            NimbusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyTeams(
                        modifier = Modifier.padding(innerPadding),
                        globalViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun MyTeams(modifier: Modifier, globalViewModel: GlobalViewModel) {
    val context = LocalContext.current
    val sharedPrefManager = SharedPreferencesManager(context)

    val viewModel: MyTeamsScreenViewModel = viewModel(
        factory = MyTeamsModelFactory(sharedPrefManager)
    )

    val uiState by viewModel.uiState.collectAsState()

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
            text = sharedPrefManager.getUsername(),
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

        val scrollState = rememberLazyListState()
        if(uiState.isLoading) { CustomLoading() }
        else {
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
                items(items = uiState.teams.toList()) {
                    TeamCard(
                        team = it,
                        onClick = {
                            globalViewModel.selectTeam(it)
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                    )
                }
            }
        }

        Text(
            text = "Adicionar time",
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