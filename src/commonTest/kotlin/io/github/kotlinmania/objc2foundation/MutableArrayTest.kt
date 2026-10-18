// port-lint: tests tests/mutable_array.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class MutableArrayTest {

    @Test
    fun testCreation() {
        val a1 = NSMutableArray.from_retained_slice(emptyList<NSNumber>())
        assertEquals(0, a1.len())
        val a2 = NSMutableArray.from_retained_slice(listOf(NSNumber.new_u8(4u), NSNumber.new_u8(2u)))
        assertEquals(2, a2.len())
    }

    @Test
    fun testContainingAnotherArray() {
        val array = NSMutableArray.from_retained_slice(listOf(NSMutableArray.new<Any>()))
        assertNotNull(array.objectAtIndex(0))
        assertNotNull(array.firstObject())
        assertNotNull(array.lastObject())
    }

    @Test
    fun testAllowedMutationWhileIterating() {
        val array = NSMutableArray.from_retained_slice(
            listOf(NSMutableString.new(), NSMutableString.new())
        )
        val toAdd = NSString.from_str("test")

        for (s in array) {
            s.appendString(toAdd)
        }

        assertEquals(toAdd, array.objectAtIndex(0))
        assertEquals(toAdd, array.objectAtIndex(1))
    }

    @Test
    fun testIterMutationDetection() {
        val array = NSMutableArray.from_retained_slice(listOf(NSObject(), NSObject()))
        assertFailsWith<ConcurrentModificationException> {
            for (item in array) {
                array.addObject(NSObject())
            }
        }
    }

    @Test
    fun testToVec() {
        val array = NSMutableArray.from_retained_slice(listOf(NSString.new()))
        val vec = array.to_vec()
        assertEquals(1, vec.size)
    }
}
