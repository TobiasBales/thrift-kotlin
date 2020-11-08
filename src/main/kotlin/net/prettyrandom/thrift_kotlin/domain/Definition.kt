package net.prettyrandom.thrift_kotlin.domain

data class Definition(
    val namespace: String?,
    val enums: List<Enum>,
    val structs: List<Struct>,
    val services: List<Service>
)