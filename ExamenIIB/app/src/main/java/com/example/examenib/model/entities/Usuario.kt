package com.example.examenib.model.entities

import java.time.LocalDate

data class Usuario(
    var nombre: String,
    var usuarioBetado: Boolean,
    var sueldo: Long,
    var fechaNacimiento: LocalDate? = null,
    var cuentas: MutableList<Cuenta>? = null
) {


    override fun toString(): String {
        return "Nombre Usuario: $nombre - Sueldo: $sueldo - Betado: $usuarioBetado"
    }
}