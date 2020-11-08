package net.prettyrandom.thrift_kotlin.parser.domain

data class Field(
    val identifier: Int,
    val requiredness: Requiredness,
    val name: String,
    val type: String,
    val defaultValue: String?,
)