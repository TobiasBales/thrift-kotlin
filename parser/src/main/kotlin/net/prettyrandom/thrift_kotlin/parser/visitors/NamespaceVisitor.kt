package net.prettyrandom.thrift_kotlin.parser.visitors

import net.prettyrandom.thrift_kotlin.parser.domain.Namespace
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftBaseVisitor
import net.prettyrandom.thrift_kotlin.parser.generated.parser.ThriftParser

class NamespaceVisitor : ThriftBaseVisitor<Namespace>() {
    override fun visitNamespace(ctx: ThriftParser.NamespaceContext): Namespace? {
        if (ctx.IDENTIFIER().size == 1) {
            return Namespace.Generic(ctx.IDENTIFIER(0).text)
        }

        val language = ctx.IDENTIFIER(0).text
        val namespaceValue = ctx.IDENTIFIER(1).text
        return when (language) {
            "kotlin" -> Namespace.Kotlin(namespaceValue)
            "java" -> Namespace.Java(namespaceValue)
            else -> null
        }
    }
}