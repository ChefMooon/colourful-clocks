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


    public static final DataComponentType<Boolean> POCKET_WATCH_CLOSED = register(
            ColourfulClocksDataComponentTypes.POCKET_WATCH_CLOSED.getPath(), (builder) -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
    );
    public static final DataComponentType<PocketWatchComponent> POCKET_WATCH = register(
            ColourfulClocksDataComponentTypes.POCKET_WATCH.getPath(), pocketWatchComponentBuilder -> pocketWatchComponentBuilder.persistent(PocketWatchComponent.CODEC).networkSynchronized(PocketWatchComponent.STREAM_CODEC)
    );
    public static final DataComponentType<PendulumComponent> PENDULUM = register(
            ColourfulClocksDataComponentTypes.PENDULUM.getPath(), pendulumComponentBuilder -> pendulumComponentBuilder.persistent(PendulumComponent.CODEC).networkSynchronized(PendulumComponent.STREAM_CODEC)
    );
    public static final DataComponentType<HandbellComponent> HANDBELL_DATA = register(
            ColourfulClocksDataComponentTypes.HANDBELL.getPath(), handBellComponentBuilder -> handBellComponentBuilder.persistent(HandbellComponent.CODEC).networkSynchronized(HandbellComponent.STREAM_CODEC)
    );

    public static final DataComponentType<BornholmMiddleDoorComponent> BORNHOLM_MIDDLE_GLASS_DATA = register(
            ColourfulClocksDataComponentTypes.BORNHOLM_MIDDLE_GLASS.getPath(), bornholmMiddleDoorComponentBuilder -> bornholmMiddleDoorComponentBuilder.persistent(BornholmMiddleDoorComponent.CODEC).networkSynchronized(BornholmMiddleDoorComponent.STREAM_CODEC)
    );
    public static final DataComponentType<BornholmTopGlassComponent> BORNHOLM_TOP_GLASS_DATA = register(
            ColourfulClocksDataComponentTypes.BORNHOLM_TOP_GLASS.getPath(), bornholmTopGlassComponentBuilder -> bornholmTopGlassComponentBuilder.persistent(BornholmTopGlassComponent.CODEC).networkSynchronized(BornholmTopGlassComponent.STREAM_CODEC)
    );

    public static final DataComponentType<MantelClockComponent> MANTEL_CLOCK_DATA = register(
            ColourfulClocksDataComponentTypes.MANTEL_CLOCK.getPath(), clockComponentBuilder -> clockComponentBuilder.persistent(MantelClockComponent.CODEC).networkSynchronized(MantelClockComponent.STREAM_CODEC)
    );
    public static final DataComponentType<AlarmClockComponent> ALARM_CLOCK_DATA = register(
            ColourfulClocksDataComponentTypes.ALARM_CLOCK.getPath(), alarmClockComponentBuilder -> alarmClockComponentBuilder.persistent(AlarmClockComponent.CODEC).networkSynchronized(AlarmClockComponent.STREAM_CODEC)
    );

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return (DataComponentType) Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, TextUtil.res(name), ((DataComponentType.Builder)builder.apply(DataComponentType.builder())).build());
    }

    public static DataComponentType<Boolean> getPocketWatchClosedData() {
        return POCKET_WATCH_CLOSED;
    }

    public static DataComponentType<PocketWatchComponent> getPocketWatchData() {
        return POCKET_WATCH;
    }

    public static DataComponentType<PendulumComponent> getPendulumData() {
        return PENDULUM;
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

    public static DataComponentType<MantelClockComponent> getMantelClockData() {
        return MANTEL_CLOCK_DATA;
    }

    public static DataComponentType<AlarmClockComponent> getAlarmClockData() {
        return ALARM_CLOCK_DATA;
    }

    public static void register() {

    }
}
