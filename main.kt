fun add (a: Double, b:Double): Double = a+b
fun sub (a: Double, b:Double): Double = a-b
fun div (a: Double, b:Double): Double = a/b
fun mul (a: Double, b:Double): Double = a*b

fun isNumber(s:String):Boolean{
    if (s.isEmpty()) return false
    for (symbol in s){
        if (!symbol.isDigit()){
            return false
        }
    }
    return true
}

val opers = arrayOf("+", "-", "*", "/")
var maxDeapth = 0
var lastIndex = 0

fun operation(oprd: String, index: Int, parts: List<String>, deapth: Int): Double{
    var op1 = 0.0
    var op2 = 0.0
    if (deapth>maxDeapth)
        maxDeapth = deapth

    if (parts[index+1] in opers)
        op1 = operation(parts[index+1], index+1, parts, deapth+1)
    else if(parts[index+1].toDouble()>=0)
        op1 = parts[index+1].toDouble()
    else {
        println("Wrong expression")
        return Double.NaN
    }

    if (parts[index+2 + (maxDeapth-deapth)*2] in opers)
        op2 = operation(parts[index+2+ (maxDeapth-deapth)*2], index+2+ (maxDeapth-deapth)*2, parts, deapth+1)
    else if(parts[index+2+ (maxDeapth-deapth)*2].toDouble() >= 0)
        op2 = parts[index+2+ (maxDeapth-deapth)*2].toDouble()
    else {
        println("Wrong expression")
        return Double.NaN
    }

    if (lastIndex <= (index+2 + (maxDeapth-deapth)*2))
        lastIndex = index+3 + (maxDeapth-deapth)*2
    when (oprd) {
        "+" -> return add(op1, op2)
        "-" -> return sub(op1, op2)
        "*" -> return mul(op1, op2)
        "/" -> return div(op1, op2)
    }
    println("Wrong expression")
    return Double.NaN
}


fun main(args: Array<String>) {

    println("Input expression")
    val answer: String? = readLine()
    var parts = answer?.split(' ')
    var stack1 = mutableListOf<String>()
    val stack2 = mutableListOf<String>()
    try {
        if (parts != null) {
            var result = operation(parts[0], lastIndex, parts, 0)
            if (lastIndex < parts.size)
                println("Wrong expression")
            else
                println(result)
        }
    }
    catch (e: Exception){
        println("Wrong expression")
    }
    return
}
