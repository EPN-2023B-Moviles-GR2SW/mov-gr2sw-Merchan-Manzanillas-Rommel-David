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
