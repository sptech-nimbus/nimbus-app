package com.example.nimbus.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nimbus.R
import com.example.nimbus.ui.components.DeleteDialog
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.viewmodels.GlobalViewModel
import com.example.nimbus.ui.viewmodels.MyTeamsScreenViewModel

@Composable
fun Profile(
    globalViewModel: GlobalViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(1f)
            .padding(20.dp, 0.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val athleteName = "Yuri Oliveira"


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
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .border(width = 3.dp, color = Color(0xFFff7425), shape = CircleShape)
                    .size(185.dp)
                    .padding(5.dp)
            ) {
                AsyncImage(
                    model = "https://www.chem.indiana.edu/wp-content/uploads/2023/09/defaultpic.jpg",
                    contentDescription = "jogador",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(200.dp))
                        .border(width = 3.dp, color = Color(0xFF131313), shape = CircleShape)
                        .size(180.dp)
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {

            InfoLine(label = stringResource(id = R.string.age), info = "19 anos")
            InfoLine(label = stringResource(id = R.string.birth_date_short), info = "10/10/2004")
            InfoLine(label = stringResource(id = R.string.phone) + " 1", info = "(11) 99999-9999")
            InfoLine(
                label = stringResource(id = R.string.email),
                info = stringResource(id = R.string.email_placeholder)
            )
            InfoLine(
                label = stringResource(id = R.string.address),
                info = "Rua Haddock Lobo, 525..."

            )
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = stringResource(id = R.string.my_teams),
                fontFamily = catamaranFontFamily,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.orange_100)
            )

            //val scrollState = rememberLazyListState()
            //LazyRow(
            //    horizontalArrangement = Arrangement.spacedBy(30.dp),
            //    state = scrollState,
            //    modifier = Modifier.drawFadingEdgesHorizontal(scrollState)
            //) {
            //    items(items = teamUiState.teams.toList()) {
            //        AsyncImage(
            //            model = it.picture,
            //            contentDescription = "Imagem do time ${it.name}",
            //            modifier = Modifier.size(100.dp)
            //        )
            //    }
            //}
        }

    }
}