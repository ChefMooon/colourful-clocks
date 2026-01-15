package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.data.*;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ColourfulClocksDataComponentTypesImpl {

    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ColourfulClocks.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AlarmClockComponent>> ALARM_CLOCK_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.ALARM_CLOCK_DATA.getPath(), alarmClockComponentBuilder -> alarmClockComponentBuilder.persistent(AlarmClockComponent.CODEC).networkSynchronized(AlarmClockComponent.STREAM_CODEC)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<HandbellComponent>> HANDBELL_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.HANDBELL_DATA.getPath(), handBellComponentBuilder -> handBellComponentBuilder.persistent(HandbellComponent.CODEC).networkSynchronized(HandbellComponent.STREAM_CODEC)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> HANDBELL_WEATHERING = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.HANDBELL_WEATHERING.getPath(), (builder) -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> POCKET_WATCH_CLOSED = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.POCKET_WATCH_CLOSED.getPath(), (builder) -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> POCKET_WATCH_WEATHERING = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING.getPath(), (builder) -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> PENDULUM_WEATHERING = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING.getPath(), (builder) -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BornholmTopGlassComponent>> BORNHOLM_TOP_GLASS_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.BORNHOLM_TOP_GLASS_DATA.getPath(), bornholmTopGlassRecordBuilder -> bornholmTopGlassRecordBuilder.persistent(BornholmTopGlassComponent.CODEC).networkSynchronized(BornholmTopGlassComponent.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BornholmMiddleDoorComponent>> BORNHOLM_MIDDLE_GLASS_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.BORNHOLM_MIDDLE_GLASS_DATA.getPath(), bornholmTopGlassRecordBuilder -> bornholmTopGlassRecordBuilder.persistent(BornholmMiddleDoorComponent.CODEC).networkSynchronized(BornholmMiddleDoorComponent.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<GlassDialComponent>> GLASS_DIAL_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.GLASS_DIAL_DATA.getPath(), glassDialComponentBuilder -> glassDialComponentBuilder.persistent(GlassDialComponent.CODEC).networkSynchronized(GlassDialComponent.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ClockComponent>> CLOCK_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.CLOCK_DATA.getPath(), clockComponentBuilder -> clockComponentBuilder.persistent(ClockComponent.CODEC).networkSynchronized(ClockComponent.STREAM_CODEC)
    );

    public static DataComponentType<BornholmMiddleDoorComponent> getBornholmMiddleGlassData() {
        return BORNHOLM_MIDDLE_GLASS_DATA.get();
    }

    public static DataComponentType<BornholmTopGlassComponent> getBornholmTopGlassData() {
        return BORNHOLM_TOP_GLASS_DATA.get();
    }

    public static DataComponentType<Integer> getHandbellWeatheringData() {
        return HANDBELL_WEATHERING.get();
    }

    public static DataComponentType<AlarmClockComponent> getAlarmClockData() {
        return ALARM_CLOCK_DATA.get();
    }

    public static DataComponentType<HandbellComponent> getHandbellData() {
        return HANDBELL_DATA.get();
    }

    public static DataComponentType<ClockComponent> getClockData() {
        return CLOCK_DATA.get();
    }

    public static DataComponentType<GlassDialComponent> getGlassDialData() {
        return GLASS_DIAL_DATA.get();
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }

}
