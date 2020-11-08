package net.prettyrandom.thrift_kotlin.domain

data class Struct(
    val name: String,
    val fields: List<StructField>
)