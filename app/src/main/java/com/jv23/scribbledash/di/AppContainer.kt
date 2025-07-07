package com.jv23.scribbledash.di

import androidx.lifecycle.ViewModelProvider
import com.jv23.scribbledash.domain.ExampleDrawingRepository

interface AppContainer {
    val exampleDrawingRepository: ExampleDrawingRepository

    val canvasDrawingViewModel: ViewModelProvider.Factory
}