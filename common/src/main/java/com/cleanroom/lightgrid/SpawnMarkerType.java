package com.cleanroom.lightgrid;

public enum SpawnMarkerType {
    RED(1.0f, 0.0f, 0.0f),       // Block light = 0 & Sky light = 0: hostile mob spawns anytime
    YELLOW(1.0f, 1.0f, 0.0f),    // Block light = 0 & Sky light > 0: hostile mob spawns at night / storms
    NONE(0.0f, 0.0f, 0.0f);      // Safe (block light > 0 or non-spawnable surface)

    private final float red;
    private final float green;
    private final float blue;

    SpawnMarkerType(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public boolean isVisible() {
        return this != NONE;
    }
}
