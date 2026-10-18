// port-lint: source array.rs
package io.github.kotlinmania.objc2foundation

/**
 * An immutable, ordered collection of objects.
 */
open class NSArray<T : Any> : NSObject, NSCopying, NSMutableCopying, Iterable<T> {

    protected open val list: List<T>

    constructor() : super() {
        this.list = emptyList()
    }

    constructor(elements: List<T>) : super() {
        this.list = elements.toList()
    }

    companion object {
        fun <T : Any> new(): NSArray<T> = NSArray()

        fun <T : Any> from_slice(slice: List<T>): NSArray<T> = NSArray(slice)

        fun <T : Any> fromSlice(slice: List<T>): NSArray<T> = from_slice(slice)

        fun <T : Any> from_retained_slice(slice: List<T>): NSArray<T> = NSArray(slice)

        fun <T : Any> fromRetainedSlice(slice: List<T>): NSArray<T> = from_retained_slice(slice)

        fun <T : Any> arrayWithObjects(vararg objects: T): NSArray<T> =
            NSArray(objects.toList())
    }

    open fun count(): NSUInteger = list.size.toULong()

    open fun len(): Int = list.size

    open fun is_empty(): Boolean = list.isEmpty()

    open fun isEmpty(): Boolean = list.isEmpty()

    open fun objectAtIndex(index: NSUInteger): T = list[index.toInt()]

    open fun objectAtIndex(index: Int): T = list[index]

    open fun firstObject(): T? = list.firstOrNull()

    open fun lastObject(): T? = list.lastOrNull()

    open fun to_vec(): List<T> = list.toList()

    open fun toVec(): List<T> = to_vec()

    open fun containsObject(anObject: T): Boolean = list.contains(anObject)

    open fun indexOfObject(anObject: T): NSUInteger {
        val idx = list.indexOf(anObject)
        return if (idx >= 0) idx.toULong() else NSNotFound.toULong()
    }

    open fun subarrayWithRange(range: NSRange): NSArray<T> {
        val start = range.location.toInt()
        val end = range.end().toInt()
        return NSArray(list.subList(start, end))
    }

    override fun iterator(): Iterator<T> = list.iterator()

    override fun copy(): NSArray<T> =
        if (this::class == NSArray::class) this else NSArray(list)

    override fun mutableCopy(): NSMutableArray<T> = NSMutableArray(list)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSArray<*>) return false
        return list == other.list
    }

    override fun hashCode(): Int = list.hashCode()

    override fun toString(): String =
        list.joinToString(prefix = "[", postfix = "]", separator = ", ")
}

/**
 * A mutable, ordered collection of objects.
 */
open class NSMutableArray<T : Any> : NSArray<T> {

    private val mutableList: MutableList<T>

    override val list: List<T>
        get() = mutableList

    constructor() : super() {
        this.mutableList = mutableListOf()
    }

    constructor(elements: List<T>) : super() {
        this.mutableList = elements.toMutableList()
    }

    constructor(initialCapacity: Int) : super() {
        this.mutableList = ArrayList(initialCapacity)
    }

    companion object {
        fun <T : Any> new(): NSMutableArray<T> = NSMutableArray()

        fun <T : Any> arrayWithCapacity(capacity: Int): NSMutableArray<T> =
            NSMutableArray(capacity)

        fun <T : Any> arrayWithCapacity(capacity: NSUInteger): NSMutableArray<T> =
            arrayWithCapacity(capacity.toInt())

        fun <T : Any> from_slice(slice: List<T>): NSMutableArray<T> =
            NSMutableArray(slice)

        fun <T : Any> fromSlice(slice: List<T>): NSMutableArray<T> = from_slice(slice)

        fun <T : Any> from_retained_slice(slice: List<T>): NSMutableArray<T> =
            NSMutableArray(slice)

        fun <T : Any> fromRetainedSlice(slice: List<T>): NSMutableArray<T> =
            from_retained_slice(slice)
    }

    fun addObject(anObject: T) {
        mutableList.add(anObject)
    }

    fun insertObjectAtIndex(anObject: T, index: NSUInteger) {
        mutableList.add(index.toInt(), anObject)
    }

    fun removeLastObject(): T? =
        if (mutableList.isNotEmpty()) mutableList.removeAt(mutableList.size - 1) else null

    fun removeObjectAtIndex(index: NSUInteger): T =
        mutableList.removeAt(index.toInt())

    fun replaceObjectAtIndexWithObject(index: NSUInteger, anObject: T): T =
        mutableList.set(index.toInt(), anObject)

    fun removeAllObjects() {
        mutableList.clear()
    }

    override fun copy(): NSArray<T> = NSArray(mutableList)

    override fun mutableCopy(): NSMutableArray<T> = NSMutableArray(mutableList)
}
