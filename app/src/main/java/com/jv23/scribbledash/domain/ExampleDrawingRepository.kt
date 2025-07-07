package com.jv23.scribbledash.domain

import com.jv23.scribbledash.data.ExampleDrawing


interface ExampleDrawingRepository {

    suspend fun parseAllXMLDrawings()

    fun getAllparsedDrawings(): List<ExampleDrawing>


}