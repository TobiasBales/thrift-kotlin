package net.prettyrandom.thrift_kotlin.visitors

import net.prettyrandom.thrift_kotlin.domain.Requiredness
import net.prettyrandom.thrift_kotlin.domain.StructField
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftParser

class StructFieldVisitor : ThriftBaseVisitor<StructField>() {
    override fun visitField(ctx: ThriftParser.FieldContext): StructField {
        return StructField(
            identifier = ctx.field_id().integer().text.toInt(),
            requiredness = ctx.getRequiredness(),
            name = ctx.IDENTIFIER().toString(),
            type = ctx.field_type().text,
            defaultValue = ctx.const_value()?.text
        )
    }
}

private fun ThriftParser.FieldContext.getRequiredness(): Requiredness {
    return when (field_req()?.text) {
        null -> return Requiredness.DEFAULT
        "required" -> Requiredness.REQUIRED
        "optional" -> Requiredness.OPTIONAL
        else -> throw Exception("Got invalid value for requiredness ${field_req().text}")
    }
}
