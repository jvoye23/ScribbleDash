package com.jv23.scribbledash.presentation.screens.canvasdrawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jv23.scribbledash.data.ExampleDrawing
import com.jv23.scribbledash.domain.ExampleDrawingRepository
import com.jv23.scribbledash.presentation.utils.PathComparisonAlgorithm
import kotlinx.coroutines.Delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay


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
    data object OnDoneClick: CanvasDrawingAction

}

class CanvasDrawingViewModel(
    val repo: ExampleDrawingRepository,
    val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = MutableStateFlow(CanvasDrawingState())
    val state = _state.asStateFlow()

    val algorithm = PathComparisonAlgorithm(DifficultyLevelOptions.BEGINNER)


    init {
        println("Init ViewModel")
        generateExample()
        println("ExampleDrawing ${state.value.exampleDrawing}")
        //val pathData = convertDrawable.convert(ExampleDrawings.ALIEN.exampleName.lowercase())




        //_state.update { it.copy(examplePaths = pathData) }
    }

    private var currentExample: ExampleDrawing? = null

    /*private fun startRound(level: Level) = viewModelScope.launch {
        repo.warmUp()
        val example = repo.all().random()                 // ONE random draw
        _state.value = _state.value.copy(
            level = level,
            referenceResId = example.resId                // store for preview
        )
        currentExample = example                          // keep for compare()
    }*/

    private fun generateExample() {
        viewModelScope.launch {
            repo.parseAllXMLDrawings()
            val example = repo.getAllparsedDrawings().random()
            _state.value = _state.value.copy(
                exampleDrawing = example
            )
            //val totalBounds = algorithm.calculateTotalBounds(example.paths, example.paths)
            //println("Totalbounds: $totalBounds")

        }
    }




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
            CanvasDrawingAction.OnDoneClick -> onDoneClick()
        }
    }

    private fun onDoneClick(){
        
    }

    private fun OnUpdateCountDownTimer(){
        _state.update { it.copy(
            countDownTimer = it.countDownTimer - 100L
        ) }
    }

    private fun OnFinishCountDownTimer(){
        _state.update { it.copy(
            isCountDownRunning = false,
            areBottomButtonsVisible = true,
            exampleDrawing = null
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
            isRedoButtonEnabled = true
        ) }


        val pathSize = state.value.paths.size
        if (pathSize == 0){
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
        println("Current List Size: ${currentList.size}")
        val undoPaths = state.value.undoPaths.asReversed()
        println("Undo Path Size: ${undoPaths.size}")
        val counter = state.value.undoCounter
        println("Counter Size: $counter")

        _state.update {
            it.copy(
                //paths = currentList + undoPaths.get(counter -1),
                paths = currentList + undoPaths,
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
