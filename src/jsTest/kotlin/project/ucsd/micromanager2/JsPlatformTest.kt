package project.ucsd.micromanager2

import kotlin.test.Test
import kotlin.test.assertEquals

class JsPlatformTest {
    @Test
    fun testPlatform() {
        assertEquals(Platform.name, "JS")
    }
}