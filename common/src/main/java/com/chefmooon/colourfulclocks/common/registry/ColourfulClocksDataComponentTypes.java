package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.data.*;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksDataComponentTypes {

    public static final ResourceLocation POCKET_WATCH_CLOSED = dataComponent("pocket_watch_closed");
    public static final ResourceLocation POCKET_WATCH = dataComponent("pocket_watch");
    public static final ResourceLocation PENDULUM = dataComponent("pendulum");
    public static final ResourceLocation HANDBELL = dataComponent("handbell");

    public static final ResourceLocation BORNHOLM_MIDDLE_GLASS = dataComponent("bornholm_trunk");
    public static final ResourceLocation BORNHOLM_TOP_GLASS = dataComponent("bornholm_dial");

    public static final ResourceLocation MANTEL_CLOCK = dataComponent("mantel_clock");
    public static final ResourceLocation ALARM_CLOCK = dataComponent("alarm_clock");
    public static final ResourceLocation WALL_CLOCK = dataComponent("wall_clock");

    @ExpectPlatform
    public static DataComponentType<Boolean> getPocketWatchClosedData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<PocketWatchComponent> getPocketWatchData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<PendulumComponent> getPendulumData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<HandbellComponent> getHandbellData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<BornholmMiddleDoorComponent> getBornholmMiddleGlassData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<BornholmTopGlassComponent> getBornholmTopGlassData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<MantelClockComponent> getMantelClockData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<AlarmClockComponent> getAlarmClockData() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static DataComponentType<WallClockComponent> getWallClockData() {
        throw new AssertionError();
    }

    private static ResourceLocation dataComponent(String string) {
        return TextUtil.res(string);
    }

    public static void init() {
    }
}
