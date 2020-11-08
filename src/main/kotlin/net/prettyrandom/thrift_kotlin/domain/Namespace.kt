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

