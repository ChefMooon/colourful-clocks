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

public record AlarmClockComponent(Optional<HandbellComponent> leftBell, Optional<HandbellComponent> rightBell, Optional<BornholmTopGlassTypes> glassType, Optional<PocketWatchComponent> pocketWatch, Optional<Boolean> ticking) {
    public static final Codec<AlarmClockComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            HandbellComponent.CODEC.optionalFieldOf("left_bell").forGetter(AlarmClockComponent::leftBell),
            HandbellComponent.CODEC.optionalFieldOf("right_bell").forGetter(AlarmClockComponent::rightBell),
            BornholmTopGlassTypes.CODEC.optionalFieldOf("glass").forGetter(AlarmClockComponent::glassType),
            PocketWatchComponent.CODEC.optionalFieldOf("pocket_watch").forGetter(AlarmClockComponent::pocketWatch),
            Codec.BOOL.optionalFieldOf("ticking").forGetter(AlarmClockComponent::ticking)
    ).apply(instance, AlarmClockComponent::new));

    public static final StreamCodec<ByteBuf, AlarmClockComponent> STREAM_CODEC = StreamCodec.composite(
            HandbellComponent.STREAM_CODEC.apply(ByteBufCodecs::optional), AlarmClockComponent::leftBell,
            HandbellComponent.STREAM_CODEC.apply(ByteBufCodecs::optional), AlarmClockComponent::rightBell,
            BornholmTopGlassTypes.STREAM_CODEC.apply(ByteBufCodecs::optional), AlarmClockComponent::glassType,
            PocketWatchComponent.STREAM_CODEC.apply(ByteBufCodecs::optional), AlarmClockComponent::pocketWatch,
            ByteBufCodecs.BOOL.apply(ByteBufCodecs::optional), AlarmClockComponent::ticking,
            AlarmClockComponent::new);

    public Optional<HandbellComponent> leftBell() {
        return leftBell;
    }

    public Optional<HandbellComponent> rightBell() {
        return rightBell;
    }

    public Optional<BornholmTopGlassTypes> glassType() {
        return glassType;
    }

    public Optional<PocketWatchComponent> pocketWatch() {
        return pocketWatch;
    }

    public Optional<Boolean> ticking() {
        return ticking;
    }

    public static AlarmClockComponent getDefaultValue() {
        return new AlarmClockComponent(Optional.empty(), Optional.empty(), Optional.of(BornholmTopGlassTypes.GLASS), Optional.of(PocketWatchComponent.getDefaultValue()), Optional.of(Boolean.FALSE));
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("alarm_clock_data", (Tag)CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static AlarmClockComponent load(@Nullable CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("alarm_clock_data")).result().orElse(getDefaultValue());
    }
}
