// port-lint: source value.rs
package io.github.kotlinmania.objc2foundation

/**
 * A simple container for a single C or Objective-C data item.
 */
open class NSValue(
    open val rawValue: Any,
    open val objCType: String,
) : NSObject(),
    NSCopying {
    companion object {
        fun <T : Any> new(value: T): NSValue {
            val enc =
                when (value) {
                    is Boolean -> "B"
                    is Byte -> "c"
                    is UByte -> "C"
                    is Short -> "s"
                    is UShort -> "S"
                    is Int -> "i"
                    is UInt -> "I"
                    is Long -> "q"
                    is ULong -> "Q"
                    is Float -> "f"
                    is Double -> "d"
                    is NSRange -> NSRange.ENCODING
                    is NSPoint -> NSPoint.ENCODING
                    is NSSize -> NSSize.ENCODING
                    is NSRect -> NSRect.ENCODING
                    else -> "@"
                }
            return NSValue(value, enc)
        }

        fun valueWithRange(range: NSRange): NSValue =
            NSValue(range, NSRange.ENCODING)

        fun valueWithPoint(point: NSPoint): NSValue =
            NSValue(point, NSPoint.ENCODING)

        fun valueWithSize(size: NSSize): NSValue =
            NSValue(size, NSSize.ENCODING)

        fun valueWithRect(rect: NSRect): NSValue =
            NSValue(rect, NSRect.ENCODING)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(): T = rawValue as T

    fun get_range(): NSRange? = if (rawValue is NSRange) rawValue as NSRange else null

    fun get_point(): NSPoint? = if (rawValue is NSPoint) rawValue as NSPoint else null

    fun get_size(): NSSize? = if (rawValue is NSSize) rawValue as NSSize else null

    fun get_rect(): NSRect? = if (rawValue is NSRect) rawValue as NSRect else null

    fun rangeValue(): NSRange = get_range() ?: NSRange()

    fun pointValue(): NSPoint = get_point() ?: NSPoint()

    fun sizeValue(): NSSize = get_size() ?: NSSize()

    fun rectValue(): NSRect = get_rect() ?: NSRect()

    fun encoding(): String = objCType

    fun contains_encoding(expected: String): Boolean = objCType == expected

    inline fun <reified T> contains_encoding(): Boolean {
        val enc =
            when (T::class) {
                Boolean::class -> "B"
                Byte::class -> "c"
                UByte::class -> "C"
                Short::class -> "s"
                UShort::class -> "S"
                Int::class -> "i"
                UInt::class -> "I"
                Long::class -> "q"
                ULong::class -> "Q"
                Float::class -> "f"
                Double::class -> "d"
                NSRange::class -> NSRange.ENCODING
                NSPoint::class -> NSPoint.ENCODING
                NSSize::class -> NSSize.ENCODING
                NSRect::class -> NSRect.ENCODING
                else -> "@"
            }
        return contains_encoding(enc)
    }

    override fun copy(): NSValue = this

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSValue) return false
        return objCType == other.objCType && rawValue == other.rawValue
    }

    override fun hashCode(): Int = 31 * objCType.hashCode() + rawValue.hashCode()

    override fun toString(): String {
        val v = rawValue
        if (v is UByte) {
            val hex = v.toString(16).padStart(2, '0')
            return "NSValue { encoding: \"$objCType\", bytes: <$hex> }"
        }
        return "NSValue { encoding: \"$objCType\", value: $rawValue }"
    }
}
