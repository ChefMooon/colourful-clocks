package com.chefmooon.colourfulclocks.common.util;

import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksItemProperties {

    public static ResourceLocation GLASS_TYPE = property("glass");

    private static ResourceLocation property(String string) {
        return TextUtil.res(string);
    }

}
