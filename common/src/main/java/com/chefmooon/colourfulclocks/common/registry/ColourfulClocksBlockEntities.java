package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksBlockEntities {
    public static final ResourceLocation BORNHOLM_MIDDLE = blockEntity("bornholm_middle");
    public static final ResourceLocation BORNHOLM_TOP = blockEntity("bornholm_top");

    public static final ResourceLocation MANTEL_CLOCK = blockEntity("mantel_clock");
    public static final ResourceLocation TALL_MANTEL_CLOCK = blockEntity("tall_mantel_clock");
    public static final ResourceLocation WALL_CLOCK = blockEntity("wall_clock");

    private static ResourceLocation blockEntity(String string) {
        return TextUtil.res(string);
    }

    public static void init() {
    }
}
