package com.jv23.scribbledash.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.scribbledash.ui.theme.OnPrimary
import com.jv23.scribbledash.ui.theme.Primary
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.SurfaceHigh

@Composable
fun ScribbleDashButton(
    modifier: Modifier = Modifier,
    surfaceColor: Color,
    backgroundColor: Color,
    buttonText: String,
    buttonTextColor: Color,
    enabled: Boolean,
    onClick: ()-> Unit) {
    Box(
        modifier = modifier
            .width(336.dp)
            .height(64.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .padding(6.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(surfaceColor)
            .clickable(onClick = onClick)
        ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            text = buttonText,
            style = MaterialTheme.typography.headlineSmall,
            color = buttonTextColor
        )
    }

}

@Preview(showSystemUi = false)
@Composable
private fun ScribbleDashButtonPreview() {
    ScribbleDashTheme {

        ScribbleDashButton(
            modifier = Modifier,
            surfaceColor = Primary,
            backgroundColor = SurfaceHigh,
            buttonText = "Start!",
            buttonTextColor = OnPrimary,
            onClick = {},
            enabled = true
        )


    }
    
}