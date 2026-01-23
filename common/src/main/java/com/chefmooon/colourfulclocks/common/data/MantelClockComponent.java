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

public record MantelClockComponent(Optional<BornholmTopGlassTypes> topGlassType, Optional<PocketWatchComponent> pocketWatch, Optional<PendulumComponent> pendulum, Optional<Boolean> ticking) {
    public static final Codec<MantelClockComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BornholmTopGlassTypes.CODEC.optionalFieldOf("glass").forGetter(MantelClockComponent::getGlassType),
            PocketWatchComponent.CODEC.optionalFieldOf("pocket_watch").forGetter(MantelClockComponent::getPocketWatch),
            PendulumComponent.CODEC.optionalFieldOf("pendulum").forGetter(MantelClockComponent::getPendulum),
            Codec.BOOL.optionalFieldOf("ticking").forGetter(MantelClockComponent::getTicking)
    ).apply(instance, MantelClockComponent::new));

    public static final StreamCodec<ByteBuf, MantelClockComponent> STREAM_CODEC = StreamCodec.composite(
            BornholmTopGlassTypes.STREAM_CODEC.apply(ByteBufCodecs::optional), MantelClockComponent::getGlassType,
            PocketWatchComponent.STREAM_CODEC.apply(ByteBufCodecs::optional), MantelClockComponent::getPocketWatch,
            PendulumComponent.STREAM_CODEC.apply(ByteBufCodecs::optional), MantelClockComponent::getPendulum,
            ByteBufCodecs.BOOL.apply(ByteBufCodecs::optional), MantelClockComponent::getTicking,
            MantelClockComponent::new);

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

    public static MantelClockComponent getDefaultValue() {
        return new MantelClockComponent(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
    }

    public static MantelClockComponent getBasicClockValue() {
        return new MantelClockComponent(Optional.of(BornholmTopGlassTypes.GLASS), Optional.of(PocketWatchComponent.getDefaultValue()), Optional.of(PendulumComponent.getDefaultValue()), Optional.of(Boolean.FALSE));
    }

    public static MantelClockComponent getNoPendulumValue() {
        return new MantelClockComponent(Optional.of(BornholmTopGlassTypes.GLASS), Optional.of(PocketWatchComponent.getDefaultValue()), Optional.empty(), Optional.of(Boolean.FALSE));
    }

    public static MantelClockComponent getNoGlassPendulumValue() {
        return new MantelClockComponent(Optional.empty(), Optional.of(PocketWatchComponent.getDefaultValue()), Optional.empty(), Optional.of(Boolean.FALSE));
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("mantel_clock", (Tag)CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static MantelClockComponent load(@Nullable CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("mantel_clock")).result().orElse(getDefaultValue());
    }
}
