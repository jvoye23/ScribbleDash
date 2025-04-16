package com.jv23.scribbledash.presentation.screens.difficultylevel

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jv23.scribbledash.ui.theme.BeginnerIcon
import com.jv23.scribbledash.ui.theme.ChallengingIcon
import com.jv23.scribbledash.ui.theme.CloseIcon
import com.jv23.scribbledash.ui.theme.MasterIcon
import com.jv23.scribbledash.ui.theme.OnBackgroundVariant
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.Success

@Composable
fun DifficultyLevelScreenRoot(
    modifier: Modifier = Modifier,
    onNavigateBack: ()-> Unit,
    onNavigateTo: () -> Unit
) {
    Scaffold () { innerPadding ->
        DifficultyLevelScreen(
            modifier = Modifier
                .padding(innerPadding),
            onCloseIconClick = onNavigateBack,
            onNavigateToDrawScreen = onNavigateTo
        )

    }


}

@Composable
fun DifficultyLevelScreen(
    modifier: Modifier = Modifier,
    onCloseIconClick: () -> Unit,
    onNavigateToDrawScreen: () -> Unit
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
                .offset{ IntOffset(0,0.dp.roundToPx()) }
                .height(72.dp)
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickable { onCloseIconClick() },
                imageVector = CloseIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        //Center Row
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset{ IntOffset(0,-190.dp.roundToPx()) }
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier
                ,

                ) {
                Text(
                    modifier = Modifier                    ,
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
                    modifier = Modifier                    ,
                    text = "Choose a difficulty setting",
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
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.background)
                .clickable(onClick = {})
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically


        ) {
            Column(
                modifier = Modifier
                    .clickable { onNavigateToDrawScreen() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(

                    imageVector = BeginnerIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
                            shape = CircleShape,
                            ambientColor = Color.Black
                            )
                        .size(88.dp)
                )
                Text(
                    text = "Beginner",
                    style = MaterialTheme.typography.labelMedium,
                    color = OnBackgroundVariant,
                    modifier = Modifier
                        .padding(top = 12.dp)

                )

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 36.dp)
                    .clickable { onNavigateToDrawScreen() }
            ) {
                Image(
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
                            shape = CircleShape,
                            ambientColor = Color.Black
                        )
                        .size(88.dp),
                    imageVector = ChallengingIcon,
                    contentDescription = null
                )
                Text(
                    text = "Challenging",
                    style = MaterialTheme.typography.labelMedium,
                    color = OnBackgroundVariant,
                    modifier = Modifier
                        .padding(top = 12.dp),

                )

            }
            Column(
                modifier = Modifier
                    .clickable { onNavigateToDrawScreen() },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
                            shape = CircleShape,
                            ambientColor = Color.Black
                        )
                        .size(88.dp),
                    imageVector = MasterIcon,
                    contentDescription = null
                )
                Text(
                    text = "Master",
                    style = MaterialTheme.typography.labelMedium,
                    color = OnBackgroundVariant,
                    modifier = Modifier
                        .padding(top = 12.dp)

                    )

            }


        }
    }

}

@Preview
@Composable
private fun DifficultyLevelScreenPreview() {
    ScribbleDashTheme {
        DifficultyLevelScreen(
            modifier = Modifier,
            onCloseIconClick = {},
            onNavigateToDrawScreen = {}
        )

    }

}