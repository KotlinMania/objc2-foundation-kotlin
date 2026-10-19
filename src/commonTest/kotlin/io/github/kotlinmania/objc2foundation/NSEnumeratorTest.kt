// port-lint: tests enumerator.rs
package io.github.kotlinmania.objc2foundation

import io.github.kotlinmania.objc2foundation.generated.NSEnumerator
import io.github.kotlinmania.objc2foundation.generated.NSFastEnumeration
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class NSEnumeratorTest {
    @Test
    fun testEmptyEnumerator() {
        val enumerator = NSEnumerator.new<NSNumber>()
        assertNull(enumerator.nextObject())
        val all = enumerator.allObjects()
        assertEquals(0, all.len())
    }

    @Test
    fun testEnumeratorWithItems() {
        val numbers = listOf(NSNumber.new_i32(1), NSNumber.new_i32(2), NSNumber.new_i32(3))
        val enumerator = NSEnumerator.fromList(numbers)

        val first = enumerator.nextObject()
        assertNotNull(first)
        assertEquals(1, first.as_i32())

        val rest = enumerator.allObjects()
        assertEquals(2, rest.len())
        assertEquals(2, rest.objectAtIndex(0).as_i32())
        assertEquals(3, rest.objectAtIndex(1).as_i32())

        // Exhausted
        assertNull(enumerator.nextObject())
        assertEquals(0, enumerator.allObjects().len())
    }

    @Test
    fun testIterableConformance() {
        val numbers = listOf(NSNumber.new_i32(10), NSNumber.new_i32(20), NSNumber.new_i32(30))
        val enumerator = NSEnumerator.fromList(numbers)

        val collected = mutableListOf<Int>()
        for (num in enumerator) {
            collected.add(num.as_i32())
        }
        assertEquals(listOf(10, 20, 30), collected)
    }

    @Test
    fun testFastEnumeration() {
        val numbers = listOf(NSNumber.new_i32(100), NSNumber.new_i32(200))
        val enumerator: NSFastEnumeration = NSEnumerator.fromList(numbers)

        val state = NSFastEnumerationState()
        val buffer = arrayOfNulls<Any>(4)
        val count = enumerator.countByEnumeratingWithState_objects_count(state, buffer, 4uL)

        assertEquals(2uL, count)
        assertEquals(100, (buffer[0] as NSNumber).as_i32())
        assertEquals(200, (buffer[1] as NSNumber).as_i32())

        // Second call should return 0
        val count2 = enumerator.countByEnumeratingWithState_objects_count(state, buffer, 4uL)
        assertEquals(0uL, count2)
    }
}
