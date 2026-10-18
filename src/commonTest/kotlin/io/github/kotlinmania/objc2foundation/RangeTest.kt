// port-lint: tests range.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RangeTest {
    @Test
    fun testFromRange() {
        val cases =
            listOf(
                (0L until 0L) to NSRange.new(0L, 0L),
                (0L until 10L) to NSRange.new(0L, 10L),
                (10L until 10L) to NSRange.new(10L, 0L),
                (10L until 20L) to NSRange.new(10L, 10L),
            )

        for ((range, expected) in cases) {
            assertEquals(expected, NSRange.from(range))
        }
    }

    @Test
    fun testFromRangeInverted() {
        assertFailsWith<IllegalArgumentException> {
            NSRange.from(10L, 0L)
        }
    }

    @Test
    fun testContains() {
        val range = NSRange.from(10L until 20L)
        assertFalse(range.contains(0L))
        assertFalse(range.contains(9L))
        assertTrue(range.contains(10L))
        assertTrue(range.contains(11L))
        assertFalse(range.contains(20L))
        assertFalse(range.contains(21L))
    }

    @Test
    fun testEnd() {
        val range = NSRange.from(10L until 20L)
        assertFalse(range.contains(0L))
        assertFalse(range.contains(9L))
        assertTrue(range.contains(10L))
        assertTrue(range.contains(11L))
        assertFalse(range.contains(20L))
        assertFalse(range.contains(21L))
    }

    @Test
    fun testEndLarge() {
        assertFailsWith<IllegalStateException> {
            NSRange.new(ULong.MAX_VALUE, ULong.MAX_VALUE).end()
        }
    }
}
