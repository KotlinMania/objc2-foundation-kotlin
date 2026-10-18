// port-lint: source comparison_result.rs
package io.github.kotlinmania.objc2foundation

/**
 * Constants that indicate sort order.
 */
enum class NSComparisonResult(val value: NSInteger) {
    Ascending(-1L),
    Same(0L),
    Descending(1L);

    companion object {
        const val ENCODING: String = "q"
        const val ENCODING_REF: String = "^q"

        fun default(): NSComparisonResult = Same

        fun from(ordering: Int): NSComparisonResult = when {
            ordering < 0 -> Ascending
            ordering > 0 -> Descending
            else -> Same
        }

        fun from(comparisonResult: NSComparisonResult): Int = when (comparisonResult) {
            Ascending -> -1
            Same -> 0
            Descending -> 1
        }
    }

    fun toInt(): Int = value.toInt()
}
