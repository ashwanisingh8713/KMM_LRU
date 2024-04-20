package com.img.app.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import imagecaching.composeapp.generated.resources.Res
import imagecaching.composeapp.generated.resources.try_again
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyUI(
    modifier: Modifier = Modifier,
    msg: String,
    onCheckAgain: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.testTag("EmptyUIText"),
                text = msg,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.size(10.dp))
            OutlinedButton(
                onClick = onCheckAgain
            ) {
                Text(
                    text = stringResource(Res.string.try_again),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}