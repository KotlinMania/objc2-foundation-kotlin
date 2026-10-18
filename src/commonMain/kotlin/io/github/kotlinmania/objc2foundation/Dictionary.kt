// port-lint: source dictionary.rs
package io.github.kotlinmania.objc2foundation

/**
 * An immutable collection of key-value pairs.
 */
open class NSDictionary<K : Any, V : Any> : NSObject, NSCopying, NSMutableCopying {

    protected open val map: Map<K, V>

    constructor() : super() {
        this.map = emptyMap()
    }

    constructor(entries: Map<K, V>) : super() {
        this.map = entries.toMap()
    }

    companion object {
        fun <K : Any, V : Any> new(): NSDictionary<K, V> = NSDictionary()

        fun <K : Any, V : Any> from_slices(keys: List<K>, objects: List<V>): NSDictionary<K, V> {
            require(keys.size == objects.size) { "key slice and object slice should have the same length" }
            val m = LinkedHashMap<K, V>(keys.size)
            for (i in keys.indices) {
                m[keys[i]] = objects[i]
            }
            return NSDictionary(m)
        }

        fun <K : Any, V : Any> fromSlices(keys: List<K>, objects: List<V>): NSDictionary<K, V> =
            from_slices(keys, objects)

        fun <K : Any, V : Any> dictionaryWithObjectsForKeys(objects: List<V>, keys: List<K>): NSDictionary<K, V> =
            from_slices(keys, objects)
    }

    open fun count(): NSUInteger = map.size.toULong()

    open fun len(): Int = map.size

    open fun is_empty(): Boolean = map.isEmpty()

    open fun isEmpty(): Boolean = map.isEmpty()

    open fun objectForKey(aKey: K): V? = map[aKey]

    open fun to_vecs(): Pair<List<K>, List<V>> =
        Pair(map.keys.toList(), map.values.toList())

    open fun keys(): Iterator<K> = map.keys.iterator()

    open fun objects(): Iterator<V> = map.values.iterator()

    open fun allKeys(): NSArray<K> = NSArray(map.keys.toList())

    open fun allValues(): NSArray<V> = NSArray(map.values.toList())

    override fun copy(): NSDictionary<K, V> =
        if (this::class == NSDictionary::class) this else NSDictionary(map)

    override fun mutableCopy(): NSMutableDictionary<K, V> = NSMutableDictionary(map)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSDictionary<*, *>) return false
        return map == other.map
    }

    override fun hashCode(): Int = map.hashCode()

    override fun toString(): String = map.toString()
}

/**
 * A mutable collection of key-value pairs.
 */
open class NSMutableDictionary<K : Any, V : Any> : NSDictionary<K, V> {

    private val mutableMap: MutableMap<K, V>

    override val map: Map<K, V>
        get() = mutableMap

    constructor() : super() {
        this.mutableMap = mutableMapOf()
    }

    constructor(entries: Map<K, V>) : super() {
        this.mutableMap = entries.toMutableMap()
    }

    constructor(initialCapacity: Int) : super() {
        this.mutableMap = LinkedHashMap(initialCapacity)
    }

    companion object {
        fun <K : Any, V : Any> new(): NSMutableDictionary<K, V> = NSMutableDictionary()

        fun <K : Any, V : Any> dictionaryWithCapacity(capacity: Int): NSMutableDictionary<K, V> =
            NSMutableDictionary(capacity)

        fun <K : Any, V : Any> dictionaryWithCapacity(capacity: NSUInteger): NSMutableDictionary<K, V> =
            dictionaryWithCapacity(capacity.toInt())
    }

    fun setObjectForKey(anObject: V, aKey: K) {
        mutableMap[aKey] = anObject
    }

    fun insert(aKey: K, anObject: V) {
        setObjectForKey(anObject, aKey)
    }

    operator fun set(aKey: K, anObject: V) {
        setObjectForKey(anObject, aKey)
    }

    fun removeObjectForKey(aKey: K): V? =
        mutableMap.remove(aKey)

    fun removeAllObjects() {
        mutableMap.clear()
    }

    override fun copy(): NSDictionary<K, V> = NSDictionary(mutableMap)

    override fun mutableCopy(): NSMutableDictionary<K, V> = NSMutableDictionary(mutableMap)
}
