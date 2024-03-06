package com.example.examenib.model.baseDeDatos

import com.example.examenib.model.entities.Cuenta
import com.example.examenib.model.entities.Usuario
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.ZoneId

class fireStoreUsuario {
    companion object {
        fun crearUsuario(usuario: Usuario) {
            val db = Firebase.firestore
            val usuarios = db.collection("usuarios")

            val datosUsuario = hashMapOf(
                "nombre" to usuario.nombre,
                "sueldo" to usuario.sueldo,
                "usuarioBetado" to usuario.usuarioBetado,
                "fechaNacimiento" to usuario.fechaNacimiento,
                "cuentas" to usuario.cuentas
            )
            usuarios.document(usuario.nombre).set(datosUsuario)
        }

        fun consultarUsuarios(listener: (ArrayList<Usuario>) -> Unit) {
            val db = Firebase.firestore
            val arregloUsuarios = arrayListOf<Usuario>()
            val usuariosRefUnico = db.collection("usuarios")

            usuariosRefUnico
                .orderBy("nombre")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (usuario in querySnapshot) {
                        arregloUsuarios.add(anadirUsuario(usuario))
                    }
                    listener(arregloUsuarios)
                }
                .addOnFailureListener {
                    // Manejar errores
                }
        }

        fun anadirUsuario(usuario: DocumentSnapshot): Usuario {
            return Usuario(
                usuario.id,
                usuario.getBoolean("usuarioBetado") ?: false,
                usuario.getLong("sueldo") ?: 0,
                usuario.getTimestamp("fechaNacimiento")?.toDate()?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate() ?: LocalDate.now(),
                usuario.get("cuentas") as? ArrayList<Cuenta>?
            )
        }

        fun consultarUsuario(nombre: String, listener: (Usuario?) -> Unit) {
            val db = Firebase.firestore
            val usuariosRefUnica = db.collection("usuarios")
            usuariosRefUnica
                .document(nombre)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        listener(anadirUsuario(document))
                    } else {
                        listener(null)
                    }
                }
                .addOnFailureListener {
                    // Manejar errores
                    listener(null)
                }
        }

        fun eliminarUsuario(
            nombre: String
        ){
            val db = Firebase.firestore
            val usuarioRefUnica = db
                .collection("usuarios")

            usuarioRefUnica
                .document(nombre)
                .delete()
                .addOnCompleteListener{ /* si todo sale bien */}
                .addOnFailureListener{/* Si algo salio mal*/}
        }

        fun actualizarUsuario(
            usuario: Usuario
        ){
            val db = Firebase.firestore
            val usuariosRefUnica = db
                .collection("usuarios")


            val datosActualizados = hashMapOf(
                "nombre" to usuario.nombre,
                "usuarioBetado" to usuario.usuarioBetado,
                "sueldo" to usuario.sueldo,
                "fechaNacimiento" to usuario.fechaNacimiento,
                "cuentas" to listOf("west_coast", "norcal"),
            )

            usuariosRefUnica
                .document(usuario.nombre)
                .update(datosActualizados)
                .addOnSuccessListener {
                    // Operación de actualización exitosa
                }
                .addOnFailureListener { e ->
                    // Manejar el error en caso de falla
                }
        }
    }
}
