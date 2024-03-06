package com.example.examenib.model.entities

import java.time.LocalDate

data class Cuenta(
    var nombreCuenta: String,
    var cantidad: Long,
    var fechaCreacion: LocalDate? = null,
    var esCaducada: Boolean,
    var nombreUsuario: String? = null,
) {

    override fun toString(): String {
        return "Tipo Cuenta: $nombreCuenta - Cantidad: $cantidad - Caducada:$esCaducada"
    }
}