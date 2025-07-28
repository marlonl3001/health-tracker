package br.com.mdr.auth.network.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
)
