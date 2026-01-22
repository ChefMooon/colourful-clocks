package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
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

public record ClockComponent(Optional<BornholmTopGlassTypes> topGlassType, Optional<PocketWatchComponent> pocketWatch, Optional<PendulumComponent> pendulum, Optional<Boolean> ticking) {
    public static final Codec<ClockComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BornholmTopGlassTypes.CODEC.optionalFieldOf("glass").forGetter(ClockComponent::getGlassType),
            PocketWatchComponent.CODEC.optionalFieldOf("pocket_watch").forGetter(ClockComponent::getPocketWatch),
            PendulumComponent.CODEC.optionalFieldOf("pendulum").forGetter(ClockComponent::getPendulum),
            Codec.BOOL.optionalFieldOf("ticking").forGetter(ClockComponent::getTicking)
    ).apply(instance, ClockComponent::new));

    public static final StreamCodec<ByteBuf, ClockComponent> STREAM_CODEC = StreamCodec.composite(
            BornholmTopGlassTypes.STREAM_CODEC.apply(ByteBufCodecs::optional), ClockComponent::getGlassType,
            PocketWatchComponent.STREAM_CODEC.apply(ByteBufCodecs::optional), ClockComponent::getPocketWatch,
            PendulumComponent.STREAM_CODEC.apply(ByteBufCodecs::optional), ClockComponent::getPendulum,
            ByteBufCodecs.BOOL.apply(ByteBufCodecs::optional), ClockComponent::getTicking,
            ClockComponent::new);

    public Optional<BornholmTopGlassTypes> getGlassType() {
        return topGlassType;
    }

    public Optional<PocketWatchComponent> getPocketWatch() {
        return pocketWatch;
    }

    public Optional<PendulumComponent> getPendulum() {
        return pendulum;
    }

    public Optional<Boolean> getTicking() {
        return ticking;
    }

    public static ClockComponent getDefaultValue() {
        return new ClockComponent(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    public static ClockComponent getBasicClockValue() {
        return new ClockComponent(Optional.of(BornholmTopGlassTypes.GLASS), Optional.of(PocketWatchComponent.getDefaultValue()), Optional.of(PendulumComponent.getDefaultValue()), Optional.of(Boolean.FALSE));
    }

    public static ClockComponent getNoPendulumValue() {
        return new ClockComponent(Optional.of(BornholmTopGlassTypes.GLASS), Optional.of(PocketWatchComponent.getDefaultValue()), Optional.empty(), Optional.of(Boolean.FALSE));
    }

    public static ClockComponent getNoGlassPendulumValue() {
        return new ClockComponent(Optional.empty(), Optional.of(PocketWatchComponent.getDefaultValue()), Optional.empty(), Optional.of(Boolean.FALSE));
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("clock_data", (Tag)CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static ClockComponent load(@Nullable CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("clock_data")).result().orElse(getDefaultValue());
    }
}
