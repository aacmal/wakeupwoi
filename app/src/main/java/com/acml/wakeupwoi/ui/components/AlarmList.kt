package com.acml.wakeupwoi.ui.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acml.wakeupwoi.ui.theme.WakeupwoiTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlarmList(
    id: Int,
    time: String,
    isActive: Boolean,
    label: String = "Alarm",
    isRepeat: Boolean = false,
    onActiveChange: (Boolean) -> Unit = {},
    onDelete: () -> Unit = {},
    onEdit: (id: Int) -> Unit = {},
) {
    var deleteDialog by rememberSaveable { mutableStateOf(false) }
    AnimatedVisibility(
        visible = true
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.large)
                .combinedClickable(onClick = {
                    onEdit(id)
                }, onLongClick = {
                    deleteDialog = true
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
    if (deleteDialog) {
        ConfirmDeleteAlarmDialog(
            onDismissRequest = {
                deleteDialog = false
            },
            onConfirm = {
                onDelete()
                deleteDialog = false
            }
        )
    }
}

@Preview
@Composable
fun AlarmListPreview() {
    WakeupwoiTheme {
        AlarmList(id = 2, time="12:00", isActive = true)
    }
}