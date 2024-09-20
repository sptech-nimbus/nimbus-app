package com.example.nimbus.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.R
import com.example.nimbus.ui.theme.catamaranFontFamily
import com.example.nimbus.ui.theme.poppinsFontFamily

@Composable
fun TopNavigation(
    screen: String,
    subtext: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(68.dp)
            .padding(20.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.hamburguer_menu_icon),
            contentDescription = stringResource(id = R.string.menu_icon_desc)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = screen.toUpperCase(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.orange_100),
                fontFamily = poppinsFontFamily
            )

            if(subtext != null) {
                Text(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    text = subtext,
                    color = colorResource(id = R.color.gray_400),
                    fontFamily = poppinsFontFamily,
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.bell_icon),
            contentDescription = stringResource(id = R.string.notification_icon_desc)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopNavigationPreview() {
    Column(
        modifier = Modifier.background(Color(0xFF212121))
    ) {
        TopNavigation(screen = "Home", "12/12/2024")
    }
}