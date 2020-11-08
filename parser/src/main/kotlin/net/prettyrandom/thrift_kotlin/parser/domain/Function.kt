package net.prettyrandom.thrift_kotlin.parser.domain

data class Function(
    val name: String,
    val returnType: String,
    val parameters: List<Field>
)