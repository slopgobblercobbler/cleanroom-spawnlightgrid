package com.cleanroom.lightgrid;

import com.cleanroom.common.math.BlockVec3i;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AsyncLightWorkerVolume {
    public static final int HORIZONTAL_RADIUS = 16;
    public static final int VERTICAL_RADIUS = 8;
    public static final int HORIZONTAL_SPAN = HORIZONTAL_RADIUS * 2 + 1; // 33
    public static final int VERTICAL_SPAN = VERTICAL_RADIUS * 2 + 1;     // 17
    public static final int TOTAL_SCANNED_VOLUME = HORIZONTAL_SPAN * HORIZONTAL_SPAN * VERTICAL_SPAN; // 18,513

    public record LightMarkerRecord(
            BlockVec3i position,
            SpawnMarkerType type
    ) {}

    private final AtomicReference<List<LightMarkerRecord>> activeMarkers = new AtomicReference<>(Collections.emptyList());

    public List<LightMarkerRecord> getRenderableMarkers() {
        return activeMarkers.get();
    }

    public void setMarkers(List<LightMarkerRecord> markers) {
        activeMarkers.set(markers != null ? Collections.unmodifiableList(markers) : Collections.emptyList());
    }

    public void clear() {
        activeMarkers.set(Collections.emptyList());
    }

    public static int getVolumeBlockCount() {
        return TOTAL_SCANNED_VOLUME;
    }
}
