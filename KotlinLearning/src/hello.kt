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

