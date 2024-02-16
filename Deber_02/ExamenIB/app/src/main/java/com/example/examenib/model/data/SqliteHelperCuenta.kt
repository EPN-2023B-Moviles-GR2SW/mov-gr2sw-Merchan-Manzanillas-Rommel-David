package com.example.examenib.model.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examenib.model.entities.Cuenta

class SqliteHelperCuenta(
    contexto: Context,
) : SQLiteOpenHelper(
    contexto,
    "cuentas", // nombre BDD
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaCuenta =
            """
           CREATE TABLE CUENTA(
               ID INTEGER PRIMARY KEY AUTOINCREMENT,
               NOMBRE_CUENTA VARCHAR(100),
               CANTIDAD REAL,
               FECHA_CREACION DATE,
               ES_CADUCADA INTEGER
           )
        """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaCuenta)
    }

    override fun onUpgrade(db: SQLiteDatabase?,
                           oldVersion: Int,
                           newVersion: Int) {}

    fun crearCuenta(
        descripcion: String,
        saldo: Double,
        fechaCreacion: String,
        esActiva: Int,
    ): Boolean {
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("DESCRIPCION", descripcion)
        valoresAGuardar.put("SALDO", saldo)
        valoresAGuardar.put("FECHA_CREACION", fechaCreacion)
        valoresAGuardar.put("ES_ACTIVA", esActiva)
        val resultadoGuardar = basedatosEscritura
            .insert(
                "CUENTA", // Nombre tabla
                null,
                valoresAGuardar // valores
            )
        basedatosEscritura.close()
        return resultadoGuardar != -1L
    }

    fun eliminarCuenta(id: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "CUENTA", // Nombre tabla
                "ID=?", // Consulta Where
                arrayOf(id.toString())
            )
        conexionEscritura.close()
        return resultadoEliminacion > 0
    }

    fun actualizarCuenta(
        id: Int,
        descripcion: String,
        saldo: Double,
        fechaCreacion: String,
        esActiva: Int
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("DESCRIPCION", descripcion)
        valoresAActualizar.put("SALDO", saldo)
        valoresAActualizar.put("FECHA_CREACION", fechaCreacion)
        valoresAActualizar.put("ES_ACTIVA", esActiva)
        val resultadoActualizacion = conexionEscritura
            .update(
                "CUENTA", // Nombre tabla
                valoresAActualizar, // Valores
                "ID=?", // Consulta Where
                arrayOf(id.toString())
            )
        conexionEscritura.close()
        return resultadoActualizacion > 0
    }

    fun consultarCuentaPorId(id: Int): Cuenta? {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM CUENTA WHERE ID = ?"
        val parametrosConsultaLectura = arrayOf(id.toString())
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaLectura,
            parametrosConsultaLectura
        )

        var cuenta: Cuenta? = null

        if (resultadoConsultaLectura.moveToFirst()) {
            val nombre = resultadoConsultaLectura.getString(1)
            val saldo = resultadoConsultaLectura.getDouble(2)
            val fechaCreacion = resultadoConsultaLectura.getString(3)
            val esActiva = resultadoConsultaLectura.getInt(4) == 1

            cuenta = Cuenta(nombre, saldo, fechaCreacion, esActiva)
        }

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return cuenta
    }

    fun getAll(): MutableList<Cuenta> {
        val baseDatosLectura = readableDatabase
        val scriptConsultaLectura = "SELECT * FROM CUENTA"
        val result = baseDatosLectura.rawQuery(scriptConsultaLectura, null)

        val cuentas = mutableListOf<Cuenta>()

        if (result.moveToFirst()) {
            do {
                val id = result.getInt(0)
                val nombre = result.getString(1)
                val saldo = result.getDouble(2)
                val fechaCreacion = result.getString(3)
                val esActiva = result.getInt(4) == 1

                val cuenta = Cuenta(nombre, saldo, fechaCreacion, esActiva)
                cuentas.add(cuenta)
            } while (result.moveToNext())
        }

        result.close()
        baseDatosLectura.close()
        return cuentas
    }
}

