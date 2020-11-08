package net.prettyrandom.thrift_kotlin.domain

import net.prettyrandom.thrift_kotlin.Parser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Parser")
class ParserTest {
    @Nested
    @DisplayName("Namespace")
    inner class NamespaceTest {
        @Test
        fun `test parser prefers kotlin namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace_kotlin.thrift")

            assertEquals("net.prettyrandom.thrift.test.kotlin", definition.namespace)
        }

        @Test
        fun `test parser falls back to java namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace_java.thrift")

            assertEquals("net.prettyrandom.thrift.test.java", definition.namespace)
        }

        @Test
        fun `test parser falls back to generic namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace_generic.thrift")

            assertEquals("net.prettyrandom.thrift.test.generic", definition.namespace)
        }

        @Test
        fun `test parser fails for missing namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace_missing.thrift")

            Assertions.assertNull(definition.namespace)
        }
    }

    @Nested
    @DisplayName("Enum")
    inner class EnumTest {
        @Test
        fun `test enums associate the correct values`() {
            val definition = Parser().parse("src/main/thrift/enum.thrift")

            val expected = listOf(
                Enum(
                    name = "STATUS",
                    values = listOf(
                        EnumValue(name = "NO_VALUE", value = 0),
                        EnumValue(name = "FOLLOWING_NO_VALUE", value = 1),
                        EnumValue(name = "NOT_FOUND", value = 404),
                        EnumValue(name = "FOLLOWING_INTEGER", value = 405),
                        EnumValue(name = "UNkNOWN", value = 10),
                        EnumValue(name = "FOLLOWING_HEX_INTEGER", value = 11)
                    )
                )
            )

            assertEquals(expected, definition.enums)
        }
    }
}