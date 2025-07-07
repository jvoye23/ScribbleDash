package com.jv23.scribbledash.presentation.utils

import android.graphics.Path
import android.graphics.RectF
import androidx.compose.foundation.layout.union
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.isEmpty
import androidx.compose.ui.graphics.Outline
import com.jv23.scribbledash.data.ExampleDrawing
import com.jv23.scribbledash.presentation.screens.canvasdrawing.DifficultyLevelOptions

class PathComparisonAlgorithm(
    //exampleDrawing: List<Path>,
    //userDrawing: List<Path>,
    difficultyLevel: DifficultyLevelOptions
)  {
    fun calculateTotalBounds(exampleDrawing: List<Path>, userDrawing: List<Path>): RectF {
        val totalBounds = RectF()
        var firstPathProcessed = false

        val processPath = { path: Path ->
            val pathBounds = RectF()
            path.computeBounds(pathBounds, true) // Calculate bounds for the current path

            if (!pathBounds.isEmpty) { // Only consider non-empty paths
                if (!firstPathProcessed) {
                    totalBounds.set(pathBounds) // Initialize with the first valid path's bounds
                    firstPathProcessed = true
                } else {
                    totalBounds.union(pathBounds) // Union with subsequent path bounds
                }
            }
        }

        exampleDrawing.forEach(processPath)
        userDrawing.forEach(processPath)

        return totalBounds
    }
}

/**
 * Calculates the total bounding RectF that encloses all paths from 'exampleDrawing'
 * and all points from 'userDrawing'.
 *
 * @param exampleDrawing A list of Path objects.
 * @param userDrawing A list of Offset points (e.g., from user input).
 * @return A RectF representing the total bounds. Returns an empty RectF if both inputs
 *         are effectively empty (no paths in exampleDrawing and no points in userDrawing,
 *         or all paths are empty).
 */

fun calculateTotalBoundsWithOffsets(
    exampleDrawing: List<Path>,
    userDrawing: List<Offset>
): RectF {
    val totalBounds = RectF()
    var firstElementProcessed = false // Tracks if any geometric element has initialized the bounds

    // Helper to initialize or union bounds
    val updateBounds = { left: Float, top: Float, right: Float, bottom: Float ->
        if (!firstElementProcessed) {
            totalBounds.set(left, top, right, bottom)
            if (left < right && top < bottom) { // Ensure it's a valid rect before marking as processed
                firstElementProcessed = true
            }
        } else {
            totalBounds.union(left, top, right, bottom)
        }
    }

    // 1. Process the List<Path> from exampleDrawing
    exampleDrawing.forEach { path ->
        val pathBounds = RectF()
        path.computeBounds(pathBounds, true) // Calculate bounds for the current path

        if (!pathBounds.isEmpty) { // Only consider non-empty paths
            updateBounds(pathBounds.left, pathBounds.top, pathBounds.right, pathBounds.bottom)
        }
    }

    // 2. Process the List<Offset> from userDrawing
    if (userDrawing.isNotEmpty()) {
        var minX = Float.MAX_VALUE
        var minY = Float.MAX_VALUE
        var maxX = Float.MIN_VALUE
        var maxY = Float.MIN_VALUE

        userDrawing.forEach { offset ->
            minX = minOf(minX, offset.x)
            minY = minOf(minY, offset.y)
            maxX = maxOf(maxX, offset.x)
            maxY = maxOf(maxY, offset.y)
        }

        // Only update bounds if we actually found valid min/max points
        if (minX <= maxX && minY <= maxY) { // Check ensures we have at least one point or a valid range
            updateBounds(minX, minY, maxX, maxY)
        }
    }

    return totalBounds
}



