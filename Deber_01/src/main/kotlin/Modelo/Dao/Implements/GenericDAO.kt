package Modelo.Dao.Implements

import Modelo.Dao.Interfaces.IGenericDAO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.Exception



open class GenericDAO<T, ID>(private val persistentClass: Class<T>) : IGenericDAO<T, ID> {

    val gson = Gson()
    private val filename = "src/main/resources/${persistentClass.simpleName}.json"


    private fun verificarArchivo() {
        val file = File(filename)
        if (!file.exists()) {
            file.createNewFile()
        }
    }


    override fun getAll(): List<T> {
        verificarArchivo()
        val fileContent = File(filename).readText()
        val listType = object : TypeToken<List<Map<String, Any>>>() {}.type
        val dataList: List<Map<String, Any>> = gson.fromJson(fileContent, listType) ?: emptyList()
        return dataList.map { gson.fromJson(gson.toJsonTree(it), persistentClass) as T }

    }

    fun saveAll(lista: List<T>) {
        val json = gson.toJson(lista)
        File(filename).writeText(json)
    }


    override fun create(t: T) {
        verificarArchivo()
        val allItems = getAll().toMutableList()
        if (esValido(getObjectoID(t), allItems)) {
            allItems.add(t)
            saveAll(allItems)
        } else {
            println("El ID ya existe o ingresaste datos INVALIDOS. No se puede crear.")
        }
    }

    private fun esValido(id: ID, allItems: List<T>): Boolean {
        val idExiste = allItems.any { getObjectoID(it) == id || id == 0}
        return !idExiste
    }


    override fun update(t: T) {
        val allItems = getAll().toMutableList()
        val objetoExistente = obtenerObjetoPorId(allItems, getObjectoID(t))
        if (objetoExistente != null) {
            val index = allItems.indexOf(objetoExistente)
            allItems[index] = t
            saveAll(allItems)
        } else {
            println("El objeto con el ID ${getObjectoID(t)} no existe. No se puede actualizar.")
        }
    }


    private fun obtenerObjetoPorId(allItems: List<T>, id: ID): T? {
        return allItems.find { getObjectoID(it) == id }
    }


    fun getObjectoID(objeto: T): ID {
        val idField = persistentClass.declaredFields.find { it.name == "id" }
        idField?.let {
            it.isAccessible = true
            return it.get(objeto) as ID
        }
        throw Exception("No se encontrol el ID del Objeto")
    }

    override fun delete(id: ID) {
    }

}