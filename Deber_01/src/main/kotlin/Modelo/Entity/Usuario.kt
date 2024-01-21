package Modelo.Entity

import java.text.SimpleDateFormat
import java.util.*

class Usuario (
    val nombre: String,
    val fechaNacimiento: Date,
    val id: Int,
    val sueldo: Double,
    val usuarioBetado: Boolean
){
    override fun toString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = dateFormat.format(fechaNacimiento)

        return """
            |************************************************
            |Usuario {
            |   ID: $id,
            |   Nombre: $nombre,
            |   Fecha de Nacimiento: $formattedDate,
            |   Sueldo: $sueldo,
            |   ¿Betado?: $usuarioBetado
            |}
        """.trimMargin()
    }
}