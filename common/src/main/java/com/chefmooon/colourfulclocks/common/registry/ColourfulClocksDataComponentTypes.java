package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksDataComponentTypes {
    public static final ResourceLocation POCKET_WATCH_WEATHERING = dataComponent("pocket_watch_weathering");
    public static final ResourceLocation PENDULUM_WEATHERING = dataComponent("pendulum_weathering");

    public static final ResourceLocation BORNHOLM_MIDDLE_GLASS_DATA = dataComponent("bornholm_middle_glass_data");
    public static final ResourceLocation BORNHOLM_TOP_GLASS_DATA = dataComponent("bornholm_top_glass_data");


    private static ResourceLocation dataComponent(String string) {
        return TextUtil.res(string);
    }
}
