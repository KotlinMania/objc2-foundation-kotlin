// port-lint: tests comparison_result.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals

class ComparisonResultTest {
    @Test
    fun testDefault() {
        assertEquals(NSComparisonResult.Same, NSComparisonResult.default())
    }

    @Test
    fun testFromOrdering() {
        assertEquals(NSComparisonResult.Ascending, NSComparisonResult.from(-1))
        assertEquals(NSComparisonResult.Same, NSComparisonResult.from(0))
        assertEquals(NSComparisonResult.Descending, NSComparisonResult.from(1))

        assertEquals(-1, NSComparisonResult.from(NSComparisonResult.Ascending))
        assertEquals(0, NSComparisonResult.from(NSComparisonResult.Same))
        assertEquals(1, NSComparisonResult.from(NSComparisonResult.Descending))
    }
}
