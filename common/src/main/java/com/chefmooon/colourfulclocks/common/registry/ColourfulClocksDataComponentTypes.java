package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.data.GlassDialComponent;
import com.chefmooon.colourfulclocks.common.data.TallMantelClockComponent;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksDataComponentTypes {
    public static final ResourceLocation POCKET_WATCH_CLOSED = dataComponent("pocket_watch_closed");
    public static final ResourceLocation POCKET_WATCH_WEATHERING = dataComponent("pocket_watch_weathering");
    public static final ResourceLocation PENDULUM_WEATHERING = dataComponent("pendulum_weathering");

    public static final ResourceLocation BORNHOLM_MIDDLE_GLASS_DATA = dataComponent("bornholm_trunk_data");
    public static final ResourceLocation BORNHOLM_TOP_GLASS_DATA = dataComponent("bornholm_dial_data");

    public static final ResourceLocation GLASS_DIAL_DATA = dataComponent("glass_dial_data");
    public static final ResourceLocation GLASS_DIAL_PENDULUM_DATA = dataComponent("glass_dial_pendulum_data");

    public static final ResourceLocation TALL_MANTEL_CLOCK_DATA = dataComponent("tall_mantel_clock_data");

    @ExpectPlatform
    public static DataComponentType<BornholmMiddleDoorComponent> getBornholmMiddleGlassData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<BornholmTopGlassComponent> getBornholmTopGlassData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<GlassDialComponent> getGlassDialData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<TallMantelClockComponent> getTallMantelClockData() {
        throw new AssertionError();
    }

    private static ResourceLocation dataComponent(String string) {
        return TextUtil.res(string);
    }

    public static void init() {
    }
}
