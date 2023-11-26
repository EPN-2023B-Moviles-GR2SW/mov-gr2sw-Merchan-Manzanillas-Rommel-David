import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")
    //INUMTABLES (No se reasignan "=")
    val inmutable: String = "Adrian"
    //inmutable = "Vicente" ---> No es correcto

    //MUTABLES (Se reasignan "=")
    val mutable: String = "Gerardo"
    //mutable = "Pepe" ---> Es correcto




    // val > var
    // Duck Typing
    var ejemploVariable = "Adrian Eguez"
    val ejemploEdad: Int = 12
    ejemploVariable.trim()
    // ejemploVariable = ejemploEdad

    //Varaibles Primitivas
    val nombreProfesor: String = "Adrian"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    //Clases Java
    val fechaNacimiento: Date = Date()


    //SWITCH
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        "S" -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"


    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.00)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    //Instacia de clases
    val sumaUno = SUMA(1,1)
    val sumaDos = SUMA(null,1)
    val sumaTres = SUMA(1,null)
    val sumaCuatro = SUMA(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(SUMA.pi)
    println(SUMA.elevearAlCuadrado(2))
    println(SUMA.historialSumas)


    //Arreglo Dinamico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Arreglo Dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)


    //FOR EACH ----> UNIT
    //Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach { valoarActual: Int ->
            println("Valor actual: ${valoarActual}")
        }
    //it (en ingles eso) significa ele elmento iterado
    arregloDinamico.forEach { println("valor actual: ${it}") }

    arregloEstatico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor: ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)



//MAP ---> Muta el arreglo (Cambia el arreglo)
// 1) Enciemos el numevo valor de la iteracion
// 2) Nos devuele un NUEVO ARREGLO con los valores moificados

    val respuestaMap: List<Double> = arregloDinamico
        .map { valorActual:Int ->
            return@map valorActual.toDouble() + 100.00
        }
    println(respuestaMap)
    val respuestaMap2 = arregloDinamico.map { it + 15 }


//Filter -> FILTRAR EL ARREGLO
// 1) Devolver una expresion (True o False)
// 2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter { valorActual: Int ->
            // Expresion Condicion
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }

    println(respuestaFilter)
    println(respuestaFilterDos)


    //OR AND
    //OR -> ANY(Alguno cumple?)
    //AND -> ALL (Todos cumplen?)
    val resouestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(resouestaAny)


    val resouestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(resouestaAll)


    // REDUCE -> Valor acumulado
    // Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    // [1,2,3,4,5] -> Sumeme todos los valores del arreglo (se puede hacer cualquier operacion matematica)
    // valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteracion 1
    // valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteracion 2
    // valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteracion 3
    // valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteracion 4
    // valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteracion 5


    val respuestaReduce: Int = arregloDinamico
        .reduce{ // acumulado 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce(acumulado + valorActual)
        }
    println(respuestaReduce)
    //acumulado + (itemCarrito.cantidad * itemCarrito.precio) ejemplo si tuvieramos un carrito de compras


}




abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){ //Bloque de coidgo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }

}


    abstract class Numeros(//Construtor primario
        // Ejemplo
        //uno: Int,(Parametro(son modificado de acceso))
        //private var uno: Int, //Propoiedade pubilca Clase de numeros.uno
        //var uno: Int, //Propiedad de la clase (Por defecto es public)
        //public var uno: Int,
        protected val numeroUno: Int, //Propiedad de la clase protected numeros.numeroUno ///ESTO SON PROPIEDADES
        protected val numeroDos: Int, //Propiedad de la clase protected numeros.numeroDos  ///ESTO SON PROPIEDADES
    ){
        init {
            this.numeroUno; this.numeroDos //This es opcional
            numeroUno; numeroDos; //Es los mismo que arriba pero sin el this
            println("Inicializando")
        }
}




class SUMA( //Construvotr primario Suma
    unoParametro: Int,
    dosParametro: Int,
):Numeros(unoParametro, dosParametro){ //Extendiendo y mando los parametros (SUPER)
    init {
        this.numeroUno
        this.numeroDos
    }
    constructor( //Segundo constructor
        uno: Int?, //Parametros
        dos: Int, //Parametros
    ):this (
        if (uno == null) 0 else uno,
        dos
    )
    constructor(
        uno: Int, //Parametros
        dos: Int? //Parametros
    ):this (
            uno,
            if(dos == null) 0 else dos,
        )
    constructor(
        uno: Int?,
        dos: Int?
    ): this (
        if (uno == null) 0 else uno,
        if(dos == null) 0 else dos
    )


    public fun sumar(): Int{
        val total = numeroUno + numeroDos
        // Suma.agregarHistoriial(total)
        agregarHistorial(total)
        return total
    }

    companion object { //Atributos y metodos compartidos
        //entre las instancias
        val pi = 3.14
        fun elevearAlCuadrado(num: Int): Int{
            return num * num
        }
        val  historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }

}






//void -> Unit
fun imprimirNombre(nombre: String): Unit{
    print("Nombre: ${nombre}") //Imprime lo siguiente: Nombre: CualquierNombre
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (valor por defecto)
    bonoEspecial: Double? = null, //Opcion null ---> nullable
): Double{
    // Int => Int? (nullable)
    //String = String? (nullable)
    // Date -> Date? (nullable)
    if(bonoEspecial == null){
        return sueldo * (100/tasa)
    } else {
        return sueldo * (100/tasa) + bonoEspecial
    }
}
