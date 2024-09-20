package com.example.nimbus.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.R

@Composable
fun Event(
    type: String,
    challengerName: String,
    secondText: String,
    date: String,
    time: String,
) {
    val icon = if(type == "Partida") R.drawable.match_icon else R.drawable.trainning_icon
    val iconDesc = if(type == "Partida") "Ícone de partida" else "Ícone de treino"
    val lineColor = if(type == "Partida") colorResource(id = R.color.orange_500) else colorResource(id = R.color.green)
    val textColor = if(type == "Partida") colorResource(id = R.color.orange_100) else colorResource(id = R.color.gray_300)

    Row(
        modifier = Modifier
            .width(350.dp),
            //.padding(horizontal = 62.dp, vertical = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = iconDesc,
                modifier = Modifier.size(30.dp)
            )
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(47.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(lineColor)
            )
            Column {
                Text(
                    text = challengerName,
                    color = colorResource(id = R.color.orange_100),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = secondText,
                    color = textColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = date,
                color = colorResource(id = R.color.orange_100),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = time,
                color = colorResource(id = R.color.orange_100),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventPreview() {
    Column(modifier = Modifier.background(Color.Black)) {
        Event(
            type = "Partida",
            secondText = "Chicago Bulls",
            challengerName = "Golden State Warriors",
            date = "10/09/2024",
            time = "20:30"
        )

        Event(
            type = "Treino",
            secondText = "Rua Haddock Lobo",
            challengerName = "Golden State Warriors",
            date = "10/09/2024",
            time = "20:30"
        )
    }
}