package com.example.nimbus.components

import android.service.autofill.CustomDescription
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nimbus.CadastroScreen
import com.example.nimbus.R
import com.example.nimbus.ui.theme.NimbusTheme
import com.example.nimbus.ui.theme.catamaranFontFamily

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onPassVisibilityChange: (Boolean) -> Unit = {}

) {
    Column {
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )
    }
}

@Composable
fun CustomTextFieldWithIcon(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: Int,
    iconDescription: String
) {
    Column {
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            leadingIcon = {
                icon?.let {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = iconDescription
                    )
                }
            }
        )
    }
}

@Composable
fun CustomTextFieldPassword(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    onTrailingIconClick: (() -> Unit)? = null,
    isPasswordVisible: Boolean = false,
    onPassVisibilityChange: (Boolean) -> Unit = {}

) {
    Column {
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = catamaranFontFamily,
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.lock_icon),
                    contentDescription = stringResource(id = R.string.lock_icon_desc)
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    onTrailingIconClick?.invoke()
                    onPassVisibilityChange(!isPasswordVisible)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye_icon),
                        contentDescription = stringResource(id = R.string.eye_icon_desc)
                    )
                }
            },
            visualTransformation = if (!isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        )
    }
}

//Função para exibição dos componentes
@Composable
fun TextFields() {
    Column {
        var value1 by remember { mutableStateOf("") }
        var value2 by remember { mutableStateOf("") }
        var value3 by remember { mutableStateOf("") }

        CustomTextField(
            value = value1,
            onValueChange = { value1 = it },
            label = "Label",
            placeholder = "Placeholder"
        )

        CustomTextFieldWithIcon(
            value = value2,
            onValueChange = { value2 = it },
            label = "Label",
            placeholder = "Placeholder",
            icon = R.drawable.envelope_icon,
            iconDescription = stringResource(id = R.string.email_icon_desc),
        )

        CustomTextFieldPassword(
            value = value3,
            onValueChange = { value3 = it },
            label = "Label",
            placeholder = "Placeholder"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldsPreview() {
    NimbusTheme {
        TextFields()
    }
}