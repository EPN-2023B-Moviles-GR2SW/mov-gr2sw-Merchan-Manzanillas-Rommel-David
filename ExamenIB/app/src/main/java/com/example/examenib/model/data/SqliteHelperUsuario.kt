package com.example.examenib.model.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examenib.model.entities.Usuario

class SqliteHelperUsuario(contexto: Context) : SQLiteOpenHelper(
    contexto,
    "usuarios", // nombre BDD
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaUsuario =
            """
               CREATE TABLE USUARIO(
                   NOMBRE VARCHAR(50),
                   FECHA_NACIMIENTO TEXT,
                   SUELDO REAL,
                   ESTA_BETADO INTEGER
               )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaUsuario)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearUsuario(usuario: Usuario): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("NOMBRE", usuario.nombre)
        valoresAGuardar.put("FECHA_NACIMIENTO", usuario.fechaNacimiento.toString())
        valoresAGuardar.put("SALARIO", usuario.sueldo)
        valoresAGuardar.put("ESTA_BETADO", if (usuario.usuarioBetado) 1 else 0)
        val resultadoGuardar = basedatosEscritura.insert("USUARIO", null, valoresAGuardar)
        basedatosEscritura.close()
        return resultadoGuardar != -1L
    }

    fun eliminarUsuario(nombre: String): Boolean {
        val conexionEscritura = writableDatabase
        val parametrosConsultaDelete = arrayOf(nombre)
        val resultadoEliminacion = conexionEscritura.delete(
            "USUARIO",
            "NOMBRE=?",
            parametrosConsultaDelete
        )
        conexionEscritura.close()
        return resultadoEliminacion != -1
    }

    fun actualizarUsuario(usuario: Usuario): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("NOMBRE", usuario.nombre)
        valoresAActualizar.put("FECHA_NACIMIENTO", usuario.fechaNacimiento.toString())
        valoresAActualizar.put("SALARIO", usuario.sueldo)
        valoresAActualizar.put("ESTA_BETADO", if (usuario.usuarioBetado) 1 else 0)

        val parametrosConsultaActualizar = arrayOf(usuario.nombre)
        val resultadoActualizacion = conexionEscritura.update(
            "USUARIO",
            valoresAActualizar,
            "NOMBRE=?",
            parametrosConsultaActualizar
        )
        conexionEscritura.close()
        return resultadoActualizacion != -1
    }

    fun consultarUsuarioPorNombre(nombre: String): Usuario {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM USUARIO WHERE NOMBRE = ?"
        val parametrosConsultaLectura = arrayOf(nombre)
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura
        )

        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        var usuarioEncontrado = Usuario("", "", 0.0, false)
        if (existeUsuario) {
            do {
                val nombre = resultadoConsultaLectura.getString(0)
                val fechaNacimiento = resultadoConsultaLectura.getString(1)
                val salario = resultadoConsultaLectura.getDouble(2)
                val esActivo = resultadoConsultaLectura.getInt(3) == 1
                if (nombre != null) {
                    usuarioEncontrado = Usuario(nombre, fechaNacimiento, salario, esActivo)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarioEncontrado
    }

    fun getAllUsuarios(): MutableList<Usuario> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM USUARIO"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        val usuarios = mutableListOf<Usuario>()
        if (resultadoConsultaLectura.moveToFirst()) {
            do {
                val nombre = resultadoConsultaLectura.getString(0)
                val fechaNacimiento = resultadoConsultaLectura.getString(1)
                val salario = resultadoConsultaLectura.getDouble(2)
                val esBetado = resultadoConsultaLectura.getInt(3) == 1

                usuarios.add(Usuario(nombre, fechaNacimiento, salario, esBetado))
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return usuarios
    }

}
