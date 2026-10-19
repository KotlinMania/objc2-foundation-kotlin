// port-lint: source generated/NSNotification.rs
package io.github.kotlinmania.objc2foundation.generated

import io.github.kotlinmania.objc2foundation.NSCoder
import io.github.kotlinmania.objc2foundation.NSCopying
import io.github.kotlinmania.objc2foundation.NSDictionary
import io.github.kotlinmania.objc2foundation.NSObject
import io.github.kotlinmania.objc2foundation.NSObjectProtocol
import io.github.kotlinmania.objc2foundation.NSOperationQueue
import io.github.kotlinmania.objc2foundation.NSString
import io.github.kotlinmania.objc2foundation.Sel

/**
 * [Apple's documentation](https://developer.apple.com/documentation/foundation/nsnotificationname?language=objc)
 */
typealias NSNotificationName = NSString

/**
 * A container for information broadcast through a notification center to all registered observers.
 *
 * [Apple's documentation](https://developer.apple.com/documentation/foundation/nsnotification?language=objc)
 */
open class NSNotification :
    NSObject,
    NSCoding,
    NSCopying {
    private val notificationName: NSNotificationName
    private val notificationObject: Any?
    private val notificationUserInfo: NSDictionary<*, *>?

    constructor(
        name: NSNotificationName,
        `object`: Any? = null,
        userInfo: NSDictionary<*, *>? = null,
    ) : super() {
        this.notificationName = name
        this.notificationObject = `object`
        this.notificationUserInfo = userInfo
    }

    constructor() : this(NSString(""))

    open fun name(): NSNotificationName = notificationName

    open fun `object`(): Any? = notificationObject

    open fun userInfo(): NSDictionary<*, *>? = notificationUserInfo

    open fun initWithName_object_userInfo(
        name: NSNotificationName,
        `object`: Any? = null,
        user_info: NSDictionary<*, *>? = null,
    ): NSNotification = NSNotification(name, `object`, user_info)

    override fun encodeWithCoder(coder: NSCoder) {}

    override fun initWithCoder(coder: NSCoder): NSNotification? = this

    override fun copy(): NSNotification = this

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSNotification) return false
        return notificationName == other.notificationName &&
            notificationObject == other.notificationObject &&
            notificationUserInfo == other.notificationUserInfo
    }

    override fun hashCode(): Int {
        var result = notificationName.hashCode()
        result = 31 * result + (notificationObject?.hashCode() ?: 0)
        result = 31 * result + (notificationUserInfo?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String =
        "NSNotification(name=$notificationName, object=$notificationObject, userInfo=$notificationUserInfo)"

    companion object {
        fun notificationWithName_object(
            a_name: NSNotificationName,
            an_object: Any? = null,
        ): NSNotification = NSNotification(a_name, an_object, null)

        fun notificationWithName_object_userInfo(
            a_name: NSNotificationName,
            an_object: Any? = null,
            a_user_info: NSDictionary<*, *>? = null,
        ): NSNotification = NSNotification(a_name, an_object, a_user_info)

        fun initWithName_object_userInfo(
            name: NSNotificationName,
            `object`: Any? = null,
            user_info: NSDictionary<*, *>? = null,
        ): NSNotification = NSNotification(name, `object`, user_info)

        fun initWithCoder(coder: NSCoder): NSNotification? = NSNotification()

        fun new(): NSNotification = NSNotification()

        fun init(): NSNotification = NSNotification()
    }
}

/**
 * A notification dispatch mechanism that enables the broadcast of information to registered observers.
 *
 * [Apple's documentation](https://developer.apple.com/documentation/foundation/nsnotificationcenter?language=objc)
 */
open class NSNotificationCenter : NSObject() {
    private data class ObserverEntry(
        val observer: Any,
        val selector: Sel? = null,
        val name: NSNotificationName? = null,
        val sender: Any? = null,
        val block: ((NSNotification) -> Unit)? = null,
    )

    private class NotificationObserverToken(
        val entry: ObserverEntry,
    ) : NSObject()

    private val entries = mutableListOf<ObserverEntry>()

    open fun defaultCenter(): NSNotificationCenter = Companion.defaultCenter()

    open fun addObserver_selector_name_object(
        observer: Any,
        a_selector: Sel,
        a_name: NSNotificationName? = null,
        an_object: Any? = null,
    ) {
        val entry =
            ObserverEntry(
                observer = observer,
                selector = a_selector,
                name = a_name,
                sender = an_object,
                block = null,
            )
        entries.add(entry)
    }

    open fun postNotification(notification: NSNotification) {
        val snapshot = entries.toList()
        for (entry in snapshot) {
            val nameMatch = entry.name == null || entry.name == notification.name()
            val senderMatch = entry.sender == null || entry.sender === notification.`object`()
            if (nameMatch && senderMatch) {
                if (entry.block != null) {
                    entry.block.invoke(notification)
                } else if (entry.observer is Function1<*, *>) {
                    @Suppress("UNCHECKED_CAST")
                    (entry.observer as (NSNotification) -> Unit).invoke(notification)
                }
            }
        }
    }

    open fun postNotificationName_object(
        a_name: NSNotificationName,
        an_object: Any? = null,
    ) {
        postNotification(NSNotification(a_name, an_object, null))
    }

    open fun postNotificationName_object_userInfo(
        a_name: NSNotificationName,
        an_object: Any? = null,
        a_user_info: NSDictionary<*, *>? = null,
    ) {
        postNotification(NSNotification(a_name, an_object, a_user_info))
    }

    open fun removeObserver(observer: Any) {
        if (observer is NotificationObserverToken) {
            entries.remove(observer.entry)
        } else {
            entries.removeAll { it.observer === observer }
        }
    }

    open fun removeObserver_name_object(
        observer: Any,
        a_name: NSNotificationName? = null,
        an_object: Any? = null,
    ) {
        entries.removeAll { entry ->
            val observerMatch = entry.observer === observer
            val nameMatch = a_name == null || entry.name == a_name
            val senderMatch = an_object == null || entry.sender === an_object
            observerMatch && nameMatch && senderMatch
        }
    }

    open fun addObserverForName_object_queue_usingBlock(
        name: NSNotificationName? = null,
        obj: Any? = null,
        queue: NSOperationQueue? = null,
        block: (NSNotification) -> Unit,
    ): NSObjectProtocol {
        val entry =
            ObserverEntry(
                observer = Any(),
                selector = null,
                name = name,
                sender = obj,
                block = block,
            )
        entries.add(entry)
        return NotificationObserverToken(entry)
    }

    companion object {
        private val defaultCenterInstance: NSNotificationCenter by lazy { NSNotificationCenter() }

        fun defaultCenter(): NSNotificationCenter = defaultCenterInstance

        fun new(): NSNotificationCenter = NSNotificationCenter()

        fun init(): NSNotificationCenter = NSNotificationCenter()
    }
}
