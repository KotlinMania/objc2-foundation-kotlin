// port-lint: tests tests/array.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ArrayTest {

    private fun sampleArray(len: Int): NSArray<NSObject> {
        val list = List(len) { NSObject() }
        return NSArray.from_slice(list)
    }

    private fun sampleNumberArray(len: Int): NSArray<NSNumber> {
        val list = List(len) { NSNumber.new_u8(it.toUByte()) }
        return NSArray.from_slice(list)
    }

    @Test
    fun testTwoEmpty() {
        val a1 = NSArray.new<NSObject>()
        val a2 = NSArray.new<NSObject>()
        assertEquals(0, a1.len())
        assertEquals(0, a2.len())
    }

    @Test
    fun testCreation() {
        val a1 = NSArray.from_slice(emptyList<NSNumber>())
        assertEquals(0, a1.len())
        val a2 = NSArray.from_slice(listOf(NSNumber.new_u8(4u), NSNumber.new_u8(2u)))
        assertEquals(2, a2.len())
    }

    @Test
    fun testLen() {
        val emptyArray = NSArray.new<NSObject>()
        assertEquals(0, emptyArray.len())

        val array = sampleArray(4)
        assertEquals(4, array.len())
    }

    @Test
    fun testEquality() {
        val array1 = sampleArray(3)
        val array2 = sampleArray(3)
        assertNotEquals(array1, array2)

        val n1 = sampleNumberArray(3)
        val n2 = sampleNumberArray(3)
        assertEquals(n1, n2)

        val n3 = sampleNumberArray(4)
        assertNotEquals(n1, n3)
    }

    @Test
    fun testDebug() {
        val obj0 = sampleNumberArray(0)
        assertEquals("[]", obj0.toString())
        val obj3 = sampleNumberArray(3)
        assertEquals("[0, 1, 2]", obj3.toString())
    }

    @Test
    fun testGet() {
        val array = sampleArray(4)
        assertNotEquals(array.objectAtIndex(0), array.objectAtIndex(3))
        assertEquals(array.firstObject(), array.objectAtIndex(0))
        assertEquals(array.lastObject(), array.objectAtIndex(3))

        val emptyArray = NSArray.new<NSObject>()
        assertNull(emptyArray.firstObject())
        assertNull(emptyArray.lastObject())
    }

    @Test
    fun testIter() {
        val array = sampleNumberArray(4)
        val vec1 = array.to_vec()
        val vec2 = array.toList()
        assertEquals(vec1, vec2)

        var count = 0
        for (item in array) {
            count++
        }
        assertEquals(4, count)
    }
}
