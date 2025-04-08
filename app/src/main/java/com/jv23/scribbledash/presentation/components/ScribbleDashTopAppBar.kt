package com.jv23.scribbledash.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jv23.scribbledash.ui.theme.CloseIcon
import com.jv23.scribbledash.ui.theme.ScribbleDashTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScribbleDashTopAppBar(
    modifier: Modifier = Modifier,
    title: String,

) {

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
        .height(72.dp)
        .background(Color.Red)
        .padding(start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp),





        title = {
            Text(
                text = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 0.dp)
                    .background(Color.Green)



                    ,

                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground

            )
        },

        /*actions = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = CloseIcon,
                        contentDescription = "Menu",
                        modifier = Modifier
                            .size(56.dp),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

        }*/
    )
}