// port-lint: tests geometry.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals

class GeometryTest {

    @Test
    fun testPartialEq() {
        val epsilon = 2.220446049250313e-16
        val minPositive = Double.MIN_VALUE

        val cases = listOf(
            0.0 to 0.0,
            -0.0 to -0.0,
            0.0 to -0.0,
            1.0 to (1.0 + epsilon),
            0.0 to minPositive,
            0.0 to epsilon,
            1.0 to 1.0,
            1.0 to -1.0,
            Double.POSITIVE_INFINITY to Double.POSITIVE_INFINITY,
            Double.POSITIVE_INFINITY to Double.NEGATIVE_INFINITY,
            Double.NEGATIVE_INFINITY to Double.NEGATIVE_INFINITY,
            Double.NaN to 0.0,
            Double.NaN to 1.0,
            Double.NaN to Double.NaN,
            Double.NaN to -Double.NaN,
            -Double.NaN to -Double.NaN,
            Double.NaN to Double.POSITIVE_INFINITY,
        )

        for (case in cases) {
            val pointA = NSPoint.new(case.first, case.second)
            val pointB = NSPoint.new(case.first, case.second)
            val actual = NSEqualPoints(pointA, pointB)
            assertEquals(pointA == pointB, actual)

            if (case.first >= 0.0 && case.second >= 0.0) {
                val sizeA = NSSize.new(case.first, case.second)
                val sizeB = NSSize.new(case.first, case.second)
                val sizeActual = NSEqualSizes(sizeA, sizeB)
                assertEquals(sizeA == sizeB, sizeActual)

                val rectA = NSRect.new(pointA, sizeA)
                val rectB = NSRect.new(pointB, sizeB)
                val rectActual = NSEqualRects(rectA, rectB)
                assertEquals(rectA == rectB, rectActual)
            }
        }
    }
}
