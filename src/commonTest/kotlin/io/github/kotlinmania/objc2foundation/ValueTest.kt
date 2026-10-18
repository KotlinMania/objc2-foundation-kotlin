// port-lint: tests tests/value.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

class ValueTest {

    @Test
    fun basic() {
        val val_ = NSValue.new(13u)
        assertEquals(13u, val_.get<UInt>())
    }

    @Test
    fun testEquality() {
        val val1 = NSValue.new(123u)
        val val2 = NSValue.new(123u)
        assertEquals(val1, val1)
        assertEquals(val1, val2)

        val val3 = NSValue.new(456u)
        assertNotEquals(val1, val3)
    }

    @Test
    fun testEqualityAcrossTypes() {
        val val1 = NSValue.new(123)
        val val2 = NSValue.new(123u)
        assertNotEquals(val1, val2)
    }

    @Test
    fun testDebug() {
        val actual = NSValue.new(171.toUByte()).toString()
        assertEquals("NSValue { encoding: \"C\", bytes: <ab> }", actual)
    }

    @Test
    fun nsrange() {
        val range = NSRange.from(1L until 2L)
        val val_ = NSValue.new(range)
        assertEquals(range, val_.get_range())
        assertNull(val_.get_point())
        assertNull(val_.get_size())
        assertNull(val_.get_rect())
        assertEquals(range, val_.get<NSRange>())
    }

    @Test
    fun nspoint() {
        val point = NSPoint.new(1.0, 2.0)
        val val_ = NSValue.new(point)
        assertEquals(point, val_.get_point())
        assertEquals(point, val_.get<NSPoint>())
    }

    @Test
    fun nssize() {
        val size = NSSize.new(1.0, 2.0)
        val val_ = NSValue.new(size)
        assertEquals(size, val_.get_size())
        assertEquals(size, val_.get<NSSize>())
    }

    @Test
    fun nsrect() {
        val rect = NSRect.new(NSPoint.new(1.0, 2.0), NSSize.new(3.0, 4.0))
        val val_ = NSValue.new(rect)
        assertEquals(rect, val_.get_rect())
        assertEquals(rect, val_.get<NSRect>())
    }
}
