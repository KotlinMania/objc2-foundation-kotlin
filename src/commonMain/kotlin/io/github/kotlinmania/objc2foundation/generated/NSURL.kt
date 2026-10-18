// port-lint: source generated/NSURL.rs
package io.github.kotlinmania.objc2foundation.generated

import io.github.kotlinmania.objc2foundation.NSCopying
import io.github.kotlinmania.objc2foundation.NSObject
import io.github.kotlinmania.objc2foundation.NSString
import io.github.kotlinmania.objc2foundation.NSUInteger

typealias NSURLResourceKey = NSString

val NSURLFileScheme: NSString = NSString("file")
val NSURLKeysOfUnsetValuesKey: NSURLResourceKey = NSString("NSURLKeysOfUnsetValuesKey")
val NSURLNameKey: NSURLResourceKey = NSString("NSURLNameKey")
val NSURLLocalizedNameKey: NSURLResourceKey = NSString("NSURLLocalizedNameKey")
val NSURLIsRegularFileKey: NSURLResourceKey = NSString("NSURLIsRegularFileKey")
val NSURLIsDirectoryKey: NSURLResourceKey = NSString("NSURLIsDirectoryKey")
val NSURLIsSymbolicLinkKey: NSURLResourceKey = NSString("NSURLIsSymbolicLinkKey")
val NSURLIsVolumeKey: NSURLResourceKey = NSString("NSURLIsVolumeKey")
val NSURLIsPackageKey: NSURLResourceKey = NSString("NSURLIsPackageKey")
val NSURLIsApplicationKey: NSURLResourceKey = NSString("NSURLIsApplicationKey")
val NSURLApplicationIsScriptableKey: NSURLResourceKey = NSString("NSURLApplicationIsScriptableKey")
val NSURLIsSystemImmutableKey: NSURLResourceKey = NSString("NSURLIsSystemImmutableKey")
val NSURLIsUserImmutableKey: NSURLResourceKey = NSString("NSURLIsUserImmutableKey")
val NSURLIsHiddenKey: NSURLResourceKey = NSString("NSURLIsHiddenKey")
val NSURLHasHiddenExtensionKey: NSURLResourceKey = NSString("NSURLHasHiddenExtensionKey")

data class NSURLBookmarkCreationOptions(
    val rawValue: NSUInteger,
)

data class NSURLBookmarkResolutionOptions(
    val rawValue: NSUInteger,
)
typealias NSURLBookmarkFileCreationOptions = NSUInteger

/**
 * An object that represents the location of a resource.
 */
open class NSURL :
    NSObject,
    NSCopying {
    val stringRepresentation: String
    val isDirectory: Boolean
    val baseURL: NSURL?

    constructor(
        string: String,
        isDirectory: Boolean = false,
        baseURL: NSURL? = null,
    ) : super() {
        this.stringRepresentation = string
        this.isDirectory = isDirectory
        this.baseURL = baseURL
    }

    companion object {
        fun URLWithString(URLString: NSString): NSURL? =
            URLWithString(URLString.rawString)

        fun URLWithString(URLString: String): NSURL? {
            if (URLString.isEmpty() || URLString.contains('\u0000')) return null
            return NSURL(URLString)
        }

        fun URLWithString(URLString: NSString, relativeToURL: NSURL?): NSURL? {
            if (URLString.rawString.isEmpty() || URLString.rawString.contains('\u0000')) return null
            return NSURL(URLString.rawString, false, relativeToURL)
        }

        fun fileURLWithPath(path: NSString, isDirectory: Boolean, relativeToURL: NSURL?): NSURL =
            fileURLWithPath(path.rawString, isDirectory, relativeToURL)

        fun fileURLWithPath(path: String, isDirectory: Boolean, relativeToURL: NSURL?): NSURL {
            val urlString = if (path.startsWith("file://")) path else "file://$path"
            return NSURL(urlString, isDirectory, relativeToURL)
        }

        fun fileURLWithPath(path: NSString, isDirectory: Boolean): NSURL =
            fileURLWithPath(path.rawString, isDirectory, null)

        fun fileURLWithPath(path: String, isDirectory: Boolean): NSURL =
            fileURLWithPath(path, isDirectory, null)

        fun fileURLWithPath(path: NSString): NSURL =
            fileURLWithPath(path.rawString, false, null)

        fun fileURLWithPath(path: String): NSURL =
            fileURLWithPath(path, false, null)
    }

    fun isFileURL(): Boolean =
        stringRepresentation.startsWith("file:")

    fun path(): NSString? {
        val s = stringRepresentation
        return when {
            s.startsWith("file://") -> NSString(s.removePrefix("file://"))
            s.startsWith("file:") -> NSString(s.removePrefix("file:"))
            else -> NSString(s)
        }
    }

    fun absoluteString(): NSString = NSString(stringRepresentation)

    fun scheme(): NSString? {
        val idx = stringRepresentation.indexOf(':')
        return if (idx >= 0) NSString(stringRepresentation.substring(0, idx)) else null
    }

    override fun copy(): NSURL = this

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSURL) return false
        return stringRepresentation == other.stringRepresentation && baseURL == other.baseURL
    }

    override fun hashCode(): Int = stringRepresentation.hashCode()

    override fun toString(): String = stringRepresentation
}
