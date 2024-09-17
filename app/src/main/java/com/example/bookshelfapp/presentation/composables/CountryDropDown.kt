package com.example.bookshelfapp.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bookshelfapp.domain.entity.CountryListResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDropDown(countryList: List<CountryListResponse>, ipDetails: String) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(ipDetails) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isExpanded) {
        if (isExpanded) {
            focusRequester.requestFocus()
        }
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        modifier = Modifier.padding(horizontal = 40.dp),
        onExpandedChange = { expanded ->
            isExpanded = expanded
        }
    ) {
        TextField(
            value = selectedOptionText,
            onValueChange = {},
            readOnly = true,
            colors = TextFieldDefaults.colors(
                focusedLabelColor = Color.Black,
                disabledLabelColor = Color.Red
            ),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            modifier = Modifier.menuAnchor()
                .focusRequester(focusRequester)
                .fillMaxWidth()
                .clickable {
                    isExpanded = !isExpanded
                }
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            countryList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(text = item.country ?: "Default Country") },
                    onClick = {
                        isExpanded = false
                        selectedOptionText = item.country ?: "Default"
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
