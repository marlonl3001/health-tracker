package br.com.mdr.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.mdr.auth.presentation.login.ui.LoginScreen

const val AUTH_ROUTE = "auth"

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    composable(route = AUTH_ROUTE) {
        LoginScreen(
            onLoginSuccess = {
                navController.navigate("home") {
                    popUpTo(AUTH_ROUTE) {
                        inclusive = true
                    }
                }
            },
        )
    }
}
