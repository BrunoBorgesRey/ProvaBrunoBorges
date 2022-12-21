package com.example.provabrunoborges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.provabrunoborges.model.Vehicle
import com.example.provabrunoborges.ui.theme.ProvaBrunoBorgesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen1()
        }
    }
}

@Composable
fun Screen1() {

    var vehicleDetails by remember { mutableStateOf( Vehicle(1)) }
    ProvaBrunoBorgesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 5.dp)
                    )
            {
                var textFieldState by remember {
                    mutableStateOf("")
                }
                var textFieldState2 by remember {
                    mutableStateOf("")
                }
                var textFieldState3 by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(
                    value = textFieldState,
                    label = { Text("Model")},
                    onValueChange = {textFieldState = it}
                )
                MyUI()
                OutlinedTextField(
                    value = textFieldState3,
                    label = { Text("Price")},
                    onValueChange = {textFieldState3 = it}
                )

                
                Button(onClick = { /*TODO*/ }) {
                    Text( "Submit")
                }
                val vehicles = mutableListOf(
                    Vehicle("Fulano 1", 0.0, "HATCH", false,0L )
                )
                VehicleList(vehicles = vehicles) {
                    vehicleDetails = it
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyUI() {

    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")

    var selectedItem by remember {
        mutableStateOf("")
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {

        OutlinedTextField(
            value = selectedItem,
            onValueChange = { selectedItem = it },
            label = { Text(text = "Choose a Type") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )

        // filter options based on text field value
        val filteringOptions =
            listItems.filter { it.contains(selectedItem, ignoreCase = true) }

        if (filteringOptions.isNotEmpty()) {

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                filteringOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedItem = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }
}

@Composable
fun VehicleView(vehicle: Vehicle, onClick: () -> Unit) {
    Card(
        modifier =
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
            },
        elevation = 4.dp
    ) {
        Row {

            Text(
                text = vehicle.model,
                fontSize = 24.sp,
                modifier =
                Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun VehicleList(vehicles: MutableList<Vehicle>,
                onClick: (vehicle: Vehicle) -> Unit) {
    LazyColumn {
        items(vehicles) { vehicle ->
            VehicleView(vehicle = vehicle) {
                onClick(vehicle)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Screen1()
}