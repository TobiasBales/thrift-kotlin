package net.prettyrandom.thrift_kotlin.parser.visitors

import net.prettyrandom.thrift_kotlin.parser.domain.Struct
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftParser

class StructVisitor : ThriftBaseVisitor<Struct>() {
    private val fieldVisitor = FieldVisitor()

    override fun visitStruct(ctx: ThriftParser.StructContext): Struct {
        return Struct(
            name = ctx.IDENTIFIER().text,
            fields = ctx.field().map { it.accept(fieldVisitor) }
        )
    }
}