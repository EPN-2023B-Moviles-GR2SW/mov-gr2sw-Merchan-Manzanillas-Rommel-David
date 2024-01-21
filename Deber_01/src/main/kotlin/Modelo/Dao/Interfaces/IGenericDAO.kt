package Modelo.Dao.Interfaces

interface IGenericDAO<T, ID> {
    fun create(t: T)
    fun update(t: T)
    fun getAll(): List<T>
    fun delete(id: ID)
}