package com.example.nimbus.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.R

@Composable
fun MatchCard(
    challengedName: String,
    challengedLogo: Int,
    challengerName: String,
    challengerLogo: Int,
    dateTime: String,
    place: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        color = Color(0xFF212121),
        border = BorderStroke(1.dp, Color(0xFF353434))
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp, 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = challengerLogo),
                contentDescription = stringResource(id = R.string.challenger_logo, challengerName),
                modifier = Modifier.size(58.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = stringResource(id = R.string.versus).toUpperCase(),
                color = Color(0xFFFFEAE0),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Image(
                painter = painterResource(id = challengedLogo),
                contentDescription = stringResource(id = R.string.challenged_logo, challengedName),
                modifier = Modifier.size(58.dp),
                contentScale = ContentScale.Fit
            )
            Column {
                Text(
                    text = dateTime,
                    color = Color(0xFFFFEAE0),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = place,
                    color = Color(0xFFFFEAE0),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchCardPrewiew() {
    Column {
        MatchCard(
            challengerName = "Golden State Warriors",
            challengedName = "Corinthians",
            challengerLogo = R.mipmap.gsw,
            challengedLogo = R.mipmap.corinthians,
            dateTime = "10/09/2024 - 20:30",
            place = "Rua Haddock Lobo"
        )
    }
}