package com.lapoushko.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * @author Lapoushko
 */
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel = koinViewModel()){
    Text(text = "Профиль")
}

class ProfileViewModel(): ViewModel(){
    init {
        println("pizdec")
    }
}