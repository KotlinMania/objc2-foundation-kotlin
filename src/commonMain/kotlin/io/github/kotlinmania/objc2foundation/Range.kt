// port-lint: source range.rs
package io.github.kotlinmania.objc2foundation

/**
 * A structure representing a range of items.
 */
data class NSRange(
    val location: NSUInteger = 0uL,
    val length: NSUInteger = 0uL,
) : Comparable<NSRange> {
    companion object {
        const val ENCODING: String = "{_NSRange=QQ}"
        const val ENCODING_REF: String = "^{_NSRange=QQ}"

        fun new(location: NSUInteger, length: NSUInteger): NSRange =
            NSRange(location, length)

        fun new(location: Long, length: Long): NSRange =
            NSRange(location.toULong(), length.toULong())

        fun new(location: Int, length: Int): NSRange =
            NSRange(location.toULong(), length.toULong())

        fun from(start: Long, endExclusive: Long): NSRange {
            require(endExclusive >= start) { "Range end < start" }
            return NSRange(start.toULong(), (endExclusive - start).toULong())
        }

        fun from(range: LongRange): NSRange {
            require(range.last >= range.first - 1) { "Range end < start" }
            val length = if (range.isEmpty()) 0uL else (range.last - range.first + 1).toULong()
            return NSRange(range.first.toULong(), length)
        }

        fun from(range: IntRange): NSRange {
            require(range.last >= range.first - 1) { "Range end < start" }
            val length = if (range.isEmpty()) 0uL else (range.last - range.first + 1).toULong()
            return NSRange(range.first.toULong(), length)
        }

        fun from(nsrange: NSRange): LongRange = nsrange.toLongRange()
    }

    fun isEmpty(): Boolean = length == 0uL

    fun contains(index: NSUInteger): Boolean =
        if (index >= location) {
            (index - location) < length
        } else {
            false
        }

    fun contains(index: Long): Boolean = contains(index.toULong())

    fun contains(index: Int): Boolean = contains(index.toULong())

    fun end(): NSUInteger {
        val result = location + length
        if (result < location) {
            error("NSRange too large")
        }
        return result
    }

    fun toLongRange(): LongRange {
        val endVal = end().toLong()
        return location.toLong() until endVal
    }

    override fun compareTo(other: NSRange): Int {
        val locCmp = location.compareTo(other.location)
        if (locCmp != 0) return locCmp
        return length.compareTo(other.length)
    }
}
