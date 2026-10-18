// port-lint: tests tests/number.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class NumberTest {

    @Test
    fun basic() {
        val val_ = NSNumber.new_u32(13u)
        assertEquals(13u, val_.as_u32())
    }

    @Test
    fun roundtrip() {
        assertTrue(NSNumber.new_bool(true).as_bool())
        assertFalse(NSNumber.new_bool(false).as_bool())

        fun assertRoundtripSigned(v: Long) {
            assertEquals(v.toByte(), NSNumber.new_i8(v.toByte()).as_i8())
            assertEquals(v.toShort(), NSNumber.new_i16(v.toShort()).as_i16())
            assertEquals(v.toInt(), NSNumber.new_i32(v.toInt()).as_i32())
            assertEquals(v, NSNumber.new_i64(v).as_i64())
            assertEquals(v, NSNumber.new_isize(v).as_isize())
        }

        assertRoundtripSigned(Long.MIN_VALUE)
        assertRoundtripSigned(Int.MIN_VALUE.toLong())
        assertRoundtripSigned(Short.MIN_VALUE.toLong())
        assertRoundtripSigned(Byte.MIN_VALUE.toLong())
        assertRoundtripSigned(-1L)
        assertRoundtripSigned(0L)
        assertRoundtripSigned(1L)
        assertRoundtripSigned(Byte.MAX_VALUE.toLong())
        assertRoundtripSigned(Short.MAX_VALUE.toLong())
        assertRoundtripSigned(Int.MAX_VALUE.toLong())
        assertRoundtripSigned(Long.MAX_VALUE)

        fun assertRoundtripUnsigned(v: ULong) {
            assertEquals(v.toUByte(), NSNumber.new_u8(v.toUByte()).as_u8())
            assertEquals(v.toUShort(), NSNumber.new_u16(v.toUShort()).as_u16())
            assertEquals(v.toUInt(), NSNumber.new_u32(v.toUInt()).as_u32())
            assertEquals(v, NSNumber.new_u64(v).as_u64())
            assertEquals(v, NSNumber.new_usize(v).as_usize())
        }

        assertRoundtripUnsigned(0uL)
        assertRoundtripUnsigned(1uL)
        assertRoundtripUnsigned(UByte.MAX_VALUE.toULong())
        assertRoundtripUnsigned(UShort.MAX_VALUE.toULong())
        assertRoundtripUnsigned(UInt.MAX_VALUE.toULong())
        assertRoundtripUnsigned(ULong.MAX_VALUE)

        fun assertRoundtripFloat(v: Double) {
            assertEquals(v.toFloat(), NSNumber.new_f32(v.toFloat()).as_f32())
            assertEquals(v, NSNumber.new_f64(v).as_f64())
        }

        assertRoundtripFloat(0.0)
        assertRoundtripFloat(-1.0)
        assertRoundtripFloat(1.0)
        assertRoundtripFloat(Double.POSITIVE_INFINITY)
        assertRoundtripFloat(Double.NEGATIVE_INFINITY)
        assertRoundtripFloat(Double.MAX_VALUE)
        assertRoundtripFloat(Double.MIN_VALUE)

        assertTrue(NSNumber.new_f32(Float.NaN).as_f32().isNaN())
        assertTrue(NSNumber.new_f64(Double.NaN).as_f64().isNaN())
        assertTrue(NSNumber.new_f32(-Float.NaN).as_f32().isNaN())
        assertTrue(NSNumber.new_f64(-Double.NaN).as_f64().isNaN())
    }

    @Test
    fun castBetweenTypes() {
        assertEquals(1.toByte(), NSNumber.new_bool(true).as_i8())
        assertEquals(Int.MAX_VALUE.toUInt(), NSNumber.new_i32(Int.MAX_VALUE).as_u32())
        assertEquals(1u, NSNumber.new_f32(1.0f).as_u32())
    }

    @Test
    fun equality() {
        val val1 = NSNumber.new_u32(123u)
        val val2 = NSNumber.new_u32(123u)
        val val3 = NSNumber.new_u8(123u.toUByte())
        assertEquals(val1, val1)
        assertEquals(val1, val2)
        assertEquals(val1, val3)

        val val4 = NSNumber.new_u32(456u)
        assertNotEquals(val1, val4)
    }

    @Test
    fun nanEquality() {
        val nan = NSNumber.new_f32(Float.NaN)
        val nan2 = NSNumber.new_f32(Float.NaN)
        val negNan = NSNumber.new_f32(-Float.NaN)
        assertEquals(nan, nan)
        assertEquals(nan, nan2)
        assertEquals(negNan, negNan)
        assertEquals(nan, negNan)
    }
}
