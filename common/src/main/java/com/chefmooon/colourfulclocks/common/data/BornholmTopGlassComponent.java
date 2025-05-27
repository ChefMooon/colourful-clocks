package com.chefmooon.colourfulclocks.common.data;

import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
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

public record BornholmTopGlassComponent(BornholmTopGlassTypes topGlassType, PocketWatchTypes pocketWatchType, boolean ticking) {

    public static final Codec<BornholmTopGlassComponent> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    BornholmTopGlassTypes.CODEC.optionalFieldOf("glass", BornholmTopGlassTypes.GLASS).forGetter(BornholmTopGlassComponent::getGlassType),
                    PocketWatchTypes.CODEC.optionalFieldOf("pocket_watch", PocketWatchTypes.EMPTY).forGetter(BornholmTopGlassComponent::getPocketWatchType),
                    Codec.BOOL.optionalFieldOf("ticking", Boolean.FALSE).forGetter(BornholmTopGlassComponent::getTicking)
            ).apply(instance, BornholmTopGlassComponent::new)
    );
    public static final StreamCodec<ByteBuf, BornholmTopGlassComponent> STREAM_CODEC = StreamCodec.composite(
            BornholmTopGlassTypes.STREAM_CODEC, BornholmTopGlassComponent::getGlassType,
            PocketWatchTypes.STREAM_CODEC, BornholmTopGlassComponent::getPocketWatchType,
            ByteBufCodecs.BOOL, BornholmTopGlassComponent::getTicking,
            BornholmTopGlassComponent::new
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

    public boolean getTicking() {
        return ticking;
    }

    public static BornholmTopGlassComponent getDefaultValue() {
        return new BornholmTopGlassComponent(BornholmTopGlassTypes.GLASS, PocketWatchTypes.EMPTY, Boolean.FALSE);
    }

    public CompoundTag save(CompoundTag tag) {
        tag.put("bornholm_dial_data", (Tag)CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow());
        return tag;
    }

    public static BornholmTopGlassComponent load(@Nullable CompoundTag tag) {
        return CODEC.parse(NbtOps.INSTANCE, tag.get("bornholm_dial_data")).result().orElse(getDefaultValue());
    }
}
