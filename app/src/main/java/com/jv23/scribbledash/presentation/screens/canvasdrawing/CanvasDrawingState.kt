package com.jv23.scribbledash.presentation.screens.canvasdrawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

data class CanvasDrawingState(
    val selectedColor: Color = Color.Black,
    val currentPath: PathData? = null,
    val lastPath: PathData? = null,
    val paths: List<PathData> = emptyList(),
    val undoPaths: List<PathData> = emptyList(),
    val redoPaths: List<PathData> = emptyList(),

    val isUndoPathsEmpty: Boolean = true,
    val isUndoButtonEnabled: Boolean = false,
    val isRedoButtonEnabled: Boolean = false,
    val isClearCanvasButtonEnabled: Boolean = false,
    val undoCounter: Int = 0
)

val allColors = listOf(
    Color.Black,
    Color.Blue
)

data class PathData(
    val id: String,
    val color: Color,
    val path: List<Offset>

)