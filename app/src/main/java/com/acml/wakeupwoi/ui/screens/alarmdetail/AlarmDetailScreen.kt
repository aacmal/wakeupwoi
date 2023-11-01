package com.acml.wakeupwoi.ui.screens.alarmdetail

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acml.wakeupwoi.R
import com.acml.wakeupwoi.domain.model.Alarm
import com.acml.wakeupwoi.service.AndroidAlarmScheduler
import com.acml.wakeupwoi.ui.components.InputField
import com.acml.wakeupwoi.ui.theme.WakeupwoiTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmDetailScreen(
    alarmViewModel: AlarmDetailViewModel = hiltViewModel(),
    onClickUpdate: () -> Unit = {},
    context: Context = LocalContext.current
) {
    val alarmData by alarmViewModel.uiState.collectAsState()
    val alarmManager = AndroidAlarmScheduler(context)

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimePicker(state = alarmData.timePickerState)
            InputField(value = alarmData.label, onChange = {
                alarmViewModel.onLabelChange(it)
            }, placeholder = R.string.label_placeholder)
            Button(
                onClick = {
                    alarmViewModel.updateAlarm()
                    onClickUpdate()
                    alarmManager.schedule(
                        Alarm(
                            alarmData.id,
                            alarmData.timePickerState.hour,
                            alarmData.timePickerState.minute,
                            alarmData.label,
                            alarmData.isRepeat,
                            alarmData.isActive,
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = stringResource(R.string.update_alarm),
                    modifier = Modifier.padding(vertical = 11.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}

@Preview
@Composable
fun AlarmDetailScreenPreview() {
    WakeupwoiTheme {
        Surface {
            AlarmDetailScreen()
        }
    }
}