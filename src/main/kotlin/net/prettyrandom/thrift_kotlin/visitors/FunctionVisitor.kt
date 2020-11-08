package net.prettyrandom.thrift_kotlin.visitors

import net.prettyrandom.thrift_kotlin.domain.Function
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftParser

class FunctionVisitor : ThriftBaseVisitor<Function>() {
    private val fieldVisitor = FieldVisitor()

    override fun visitFunction(ctx: ThriftParser.FunctionContext): Function {
        return Function(
            name = ctx.IDENTIFIER().text,
            returnType = ctx.function_type().text,
            parameters = ctx.field().mapNotNull { it.accept(fieldVisitor) }
        )
    }

}
