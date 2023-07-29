package com.example.pre_app_language


class DropDownUiModel(val displayText: String, val countryCode: String)

val dropdownItemList = listOf(
    DropDownUiModel("English", "en"),
    DropDownUiModel("German", "de"),
    DropDownUiModel("Spanish", "es"),
    DropDownUiModel("Turkish", "tr")
)