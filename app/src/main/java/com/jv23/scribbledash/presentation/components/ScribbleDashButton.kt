package com.jv23.scribbledash.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.Success
import com.jv23.scribbledash.ui.theme.SurfaceHigh
import com.jv23.scribbledash.ui.theme.SurfaceLowest


@Composable
fun ScribbleDashButton(
    modifier: Modifier = Modifier,
    buttonText: String,
    enabled: Boolean,
    onClick: ()-> Unit) {
    Button(
        onClick = onClick,
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .width(336.dp)
            .height(64.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceHigh)
            .padding(6.dp)
            .clip(RoundedCornerShape(16.dp)),
        enabled = enabled,
        colors = ButtonColors(
            containerColor = Success,
            contentColor = MaterialTheme.colorScheme.onPrimary ,
            disabledContainerColor = SurfaceLowest,
            disabledContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
        )
    ) {
        Text(
            modifier = Modifier,
            text = buttonText,
            style = MaterialTheme.typography.headlineSmall,
        )
    }

}

@Preview(showSystemUi = false)
@Composable
private fun ScribbleDashButtonPreview() {
    ScribbleDashTheme {
        ScribbleDashButton(
            modifier = Modifier,
            buttonText = "CLEAR CANVAS",
            enabled = true,
            onClick = {}
        )
    }
}