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

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> POCKET_WATCH_CLOSED = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.POCKET_WATCH_CLOSED.getPath(), (builder) -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<PocketWatchComponent>> POCKET_WATCH = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.POCKET_WATCH.getPath(), pocketWatchComponentBuilder -> pocketWatchComponentBuilder.persistent(PocketWatchComponent.CODEC).networkSynchronized(PocketWatchComponent.STREAM_CODEC)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<PendulumComponent>> PENDULUM = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.PENDULUM.getPath(), pendulumComponentBuilder -> pendulumComponentBuilder.persistent(PendulumComponent.CODEC).networkSynchronized(PendulumComponent.STREAM_CODEC)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<HandbellComponent>> HANDBELL_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.HANDBELL.getPath(), handBellComponentBuilder -> handBellComponentBuilder.persistent(HandbellComponent.CODEC).networkSynchronized(HandbellComponent.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BornholmMiddleDoorComponent>> BORNHOLM_MIDDLE_GLASS_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.BORNHOLM_MIDDLE_GLASS.getPath(), bornholmTopGlassRecordBuilder -> bornholmTopGlassRecordBuilder.persistent(BornholmMiddleDoorComponent.CODEC).networkSynchronized(BornholmMiddleDoorComponent.STREAM_CODEC)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BornholmTopGlassComponent>> BORNHOLM_TOP_GLASS_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.BORNHOLM_TOP_GLASS.getPath(), bornholmTopGlassRecordBuilder -> bornholmTopGlassRecordBuilder.persistent(BornholmTopGlassComponent.CODEC).networkSynchronized(BornholmTopGlassComponent.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MantelClockComponent>> MANTEL_CLOCK_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.MANTEL_CLOCK.getPath(), clockComponentBuilder -> clockComponentBuilder.persistent(MantelClockComponent.CODEC).networkSynchronized(MantelClockComponent.STREAM_CODEC)
    );
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AlarmClockComponent>> ALARM_CLOCK_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.ALARM_CLOCK.getPath(), alarmClockComponentBuilder -> alarmClockComponentBuilder.persistent(AlarmClockComponent.CODEC).networkSynchronized(AlarmClockComponent.STREAM_CODEC)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<WallClockComponent>> WALL_CLOCK_DATA = DATA_COMPONENTS.registerComponentType(
            ColourfulClocksDataComponentTypes.WALL_CLOCK.getPath(), wallClockComponentBuilder -> wallClockComponentBuilder.persistent(WallClockComponent.CODEC).networkSynchronized(WallClockComponent.STREAM_CODEC)
    );

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }

}
