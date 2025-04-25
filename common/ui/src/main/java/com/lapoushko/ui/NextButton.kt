package com.lapoushko.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lapoushko.ui.theme.Green
import com.lapoushko.ui.theme.Typography
import com.lapoushko.ui.theme.White

/**
 * @author Lapoushko
 */
@Composable
fun NextButton(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Green, contentColor = White),
    ){
        Text(text = title, style = Typography.labelLarge, modifier = Modifier.padding(vertical = 10.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun NextButtonPreview() {
    NextButton(
        onClick = {},
        title = "Продолжить"
    )
}