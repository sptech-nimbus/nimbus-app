package com.example.nimbus.components

import android.content.Intent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.ui.DashboardScreen
import com.example.nimbus.ui.EventsScreen
import com.example.nimbus.ui.theme.poppinsFontFamily
import com.example.nimbus.R
import com.example.nimbus.ui.RosterScreen
import com.example.nimbus.ui.theme.NimbusTheme

@Composable
fun BottomNavigation(
    screen: String
) {
    var screenName: String = screen
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.gray_700))
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .padding(horizontal = 16.dp, vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(screenName == "Chat")
            ActiveNavItem(
                iconRes = R.drawable.chat_icon_active,
                iconDesc = R.string.chat,
                screenName = screenName
            )
        else NavItem(
            iconRes = R.drawable.chat_icon,
            iconDesc = R.string.chat,
            onClick = {}
        )

        if(screenName == "Time")
            ActiveNavItem(
                iconRes = R.drawable.team_icon_active,
                iconDesc = R.string.team,
                screenName = screenName
            )
        else NavItem(
            iconRes = R.drawable.team_icon,
            iconDesc = R.string.team,
            onClick = { context.startActivity(Intent(context, RosterScreen::class.java)) }
        )

        if(screenName == "Home")
            ActiveNavItem(
                iconRes = R.drawable.house_icon_active,
                iconDesc = R.string.home,
                screenName = screenName
            )
        else NavItem(
            iconRes = R.drawable.house_icon,
            iconDesc = R.string.home,
            onClick = { context.startActivity(Intent(context, DashboardScreen::class.java)) }
        )

        if(screenName == "Eventos")
            ActiveNavItem(
                iconRes = R.drawable.calender_icon_active,
                iconDesc = R.string.events,
                screenName = screenName
            )
        else NavItem(
            iconRes = R.drawable.calender_icon,
            iconDesc = R.string.events,
            onClick = { context.startActivity(Intent(context, EventsScreen::class.java)) }
        )

        if(screenName == "Conta")
            ActiveNavItem(
                iconRes = R.drawable.user_icon_active,
                iconDesc = R.string.account,
                screenName = screenName
            )
        else NavItem(
            iconRes = R.drawable.user_icon,
            iconDesc = R.string.account,
            onClick = {}
        )
    }
}

@Composable
fun NavItem(iconRes: Int, iconDesc: Int, onClick: () -> Unit) {
    val interactionSource= remember { MutableInteractionSource() }

    Image(
        painter = painterResource(id = iconRes),
        contentDescription = stringResource(id = iconDesc),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(35.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                onClick()
            },
    )
}

@Composable
fun ActiveNavItem(iconRes: Int, screenName: String, iconDesc: Int) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(99.237.dp))
            .background(
                brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                    colors = listOf(
                        Color(0x42FF7425),
                        Color(0x12994516)
                    )
                )
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = stringResource(id = iconDesc),
            modifier = Modifier.size(24.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = screenName,
            color = colorResource(id = R.color.orange_500),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = poppinsFontFamily
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BottomNavigationPreview() {
    NimbusTheme {
        Column {
            BottomNavigation(screen = stringResource(id = R.string.chat))
            BottomNavigation(screen = stringResource(id = R.string.team))
            BottomNavigation(screen = stringResource(id = R.string.home))
            BottomNavigation(screen = stringResource(id = R.string.events))
            BottomNavigation(screen = stringResource(id = R.string.account))
        }
    }
}