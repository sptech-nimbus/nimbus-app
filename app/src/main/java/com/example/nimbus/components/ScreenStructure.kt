package com.example.nimbus.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nimbus.R

@Composable
fun ScreenStructure(
    screen: String,
    topText: String? = null,
    topSubtext: String,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF131313))
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val topBarText = if(topText == null) screen else topText

        TopNavigation(
            screen = topBarText,
            subtext = topSubtext
        )

        content()

        BottomNavigation(screen = screen)
    }
}