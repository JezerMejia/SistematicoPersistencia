package com.jezerm.sistematicopersistencia

import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jezerm.sistematicopersistencia.database.BaseDatos
import com.jezerm.sistematicopersistencia.database.Repositorio
import com.jezerm.sistematicopersistencia.ui.theme.SistematicoPersistenciaTheme

class MainActivity : ComponentActivity() {
    val database by lazy { BaseDatos.obtBaseDatos(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Repositorio.init(database.productoDao())

        setContent {
            SistematicoPersistenciaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    InsertarRegistro()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SistematicoPersistenciaTheme {
        InsertarRegistro()
    }
}