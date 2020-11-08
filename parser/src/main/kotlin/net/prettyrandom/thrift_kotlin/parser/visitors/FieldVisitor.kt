package net.prettyrandom.thrift_kotlin.parser.visitors

import net.prettyrandom.thrift_kotlin.parser.domain.Field
import net.prettyrandom.thrift_kotlin.parser.extensions.getRequiredness
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftParser

class FieldVisitor : ThriftBaseVisitor<Field>() {
    override fun visitField(ctx: ThriftParser.FieldContext): Field {
        return Field(
            identifier = ctx.field_id().integer().text.toInt(),
            requiredness = ctx.getRequiredness(),
            name = ctx.IDENTIFIER().toString(),
            type = ctx.field_type().text,
            defaultValue = ctx.const_value()?.text
        )
    }
}
