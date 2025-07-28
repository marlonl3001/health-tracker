package br.com.mdr.auth.presentation.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mdr.auth.domain.usecase.LoginUseCase
import br.com.mdr.auth.presentation.login.state.LoginEffect
import br.com.mdr.auth.presentation.login.state.LoginEvent
import br.com.mdr.auth.presentation.login.state.LoginUiState
import br.com.mdr.core.session.domain.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val sessionManager: SessionManager,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set

    var effect = MutableSharedFlow<LoginEffect>()
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChange -> uiState = uiState.copy(email = event.email)
            is LoginEvent.OnPasswordChange -> uiState = uiState.copy(password = event.password)
            is LoginEvent.OnLoginClick -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            loginUseCase(uiState.email, uiState.password).collect { result ->
                result?.token?.let {
                    sessionManager.setToken(it)
                    effect.emit(LoginEffect.NavigateToHome)
                } ?: effect.emit(LoginEffect.ShowError("Invalid credentials"))
            }

            uiState = uiState.copy(isLoading = false)
        }
    }

    fun onLoginSuccess(token: String) {
        viewModelScope.launch {
            sessionManager.setToken(token)
            effect.emit(LoginEffect.NavigateToHome)
        }
    }
}
