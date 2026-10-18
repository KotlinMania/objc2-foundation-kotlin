// port-lint: tests tests/mutable_string.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MutableStringTest {
    @Test
    fun displayDebug() {
        val s = NSMutableString.from_str("test\"123")
        assertEquals("test\"123", s.toString())
    }

    @Test
    fun testFromNsstring() {
        val s = NSString.from_str("abc")
        val ms = NSMutableString.stringWithString(s)
        assertEquals("abc", ms.toString())
    }

    @Test
    fun testAppend() {
        val s = NSMutableString.from_str("abc")
        s.appendString(NSString.from_str("def"))
        s += NSString.from_str("ghi")
        assertEquals("abcdefghi", s.toString())
    }

    @Test
    fun testSet() {
        val s = NSMutableString.from_str("abc")
        s.setString(NSString.from_str("def"))
        assertEquals("def", s.toString())
    }

    @Test
    fun testWithCapacity() {
        val s = NSMutableString.stringWithCapacity(3)
        s += NSString.from_str("abc")
        s += NSString.from_str("def")
        assertEquals("abcdef", s.toString())
    }

    @Test
    fun testCopy() {
        val s1 = NSMutableString.from_str("abc")
        val s2 = s1.copy()
        assertEquals(s1.rawString, s2.rawString)
        assertEquals(NSString::class, s2::class)

        val s3 = s1.mutableCopy()
        assertEquals(s1.rawString, s3.rawString)
        assertEquals(NSMutableString::class, s3::class)
        assertTrue(s1 !== s3)
    }

    @Test
    fun testCopyWithZone() {
        val s1 = NSString.from_str("abc")
        val s2 = s1.copyWithZone(null)
        assertEquals(s1, s2)

        val s3 = s1.mutableCopyWithZone(null)
        assertEquals(s1, s3)
        assertTrue(s3 is NSMutableString)
    }

    @Test
    fun writeSameString() {
        val s = NSMutableString.from_str("abc")
        s.appendString(s)
        assertEquals("abcabc", s.toString())
    }

    @Test
    fun appendSameString() {
        val s = NSMutableString.from_str("abc")
        s += s
        assertEquals("abcabc", s.toString())
    }
}
