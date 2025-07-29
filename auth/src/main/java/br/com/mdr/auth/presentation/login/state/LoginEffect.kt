package br.com.mdr.auth.presentation.login.state

sealed class LoginEffect {
    data class ShowError(val message: String) : LoginEffect()

    object NavigateToHome : LoginEffect()
}
