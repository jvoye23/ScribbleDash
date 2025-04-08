package com.jv23.scribbledash.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jv23.scribbledash.presentation.components.ScribbleDashBottomNavBar
import com.jv23.scribbledash.ui.theme.OneRoundWonderIcon
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.Success

@Composable
fun HomeScreenRoot(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            ScribbleDashBottomNavBar(
                modifier = Modifier,
                navController = navController
            )
        }

    ) { innerPadding ->
        HomeScreen(
            modifier = Modifier
                .padding(innerPadding)
        )

    }


}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,

) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)

    ) {
        //1st Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopStart)
                .offset{ IntOffset(0,0.dp.roundToPx())}
                .height(72.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                text = "ScribbleDash",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        //Center Row
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset{ IntOffset(0,-150.dp.roundToPx())}
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier
                ,

                ) {
                Text(
                    modifier = Modifier

                    ,
                    text = "Start Drawing!",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier


            ) {
                Text(
                    modifier = Modifier

                    ,
                    text = "Select game mode",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }

        }

        //Center Rectangle
        Row(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .height(128.dp)

                .clip(RoundedCornerShape(20.dp))
                .background(Success)
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
                .clickable(onClick = {})
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically


        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.5f),

                ) {
                Text(
                    modifier = Modifier
                        .padding(start = 22.dp),
                    text = "One Round Wounder",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,


                    )

            }
            Column() {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .fillMaxWidth(0.5f),
                    imageVector = OneRoundWonderIcon,
                    contentDescription = null
                )

            }


        }
    }


}

@Preview
@Composable
private fun HomeScreenPreview() {
    ScribbleDashTheme {
        HomeScreen(
            modifier = Modifier

        )

    }

}