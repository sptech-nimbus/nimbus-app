package com.example.nimbus.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.nimbus.GlobalData
import com.example.nimbus.RetrofitService
import com.example.nimbus.components.TeamCard
import com.example.nimbus.model.Team
import com.example.nimbus.ui.ui.theme.NimbusTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NimbusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainActivity(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainActivity(modifier: Modifier = Modifier) {
    val teamsList = remember { mutableStateListOf<Team>() }
    var isLoading by remember { mutableStateOf(true) }

    Column {
        Text(text = "Testando essa bomba")

        LaunchedEffect(Unit) {
            GlobalScope.launch {
                val teamApi = RetrofitService.getTeamsApi()
                try {
                    val getTeams = teamApi.getAllTeams()

                    if(getTeams.isSuccessful) {
                        Log.i("api", "Resposta: ${getTeams.body()}")

                        if(!getTeams.body().isNullOrEmpty()) {
                            teamsList.clear()
                            teamsList.addAll(getTeams.body()!!)
                        }
                    }
                    else {
                        Log.e("api", "Erro na resposta: ${getTeams.errorBody()!!.string()}")
                    }
                }
                catch (e: Exception) {
                    //tratamento de erro
                }
                finally { isLoading = false }
            }
        }
        
        if(isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn(modifier = Modifier.fillMaxHeight(0.8f)) {
                items(items = teamsList.toList()) {
                    TeamCard(
                        team = it,
                        players = 10,
                        onClick = {}
                    )
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