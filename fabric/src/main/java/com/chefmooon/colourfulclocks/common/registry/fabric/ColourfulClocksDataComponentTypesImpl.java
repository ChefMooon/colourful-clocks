package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.data.*;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.function.UnaryOperator;

public class ColourfulClocksDataComponentTypesImpl {

    public static final DataComponentType<AlarmClockComponent> ALARM_CLOCK_DATA = register(
            ColourfulClocksDataComponentTypes.ALARM_CLOCK_DATA.getPath(), alarmClockComponentBuilder -> alarmClockComponentBuilder.persistent(AlarmClockComponent.CODEC).networkSynchronized(AlarmClockComponent.STREAM_CODEC)
    );
    public static final DataComponentType<HandbellComponent> HANDBELL_DATA = register(
            ColourfulClocksDataComponentTypes.HANDBELL_DATA.getPath(), handBellComponentBuilder -> handBellComponentBuilder.persistent(HandbellComponent.CODEC).networkSynchronized(HandbellComponent.STREAM_CODEC)
    );
    public static final DataComponentType<Integer> HANDBELL_WEATHERING = register(
            ColourfulClocksDataComponentTypes.HANDBELL_WEATHERING.getPath(), (builder) -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
    );

    public static final DataComponentType<Boolean> POCKET_WATCH_CLOSED = register(
            ColourfulClocksDataComponentTypes.POCKET_WATCH_CLOSED.getPath(), (builder) -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
    );

    public static final DataComponentType<Integer> POCKET_WATCH_WEATHERING = register(
            ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING.getPath(), (builder) -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
    );

    public static final DataComponentType<Integer> PENDULUM_WEATHERING = register(
            ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING.getPath(), (builder) -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
    );

    public static final DataComponentType<BornholmTopGlassComponent> BORNHOLM_TOP_GLASS_DATA = register(
            ColourfulClocksDataComponentTypes.BORNHOLM_TOP_GLASS_DATA.getPath(), bornholmTopGlassComponentBuilder -> bornholmTopGlassComponentBuilder.persistent(BornholmTopGlassComponent.CODEC).networkSynchronized(BornholmTopGlassComponent.STREAM_CODEC)
    );

    public static final DataComponentType<BornholmMiddleDoorComponent> BORNHOLM_MIDDLE_GLASS_DATA = register(
            ColourfulClocksDataComponentTypes.BORNHOLM_MIDDLE_GLASS_DATA.getPath(), bornholmMiddleDoorComponentBuilder -> bornholmMiddleDoorComponentBuilder.persistent(BornholmMiddleDoorComponent.CODEC).networkSynchronized(BornholmMiddleDoorComponent.STREAM_CODEC)
    );

    public static final DataComponentType<GlassDialComponent> GLASS_DIAL_DATA = register(
            ColourfulClocksDataComponentTypes.GLASS_DIAL_DATA.getPath(), glassDialComponentBuilder -> glassDialComponentBuilder.persistent(GlassDialComponent.CODEC).networkSynchronized(GlassDialComponent.STREAM_CODEC)
    );

    public static final DataComponentType<ClockComponent> CLOCK_DATA = register(
            ColourfulClocksDataComponentTypes.CLOCK_DATA.getPath(), clockComponentBuilder -> clockComponentBuilder.persistent(ClockComponent.CODEC).networkSynchronized(ClockComponent.STREAM_CODEC)
    );

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return (DataComponentType) Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, TextUtil.res(name), ((DataComponentType.Builder)builder.apply(DataComponentType.builder())).build());
    }

    public static DataComponentType<Integer> getHandbellWeatheringData() {
        return HANDBELL_WEATHERING;
    }

    public static DataComponentType<AlarmClockComponent> getAlarmClockData() {
        return ALARM_CLOCK_DATA;
    }

    public static DataComponentType<HandbellComponent> getHandbellData() {
        return HANDBELL_DATA;
    }

    public static DataComponentType<BornholmMiddleDoorComponent> getBornholmMiddleGlassData() {
        return BORNHOLM_MIDDLE_GLASS_DATA;
    }

    public static DataComponentType<BornholmTopGlassComponent> getBornholmTopGlassData() {
        return BORNHOLM_TOP_GLASS_DATA;
    }

    public static DataComponentType<ClockComponent> getClockData() {
        return CLOCK_DATA;
    }

    public static DataComponentType<GlassDialComponent> getGlassDialData() {
        return GLASS_DIAL_DATA;
    }

    public static void register() {

    }
}
