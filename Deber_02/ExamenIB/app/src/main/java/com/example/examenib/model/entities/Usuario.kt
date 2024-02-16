package com.example.examenib.model.entities

data class Usuario(
    var nombre: String,
    var fechaNacimiento: String,
    var sueldo: Double,
    var usuarioBetado: Boolean,
    var cuentas: MutableList<Cuenta> = mutableListOf()
) {


    override fun toString(): String {
        return "Nombre Usuario: $nombre - Sueldo: $sueldo - Betado: $usuarioBetado"
    }
}