package com.cleanroom.lightgrid;

import com.cleanroom.common.api.CommonModInitializer;
import com.cleanroom.common.telemetry.CleanRoomTelemetry;

public class SpawnLightGridCore implements CommonModInitializer {
    public static final String MOD_ID = "cleanroom_spawnlightgrid";
    public static final String MOD_NAME = "Spawn Light Grid CleanRoom";

    private static SpawnLightGridCore instance;
    private final AsyncLightWorkerVolume workerVolume = new AsyncLightWorkerVolume();
    private final ChunkBoundaryVisualizer chunkVisualizer = new ChunkBoundaryVisualizer();

    public static SpawnLightGridCore getInstance() {
        if (instance == null) {
            instance = new SpawnLightGridCore();
        }
        return instance;
    }

    public AsyncLightWorkerVolume getWorkerVolume() {
        return workerVolume;
    }

    public ChunkBoundaryVisualizer getChunkVisualizer() {
        return chunkVisualizer;
    }

    @Override
    public String getModId() {
        return MOD_ID;
    }

    @Override
    public void onInitializeCommon() {
        instance = this;
        CleanRoomTelemetry.record("INIT", "Initialized core logic for " + MOD_NAME + " (" + MOD_ID + ")");
        System.out.println("[CleanRoom] Initialized core logic for " + MOD_NAME + " (" + MOD_ID + ")");
    }
}
