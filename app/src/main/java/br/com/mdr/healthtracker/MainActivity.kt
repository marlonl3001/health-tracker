package br.com.mdr.healthtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.mdr.auth.navigation.AUTH_ROUTE
import br.com.mdr.auth.navigation.authGraph
import br.com.mdr.core.session.domain.SessionManager
import br.com.mdr.healthtracker.ui.theme.HealthTrackerTheme
import br.com.mdr.home.navigation.homeGraph
import br.com.mdr.home.presentation.HomeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val startDestination = runBlocking {
            val token = sessionManager.getToken()
            if (token == null) {
                AUTH_ROUTE
            } else if (sessionManager.isTokenExpired(token)) {
                sessionManager.clearSession()
                AUTH_ROUTE
            } else {
                "home"
            }
        }
        setContent {
            HealthTrackerTheme {
                MainNavigation(startDestination)
            }
        }
    }
}

@Composable
fun MainNavigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(navController)
        homeGraph()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HealthTrackerTheme {
        HomeScreen()
    }
}