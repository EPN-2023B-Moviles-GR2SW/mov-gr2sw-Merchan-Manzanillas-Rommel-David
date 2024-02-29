package com.example.youtube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtube.model.PerfilMostrar
import java.util.ArrayList

class RecyclerViewAdaptadorPerfilPersona(
    private val contexto: MainActivity,
    private val lista: ArrayList<PerfilMostrar>,
    private val recyclearView: RecyclerView
): RecyclerView.Adapter <RecyclerViewAdaptadorPerfilPersona.MyViewHolder>(){
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView: ImageView

        init {
            imageView = view.findViewById(R.id.imageView)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista_perfiles_persona,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val PerfilActual = this.lista[position]
        holder.imageView.setImageResource(PerfilActual.imagen)
    }
}