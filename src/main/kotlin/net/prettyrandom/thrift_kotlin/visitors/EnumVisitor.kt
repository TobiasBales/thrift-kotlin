package net.prettyrandom.thrift_kotlin.visitors

import net.prettyrandom.thrift_kotlin.domain.Enum
import net.prettyrandom.thrift_kotlin.domain.EnumValue
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftParser

class EnumVisitor : ThriftBaseVisitor<Enum>() {
    override fun visitEnum_rule(ctx: ThriftParser.Enum_ruleContext): Enum {
        val values = ctx.enum_field().runningFold(EnumValue(name = "dummy", value = -1), { acc, enumFieldcontext ->
            enumFieldcontext.accept(EnumValueVisitor(acc.value + 1))
        }).drop(1)

        return Enum(
            name = ctx.IDENTIFIER().text,
            values = values
        )
    }
}