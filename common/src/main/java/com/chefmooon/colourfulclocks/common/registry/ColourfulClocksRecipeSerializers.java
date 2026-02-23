package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksRecipeSerializers {

    public static final ResourceLocation WAXED_COPPER_HANDBELL = recipeSerializer("waxed_copper_handbell");
    public static final ResourceLocation HANDBELL_HANDLE = recipeSerializer("handbell_handle");
    public static final ResourceLocation BORNHOLM_MIDDLE = recipeSerializer("bornholm_trunk");
    public static final ResourceLocation BORNHOLM_TOP = recipeSerializer("bornholm_dial");
    public static final ResourceLocation MANTEL_CLOCK = recipeSerializer("mantel_clock");
    public static final ResourceLocation ALARM_CLOCK = recipeSerializer("alarm_clock");
    public static final ResourceLocation WALL_CLOCK = recipeSerializer("wall_clock");
    private static ResourceLocation recipeSerializer(String string) {
        return TextUtil.res(string);
    }
    public static void init() {
    }
}
