package com.acml.wakeupwoi.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acml.wakeupwoi.ui.theme.WakeupwoiTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlarmList(
    time: String, isActive: Boolean, label: String = "Alarm", onActiveChange: (Boolean) -> Unit = {}
) {
    AnimatedVisibility(
        visible = true
    ) {
        Card(shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .combinedClickable(onClick = {}, onLongClick = {
                    onActiveChange(!isActive)
                })
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row {
                        Text(
                            time, style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 42.sp
                            )
                        )
                        Text(
                            "AM", style = MaterialTheme.typography.labelSmall
                        )
                    }
                    Text(label)
                }
                Switch(checked = isActive, onCheckedChange = {
                    onActiveChange(it)
                })
            }
        }

    }
}

@Preview
@Composable
fun AlarmListPreview() {
    WakeupwoiTheme {
        AlarmList("12:00", true)
    }
}