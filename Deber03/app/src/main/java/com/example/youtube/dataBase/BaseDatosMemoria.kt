package com.example.youtube.dataBase


import com.example.youtube.model.PerfilMatch
import com.example.youtube.model.PerfilMostrar
import com.example.youtube.R

class BaseDatosMemoria {
    companion object {
        val arregloPerfil = arrayListOf<PerfilMostrar>()
        val arregloPerfilMatch = arrayListOf<PerfilMatch>()
        init {
            arregloPerfil.add(PerfilMostrar(R.drawable.h_tinder_p2))
            arregloPerfil.add(PerfilMostrar(R.drawable.h_tinder_p3))
            arregloPerfil.add(PerfilMostrar(R.drawable.h_tinder_p4))
            arregloPerfil.add(PerfilMostrar(R.drawable.h_tinder_p5))
            arregloPerfil.add(PerfilMostrar(R.drawable.h_tinder_p6))

            arregloPerfilMatch.add((PerfilMatch("Maria","20","2 millas",R.drawable.persona_m1)))
            arregloPerfilMatch.add((PerfilMatch("Andrea","22","1 milla",R.drawable.persona_m2)))
            arregloPerfilMatch.add((PerfilMatch("Josefa","21","Menos de una milla",R.drawable.persona_m3)))
            arregloPerfilMatch.add((PerfilMatch("Verónica","19","1 milla",R.drawable.persona_m4)))
            arregloPerfilMatch.add((PerfilMatch("Nicol","20","3 millas",R.drawable.persona_m5)))
            arregloPerfilMatch.add((PerfilMatch("Sofía","24","2 millas",R.drawable.persona_m6)))
        }
    }

}