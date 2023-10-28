import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.foundation.lazy.items as lazyItems
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acml.wakeupwoi.ui.components.AlarmList
import com.acml.wakeupwoi.ui.screens.alarm.Alarm
import com.acml.wakeupwoi.ui.screens.alarm.AlarmViewModel
import com.acml.wakeupwoi.ui.theme.WakeupwoiTheme

@Composable
fun AlarmScreen(alarmViewModel: AlarmViewModel = viewModel()) {
    val alarms = alarmViewModel.getAlarms()
    Box(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            lazyItems(items = alarms) { alarm ->
                AlarmList(
                    time = alarm.time,
                    label = alarm.label,
                    isActive = alarm.isActive,
                    onActiveChange = {
                        alarmViewModel.updateAlarm(alarm.copy(isActive = it))
                    }
                )
            }
        }
        FloatingActionButton(
            onClick = {
                alarmViewModel.addAlarm(
                    alarm = Alarm(
                        time = "12:0${alarms.size + 1}",
                        label = "Alarm ${alarms.size + 1}",
                        isActive = false
                    )
                )
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Alarm")
        }
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