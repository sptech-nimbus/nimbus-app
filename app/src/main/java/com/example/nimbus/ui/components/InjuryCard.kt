package com.example.nimbus.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.nimbus.model.Injury
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import com.example.nimbus.R
import com.example.nimbus.ui.theme.catamaranFontFamily
import java.time.Duration

@Composable
fun InjuryCard(
    injury: Injury
) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val initialDate = injury.initialDate.format(formatter)
    val finalDate = injury.finalDate.format(formatter)
    val dayDiff = Duration.between(injury.initialDate.atStartOfDay(), injury.finalDate.atStartOfDay()).toDays()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(colorResource(id = R.color.gray_700))
            .padding(16.dp, 8.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(.5f),
            //horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = injury.type,
                color = colorResource(id = R.color.orange_500),
                fontWeight = FontWeight.Bold,
                fontFamily = catamaranFontFamily,
                fontSize = 16.sp
            )
        }

        Column(
            //horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //label
            Text(
                text = initialDate,
                color = colorResource(id = R.color.orange_100),
                fontWeight = FontWeight.Bold,
            )

            //value
            Text(
                text = "$dayDiff dia(s) fora",
                color = colorResource(id = R.color.orange_100),
                fontWeight = FontWeight.Bold,
                fontFamily = catamaranFontFamily,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InjuryCardPreview() {
    val example = Injury("1", "Lesão muscular", LocalDate.of(2024, 10, 1), LocalDate.of(2024, 12, 17))
    
    InjuryCard(injury = example)
}