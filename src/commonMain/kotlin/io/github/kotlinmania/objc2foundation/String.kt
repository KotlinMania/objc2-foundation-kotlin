// port-lint: source string.rs
package io.github.kotlinmania.objc2foundation

/**
 * An immutable string of Unicode characters.
 */
open class NSString : NSObject, NSCopying, NSMutableCopying, Comparable<NSString> {

    open val rawString: String

    constructor() : super() {
        this.rawString = ""
    }

    constructor(string: String) : super() {
        this.rawString = string
    }

    companion object {
        fun new(): NSString = NSString("")

        fun from_str(string: String): NSString = initWithStr(string)

        fun fromStr(string: String): NSString = from_str(string)

        internal fun initWithStr(string: String): NSString {
            val processed = if (string.startsWith("\uFEFF")) {
                string.substring(1)
            } else {
                string
            }
            return NSString(processed)
        }
    }

    open fun length(): NSUInteger = rawString.length.toULong()

    open fun len(): Int = rawString.encodeToByteArray().size

    open fun len_utf16(): Int = rawString.length

    open fun is_empty(): Boolean = rawString.isEmpty()

    open fun isEmpty(): Boolean = rawString.isEmpty()

    open fun to_str(): String = rawString

    open fun to_str(pool: Any?): String = rawString

    open fun to_string(): String = rawString

    open fun characterAtIndex(index: NSUInteger): unichar =
        rawString[index.toInt()].code.toUShort()

    open fun substringFromIndex(from: NSUInteger): NSString =
        NSString(rawString.substring(from.toInt()))

    open fun substringToIndex(to: NSUInteger): NSString =
        NSString(rawString.substring(0, to.toInt()))

    open fun substringWithRange(range: NSRange): NSString =
        NSString(rawString.substring(range.location.toInt(), range.end().toInt()))

    open fun hasPrefix(prefix: NSString): Boolean = rawString.startsWith(prefix.rawString)

    open fun hasSuffix(suffix: NSString): Boolean = rawString.endsWith(suffix.rawString)

    open fun compare(other: NSString): NSComparisonResult =
        NSComparisonResult.from(rawString.compareTo(other.rawString))

    open fun stringByAppendingString(aString: NSString): NSString =
        NSString(rawString + aString.rawString)

    open fun stringByAppendingPathComponent(str: NSString): NSString {
        val comp = str.rawString
        if (rawString.isEmpty()) return NSString(comp)
        if (comp.isEmpty()) return this
        val base = if (rawString.endsWith("/") && rawString.length > 1) {
            rawString.trimEnd('/')
        } else {
            rawString
        }
        return if (base == "/") {
            NSString("/$comp")
        } else {
            NSString("$base/$comp")
        }
    }

    override fun copy(): NSString =
        if (this::class == NSString::class) this else NSString(rawString)

    override fun mutableCopy(): NSMutableString = NSMutableString(rawString)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSString) return false
        return rawString == other.rawString
    }

    override fun hashCode(): Int = rawString.hashCode()

    override fun compareTo(other: NSString): Int = rawString.compareTo(other.rawString)

    override fun toString(): String = rawString
}

/**
 * A mutable string of Unicode characters.
 */
open class NSMutableString : NSString {

    private val builder: StringBuilder

    override val rawString: String
        get() = builder.toString()

    constructor() : super("") {
        this.builder = StringBuilder()
    }

    constructor(initial: String) : super("") {
        this.builder = StringBuilder(initial)
    }

    constructor(builder: StringBuilder) : super("") {
        this.builder = builder
    }

    companion object {
        fun new(): NSMutableString = NSMutableString()

        fun from_str(string: String): NSMutableString {
            val processed = if (string.startsWith("\uFEFF")) {
                string.substring(1)
            } else {
                string
            }
            return NSMutableString(processed)
        }

        fun fromStr(string: String): NSMutableString = from_str(string)

        fun stringWithString(string: NSString): NSMutableString =
            NSMutableString(string.rawString)

        fun stringWithCapacity(capacity: Int): NSMutableString =
            NSMutableString(StringBuilder(capacity))

        fun stringWithCapacity(capacity: NSUInteger): NSMutableString =
            NSMutableString(StringBuilder(capacity.toInt()))
    }

    fun appendString(other: NSString) {
        builder.append(other.rawString)
    }

    operator fun plusAssign(other: NSString) {
        appendString(other)
    }

    fun setString(other: NSString) {
        builder.clear()
        builder.append(other.rawString)
    }

    fun insertStringAtIndex(str: NSString, loc: NSUInteger) {
        builder.insert(loc.toInt(), str.rawString)
    }

    fun deleteCharactersInRange(range: NSRange) {
        builder.delete(range.location.toInt(), range.end().toInt())
    }

    fun replaceCharactersInRangeWithString(range: NSRange, str: NSString) {
        builder.replace(range.location.toInt(), range.end().toInt(), str.rawString)
    }

    override fun copy(): NSString = NSString(rawString)

    override fun mutableCopy(): NSMutableString = NSMutableString(rawString)
}

fun ns_string(value: String): NSString = NSString.from_str(value)
