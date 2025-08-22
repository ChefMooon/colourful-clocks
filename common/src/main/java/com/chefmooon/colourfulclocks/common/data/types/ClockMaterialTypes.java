package com.chefmooon.colourfulclocks.common.data.types;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum ClockMaterialTypes implements StringRepresentable {
    WOOD(),
    STONE(),
    DEEPSLATE(),
    TUFF_BRICK(),
    BRICK(),
    MUD_BRICK(),
    NETHER_BRICK(),
    ;

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
