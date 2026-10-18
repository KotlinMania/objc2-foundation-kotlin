// port-lint: source generated/NSObject.rs
package io.github.kotlinmania.objc2foundation.generated

import io.github.kotlinmania.objc2foundation.NSCoder
import io.github.kotlinmania.objc2foundation.NSInteger
import io.github.kotlinmania.objc2foundation.NSObject
import io.github.kotlinmania.objc2foundation.NSUInteger
import io.github.kotlinmania.objc2foundation.NSZone

/**
 * Protocol for encoding and decoding objects.
 */
interface NSCoding {
    fun encodeWithCoder(coder: NSCoder)

    fun initWithCoder(coder: NSCoder): Any?
}

/**
 * Protocol for secure coding.
 */
interface NSSecureCoding : NSCoding {
    companion object {
        fun supportsSecureCoding(): Boolean = true
    }
}

/**
 * Base protocol for discardable content.
 */
interface NSDiscardableContent {
    fun beginContentAccess(): Boolean

    fun endContentAccess()

    fun discardContentIfPossible()

    fun isContentDiscarded(): Boolean
}

/**
 * Category NSCoderMethods on NSObject.
 */
interface NSObjectNSCoderMethods {
    fun version(): NSInteger = 0L

    fun setVersion(version: NSInteger) {}

    fun classForCoder(): Any? = null

    fun replacementObjectForCoder(coder: NSCoder): Any? = null
}

/**
 * Category NSDiscardableContentProxy on NSObject.
 */
interface NSObjectNSDiscardableContentProxy {
    fun autoContentAccessingProxy(): Any? = null
}

fun NSAllocateObject(clazz: Any?, extraBytes: NSUInteger, zone: NSZone?): Any = NSObject()

fun NSCopyObject(obj: Any, extraBytes: NSUInteger, zone: NSZone?): Any = obj

fun NSShouldRetainWithZone(obj: Any, requestedZone: NSZone?): Boolean = true

fun NSIncrementExtraRefCount(obj: Any) {}

fun NSDecrementExtraRefCountWasZero(obj: Any): Boolean = false

fun NSExtraRefCount(obj: Any): NSUInteger = 0uL

fun NSDeallocateObject(obj: Any) {}
