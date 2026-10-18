// port-lint: tests tests/data.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DataTest {
    @Test
    fun testBytes() {
        val bytes = byteArrayOf(3, 7, 16, 52, 112, 19)
        val data = NSData.with_bytes(bytes)
        assertEquals(bytes.size, data.len())
        assertTrue(bytes.contentEquals(data.to_vec()))
    }

    @Test
    fun testNoBytes() {
        val data = NSData.new()
        assertEquals(0, data.to_vec().size)
    }

    @Test
    fun testFromVec() {
        val bytes = byteArrayOf(3, 7, 16)
        val data = NSData.from_vec(bytes)
        assertTrue(bytes.contentEquals(data.as_bytes_unchecked()))
    }

    @Test
    fun testDebug() {
        val bytes = byteArrayOf(3, 7, 16, 52, 112, 19)
        val data = NSData.with_bytes(bytes)
        assertEquals("[3, 7, 16, 52, 112, 19]", data.toString())
    }
}
