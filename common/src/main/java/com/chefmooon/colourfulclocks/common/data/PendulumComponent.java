package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Optional;

public record PendulumComponent(PendulumTypes type, Optional<Integer> weathering) {
    public static final Codec<PendulumComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PendulumTypes.CODEC.fieldOf("type").forGetter(PendulumComponent::type),
            Codec.INT.optionalFieldOf("weathering").forGetter(PendulumComponent::weathering)
    ).apply(instance, PendulumComponent::new));

    public static final StreamCodec<ByteBuf, PendulumComponent> STREAM_CODEC = StreamCodec.composite(
            PendulumTypes.STREAM_CODEC, PendulumComponent::type,
            ByteBufCodecs.INT.apply(ByteBufCodecs::optional), PendulumComponent::weathering,
            PendulumComponent::new);

    public PendulumTypes getType() {
        return type;
    }

    public Optional<Integer> getWeathering() {
        return weathering;
    }

    public static PendulumComponent getBaseValue(PendulumTypes type) {
        return new PendulumComponent(type, Optional.empty());
    }

    public static PendulumComponent getWeatheringCopperValue(PendulumTypes type) {
        return new PendulumComponent(type, Optional.of(0));
    }

    public static PendulumComponent getDefaultValue() {
        return new PendulumComponent(PendulumTypes.EMPTY, Optional.empty());
    }
}
