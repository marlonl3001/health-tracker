package br.com.mdr.auth.domain.repository

import br.com.mdr.auth.network.response.AuthResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signup(email: String, password: String): Boolean
    suspend fun login(email: String, password: String): Flow<AuthResponse>
}