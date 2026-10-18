// port-lint: source copying.rs
package io.github.kotlinmania.objc2foundation

/**
 * A helper interface for implementing NSCopying.
 */
interface CopyingHelper<out R> {
    fun copy(): R
}

/**
 * A helper interface for implementing NSMutableCopying.
 */
interface MutableCopyingHelper<out R> {
    fun mutableCopy(): R
}

/**
 * A protocol to provide functional copies of objects.
 */
interface NSCopying : CopyingHelper<Any> {
    override fun copy(): Any
    fun copyWithZone(zone: NSZone?): Any = copy()
}

/**
 * A protocol to provide mutable copies of objects.
 */
interface NSMutableCopying : MutableCopyingHelper<Any> {
    override fun mutableCopy(): Any
    fun mutableCopyWithZone(zone: NSZone?): Any = mutableCopy()
}
