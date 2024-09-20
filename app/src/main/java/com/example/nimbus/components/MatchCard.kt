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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nimbus.R

@Composable
fun MatchCard(
    challengedName: String,
    challengedLogo: String,
    challengerName: String,
    challengerLogo: String,
    dateTime: String,
    place: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        color = colorResource(id = R.color.gray_700),
        border = BorderStroke(1.dp, colorResource(id = R.color.gray_500))
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp, 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = challengedLogo,
                contentDescription = stringResource(id = R.string.challenger_logo, challengerName),
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = stringResource(id = R.string.versus).toUpperCase(),
                color = colorResource(id = R.color.orange_100),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            AsyncImage(
                model = challengerLogo,
                contentDescription = stringResource(id = R.string.challenged_logo, challengedName),
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Fit
            )
            Column {
                Text(
                    text = dateTime,
                    color = colorResource(id = R.color.orange_100),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = place,
                    color = colorResource(id = R.color.orange_100),
                    fontSize = 14.sp,
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
            challengerLogo = "https://logodownload.org/wp-content/uploads/2019/06/golden-state-warriors-logo-2-1.png",
            challengedLogo = "https://logodownload.org/wp-content/uploads/2016/11/Corinthians-logo-escudo.png",
            dateTime = "10/09/2024 - 20:30",
            place = "Rua Haddock Lobo"
        )
    }
}