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
import com.jv23.scribbledash.presentation.screens.statistics.StatisticsScreenRoot
import kotlinx.serialization.Serializable



@Serializable
data object HomeScreenRoute

@Serializable
data object CanvasDrawingScreenRoute

@Serializable
data object DifficultyLevelScreenRoute

@Serializable
data object StatisticsScreenRoute

@Composable
fun ScribbleDashNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreenRoute
    ) {
        composable<HomeScreenRoute> {
            HomeScreenRoot(
                modifier = modifier,
                navController = navController,
                onNavigateToDifficultyLevelScreen = {
                    navController.navigate(DifficultyLevelScreenRoute)
                }
            )
        }
        composable<DifficultyLevelScreenRoute> {
            DifficultyLevelScreenRoot(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateTo = {
                    navController.navigate(CanvasDrawingScreenRoute)
                }
            )
        }
        composable<CanvasDrawingScreenRoute> {
            CanvasDrawingScreenRoot(
                onNavigateBack = {navController.popBackStack(HomeScreenRoute, false)
                }
            )
        }
        composable<StatisticsScreenRoute> {
            StatisticsScreenRoot(
                navController = navController
            )
        }
    }
}