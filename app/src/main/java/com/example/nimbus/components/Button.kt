package com.example.nimbus.components

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.R
import com.example.nimbus.Register2Screen
import com.example.nimbus.ui.theme.catamaranFontFamily

@Composable
fun Button(
    text: String,
    fontSize: Int,
    onClick: () -> Unit
) {
    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7425)),
        shape = RoundedCornerShape(11.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = catamaranFontFamily,
        )
    }
}