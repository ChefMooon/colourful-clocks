package com.chefmooon.colourfulclocks.common.util;

import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksItemProperties {

    public static ResourceLocation GLASS_TYPE = property("glass");
    public static ResourceLocation POCKET_WATCH_TYPE = property("pocket_watch");
    public static ResourceLocation TICKING = property("ticking");

    private static ResourceLocation property(String string) {
        return TextUtil.res(string);
    }

}
