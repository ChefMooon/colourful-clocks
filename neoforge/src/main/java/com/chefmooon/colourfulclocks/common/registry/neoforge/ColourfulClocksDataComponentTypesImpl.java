package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ColourfulClocksDataComponentTypesImpl {

    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ColourfulClocks.MOD_ID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> POCKET_WATCH_OPEN = DATA_COMPONENTS.registerComponentType(
            "pocket_watch_open", (builder) -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
    );


    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }

}
