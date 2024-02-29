package com.example.youtube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.dataBase.BaseDatosMemoria

class RecyclerViewMatches : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_match)
        inicializarRecyclerView()

        val imageViewShorts = findViewById<ImageView>(R.id.idTinderPrincipal3)
        imageViewShorts.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }

    fun inicializarRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_short)
        val adaptador = RecyclerViewAdaptadorMatches(
            this,
            BaseDatosMemoria.arregloPerfilMatch,
            recyclerView
        )

        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget
            .DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget
            .LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}