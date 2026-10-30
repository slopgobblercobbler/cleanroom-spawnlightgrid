package com.cleanroom.lightgrid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SpawnLightGridTest {

    @Test
    void testPitchBlackMobSpawnRedCross() {
        // Deep cave / dark room: block light = 0, sky light = 0, sturdy solid floor, clear headspace
        SpawnMarkerType marker = SpawnLightEvaluator.evaluate(
                true,  // isSurfaceSturdy
                false, // isHeadspaceObstructed
                0,     // blockLight
                0      // skyLight
        );
        assertEquals(SpawnMarkerType.RED, marker);
        assertTrue(marker.isVisible());
        assertEquals(1.0f, marker.getRed(), 1E-5);
        assertEquals(0.0f, marker.getGreen(), 1E-5);
        assertEquals(0.0f, marker.getBlue(), 1E-5);
    }

    @Test
    void testNightExposedMobSpawnYellowCross() {
        // Outdoor surface: block light = 0, sky light = 15, sturdy solid floor, clear headspace
        SpawnMarkerType marker = SpawnLightEvaluator.evaluate(
                true,
                false,
                0,
                15
        );
        assertEquals(SpawnMarkerType.YELLOW, marker);
        assertTrue(marker.isVisible());
        assertEquals(1.0f, marker.getRed(), 1E-5);
        assertEquals(1.0f, marker.getGreen(), 1E-5);
        assertEquals(0.0f, marker.getBlue(), 1E-5);
    }

    @Test
    void testIlluminatedSurfaceSafeNoCross() {
        // Torch placed: block light = 7, sky light = 0
        SpawnMarkerType marker = SpawnLightEvaluator.evaluate(
                true,
                false,
                7,
                0
        );
        assertEquals(SpawnMarkerType.NONE, marker);
        assertFalse(marker.isVisible());
    }

    @Test
    void testNonSturdySurfaceRendersNoCross() {
        // Glass, bottom slab, or stairs: non-sturdy surface
        SpawnMarkerType marker = SpawnLightEvaluator.evaluate(
                false, // non-sturdy
                false,
                0,
                0
        );
        assertEquals(SpawnMarkerType.NONE, marker);
        assertFalse(marker.isVisible());
    }

    @Test
    void testObstructedHeadspaceRendersNoCross() {
        // Solid block directly above spawn position
        SpawnMarkerType marker = SpawnLightEvaluator.evaluate(
                true,
                true, // headspace obstructed
                0,
                0
        );
        assertEquals(SpawnMarkerType.NONE, marker);
        assertFalse(marker.isVisible());
    }

    @Test
    void testAsyncLightWorkerVolumeDimensions() {
        assertEquals(16, AsyncLightWorkerVolume.HORIZONTAL_RADIUS);
        assertEquals(8, AsyncLightWorkerVolume.VERTICAL_RADIUS);
        assertEquals(33, AsyncLightWorkerVolume.HORIZONTAL_SPAN);
        assertEquals(17, AsyncLightWorkerVolume.VERTICAL_SPAN);
        assertEquals(18513, AsyncLightWorkerVolume.TOTAL_SCANNED_VOLUME);
        assertEquals(18513, AsyncLightWorkerVolume.getVolumeBlockCount());
    }

    @Test
    void testChunkBoundaryBoundingBoxCalculation() {
        // Player at (25, 64, -35)
        // chunkX = 25 >> 4 = 1, chunkZ = -35 >> 4 = -3
        // minX = 1 << 4 = 16, maxX = 32
        // minZ = -3 << 4 = -48, maxZ = -32
        ChunkBoundaryVisualizer.ChunkBoundingBox box = ChunkBoundaryVisualizer.computeChunkBounds(
                25, -35, -64, 320
        );
        assertEquals(16, box.minX());
        assertEquals(32, box.maxX());
        assertEquals(-48, box.minZ());
        assertEquals(-32, box.maxZ());
        assertEquals(-64, box.minY());
        assertEquals(320, box.maxY());
    }

    @Test
    void testSlimeChunkPrngDeterminism() {
        long seed = 123456789L;
        // Verify determinism for chunk (5, -12)
        boolean isSlime1 = ChunkBoundaryVisualizer.isSlimeChunk(seed, 5, -12);
        boolean isSlime2 = ChunkBoundaryVisualizer.isSlimeChunk(seed, 5, -12);
        assertEquals(isSlime1, isSlime2);

        // Verify across 100 random chunks roughly 10% are slime chunks
        int slimeCount = 0;
        for (int cx = 0; cx < 100; cx++) {
            if (ChunkBoundaryVisualizer.isSlimeChunk(seed, cx, 0)) {
                slimeCount++;
            }
        }
        assertTrue(slimeCount >= 3 && slimeCount <= 25, "Expected ~10% slime chunks, got " + slimeCount);
    }

    @Test
    void testOverlayModeCycling() {
        ChunkBoundaryVisualizer visualizer = new ChunkBoundaryVisualizer();
        assertEquals(ChunkBoundaryVisualizer.OverlayMode.OFF, visualizer.getMode());

        assertEquals(ChunkBoundaryVisualizer.OverlayMode.LIGHT_OVERLAY, visualizer.cycleMode());
        assertEquals(ChunkBoundaryVisualizer.OverlayMode.LIGHT_OVERLAY_AND_CHUNK_BORDERS, visualizer.cycleMode());
        assertEquals(ChunkBoundaryVisualizer.OverlayMode.SPAWN_AND_SLIME_CHUNKS, visualizer.cycleMode());
        assertEquals(ChunkBoundaryVisualizer.OverlayMode.OFF, visualizer.cycleMode());
    }
}
