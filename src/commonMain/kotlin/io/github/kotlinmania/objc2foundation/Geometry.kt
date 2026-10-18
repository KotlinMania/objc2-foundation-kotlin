// port-lint: source geometry.rs
package io.github.kotlinmania.objc2foundation

data class CGPoint(
    val x: CGFloat = 0.0,
    val y: CGFloat = 0.0,
) {
    companion object {
        const val ENCODING: String = "{CGPoint=dd}"
        const val ENCODING_REF: String = "^{CGPoint=dd}"

        fun new(x: CGFloat, y: CGFloat): CGPoint = CGPoint(x, y)
    }
}

typealias NSPoint = CGPoint

data class CGSize(
    val width: CGFloat = 0.0,
    val height: CGFloat = 0.0,
) {
    companion object {
        const val ENCODING: String = "{CGSize=dd}"
        const val ENCODING_REF: String = "^{CGSize=dd}"

        fun new(width: CGFloat, height: CGFloat): CGSize = CGSize(width, height)
    }
}

typealias NSSize = CGSize

data class CGRect(
    val origin: CGPoint = CGPoint(),
    val size: CGSize = CGSize(),
) {
    companion object {
        const val ENCODING: String = "{CGRect={CGPoint=dd}{CGSize=dd}}"
        const val ENCODING_REF: String = "^{CGRect={CGPoint=dd}{CGSize=dd}}"

        fun new(origin: CGPoint, size: CGSize): CGRect = CGRect(origin, size)

        fun new(x: CGFloat, y: CGFloat, width: CGFloat, height: CGFloat): CGRect =
            CGRect(CGPoint(x, y), CGSize(width, height))
    }
}

typealias NSRect = CGRect

data class NSRectEdge(val rawValue: NSUInteger) {
    companion object {
        const val ENCODING: String = "Q"
        const val ENCODING_REF: String = "^Q"

        val MinX: NSRectEdge = NSRectEdge(0uL)
        val MinY: NSRectEdge = NSRectEdge(1uL)
        val MaxX: NSRectEdge = NSRectEdge(2uL)
        val MaxY: NSRectEdge = NSRectEdge(3uL)

        val NSMinXEdge: NSRectEdge = MinX
        val NSMinYEdge: NSRectEdge = MinY
        val NSMaxXEdge: NSRectEdge = MaxX
        val NSMaxYEdge: NSRectEdge = MaxY
    }
}

fun NSEqualPoints(a: NSPoint, b: NSPoint): Boolean = a.x == b.x && a.y == b.y

fun NSEqualSizes(a: NSSize, b: NSSize): Boolean = a.width == b.width && a.height == b.height

fun NSEqualRects(a: NSRect, b: NSRect): Boolean =
    NSEqualPoints(a.origin, b.origin) && NSEqualSizes(a.size, b.size)

fun NSMakePoint(x: CGFloat, y: CGFloat): NSPoint = NSPoint(x, y)

fun NSMakeSize(w: CGFloat, h: CGFloat): NSSize = NSSize(w, h)

fun NSMakeRect(x: CGFloat, y: CGFloat, w: CGFloat, h: CGFloat): NSRect =
    NSRect(NSPoint(x, y), NSSize(w, h))
