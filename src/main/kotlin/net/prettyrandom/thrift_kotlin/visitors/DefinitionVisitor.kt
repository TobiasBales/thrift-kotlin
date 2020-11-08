package net.prettyrandom.thrift_kotlin.visitors

import net.prettyrandom.thrift_kotlin.domain.Definition
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftParser

class DefinitionVisitor : ThriftBaseVisitor<Definition>() {
    private val namespaceVisitor = NamespaceVisitor()
    private val enumVisitor = EnumVisitor()
    private val structVisitor = StructVisitor()
    private val serviceVisitor = ServiceVisitor()

    override fun visitDocument(ctx: ThriftParser.DocumentContext): Definition {
        val namespace = ctx.header().map { it.accept(namespaceVisitor) }.maxOrNull()

        return Definition(
            namespace = namespace?.namespace,
            enums = ctx.definition().mapNotNull { it.accept(enumVisitor) },
            structs = ctx.definition().mapNotNull { it.accept(structVisitor) },
            services = ctx.definition().mapNotNull { it.accept(serviceVisitor) }
        )
    }
}