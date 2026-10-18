// port-lint: source generated/NSError.rs
package io.github.kotlinmania.objc2foundation.generated

import io.github.kotlinmania.objc2foundation.NSError
import io.github.kotlinmania.objc2foundation.NSString
import io.github.kotlinmania.objc2foundation.NSUInteger

/**
 * Apple's documentation: [NSErrorDomain](https://developer.apple.com/documentation/foundation/nserrordomain?language=objc)
 */
typealias NSErrorDomain = NSString

val NSCocoaErrorDomain: NSErrorDomain = NSString("NSCocoaErrorDomain")
val NSPOSIXErrorDomain: NSErrorDomain = NSString("NSPOSIXErrorDomain")
val NSOSStatusErrorDomain: NSErrorDomain = NSString("NSOSStatusErrorDomain")
val NSMachErrorDomain: NSErrorDomain = NSString("NSMachErrorDomain")

/**
 * Apple's documentation: [NSErrorUserInfoKey](https://developer.apple.com/documentation/foundation/nserroruserinfokey?language=objc)
 */
typealias NSErrorUserInfoKey = NSString

val NSUnderlyingErrorKey: NSErrorUserInfoKey = NSString("NSUnderlyingError")
val NSMultipleUnderlyingErrorsKey: NSErrorUserInfoKey = NSString("NSMultipleUnderlyingErrors")
val NSLocalizedDescriptionKey: NSErrorUserInfoKey = NSString("NSLocalizedDescription")
val NSLocalizedFailureReasonErrorKey: NSErrorUserInfoKey = NSString("NSLocalizedFailureReason")
val NSLocalizedRecoverySuggestionErrorKey: NSErrorUserInfoKey = NSString("NSLocalizedRecoverySuggestion")
val NSLocalizedRecoveryOptionsErrorKey: NSErrorUserInfoKey = NSString("NSLocalizedRecoveryOptions")
val NSRecoveryAttempterErrorKey: NSErrorUserInfoKey = NSString("NSRecoveryAttempter")
val NSHelpAnchorErrorKey: NSErrorUserInfoKey = NSString("NSHelpAnchor")
val NSDebugDescriptionErrorKey: NSErrorUserInfoKey = NSString("NSDebugDescription")
val NSLocalizedFailureErrorKey: NSErrorUserInfoKey = NSString("NSLocalizedFailure")
val NSStringEncodingErrorKey: NSErrorUserInfoKey = NSString("NSStringEncoding")
val NSURLErrorKey: NSErrorUserInfoKey = NSString("NSURL")
val NSFilePathErrorKey: NSErrorUserInfoKey = NSString("NSFilePathErrorKey")

typealias Result = NSError

interface Sealed

/**
 * Category "NSErrorRecoveryAttempting" on [NSObject].
 */
interface NSObjectNSErrorRecoveryAttempting : Sealed {
    fun attemptRecoveryFromError_optionIndex_delegate_didRecoverSelector_contextInfo(
        error: NSError,
        recoveryOptionIndex: NSUInteger,
        delegate: Any? = null,
        didRecoverSelector: Any? = null,
        contextInfo: Any? = null,
    ) {}

    fun attemptRecoveryFromError_optionIndex(
        error: NSError,
        recoveryOptionIndex: NSUInteger,
    ): Boolean = false
}
