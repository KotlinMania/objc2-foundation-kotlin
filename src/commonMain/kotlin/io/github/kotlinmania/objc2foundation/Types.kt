package io.github.kotlinmania.objc2foundation

/**
 * Objective-C signed integer type.
 */
typealias NSInteger = Long

/**
 * Objective-C unsigned integer type.
 */
typealias NSUInteger = ULong

/**
 * Objective-C time interval (seconds).
 */
typealias NSTimeInterval = Double

/**
 * Apple MacTypes.h definitions.
 */
typealias MacBoolean = UByte
typealias FourCharCode = UInt
typealias OSType = FourCharCode
typealias UTF32Char = UInt
typealias SRefCon = Long
typealias OSErr = Short

/**
 * Number of seconds between January 1, 1970 and January 1, 2001.
 */
const val NSTimeIntervalSince1970: Double = 978307200.0

/**
 * Returned by NSURLResponse when expected content length is unknown.
 */
const val NSURLResponseUnknownLength: Long = -1L
