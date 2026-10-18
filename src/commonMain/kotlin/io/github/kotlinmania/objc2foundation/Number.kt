// port-lint: source number.rs
package io.github.kotlinmania.objc2foundation

/**
 * An object wrapper for primitive scalar numeric values.
 */
open class NSNumber(
    val numberValue: Number,
    objCType: String,
) : NSValue(numberValue, objCType),
    Comparable<NSNumber> {
    companion object {
        fun new_bool(val_: Boolean): NSNumber =
            NSNumber(if (val_) 1 else 0, "c")

        fun new_i8(val_: Byte): NSNumber =
            NSNumber(val_, "c")

        fun new_u8(val_: UByte): NSNumber =
            NSNumber(val_.toShort(), "C")

        fun new_i16(val_: Short): NSNumber =
            NSNumber(val_, "s")

        fun new_u16(val_: UShort): NSNumber =
            NSNumber(val_.toInt(), "S")

        fun new_i32(val_: Int): NSNumber =
            NSNumber(val_, "i")

        fun new_u32(val_: UInt): NSNumber =
            NSNumber(val_.toLong(), "I")

        fun new_i64(val_: Long): NSNumber =
            NSNumber(val_, "q")

        fun new_u64(val_: ULong): NSNumber =
            NSNumber(val_.toLong(), "Q")

        fun new_isize(val_: Long): NSNumber =
            NSNumber(val_, "q")

        fun new_usize(val_: ULong): NSNumber =
            NSNumber(val_.toLong(), "Q")

        fun new_f32(val_: Float): NSNumber =
            NSNumber(val_, "f")

        fun new_f64(val_: Double): NSNumber =
            NSNumber(val_, "d")

        fun new_cgfloat(val_: CGFloat): NSNumber =
            new_f64(val_)
    }

    fun as_bool(): Boolean = numberValue.toLong() != 0L

    fun as_i8(): Byte = numberValue.toByte()

    fun as_u8(): UByte = numberValue.toByte().toUByte()

    fun as_i16(): Short = numberValue.toShort()

    fun as_u16(): UShort = numberValue.toShort().toUShort()

    fun as_i32(): Int = numberValue.toInt()

    fun as_u32(): UInt = numberValue.toInt().toUInt()

    fun as_i64(): Long = numberValue.toLong()

    fun as_u64(): ULong = numberValue.toLong().toULong()

    fun as_isize(): Long = numberValue.toLong()

    fun as_usize(): ULong = numberValue.toLong().toULong()

    fun as_f32(): Float = numberValue.toFloat()

    fun as_f64(): Double = numberValue.toDouble()

    fun as_cgfloat(): CGFloat = numberValue.toDouble()

    fun isEqualToNumber(other: NSNumber): Boolean = compareTo(other) == 0

    override fun compareTo(other: NSNumber): Int {
        val isFloatThis = objCType == "f" || objCType == "d"
        val isFloatOther = other.objCType == "f" || other.objCType == "d"

        if (isFloatThis || isFloatOther) {
            val d1 = as_f64()
            val d2 = other.as_f64()
            if (d1.isNaN() && d2.isNaN()) return 0
            return d1.compareTo(d2)
        }

        return as_i64().compareTo(other.as_i64())
    }

    fun compare(other: NSNumber): NSComparisonResult =
        NSComparisonResult.from(compareTo(other))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSNumber) return false
        return isEqualToNumber(other)
    }

    override fun hashCode(): Int = numberValue.hashCode()

    override fun toString(): String = numberValue.toString()
}
