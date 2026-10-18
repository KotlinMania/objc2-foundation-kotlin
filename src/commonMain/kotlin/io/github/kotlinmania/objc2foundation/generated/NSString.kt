// port-lint: source generated/NSString.rs
package io.github.kotlinmania.objc2foundation.generated

import io.github.kotlinmania.objc2foundation.NSString
import io.github.kotlinmania.objc2foundation.NSUInteger

/**
 * 16-bit unicode character.
 */
typealias unichar = UShort

/**
 * Options for string searching and comparison.
 */
data class NSStringCompareOptions(
    val rawValue: NSUInteger,
) {
    companion object {
        val CaseInsensitiveSearch = NSStringCompareOptions(1uL)
        val LiteralSearch = NSStringCompareOptions(2uL)
        val BackwardsSearch = NSStringCompareOptions(4uL)
        val AnchoredSearch = NSStringCompareOptions(8uL)
        val NumericSearch = NSStringCompareOptions(64uL)
        val DiacriticInsensitiveSearch = NSStringCompareOptions(128uL)
        val WidthInsensitiveSearch = NSStringCompareOptions(256uL)
        val ForcedOrderingSearch = NSStringCompareOptions(512uL)
        val RegularExpressionSearch = NSStringCompareOptions(1024uL)
    }
}

/**
 * String encoding identifier.
 */
typealias NSStringEncoding = NSUInteger

const val NSASCIIStringEncoding: NSStringEncoding = 1uL
const val NSNEXTSTEPStringEncoding: NSStringEncoding = 2uL
const val NSJapaneseEUCStringEncoding: NSStringEncoding = 3uL
const val NSUTF8StringEncoding: NSStringEncoding = 4uL
const val NSISOLatin1StringEncoding: NSStringEncoding = 5uL
const val NSSymbolStringEncoding: NSStringEncoding = 6uL
const val NSNonLossyASCIIStringEncoding: NSStringEncoding = 7uL
const val NSShiftJISStringEncoding: NSStringEncoding = 8uL
const val NSISOLatin2StringEncoding: NSStringEncoding = 9uL
const val NSUnicodeStringEncoding: NSStringEncoding = 10uL
const val NSWindowsCP1251StringEncoding: NSStringEncoding = 11uL
const val NSWindowsCP1252StringEncoding: NSStringEncoding = 12uL
const val NSWindowsCP1253StringEncoding: NSStringEncoding = 13uL
const val NSWindowsCP1254StringEncoding: NSStringEncoding = 14uL
const val NSWindowsCP1250StringEncoding: NSStringEncoding = 15uL
const val NSISO2022JPStringEncoding: NSStringEncoding = 21uL
const val NSMacOSRomanStringEncoding: NSStringEncoding = 30uL
const val NSUTF16StringEncoding: NSStringEncoding = NSUnicodeStringEncoding
const val NSUTF16BigEndianStringEncoding: NSStringEncoding = 0x90000100uL
const val NSUTF16LittleEndianStringEncoding: NSStringEncoding = 0x94000100uL
const val NSUTF32StringEncoding: NSStringEncoding = 0x8c000100uL
const val NSUTF32BigEndianStringEncoding: NSStringEncoding = 0x98000100uL
const val NSUTF32LittleEndianStringEncoding: NSStringEncoding = 0x9c000100uL

/**
 * Options for string enumeration.
 */
data class NSStringEnumerationOptions(
    val rawValue: NSUInteger,
) {
    companion object {
        val ByLines = NSStringEnumerationOptions(0uL)
        val ByParagraphs = NSStringEnumerationOptions(1uL)
        val ByComposedCharacterSequences = NSStringEnumerationOptions(2uL)
        val ByWords = NSStringEnumerationOptions(3uL)
        val BySentences = NSStringEnumerationOptions(4uL)
        val ByCaretPositions = NSStringEnumerationOptions(5uL)
        val ByDeletionClusters = NSStringEnumerationOptions(6uL)
        val Reverse = NSStringEnumerationOptions(1uL shl 8)
        val SubstringNotRequired = NSStringEnumerationOptions(1uL shl 9)
        val Localized = NSStringEnumerationOptions(1uL shl 10)
    }
}

/**
 * Standard string transform types.
 */
typealias NSStringTransform = NSString

val NSStringTransformLatinToKatakana: NSStringTransform = NSString("Latin-Katakana")
val NSStringTransformLatinToHiragana: NSStringTransform = NSString("Latin-Hiragana")
val NSStringTransformLatinToHangul: NSStringTransform = NSString("Latin-Hangul")
val NSStringTransformLatinToArabic: NSStringTransform = NSString("Latin-Arabic")
val NSStringTransformLatinToHebrew: NSStringTransform = NSString("Latin-Hebrew")
val NSStringTransformLatinToThai: NSStringTransform = NSString("Latin-Thai")
val NSStringTransformLatinToCyrillic: NSStringTransform = NSString("Latin-Cyrillic")
val NSStringTransformLatinToGreek: NSStringTransform = NSString("Latin-Greek")
val NSStringTransformToLatin: NSStringTransform = NSString("Any-Latin")
val NSStringTransformMandarinToLatin: NSStringTransform = NSString("Mandarin-Latin")
val NSStringTransformHiraganaToKatakana: NSStringTransform = NSString("Hiragana-Katakana")
val NSStringTransformFullwidthToHalfwidth: NSStringTransform = NSString("Fullwidth-Halfwidth")
val NSStringTransformToXMLHex: NSStringTransform = NSString("Any-Hex/XML")
val NSStringTransformToUnicodeName: NSStringTransform = NSString("Any-Name")
val NSStringTransformStripCombiningMarks: NSStringTransform = NSString("NFD; [:M:] Remove; NFC")
val NSStringTransformStripDiacritics: NSStringTransform = NSString("NFD; [:Nonspacing Mark:] Remove; NFC")

/**
 * String encoding detection options.
 */
typealias NSStringEncodingDetectionOptionsKey = NSString

val NSStringEncodingDetectionSuggestedEncodingsKey: NSStringEncodingDetectionOptionsKey =
    NSString("NSStringEncodingDetectionSuggestedEncodingsKey")
val NSStringEncodingDetectionDisallowedEncodingsKey: NSStringEncodingDetectionOptionsKey =
    NSString("NSStringEncodingDetectionDisallowedEncodingsKey")
val NSStringEncodingDetectionUseOnlySuggestedEncodingsKey: NSStringEncodingDetectionOptionsKey =
    NSString("NSStringEncodingDetectionUseOnlySuggestedEncodingsKey")
val NSStringEncodingDetectionAllowLossyKey: NSStringEncodingDetectionOptionsKey =
    NSString("NSStringEncodingDetectionAllowLossyKey")
val NSStringEncodingDetectionFromWindowsKey: NSStringEncodingDetectionOptionsKey =
    NSString("NSStringEncodingDetectionFromWindowsKey")
val NSStringEncodingDetectionLossySubstitutionKey: NSStringEncodingDetectionOptionsKey =
    NSString("NSStringEncodingDetectionLossySubstitutionKey")
val NSStringEncodingDetectionLikelyLanguageKey: NSStringEncodingDetectionOptionsKey =
    NSString("NSStringEncodingDetectionLikelyLanguageKey")

const val NSCharacterConversionException: String = "NSCharacterConversionException"
const val NSParseErrorException: String = "NSParseErrorException"
