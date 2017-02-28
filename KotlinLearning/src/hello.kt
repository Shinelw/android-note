import java.util.*
import java.util.concurrent.locks.Lock

/**
 * Created by peixuan on 2017/2/27.
 */
fun main(args: Array<String>) {
    println("hello kotlin")
    println(add(1,2))
    println(add(2,3))
    if (args.size == 0){
        println("args size: ${args.size}")
    }else {
        println("This is : ${args[0]}")
    }
    cases("hello")
    cases(3)
    cases("fjsldfl")
    println(text)
    println(max)
    val  bbb = BaseImpl(10)
    Derived(bbb).print()

    println(numbers)
    numbers.add(4)
    println(numbers)
    println(readOnlyView)

    val strings = hashSetOf("a", "b", "c", "c")
    assert(strings.size == 3)

    for  (i in 1..10){
        println(i)
    }

    for (i in 4 downTo 1){
        println(i)
    }
}


//定义函数
fun add(a: Int, b: Int): Int{
    return a+b
}

//定义局部变量
val a: Int = 1
val s = 2

fun cases(obj: Any){
    when(obj){
        1           -> println("one")
        2           -> println("two")
        "hello"     -> println("hello")
        is String   -> println("String")
        !is String  -> println("not String")
        else        -> println("unknown")
    }
}


//显示转换


val text = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()


val b = 2
//val max = if (a > b) a else b

val max = if(a > b){
    println("choose a")
    a
}else{
    println("choose b")
    b
}

interface MyInterface{
    val prop: Int
    val properyWithImplementation: String
        get() = "foo"

    fun foo(){
        print(prop)
    }
}


class Child : MyInterface{
    override val prop: Int = 29
}


class Box<T>(t : T){
    var value = t

}


class Outer {
    private var bar : Int = 1
    inner class Inner {
        fun foo() = bar
    }
}

var demo : Int = Outer().Inner().foo()

enum class Direction {
    NORTH, WEST, SOUTH, EAST
}



interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
    }
}

class Derived(b: Base) : Base by b


//函数
fun double(x: Int): Int {
    return x
}

val result = double(2)

//默认参数
fun read(b: Array<Byte>, off: Int = 0, len: Int = b.size) {

}

//返回Unit的函数
fun printHello(name: String?): Unit {
    if(name != null){
        println("hello %{name}")
    }else{
        println("Hi there!")
    }
}


//可变数量的参数（Varargs）
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>()
    for(t in ts){
        result.add(t)
    }
    return result
}

val list = arrayOf(1, 2, 3)

val list2 = asList(-1, 0, *list, 4)


//高阶函数
fun <T> lock(lock: Lock, body: () -> T): T {
    lock.lock()
    try {
        return body()
    }finally {
        lock.unlock()
    }
}

fun <T, R> List<T>.map(transform: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (item in this){
        result.add(transform(item))
    }
    return result
}

//Lambda
val ints = arrayListOf(1,2,3,4)
val doubled = ints.map{it -> it * 2}


val sum = {x: Int, y: Int -> x + y}


//解构声明
//data class Result(val result: Int, val status: Status)

//集合
val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
val readOnlyView: List<Int> = numbers



fun demo(x: Any){
    if (x is String){
        println(x.length) // 自动转换成字符串
    }

    if (x !is String) return

    println(x.length)
}








