package com.example.examenib.model.data

import com.example.examenib.model.entities.Cuenta
import com.example.examenib.model.entities.Usuario
import java.time.LocalDate

class BaseDeDatos {
    companion object{
    var tablaUsuario: SqliteHelperUsuario? = null
    var tablaCuenta: SqliteHelperCuenta? = null
    var profesorSelecciondo = Usuario("", LocalDate.of(2010, 11, 10).toString(), 0.0, false, arrayListOf())
    var materiaSeleccionada = Cuenta("",0.0, LocalDate.of(2010, 11, 10), true)
    }
}