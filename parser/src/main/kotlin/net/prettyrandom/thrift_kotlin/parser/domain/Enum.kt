package net.prettyrandom.thrift_kotlin.parser.domain

data class Enum(
    val name: String,
    val values: List<EnumValue>
)
