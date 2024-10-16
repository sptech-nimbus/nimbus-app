package com.example.nimbus.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.model.Team
import com.example.nimbus.R
import com.example.nimbus.api.game1
import com.example.nimbus.api.team1
import com.example.nimbus.model.Game
import com.example.nimbus.ui.theme.poppinsFontFamily

@Composable
fun PendingResult(
    adversaryTeam: Team,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit,
    isChallenger: Boolean,
    game: Game,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        color = colorResource(id = R.color.gray_700),
        border = BorderStroke(1.dp, colorResource(id = R.color.gray_500))
    ) {
        Row(
            modifier = Modifier
                .padding(14.dp, 10.dp)
                .fillMaxWidth()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = adversaryTeam.name,
                    color = colorResource(R.color.orange_100),
                    fontSize = 18.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold
                )

                //row com o resultado do jogo
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = game.gameResult?.chalengedPoints.toString(),
                        fontFamily = poppinsFontFamily,
                        color = colorResource(id = R.color.orange_500),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Black
                    )
                    
                    Text(
                        text = "X",
                        color = colorResource(R.color.orange_500),
                        fontWeight = FontWeight.Black
                    )
                    
                    Text(
                        text = game.gameResult?.challengerPoints.toString(),
                        fontFamily = poppinsFontFamily,
                        color = colorResource(id = R.color.orange_500),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }

            Row {
                game.gameResult?.let {
                    ResultCard(
                        gameResult = it,
                        16,
                        Modifier.width(100.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PendingItemsPreview() {
    PendingResult(
        adversaryTeam = team1,
        onConfirmClick = { /*TODO*/ },
        onDismissClick = { /*TODO*/ },
        isChallenger = true,
        game = game1
    )
}