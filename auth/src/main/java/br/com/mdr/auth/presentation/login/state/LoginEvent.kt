package br.com.mdr.auth.presentation.login.state

sealed class LoginEvent {
    data class OnEmailChange(val email: String): LoginEvent()
    data class OnPasswordChange(val password: String): LoginEvent()
    object OnLoginClick: LoginEvent()
}