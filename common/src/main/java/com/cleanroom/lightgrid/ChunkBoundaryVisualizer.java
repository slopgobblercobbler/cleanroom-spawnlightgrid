package com.cleanroom.lightgrid;

import java.util.Random;

public class ChunkBoundaryVisualizer {

    public enum OverlayMode {
        OFF,
        LIGHT_OVERLAY,
        LIGHT_OVERLAY_AND_CHUNK_BORDERS,
        SPAWN_AND_SLIME_CHUNKS;

        public OverlayMode next() {
            OverlayMode[] vals = values();
            return vals[(ordinal() + 1) % vals.length];
        }
    }

    public record ChunkBoundingBox(
            int minX,
            int minZ,
            int maxX,
            int maxZ,
            int minY,
            int maxY
    ) {}

    private OverlayMode mode = OverlayMode.OFF;

    public OverlayMode getMode() {
        return mode;
    }

    public void setMode(OverlayMode mode) {
        this.mode = mode != null ? mode : OverlayMode.OFF;
    }

    public OverlayMode cycleMode() {
        this.mode = this.mode.next();
        return this.mode;
    }

    public static ChunkBoundingBox computeChunkBounds(int playerX, int playerZ, int minY, int maxY) {
        int chunkX = playerX >> 4;
        int chunkZ = playerZ >> 4;
        int minX = chunkX << 4;
        int minZ = chunkZ << 4;
        int maxX = minX + 16;
        int maxZ = minZ + 16;
        return new ChunkBoundingBox(minX, minZ, maxX, maxZ, minY, maxY);
    }

    public static boolean isSlimeChunk(long seed, int chunkX, int chunkZ) {
        long term1 = (long) chunkX * chunkX * 4987142L;
        long term2 = (long) chunkX * 5947611L;
        long term3 = (long) chunkZ * chunkZ * 4392871L;
        long term4 = (long) chunkZ * 389711L;
        long internalSeed = (seed + term1 + term2 + term3 + term4) ^ 987234911L;
        Random rnd = new Random(internalSeed);
        return rnd.nextInt(10) == 0;
    }
}
