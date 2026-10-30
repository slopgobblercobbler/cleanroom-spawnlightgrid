package com.cleanroom.lightgrid.fabric;

import com.cleanroom.lightgrid.SpawnLightGridCore;

public class SpawnLightGridFabric {
    private final SpawnLightGridCore core = new SpawnLightGridCore();

    public void onInitialize() {
        System.out.println("[CleanRoom Fabric] Bootstrapping " + SpawnLightGridCore.MOD_NAME);
        core.onInitializeCommon();
    }
}
