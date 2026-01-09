package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.HandbellHandleTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.codec.StreamCodec;

public record HandbellComponent(HandbellHandleTypes materialType) {
    public static final Codec<HandbellComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    HandbellHandleTypes.CODEC.optionalFieldOf("handle", HandbellHandleTypes.OAK).forGetter(HandbellComponent::materialType)
            ).apply(instance, HandbellComponent::new)
    );
    public static final StreamCodec<ByteBuf, HandbellComponent> STREAM_CODEC = StreamCodec.composite(
            HandbellHandleTypes.STREAM_CODEC, HandbellComponent::materialType,
            HandbellComponent::new
    );

    public HandbellHandleTypes getMaterialType() {
        return materialType;
    }

    public static HandbellComponent getDefaultValue() {
        return new HandbellComponent(HandbellHandleTypes.OAK);
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("handbell_data", CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static HandbellComponent load(CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("handbell_data")).result().orElse(getDefaultValue());
    }
}
