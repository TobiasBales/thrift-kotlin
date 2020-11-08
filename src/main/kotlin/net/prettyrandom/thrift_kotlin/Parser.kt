package net.prettyrandom.thrift_kotlin

import ThriftLexer
import ThriftParser
import net.prettyrandom.thrift_kotlin.domain.Definition
import net.prettyrandom.thrift_kotlin.listeners.NamespaceListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

class Parser {
    private val namespaceListener = NamespaceListener()

    fun parse(filename: String): Definition {
        val parser = buildParser(readDefinition(filename))

        parser.document()

        return Definition(namespace = namespaceListener.getNamespace())
    }

    private fun readDefinition(filename: String): String {
        return File(filename).readLines().joinToString(separator = "\n")
    }

    private fun buildParser(definition: String): ThriftParser {
        val lexer = ThriftLexer(CharStreams.fromString(definition))
        val tokens = CommonTokenStream(lexer)
        val parser = ThriftParser(tokens)

        parser.addParseListener(namespaceListener)

        return parser
    }
}