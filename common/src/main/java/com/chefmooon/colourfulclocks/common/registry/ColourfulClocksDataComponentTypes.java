package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.data.*;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
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

    @SuppressWarnings("unchecked")
    public static DataComponentType<Boolean> getPocketWatchClosedData() {
        return (DataComponentType<Boolean>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(POCKET_WATCH_CLOSED);
    }

    @SuppressWarnings("unchecked")
    public static DataComponentType<PocketWatchComponent> getPocketWatchData() {
        return (DataComponentType<PocketWatchComponent>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(POCKET_WATCH);
    }

    @SuppressWarnings("unchecked")
    public static DataComponentType<PendulumComponent> getPendulumData() {
        return (DataComponentType<PendulumComponent>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(PENDULUM);
    }

    @SuppressWarnings("unchecked")
    public static DataComponentType<HandbellComponent> getHandbellData() {
        return (DataComponentType<HandbellComponent>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(HANDBELL);
    }

    @SuppressWarnings("unchecked")
    public static DataComponentType<BornholmMiddleDoorComponent> getBornholmMiddleGlassData() {
        return (DataComponentType<BornholmMiddleDoorComponent>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(BORNHOLM_MIDDLE_GLASS);
    }

    @SuppressWarnings("unchecked")
    public static DataComponentType<BornholmTopGlassComponent> getBornholmTopGlassData() {
        return (DataComponentType<BornholmTopGlassComponent>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(BORNHOLM_TOP_GLASS);
    }

    @SuppressWarnings("unchecked")
    public static DataComponentType<MantelClockComponent> getMantelClockData() {
        return (DataComponentType<MantelClockComponent>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(MANTEL_CLOCK);
    }

    @SuppressWarnings("unchecked")
    public static DataComponentType<AlarmClockComponent> getAlarmClockData() {
        return (DataComponentType<AlarmClockComponent>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ALARM_CLOCK);
    }

    @SuppressWarnings("unchecked")
    public static DataComponentType<WallClockComponent> getWallClockData() {
        return (DataComponentType<WallClockComponent>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(WALL_CLOCK);
    }

    private static ResourceLocation dataComponent(String string) {
        return TextUtil.res(string);
    }

    public static void init() {
    }
}
