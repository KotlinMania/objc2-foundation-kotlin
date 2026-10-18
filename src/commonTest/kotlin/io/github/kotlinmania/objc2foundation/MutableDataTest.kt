// port-lint: tests tests/mutable_data.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class MutableDataTest {

    @Test
    fun testBytesMut() {
        val data = NSMutableData.with_bytes(byteArrayOf(7, 16))
        data.as_mut_bytes_unchecked()[0] = 3
        assertTrue(byteArrayOf(3, 16).contentEquals(data.to_vec()))
    }

    @Test
    fun testSetLen() {
        val data = NSMutableData.with_bytes(byteArrayOf(7, 16))
        data.setLength(4)
        assertEquals(4, data.len())
        assertTrue(byteArrayOf(7, 16, 0, 0).contentEquals(data.to_vec()))

        data.setLength(1)
        assertEquals(1, data.len())
        assertTrue(byteArrayOf(7).contentEquals(data.to_vec()))
    }

    @Test
    fun testAppend() {
        val data = NSMutableData.with_bytes(byteArrayOf(7, 16))
        data.extend_from_slice(byteArrayOf(3, 52))
        assertEquals(4, data.len())
        assertTrue(byteArrayOf(7, 16, 3, 52).contentEquals(data.to_vec()))
    }

    @Test
    fun testReplace() {
        val data = NSMutableData.with_bytes(byteArrayOf(7, 16))
        data.replace_range(0L until 0L, byteArrayOf(3))
        assertTrue(byteArrayOf(3, 7, 16).contentEquals(data.to_vec()))

        data.replace_range(1L until 2L, byteArrayOf(52, 13))
        assertTrue(byteArrayOf(3, 52, 13, 16).contentEquals(data.to_vec()))

        data.replace_range(2L until 4L, byteArrayOf(6))
        assertTrue(byteArrayOf(3, 52, 6).contentEquals(data.to_vec()))

        data.set_bytes(byteArrayOf(8, 17))
        assertTrue(byteArrayOf(8, 17).contentEquals(data.to_vec()))
    }

    @Test
    fun testFromData() {
        val data = NSData.with_bytes(byteArrayOf(1, 2))
        val mutData = NSMutableData.dataWithData(data)
        assertEquals(data, mutData)
    }

    @Test
    fun testWithCapacity() {
        val data = NSMutableData.dataWithCapacity(5)
        assertNotNull(data)
        assertEquals(0, data.to_vec().size)
        data.extend_from_slice(byteArrayOf(1, 2, 3, 4, 5))
        assertTrue(byteArrayOf(1, 2, 3, 4, 5).contentEquals(data.to_vec()))
        data.extend_from_slice(byteArrayOf(6, 7))
        assertTrue(byteArrayOf(1, 2, 3, 4, 5, 6, 7).contentEquals(data.to_vec()))
    }

    @Test
    fun testExtend() {
        val data = NSMutableData.with_bytes(byteArrayOf(1, 2))
        data.extend(3..5)
        assertTrue(byteArrayOf(1, 2, 3, 4, 5).contentEquals(data.to_vec()))
        data.extend(NSData.with_bytes(byteArrayOf(6, 7)))
        assertTrue(byteArrayOf(1, 2, 3, 4, 5, 6, 7).contentEquals(data.to_vec()))
    }
}
