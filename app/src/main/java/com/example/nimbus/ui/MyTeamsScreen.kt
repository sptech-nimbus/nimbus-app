package com.example.nimbus.ui

import android.content.Intent
import com.example.nimbus.components.TeamCard
import com.example.nimbus.model.Team
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nimbus.ui.theme.NimbusTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.R
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily
import com.example.nimbus.GlobalData
import com.example.nimbus.RetrofitService
import com.example.nimbus.components.CustomLoading
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
    var isLoading by remember { mutableStateOf(true) }

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

        //lista estatica sem a lista de jogadores
        //val teams = listOf(
        //    Team("1", "Golden State Warriors", "Sub-20", "https://logodownload.org/wp-content/uploads/2019/06/golden-state-warriors-logo-2-1.png", "Rua Haddock Lobo", 0),
        //    Team("2", "Chigago Bulls", "Sub-20", "https://cdn.inspireuplift.com/uploads/images/seller_products/17516/1710752450_2.png", "Rua Haddock Lobo", 2),
        //    Team("3", "Los Angeles Lakers", "Sub-20", "https://static.vecteezy.com/system/resources/previews/027/127/539/non_2x/lakers-logo-lakers-icon-transparent-free-png.png", "Rua Haddock Lobo", 3),
        //    Team("4", "Corinthians", "Sub-20", "https://logodownload.org/wp-content/uploads/2016/11/Corinthians-logo-escudo.png", "Rua Haddock Lobo", 4),
        //    Team("5", "Flamengo", "Sub-20", "https://upload.wikimedia.org/wikipedia/commons/2/22/Logo_Flamengo_crest_1980-2018.png", "Rua Haddock Lobo", 5)
        //)
        val teams = remember { mutableStateListOf<Team>() }

        LaunchedEffect(Unit) {
            GlobalScope.launch {
                val teamApi = RetrofitService.getTeamsApi()
                try {
                    val getTeams = teamApi.getAllTeams()

                    if(getTeams.isSuccessful) {
                        Log.i("api", "Resposta: ${getTeams.body()}")

                        if(!getTeams.body().isNullOrEmpty()) {
                            teams.clear()
                            teams.addAll(getTeams.body()!!)
                        }
                    }
                    else {
                        Log.e("api", "Erro na resposta: ${getTeams.errorBody()!!.string()}")
                    }
                }
                catch (e: Exception) {
                    //tratamento de excessão
                }
                finally { isLoading = false }
            }
        }

        val scrollState = rememberLazyListState()

        if(isLoading) { CustomLoading() }
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
                items(items = teams.toList()) {
                    TeamCard(
                        team = it,
                        players = 10,
                        onClick = {
                            GlobalData.selectedTeam = it
                            context.startActivity(Intent(context, DashboardScreen::class.java))
                        }
                    )
                }
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