package com.jv23.scribbledash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jv23.scribbledash.navigation.ScribbleDashNavigation
import com.jv23.scribbledash.presentation.components.ScribbleDashBottomNavBar
import com.jv23.scribbledash.presentation.components.ScribbleDashButton
import com.jv23.scribbledash.presentation.components.ScribbleDashTopAppBar
import com.jv23.scribbledash.presentation.screens.canvasdrawing.CanvasDrawingScreenRoot
import com.jv23.scribbledash.presentation.screens.difficultylevel.DifficultyLevelScreenRoot
import com.jv23.scribbledash.presentation.screens.home.HomeScreen
import com.jv23.scribbledash.ui.theme.BackgroundBrush
import com.jv23.scribbledash.ui.theme.OnPrimary
import com.jv23.scribbledash.ui.theme.Primary
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.SurfaceHigh

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScribbleDashTheme {
                ScribbleDashNavigation()
                /*Scaffold(
                    containerColor = Color.Transparent,
                    bottomBar = { ScribbleDashBottomNavBar(navController = NavController(this))}

                ) { innerPadding ->
                    CanvasDrawingScreenRoot(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }*/
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello $name!",
            style = MaterialTheme.typography.displayLarge,
            modifier = modifier

        )
        Spacer(modifier = Modifier.height(16.dp))
        ScribbleDashButton(
            modifier = Modifier,
            surfaceColor = Primary,
            backgroundColor = SurfaceHigh,
            buttonText = "Start!",
            buttonTextColor = OnPrimary,
            enabled = true
        ) { }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ScribbleDashTheme {
        Greeting("Android")
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    modifier: Modifier
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text("My App Title")
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Open navigation drawer */ }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    // Add any actions here, like icons for settings, search, etc.
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text("This is the main content of the screen.")
                // Add more content here...
            }
        }
    )
}

@Composable
fun RootScreen(
    modifier: Modifier = Modifier
) {


}