package com.jv23.scribbledash.presentation.screens.result

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jv23.scribbledash.presentation.screens.difficultylevel.DifficultyLevelScreen
import com.jv23.scribbledash.ui.theme.CloseIcon
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme

@Composable
fun ResultScreenRoot(
    modifier: Modifier = Modifier,
    onNavigateBack: ()-> Unit,
    onNavigateTo: () -> Unit
) {
    ResultScreen(
        modifier = modifier,
        onCloseIconClick = onNavigateBack,
        onNavigateToDrawScreen = onNavigateTo
    )
}

@Composable
fun ResultScreen(
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
                .offset { IntOffset(0, 0.dp.roundToPx()) }
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
        //Center Headline & Subline
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset { IntOffset(0, 190.dp.roundToPx()) }
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row(
                modifier = Modifier,

                ) {
                Text(
                    modifier = Modifier,
                    text = "100%",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
            

        }
    }

}

@Preview
@Composable
fun ResultScreenPreview(){
    ScribbleDashTheme {
        ResultScreen(
            modifier = Modifier,
            onCloseIconClick = {},
            onNavigateToDrawScreen = {}
        )

    }
}