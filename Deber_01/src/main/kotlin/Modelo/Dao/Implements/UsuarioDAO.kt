package Modelo.Dao.Implements

import Modelo.Dao.Interfaces.IUsuario
import Modelo.Entity.Cuenta
import Modelo.Entity.Usuario
import java.lang.Exception

class UsuarioDAO: GenericDAO<Usuario, Int>(Usuario::class.java), IUsuario {
    override fun delete(id: Int){
        val allItems = getAll().toMutableList()
        allItems.remove(allItems.find {it.id == id})
        saveAll(allItems)
    }


}