package com.jezerm.sistematicopersistencia

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jezerm.sistematicopersistencia.database.Repositorio
import com.jezerm.sistematicopersistencia.database.entities.EntityProducto
import com.jezerm.sistematicopersistencia.ui.theme.SistematicoPersistenciaTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun InsertarRegistro() {
    val repository = Repositorio.getInstance()
    val coroutineScope = rememberCoroutineScope()

    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var existe by remember { mutableStateOf(true) }

    fun clearInput() {
        nombre = ""
        precio = ""
        existe = true
    }

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        modifier = Modifier.imePadding(),
        scaffoldState = scaffoldState,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text("Insertar producto", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = {
                Text(text = "Nombre")
            })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = {
                    Text(text = "Precio")
                })
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically, ) {
                Checkbox(
                    checked = existe,
                    onCheckedChange = { existe = it }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text("Existe")
            }
            Spacer(modifier = Modifier.height(32.dp))

            Row {
                OutlinedButton(onClick = {
                    clearInput()
                }) {
                    Text("Limpiar")
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {
                    val precioDouble = precio.toDouble()
                    val producto = EntityProducto(0, nombre, precioDouble, existe)

                    coroutineScope.launch(Dispatchers.IO) {
                        try {
                            repository.insert(producto)
                            clearInput()
                            scaffoldState.snackbarHostState.showSnackbar(
                                "El producto fue añadido"
                            )
                        } catch (e: Exception) {
                            scaffoldState.snackbarHostState.showSnackbar(
                                "El producto no pudo ser añadido",
                                duration = SnackbarDuration.Long
                            )
                        }
                    }
                }) {
                    Text("Añadir")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InsertarRegistroPreview() {
    SistematicoPersistenciaTheme {
        InsertarRegistro()
    }
}
