package com.jv23.scribbledash.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.jv23.scribbledash.data.DrawingExampleResources
import com.jv23.scribbledash.data.DrawingTestResources
import com.jv23.scribbledash.data.ExampleDrawingRepositoryImpl
import com.jv23.scribbledash.data.allExampleResIds
import com.jv23.scribbledash.domain.ExampleDrawingRepository
import com.jv23.scribbledash.presentation.screens.canvasdrawing.CanvasDrawingViewModel

class AppDataContainer(
    private val appContext: Context
) : AppContainer {

    override val exampleDrawingRepository: ExampleDrawingRepository
        get() = ExampleDrawingRepositoryImpl(
            appContext,
            resIds = DrawingExampleResources.allDrawingResIds
            //resIds = DrawingTestResources.testResIds
        )





    override val canvasDrawingViewModel: ViewModelProvider.Factory
        get() = viewModelFactory { savedStateHandle ->
            CanvasDrawingViewModel(
                repo = exampleDrawingRepository,
                savedStateHandle = savedStateHandle
            )

        }
}