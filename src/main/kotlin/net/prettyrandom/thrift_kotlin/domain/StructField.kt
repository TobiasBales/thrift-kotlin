package net.prettyrandom.thrift_kotlin.domain

data class StructField(
    val identifier: Int,
    val requiredness: Requiredness,
    val name: String,
    val type: String,
    val defaultValue: String?,
)