package net.prettyrandom.thrift_kotlin.listeners

import ThriftBaseListener
import ThriftParser
import net.prettyrandom.thrift_kotlin.domain.Namespace
import net.prettyrandom.thrift_kotlin.domain.NamespaceComparator
import net.prettyrandom.thrift_kotlin.exceptions.MissingNamespaceException
import java.util.*

class NamespaceListener : ThriftBaseListener() {
    private var namespace: Namespace? = null

    fun getNamespace(): Namespace? {
        return namespace
    }

    override fun exitNamespace(ctx: ThriftParser.NamespaceContext) {
        val parsedNamespace = parseNamespace(ctx)

        if (Objects.compare(parsedNamespace, namespace, NamespaceComparator()::compare) == 1) {
            namespace = parsedNamespace
        }
    }

    private fun parseNamespace(ctx: ThriftParser.NamespaceContext): Namespace? {
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