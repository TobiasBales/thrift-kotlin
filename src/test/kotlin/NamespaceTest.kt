package net.prettyrandom.thrift_kotlin.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("Namespace")
class NamespaceTest {
    private val kotlin = Namespace.Kotlin("namespace.kotlin")
    private val java = Namespace.Java("namespace.java")
    private val generic = Namespace.Generic("namespace.generic")

    @Test
    fun `test comparison for kotlin`() {
        Assertions.assertEquals(0, kotlin.compareTo(kotlin))
        Assertions.assertEquals(1, kotlin.compareTo(java))
        Assertions.assertEquals(1, kotlin.compareTo(generic))
        Assertions.assertEquals(1, kotlin.compareTo(null))
    }

    @Test
    fun `test comparison for java`() {
        Assertions.assertEquals(-1, java.compareTo(kotlin))
        Assertions.assertEquals(0, java.compareTo(java))
        Assertions.assertEquals(1, java.compareTo(generic))
        Assertions.assertEquals(1, java.compareTo(null))
    }

    @Test
    fun `test comparison for generic`() {
        Assertions.assertEquals(-1, generic.compareTo(kotlin))
        Assertions.assertEquals(-1, generic.compareTo(java))
        Assertions.assertEquals(0, generic.compareTo(generic))
        Assertions.assertEquals(1, generic.compareTo(null))
    }
}