// port-lint: tests tests/mutable_dictionary.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MutableDictionaryTest {
    private fun sampleDict(): NSMutableDictionary<NSNumber, NSObject> {
        val keys = listOf(NSNumber.new_i32(1), NSNumber.new_i32(2), NSNumber.new_i32(3))
        val objs = listOf(NSObject(), NSObject(), NSObject())
        return NSMutableDictionary(keys.zip(objs).toMap())
    }

    @Test
    fun testNew() {
        val dict = NSMutableDictionary.new<NSObject, NSObject>()
        assertTrue(dict.is_empty())
    }

    @Test
    fun testGet() {
        val dict = sampleDict()
        assertNotNull(dict.objectForKey(NSNumber.new_i32(1)))
        assertNotNull(dict.objectForKey(NSNumber.new_i32(2)))
        assertNull(dict.objectForKey(NSNumber.new_i32(4)))
    }

    @Test
    fun testToVecs() {
        val dict = sampleDict()
        val (keys, objects) = dict.to_vecs()
        assertEquals(3, keys.size)
        assertEquals(3, objects.size)
    }

    @Test
    fun testInsert() {
        val dict = NSMutableDictionary.new<NSNumber, NSObject>()
        dict.insert(NSNumber.new_i32(1), NSObject())
        dict.insert(NSNumber.new_i32(2), NSObject())
        dict.insert(NSNumber.new_i32(3), NSObject())
        dict.insert(NSNumber.new_i32(1), NSObject())
        assertEquals(3, dict.len())
    }

    @Test
    fun testRemove() {
        val dict = sampleDict()
        assertEquals(3, dict.len())
        dict.removeObjectForKey(NSNumber.new_i32(1))
        dict.removeObjectForKey(NSNumber.new_i32(2))
        dict.removeObjectForKey(NSNumber.new_i32(1))
        dict.removeObjectForKey(NSNumber.new_i32(4))
        assertEquals(1, dict.len())
    }

    @Test
    fun testClear() {
        val dict = sampleDict()
        assertEquals(3, dict.len())
        dict.removeAllObjects()
        assertTrue(dict.is_empty())
    }
}
