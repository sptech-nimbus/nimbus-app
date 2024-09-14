package com.example.nimbus.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily
import com.example.nimbus.R

@Composable
fun PlayerCard(
    playerName: String = "João Pedro",
    playerPosition: String = "Pivô",
    playerImage: String = "https://s2-ge.glbimg.com/5B-_zhI6bYntSaxneldBiM-Ezxw=/33x44:650x576/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_bc8228b6673f488aa253bbcb03c80ec5/internal_photos/bs/2023/9/V/tuqz2GRDOIBJzRWAqinQ/gettyimages-1235563907.jpg",
    isStarting: Boolean = false,
    isInjuried: Boolean = false
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF212121),
        ),
        modifier = Modifier
            .size(width = 165.dp, height = 220.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            //Image(
            //    painter = painterResource(id = R.mipmap.jogador),
            //    contentDescription = "jogador",
            //    modifier = Modifier.clip(RoundedCornerShape(10.dp))
            //)

            AsyncImage(
                model = playerImage,
                contentDescription = stringResource(id = R.string.player_image, playerName),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .size(150.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = playerName,
                color = Color(0xFFFFEAE0),
                fontWeight = FontWeight.Bold,
                fontFamily = catamaranFontFamily
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    fontSize = 12.sp,
                    text = playerPosition,
                    color = Color(0xFFd7d7d7),
                    fontFamily = poppinsFontFamily
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    var starting: Int = R.drawable.star
                    var startingDesc: Int = R.string.not_starting_icon
                    var injury: Int = R.drawable.injury
                    var injuryDesc: Int = R.string.not_starting_icon

                    if(isStarting) {
                        starting = R.drawable.star_filled
                        startingDesc = R.string.is_injuried_icon
                    }


                    if(isInjuried) {
                        injury = R.drawable.injury_filled
                        injuryDesc = R.string.is_injuried_icon
                    }

                    Image(
                        painter = painterResource(id = starting),
                        contentDescription = stringResource(id = startingDesc),
                        modifier = Modifier.size(18.dp)
                    )

                    Image(
                        painter = painterResource(id = injury),
                        contentDescription = stringResource(id = injuryDesc),
                        modifier = Modifier.size(18.dp)
                    )

                }
            }
        }
    }
}
