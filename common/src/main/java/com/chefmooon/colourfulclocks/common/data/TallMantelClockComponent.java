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

public record TallMantelClockComponent(BornholmTopGlassTypes topGlassType, PocketWatchTypes pocketWatchType, PendulumTypes pendulumType, boolean ticking) {
    public static final Codec<TallMantelClockComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    BornholmTopGlassTypes.CODEC.optionalFieldOf("glass", BornholmTopGlassTypes.GLASS).forGetter(TallMantelClockComponent::getGlassType),
                    PocketWatchTypes.CODEC.optionalFieldOf("pocket_watch", PocketWatchTypes.EMPTY).forGetter(TallMantelClockComponent::getPocketWatchType),
                    PendulumTypes.CODEC.optionalFieldOf("pendulum", PendulumTypes.EMPTY).forGetter(TallMantelClockComponent::getPendulumType),
                    Codec.BOOL.optionalFieldOf("ticking", Boolean.FALSE).forGetter(TallMantelClockComponent::getTicking)
            ).apply(instance, TallMantelClockComponent::new)
    );
    public static final StreamCodec<ByteBuf, TallMantelClockComponent> STREAM_CODEC = StreamCodec.composite(
            BornholmTopGlassTypes.STREAM_CODEC, TallMantelClockComponent::getGlassType,
            PocketWatchTypes.STREAM_CODEC, TallMantelClockComponent::getPocketWatchType,
            PendulumTypes.STREAM_CODEC, TallMantelClockComponent::getPendulumType,
            ByteBufCodecs.BOOL, TallMantelClockComponent::getTicking,
            TallMantelClockComponent::new
    );

    public String type() {
        return this.topGlassType.getName();
    }

    public BornholmTopGlassTypes getGlassType() {
        return topGlassType;
    }

    public PocketWatchTypes getPocketWatchType() {
        return pocketWatchType;
    }

    public PendulumTypes getPendulumType() {
        return pendulumType;
    }

    public boolean getTicking() {
        return ticking;
    }

    public static TallMantelClockComponent getDefaultValue() {
        return new TallMantelClockComponent(BornholmTopGlassTypes.GLASS, PocketWatchTypes.EMPTY, PendulumTypes.EMPTY, Boolean.FALSE);
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("tall_mantel_clock_data", (Tag)CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static TallMantelClockComponent load(@Nullable CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("tall_mantel_clock_data")).result().orElse(getDefaultValue());
    }
}
