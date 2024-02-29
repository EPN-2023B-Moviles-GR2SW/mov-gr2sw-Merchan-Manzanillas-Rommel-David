package com.example.youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.model.PerfilMatch
import java.util.ArrayList

class RecyclerViewAdaptadorMatches(
    private val contexto: RecyclerViewMatches,
    private val lista: ArrayList<PerfilMatch>,
    private val recyclearView: RecyclerView
): RecyclerView.Adapter <RecyclerViewAdaptadorMatches.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val shortImageView: ImageView
        val nombreTextView: TextView
        val edadTextView: TextView
        val cercaniaTextView: TextView
        init {
            shortImageView = view.findViewById(R.id.imageMatch)
            nombreTextView = view.findViewById(R.id.nombreM)
            edadTextView = view.findViewById(R.id.edadM)
            cercaniaTextView = view.findViewById(R.id.cercaniaM)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_match,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val personaMatch = this.lista[position]
        holder.shortImageView.setImageResource(personaMatch.imagen)
        holder.nombreTextView.text = "Nombre: "+personaMatch.nombre
        holder.edadTextView.text = "Edad: "+personaMatch.edad
        holder.cercaniaTextView.text = "Área: "+personaMatch.cercania
        //holder.fechaTextView.text = videoActual.fecha
        "Nombre:${personaMatch.imagen}"
    }
}