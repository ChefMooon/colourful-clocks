package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.WallClockType;
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

public record WallClockComponent(WallClockType type, Optional<PocketWatchComponent> pocketWatch, Optional<Boolean> ticking) {
    public static final Codec<WallClockComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            WallClockType.CODEC.fieldOf("type").forGetter(WallClockComponent::getType),
            PocketWatchComponent.CODEC.optionalFieldOf("pocket_watch").forGetter(WallClockComponent::getPocketWatch),
            Codec.BOOL.optionalFieldOf("ticking").forGetter(WallClockComponent::isTicking)
    ).apply(instance, WallClockComponent::new));

    public static final StreamCodec<ByteBuf, WallClockComponent> STREAM_CODEC = StreamCodec.composite(
            WallClockType.STREAM_CODEC, WallClockComponent::getType,
            PocketWatchComponent.STREAM_CODEC.apply(ByteBufCodecs::optional), WallClockComponent::getPocketWatch,
            ByteBufCodecs.BOOL.apply(ByteBufCodecs::optional), WallClockComponent::isTicking,
            WallClockComponent::new);

    public WallClockType getType() {
        return type;
    }

    public Optional<PocketWatchComponent> getPocketWatch() {
        return pocketWatch;
    }

    public Optional<Boolean> isTicking() {
        return ticking;
    }

    public static WallClockComponent getDefaultValue() {
        return new WallClockComponent(WallClockType.SMALL, Optional.of(PocketWatchComponent.getDefaultValue()), Optional.of(Boolean.FALSE));
    }

    public static WallClockComponent getValue(WallClockType type) {
        return new WallClockComponent(type, Optional.of(PocketWatchComponent.getDefaultValue()), Optional.of(Boolean.FALSE));
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("wall_clock", (Tag)CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static WallClockComponent load(@Nullable CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("wall_clock")).result().orElse(getDefaultValue());
    }
}
