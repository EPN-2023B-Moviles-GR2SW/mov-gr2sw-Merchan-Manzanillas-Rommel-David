package com.example.youtube.model

class PerfilMatch (
    var nombre: String,
    var edad: String,
    var cercania: String,
    var imagen: Int
) {
    override fun toString(): String {
        return "PerfilMatch(nombre='$nombre', edad='$edad', cercania='$cercania', imagen=$imagen)"
    }
}