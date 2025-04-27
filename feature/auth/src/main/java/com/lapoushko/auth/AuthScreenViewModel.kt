package com.lapoushko.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * @author Lapoushko
 */
class AuthScreenViewModel: ViewModel() {
    private var _state = MutableAuthScreenState()
    val state = _state as AuthScreenState


    fun updateEmail(text: String){
        if (!text.any { it in ALPH_CYRILLIC }) _state.email = text
    }

    fun updatePassword(text: String){
        _state.password = text
    }

    fun checkCorrectInputs(){
        val isCorrectEmail = Patterns.EMAIL_ADDRESS.matcher(state.email).matches()
        _state.isCorrectInput = state.password.isNotEmpty() && isCorrectEmail
    }

    fun onToLink(context: Context, link: AuthScreenState.Link){
        val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(link.url) }
        context.startActivity(intent)
    }

    companion object{
        private val ALPH_CYRILLIC = '\u0400'..'\u04FF'
    }

    private class MutableAuthScreenState: AuthScreenState{
        override var email: String by mutableStateOf("text@text.text")
        override var password: String by mutableStateOf("1")
        override var isCorrectInput: Boolean by mutableStateOf(false)
        override val link: AuthScreenState.Link by mutableStateOf(AuthScreenState.Link.VK)
    }
}

interface AuthScreenState{
    val email: String
    val password: String
    val isCorrectInput: Boolean
    val link: Link

    enum class Link(val url: String){
        VK("https://vk.com/"),
        OK("https://ok.ru/")
    }
}