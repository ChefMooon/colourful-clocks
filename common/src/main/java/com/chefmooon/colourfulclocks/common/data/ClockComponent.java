package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
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

public record ClockComponent(Optional<BornholmTopGlassTypes> topGlassType, Optional<PocketWatchTypes> pocketWatchType, Optional<PendulumTypes> pendulumType, Optional<Boolean> ticking) {
    public static final Codec<ClockComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BornholmTopGlassTypes.CODEC.optionalFieldOf("glass").forGetter(ClockComponent::getGlassType),
            PocketWatchTypes.CODEC.optionalFieldOf("pocket_watch").forGetter(ClockComponent::getPocketWatchType),
            PendulumTypes.CODEC.optionalFieldOf("pendulum").forGetter(ClockComponent::getPendulumType),
            Codec.BOOL.optionalFieldOf("ticking").forGetter(ClockComponent::getTicking)
    ).apply(instance, ClockComponent::new));

    public static final StreamCodec<ByteBuf, ClockComponent> STREAM_CODEC = StreamCodec.composite(
            BornholmTopGlassTypes.STREAM_CODEC.apply(ByteBufCodecs::optional), ClockComponent::getGlassType,
            PocketWatchTypes.STREAM_CODEC.apply(ByteBufCodecs::optional), ClockComponent::getPocketWatchType,
            PendulumTypes.STREAM_CODEC.apply(ByteBufCodecs::optional), ClockComponent::getPendulumType,
            ByteBufCodecs.BOOL.apply(ByteBufCodecs::optional), ClockComponent::getTicking,
            ClockComponent::new);

    public Optional<BornholmTopGlassTypes> getGlassType() {
        return topGlassType;
    }

    public Optional<PocketWatchTypes> getPocketWatchType() {
        return pocketWatchType;
    }

    public Optional<PendulumTypes> getPendulumType() {
        return pendulumType;
    }

    public Optional<Boolean> getTicking() {
        return ticking;
    }

    public static ClockComponent getDefaultValue() {
        return new ClockComponent(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    public static ClockComponent getBasicClockValue() {
        return new ClockComponent(Optional.of(BornholmTopGlassTypes.GLASS), Optional.of(PocketWatchTypes.EMPTY), Optional.of(PendulumTypes.EMPTY), Optional.of(Boolean.FALSE));
    }

    public static ClockComponent getNoPendulumValue() {
        return new ClockComponent(Optional.of(BornholmTopGlassTypes.GLASS), Optional.of(PocketWatchTypes.EMPTY), Optional.empty(), Optional.of(Boolean.FALSE));
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("clock_data", (Tag)CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static ClockComponent load(@Nullable CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("clock_data")).result().orElse(getDefaultValue());
    }
}
