package com.jv23.scribbledash.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jv23.scribbledash.presentation.screens.canvasdrawing.CanvasDrawingScreenRoot
import com.jv23.scribbledash.presentation.screens.difficultylevel.DifficultyLevelScreenRoot
import com.jv23.scribbledash.presentation.screens.home.HomeScreenRoot
import kotlinx.serialization.Serializable



@Serializable
data object HomeRoute

@Serializable
data object CanvasDrawingScreenRoute

@Serializable
data object DifficultyLevelScreenRoute

@Serializable
data object ChartScreenRoute

@Composable
fun ScribbleDashNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreenRoot(
                modifier = modifier,
                navController = navController
            )
        }
        composable<DifficultyLevelScreenRoute> {
            DifficultyLevelScreenRoot(
                onNavigateBack = {navController.navigateUp()
                }
            )
        }
        composable<CanvasDrawingScreenRoute> {
            CanvasDrawingScreenRoot(
                onNavigateBack = {navController.navigateUp()
                }
            )
        }
    }
}