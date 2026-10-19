// port-lint: source generated/NSEnumerator.rs
package io.github.kotlinmania.objc2foundation.generated

import io.github.kotlinmania.objc2foundation.NSArray
import io.github.kotlinmania.objc2foundation.NSFastEnumerationState
import io.github.kotlinmania.objc2foundation.NSObject
import io.github.kotlinmania.objc2foundation.NSUInteger

/**
 * Protocol for fast enumeration.
 *
 * [Apple's documentation](https://developer.apple.com/documentation/foundation/nsfastenumeration?language=objc)
 */
interface NSFastEnumeration {
    fun countByEnumeratingWithState_objects_count(
        state: NSFastEnumerationState,
        buffer: Array<Any?>,
        len: NSUInteger,
    ): NSUInteger
}

/**
 * An abstract class whose subclasses enumerate collections of other objects.
 *
 * [Apple's documentation](https://developer.apple.com/documentation/foundation/nsenumerator?language=objc)
 */
open class NSEnumerator<ObjectType : Any> :
    NSObject,
    NSFastEnumeration,
    Iterable<ObjectType> {
    private val underlyingIterator: Iterator<ObjectType>?

    constructor() : super() {
        this.underlyingIterator = null
    }

    constructor(elements: List<ObjectType>) : super() {
        this.underlyingIterator = elements.iterator()
    }

    internal constructor(iterator: Iterator<ObjectType>) : super() {
        this.underlyingIterator = iterator
    }

    open fun nextObject(): ObjectType? =
        if (underlyingIterator != null && underlyingIterator.hasNext()) {
            underlyingIterator.next()
        } else {
            null
        }

    open fun allObjects(): NSArray<ObjectType> {
        val list = mutableListOf<ObjectType>()
        while (true) {
            val obj = nextObject() ?: break
            list.add(obj)
        }
        return NSArray(list)
    }

    override fun countByEnumeratingWithState_objects_count(
        state: NSFastEnumerationState,
        buffer: Array<Any?>,
        len: NSUInteger,
    ): NSUInteger {
        var count = 0uL
        val maxLen = len.toInt().coerceAtMost(buffer.size)
        while (count.toInt() < maxLen) {
            val obj = nextObject() ?: break
            buffer[count.toInt()] = obj
            count++
        }
        state.state = if (count > 0uL) state.state + count else 0uL
        return count
    }

    override fun iterator(): Iterator<ObjectType> =
        object : Iterator<ObjectType> {
            private var nextVal: ObjectType? = null
            private var hasNextVal: Boolean = false

            override fun hasNext(): Boolean {
                if (hasNextVal) return true
                nextVal = nextObject()
                hasNextVal = nextVal != null
                return hasNextVal
            }

            override fun next(): ObjectType {
                if (!hasNext()) throw NoSuchElementException()
                hasNextVal = false
                val res = nextVal ?: throw NoSuchElementException()
                nextVal = null
                return res
            }
        }

    companion object {
        fun <ObjectType : Any> new(): NSEnumerator<ObjectType> = NSEnumerator()

        fun <ObjectType : Any> init(): NSEnumerator<ObjectType> = NSEnumerator()

        fun <ObjectType : Any> fromIterator(iterator: Iterator<ObjectType>): NSEnumerator<ObjectType> =
            NSEnumerator(iterator)

        fun <ObjectType : Any> fromList(elements: List<ObjectType>): NSEnumerator<ObjectType> =
            NSEnumerator(elements)
    }
}
