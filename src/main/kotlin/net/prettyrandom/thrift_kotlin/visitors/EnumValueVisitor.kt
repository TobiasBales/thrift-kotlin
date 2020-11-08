package net.prettyrandom.thrift_kotlin.visitors

import net.prettyrandom.thrift_kotlin.domain.EnumValue
import net.prettyrandom.thrift_kotlin.extensions.getValue
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftParser

class EnumValueVisitor(private val nextValue: Int) : ThriftBaseVisitor<EnumValue>() {
    override fun visitEnum_field(ctx: ThriftParser.Enum_fieldContext): EnumValue {
        return EnumValue(
            name = ctx.IDENTIFIER().text,
            value = ctx.getValue() ?: nextValue
        )
    }
}