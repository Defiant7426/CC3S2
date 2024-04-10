package test.collective

import io.collective.SimpleAgedKache
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import org.junit.jupiter.api.Assertions.*

class SimpleAgedKacheTest {
    private var empty = SimpleAgedKache()
    private var nonempty = SimpleAgedKache()

    @BeforeEach
    fun before() {
        nonempty.put("aKey", "aValue", 2000)
        nonempty.put("anotherKey", "anotherValue", 4000)
    }

    @Test
    @Disabled("Ignored for demonstration purposes")
    fun isEmpty() {
        assertTrue(empty.isEmpty())
        assertFalse(nonempty.isEmpty())
    }

    @Test
    @Disabled("Ignored for demonstration purposes")
    fun size() {
        assertEquals(0, empty.size())
        assertEquals(2, nonempty.size())
    }

    @Test
    @Disabled("Ignored for demonstration purposes")
    fun get() {
        assertNull(empty.get("aKey"))
        assertEquals("aValue", nonempty.get("aKey"))
        assertEquals("anotherValue", nonempty.get("anotherKey"))
    }

    @Test
    @Disabled("Ignored for demonstration purposes")
    fun getExpired() {
        val clock = TestClock()
        val expired = SimpleAgedKache(clock)
        expired.put("aKey", "aValue", 2000)
        expired.put("anotherKey", "anotherValue", 4000)
        clock.offset(Duration.ofMillis(3000))
        assertEquals(1, expired.size())
        assertEquals("anotherValue", expired.get("anotherKey"))
    }

    internal class TestClock : Clock() {
        var offset = Duration.ZERO

        override fun getZone(): ZoneId = Clock.systemDefaultZone().zone

        override fun withZone(zone: ZoneId): Clock = Clock.offset(Clock.system(zone), offset)

        override fun instant(): Instant = Clock.offset(Clock.systemDefaultZone(), offset).instant()

        fun offset(offset: Duration) {
            this.offset = offset
        }
    }
}
