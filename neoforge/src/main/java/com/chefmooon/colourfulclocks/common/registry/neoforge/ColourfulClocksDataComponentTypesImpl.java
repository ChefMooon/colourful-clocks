package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
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

    public static DataComponentType<BornholmMiddleDoorComponent> getBornholmMiddleGlassData() {
        return BORNHOLM_MIDDLE_GLASS_DATA.get();
    }

    public static DataComponentType<BornholmTopGlassComponent> getBornholmTopGlassData() {
        return BORNHOLM_TOP_GLASS_DATA.get();
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }

}
