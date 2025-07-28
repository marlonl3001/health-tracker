package br.com.mdr.auth.network

import br.com.mdr.auth.network.request.AuthRequest
import br.com.mdr.auth.network.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/login")
    suspend fun login(@Body loginRequest: AuthRequest): AuthResponse

    @POST("signup")
    suspend fun signup(@Body request: AuthRequest): Boolean

}