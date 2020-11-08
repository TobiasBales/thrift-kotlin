package net.prettyrandom.thrift_kotlin

import net.prettyrandom.thrift_kotlin.domain.Definition
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftLexer
import net.prettyrandom.thrift_kotlin.generated.parser.ThriftParser
import net.prettyrandom.thrift_kotlin.visitors.DefinitionVisitor
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

class Parser {
    fun parse(filename: String): Definition {
        val parser = buildParser(readDefinition(filename))

        val visitor = DefinitionVisitor()

        return visitor.visit(parser.document())
    }

    private fun readDefinition(filename: String): String {
        return File(filename).readLines().joinToString(separator = "\n")
    }

    private fun buildParser(definition: String): ThriftParser {
        val lexer = ThriftLexer(CharStreams.fromString(definition))
        val tokens = CommonTokenStream(lexer)
        return ThriftParser(tokens)
    }
}