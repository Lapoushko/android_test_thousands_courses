package com.lapoushko.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lapoushko.ui.theme.LightGray
import com.lapoushko.ui.theme.PlaceholderInputColor
import com.lapoushko.ui.theme.Typography
import com.lapoushko.ui.theme.White

/**
 * @author Lapoushko
 */
@Composable
fun LoginTextField(
    title: String,
    placeholder: String,
    text: String,
    onValueChanged: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = title, style = Typography.titleMedium, color = White)

        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightGray,
                unfocusedContainerColor = LightGray,
                disabledContainerColor = LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            value = text,
            onValueChange = { onValueChanged(it) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
            placeholder = {
                Text(
                    text = placeholder,
                    style = Typography.bodyMedium,
                    color = PlaceholderInputColor
                )
            },
            shape = RoundedCornerShape(30.dp),
        )
    }
}

@Preview(showBackground = false)
@Composable
private fun LoginTextFieldPreview() {
    LoginTextField(
        text = "",
        placeholder = "example@gmail.com",
        title = "Email",
        onValueChanged = {}
    )
}