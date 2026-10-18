// port-lint: source data.rs
package io.github.kotlinmania.objc2foundation

/**
 * An immutable buffer of bytes.
 */
open class NSData :
    NSObject,
    NSCopying,
    NSMutableCopying {
    private val fixedBytes: ByteArray

    protected open val internalBytes: ByteArray
        get() = fixedBytes

    constructor() : super() {
        this.fixedBytes = ByteArray(0)
    }

    constructor(bytes: ByteArray) : super() {
        this.fixedBytes = bytes.copyOf()
    }

    companion object {
        fun new(): NSData = NSData()

        fun with_bytes(bytes: ByteArray): NSData = NSData(bytes)

        fun withBytes(bytes: ByteArray): NSData = with_bytes(bytes)

        fun from_vec(bytes: ByteArray): NSData = NSData(bytes)

        fun fromVec(bytes: ByteArray): NSData = from_vec(bytes)
    }

    open fun length(): NSUInteger = internalBytes.size.toULong()

    open fun len(): Int = internalBytes.size

    open fun is_empty(): Boolean = internalBytes.isEmpty()

    open fun isEmpty(): Boolean = internalBytes.isEmpty()

    open fun to_vec(): ByteArray = internalBytes.copyOf()

    open fun toVec(): ByteArray = to_vec()

    open fun as_bytes_unchecked(): ByteArray = internalBytes

    open fun subdataWithRange(range: NSRange): NSData {
        val start = range.location.toInt()
        val end = range.end().toInt()
        return NSData(internalBytes.copyOfRange(start, end))
    }

    override fun copy(): NSData =
        if (this::class == NSData::class) this else NSData(internalBytes)

    override fun mutableCopy(): NSMutableData = NSMutableData(internalBytes)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSData) return false
        return internalBytes.contentEquals(other.internalBytes)
    }

    override fun hashCode(): Int = internalBytes.contentHashCode()

    override fun toString(): String =
        internalBytes.joinToString(prefix = "[", postfix = "]", separator = ", ")
}

/**
 * A mutable buffer of bytes.
 */
open class NSMutableData : NSData {
    private var buffer: ByteArray
    private var size: Int

    override val internalBytes: ByteArray
        get() = buffer.copyOf(size)

    constructor() : super() {
        this.buffer = ByteArray(0)
        this.size = 0
    }

    constructor(bytes: ByteArray) : super() {
        this.buffer = bytes.copyOf()
        this.size = bytes.size
    }

    constructor(initialCapacity: Int) : super() {
        this.buffer = ByteArray(initialCapacity)
        this.size = 0
    }

    companion object {
        fun new(): NSMutableData = NSMutableData()

        fun with_bytes(bytes: ByteArray): NSMutableData = NSMutableData(bytes)

        fun withBytes(bytes: ByteArray): NSMutableData = with_bytes(bytes)

        fun from_vec(bytes: ByteArray): NSMutableData = NSMutableData(bytes)

        fun fromVec(bytes: ByteArray): NSMutableData = from_vec(bytes)

        fun dataWithData(data: NSData): NSMutableData = NSMutableData(data.to_vec())

        fun dataWithCapacity(capacity: Int): NSMutableData? {
            if (capacity < 0) return null
            return NSMutableData(capacity)
        }

        fun dataWithCapacity(capacity: NSUInteger): NSMutableData? =
            dataWithCapacity(capacity.toInt())
    }

    override fun length(): NSUInteger = size.toULong()

    override fun len(): Int = size

    override fun is_empty(): Boolean = size == 0

    override fun isEmpty(): Boolean = size == 0

    override fun to_vec(): ByteArray = buffer.copyOf(size)

    override fun toVec(): ByteArray = to_vec()

    fun as_mut_bytes_unchecked(): ByteArray = buffer

    private fun ensureCapacity(minCapacity: Int) {
        if (minCapacity > buffer.size) {
            val newCapacity = maxOf(minCapacity, buffer.size * 2 + 1)
            buffer = buffer.copyOf(newCapacity)
        }
    }

    fun setLength(newLength: Int) {
        require(newLength >= 0)
        if (newLength > size) {
            ensureCapacity(newLength)
            buffer.fill(0, size, newLength)
        }
        size = newLength
    }

    fun setLength(newLength: NSUInteger) = setLength(newLength.toInt())

    fun extend_from_slice(bytes: ByteArray) {
        ensureCapacity(size + bytes.size)
        bytes.copyInto(buffer, destinationOffset = size)
        size += bytes.size
    }

    fun appendBytes(bytes: ByteArray) = extend_from_slice(bytes)

    fun appendData(other: NSData) = extend_from_slice(other.to_vec())

    fun extend(range: IntRange) {
        val count = if (range.isEmpty()) 0 else (range.last - range.first + 1)
        ensureCapacity(size + count)
        var i = size
        for (v in range) {
            buffer[i++] = v.toByte()
        }
        size += count
    }

    fun extend(data: NSData) = extend_from_slice(data.to_vec())

    fun replace_range(range: LongRange, bytes: ByteArray) {
        val start = range.first.toInt()
        val removeCount = if (range.isEmpty()) 0 else (range.last - range.first + 1).toInt()
        val insertCount = bytes.size
        val newSize = size - removeCount + insertCount
        ensureCapacity(newSize)
        val tailStart = start + removeCount
        val tailLength = size - tailStart
        if (tailLength > 0) {
            buffer.copyInto(buffer, destinationOffset = start + insertCount, startIndex = tailStart, endIndex = size)
        }
        bytes.copyInto(buffer, destinationOffset = start)
        size = newSize
    }

    fun replace_range(range: IntRange, bytes: ByteArray) =
        replace_range(range.first.toLong()..range.last.toLong(), bytes)

    fun set_bytes(bytes: ByteArray) {
        this.buffer = bytes.copyOf()
        this.size = bytes.size
    }

    override fun copy(): NSData = NSData(to_vec())

    override fun mutableCopy(): NSMutableData = NSMutableData(to_vec())
}
