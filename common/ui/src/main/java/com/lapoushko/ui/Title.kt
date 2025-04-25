package com.lapoushko.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lapoushko.ui.theme.Typography

/**
 * @author Lapoushko
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Title(textAlign: TextAlign, text: String){
    LargeTopAppBar(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        title = {
            Text(text = text, textAlign = textAlign, style = Typography.titleSmall)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TitlePreview(){
    Title(textAlign = TextAlign.Center, text = "Тысячи курсов в одном месте")
}