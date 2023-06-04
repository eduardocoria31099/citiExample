import Dummies.productos
import Dummies.productosFalse

private var listSize: Int = 0
private lateinit var successList: MutableList<Productos>
private lateinit var errorList: MutableList<Productos>

private lateinit var baseList: MutableList<Productos>

fun main() {
    init()
    getLista()
}

private fun init() {
    errorList = mutableListOf()
    successList = mutableListOf()
    baseList = mutableListOf()
    listSize = productos.size
}

private fun getLista() {
    println("LOADING ...")

    if (listSize > 5) {
        println("mayor 5")
        println(listSize)
        val listSubFirst = productos.subList(fromIndex = 0, toIndex = 5)
        val listSubLast = productos.subList(fromIndex = 5, toIndex = listSize)

        println(listSubFirst.size)
        println(listSubLast.size)
        /*println(listSubFirst)
        println(listSubLast)*/

        firstCallService(listSubFirst)
        firstCallService(listSubLast)
        selectList()
        validateList()
    } else {
        println("5 o menos")
        firstCallService(productos)
        selectList()
        validateList()
    }
}

private fun firstCallService(listProducts: List<Productos>) {
    println("Llamado al servicio 1")
    baseList.addAll(listProducts)
    println(baseList.size)

}

private fun secondCallService(listProducts: List<Productos>) {
    println("Llamado al servicio 2")
    listProducts.forEachIndexed { index, productos ->
        if (productos.responseFlag == true) {
            successList.add(productos)
            errorList.removeAt(index)
        }
    }
    println("success List ${successList.size}")
    println("error List ${errorList.size}")

    if (successList.size == listSize) {
        println("Operacion exitosa 2")
    } else {
        if (errorList.size == listSize) {
            println("Operacion erronea")
        } else {
            println("No se pudieron diferir algunas compras")
        }
    }
}

private fun validateList() {
    if (successList.size == listSize) {
        println("Operacion exitosa 1")
    } else {
        secondCallService(productosFalse)
    }
}

private fun selectList() {
    baseList.forEach {
        if (it.responseFlag == true) {
            successList.add(it)
        } else {
            errorList.add(it)
        }
    }

    println("success List ${successList.size}")
    println("error List ${errorList.size}")
}