package Modelo.Dao.Implements

import Modelo.Dao.Interfaces.ICuenta
import Modelo.Dao.Interfaces.IGenericDAO
import Modelo.Entity.Cuenta
import Modelo.Entity.Usuario
import java.lang.Exception


class CuentaDAO: GenericDAO<Cuenta, Int>(Cuenta::class.java), ICuenta{

    override fun delete(id: Int){
        val allItems = getAll().toMutableList()
        allItems.remove(allItems.find {it.id == id})
        saveAll(allItems)
    }
    fun getCuentasByUsuario(usuarioId: Int): List<Cuenta> {
        val allCuentas = getAll()
        return allCuentas.filter { it.usuarioId == usuarioId }
    }

}