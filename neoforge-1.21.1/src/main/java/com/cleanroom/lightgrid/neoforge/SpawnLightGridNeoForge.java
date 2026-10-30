package com.cleanroom.lightgrid.neoforge;

import com.cleanroom.lightgrid.SpawnLightGridCore;

public class SpawnLightGridNeoForge {
    private final SpawnLightGridCore core = new SpawnLightGridCore();

    public SpawnLightGridNeoForge() {
        System.out.println("[CleanRoom NeoForge] Bootstrapping " + SpawnLightGridCore.MOD_NAME);
        core.onInitializeCommon();
    }
}
