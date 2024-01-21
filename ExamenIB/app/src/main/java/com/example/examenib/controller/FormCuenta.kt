package com.example.examenib.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examenib.R
import com.example.examenib.model.data.BaseDatos
import com.example.examenib.model.entities.Cuenta
import java.text.ParseException
import java.time.LocalDate

class FormCuenta : AppCompatActivity() {

    lateinit var nombreCuenta: String
    var cantidad: Double = 0.00
    lateinit var fechaCreacion: LocalDate
    var esCaducado: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cuenta)

        val positionCuentaSelected = intent.getIntExtra("positionCuentaSelected", -1)
        val positionUsuarioSelected = intent.getIntExtra("positionUsuarioSelected", -1)

        val inputNombreCuenta = findViewById<EditText>(R.id.inputNombreCuenta)
        val inputCaducado = findViewById<EditText>(R.id.inputCaducado)
        val inputCreacionCuenta = findViewById<EditText>(R.id.inputCreacionCuenta)
        val inputCantidad = findViewById<EditText>(R.id.inputCantidad)
        val btnCrear = findViewById<Button>(R.id.btnAggPelicula)
        val btnActualizar = findViewById<Button>(R.id.btnActualizarPelicula)

        if (positionCuentaSelected != -1) {
            // Editar
            btnActualizar.isEnabled = true
            btnCrear.isEnabled = false

            inputNombreCuenta.setText(BaseDatos.datos[positionUsuarioSelected].cuentas[positionCuentaSelected].nombreCuenta)
            inputCaducado.setText(BaseDatos.datos[positionUsuarioSelected].cuentas[positionCuentaSelected].esCaducada.toString())
            inputCreacionCuenta.setText(BaseDatos.datos[positionUsuarioSelected].cuentas[positionCuentaSelected].fechaCreacion.toString())
            inputCantidad.setText(BaseDatos.datos[positionUsuarioSelected].cuentas[positionCuentaSelected].cantidad.toString())
            //Actualizacion de cuenta
            btnActualizar.setOnClickListener {
                try {
                    nombreCuenta = inputNombreCuenta.text.toString()
                    esCaducado = inputCaducado.text.toString().toBoolean()
                    fechaCreacion = LocalDate.parse(inputCreacionCuenta.text.toString())
                    cantidad = inputCantidad.text.toString().toDouble()
                    //Actualizacion de cuenta en la base de datos
                    BaseDatos.datos[positionUsuarioSelected].cuentas[positionCuentaSelected].nombreCuenta =
                        nombreCuenta
                    BaseDatos.datos[positionUsuarioSelected].cuentas[positionCuentaSelected].esCaducada =
                        esCaducado
                    BaseDatos.datos[positionUsuarioSelected].cuentas[positionCuentaSelected].fechaCreacion =
                        fechaCreacion
                    BaseDatos.datos[positionUsuarioSelected].cuentas[positionCuentaSelected].cantidad =
                        cantidad
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
                    //Añade un cuenta a su respectivo usuario
                    BaseDatos.datos[positionUsuarioSelected].cuentas.add(
                        Cuenta(
                            nombreCuenta,
                            cantidad,
                            fechaCreacion,
                            esCaducado
                        )
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