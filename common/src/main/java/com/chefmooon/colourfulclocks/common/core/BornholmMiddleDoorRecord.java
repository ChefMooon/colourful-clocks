package com.chefmooon.colourfulclocks.common.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Objects;

public record BornholmMiddleDoorRecord(String type) {

    public static final Codec<BornholmMiddleDoorRecord> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.STRING.fieldOf("middle_glass_type").forGetter(BornholmMiddleDoorRecord::type)
            ).apply(instance, BornholmMiddleDoorRecord::new)
    );
    public static final Codec<String> STRING_CODEC = Codec.STRING;
    public static final StreamCodec<ByteBuf, BornholmMiddleDoorRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, BornholmMiddleDoorRecord::type,
            BornholmMiddleDoorRecord::new
    );

    public BornholmMiddleDoorRecord(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }

    public BornholmDoorTypes getDoorType() {
        for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
            if (Objects.equals(this.type, bornholmDoorTypes.getSerializedName())) return bornholmDoorTypes;
        }
        return BornholmDoorTypes.BASE;
    }
}
