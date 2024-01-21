package com.example.examenib.model.entities

import java.time.LocalDate

data class Cuenta(
    var nombreCuenta: String,
    var cantidad: Double,
    var fechaCreacion: LocalDate,
    var esCaducada: Boolean
) {

    override fun toString(): String {
        return "Tipo Cuenta: $nombreCuenta - Cantidad: $cantidad - Caducada:$esCaducada"
    }
}