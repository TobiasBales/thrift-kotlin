package net.prettyrandom.thrift_kotlin.parser.visitors

import net.prettyrandom.thrift_kotlin.parser.domain.Service
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftParser

class ServiceVisitor : ThriftBaseVisitor<Service>() {
    private val functionVisitor = FunctionVisitor()

    override fun visitService(ctx: ThriftParser.ServiceContext): Service {
        return Service(
            name = ctx.IDENTIFIER()[0].text,
            functions = ctx.function().mapNotNull { it.accept(functionVisitor) }
        )
    }
}
