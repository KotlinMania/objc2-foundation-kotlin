package io.github.kotlinmania.objc2foundation

/**
 * Root class of Objective-C class hierarchies.
 */
open class NSObject : NSObjectProtocol {
    override fun isEqual(other: Any?): Boolean = this == other
    override fun hash(): NSUInteger = hashCode().toULong()
    override fun toString(): String = "NSObject@${hashCode().toUInt().toString(16)}"
}

/**
 * Protocol that defines the basic interface for Objective-C objects.
 */
interface NSObjectProtocol {
    fun isEqual(other: Any?): Boolean
    fun hash(): NSUInteger
}

/**
 * Memory zone representation.
 */
class NSZone

/**
 * Abstract class for encoding and decoding objects.
 */
open class NSCoder : NSObject()

/**
 * Marker type representing the main thread.
 */
class MainThreadMarker private constructor() {
    companion object {
        fun new(): MainThreadMarker = MainThreadMarker()
    }
}
