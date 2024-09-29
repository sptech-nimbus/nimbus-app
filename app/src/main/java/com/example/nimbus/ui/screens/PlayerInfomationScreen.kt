package com.example.nimbus.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nimbus.GlobalData
import com.example.nimbus.R
import com.example.nimbus.ui.components.BottomNavigation
import com.example.nimbus.ui.components.TopNavigation
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.ui.theme.catamaranFontFamily
import kotlinx.coroutines.launch

class PlayerInfomationScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            window.statusBarColor = getColor(R.color.gray_900)
            window.navigationBarColor = getColor(R.color.gray_700)

            NimbusTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { TopNavigation(screen = stringResource(id = R.string.info)) },
                    bottomBar = {
                        BottomNavigation(
                            selectedPage = 1,
                            onItemClick = {},
                            screen = stringResource(id = R.string.team)
                        )
                    }
                ) { innerPadding ->
                    PlayerInformation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun DottedLine() {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)) {
        val canvasWidth = size.width
        val dotWidth = 2.dp.toPx() // Largura do ponto
        val spaceWidth = 5.dp.toPx() // Espaçamento entre pontos

        var startX = 0f
        while (startX < canvasWidth) {
            drawCircle(
                color = Color(0xFFFFEAE0), // Cor dos pontos
                radius = dotWidth / 2,
                center = Offset(startX + dotWidth / 2, size.height / 2)
            )
            startX += dotWidth + spaceWidth
        }
    }
}

@Composable
fun InfoLine(
    label: String? = "N/A",
    info: String? = "N/A"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        if (label != null) {
            Text(
                text = label.toUpperCase(),
                fontWeight = FontWeight.Black,
                color = Color(0xFFFFEAE0),
                fontFamily = catamaranFontFamily,
                fontSize = 16.sp
            )
        }

        if (info != null) {
            Text(
                text = info,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFFFEAE0),
                fontFamily = catamaranFontFamily,
                fontSize = 16.sp
            )
        }
    }
    DottedLine()
}

@Composable
fun PlayerInformation(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(1f)
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val athleteName = "${GlobalData.selectedAthlete?.firstName} ${GlobalData.selectedAthlete?.lastName}"
        Text(
            text = athleteName.toUpperCase(),
            fontWeight = FontWeight.Black,
            color = Color(0xFFFFEAE0),
            fontFamily = catamaranFontFamily,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier= Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Altura".toUpperCase(),
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFFFEAE0),
                    fontFamily = catamaranFontFamily,
                    fontSize = 16.sp
                )
                Text(
                    text = "1.87m".toUpperCase(),
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFFEAE0),
                    fontFamily = catamaranFontFamily,
                    fontSize = 16.sp
                )
            }
            Box(
                modifier = Modifier
                    .border(width = 3.dp, color = Color(0xFFff7425), shape = CircleShape)
                    .size(155.dp)
                    .padding(5.dp)
            ) {
                AsyncImage(
                    model = GlobalData.selectedAthlete?.picture,
                    contentDescription = "jogador",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(200.dp))
                        .border(width = 3.dp, color = Color(0xFF131313), shape = CircleShape)
                        .size(150.dp)
                )
            }

            Column(
                modifier= Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Peso".toUpperCase(),
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFFFEAE0),
                    fontFamily = catamaranFontFamily,
                    fontSize = 16.sp
                )
                Text(
                    text = "87kg".toUpperCase(),
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFFFEAE0),
                    fontFamily = catamaranFontFamily,
                    fontSize = 16.sp
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            InfoLine(label = stringResource(id = R.string.starting), info = "Sim")
            InfoLine(label = stringResource(id = R.string.position), info = GlobalData.selectedAthlete?.position)
            InfoLine(label = stringResource(id = R.string.age), info = "19 anos")
            InfoLine(label = stringResource(id = R.string.category), info = GlobalData.selectedAthlete?.category)
            InfoLine(label = stringResource(id = R.string.birth_date_short), info = "10/10/2004")
            InfoLine(label = stringResource(id = R.string.phone) + " 1", info = "(11) 99999-9999")
            InfoLine(label = stringResource(id = R.string.phone) + " 2", info = "Indefinido")
            InfoLine(label = stringResource(id = R.string.email), info = stringResource(id = R.string.email_placeholder))
            InfoLine(label = stringResource(id = R.string.address), info = "Rua Haddock Lobo, 525...")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    NimbusTheme {
        PlayerInformation()
    }
}