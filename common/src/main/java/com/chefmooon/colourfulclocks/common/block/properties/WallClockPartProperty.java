package com.chefmooon.colourfulclocks.common.block.properties;

import com.chefmooon.colourfulclocks.common.data.OffsetPair;
import com.chefmooon.colourfulclocks.common.data.OffsetRecord;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum WallClockPartProperty implements StringRepresentable {
    BASE(new OffsetPair(new OffsetRecord(0, 0, 0), new OffsetRecord(0, 0, 0))),
    BOTTOM_LEFT(new OffsetPair(new OffsetRecord(0, 0, 0), new OffsetRecord(0, 0, 0))),
    BOTTOM_RIGHT(new OffsetPair(new OffsetRecord(1, 0, 0), new OffsetRecord(2, 0, 0))),
    TOP_LEFT(new OffsetPair(new OffsetRecord(0, 1, 0), new OffsetRecord(0, 2, 0))),
    TOP_RIGHT(new OffsetPair(new OffsetRecord(1, 1, 0), new OffsetRecord(2, 2, 0))),

    CENTER(new OffsetPair(new OffsetRecord(1, 1, 0), new OffsetRecord(1, 1, 0))),
    BOTTOM_MIDDLE(new OffsetPair(new OffsetRecord(1, 0, 0), new OffsetRecord(1 , 0, 0))),
    LEFT_MIDDLE(new OffsetPair(new OffsetRecord(0, 1, 0), new OffsetRecord(0 , 1, 0))),
    TOP_MIDDLE(new OffsetPair(new OffsetRecord(1, 2, 0), new OffsetRecord(1 , 2, 0))),
    RIGHT_MIDDLE(new OffsetPair(new OffsetRecord(2, 1, 0), new OffsetRecord(2 , 1, 0))),
    ;

    private final OffsetPair offset; // first is 2x2, second is 3x3

    WallClockPartProperty(OffsetPair offset) {
        this.offset = offset;
    }

    public OffsetPair getOffset() {
        return offset;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
