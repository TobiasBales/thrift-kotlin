package net.prettyrandom.thrift_kotlin.domain

import net.prettyrandom.thrift_kotlin.Parser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

@TestInstance(Lifecycle.PER_CLASS)
class ParserTest {
    @Nested
    @DisplayName("Parser > Namespace")
    class Namespace {
        @Test
        fun `test parser prefers kotlin namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace_kotlin.thrift")

            Assertions.assertEquals("net.prettyrandom.thrift.test.kotlin", definition.namespace)
        }

        @Test
        fun `test parser falls back to java namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace_java.thrift")

            Assertions.assertEquals("net.prettyrandom.thrift.test.java", definition.namespace)
        }

        @Test
        fun `test parser falls back to generic namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace_generic.thrift")

            Assertions.assertEquals("net.prettyrandom.thrift.test.generic", definition.namespace)
        }

        @Test
        fun `test parser fails for missing namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace_missing.thrift")

            Assertions.assertNull(definition.namespace)
        }
    }
}