// port-lint: source fast_enumeration_state.rs
package io.github.kotlinmania.objc2foundation

/**
 * State structure used during fast enumeration.
 */
data class NSFastEnumerationState(
    var state: ULong = 0uL,
    var itemsPtr: Long = 0L,
    var mutationsPtr: Long = 0L,
    val extra: ULongArray = ULongArray(5),
) {
    companion object {
        const val ENCODING: String = "{?=L^@^L[5L]}"
        const val ENCODING_REF: String = "^{?=L^@^L[5L]}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSFastEnumerationState) return false

        if (state != other.state) return false
        if (itemsPtr != other.itemsPtr) return false
        if (mutationsPtr != other.mutationsPtr) return false
        if (!extra.contentEquals(other.extra)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = state.hashCode()
        result = 31 * result + itemsPtr.hashCode()
        result = 31 * result + mutationsPtr.hashCode()
        result = 31 * result + extra.contentHashCode()
        return result
    }
}
