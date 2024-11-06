package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.core.BornholmMiddleDoorRecord;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassRecord;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.function.UnaryOperator;

public class ColourfulClocksDataComponentTypesImpl {

    public static final DataComponentType<Integer> POCKET_WATCH_WEATHERING = register(
            ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING.getPath(), (builder) -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
    );

    public static final DataComponentType<Integer> PENDULUM_WEATHERING = register(
            ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING.getPath(), (builder) -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
    );


    public static final DataComponentType<BornholmTopGlassRecord> BORNHOLM_TOP_GLASS_DATA = register(
            ColourfulClocksDataComponentTypes.BORNHOLM_TOP_GLASS_DATA.getPath(), bornholmTopGlassRecordBuilder -> bornholmTopGlassRecordBuilder.persistent(BornholmTopGlassRecord.CODEC).networkSynchronized(BornholmTopGlassRecord.STREAM_CODEC)
    );

    public static final DataComponentType<BornholmMiddleDoorRecord> BORNHOLM_MIDDLE_GLASS_DATA = register(
            ColourfulClocksDataComponentTypes.BORNHOLM_MIDDLE_GLASS_DATA.getPath(), bornholmTopGlassRecordBuilder -> bornholmTopGlassRecordBuilder.persistent(BornholmMiddleDoorRecord.CODEC).networkSynchronized(BornholmMiddleDoorRecord.STREAM_CODEC)
    );

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return (DataComponentType) Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, TextUtil.res(name), ((DataComponentType.Builder)builder.apply(DataComponentType.builder())).build());
    }

    public static void register() {

    }
}
