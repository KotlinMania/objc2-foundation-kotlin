// port-lint: tests url.rs
package io.github.kotlinmania.objc2foundation

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UrlTest {

    @Test
    fun invalidPath() {
        assertNull(NSURL.from_file_path(""))
        assertNull(NSURL.from_file_path("/\u0000/a"))
    }

    @Test
    fun roundtrip() {
        val path = "/abc/def"
        val url = NSURL.from_file_path(path)
        assertEquals(path, url?.to_file_path())

        val path2 = "/\u0008"
        val url2 = NSURL.from_file_path(path2)
        assertEquals(path2, url2?.to_file_path())
    }
}
