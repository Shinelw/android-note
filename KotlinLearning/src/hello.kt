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


//继承
open class Base(p : Int){
    open fun a(){}
    fun b(){}
}


class Derived(p: Int) : Base(p){
    override fun a() {
        super.a()
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



