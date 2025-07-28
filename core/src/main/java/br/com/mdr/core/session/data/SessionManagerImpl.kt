package br.com.mdr.core.session.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import br.com.mdr.core.session.domain.SessionManager
import com.auth0.android.jwt.JWT
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Date
import javax.inject.Inject

@Suppress("SwallowedException", "TooGenericExceptionCaught")
class SessionManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SessionManager {
    companion object {
        private const val PREF_NAME = "user_session"
        private const val TOKEN_KEY = "auth_token"
    }

    private val prefs by lazy {
        val masterKey =
            MasterKey
                .Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()

        EncryptedSharedPreferences.create(
            context,
            PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    override fun setToken(token: String) {
        prefs.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getToken(): String? {
        return prefs.getString(TOKEN_KEY, null)
    }

    override fun clearSession() {
        prefs.edit().clear().apply()
    }

    override fun isLoggedIn(): Boolean = getToken() != null

    override fun isTokenExpired(token: String): Boolean {
        return try {
            val jwt = JWT(token)
            val expiresAt = jwt.expiresAt
            expiresAt?.before(Date()) ?: true
        } catch (e: Exception) {
            true
        }
    }
}
