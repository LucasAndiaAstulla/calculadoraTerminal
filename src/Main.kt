import java.lang.reflect.Array

fun main() {

    println("Bem vindo!!!")

    inicio@ do{
        calculadora()

        println("Você deseja continuar?? (SIM/NAO)")
        var continuar = readLine().orEmpty().uppercase()

        //Tratamento se o valor for != SIM ou NAO

        while (continuar.isBlank() || !continuar.equals("NÃO", ignoreCase = true) && !continuar.equals("SIM", ignoreCase = true)) {
            println("Por favor insira somente SIM ou NÃO!!!")
            continuar = readLine().orEmpty().uppercase()

        }

    } while (continuar == "SIM")

}

fun calculadora() {


    println("Escolha a quantidade de variáveis a se efetuar:")
    var quantVar = readLine()?.toDoubleOrNull() ?: 0.0
    val valores = mutableListOf<Double>()

    while(quantVar <= 0) {
        println("Foi confirmado uma variável errada, tente de novo!!")
        quantVar = readLine()?.toDoubleOrNull() ?: 0.0
    }

    for (i in 1..quantVar.toInt()) {

        var valor: Double?

        do {
            println("Digite o valor $i: ")
            valor = readLine()?.toDoubleOrNull()


            if (valor == null) {
                println("Entrada inválida! Digite apenas números inteiros.")
            }


        } while(valor == null) //valor incorreto, faz permanecer no loop

        //adiciona no array
        valores.add(valor)
    }

    println("Valores armazenados: $valores")


    val permitidos = listOf("-", "+", "/", "*")

    println("Qual operação deseja fazer:")

    var operador: String

    do {
        println(
            "Escolha um operador válido(+, -, *, /): \n" +
                    "Use as seguintes váriaveis\n" +
                    "(+) - soma\n" +
                    "(-) - subtração\n" +
                    "(/) - divisão\n" +
                    "(*) - multiplicação\n"
        )
        operador = readLine().orEmpty()


    } while (operador !in permitidos)

    println("Muito obrigado, prosseguiremos...")

    //falta resolver
    println(calcularResultado(operador, valores))
}

fun calcularResultado(operador: String?, valores: List<Double>): String {

    return when(operador) {
        "+" ->  "O resultado é ${valores.sum()}"
        "-" ->  "O resultado é ${valores.reduce{acc, num -> acc - num}}"
        "*" ->  "O resultado é ${valores.reduce{acc, num -> acc * num}}"
        "/" -> {
            //valores.qualquerum { é = 0 ou primeiro = 0}
            if(valores.any{ it == 0.0} || valores.first() == 0.0) return "Erro de divisão por zero!"
            "O resultado é ${valores.reduce { acc, num -> acc / num }}"
        }
        else -> "Operador invalido"
    }

}