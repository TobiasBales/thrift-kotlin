package net.prettyrandom.thrift_kotlin.parser.extensions

import net.prettyrandom.thrift_kotlin.parser.domain.Requiredness
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftParser

fun ThriftParser.Enum_fieldContext.getValue(): Int? {
    val integer = this.integer() ?: return null

    if (integer.INTEGER() != null) {
        return integer.INTEGER().text.toInt()
    }

    if (integer.HEX_INTEGER() != null) {
        return integer.HEX_INTEGER().text.replace("0x", "").toLong(radix = 16).toInt()
    }

    throw Exception("Got invalid state while extracting value from enum field")
}

fun ThriftParser.FieldContext.getRequiredness(): Requiredness {
    return when (field_req()?.text) {
        null -> return Requiredness.DEFAULT
        "required" -> Requiredness.REQUIRED
        "optional" -> Requiredness.OPTIONAL
        else -> throw Exception("Got invalid value for requiredness ${field_req().text}")
    }
}
