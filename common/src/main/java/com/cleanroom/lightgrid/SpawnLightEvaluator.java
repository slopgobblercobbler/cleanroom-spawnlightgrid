package com.cleanroom.lightgrid;

public final class SpawnLightEvaluator {

    /**
     * Evaluates spawn marker status adhering to modern Minecraft 1.18+ light rules.
     * Hostile mobs only spawn if Block Light is strictly 0.
     *
     * @param isSurfaceSturdy       whether the top face of the block below is sturdy/solid
     * @param isHeadspaceObstructed whether the space above is blocked by a solid block
     * @param blockLight            artificial block light level at spawn position (0..15)
     * @param skyLight              ambient sky light level at spawn position (0..15)
     * @return RED for immediate hazard, YELLOW for night danger, NONE for safe/invalid
     */
    public static SpawnMarkerType evaluate(
            boolean isSurfaceSturdy,
            boolean isHeadspaceObstructed,
            int blockLight,
            int skyLight
    ) {
        if (!isSurfaceSturdy) {
            return SpawnMarkerType.NONE;
        }

        if (isHeadspaceObstructed) {
            return SpawnMarkerType.NONE;
        }

        // Minecraft 1.18+ Rule: Hostile mobs only spawn if block light == 0
        if (blockLight > 0) {
            return SpawnMarkerType.NONE; // Illuminated surface is safe
        }

        if (skyLight == 0) {
            return SpawnMarkerType.RED; // Deep cave / pitch black - spawns anytime
        } else {
            return SpawnMarkerType.YELLOW; // Surface exposed to sky - spawns at night
        }
    }
}
