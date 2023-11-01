package com.acml.wakeupwoi.ui.screens.alarmdetail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acml.wakeupwoi.domain.model.Alarm
import com.acml.wakeupwoi.domain.repository.AlarmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AlarmDetailState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val timePickerState: TimePickerState = TimePickerState(
        initialHour = 0,
        initialMinute = 0,
        is24Hour = false
    ),
    val isRepeat: Boolean = false,
    val isActive: Boolean = false,
    val label: String = ""
)

@HiltViewModel
class AlarmDetailViewModel @Inject constructor(
    private val alarmRepository: AlarmRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val alarmId: String? = savedStateHandle["id"]

    private val _uiState = MutableStateFlow(AlarmDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        if (alarmId != null) {
            loadAlarm(alarmId)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun onLabelChange(label: String) {
        _uiState.update {
            it.copy(label = label)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun updateAlarm() {
        viewModelScope.launch {
            val alarm = Alarm(
                id = alarmId?.toInt() ?: 0,
                hour = _uiState.value.timePickerState.hour,
                minute = _uiState.value.timePickerState.minute,
                isRepeat = _uiState.value.isRepeat,
                isActive = _uiState.value.isActive,
                label = _uiState.value.label
            )
            alarmRepository.updateAlarm(alarm)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun loadAlarm(id: String) {
        viewModelScope.launch {
            alarmRepository.getAlarm(id.toInt()).let { alarm ->
                if (alarm != null) {
                    _uiState.update {
                        it.copy(
                            timePickerState = TimePickerState(
                                initialHour = alarm.hour,
                                initialMinute = alarm.minute,
                                is24Hour = false
                            ),
                            isRepeat = alarm.isRepeat,
                            isActive = alarm.isActive,
                            label = alarm.label
                        )
                    }
                }
            }
        }
    }
}