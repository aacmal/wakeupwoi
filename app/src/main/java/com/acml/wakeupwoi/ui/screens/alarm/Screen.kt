package com.acml.wakeupwoi.ui.screens.alarm

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items as lazyItems
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acml.wakeupwoi.service.AndroidAlarmScheduler
import com.acml.wakeupwoi.ui.components.AddAlarmBottomSheet
import com.acml.wakeupwoi.ui.components.AlarmList
import com.acml.wakeupwoi.ui.theme.WakeupwoiTheme

@Composable
fun AlarmScreen(
    alarmViewModel: AlarmViewModel = viewModel(),
    context: Context = LocalContext.current
) {
    val alarmScheduler = AndroidAlarmScheduler(context)
    val alarms by alarmViewModel.getAlarms().collectAsState(initial = emptyList())
    var showBottomSheet by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            lazyItems(items = alarms) { alarm ->
                AlarmList(
                    id = alarm.id,
                    time = String.format("%02d:%02d", alarm.hour, alarm.minute),
                    label = alarm.label,
                    isActive = alarm.isActive,
                    onActiveChange = {
                        alarmViewModel.updateAlarm(alarm.copy(isActive = it))
                        if (it) {
                            alarmScheduler.schedule(alarm)
                        } else {
                            alarmScheduler.cancel(alarm)
                        }
                    },
                    onDelete = {
                        // remove alarm from list
                        alarmViewModel.removeAlarm(alarm)
                        // cancel alarm
                        alarmScheduler.cancel(alarm)
                    }
                )
            }
        }
        FloatingActionButton(
            onClick = {
                showBottomSheet = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Alarm")
        }
        if (showBottomSheet) AddAlarmBottomSheet(
            onDismiss = {
                showBottomSheet = false
            },
            onClickAdd = {
                alarmViewModel.addAlarm(
                    alarm = it
                )
                alarmScheduler.schedule(it)
                showBottomSheet = false
            }
        )
    }
}

@Preview(apiLevel = 33, device = "id:pixel_6")
@Composable
fun AlarmScreenPreview() {
    WakeupwoiTheme {
        Surface {
            AlarmScreen()
        }
    }
}