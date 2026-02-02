package com.chefmooon.colourfulclocks.common.block.properties;

import net.minecraft.util.StringRepresentable;

import java.util.EnumSet;
import java.util.Locale;

public enum WallClockPartProperty implements StringRepresentable {

    BASE(0, 0, false),
    BOTTOM_LEFT(-1, -1, false),
    BOTTOM_RIGHT(0, -1, false),
    TOP_LEFT(-1, 0, false),
    TOP_RIGHT(0, 0, false),

    CENTER(0, 0, true),
    BOTTOM_MIDDLE(0, -1, true),
    LEFT_MIDDLE(-1, 0, true),
    TOP_MIDDLE(0, 1, true),
    RIGHT_MIDDLE(1, 0, true),

    BOTTOM_LEFT_XL(-1, -1, true),
    BOTTOM_RIGHT_XL(1, -1, true),
    TOP_LEFT_XL(-1, 1, true),
    TOP_RIGHT_XL(1, 1, true),
    ;
    private final int xOffset;
    private final int yOffset;
    private final boolean isXL;

    WallClockPartProperty(int xOffset, int yOffset, boolean isXL) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.isXL = isXL;
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public boolean isXL() {
        return isXL;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public boolean isController() {
        return this == BASE || this == TOP_RIGHT || this == CENTER;
    }

    public static EnumSet<WallClockPartProperty> smallParts() {
        return EnumSet.of(BASE);
    }

    public static EnumSet<WallClockPartProperty> mediumParts() {
        return EnumSet.of(BOTTOM_LEFT, BOTTOM_RIGHT, TOP_LEFT, TOP_RIGHT);
    }

    public static EnumSet<WallClockPartProperty> largeParts() {
        return EnumSet.of(
                BOTTOM_LEFT_XL, BOTTOM_MIDDLE, BOTTOM_RIGHT_XL,
                LEFT_MIDDLE, CENTER, RIGHT_MIDDLE,
                TOP_LEFT_XL, TOP_MIDDLE, TOP_RIGHT_XL
        );
    }
}
