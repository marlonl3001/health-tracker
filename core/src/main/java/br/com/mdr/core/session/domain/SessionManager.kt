package br.com.mdr.core.session.domain

interface SessionManager {
    fun setToken(token: String)

    fun getToken(): String?

    fun clearSession()

    fun isLoggedIn(): Boolean

    fun isTokenExpired(token: String): Boolean
}
