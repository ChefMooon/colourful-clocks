package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public record BornholmMiddleDoorComponent(BornholmDoorTypes doorType, Optional<PendulumComponent> pendulum) {

    public static final Codec<BornholmMiddleDoorComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    BornholmDoorTypes.CODEC.fieldOf("glass").forGetter(BornholmMiddleDoorComponent::getDoorType),
                    PendulumComponent.CODEC.optionalFieldOf("pendulum").forGetter(BornholmMiddleDoorComponent::getPendulum)
            ).apply(instance, BornholmMiddleDoorComponent::new)
    );
    public static final StreamCodec<ByteBuf, BornholmMiddleDoorComponent> STREAM_CODEC = StreamCodec.composite(
            BornholmDoorTypes.STREAM_CODEC, BornholmMiddleDoorComponent::getDoorType,
            PendulumComponent.STREAM_CODEC.apply(ByteBufCodecs::optional), BornholmMiddleDoorComponent::getPendulum,
            BornholmMiddleDoorComponent::new
    );

    public BornholmDoorTypes getDoorType() {
        return doorType;
    }

    public Optional<PendulumComponent> getPendulum() {
        return pendulum;
    }

    public static BornholmMiddleDoorComponent getDefaultValue() {
        return new BornholmMiddleDoorComponent(BornholmDoorTypes.BASE, Optional.of(PendulumComponent.getDefaultValue()));
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("bornholm_trunk_data", (Tag)CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static BornholmMiddleDoorComponent load(@Nullable CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("bornholm_trunk_data")).result().orElse(getDefaultValue());
    }
}
