package com.example.examenib.controller

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.example.examenib.R
import com.example.examenib.model.data.BaseDatos
import com.example.examenib.model.entities.Usuario

class ListaUsuarios : ComponentActivity() {

    val data = BaseDatos.datos
    var positionUsuarioSelected = -1
    lateinit var adapter: ArrayAdapter<Usuario>

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
        setContentView(R.layout.activity_lista_usuarios)

        val listView = findViewById<ListView>(R.id.lvUsuarios)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            data
        )
        listView.adapter = adapter
        adapter.notifyDataSetChanged()

        val btnCrearUsuario = findViewById<Button>(R.id.btnCrearUsuario)
        btnCrearUsuario.setOnClickListener {
            val intent = Intent(this, FormUsuario::class.java)
            intent.putExtra("positionUsuarioSelected", -1)
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
        inflater.inflate(R.menu.menu_usuario, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        positionUsuarioSelected = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemEditarUsuario -> {
                val intent = Intent(this, FormUsuario::class.java)
                intent.putExtra("positionUsuarioSelected", positionUsuarioSelected)
                callbackContenido.launch(intent)
                true
            }

            R.id.itemEliminarUsuario -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("¿Desear eliminar el usuario?")
                builder.setPositiveButton("Sí") { dialog, which ->
                    data.removeAt(positionUsuarioSelected)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(
                        applicationContext,
                        "Usuario eliminado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                builder.setNegativeButton("No") { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        "Operación cancelada",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                builder.create().show()
                true
            }

            R.id.itemCuentas -> {
                val intent = Intent(this, ListaCuentas::class.java)
                intent.putExtra("positionUsuarioSelected", positionUsuarioSelected)
                startActivity(intent)
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }
}