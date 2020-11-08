package net.prettyrandom.thrift_kotlin.visitors

import net.prettyrandom.thrift_kotlin.domain.Struct
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftParser

class StructVisitor : ThriftBaseVisitor<Struct>() {
    private val structFieldVisitor = StructFieldVisitor()

    override fun visitStruct(ctx: ThriftParser.StructContext): Struct {
        return Struct(
            name = ctx.IDENTIFIER().text,
            fields = ctx.field().map { it.accept(structFieldVisitor) }
        )
    }
}