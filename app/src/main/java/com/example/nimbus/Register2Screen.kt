package com.example.nimbus

import com.example.nimbus.components.CustomTextField
import com.example.nimbus.components.Button

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nimbus.ui.theme.NimbusTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Register2Screen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NimbusTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    CadastroScreen2(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CadastroScreen2(modifier: Modifier = Modifier) {
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
            fontWeight = FontWeight.Normal
        )

        Text(
            text = stringResource(id = R.string.register).toUpperCase(),
            color = Color(0xFFFF7425),
            fontSize = 32.sp,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(20.dp))

        CadastroForm2()
    }
}

@Composable
fun CadastroForm2(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(id = R.string.email),
            placeholder = stringResource(id = R.string.email_placeholder)
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(
            value = phone,
            onValueChange = { phone = it },
            label = stringResource(id = R.string.phone),
            placeholder = stringResource(id = R.string.phone_placeholder)
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(id = R.string.password),
            placeholder = stringResource(id = R.string.password_placeholder)
        )

        Spacer(modifier = Modifier.height(30.dp))

        CustomTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = stringResource(id = R.string.confirm_password),
            placeholder = stringResource(id = R.string.password_placeholder)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            text = stringResource(id = R.string.continue_),
            fontSize = 24,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro2() {
    NimbusTheme {
        CadastroScreen()
    }
}