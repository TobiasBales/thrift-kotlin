package net.prettyrandom.thrift_kotlin.domain


sealed class Namespace(val namespace: String) : Comparable<Namespace?> {
    class Kotlin(namespace: String) : Namespace(namespace)
    class Java(namespace: String) : Namespace(namespace)
    class Generic(namespace: String) : Namespace(namespace)

    override fun compareTo(other: Namespace?): Int {
        return NamespaceComparator().compare(this, other)
    }

    override fun toString(): String {
        return "Namespace(namespace='$namespace')"
    }
}

class NamespaceComparator : Comparator<Namespace> {
    override fun compare(a: Namespace?, b: Namespace?): Int {
        if (a is Namespace.Kotlin && b is Namespace.Kotlin) {
            return 0
        } else if (a is Namespace.Kotlin) {
            return 1
        } else if (b is Namespace.Kotlin) {
            return -1
        } else if (a is Namespace.Java && b is Namespace.Java) {
            return 0
        } else if (a is Namespace.Java) {
            return 1
        } else if (b is Namespace.Java) {
            return -1
        } else if (a is Namespace.Generic && b is Namespace.Generic) {
            return 0
        } else if (a is Namespace.Generic) {
            return 1
        } else if (b is Namespace.Generic) {
            return -1
        } else if (a == null && b == null) {
            return 0
        } else {
            throw IllegalArgumentException("Illegal comparison between $a and $b")
        }
    }
}
