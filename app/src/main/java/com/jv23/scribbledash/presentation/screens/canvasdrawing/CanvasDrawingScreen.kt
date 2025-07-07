package com.jv23.scribbledash.presentation.screens.canvasdrawing

import android.content.Context
import android.graphics.RectF

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jv23.scribbledash.presentation.components.ScribbleDashButton
import com.jv23.scribbledash.presentation.components.ScribbleDashIconButton
import com.jv23.scribbledash.ui.theme.CloseIcon
import com.jv23.scribbledash.ui.theme.ForwardIcon
import com.jv23.scribbledash.ui.theme.ReplyIcon
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform

import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.PathParser
import com.jv23.scribbledash.R
import com.jv23.scribbledash.ScribbleDashApp
import com.jv23.scribbledash.presentation.components.GridBackground
import com.jv23.scribbledash.presentation.utils.calculateTotalBoundsWithOffsets
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException
import kotlin.math.abs
import kotlin.math.min

@Composable
fun CanvasDrawingScreenRoot(
    modifier: Modifier = Modifier,
    onNavigateBack: ()-> Unit,
    viewModel: CanvasDrawingViewModel = viewModel<CanvasDrawingViewModel>(factory = ScribbleDashApp.container.canvasDrawingViewModel)
) {
    //val viewModel = viewModel<CanvasDrawingViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()


    CanvasDrawingScreen(
        onCloseIconClick = onNavigateBack,
        onAction = viewModel::onAction,
        state = state,
        modifier = modifier
        
    )


}


@Composable
fun CanvasDrawingScreen(
    onCloseIconClick:() -> Unit,
    onAction: (CanvasDrawingAction) -> Unit,
    state: CanvasDrawingState,
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
                .offset{ IntOffset(0,-240.dp.roundToPx()) }
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Row {
                Text(
                    modifier = Modifier                    ,
                    text = if(state.isCountDownRunning){
                        "Ready, set"
                    } else "Time to draw!",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }

        //Center Rectangle
        GridBackground(
            modifier = Modifier
                .align(Alignment.Center)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(36.dp)
                )
        )

        Canvas(
            modifier = modifier
                .size(360.dp)
                .clipToBounds()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Transparent)
                .align(Alignment.Center)
                .background(Color.Transparent)
                .pointerInput(true) {
                    detectDragGestures(
                        onDragStart = {
                            onAction(CanvasDrawingAction.OnNewPathStart)
                        },
                        onDragEnd = {
                            onAction(CanvasDrawingAction.OnPathEnd)
                        },
                        onDrag = { change, _ ->
                            onAction(CanvasDrawingAction.OnDraw(change.position))
                        },
                        onDragCancel = {
                            onAction(CanvasDrawingAction.OnPathEnd)
                        },
                    )
                }
        ) {


            state.paths.fastForEach { pathData ->
                drawPath(
                    path = pathData.path,
                    color = pathData.color,
                )
            }
            state.currentPath?.let {
                drawPath(
                    path = it.path,
                    color = it.color
                )
            }

            val examplePath = state.exampleDrawing?.paths
            val viewportWidth = state.exampleDrawing?.viewportWidth
            val viewportHeight = state.exampleDrawing?.viewportHeight

            if (examplePath != null) {
                if (viewportHeight != null && viewportWidth != null) {
                    if (examplePath.isNotEmpty() && viewportHeight > 0 ) {
                        val canvasWidth = this.size.width
                        val canvasHeight = this.size.height

                        // Calculate scale factors
                        val scaleX = canvasWidth / viewportWidth
                        val scaleY = canvasHeight / viewportHeight

                        println("Before scaling:")
                        println("Canvas W/H: $canvasWidth / $canvasHeight")
                        println("Scale X/Y: $scaleX / $scaleY")

                        val scale = min(scaleX, scaleY)
                        println("Chosen Scale (max): $scale")  // Note: We're using max now
                        val scaledWidth = viewportWidth * scale
                        val scaledHeight = viewportHeight * scale
                        println("Scaled W/H: $scaledWidth / $scaledHeight")
                        val translateX = (canvasWidth - scaledWidth) / 2f
                        val translateY = (canvasHeight - scaledHeight) / 2f
                        println("Translate X/Y: $translateX / $translateY")

                        withTransform({
                            translate(left = translateX, top = translateY)
                            scale(scaleX = scale, scaleY = scale, pivot = Offset.Zero )
                        }){
                            println("Inside withTransform - Drawing sample paths...")
                            examplePath.forEach { path ->

                                drawPath(
                                    path = path.asComposePath(),
                                    color = Color.Black,
                                    style = Stroke(width = 1f)
                                )

                            }
                        }

                    }
                }
            }








            // draw algorithm

        }
        // End of Canvas

        Text(
            text = if(state.isCountDownRunning){
                "Example"
            } else "Your Drawing",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.Center)
                .offset{ IntOffset(0,200.dp.roundToPx()) }
        )

        // Bottom Row
        LaunchedEffect(key1 = state.countDownTimer, key2 =  state.isCountDownRunning) {
            if (state.countDownTimer >= 1  && state.isCountDownRunning) {
                delay(100L)
                onAction(CanvasDrawingAction.OnUpdateCountDownTimer)
            } else {
                onAction(CanvasDrawingAction.OnFinishCountDownTimer)
            }
        }


        AnimatedVisibility(
            visible = state.isCountDownRunning,
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,

            ) {
                Text(
                    text = "${state.countDownTimer / 1000L} seconds left",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,

                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }



        AnimatedVisibility(
            visible = state.areBottomButtonsVisible,
            modifier = Modifier
                .align(Alignment.BottomStart)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,

                ) {

                ScribbleDashIconButton(
                    modifier = Modifier
                        .size(64.dp),
                    icon = ReplyIcon,
                    enabled = state.isUndoButtonEnabled,
                    onClick = {
                        onAction(CanvasDrawingAction.OnUndoClick)
                    }
                )

                ScribbleDashIconButton(
                    modifier = Modifier
                        .size(64.dp),
                    icon = ForwardIcon,
                    enabled = state.isRedoButtonEnabled,
                    onClick = {
                        onAction(CanvasDrawingAction.OnRedoClick)
                    }
                )
                ScribbleDashButton(
                    modifier = Modifier
                        .shadow(
                            elevation = 2.dp,
                            shape = RoundedCornerShape(20.dp),
                            ambientColor = Color.Black
                        )
                        .width(201.dp),
                    buttonText = "CLEAR CANVAS",
                    enabled = state.isClearCanvasButtonEnabled,
                    onClick = {
                        onAction(CanvasDrawingAction.OnClearCanvasClick)
                    }
                )



            }


        }

        /*Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(16.dp)
                ,
            horizontalArrangement = Arrangement.SpaceBetween,

        ) {

            ScribbleDashIconButton(
                modifier = Modifier
                    .size(64.dp),
                icon = ReplyIcon,
                enabled = state.isUndoButtonEnabled,
                onClick = {
                    onAction(CanvasDrawingAction.OnUndoClick)
                }
            )

            ScribbleDashIconButton(
                modifier = Modifier
                    .size(64.dp),
                icon = ForwardIcon,
                enabled = state.isRedoButtonEnabled,
                onClick = {
                    onAction(CanvasDrawingAction.OnRedoClick)
                }
            )
            ScribbleDashButton(
                modifier = Modifier
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(20.dp),
                        ambientColor = Color.Black
                    )
                    .width(201.dp),
                buttonText = "CLEAR CANVAS",
                enabled = state.isClearCanvasButtonEnabled,
                onClick = {
                    onAction(CanvasDrawingAction.OnClearCanvasClick)
                }
            )



        }*/
    }

}

