package com.lapoushko.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * @author Lapoushko
 */
@Composable
fun DetailScreen(id: Long){
    Text(text = "$id")
}