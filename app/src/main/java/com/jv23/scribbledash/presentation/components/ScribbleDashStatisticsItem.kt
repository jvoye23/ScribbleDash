package com.jv23.scribbledash.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.scribbledash.ui.theme.HourGlassBackground
import com.jv23.scribbledash.ui.theme.HourGlassIcon
import com.jv23.scribbledash.ui.theme.OnBackgroundVariant
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.SurfaceHigh
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.vectorResource
import com.jv23.scribbledash.R


@Composable
fun ScribbleDashStatisticsItem(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    iconId: Int,
    iconBackgroundColor: Color,
    description: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)

    ) {
        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, top = 12.dp, end = 20.dp, bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBackgroundColor.copy(alpha = 0.1f))
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(iconId),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier
                    .weight(1f),
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = OnBackgroundVariant
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(

                text = value,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,

            )

        }
    }

}

@Preview (showSystemUi = true)
@Composable
private fun ScribbleDashStatisticsItemPreview() {
    ScribbleDashTheme {

        Column(
            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.Center
        ) {
            ScribbleDashStatisticsItem(
                modifier = Modifier,
                backgroundColor = SurfaceHigh,
                iconId = R.drawable.hourglass,
                iconBackgroundColor = HourGlassBackground,
                description = "Nothing to track...for now",
                value = "0%"
            )
        }


    }

}