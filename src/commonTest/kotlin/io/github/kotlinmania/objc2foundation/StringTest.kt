// port-lint: tests tests/string.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class StringTest {
    @Test
    fun testEquality() {
        val s1 = NSString.from_str("abc")
        val s2 = NSString.from_str("abc")
        assertEquals(s1, s1)
        assertEquals(s1, s2)

        val s3 = NSString.from_str("def")
        assertNotEquals(s1, s3)
    }

    @Test
    fun displayDebug() {
        val s = NSString.from_str("xyz\"123")
        assertEquals("xyz\"123", s.toString())
    }

    @Test
    fun testEmpty() {
        val s1 = NSString.from_str("")
        val s2 = NSString.new()

        assertEquals(0, s1.len())
        assertEquals(0, s2.len())

        assertEquals(s1, s2)

        assertEquals("", s1.to_str())
        assertEquals("", s2.to_str())

        assertEquals("", s1.toString())
        assertEquals("", s2.toString())
    }

    @Test
    fun testUtf8() {
        val expected = "ประเทศไทย中华Việt Nam"
        val s = NSString.from_str(expected)
        assertEquals(expected.encodeToByteArray().size, s.len())
        assertEquals(expected, s.to_str())
        assertEquals(expected, s.toString())
    }

    @Test
    fun testNul() {
        val expected = "\u0000"
        val s = NSString.from_str(expected)
        assertEquals(expected.encodeToByteArray().size, s.len())
        assertEquals(expected, s.to_str())
        assertEquals(expected, s.toString())
    }

    @Test
    fun testInteriorNul() {
        val expected = "Hello\u0000World"
        val s = NSString.from_str(expected)
        assertEquals(expected.encodeToByteArray().size, s.len())
        assertEquals(expected, s.to_str())
        assertEquals(expected, s.toString())
    }

    @Test
    fun testCopy() {
        val s1 = NSString.from_str("abc")
        val s2 = s1.copy()
        assertEquals(s1, s2)

        val s3 = s1.mutableCopy()
        assertEquals(s1, s3)
        assertEquals(NSMutableString::class, s3::class)
    }

    @Test
    fun testCopyNsstringIsSame() {
        val string1 = NSString.from_str("Hello, world!")
        val string2 = string1.copy()
        assertTrue(string1 === string2, "Cloned NSString didn't have the same address")
    }

    @Test
    fun testStripsFirstLeadingZeroWidthNoBreakSpace() {
        val nsString = NSString.from_str("\uFEFF")
        val expected = ""
        assertEquals(expected, nsString.to_str())
        assertEquals(expected, nsString.toString())
        assertEquals(0, nsString.len())

        val s = "\uFEFF\uFEFFa\uFEFF"
        val expected2 = "\uFEFFa\uFEFF"
        val nsString2 = NSString.from_str(s)
        assertEquals(expected2, nsString2.to_str())
        assertEquals(expected2, nsString2.toString())
        assertEquals(expected2.encodeToByteArray().size, nsString2.len())
    }

    @Test
    fun testHash() {
        val s1 = NSString.from_str("example string goes here")
        val s2 = NSString.from_str("example string goes here")
        assertEquals(s1.hashCode(), s2.hashCode())
    }

    @Test
    fun testPrefixSuffix() {
        val s = NSString.from_str("abcdef")
        val prefix = NSString.from_str("abc")
        val suffix = NSString.from_str("def")
        assertTrue(s.hasPrefix(prefix))
        assertTrue(s.hasSuffix(suffix))
        assertFalse(s.hasPrefix(suffix))
        assertFalse(s.hasSuffix(prefix))
    }

    @Test
    fun testCmp() {
        val s1 = NSString.from_str("aa")
        assertTrue(s1 <= s1)
        assertTrue(s1 >= s1)
        val s2 = NSString.from_str("ab")
        assertTrue(s1 < s2)
        assertFalse(s1 > s2)
        assertTrue(s1 <= s2)
        assertFalse(s1 >= s2)
        val s3 = NSString.from_str("ba")
        assertTrue(s1 < s3)
        assertFalse(s1 > s3)
        assertTrue(s1 <= s3)
        assertFalse(s1 >= s3)
        assertTrue(s2 < s3)
        assertFalse(s2 > s3)
        assertTrue(s2 <= s3)
        assertFalse(s2 >= s3)

        val s = NSString.from_str("abc")
        val shorter = NSString.from_str("a")
        val longer = NSString.from_str("abcdef")
        assertTrue(s > shorter)
        assertTrue(s < longer)
    }

    @Test
    fun testAppend() {
        val errorTag = NSString.from_str("Error: ")
        val errorString = NSString.from_str("premature end of file.")
        val errorMessage = errorTag.stringByAppendingString(errorString)
        assertEquals(
            NSString.from_str("Error: premature end of file."),
            errorMessage,
        )

        val extension = NSString.from_str("scratch.tiff")
        assertEquals(
            NSString.from_str("/tmp/scratch.tiff"),
            NSString.from_str("/tmp").stringByAppendingPathComponent(extension),
        )
        assertEquals(
            NSString.from_str("/tmp/scratch.tiff"),
            NSString.from_str("/tmp/").stringByAppendingPathComponent(extension),
        )
        assertEquals(
            NSString.from_str("/scratch.tiff"),
            NSString.from_str("/").stringByAppendingPathComponent(extension),
        )
        assertEquals(
            NSString.from_str("scratch.tiff"),
            NSString.from_str("").stringByAppendingPathComponent(extension),
        )
    }

    @Test
    fun testMacro() {
        val samples =
            listOf(
                "",
                "asdf",
                "🦀",
                "🏳️‍🌈",
                "𝄞music",
                "abcd【e】fg",
                "abcd⒠fg",
                "ääääh",
                "lööps, bröther?",
                "\uFFFD \uFFFD \uFFFD",
                "讓每個人都能打造出。",
                "\u0000",
                "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\u0008\u0009",
            )

        for (sample in samples) {
            val s1 = ns_string(sample)
            val s2 = NSString.from_str(sample)
            assertEquals(s1, s1)
            assertEquals(s1, s2)
            assertEquals(sample, s1.toString())
            assertEquals(sample, s2.toString())
        }
    }
}
