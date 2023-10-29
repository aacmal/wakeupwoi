package com.acml.wakeupwoi.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.acml.wakeupwoi.ui.screens.alarm.Alarm
import kotlinx.coroutines.launch
import java.text.DateFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmBottomSheet(onDismiss: () -> Unit = {}, onClickAdd: (Alarm) -> Unit = {}) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    val scope = rememberCoroutineScope()

    val timePickerState = rememberTimePickerState(
        initialHour = 12, initialMinute = 0, is24Hour = true
    );

    var label by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                TimeInput(state = timePickerState)
            }
            OutlinedTextField(value = label, onValueChange = {
                label = it
            }, label = {
                Text(text = "Label")
            }, modifier = Modifier.fillMaxWidth())
            Button(
                onClick = {
                    onClickAdd(
                        Alarm(
                            hour = timePickerState.hour,
                            minute = timePickerState.minute,
                            isActive = true,
                            label = label
                        )
                    )
                    scope.launch {
                        sheetState.hide()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = "Add Alarm",
                    modifier = Modifier.padding(vertical = 11.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}