package net.prettyrandom.thrift_kotlin.parser.domain

data class Service(
    val name: String,
    val functions: List<Function>
)