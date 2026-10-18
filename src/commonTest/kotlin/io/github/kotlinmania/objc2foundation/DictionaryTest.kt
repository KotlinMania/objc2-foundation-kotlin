// port-lint: tests tests/dictionary.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class DictionaryTest {

    private fun sampleDict(key: String): NSDictionary<NSString, NSObject> {
        val string = NSString.from_str(key)
        val obj = NSObject()
        return NSDictionary.from_slices(listOf(string), listOf(obj))
    }

    @Test
    fun testLen() {
        val dict = sampleDict("abcd")
        assertEquals(1, dict.len())
    }

    @Test
    fun testGet() {
        val dict = sampleDict("abcd")
        assertNotNull(dict.objectForKey(ns_string("abcd")))
        assertNull(dict.objectForKey(ns_string("abcde")))
    }

    @Test
    fun testKeysAndObjects() {
        val dict = sampleDict("abcd")
        val (keys, objs) = dict.to_vecs()

        assertEquals(1, keys.size)
        assertEquals(1, objs.size)
        assertEquals("abcd", keys[0].toString())
        assertEquals(objs[0], dict.objectForKey(keys[0]))
    }

    @Test
    fun testArrays() {
        val dict = sampleDict("abcd")
        val keys = dict.allKeys()
        assertEquals(1, keys.len())
        assertEquals("abcd", keys.objectAtIndex(0).toString())

        val objs = dict.allValues()
        assertEquals(1, objs.len())
    }

    @Test
    fun newDifferentLengths() {
        assertFailsWith<IllegalArgumentException> {
            NSDictionary.from_slices(
                listOf(ns_string("a"), ns_string("b"), ns_string("c")),
                listOf(NSObject(), NSObject()),
            )
        }
    }
}
