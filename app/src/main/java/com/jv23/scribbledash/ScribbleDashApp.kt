package com.jv23.scribbledash

import android.app.Application
import com.jv23.scribbledash.di.AppContainer
import com.jv23.scribbledash.di.AppDataContainer
import com.jv23.scribbledash.domain.ExampleDrawingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch



class ScribbleDashApp : Application() {

    //val applicationScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    companion object {
        lateinit var container: AppContainer
    }


    override fun onCreate() {
        super.onCreate()

        container = AppDataContainer(
            this@ScribbleDashApp
        )

        CoroutineScope(Dispatchers.Default).launch {
            container.exampleDrawingRepository.parseAllXMLDrawings()


        }


    }


}