// port-lint: source error.rs
package io.github.kotlinmania.objc2foundation

import io.github.kotlinmania.objc2foundation.generated.NSErrorDomain
import io.github.kotlinmania.objc2foundation.generated.NSErrorUserInfoKey
import io.github.kotlinmania.objc2foundation.generated.NSHelpAnchorErrorKey
import io.github.kotlinmania.objc2foundation.generated.NSLocalizedDescriptionKey
import io.github.kotlinmania.objc2foundation.generated.NSLocalizedFailureReasonErrorKey
import io.github.kotlinmania.objc2foundation.generated.NSLocalizedRecoveryOptionsErrorKey
import io.github.kotlinmania.objc2foundation.generated.NSLocalizedRecoverySuggestionErrorKey
import io.github.kotlinmania.objc2foundation.generated.NSRecoveryAttempterErrorKey
import io.github.kotlinmania.objc2foundation.generated.NSSecureCoding
import io.github.kotlinmania.objc2foundation.generated.NSUnderlyingErrorKey

/**
 * Information about an error condition including a domain, a domain-specific error code,
 * and application-specific information.
 */
open class NSError(
    domain: NSErrorDomain = NSString(""),
    code: NSInteger = 0L,
    userInfo: NSDictionary<*, *>? = null,
) : NSObject(),
    NSCopying,
    NSSecureCoding {
    private val fixedDomain: NSErrorDomain = domain
    private val fixedCode: NSInteger = code

    @Suppress("UNCHECKED_CAST")
    private val fixedUserInfo: NSDictionary<Any, Any>? = userInfo as? NSDictionary<Any, Any>

    companion object {
        /**
         * Construct a new [NSError] with the given code in the given domain.
         */
        fun new(
            code: NSInteger,
            domain: NSErrorDomain,
        ): NSError = NSError(domain, code, null)

        /**
         * Accessor for [NSLocalizedDescriptionKey].
         */
        fun NSLocalizedDescriptionKey(): NSErrorUserInfoKey =
            io.github.kotlinmania.objc2foundation.generated.NSLocalizedDescriptionKey

        fun errorWithDomain_code_userInfo(
            domain: NSErrorDomain,
            code: NSInteger,
            dict: NSDictionary<*, *>? = null,
        ): NSError = NSError(domain, code, dict)

        fun initWithDomain_code_userInfo(
            domain: NSErrorDomain,
            code: NSInteger,
            dict: NSDictionary<*, *>? = null,
        ): NSError = NSError(domain, code, dict)

        fun init(): NSError = NSError()

        fun setUserInfoValueProviderForDomain_provider(
            errorDomain: NSErrorDomain,
            provider: Any? = null,
        ) {}

        fun userInfoValueProviderForDomain(
            errorDomain: NSErrorDomain,
        ): Any? = null
    }

    fun domain(): NSErrorDomain = fixedDomain

    fun code(): NSInteger = fixedCode

    @Suppress("UNCHECKED_CAST")
    fun userInfo(): NSDictionary<NSErrorUserInfoKey, Any> =
        (fixedUserInfo as? NSDictionary<NSErrorUserInfoKey, Any>) ?: NSDictionary()

    fun localizedDescription(): NSString {
        val desc = fixedUserInfo?.objectForKey(NSLocalizedDescriptionKey)
        if (desc != null) {
            return if (desc is NSString) desc else NSString(desc.toString())
        }
        return NSString("${fixedDomain.rawString} $fixedCode")
    }

    fun localizedFailureReason(): NSString? =
        fixedUserInfo?.objectForKey(NSLocalizedFailureReasonErrorKey) as? NSString

    fun localizedRecoverySuggestion(): NSString? =
        fixedUserInfo?.objectForKey(NSLocalizedRecoverySuggestionErrorKey) as? NSString

    @Suppress("UNCHECKED_CAST")
    fun localizedRecoveryOptions(): NSArray<NSString>? =
        fixedUserInfo?.objectForKey(NSLocalizedRecoveryOptionsErrorKey) as? NSArray<NSString>

    fun recoveryAttempter(): Any? =
        fixedUserInfo?.objectForKey(NSRecoveryAttempterErrorKey)

    fun helpAnchor(): NSString? =
        fixedUserInfo?.objectForKey(NSHelpAnchorErrorKey) as? NSString

    @Suppress("UNCHECKED_CAST")
    fun underlyingErrors(): NSArray<NSError> =
        (fixedUserInfo?.objectForKey(NSUnderlyingErrorKey) as? NSArray<NSError>) ?: NSArray()

    fun fmt(): String = toString()

    fun fmt(formatter: Any?): String =
        "NSError { code: ${code()}, domain: ${domain().rawString}, userInfo: ${userInfo()} }"

    override fun toString(): String = localizedDescription().rawString

    override fun copy(): NSError = NSError(fixedDomain, fixedCode, fixedUserInfo)

    override fun encodeWithCoder(coder: NSCoder) {}

    override fun initWithCoder(coder: NSCoder): Any = this

    override fun isEqual(other: Any?): Boolean {
        if (this === other) return true
        if (other !is NSError) return false
        return fixedCode == other.fixedCode && fixedDomain == other.fixedDomain && fixedUserInfo == other.fixedUserInfo
    }

    override fun equals(other: Any?): Boolean = isEqual(other)

    override fun hashCode(): Int {
        var result = fixedCode.hashCode()
        result = 31 * result + fixedDomain.hashCode()
        result = 31 * result + (fixedUserInfo?.hashCode() ?: 0)
        return result
    }
}
