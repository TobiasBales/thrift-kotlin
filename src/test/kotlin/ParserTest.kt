package net.prettyrandom.thrift_kotlin.domain

import net.prettyrandom.thrift_kotlin.Parser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Parser")
class ParserTest {
    @Nested
    @DisplayName("Enum")
    inner class EnumTest {
        @Test
        fun `test enums associate the correct values`() {
            val definition = Parser().parse("src/main/thrift/enum/example.thrift")

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

    @Nested
    @DisplayName("Namespace")
    inner class NamespaceTest {
        @Test
        fun `test parser prefers kotlin namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace/kotlin.thrift")

            assertEquals("net.prettyrandom.thrift.test.kotlin", definition.namespace)
        }

        @Test
        fun `test parser falls back to java from kotlin namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace/java.thrift")

            assertEquals("net.prettyrandom.thrift.test.java", definition.namespace)
        }

        @Test
        fun `test parser falls back to generic from java namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace/generic.thrift")

            assertEquals("net.prettyrandom.thrift.test.generic", definition.namespace)
        }

        @Test
        fun `test parser handles missing namespace`() {
            val definition = Parser().parse("src/main/thrift/namespace/missing.thrift")

            assertNull(definition.namespace)
        }
    }

    @Nested
    @DisplayName("Service")
    inner class ServiceTest {
        @Test
        fun `test services associate the correct values`() {
            val definition = Parser().parse("src/main/thrift/service/example.thrift")

            val expected = listOf(
                Service(
                    name = "TestService",
                    functions = listOf(
                        Function(
                            name = "function",
                            returnType = "Response",
                            parameters = listOf(
                                Field(
                                    identifier = 1,
                                    requiredness = Requiredness.DEFAULT,
                                    name = "request",
                                    type = "Request",
                                    defaultValue = null
                                )
                            )
                        ),
                        Function(
                            name = "voidFunction",
                            returnType = "void",
                            parameters = listOf(
                                Field(
                                    identifier = 1,
                                    requiredness = Requiredness.REQUIRED,
                                    name = "request",
                                    type = "Request",
                                    defaultValue = null
                                )
                            )
                        )
                    )
                )
            )

            assertEquals(expected, definition.services)
        }
    }

    @Nested
    @DisplayName("Struct")
    inner class StructTest {
        @Test
        fun `test structs associate the correct values`() {
            val definition = Parser().parse("src/main/thrift/struct/example.thrift")

            val expected = listOf(
                Struct(
                    name = "Response",
                    fields = listOf(
                        Field(identifier = 1, requiredness = Requiredness.DEFAULT, type = "string", name = "one", defaultValue = null),
                        Field(identifier = 2, requiredness = Requiredness.REQUIRED, type = "i32", name = "two", defaultValue = null),
                        Field(identifier = 3, requiredness = Requiredness.OPTIONAL, type = "i64", name = "three", defaultValue = "123")
                    )
                )
            )

            assertEquals(expected, definition.structs)
        }
    }
}