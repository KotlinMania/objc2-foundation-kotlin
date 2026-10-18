// port-lint: source data.rs
package io.github.kotlinmania.objc2foundation

/**
 * An immutable buffer of bytes.
 */
open class NSData : NSObject, NSCopying, NSMutableCopying {

    protected open val internalBytes: ByteArray

    constructor() : super() {
        this.internalBytes = ByteArray(0)
    }

    constructor(bytes: ByteArray) : super() {
        this.internalBytes = bytes.copyOf()
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

    private val mutableList: MutableList<Byte>

    override val internalBytes: ByteArray
        get() = mutableList.toByteArray()

    constructor() : super() {
        this.mutableList = mutableListOf()
    }

    constructor(bytes: ByteArray) : super() {
        this.mutableList = bytes.toMutableList()
    }

    constructor(initialCapacity: Int) : super() {
        this.mutableList = ArrayList(initialCapacity)
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

    override fun length(): NSUInteger = mutableList.size.toULong()

    override fun len(): Int = mutableList.size

    override fun is_empty(): Boolean = mutableList.isEmpty()

    override fun isEmpty(): Boolean = mutableList.isEmpty()

    override fun to_vec(): ByteArray = mutableList.toByteArray()

    override fun toVec(): ByteArray = to_vec()

    fun as_mut_bytes_unchecked(): MutableList<Byte> = mutableList

    fun setLength(newLength: Int) {
        if (newLength < mutableList.size) {
            while (mutableList.size > newLength) {
                mutableList.removeAt(mutableList.size - 1)
            }
        } else {
            while (mutableList.size < newLength) {
                mutableList.add(0)
            }
        }
    }

    fun setLength(newLength: NSUInteger) = setLength(newLength.toInt())

    fun extend_from_slice(bytes: ByteArray) {
        for (b in bytes) {
            mutableList.add(b)
        }
    }

    fun appendBytes(bytes: ByteArray) = extend_from_slice(bytes)

    fun appendData(other: NSData) = extend_from_slice(other.to_vec())

    fun extend(range: IntRange) {
        for (i in range) {
            mutableList.add(i.toByte())
        }
    }

    fun extend(data: NSData) = extend_from_slice(data.to_vec())

    fun replace_range(range: LongRange, bytes: ByteArray) {
        val start = range.first.toInt()
        val count = if (range.isEmpty()) 0 else (range.last - range.first + 1).toInt()
        repeat(count) {
            if (start < mutableList.size) {
                mutableList.removeAt(start)
            }
        }
        var insertIdx = start
        for (b in bytes) {
            mutableList.add(insertIdx++, b)
        }
    }

    fun replace_range(range: IntRange, bytes: ByteArray) =
        replace_range(range.first.toLong()..range.last.toLong(), bytes)

    fun set_bytes(bytes: ByteArray) {
        mutableList.clear()
        for (b in bytes) {
            mutableList.add(b)
        }
    }

    override fun copy(): NSData = NSData(mutableList.toByteArray())

    override fun mutableCopy(): NSMutableData = NSMutableData(mutableList.toByteArray())
}
