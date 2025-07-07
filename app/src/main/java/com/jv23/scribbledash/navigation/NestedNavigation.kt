package com.jv23.scribbledash.navigation

import androidx.annotation.NavigationRes
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.jv23.scribbledash.R
import com.jv23.scribbledash.presentation.components.BottomNavigationItem
import com.jv23.scribbledash.presentation.screens.canvasdrawing.CanvasDrawingScreenRoot
import com.jv23.scribbledash.presentation.screens.difficultylevel.DifficultyLevelScreenRoot
import com.jv23.scribbledash.presentation.screens.home.HomeScreen
import com.jv23.scribbledash.presentation.screens.home.HomeScreenRoot
import com.jv23.scribbledash.presentation.screens.statistics.StatisticsScreen
import com.jv23.scribbledash.presentation.screens.statistics.StatisticsScreenRoot
import com.jv23.scribbledash.ui.theme.BrandTertiary
import com.jv23.scribbledash.ui.theme.ChartIcon
import com.jv23.scribbledash.ui.theme.HomeIcon
import com.jv23.scribbledash.ui.theme.SurfaceHigh
import com.jv23.scribbledash.ui.theme.SurfaceLow
import com.jv23.scribbledash.ui.theme.SurfaceLowest
import kotlinx.serialization.Serializable


@Serializable
object DrawingGraph

@Serializable
object StatisticsGraph

@Serializable
object DeepGraph

@Serializable
data object HomeRoute

@Serializable
data object CanvasDrawingRoute

@Serializable
data object DifficultyLevelRoute

@Serializable
data object StatisticsRoute

fun NavGraphBuilder.deepGraph(modifier: Modifier, navController: NavController){
    navigation<DeepGraph>(startDestination = deepGraph(modifier, navController)){
        composable<DifficultyLevelRoute> {
            DifficultyLevelScreenRoot(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateTo = {
                    navController.navigate(CanvasDrawingRoute)
                }
            )
        }

        composable<CanvasDrawingRoute> {
            CanvasDrawingScreenRoot(
                onNavigateBack = {navController.popBackStack(HomeRoute, false)
                }
            )
        }
    }
}

fun NavGraphBuilder.drawingGraph(modifier: Modifier, navController: NavController){
    navigation<DrawingGraph>(startDestination = HomeRoute) {
        composable<HomeRoute> {

            HomeScreen(
                modifier = modifier,
                onGameModeClick = {
                    navController.navigate(DifficultyLevelRoute)
                }

            )
        }
        composable<DifficultyLevelRoute> {
            DifficultyLevelScreenRoot(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateTo = {
                    navController.navigate(CanvasDrawingRoute)
                }
            )
        }

        composable<CanvasDrawingRoute> {
            CanvasDrawingScreenRoot(
                onNavigateBack = {navController.popBackStack(HomeRoute, false)
                }
            )
        }
    }
}

fun NavGraphBuilder.statisticsGraph(modifier: Modifier){
    navigation<StatisticsGraph>(startDestination = StatisticsRoute){
        composable<StatisticsRoute> {
            StatisticsScreen(
                modifier = modifier

            )

        }
    }
}

data class TopLevelRoute<T: Any>(val name: String, val route: T, val icon: @Composable () -> Unit)

//val bottomDestinations = listOf(DrawingGraph, StatisticsGraph)

val topLevelRoutes = listOf(
    TopLevelRoute(
        name = "Chart",
        route = StatisticsGraph,
        icon =  {
            Icon(imageVector = ChartIcon, contentDescription = null, modifier = Modifier.size(32.dp))
        }
    ),
    TopLevelRoute(
        name = "Home",
        route = DrawingGraph,
        icon =  {
            Icon(imageVector = HomeIcon, contentDescription = null, modifier = Modifier.size(32.dp))
        }
    )
)

@Composable
fun TopLevelNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val topLevelDestinations = setOf(
        HomeRoute::class,
        StatisticsRoute::class
    )
    val isInTopLevelDestination = topLevelDestinations.any { currentDestination?.hasRoute(it) == true }

    Scaffold(
        bottomBar = {
            if (isInTopLevelDestination){
                NavigationBar(
                    containerColor = SurfaceHigh
                ) {
                    topLevelRoutes.forEach { topLevelRoute ->
                        NavigationBarItem(
                            icon = topLevelRoute.icon,
                            label = { },
                            selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class)} == true,
                            onClick = {
                                navController.navigate(topLevelRoute.route) {
                                    // Pop up to the start destination of the graph to avoid building
                                    // up a large stack of destinations on the back stack as users
                                    // select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid mulitiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemColors(
                                selectedIconColor = if(topLevelRoute.name == "Chart" ){
                                    BrandTertiary
                                } else {
                                   MaterialTheme.colorScheme.primary
                                },


                                selectedTextColor = MaterialTheme.colorScheme.onSurface,
                                selectedIndicatorColor = Color.Transparent,
                                unselectedIconColor = SurfaceLowest,
                                unselectedTextColor = SurfaceLowest,
                                disabledIconColor = SurfaceLow,
                                disabledTextColor = SurfaceLow
                            )
                        )
                    }
                }
            }

        }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = DrawingGraph,
            modifier = modifier
                .padding(innerPadding),
            enterTransition = {EnterTransition.None},
            exitTransition = {ExitTransition.None},
            popEnterTransition = { EnterTransition.None},
            popExitTransition = {ExitTransition.None},



        ) {
            drawingGraph(modifier, navController)
            statisticsGraph(modifier)

        }
    }
}




/*@Composable
fun NestedNavigation(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DrawingGraph) {
        drawingGraph(modifier, navController)
        statisticsGraph(modifier)
        deepGraph(modifier, navController)

    }

}*/

