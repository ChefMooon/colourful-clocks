package com.chefmooon.colourfulclocks.common.core;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record BornholmTopGlassRecord(String type) {
    public static final Codec<BornholmTopGlassRecord> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.STRING.fieldOf("top_glass_type").forGetter(BornholmTopGlassRecord::type)
            ).apply(instance, BornholmTopGlassRecord::new)
    );
    public static final Codec<String> STRING_CODEC = Codec.STRING;
    public static final StreamCodec<ByteBuf, BornholmTopGlassRecord> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, BornholmTopGlassRecord::type,
            BornholmTopGlassRecord::new
    );

    public BornholmTopGlassRecord(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }
}
