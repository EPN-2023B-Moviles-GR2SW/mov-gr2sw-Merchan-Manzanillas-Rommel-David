package com.example.examenib.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examenib.R
import com.example.examenib.model.data.BaseDeDatos
import com.example.examenib.model.data.SqliteHelperUsuario
import com.example.examenib.model.entities.Usuario
import java.text.ParseException
import java.time.LocalDate

class FormUsuario : AppCompatActivity() {

    lateinit var nombre: String
    var sueldo: Double = 0.00
    var betado: Boolean = false
    lateinit var fechaNacimiento: LocalDate

    lateinit var sqliteHelperUsuario: SqliteHelperUsuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_usuario)

        sqliteHelperUsuario = SqliteHelperUsuario(this)

        val positionUsuarioSelected = intent.getIntExtra("positionUsuarioSelected", -1)

        val inputNombreUsuario = findViewById<EditText>(R.id.inputNombre)
        val inputSueldo = findViewById<EditText>(R.id.inputSueldo)
        val inputUsuarioBetado = findViewById<EditText>(R.id.inputBetado)
        val inputFechaNacimientoUsuario = findViewById<EditText>(R.id.inputCreacionCuenta)
        val btnCrear = findViewById<Button>(R.id.btnAggUsuario)
        val btnActualizar = findViewById<Button>(R.id.btnActualizarUsuario)

        if (positionUsuarioSelected != -1) {
            // Editar
            btnCrear.isEnabled = false
            btnActualizar.isEnabled = true

            val usuarioSeleccionado = sqliteHelperUsuario.consultarUsuarioPorNombre(
                BaseDeDatos.tablaUsuario?.consultarUsuarioPorNombre(nombre).toString()
            )
            inputNombreUsuario.setText(usuarioSeleccionado.nombre)
            inputSueldo.setText(usuarioSeleccionado.sueldo.toString())
            inputUsuarioBetado.setText(usuarioSeleccionado.usuarioBetado.toString())
            inputFechaNacimientoUsuario.setText(usuarioSeleccionado.fechaNacimiento.toString())
            btnActualizar.setOnClickListener {
                try {
                    nombre = inputNombreUsuario.text.toString()
                    sueldo = inputSueldo.text.toString().toDouble()
                    betado = inputUsuarioBetado.text.toString().toBoolean()
                    fechaNacimiento = LocalDate.parse(inputFechaNacimientoUsuario.text.toString())

                    val usuario = Usuario(nombre, fechaNacimiento, sueldo, betado)
                    if (sqliteHelperUsuario.actualizarUsuario(usuario)) {
                        Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
                        setResult(RESULT_OK, Intent())
                        finish()
                    } else {
                        Toast.makeText(this, "Error al actualizar el usuario", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: ParseException) {
                    Toast.makeText(this, "Error al parsear la fecha", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show()
                }

            }
        } else {
            // Crear
            btnCrear.isEnabled = true
            btnActualizar.isEnabled = false
            btnCrear.setOnClickListener {
                try {
                    nombre = inputNombreUsuario.text.toString()
                    sueldo = inputSueldo.text.toString().toDouble()
                    betado = inputUsuarioBetado.text.toString().toBoolean()
                    fechaNacimiento = LocalDate.parse(inputFechaNacimientoUsuario.text.toString())

                    val usuario = Usuario(nombre, fechaNacimiento, sueldo, betado)
                    if (sqliteHelperUsuario.crearUsuario(usuario)) {
                        Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show()
                        setResult(RESULT_OK, Intent())
                        finish()
                    } else {
                        Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: ParseException) {
                    Toast.makeText(this, "Error al parsear la fecha", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
