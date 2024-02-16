package com.example.examenib.model.entities

data class Cuenta(
    var nombreCuenta: String,
    var cantidad: Double,
    var fechaCreacion: String,
    var esCaducada: Boolean,
    val id: Int
) {

    override fun toString(): String {
        return "Tipo Cuenta: $nombreCuenta - Cantidad: $cantidad - Caducada:$esCaducada"
    }
}