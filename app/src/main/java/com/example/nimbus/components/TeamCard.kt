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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.R
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily

@Composable
fun TeamCard(
    teamName: String,
    teamImage: Int,
    players: Int,
    badge: Painter? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFF212121))
            .padding(16.dp, 20.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = teamImage),
            contentDescription = stringResource(id = R.string.team_image_desc, teamName),
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterVertically),
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier
                .weight(1f),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = teamName,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = catamaranFontFamily,
                )

                Spacer(modifier = Modifier.width(14.dp))

                if(badge != null) {
                    Image(
                        painter = badge,
                        contentDescription = "Icon",
                        modifier = Modifier.size(30.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.players_amount, players),
                color = Color(0xFF818181),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = poppinsFontFamily,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardPreview() {
    NimbusTheme {
        TeamCard(
            teamName = "Golden State Warriors",
            teamImage = R.mipmap.gsw,
            players = 20,
            badge = painterResource(id = R.drawable.badge_4)
        )
    }
}