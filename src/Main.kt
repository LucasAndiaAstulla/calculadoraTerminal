import java.lang.reflect.Array

fun main() {

    println("Bem vindo!!!")

    do{
        calculadora()

        println("Você deseja continuar?? (SIM/NAO)")
        val continuar = readLine().orEmpty().uppercase()

        //Erro string vazia forma looping
        while (!continuar.equals("NÃO", ignoreCase = true) && !continuar.equals("SIM", ignoreCase = true)) {
            println("Por favor insira somente SIM ou NÃO!!!")
        }

    } while (continuar == "SIM")

}

fun calculadora() {
    println("Escolha a quantidade de variáveis a se efetuar:")
    var quantVar = readLine()?.toIntOrNull() ?: 0
    val valores = mutableListOf<Int>()

    while(quantVar <= 0) {
        println("Foi confirmado uma variável errada, tente de novo!!")
        quantVar = readLine()?.toIntOrNull() ?: 0
    }

    for (i in 1..quantVar) {

        var valor: Int?

        do {
            println("Digite o valor $i: ")
            valor = readLine()?.toIntOrNull()
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

fun calcularResultado(operador: String?, valores: List<Int>): String {

    return when(operador) {
        "+" ->  "O resultado é ${valores.sum()}"
        "-" ->  "O resultado é ${valores.reduce{acc, num -> acc - num}}"
        "*" ->  "O resultado é ${valores.reduce{acc, num -> acc * num}}"
        "/" -> {
            //valores.qualquerum { é = 0 ou primeiro = 0}
            if(valores.any{ it == 0} || valores.first() == 0) return "Erro de divisão por zero!"
            "O resultado é ${valores.reduce { acc, num -> acc / num }}"
        }
        else -> "Operador invalido"
    }

}