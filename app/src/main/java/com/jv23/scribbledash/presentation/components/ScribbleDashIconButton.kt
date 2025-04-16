package com.jv23.scribbledash.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jv23.scribbledash.ui.theme.ReplyIcon
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme
import com.jv23.scribbledash.ui.theme.SurfaceLow


@Composable
fun ScribbleDashIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    enabled: Boolean,
    onClick: ()-> Unit
) {
    Button(
        onClick = onClick,
        shape = RectangleShape,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .size(64.dp)
            .clip(RoundedCornerShape(22.dp)),
        enabled = enabled,
        colors = ButtonColors(
            containerColor = SurfaceLow,
            contentColor = MaterialTheme.colorScheme.onBackground ,
            disabledContainerColor = SurfaceLow.copy(alpha = 0.4f),
            disabledContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
        )

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(28.dp)
            )

        }


    }

}




@Preview
@Composable
private fun ScribbleDashIconButtonPreview() {
    ScribbleDashTheme {
        ScribbleDashIconButton(
            modifier = Modifier,
            icon = ReplyIcon,
            onClick = {},
            enabled = false,
        )
    }

}