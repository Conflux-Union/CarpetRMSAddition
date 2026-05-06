package rms.carpet_rms_addition.test;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

final class SmokeTest {
    @Test
    void allSettingsFieldsExistAndHaveDefaults() throws Exception {
        final Class<?> settings = Class.forName("rms.carpet_rms_addition.CarpetRMSAdditionSettings");
        assertDefault(settings, "overrideMonsterBlockLightLevel", "false");
        assertDefault(settings, "overrideMonsterSkyLightLevel", "false");
        assertDefault(settings, "enhancedDataGet", false);
        assertDefault(settings, "interceptUpdatePacketEntities", "[]");
        assertDefault(settings, "interceptAllPacketEntities", "[]");
        assertDefault(settings, "naturalSpawnBlacklist", "[]");
        assertDefault(settings, "usePortalBlacklist", "[]");
        assertDefault(settings, "interceptParticlePackets", false);
        assertDefault(settings, "separateMapModWorlds", "false");
        assertDefault(settings, "selfCheckOnPlacement", "false");
    }

    @Test
    void rawCustomPayloadClassLoads() {
        assertDoesNotThrow(() -> Class.forName("rms.carpet_rms_addition.RawCustomPayload"));
    }

    @Test
    void worldMapIdentityChannels() {
        assertNotNull(invokeStatic("voxelMapChannel"));
        assertNotNull(invokeStatic("xaeroWorldMapChannel"));
        assertNotNull(invokeStatic("xaeroMiniMapChannel"));
    }

    @Test
    void formatVoxelMapResponse() {
        final byte[] r = invokeStatic("formatVoxelMapResponse",
            new Class[]{byte[].class, String.class},
            new byte[]{0, 0, 0, 42}, "testWorld");
        assertNotNull(r);
        assertTrue(r.length > 2);
    }

    @Test
    void formatXaeroPayload() {
        final byte[] p = invokeStatic("formatXaeroPayload",
            new Class[]{String.class}, "testWorld");
        assertNotNull(p);
        assertEquals(5, p.length);
    }

    private static void assertDefault(final Class<?> cls, final String name, final Object expected) throws Exception {
        final Field f = cls.getDeclaredField(name);
        f.setAccessible(true);
        assertEquals(expected, f.get(null), name);
    }

    @SuppressWarnings("unchecked")
    private static <T> T invokeStatic(final String name, final Class<?>[] params, final Object... args) {
        try {
            final Method m = Class.forName("rms.carpet_rms_addition.WorldMapIdentityHelper")
                .getDeclaredMethod(name, params);
            m.setAccessible(true);
            return (T) m.invoke(null, args);
        } catch (final Exception e) {
            throw new AssertionError("invoke " + name + " failed", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T invokeStatic(final String name) {
        return invokeStatic(name, new Class[0]);
    }
}
