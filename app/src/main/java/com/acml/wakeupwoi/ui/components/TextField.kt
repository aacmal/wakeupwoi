package com.acml.wakeupwoi.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun InputField(
    value: String,
    placeholder: String = "",
    onChange: (String) -> Unit,
    label: String = ""
) {
    TextField(
        value = value,
        placeholder = {
            Text(
                text = placeholder, style = TextStyle(
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },
        label = {
            Text(
                label, style = TextStyle(
                    fontSize = 14.sp
                )
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            containerColor = if (value.length >= 0) MaterialTheme.colorScheme.surfaceVariant.copy(
                alpha = 0.5f
            ) else Color.Transparent,
        ),
        onValueChange = {
            onChange(it)
        },
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            )
    )
}
