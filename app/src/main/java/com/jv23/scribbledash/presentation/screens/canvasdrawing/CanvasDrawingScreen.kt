package com.jv23.scribbledash.presentation.screens.canvasdrawing

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
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
import androidx.compose.ui.graphics.drawscope.Stroke
import com.jv23.scribbledash.presentation.components.GridBackground
import kotlin.math.abs

@Composable
fun CanvasDrawingScreenRoot(
    modifier: Modifier = Modifier,
    onNavigateBack: ()-> Unit
) {
    Scaffold() { innerPadding ->
        val viewModel = viewModel<CanvasDrawingViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()

        CanvasDrawingScreen(
            onCloseIconClick = onNavigateBack,
            paths = state.paths,
            currentPath = state.currentPath,
            onAction = viewModel::onAction,
            modifier = modifier
                .padding(innerPadding),
            isUndoButtonEnabled = state.isUndoButtonEnabled,
            isRedoButtonEnabled = state.isRedoButtonEnabled,
            isClearCanvasButtonEnabled = state.isClearCanvasButtonEnabled
        )
    }
}

@Composable
fun CanvasDrawingScreen(
    onCloseIconClick:() -> Unit,
    paths: List<PathData>,
    currentPath: PathData?,
    onAction: (CanvasDrawingAction) -> Unit,
    isUndoButtonEnabled: Boolean,
    isRedoButtonEnabled: Boolean,
    isClearCanvasButtonEnabled: Boolean,
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
            paths.fastForEach { pathData ->
                drawPath(
                    path = pathData.path,
                    color = pathData.color,
                )
            }
            currentPath?.let {
                drawPath(
                    path = it.path,
                    color = it.color
                )
            }

        }



        // Bottom Row
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ScribbleDashIconButton(
                modifier = Modifier
                    .size(64.dp),
                icon = ReplyIcon,
                enabled = isUndoButtonEnabled,
                onClick = {
                    onAction(CanvasDrawingAction.OnUndoClick)
                }
            )
            ScribbleDashIconButton(
                modifier = Modifier
                    .size(64.dp),
                icon = ForwardIcon,
                enabled = isRedoButtonEnabled,
                onClick = {
                    onAction(CanvasDrawingAction.OnRedoClick)
                }
            )
            ScribbleDashButton(
                modifier = Modifier
                    .width(201.dp),
                buttonText = "CLEAR CANVAS",
                enabled = isClearCanvasButtonEnabled,
                onClick = {
                    onAction(CanvasDrawingAction.OnClearCanvasClick)
                }
            )
        }
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

@Preview
@Composable
private fun CanvasDrawingScreenPreview() {
    ScribbleDashTheme {
        CanvasDrawingScreen(
            onCloseIconClick = {},
            paths = emptyList(),
            currentPath = null,
            onAction = {},
            isUndoButtonEnabled = true,
            isRedoButtonEnabled = false,
            isClearCanvasButtonEnabled = true,
            modifier = Modifier,
        )

    }

}