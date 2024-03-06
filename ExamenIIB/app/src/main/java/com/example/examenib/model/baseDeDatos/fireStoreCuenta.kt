package com.example.examenib.model.baseDeDatos

import com.example.examenib.model.entities.Cuenta
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class fireStoreCuenta {
    companion object{
        fun crearCuenta(cuenta: Cuenta, nombreUsuario: String){
            val db = Firebase.firestore
            val cuentas = db.collection("cuentas")

            val datosCuenta = hashMapOf(
                "nombreCuenta" to cuenta.nombreCuenta,
                "cantidad" to cuenta.cantidad ,
                "esCaducada" to cuenta.esCaducada,
                "nombreUsuario" to nombreUsuario
            )
            cuentas.document(cuenta.nombreCuenta).set(datosCuenta)
        }

        fun consultarCuentas(listener: (ArrayList<Cuenta>) -> Unit)
        {
            val db = Firebase.firestore
            val arregloCuenta = arrayListOf<Cuenta>()
            val cuentasRefUnico = db.collection("cuentas")

            cuentasRefUnico
                .orderBy("nombre", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener {querySnapshot ->
                    // it == eso (lo que llegue)
                    for (cuenta in querySnapshot){
                        cuenta.id
                        arregloCuenta.add(anadirCuenta(cuenta))
                    }
                    listener(arregloCuenta)
                }
                .addOnFailureListener{
                    // Errores
                }
        }


        fun anadirCuenta(
            cuenta: QueryDocumentSnapshot
        ) : Cuenta {
            val nuevaCuenta =  Cuenta(
                cuenta.id as String,
                cuenta.data.get("cantidad") as Long,
                cuenta.data.get("fechaCreacion") as LocalDate,
                cuenta.data.get("esCaducada") as Boolean
            )
            return nuevaCuenta
        }

        fun consultarCuenta(
            codigo: String,
            onSuccess: (Cuenta) -> Unit
        ) {
            val db = Firebase.firestore
            val cuentasRefUnica = db.collection("cuentas")
            cuentasRefUnica
                .document(codigo)
                .get() // obtener 1 DOCUMENTO
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null && document.exists()) {
                            val cuenta = Cuenta(
                                document.reference.id,
                                document.data?.get("cantidad") as Long,
                                document.data?.get("fechaCreacion") as LocalDate,
                                document.data?.get("esCaducada") as Boolean
                            )
                            onSuccess(cuenta)
                        } else {
                            //salio mal
                        }
                    } else {
                        //salio mal
                    }
                }
        }

        fun eliminarCuenta(
            nombreCuenta: String
        ){
            val db = Firebase.firestore
            val cuentasRefUnica = db
                .collection("cuentas")

            cuentasRefUnica
                .document(nombreCuenta)
                .delete()
                .addOnCompleteListener{ /* si todo sale bien */}
                .addOnFailureListener{/* Si algo salio mal*/}
        }

        fun actualizarCuenta(
            cuenta: Cuenta
        ){
            val db = Firebase.firestore
            val cuentasRefUnica = db
                .collection("cuentas")

            val datosActualizados = hashMapOf(
                "nombreCuenta" to cuenta.nombreCuenta,
                "cantidad" to cuenta.cantidad,
                "esCaducada" to cuenta.esCaducada
            )

            cuentasRefUnica
                .document(cuenta.nombreCuenta)
                .update(datosActualizados as Map<String, Any>)
                .addOnSuccessListener {
                    // Operación de actualización exitosa
                }
                .addOnFailureListener { e ->
                    // Manejar el error en caso de falla
                }
        }

        fun consultarCuentasUsuarios(
            nombreUsuario: String,
            listener: (ArrayList<Cuenta>) -> Unit
        ){
            var arregloCuentas = arrayListOf<Cuenta>()
            val db = Firebase.firestore
            val cuentasRefUnica = db.collection("cuentas")
            cuentasRefUnica
                .whereEqualTo("nombreUsuario", nombreUsuario)
                .orderBy("nombreUsuario", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (cuenta in querySnapshot){
                        cuenta.id
                        arregloCuentas.add(anadirCuenta(cuenta))
                    }
                    listener(arregloCuentas)
                }
                .addOnFailureListener{
                    // Errores
                }
        }
    }
}