package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;

public record PocketWatchComponent(PocketWatchTypes type, Optional<Integer> weathering) {
    public static final Codec<PocketWatchComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PocketWatchTypes.CODEC.fieldOf("type").forGetter(PocketWatchComponent::type),
            Codec.INT.optionalFieldOf("weathering").forGetter(PocketWatchComponent::weathering)
    ).apply(instance, PocketWatchComponent::new));

    public static final StreamCodec<ByteBuf, PocketWatchComponent> STREAM_CODEC = StreamCodec.composite(
            PocketWatchTypes.STREAM_CODEC, PocketWatchComponent::type,
            ByteBufCodecs.INT.apply(ByteBufCodecs::optional), PocketWatchComponent::weathering,
            PocketWatchComponent::new
    );

    public PocketWatchTypes getType() {
        return type;
    }

    public Optional<Integer> getWeathering() {
        return weathering;
    }

    public static PocketWatchComponent getBaseValue(PocketWatchTypes type) {
        return new PocketWatchComponent(type, Optional.empty());
    }

    public static PocketWatchComponent getWeatheredValue(PocketWatchTypes type) {
        return new PocketWatchComponent(type, Optional.of(0));
    }

    public static PocketWatchComponent getDefaultValue() {
        return new PocketWatchComponent(PocketWatchTypes.EMPTY, Optional.empty());
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("pocket_watch_data", CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static PocketWatchComponent load(CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("pocket_watch_data")).result().orElse(getDefaultValue());
    }
}
