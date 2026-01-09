package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksRecipeSerializers {

    public static final ResourceLocation WAXED_COPPER_HANDBELL = recipeSerializer("waxed_copper_handbell");
    private static ResourceLocation recipeSerializer(String string) {
        return TextUtil.res(string);
    }
    public static void init() {
    }
}
