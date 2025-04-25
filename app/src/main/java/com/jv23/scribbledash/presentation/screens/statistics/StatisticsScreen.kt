package com.jv23.scribbledash.presentation.screens.statistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jv23.scribbledash.R
import com.jv23.scribbledash.presentation.components.ScribbleDashBottomNavBar
import com.jv23.scribbledash.presentation.components.ScribbleDashStatisticsItem
import com.jv23.scribbledash.ui.theme.BoltBackground
import com.jv23.scribbledash.ui.theme.HourGlassBackground
import com.jv23.scribbledash.ui.theme.HourGlassIcon
import com.jv23.scribbledash.ui.theme.OneRoundWonderIcon
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.Success
import com.jv23.scribbledash.ui.theme.SurfaceHigh

data class ScribbleDashStatistics(
    val backgroundColor: Color,
    val iconId: Int,
    val iconBackgroundColor: Color,
    val description: String,
    val value: String
)

val scribbleDashStatistics = listOf(
    ScribbleDashStatistics(
        backgroundColor = SurfaceHigh,
        iconId = R.drawable.hourglass,
        iconBackgroundColor = HourGlassBackground,
        description = "Nothing to track...for now",
        value = "0%"
    ),
    ScribbleDashStatistics(
        backgroundColor = SurfaceHigh,
        iconId = R.drawable.bolt,
        iconBackgroundColor = BoltBackground,
        description = "Nothing to track...for now",
        value = "0"
    )

)

@Composable
fun StatisticsScreenRoot(
    modifier: Modifier = Modifier,
    navController: NavController,


) {
    Scaffold(
        bottomBar = {
            ScribbleDashBottomNavBar(
                modifier = modifier,
                navController = navController
            )
        }

    ) { innerPadding ->
        StatisticsScreen(
            modifier = modifier
                .padding(innerPadding),

        )

    }


}

@Composable
fun StatisticsScreen(
    modifier: Modifier = Modifier,


) {
    Column(
    modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
) {
    //1st Row
    Row(
        modifier = Modifier
            .fillMaxWidth()

            .offset{ IntOffset(0,0.dp.roundToPx())}
            .height(72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            text = "Statistics",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(16.dp)


    ) {
        items(scribbleDashStatistics) { item ->
            ScribbleDashStatisticsItem(

                backgroundColor = item.backgroundColor,
                iconId = item.iconId,
                iconBackgroundColor = item.iconBackgroundColor,
                description = item.description,
                value = item.value
            )

        }
    }
}



}

@Preview
@Composable
private fun StatisticsScreenPreview() {
    ScribbleDashTheme {
        StatisticsScreen(
            modifier = Modifier,

        )

    }

}