package net.prettyrandom.thrift_kotlin.visitors

import net.prettyrandom.thrift_kotlin.domain.Service
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftParser

class ServiceVisitor : ThriftBaseVisitor<Service>() {
    private val functionVisitor = FunctionVisitor()

    override fun visitService(ctx: ThriftParser.ServiceContext): Service {
        return Service(
            name = ctx.IDENTIFIER()[0].text,
            functions = ctx.function().mapNotNull { it.accept(functionVisitor) }
        )
    }
}