package Modelo.Entity

import java.text.SimpleDateFormat
import java.util.Date

class Cuenta (
    val nombre: String,
    var id: Int,
    var usuarioId: Int,
    val cantidad: Double,
    val esCaducada: Boolean,
    val fechaCreacion: Date
){
    override fun toString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = dateFormat.format(fechaCreacion)

        return """
            |***********************************************
            |Cuenta {
            |   ID: $id,
            |   Usuario Asociado: $usuarioId,
            |   Tipo de Cuenta: $nombre,
            |   Caducidad: $esCaducada,
            |   Cantidad en la Cuenta: $cantidad,
            |   Fecha de Creación: $formattedDate
            |}
        """.trimMargin()
    }
}