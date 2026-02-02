package com.chefmooon.colourfulclocks.common.data.types;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public enum WallClockType implements StringRepresentable {
    SMALL(0),
    MEDIUM(1),
    LARGE(2);

    public static final Codec<WallClockType> CODEC = StringRepresentable.fromEnum(WallClockType::values);
    public static final StreamCodec<ByteBuf, WallClockType> STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(WallClockType::parse, WallClockType::getSerializedName);

    private final int id;
    WallClockType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    public static WallClockType parse(String name) {
        for (WallClockType type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return SMALL; // Default value
    }
}
