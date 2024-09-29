package com.example.nimbus.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.R
import com.example.nimbus.ui.components.Button
import com.example.nimbus.ui.components.CustomTextField
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.ui.theme.catamaranFontFamily

class Register1Screen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_900)
            NimbusTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    CadastroScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CadastroScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
            .padding(30.dp, 55.dp, 30.dp, 15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(id = R.mipmap.logo),
            contentDescription = stringResource(id = R.string.logo_desc),
            modifier = modifier.size(90.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.need_info),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = catamaranFontFamily,
        )

        Text(
            text = stringResource(id = R.string.register).toUpperCase(),
            color = Color(0xFFFF7425),
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            fontFamily = catamaranFontFamily,
        )

        Spacer(modifier = Modifier.height(20.dp))

        CadastroForm()

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.has_account),
            color = Color(0xFFFFFFFF),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = catamaranFontFamily,
        )
        Text(
            text = stringResource(id = R.string.go_to_login),
            color = Color(0xFFFF7425),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = catamaranFontFamily,
        )
    }
}

@Composable
fun CadastroForm(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            value = nome,
            onValueChange = { nome = it },
            label = stringResource(id = R.string.name),
            placeholder = "Seu nome",
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(
            value = sobrenome,
            onValueChange = { sobrenome = it },
            label = stringResource(id = R.string.last_name),
            placeholder = "Seu sobrenome"
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(
            value = dataNascimento,
            onValueChange = { dataNascimento = it },
            label = stringResource(id = R.string.birth_date),
            placeholder = "01/01/2000"
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            text = stringResource(id = R.string.continue_),
            fontSize = 24,
            onClick = { context.startActivity(Intent(context, Register2Screen::class.java)) }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro() {
    NimbusTheme {
        CadastroScreen()
    }
}