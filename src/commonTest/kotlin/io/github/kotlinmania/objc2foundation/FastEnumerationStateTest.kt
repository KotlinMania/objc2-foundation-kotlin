// port-lint: tests fast_enumeration_state.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class FastEnumerationStateTest {
    @Test
    fun testDefault() {
        val s1 = NSFastEnumerationState()
        val s2 = NSFastEnumerationState()
        assertEquals(s1, s2)
        assertEquals(s1.hashCode(), s2.hashCode())

        s1.state = 42uL
        assertNotEquals(s1, s2)
    }
}
