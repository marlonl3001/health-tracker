package br.com.mdr.auth.data.repository

import br.com.mdr.auth.domain.repository.AuthRepository
import br.com.mdr.auth.network.AuthApi
import br.com.mdr.auth.network.request.AuthRequest
import br.com.mdr.auth.network.response.AuthResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(private val api: AuthApi) : AuthRepository {
    override suspend fun signup(
        email: String,
        password: String,
    ): Boolean {
        return api.signup(AuthRequest(email, password))
    }

    override suspend fun login(
        email: String,
        password: String,
    ): Flow<AuthResponse> =
        flow {
            emit(api.login(AuthRequest(email, password)))
        }
}
