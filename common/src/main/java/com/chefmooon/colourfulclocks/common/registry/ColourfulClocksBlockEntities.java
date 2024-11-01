package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksBlockEntities {
    public static final ResourceLocation BORNHOLM_MIDDLE = blockEntity("bornholm_middle");
    public static final ResourceLocation BORNHOLM_TOP = blockEntity("bornholm_top");

    private static ResourceLocation blockEntity(String string) {
        return TextUtil.res(string);
    }

    public static void register() {
    }
}
