/**
 * Created by peixuan on 2017/2/28.
 */

//空安全
fun main(args: Array<String>) {
    var a: String = "abc"
//    a = null
    var b: String? = "abc"
    b = null
    println(b)
    val l = a.length;
    if (b != null && b.length > 0) {
        val s = b.length
    }
    b?.length

    val listWithNulls: List<String?> = listOf("A", null)
    for (item in listWithNulls){
        item?.let { println(it) }
    }

}