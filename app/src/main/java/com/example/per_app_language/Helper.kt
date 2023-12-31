package com.example.per_app_language

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


class DropDownUiModel(val displayText: String, val countryCode: String)

val dropdownItemList = listOf(
    DropDownUiModel("English", "en"),
    DropDownUiModel("German", "de-DE"),
    DropDownUiModel("Spanish", "es-ES"),
    DropDownUiModel("Turkish", "tr-TR")
)

private val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
val formattedCurrentDate: String
    get() {
        val now = LocalDate.now()
        return formatter.format(now)
    }