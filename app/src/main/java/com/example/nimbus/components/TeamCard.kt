package com.example.nimbus.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nimbus.R
import com.example.nimbus.model.Team
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily

@Composable
fun TeamCard(
    team: Team,
    players: Int,
    onClick: () -> Unit
) {
    val interactionSource= remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(colorResource(id = R.color.gray_700))
            .padding(16.dp, 20.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() },
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = team.picture,
            contentDescription = stringResource(id = R.string.team_image_desc, team.name),
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
                    text = team.name,
                    color = colorResource(id = R.color.orange_100),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = catamaranFontFamily,
                )

                Spacer(modifier = Modifier.width(14.dp))

                val badgeValue = when(team.level) {
                    1 -> R.drawable.badge_1
                    2 -> R.drawable.badge_2
                    3 -> R.drawable.badge_3
                    4 -> R.drawable.badge_4
                    else -> null
                }

                if(badgeValue != null) {
                    Image(
                        painter = painterResource(id = badgeValue),
                        contentDescription = "Icon",
                        modifier = Modifier.size(30.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.players_amount, players),
                color = colorResource(id = R.color.gray_400),
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
        val time = Team("1", "Golden State Warriors", "Sub-20", "https://logodownload.org/wp-content/uploads/2019/06/golden-state-warriors-logo-2-1.png", "Rua Haddock Lobo", 0)

        TeamCard(
            team = time,
            players = 20,
            onClick = {}
        )
    }
}