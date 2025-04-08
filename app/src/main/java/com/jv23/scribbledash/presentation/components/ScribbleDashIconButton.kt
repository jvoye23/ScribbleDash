package com.jv23.scribbledash.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.scribbledash.ui.theme.OnBackground
import com.jv23.scribbledash.ui.theme.ReplyIcon
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.SurfaceLow

@Composable
fun ScribbleDashIconButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    icon: ImageVector,
    enabled: Boolean,
    iconTint: Color,
    onClick: ()-> Unit
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier
                .align(Alignment.Center)
                .size(28.dp)
        )
    }

}

@Preview
@Composable
private fun ScribbleDashIconButtonPreview() {
    ScribbleDashTheme {
        ScribbleDashIconButton(
            modifier = Modifier,
            icon = ReplyIcon,
            iconTint = OnBackground,
            onClick = {},
            backgroundColor = SurfaceLow,
            enabled = true,
        )
    }

}