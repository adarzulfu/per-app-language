package com.example.pre_app_language

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.setApplicationLocales
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.os.LocaleListCompat
import com.example.pre_app_language.ui.theme.PreapplanguageTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreapplanguageTheme {
                Content()
            }
        }
    }
}

private fun changeLanguage(code: String) {
    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(code)
    setApplicationLocales(appLocale)
}

@Composable
fun Content() {
    Column(modifier = Modifier.fillMaxSize()) {

        DropdownContent()

        Row(
            modifier = Modifier
                .align(CenterHorizontally)
        ) {
            Greeting(LocalContext.current.getString(R.string.hello))
            Image(
                painter = painterResource(id = R.mipmap.country_flag),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownContent() {
    val currentLanguage = AppCompatDelegate.getApplicationLocales()[0]
    val selectedCountry =
        dropdownItemList.firstOrNull { it.countryCode == currentLanguage?.toLanguageTag() }?.displayText
            ?: ""

    var selectedText by remember { mutableStateOf(selectedCountry) }

    var textFieldSize by remember { mutableStateOf(Size(0f, 0f)) }

    var expanded by remember {
        mutableStateOf(false)
    }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        val disabledItem = 0
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text("Label") },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { expanded = !expanded })
            }
        )



        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            content = {
                dropdownItemList.forEachIndexed { itemIndex, itemValue ->
                    DropdownMenuItem(
                        text = {
                            Text(text = itemValue.displayText)
                        },
                        onClick = {
                            selectedText = itemValue.displayText
                            changeLanguage(itemValue.countryCode)
                            expanded = false
                        },
                        enabled = (itemIndex != disabledItem)
                    )
                }
            }
        )
    }
}

@Composable
fun Greeting(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontSize = 30.sp,
        modifier = modifier
    )
}