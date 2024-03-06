package com.example.examenib.controller

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.examenib.R
import com.example.examenib.model.baseDeDatos.fireStoreCuenta
import com.example.examenib.model.entities.Cuenta

class ListaCuentas : AppCompatActivity() {
    var positionCuentaSelected = 0
    var nombreUsuarioSeleccionado = ""
    var nombreCuentaSeleccionado = ""
    lateinit var adapter: ArrayAdapter<Cuenta>

    val callbackContenido =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode === RESULT_OK) {
                if (result.data != null) {
                    adapter.notifyDataSetChanged()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_cuentas)

        nombreUsuarioSeleccionado = intent.getStringExtra("nombreUsuarioSeleccionado")!!

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            mutableListOf() // Inicializar con una lista vacía
        )

        val listView = findViewById<ListView>(R.id.lvCuentas)
        listView.adapter = adapter

        // Recoge todas la cuentas de la base de datos
        fireStoreCuenta.consultarCuentas {
            adapter.clear() // Limpiar el adaptador antes de añadir nuevas cuentas
            adapter.addAll(it) // Agregar todas las cuentas al adaptador
        }

        val btnCrearCuenta = findViewById<TextView>(R.id.btnCrearCuenta)
        btnCrearCuenta.setOnClickListener {
            val intent = Intent(this, FormCuenta::class.java)
            intent.putExtra("positionCuentaSelected", -1)
            intent.putExtra("nombreUsuarioSeleccionado", nombreUsuarioSeleccionado)
            callbackContenido.launch(intent)
        }

        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_cuenta, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        positionCuentaSelected = info.position
        val cuenta = adapter.getItem(positionCuentaSelected)
        nombreCuentaSeleccionado = cuenta?.nombreCuenta ?: ""
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuEditarCuenta -> {
                val intent = Intent(this, FormCuenta::class.java)
                intent.putExtra("positionCuentaSelected", positionCuentaSelected)
                intent.putExtra("positionUsuarioSelected", nombreUsuarioSeleccionado)
                callbackContenido.launch(intent)
                true
            }

            R.id.menuEliminarCuenta -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("¿Desea eliminar esta cuenta?")
                builder.setPositiveButton("Si") { dialog, which ->
                    fireStoreCuenta.eliminarCuenta(nombreCuentaSeleccionado)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(
                        this,
                        "Cuenta eliminada",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                builder.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
                }
                builder.create().show()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }
}
