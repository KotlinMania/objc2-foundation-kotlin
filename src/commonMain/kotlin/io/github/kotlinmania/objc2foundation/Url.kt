// port-lint: source url.rs
package io.github.kotlinmania.objc2foundation

import io.github.kotlinmania.objc2foundation.generated.NSURL

const val PATH_MAX: Int = 1024

fun NSURL.Companion.fromPath(
    path: String,
    isDirectory: Boolean = false,
    baseURL: NSURL? = null,
): NSURL? {
    if (path.isEmpty() || path.contains('\u0000')) return null
    return fileURLWithPath(path, isDirectory, baseURL)
}

fun NSURL.Companion.from_path(
    path: String,
    isDirectory: Boolean = false,
    baseURL: NSURL? = null,
): NSURL? = fromPath(path, isDirectory, baseURL)

fun NSURL.Companion.fromFilePath(path: String): NSURL? {
    if (path.isEmpty() || path.contains('\u0000')) return null
    return fromPath(path, false, null)
}

fun NSURL.Companion.from_file_path(path: String): NSURL? = fromFilePath(path)

fun NSURL.Companion.fromDirectoryPath(path: String): NSURL? {
    if (path.isEmpty() || path.contains('\u0000')) return null
    return fromPath(path, true, null)
}

fun NSURL.Companion.from_directory_path(path: String): NSURL? = fromDirectoryPath(path)

fun NSURL.toFilePath(): String? {
    if (!isFileURL()) return null
    return path()?.rawString
}

fun NSURL.to_file_path(): String? = toFilePath()
