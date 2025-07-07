package com.jv23.scribbledash.data

import android.content.Context
import android.graphics.Path
import android.util.Log
import androidx.annotation.XmlRes
import androidx.core.graphics.PathParser
import com.jv23.scribbledash.domain.ExampleDrawingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.xmlpull.v1.XmlPullParser



/**
 * Loads and caches reference drawings (vector XML → list<Path>).
 * Call [warmUp] once (e.g. in Application.onCreate()).
 */
class ExampleDrawingRepositoryImpl(
    private val context: Context,
    @XmlRes private val resIds: List<Int>,
): ExampleDrawingRepository {

    private var cache: List<ExampleDrawing>? = null

    override suspend fun parseAllXMLDrawings() = withContext(Dispatchers.Default){
        if (cache != null) return@withContext   // already done

        val androidNs = "http://schemas.android.com/apk/res/android"

        cache = resIds.map { resId ->
            val paths = mutableListOf<Path>()
            var viewportWidth = 0f
            var viewportHeight = 0f
            println("CacheValues $cache")

            context.resources.getXml(resId).use { parser ->
                var eventType = parser.eventType
                while (eventType != XmlPullParser.END_DOCUMENT){
                    when(eventType){
                        XmlPullParser.START_TAG -> {
                            when (parser.name) {
                                "vector" -> {
                                    // Get viewport dimensions from the <vector> tag
                                    viewportWidth = parser.getAttributeValue(androidNs, "viewportWidth")?.toFloatOrNull() ?: 0f
                                    viewportHeight = parser.getAttributeValue(androidNs, "viewportHeight")?.toFloatOrNull() ?: 0f
                                }
                                "path" -> {
                                    parser.getAttributeValue(androidNs, "pathData")
                                        ?.let { data ->
                                            try {
                                                PathParser.createPathFromPathData(data)
                                                    ?.also(paths::add)
                                            } catch (e: Exception){
                                                // Handle cases where path data might be invalid
                                                Log.e("XML Parser", "Error parsing pathData for resId $resId: ${e.message}")
                                            }
                                        }
                                }
                            }
                        }
                    }
                    eventType = parser.next()
                }
            }

            if (paths.isEmpty()) {
                //Timber.tag(TAG).w("Vector #$resId contained no <path> tags!")
                Log.d("ExampleDrawing", "Vector #$resId $ contained no <path> tags!")
            }

            ExampleDrawing(
                resId = resId,
                paths = paths,
                viewportWidth = viewportWidth,
                viewportHeight = viewportHeight
            )
        }
    }

    override fun getAllparsedDrawings(): List<ExampleDrawing> =
            cache ?: emptyList()
}