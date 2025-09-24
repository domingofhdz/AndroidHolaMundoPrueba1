package com.example.holamundo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.holamundo.ui.theme.HolaMundoTheme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HolaMundoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding -> AppContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var usuario: String by remember { mutableStateOf("") }
    var contrasena: String by remember { mutableStateOf("") }

    data class Opcion(val value: String, val label: String)
    val productoOpciones = listOf(
        Opcion("1", "Sponch Fresa"),
        Opcion("2", "Emperador Combinado"),
        Opcion("3", "Florentinas Cajeta")
    )
    var productoExpandido by remember { mutableStateOf(false) }
    var producto by remember { mutableStateOf(productoOpciones[0]) }

    var comentarios: String by remember { mutableStateOf(("")) }
    var precio: String by remember { mutableStateOf(("")) }
    var cantidad: String by remember { mutableStateOf(("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {


        Text(
            text = "Inicio de Sesión",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Usuario:")
        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            placeholder = { Text("Ingresa tu usuario") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Contraseña:")
        TextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            placeholder = { Text("Ingresa tu contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Iniciar sesión")
        }


        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Producto:")
        ExposedDropdownMenuBox(
            expanded = productoExpandido,
            onExpandedChange = { productoExpandido = !productoExpandido }
        ) {
            TextField(
                value = producto.label,
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona una opción") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = productoExpandido)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            DropdownMenu(
                expanded = productoExpandido,
                onDismissRequest = { productoExpandido = false }
            ) {
                productoOpciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion.label) },
                        onClick = {
                            producto = opcion
                            productoExpandido = false
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Comentarios:")
        TextField(
            value = comentarios,
            onValueChange = { comentarios = it },
            label = { Text("Ingresa comentarios") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            maxLines = 3,
            singleLine = false
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Precio:")
        TextField(
            value = precio,
            onValueChange = { precio = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Cantidad:")
        TextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            placeholder = { Text("Ingresa la cantidad") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Toast.makeText(context, "Producto Id: ${producto.value}", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Producto: ${producto.label}", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Comentarios: ${comentarios}", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Precio: ${precio}", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Cantidad: ${cantidad}", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Enviar")
        }


    }
}

@Preview(showBackground = true)
@Composable
fun AppContentPreview() {
    HolaMundoTheme {
        AppContent()
    }
}