private fun DrawScope.drawPath(
    path: List<Offset>,
    color: Color,
    thickness: Float = 10f
) {
    val smoothedPath = Path().apply {
        if(path.isNotEmpty()) {
            moveTo(path.first().x, path.first().y)

            val smoothness = 5
            for(i in 1..path.lastIndex) {
                val from = path[i - 1]
                val to = path[i]
                val dx = abs(from.x - to.x)
                val dy = abs(from.y - to.y)
                if(dx >= smoothness || dy >= smoothness) {
                    quadraticTo(
                        x1 = (from.x + to.x) / 2f,
                        y1 = (from.y + to.y) / 2f,
                        x2 = to.x,
                        y2 = to.y
                    )
                }
            }
        }
    }
    drawPath(
        path = smoothedPath,
        color = color,
        style = Stroke(
            width = thickness,
            cap = StrokeCap.Round,
            join = StrokeJoin.Round
        )
    )
}

private fun drawAlogorithm(state: CanvasDrawingState): RectF{
    val exampleDrawingPaths = state.exampleDrawing?.paths
    val userDrawingPaths = state.currentPath?.path
    var rectF: RectF = RectF(0f, 0f, 0f, 0f)
    if (exampleDrawingPaths != null && userDrawingPaths != null){
        rectF =  calculateTotalBoundsWithOffsets(exampleDrawingPaths, userDrawingPaths)
    }
    return rectF

}

@Preview
@Composable
private fun CanvasDrawingScreenPreview() {
    ScribbleDashTheme {
        CanvasDrawingScreen(
            onCloseIconClick = {},
            onAction = {},
            state = CanvasDrawingState(
                isCountDownRunning = false,

            )
        )
    }
}


