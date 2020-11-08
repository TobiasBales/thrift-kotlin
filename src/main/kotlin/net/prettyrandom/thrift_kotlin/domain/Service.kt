package net.prettyrandom.thrift_kotlin.domain

data class Service(
    val name: String,
    val functions: List<Function>
)