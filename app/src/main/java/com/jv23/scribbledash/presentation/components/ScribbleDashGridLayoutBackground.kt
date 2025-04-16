package com.jv23.scribbledash.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.scribbledash.ui.theme.OnSurfaceVariant
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.SurfaceHigh

@Composable
fun GridBackground(
    modifier: Modifier = Modifier,

) {
    Box(
        modifier = modifier
            .size(360.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(SurfaceHigh)

    ){
        val borderColor = SolidColor(OnSurfaceVariant)
        Box(
            modifier = Modifier
                .size(330.dp)
                .clip(RoundedCornerShape(24.dp))
                .align(Alignment.Center)
                .border(
                    width = 1.dp,
                    brush = borderColor,
                    shape = RoundedCornerShape(24.dp)
                )

        ){
            HorizontalDivider(
                color = OnSurfaceVariant,
                thickness = 1.dp,
                modifier = Modifier
                    .offset(x = 0.dp, y = 110.dp )
            )
            HorizontalDivider(
                color = OnSurfaceVariant,
                thickness = 1.dp,
                modifier = Modifier
                    .offset(x = 0.dp, y = 220.dp )
            )
            VerticalDivider(
                color = OnSurfaceVariant,
                thickness = 1.dp,
                modifier = Modifier
                    .offset(x = 110.dp, y = 0.dp )
            )
            VerticalDivider(
                color = OnSurfaceVariant,
                thickness = 1.dp,
                modifier = Modifier
                    .offset(x = 220.dp, y = 0.dp )
            )

        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    ScribbleDashTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            GridBackground(
                modifier = Modifier,

            )

        }

    }

}