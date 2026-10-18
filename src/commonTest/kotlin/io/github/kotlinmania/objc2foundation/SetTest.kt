// port-lint: tests tests/set.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class SetTest {

    @Test
    fun testNew() {
        val set = NSSet.new<NSObject>()
        assertTrue(set.is_empty())
    }

    @Test
    fun testFromRetainedSlice() {
        val set = NSSet.from_retained_slice(emptyList<NSObject>())
        assertTrue(set.is_empty())

        val strs = listOf("one", "two", "three").map { NSString.from_str(it) }
        val stringSet = NSSet.from_retained_slice(strs)
        assertTrue(strs.all { stringSet.containsObject(it) })

        val nums = listOf(1, 2, 3).map { NSNumber.new_i32(it) }
        val numSet = NSSet.from_retained_slice(nums)
        assertTrue(nums.all { numSet.containsObject(it) })
    }

    @Test
    fun testFromSlice() {
        val set = NSSet.from_slice(emptyList<NSString>())
        assertTrue(set.is_empty())

        val strs = listOf(ns_string("one"), ns_string("two"), ns_string("three"))
        val stringSet = NSSet.from_slice(strs)
        assertTrue(strs.all { stringSet.containsObject(it) })
    }

    @Test
    fun testLen() {
        val set = NSSet.new<NSObject>()
        assertTrue(set.is_empty())

        val set2 = NSSet.from_slice(listOf(ns_string("one"), ns_string("two"), ns_string("two")))
        assertEquals(2, set2.len())

        val set3 = NSSet.from_retained_slice(
            listOf(NSNumber.new_i32(1), NSNumber.new_i32(2), NSNumber.new_i32(3))
        )
        assertEquals(3, set3.len())
    }

    @Test
    fun testGet() {
        val set = NSSet.new<NSString>()
        assertNull(set.member(ns_string("one")))

        val set2 = NSSet.from_slice(listOf(ns_string("one"), ns_string("two"), ns_string("two")))
        assertNotNull(set2.member(ns_string("two")))
        assertNull(set2.member(ns_string("three")))
    }

    @Test
    fun testGetAny() {
        val set = NSSet.new<NSObject>()
        assertNull(set.anyObject())

        val strs = listOf(ns_string("one"), ns_string("two"), ns_string("three"))
        val set2 = NSSet.from_slice(strs)
        val any = set2.anyObject()
        assertNotNull(any)
        assertTrue(strs.contains(any))
    }

    @Test
    fun testContains() {
        val set = NSSet.new<NSString>()
        assertFalse(set.containsObject(ns_string("one")))

        val set2 = NSSet.from_slice(listOf(ns_string("one"), ns_string("two"), ns_string("two")))
        assertTrue(set2.containsObject(ns_string("one")))
        assertFalse(set2.containsObject(ns_string("three")))
    }
}
