package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.HandbellHandleTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;

public record HandbellComponent(HandbellTypes type, HandbellHandleTypes materialType, Optional<Integer> weathering) {
    public static final Codec<HandbellComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    HandbellTypes.CODEC.fieldOf("type").forGetter(HandbellComponent::getType),
                    HandbellHandleTypes.CODEC.optionalFieldOf("handle", HandbellHandleTypes.OAK).forGetter(HandbellComponent::materialType),
                    Codec.INT.optionalFieldOf("weathering").forGetter(HandbellComponent::weathering)
            ).apply(instance, HandbellComponent::new)
    );
    public static final StreamCodec<ByteBuf, HandbellComponent> STREAM_CODEC = StreamCodec.composite(
            HandbellTypes.STREAM_CODEC, HandbellComponent::getType,
            HandbellHandleTypes.STREAM_CODEC, HandbellComponent::materialType,
            ByteBufCodecs.INT.apply(ByteBufCodecs::optional), HandbellComponent::weathering,
            HandbellComponent::new
    );

    public HandbellTypes getType() {
        return type;
    }

    public HandbellHandleTypes getMaterialType() {
        return materialType;
    }

    public Optional<Integer> getWeathering() {
        return weathering;
    }

    public static HandbellComponent getBaseValue(HandbellTypes type) {
        return new HandbellComponent(type, HandbellHandleTypes.OAK, Optional.empty());
    }

    public static HandbellComponent getWeatheringCopperValue(HandbellTypes type) {
        return new HandbellComponent(type, HandbellHandleTypes.OAK, Optional.of(0));
    }

    public static HandbellComponent getCopperValue(HandbellTypes type) {
        return new HandbellComponent(type, HandbellHandleTypes.OAK, Optional.empty());
    }

    public static HandbellComponent getDefaultValue() {
        return new HandbellComponent(HandbellTypes.IRON, HandbellHandleTypes.OAK, Optional.empty());
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("handbell", CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static HandbellComponent load(CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("handbell")).result().orElse(getDefaultValue());
    }
}
