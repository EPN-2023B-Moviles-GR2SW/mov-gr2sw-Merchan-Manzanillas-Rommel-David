import Modelo.Dao.Implements.CuentaDAO
import Modelo.Dao.Implements.UsuarioDAO
import Modelo.Entity.Cuenta
import Modelo.Entity.Usuario
import java.util.*
import java.text.SimpleDateFormat

fun main(args: Array<String>) {
    val usuarioDAO = UsuarioDAO()
    val cuentaDAO = CuentaDAO()

    var opcion: Int

    do {
        println("Menú del BANCO:")
        println("1. Crear Usuario")
        println("2. Eliminar Usuario")
        println("3. Actualizar Usuario")
        println("4. Mostrar Todos los Usuarios")
        println("5. Crear Cuenta")
        println("6. Eliminar Cuenta")
        println("7. Actualizar Cuenta")
        println("8. Mostrar Todas las Cuentas por ID de un Usuario")
        println("9. Mostrar Todas las Cuentas")
        println("10. Salir")

        print("Ingrese la opción deseada: ")
        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> {
                // Crear Usuario
                val nuevoUsuario = crearUsuarioDesdeConsola()
                usuarioDAO.create(nuevoUsuario)
            }
            2 -> {
                // Eliminar Usuario
                print("Ingrese el ID del Usuario a eliminar: ")
                val idUsuarioEliminar = readLine()?.toIntOrNull() ?: 0
                usuarioDAO.delete(idUsuarioEliminar)
            }
            3 -> {
                // Actualizar Usuario
                val usuarioActualizado = crearUsuarioDesdeConsola()
                usuarioDAO.update(usuarioActualizado)
            }
            4 -> {
                // Mostrar Todos los Usuarios
                val usuarios = usuarioDAO.getAll()
                usuarios.forEach { println(it) }
            }
            5 -> {
                // Crear Cuenta
                val nuevaCuenta = crearCuentaDesdeConsola()
                cuentaDAO.create(nuevaCuenta)
            }
            6 -> {
                // Eliminar Cuenta
                print("Ingrese el ID de la Cuenta a eliminar: ")
                val idCuentaEliminar = readLine()?.toIntOrNull() ?: 0
                cuentaDAO.delete(idCuentaEliminar)
            }
            7 -> {
                // Actualizar Cuenta
                val cuentaActualizada = crearCuentaDesdeConsola()
                cuentaDAO.update(cuentaActualizada)
            }
            8 -> {
                // Mostrar Todas las Cuentas del Usario Selecionado
                print("Ingrese el ID del Usuario para ver sus Cuentas: ")
                val usuarioID = readLine()?.toIntOrNull() ?:0
                val cuentasDelUsuario = cuentaDAO.getCuentasByUsuario(usuarioID)
                cuentasDelUsuario.forEach { println(it) }
            }
            9 -> {
                // Mostrar Todas las Cuentas
                val cuentas = cuentaDAO.getAll()
                cuentas.forEach { println(it) }
            }
            10 -> {
                println("Saliendo del programa...")
            }
            else -> {
                println("Opción no válida. Por favor, ingrese una opción del 1 al 10.")
            }
        }

    } while (opcion != 10)
}

fun crearUsuarioDesdeConsola(): Usuario {
    print("Ingrese el nombre del usuario: ")
    val nombre = readLine() ?: ""

    try {
        print("Ingrese la fecha de nacimiento (yyyy-MM-dd): ")
        val fechaNacimientoStr = readLine()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val fechaNacimiento = if (fechaNacimientoStr != null && fechaNacimientoStr.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {
            dateFormat.parse(fechaNacimientoStr)
        } else {
            throw IllegalArgumentException("Formato de fecha incorrecto. Debe ser yyyy-MM-dd.")
        }

        print("Ingrese el ID del usuario: ")
        val id = readLine()?.toIntOrNull()
        if (id == null || id <= 0) {
            throw IllegalArgumentException("ID de usuario no válido. Debe ser un número entero positivo.")
        }

        print("Ingrese el sueldo del usuario: ")
        val sueldo = readLine()?.toDoubleOrNull()
        if (sueldo == null || sueldo < 0) {
            throw IllegalArgumentException("Sueldo no válido. Debe ser un número decimal no negativo.")
        }

        print("¿El usuario está vetado? (true/false): ")
        val usuarioVetadoStr = readLine()
        val usuarioVetado = when (usuarioVetadoStr?.toLowerCase()) {
            "true" -> true
            "false" -> false
            else -> throw IllegalArgumentException("Valor no válido para usuario vetado. Debe ser true o false.")
        }

        return Usuario(nombre, fechaNacimiento, id, sueldo, usuarioVetado)
    } catch (e: Exception) {
        println("Error: ${e.message}. USARIO INVALIDO.")
        return Usuario(nombre, Date(), 0, 0.0, false)
    }
}


fun crearCuentaDesdeConsola(): Cuenta {
    try {
        print("Ingrese el nombre de la cuenta: ")
        val nombre = readLine() ?: ""

        print("Ingrese el ID de la cuenta: ")
        val id = readLine()?.toIntOrNull()
        if (id == null || id <= 0) {
            throw IllegalArgumentException("ID de cuenta no válido. Debe ser un número entero positivo.")
        }

        print("Ingrese el ID del usuario asociado: ")
        val usuarioId = readLine()?.toIntOrNull()
        if (usuarioId == null || usuarioId <= 0) {
            throw IllegalArgumentException("ID de usuario asociado no válido. Debe ser un número entero positivo.")
        }

        print("Ingrese la fecha de creación (yyyy-MM-dd): ")
        val fechaCreacionStr = readLine()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val fechaCreacion = if (fechaCreacionStr != null && fechaCreacionStr.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {
            dateFormat.parse(fechaCreacionStr)
        } else {
            throw IllegalArgumentException("Formato de fecha incorrecto. Debe ser yyyy-MM-dd.")
        }

        print("Ingrese la cantidad de la cuenta: ")
        val cantidad = readLine()?.toDoubleOrNull()
        if (cantidad == null || cantidad < 0) {
            throw IllegalArgumentException("Cantidad no válida. Debe ser un número decimal no negativo.")
        }

        print("¿La cuenta está caducada? (true/false): ")
        val esCaducadaStr = readLine()
        val esCaducada = when (esCaducadaStr?.toLowerCase()) {
            "true" -> true
            "false" -> false
            else -> throw IllegalArgumentException("Valor no válido para estado de cuenta caducada. Debe ser true o false.")
        }

        return Cuenta(nombre, id, usuarioId, cantidad, esCaducada, fechaCreacion)
    } catch (e: Exception) {
        println("Error: ${e.message}. CUENTA INVALIDA.")
        return Cuenta("", 0, 0, 0.0, false, Date())
    }
}
