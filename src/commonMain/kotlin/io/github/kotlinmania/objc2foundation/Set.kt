// port-lint: source set.rs
package io.github.kotlinmania.objc2foundation

/**
 * An immutable, unordered collection of unique objects.
 */
open class NSSet<T : Any> : NSObject, NSCopying, NSMutableCopying, Iterable<T> {

    protected open val set: Set<T>

    constructor() : super() {
        this.set = emptySet()
    }

    constructor(elements: Collection<T>) : super() {
        this.set = elements.toSet()
    }

    companion object {
        fun <T : Any> new(): NSSet<T> = NSSet()

        fun <T : Any> from_slice(slice: List<T>): NSSet<T> = NSSet(slice)

        fun <T : Any> fromSlice(slice: List<T>): NSSet<T> = from_slice(slice)

        fun <T : Any> from_retained_slice(slice: List<T>): NSSet<T> = NSSet(slice)

        fun <T : Any> fromRetainedSlice(slice: List<T>): NSSet<T> = from_retained_slice(slice)

        fun <T : Any> setWithObjects(vararg objects: T): NSSet<T> =
            NSSet(objects.toList())
    }

    open fun count(): NSUInteger = set.size.toULong()

    open fun len(): Int = set.size

    open fun is_empty(): Boolean = set.isEmpty()

    open fun isEmpty(): Boolean = set.isEmpty()

    open fun member(anObject: T): T? = set.firstOrNull { it == anObject }

    open fun anyObject(): T? = set.firstOrNull()

    open fun containsObject(anObject: T): Boolean = set.contains(anObject)

    open fun allObjects(): NSArray<T> = NSArray(set.toList())

    override fun iterator(): Iterator<T> = set.iterator()

    override fun copy(): NSSet<T> =
        if (this::class == NSSet::class) this else NSSet(set)

    override fun mutableCopy(): NSMutableSet<T> = NSMutableSet(set)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSSet<*>) return false
        return set == other.set
    }

    override fun hashCode(): Int = set.hashCode()

    override fun toString(): String =
        set.joinToString(prefix = "{(", postfix = ")}", separator = ", ")
}

/**
 * A mutable, unordered collection of unique objects.
 */
open class NSMutableSet<T : Any> : NSSet<T> {

    private val mutableSet: MutableSet<T>

    override val set: Set<T>
        get() = mutableSet

    constructor() : super() {
        this.mutableSet = mutableSetOf()
    }

    constructor(elements: Collection<T>) : super() {
        this.mutableSet = elements.toMutableSet()
    }

    constructor(initialCapacity: Int) : super() {
        this.mutableSet = LinkedHashSet(initialCapacity)
    }

    companion object {
        fun <T : Any> new(): NSMutableSet<T> = NSMutableSet()

        fun <T : Any> setWithCapacity(capacity: Int): NSMutableSet<T> =
            NSMutableSet(capacity)

        fun <T : Any> setWithCapacity(capacity: NSUInteger): NSMutableSet<T> =
            setWithCapacity(capacity.toInt())
    }

    fun addObject(anObject: T) {
        mutableSet.add(anObject)
    }

    fun removeObject(anObject: T) {
        mutableSet.remove(anObject)
    }

    fun removeAllObjects() {
        mutableSet.clear()
    }

    override fun copy(): NSSet<T> = NSSet(mutableSet)

    override fun mutableCopy(): NSMutableSet<T> = NSMutableSet(mutableSet)
}
