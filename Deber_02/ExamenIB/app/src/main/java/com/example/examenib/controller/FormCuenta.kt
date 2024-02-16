package com.example.examenib.controller

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examenib.R
import com.example.examenib.model.data.SqliteHelperCuenta
import java.text.ParseException
import java.time.LocalDate

class FormCuenta : AppCompatActivity() {

    lateinit var nombreCuenta: String
    var cantidad: Double = 0.00
    lateinit var fechaCreacion: LocalDate
    var esCaducado: Boolean = false

    private lateinit var dbHelperCuenta: SqliteHelperCuenta
    private var positionCuentaSelected = -1
    private var positionUsuarioSelected = -1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cuenta)

        dbHelperCuenta = SqliteHelperCuenta(this)

        positionCuentaSelected = intent.getIntExtra("positionCuentaSelected", -1)
        positionUsuarioSelected = intent.getIntExtra("positionUsuarioSelected", -1)

        val inputNombreCuenta = findViewById<EditText>(R.id.inputNombreCuenta)
        val inputCaducado = findViewById<EditText>(R.id.inputCaducado)
        val inputCreacionCuenta = findViewById<EditText>(R.id.inputCreacionCuenta)
        val inputCantidad = findViewById<EditText>(R.id.inputCantidad)
        val btnCrear = findViewById<Button>(R.id.btnAggCuenta)
        val btnActualizar = findViewById<Button>(R.id.btnActualizarCuenta)

        if (positionCuentaSelected != -1) {
            // Editar
            btnActualizar.isEnabled = true
            btnCrear.isEnabled = false

            val cuenta = dbHelperCuenta.consultarCuentaPorId(positionCuentaSelected)
            inputNombreCuenta.setText(cuenta?.nombreCuenta)
            inputCaducado.setText(cuenta?.esCaducada.toString())
            inputCreacionCuenta.setText(cuenta?.fechaCreacion.toString())
            inputCantidad.setText(cuenta?.cantidad.toString())

            // Actualizacion de cuenta
            btnActualizar.setOnClickListener {
                try {
                    nombreCuenta = inputNombreCuenta.text.toString()
                    esCaducado = inputCaducado.text.toString().toBoolean()
                    fechaCreacion = LocalDate.parse(inputCreacionCuenta.text.toString())
                    cantidad = inputCantidad.text.toString().toDouble()

                    dbHelperCuenta.actualizarCuenta(
                        positionCuentaSelected,
                        nombreCuenta,
                        cantidad,
                        fechaCreacion.toString(),
                        if (esCaducado) 1 else 0
                    )

                    Toast.makeText(this, "Cuenta actualizada", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent())
                    finish()
                } catch (e: ParseException) {
                    Toast.makeText(this, "Error al parsear la fecha", Toast.LENGTH_SHORT).show()
                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        this,
                        "Error al convertir la cantidad a número",
                        Toast.LENGTH_SHORT
                    ).show()
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
                    nombreCuenta = inputNombreCuenta.text.toString()
                    cantidad = inputCantidad.text.toString().toDouble()
                    fechaCreacion = LocalDate.parse(inputCreacionCuenta.text.toString())
                    esCaducado = inputCaducado.text.toString().toBoolean()

                    dbHelperCuenta.crearCuenta(
                        nombreCuenta,
                        cantidad,
                        fechaCreacion.toString(),
                        if (esCaducado) 1 else 0
                    )

                    Toast.makeText(this, "Cuenta creada", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK, Intent())
                    finish()
                } catch (e: ParseException) {
                    Toast.makeText(this, "Error al parsear la fecha", Toast.LENGTH_SHORT).show()
                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        this,
                        "Error al convertir la cantidad a número",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error desconocido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
