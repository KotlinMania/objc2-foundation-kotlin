// port-lint: source generated/NSData.rs
package io.github.kotlinmania.objc2foundation

/**
 * Options for data reading.
 */
data class NSDataReadingOptions(val rawValue: NSUInteger) {
    companion object {
        val MappedIfSafe = NSDataReadingOptions(1uL)
        val Uncached = NSDataReadingOptions(2uL)
        val MappedAlways = NSDataReadingOptions(8uL)
    }
}

/**
 * Options for data writing.
 */
data class NSDataWritingOptions(val rawValue: NSUInteger) {
    companion object {
        val Atomic = NSDataWritingOptions(1uL)
        val WithoutOverwriting = NSDataWritingOptions(2uL)
        val FileProtectionNone = NSDataWritingOptions(0x10000000uL)
        val FileProtectionComplete = NSDataWritingOptions(0x20000000uL)
    }
}

/**
 * Options for searching data.
 */
data class NSDataSearchOptions(val rawValue: NSUInteger) {
    companion object {
        val Backwards = NSDataSearchOptions(1uL)
        val Anchored = NSDataSearchOptions(2uL)
    }
}

/**
 * Base64 encoding options.
 */
data class NSDataBase64EncodingOptions(val rawValue: NSUInteger) {
    companion object {
        val Encoding64CharacterLineLength = NSDataBase64EncodingOptions(1uL)
        val Encoding76CharacterLineLength = NSDataBase64EncodingOptions(2uL)
        val EncodingEndLineWithCarriageReturn = NSDataBase64EncodingOptions(16uL)
        val EncodingEndLineWithLineFeed = NSDataBase64EncodingOptions(32uL)
    }
}

/**
 * Base64 decoding options.
 */
data class NSDataBase64DecodingOptions(val rawValue: NSUInteger) {
    companion object {
        val IgnoreUnknownCharacters = NSDataBase64DecodingOptions(1uL)
    }
}
