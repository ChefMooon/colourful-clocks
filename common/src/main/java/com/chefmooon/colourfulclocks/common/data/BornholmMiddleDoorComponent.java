package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.Nullable;

public record BornholmMiddleDoorComponent(BornholmDoorTypes doorType, PendulumTypes pendulumType) {

    public static final Codec<BornholmMiddleDoorComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    BornholmDoorTypes.CODEC.optionalFieldOf("glass", BornholmDoorTypes.BASE).forGetter(BornholmMiddleDoorComponent::getDoorType),
                    PendulumTypes.CODEC.optionalFieldOf("pendulum", PendulumTypes.EMPTY).forGetter(BornholmMiddleDoorComponent::getPendulumType)
            ).apply(instance, BornholmMiddleDoorComponent::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, BornholmMiddleDoorComponent> STREAM_CODEC = StreamCodec.composite(
            BornholmDoorTypes.STREAM_CODEC, BornholmMiddleDoorComponent::getDoorType,
            PendulumTypes.STREAM_CODEC, BornholmMiddleDoorComponent::getPendulumType,
            BornholmMiddleDoorComponent::new
    );

    public BornholmDoorTypes getDoorType() {
        return doorType;
    }

    public PendulumTypes getPendulumType() {
        return pendulumType;
    }

    public static BornholmMiddleDoorComponent getDefaultValue() {
        return new BornholmMiddleDoorComponent(BornholmDoorTypes.BASE, PendulumTypes.EMPTY);
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("bornholm_trunk_data", (Tag)CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static BornholmMiddleDoorComponent load(@Nullable CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("bornholm_trunk_data")).result().orElse(getDefaultValue());
    }
}
