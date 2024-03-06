package com.example.examenib.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examenib.R
import com.example.examenib.model.baseDeDatos.fireStoreUsuario
import com.example.examenib.model.entities.Usuario
import java.time.LocalDate

class FormUsuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_usuario)

        val positionUsuarioSelected = intent.getIntExtra("positionUsuarioSelected", -1)

        val inputNombreUsuario = findViewById<EditText>(R.id.inputNombre)
        val inputSueldo = findViewById<EditText>(R.id.inputSueldo)
        val inputUsuarioBetado = findViewById<EditText>(R.id.inputBetado)
        val inputFechaNacimientoUsuario = findViewById<EditText>(R.id.inputCreacionCuenta)
        val btnCrear = findViewById<Button>(R.id.btnAggUsuario)

        val codigoUsuario = intent.getStringExtra("positionUsuarioSelected")
        if (!codigoUsuario.isNullOrEmpty()) {
            fireStoreUsuario.consultarUsuario(codigoUsuario) { usuario ->
                usuario?.let {
                    inputNombreUsuario.setText(it.nombre)
                    inputSueldo.setText(it.sueldo.toString())
                    inputUsuarioBetado.setText(it.usuarioBetado.toString())
                    inputFechaNacimientoUsuario.setText(it.fechaNacimiento.toString())
                }
            }
        }

        btnCrear.setOnClickListener {
            val nombre = inputNombreUsuario.text.toString()
            val sueldo = inputSueldo.text.toString().toLong()
            val betado = inputUsuarioBetado.text.toString().toBoolean()
            val fechaNacimiento = LocalDate.parse(inputFechaNacimientoUsuario.text.toString())

            val usuario = Usuario(nombre, betado, sueldo, fechaNacimiento)

            fireStoreUsuario.crearUsuario(usuario)

            Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        }
    }
}
