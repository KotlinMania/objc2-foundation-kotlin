// port-lint: tests tests/error.rs
package io.github.kotlinmania.objc2foundation

import io.github.kotlinmania.objc2foundation.generated.NSCocoaErrorDomain
import kotlin.test.Test
import kotlin.test.assertEquals

class ErrorTest {
    @Test
    fun basic() {
        val error = NSError.new(-999L, NSCocoaErrorDomain)
        val expected = "NSCocoaErrorDomain -999"
        assertEquals(expected, error.toString())
    }

    @Test
    fun custom_domain() {
        val error = NSError.new(42L, ns_string("MyDomain"))
        assertEquals(42L, error.code())
        assertEquals(ns_string("MyDomain"), error.domain())
        val expected = "MyDomain 42"
        assertEquals(expected, error.toString())
    }
}
