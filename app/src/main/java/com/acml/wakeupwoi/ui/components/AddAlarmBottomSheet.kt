package com.acml.wakeupwoi.ui.components

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.SheetValue
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
import com.acml.wakeupwoi.domain.model.Alarm
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAlarmBottomSheet(onDismiss: () -> Unit = {}, onClickAdd: (Alarm) -> Unit = {}) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )
    val scope = rememberCoroutineScope()

    val timePickerState = rememberTimePickerState(
        initialHour = 12, initialMinute = 0, is24Hour = false
    );

    var labelTxt by remember { mutableStateOf("") }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                TimeInput(state = timePickerState)
            }
            InputField(
                value = labelTxt,
                onChange = {
                    labelTxt = it
                },
                label = "Label",
                placeholder = "Alarm 1"
            )
            Button(
                onClick = {
                    onClickAdd(
                        Alarm(
                            hour = timePickerState.hour,
                            minute = timePickerState.minute,
                            isActive = true,
                            label = labelTxt,
                            isRepeat = false
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