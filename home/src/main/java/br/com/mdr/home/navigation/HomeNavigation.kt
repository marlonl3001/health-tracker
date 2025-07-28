package br.com.mdr.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.mdr.home.presentation.HomeScreen

fun NavGraphBuilder.homeGraph() {
    composable("home") {
        HomeScreen()
    }
}