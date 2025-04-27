package com.lapoushko.auth

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lapoushko.ui.LoginTextField
import com.lapoushko.ui.NextButton
import com.lapoushko.ui.Title
import com.lapoushko.ui.theme.Green
import com.lapoushko.ui.theme.OKBrush
import com.lapoushko.ui.theme.Stroke
import com.lapoushko.ui.theme.Typography
import com.lapoushko.ui.theme.VKColor
import com.lapoushko.ui.theme.White
import com.lapoushko.ui.theme.horizontalPadding
import com.lapoushko.ui.theme.verticalArrangement
import org.koin.androidx.compose.koinViewModel

/**
 * @author Lapoushko
 */
@Composable
fun AuthScreen(
    onClick: () -> Unit,
    viewModel: AuthScreenViewModel = koinViewModel()
) {
    val state = viewModel.state

    Column(
        modifier = Modifier
            .padding(horizontal = horizontalPadding)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(verticalArrangement)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
            Auth(
                email = state.email,
                password = state.password,
                updateEmail = { viewModel.updateEmail(it) },
                updatePassword = { viewModel.updatePassword(it) }
            )
            LoginButtons(
                onClick = {
                    viewModel.checkCorrectInputs()
                    if (state.isCorrectInput) {
                        onClick()
                    }
                }
            )
            HorizontalDivider(color = Stroke)
            SocialNetworksButtons(
                onToVk = { viewModel.onToLink(context = it, link = AuthScreenState.Link.VK) },
                onToOk = { viewModel.onToLink(context = it, link = AuthScreenState.Link.OK) },
            )
        }
    }
}

@Composable
private fun Auth(
    email: String,
    password: String,
    updateEmail: (String) -> Unit,
    updatePassword: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(28.dp)) {
        Title(
            textAlign = TextAlign.Start,
            text = "Вход"
        )
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            LoginTextField(
                title = "Email",
                placeholder = "example@gmail.com",
                text = email,
                onValueChanged = { updateEmail(it) }
            )
            //В ТЗ не сказано, нужно ли прятать символы пароля
            LoginTextField(
                title = "Пароль",
                placeholder = "Введите пароль",
                text = password,
                onValueChanged = { updatePassword(it) },
                keyboardType = KeyboardType.Password
            )
        }
    }
}

@Composable
private fun LoginButtons(onClick: () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        NextButton(onClick = onClick, title = "Вход")
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Нету аккаунта? ", //правильно "нет", но оставил как в тз
                    style = Typography.labelLarge,
                    fontSize = 12.sp,
                    color = White
                )
                Text(
                    modifier = Modifier.clickable { /*Todo*/ },
                    text = "Регистрация",
                    style = Typography.labelLarge,
                    fontSize = 12.sp,
                    color = Green
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Забыл пароль",
                textAlign = TextAlign.Center,
                style = Typography.labelLarge,
                fontSize = 12.sp,
                color = Green
            )
        }
    }
}

@Composable
private fun SocialNetworksButtons(
    onToVk: (Context) -> Unit,
    onToOk: (Context) -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            onClick = {
                onToVk(context)
            }, colors = ButtonDefaults.buttonColors(
                containerColor = VKColor,
                contentColor = White
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.vk),
                contentDescription = null,
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .background(
                    brush = OKBrush, shape = ButtonDefaults.shape
                ),
            onClick = {
                onToOk(context)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Icon(
                painter = painterResource(R.drawable.ok),
                contentDescription = null,
                tint = White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthScreenPreview() {
    AuthScreen({})
}