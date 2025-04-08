package com.jv23.scribbledash.presentation.screens.canvasdrawing

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.jv23.scribbledash.presentation.components.ScribbleDashButton
import com.jv23.scribbledash.presentation.components.ScribbleDashIconButton
import com.jv23.scribbledash.ui.theme.BeginnerIcon
import com.jv23.scribbledash.ui.theme.ChallengingIcon
import com.jv23.scribbledash.ui.theme.CloseIcon
import com.jv23.scribbledash.ui.theme.ForwardIcon
import com.jv23.scribbledash.ui.theme.MasterIcon
import com.jv23.scribbledash.ui.theme.OnBackgroundVariant
import com.jv23.scribbledash.ui.theme.OnPrimary
import com.jv23.scribbledash.ui.theme.ReplyIcon
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.SurfaceHigh
import com.jv23.scribbledash.ui.theme.SurfaceLow
import com.jv23.scribbledash.ui.theme.SurfaceLowest

@Composable
fun CanvasDrawingScreenRoot(
    modifier: Modifier = Modifier,
    onNavigateBack: ()-> Unit
) {
    CanvasDrawingScreen()

}

@Composable
fun CanvasDrawingScreen(
    modifier: Modifier = Modifier
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
                    .size(32.dp),
                imageVector = CloseIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
        //Center Row
        Column(
            modifier = Modifier

                .align(Alignment.Center)
                .offset{ IntOffset(0,-240.dp.roundToPx()) }
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
                    text = "Time to draw!",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }

        //Center Rectangle
        Box(
            modifier = Modifier
                .height(360.dp)
                .width(354.dp)

                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .align(Alignment.Center)
        ){

        }
        // Bottom Row
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(16.dp)
                ,
            horizontalArrangement = Arrangement.SpaceBetween


        ) {
            ScribbleDashIconButton(
                modifier = Modifier
                    .size(64.dp),
                backgroundColor = SurfaceLow,
                icon = ReplyIcon,
                onClick = {},
                enabled = true,
                iconTint = MaterialTheme.colorScheme.onBackground
            )
            ScribbleDashIconButton(
                modifier = Modifier
                    .size(64.dp),
                backgroundColor = SurfaceLow,
                icon = ForwardIcon,
                onClick = {},
                enabled = true,
                iconTint = MaterialTheme.colorScheme.onBackground
            )
            ScribbleDashButton(
                modifier = Modifier
                    .width(201.dp),
                surfaceColor = SurfaceLowest,
                backgroundColor = SurfaceHigh,
                buttonText = "CLEAR CANVAS",
                buttonTextColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f),
                onClick = {},
                enabled = true
            )


        }
    }

}

@Preview
@Composable
private fun CanvasDrawingScreenPreview() {
    ScribbleDashTheme {
        CanvasDrawingScreen()

    }

}