package net.prettyrandom.thrift_kotlin.visitors

import net.prettyrandom.thrift_kotlin.domain.Definition
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftParser

class DefinitionVisitor : ThriftBaseVisitor<Definition>() {
    private val namespaceVisitor = NamespaceVisitor()
    private val enumVisitor = EnumVisitor()

    override fun visitDocument(ctx: ThriftParser.DocumentContext): Definition {
        val namespace = ctx.header().map { it.accept(namespaceVisitor) }.maxOrNull()

        return Definition(
            namespace = namespace?.namespace,
            enums = ctx.definition().map { it.accept(enumVisitor)}
        )
    }
}