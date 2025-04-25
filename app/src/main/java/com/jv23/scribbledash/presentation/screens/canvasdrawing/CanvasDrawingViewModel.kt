package com.jv23.scribbledash.presentation.screens.canvasdrawing

import android.os.CountDownTimer
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


sealed interface CanvasDrawingAction {
    data object OnNewPathStart: CanvasDrawingAction
    data class  OnDraw(val offset: Offset): CanvasDrawingAction
    data object OnPathEnd: CanvasDrawingAction
    data class  OnSelectColor(val color: Color): CanvasDrawingAction
    data object OnClearCanvasClick: CanvasDrawingAction
    data object OnUndoClick: CanvasDrawingAction
    data object OnRedoClick: CanvasDrawingAction
    data object OnUpdateCountDownTimer: CanvasDrawingAction
    data object OnFinishCountDownTimer: CanvasDrawingAction

}

class CanvasDrawingViewModel: ViewModel() {

    private val _state = MutableStateFlow(CanvasDrawingState())
    val state = _state.asStateFlow()


    fun onAction(action: CanvasDrawingAction){
        when(action){
            CanvasDrawingAction.OnClearCanvasClick -> onClearCanvasClick()
            is CanvasDrawingAction.OnDraw -> onDraw(action.offset)
            CanvasDrawingAction.OnNewPathStart -> onNewPathStart()
            CanvasDrawingAction.OnPathEnd -> onPathEnd()
            is CanvasDrawingAction.OnSelectColor -> onSelectColor(action.color)
            CanvasDrawingAction.OnUndoClick -> onUndoClick()
            CanvasDrawingAction.OnRedoClick -> onRedoClick()
            CanvasDrawingAction.OnFinishCountDownTimer -> OnFinishCountDownTimer()
            CanvasDrawingAction.OnUpdateCountDownTimer -> OnUpdateCountDownTimer()
        }
    }

    private fun OnUpdateCountDownTimer(){
        _state.update { it.copy(
            countDownTimer = it.countDownTimer - 100L
        ) }
    }

    private fun OnFinishCountDownTimer(){
        _state.update { it.copy(
            isCountDownRunning = false,
            areBottomButtonsVisible = true
        ) }
    }



    private fun onClearCanvasClick(){
        _state.update { it.copy(
            currentPath = null,
            paths = emptyList(),
            undoPaths = emptyList(),
            isUndoPathsEmpty = true,
            isClearCanvasButtonEnabled = false,
            isRedoButtonEnabled = false,
            isUndoButtonEnabled = false,
            undoCounter = 0
        ) }
    }

    private fun onDraw(offset: Offset){
        val currentPathData = state.value.currentPath ?: return
        _state.update { it.copy(
            currentPath = currentPathData.copy(
                path = currentPathData.path + offset
            )
        ) }
    }

    private fun onNewPathStart(){
        _state.update { it.copy(
            currentPath = PathData(
                id = System.currentTimeMillis().toString(),
                color = it.selectedColor,
                path = emptyList()
            )
        ) }
    }



    private fun onPathEnd(){
        val currentPathData = state.value.currentPath ?: return
        val currentUndoPaths = state.value.undoPaths
        var newUndoPaths: List<PathData> = emptyList()

        if (currentUndoPaths.size == 5){
            newUndoPaths = currentUndoPaths.subList(fromIndex = 1, toIndex = 5) + currentPathData
        } else {
            newUndoPaths = currentUndoPaths + currentPathData
        }

        _state.update { it.copy(
            currentPath = null,
            lastPath = currentPathData,
            paths = it.paths + currentPathData,
            undoPaths = newUndoPaths,
            isUndoPathsEmpty = false,
            isClearCanvasButtonEnabled = true,
            isUndoButtonEnabled = true,
            undoCounter = 0
          )
        }

        println(state.value.undoPaths.size)
        println("Counter: ${state.value.undoCounter}")

    }

    private fun onSelectColor(color: Color){
        _state.update { it.copy(
            selectedColor = color
        ) }
    }

    private fun onUndoClick(){
        _state.update { it.copy(
            paths = it.paths.dropLast(1),
            undoCounter = it.undoCounter + 1,
            isRedoButtonEnabled = true
        ) }

        val counter = state.value.undoCounter
        if (counter > 4){
            _state.update {
                it.copy(
                    isUndoButtonEnabled = false
                )
            }
        }


        println("Counter: ${state.value.undoCounter}")
        println("UndoButton: ${state.value.isUndoButtonEnabled}")

    }

    private fun onRedoClick(){
        val currentList = state.value.paths
        val undoPaths = state.value.undoPaths.asReversed()
        val counter = state.value.undoCounter

        _state.update {
            it.copy(
                paths = currentList + undoPaths.get(counter -1),
                undoCounter = it.undoCounter - 1

            )

        }
        println("Counter: ${state.value.undoCounter}")

        val newCounter = state.value.undoCounter
        if (newCounter > 0){
            _state.update {
                it.copy(
                    isRedoButtonEnabled = true,
                    isUndoButtonEnabled = true
                )
            }
        } else {
            _state.update { it.copy(
                isRedoButtonEnabled = false
            ) }
        }

    }


}
