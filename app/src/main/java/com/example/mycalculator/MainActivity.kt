package com.example.mycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycalculator.ui.theme.MyCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorScreen()


        }
    }
}

@Composable
fun CalculatorScreen(){
    var value1 by remember { mutableStateOf("") }
    var value2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {
        TextField(
            value = value1,
            onValueChange = { value1 = it},
            label = { Text("Value 1")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = value2,
            onValueChange = { value2 = it},
            label = { Text("Value 2")},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            Modifier
                .padding(vertical = 16.dp)
                .align(CenterHorizontally)) {
            Button(onClick = { operator = "+" }, modifier = Modifier.padding(5.dp)) {
                Text("+")
            }
            Button(onClick = { operator = "-" }, modifier = Modifier.padding(5.dp)) {
                Text("-")
            }
            Button(onClick = { operator = "*" }, modifier = Modifier.padding(5.dp)) {
                Text("*")
            }
            Button(onClick = { operator = "/" }, modifier = Modifier.padding(5.dp)) {
                Text("/")
            }
            Button(modifier = Modifier.padding(5.dp), onClick = {
                if (value1.isNotEmpty() && value2.isNotEmpty() && operator.isNotEmpty()) {
                    result = when (operator) {
                        "+" -> (value1.toDouble() + value2.toDouble()).toString()
                        "-" -> (value1.toDouble() - value2.toDouble()).toString()
                        "*" -> (value1.toDouble() * value2.toDouble()).toString()
                        "/" -> (value1.toDouble() / value2.toDouble()).toString()
                        else -> ""
                    }
                }
            }) {
                Text("=")
            }
        }

        Button(modifier = Modifier.padding(5.dp), onClick = {
            value1 = ""
            value2 = ""
            operator = ""
            result = ""
        }) {
            Text("Clear")
        }
        if (result.isNotEmpty()) {
            Text("Result: $result", Modifier.padding(vertical = 16.dp))
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    CalculatorScreen()
}

