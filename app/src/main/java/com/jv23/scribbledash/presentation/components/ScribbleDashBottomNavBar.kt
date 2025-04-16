package com.jv23.scribbledash.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jv23.scribbledash.navigation.DifficultyLevelScreenRoute
import com.jv23.scribbledash.navigation.HomeScreenRoute
import com.jv23.scribbledash.ui.theme.ChartIcon
import com.jv23.scribbledash.ui.theme.HomeIcon
import com.jv23.scribbledash.ui.theme.SurfaceHigh
import com.jv23.scribbledash.ui.theme.SurfaceLow
import com.jv23.scribbledash.ui.theme.SurfaceLowest

data class BottomNavigationItem(
    val title: String,
    val icon: @Composable () -> Unit,
    val route: Any
)

@Composable
fun ScribbleDashBottomNavBar(
    modifier: Modifier = Modifier,
    navController:NavController
) {
    val items = listOf(
        BottomNavigationItem(
            title = "Charts",
            icon = {
                Icon(
                    imageVector = ChartIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            },
            route = DifficultyLevelScreenRoute
        ),
        BottomNavigationItem(
            title = "Home",
            icon = {
                Icon(
                    imageVector = HomeIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)
                )
            },
            route = HomeScreenRoute
        )
    )
    var selectedItemIndex by remember {
        mutableIntStateOf(1)
    }
    NavigationBar(
        containerColor = SurfaceHigh
    ) {
        items.forEachIndexed{ index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route) {
                        popUpTo(navController.graph
                            .startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = item.icon,
                label = { },
                alwaysShowLabel = false,
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
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