package com.example.nimbus.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily

@Composable
fun Container(
    title: String? = null,
    subtitle: String? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        color = Color(0xFF212121),
        border = BorderStroke(1.dp, Color(0xFF353434))
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp, 16.dp)
                .fillMaxWidth()
        ) {
            if(title != null) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    color = Color(0xFFFFEAE0),
                    fontWeight = FontWeight.Bold,
                    fontFamily = catamaranFontFamily
                )

                Spacer(modifier = Modifier.height(5.dp))

                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        color = Color(0xFFBDBDBD),
                        fontFamily = poppinsFontFamily
                    )

                    Spacer(modifier = Modifier.height(5.dp))
                }
            }

            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContainerPreview() {
    Column {
        Container(
            title = "Titulo",
            subtitle = "Subtitulo"
        ) {
            Text(text = "alo")
        }
    }
}