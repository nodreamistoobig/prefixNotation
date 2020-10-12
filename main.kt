import java.util.ArrayList

fun add (a: Double, b:Double): Double = a+b
fun sub (a: Double, b:Double): Double = a-b
fun div (a: Double, b:Double): Double = a/b
fun mul (a: Double, b:Double): Double = a*b

val opers = arrayOf("+", "-", "*", "/")
var maxDeapth = 0
var lastIndex = 0
var expr = ArrayList<String>();

fun operation(oprd: String, index: Int, parts: List<String>, deapth: Int): Double{
    var op1: Double
    var op2: Double
    if (deapth>maxDeapth)
        maxDeapth = deapth

    if (parts[index+1] in opers){
        expr.add("(");
        op1 = operation(parts[index+1], index+1, parts, deapth+1)
        expr.add(")");
    }
    else if(parts[index+1].toDouble()>=0){
        op1 = parts[index+1].toDouble()
        expr.add(op1.toString());
    }
    else {
        println("Wrong expression")
        return Double.NaN
    }

    if (parts[index+2 + (maxDeapth-deapth)*2] in opers){
        expr.add(oprd);
        expr.add("(");
        op2 = operation(parts[index+2+ (maxDeapth-deapth)*2], index+2+ (maxDeapth-deapth)*2, parts, deapth+1)
        expr.add(")");
    }
    else if(parts[index+2+ (maxDeapth-deapth)*2].toDouble() >= 0){
        op2 = parts[index+2+ (maxDeapth-deapth)*2].toDouble()
        expr.add(oprd);
        expr.add(op2.toString());
    }
    else {
        println("Wrong expression")
        return Double.NaN
    }

    if (lastIndex <= (index+2 + (maxDeapth-deapth)*2))
        lastIndex = index+3 + (maxDeapth-deapth)*2
    when (oprd) {
        "+" ->  return add(op1, op2)
        "-" ->  return sub(op1, op2)
        "*" ->  return mul(op1, op2)
        "/" ->  return div(op1, op2)
    }
    println("Wrong expression")
    return Double.NaN
}


fun main() {

    println("Input expression")
    val answer: String? = readLine()
    var parts = answer?.split(' ')
    try {
        if (parts != null) {
            var result = operation(parts[0], lastIndex, parts, 0)
            if (lastIndex < parts.size)
                println("Wrong expression")
            else{
                for (part in expr){
                    print("$part ")
                }
                println("= $result")
            }
        }
    }
    catch (e: Exception){
        println("Wrong expression")
    }
    return
}
