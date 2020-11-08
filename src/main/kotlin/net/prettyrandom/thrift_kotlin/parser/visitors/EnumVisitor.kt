package net.prettyrandom.thrift_kotlin.parser.visitors

import net.prettyrandom.thrift_kotlin.parser.domain.Enum
import net.prettyrandom.thrift_kotlin.parser.domain.EnumValue
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftParser
import net.prettyrandom.thrift_kotlin.visitors.EnumValueVisitor

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