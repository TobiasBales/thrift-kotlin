package net.prettyrandom.thrift_kotlin.listeners

import ThriftBaseListener
import ThriftParser
import net.prettyrandom.thrift_kotlin.domain.Enum
import net.prettyrandom.thrift_kotlin.domain.EnumValue

class EnumListener : ThriftBaseListener() {
    private val enums = mutableListOf<Enum>()

    fun getEnums(): List<Enum> {
        return enums
    }

    override fun exitEnum_rule(ctx: ThriftParser.Enum_ruleContext) {
        val values = ctx.enum_field().fold(Pair<Int, MutableList<EnumValue>>(0, mutableListOf())) { pair, enumField ->
            val name = enumField.IDENTIFIER().text
            val value = getValue(enumField) ?: pair.first

            pair.second.add(EnumValue(name = name, value = value))

            Pair(value + 1, pair.second)
        }.second

        enums.add(Enum(name = ctx.IDENTIFIER().text, values = values))
    }

    private fun getValue(ctx: ThriftParser.Enum_fieldContext): Int? {
        val integer = ctx.integer() ?: return null

        if (integer.INTEGER() != null) {
            return integer.INTEGER().text.toInt()
        }

        if (integer.HEX_INTEGER() != null) {
            return integer.HEX_INTEGER().text.replace("0x", "").toLong(radix = 16).toInt()
        }

        throw Exception("Got invalid state while extracting value from enum field")
    }
}
