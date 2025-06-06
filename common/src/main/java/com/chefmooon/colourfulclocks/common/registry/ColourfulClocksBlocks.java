package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksBlocks {
    public static ResourceLocation BORNHOLM_BASE = block("bornholm_base");
    public static ResourceLocation BORNHOLM_MIDDLE = block("bornholm_middle");
    public static ResourceLocation BORNHOLM_TOP = block("bornholm_top");

    public static ResourceLocation MANTEL_CLOCK = block("mantel_clock");
    public static ResourceLocation TALL_MANTEL_CLOCK = block("tall_mantel_clock");

    private static ResourceLocation block(String string) {
        return TextUtil.res(string);
    }

    public static void init() {
    }
}
