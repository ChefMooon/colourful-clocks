package com.chefmooon.colourfulclocks.common.util;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

public class TextUtil {

    public static MutableComponent getTranslatable(String string, Object... args) {
        return Component.translatable(ColourfulClocks.MOD_ID + "." + string, args);
    }

    public static ResourceLocation res(String string) {
        return ResourceLocation.fromNamespaceAndPath(ColourfulClocks.MOD_ID, string);
    }
}
