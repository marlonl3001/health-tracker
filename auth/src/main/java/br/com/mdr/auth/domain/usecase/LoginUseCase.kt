package br.com.mdr.auth.domain.usecase

import br.com.mdr.auth.domain.repository.AuthRepository
import br.com.mdr.auth.network.response.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase
@Inject
constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): Flow<AuthResponse?> =
        flow {
            repository.login(email, password)
                .catch {
                    emit(null)
                }
                .collect {
                    emit(it)
                }
        }
}
