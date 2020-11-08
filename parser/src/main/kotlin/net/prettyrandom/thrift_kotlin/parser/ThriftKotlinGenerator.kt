package net.prettyrandom.thrift_kotlin.parser

class ThriftKotlinGenerator {
    fun run(filename: String) {
        println(Parser().parse(filename))
    }
}

fun main(args: Array<String>) {
    ThriftKotlinGenerator().run("src/main/thrift/simple.thrift")
}