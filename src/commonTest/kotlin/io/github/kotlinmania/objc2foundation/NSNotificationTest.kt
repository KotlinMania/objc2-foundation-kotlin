// port-lint: tests notification.rs
package io.github.kotlinmania.objc2foundation

import io.github.kotlinmania.objc2foundation.generated.NSNotification
import io.github.kotlinmania.objc2foundation.generated.NSNotificationCenter
import io.github.kotlinmania.objc2foundation.generated.NSNotificationName
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class NSNotificationTest {
    @Test
    fun testNotificationCreation() {
        val name: NSNotificationName = NSString("CustomNotification")
        val sender = NSObject()
        val notif = NSNotification.notificationWithName_object(name, sender)

        assertEquals(name, notif.name())
        assertEquals(sender, notif.`object`())
        assertNull(notif.userInfo())

        val notif2 = NSNotification.notificationWithName_object(name, sender)
        assertEquals(notif, notif2)
        assertEquals(notif.hashCode(), notif2.hashCode())

        val notif3 = notif.copy()
        assertEquals(notif, notif3)
    }

    @Test
    fun testNotificationWithUserInfo() {
        val name: NSNotificationName = NSString("UserInfoNotification")
        val sender = NSObject()
        val key = NSString("Status")
        val value = NSString("OK")
        val userInfo = NSDictionary.from_slices(listOf(key), listOf(value))

        val notif = NSNotification.notificationWithName_object_userInfo(name, sender, userInfo)
        assertEquals(name, notif.name())
        assertEquals(sender, notif.`object`())
        assertNotNull(notif.userInfo())
        @Suppress("UNCHECKED_CAST")
        val info = notif.userInfo() as? NSDictionary<NSString, NSString>
        assertNotNull(info)
        assertEquals(value, info.objectForKey(key))
    }

    @Test
    fun testNotificationCenterBlockObserver() {
        val center = NSNotificationCenter.new()
        val testName: NSNotificationName = NSString("BlockTestNotification")
        val sender = NSObject()

        var receivedCount = 0
        var lastReceivedName: NSNotificationName? = null
        var lastReceivedObject: Any? = null

        val token =
            center.addObserverForName_object_queue_usingBlock(testName, sender, null) { notif ->
                receivedCount++
                lastReceivedName = notif.name()
                lastReceivedObject = notif.`object`()
            }

        center.postNotificationName_object(testName, sender)
        assertEquals(1, receivedCount)
        assertEquals(testName, lastReceivedName)
        assertEquals(sender, lastReceivedObject)

        // Post with different sender: should not match
        val otherSender = NSObject()
        center.postNotificationName_object(testName, otherSender)
        assertEquals(1, receivedCount)

        // Post with different name: should not match
        center.postNotificationName_object(NSString("OtherName"), sender)
        assertEquals(1, receivedCount)

        // Remove observer
        center.removeObserver(token)
        center.postNotificationName_object(testName, sender)
        assertEquals(1, receivedCount)
    }

    @Test
    fun testNotificationCenterSelectorObserver() {
        val center = NSNotificationCenter.new()
        val testName: NSNotificationName = NSString("SelectorNotification")

        var received = false
        val observerHandler: (NSNotification) -> Unit = { notif ->
            if (notif.name() == testName) {
                received = true
            }
        }

        center.addObserver_selector_name_object(observerHandler, "handleNotification:", testName, null)
        center.postNotificationName_object(testName, null)
        assertEquals(true, received)

        center.removeObserver(observerHandler)
        received = false
        center.postNotificationName_object(testName, null)
        assertEquals(false, received)
    }
}
