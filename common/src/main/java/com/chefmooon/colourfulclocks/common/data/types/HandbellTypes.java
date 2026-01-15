package com.chefmooon.colourfulclocks.common.data.types;

import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public enum HandbellTypes implements StringRepresentable {

    IRON(0, "iron", Items.IRON_INGOT, ColourfulClocksSounds.ITEM_IRON_HANDBELL_RING, ColourfulClocksSounds.ITEM_IRON_HANDBELL_HIT, 1.5F),
    COPPER(1, "copper", Items.COPPER_INGOT, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_RING, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_HIT, 1.5F),
    EXPOSED_COPPER(2, "exposed_copper", Items.AIR, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_RING, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_HIT, 1.5F),
    WEATHERED_COPPER(3, "weathered_copper", Items.AIR, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_RING, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_HIT, 1.5F),
    OXIDIZED_COPPER(4, "oxidized_copper", Items.AIR, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_RING, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_HIT, 1.5F),
    WAXED_COPPER(5, "waxed_copper", Items.AIR, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_RING, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_HIT, 1.5F),
    WAXED_EXPOSED_COPPER(6, "waxed_exposed_copper", Items.AIR, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_RING, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_HIT, 1.5F),
    WAXED_WEATHERED_COPPER(7, "waxed_weathered_copper", Items.AIR, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_RING, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_HIT, 1.5F),
    WAXED_OXIDIZED_COPPER(8, "waxed_oxidized_copper", Items.AIR, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_RING, ColourfulClocksSounds.ITEM_COPPER_HANDBELL_HIT, 1.5F),
    GOLD(9, "gold", Items.GOLD_INGOT, ColourfulClocksSounds.ITEM_GOLD_HANDBELL_RING, ColourfulClocksSounds.ITEM_GOLD_HANDBELL_HIT, 1.5F),
    DIAMOND(10, "diamond", Items.DIAMOND, ColourfulClocksSounds.ITEM_DIAMOND_HANDBELL_RING, ColourfulClocksSounds.ITEM_DIAMOND_HANDBELL_HIT, 1.5F),
    NETHERITE(11, "netherite", Items.NETHERITE_INGOT, ColourfulClocksSounds.ITEM_NETHERITE_HANDBELL_RING, ColourfulClocksSounds.ITEM_NETHERITE_HANDBELL_HIT, 1.5F),

    EMERALD(12, "emerald", Items.EMERALD, ColourfulClocksSounds.ITEM_EMERALD_HANDBELL_RING, ColourfulClocksSounds.ITEM_EMERALD_HANDBELL_HIT, 1.5F),
    AMETHYST(13, "amethyst", Items.AMETHYST_SHARD, ColourfulClocksSounds.ITEM_AMETHYST_HANDBELL_RING, ColourfulClocksSounds.ITEM_AMETHYST_HANDBELL_HIT, 1.5F),
    QUARTZ(14, "quartz", Items.QUARTZ, ColourfulClocksSounds.ITEM_QUARTZ_HANDBELL_RING, ColourfulClocksSounds.ITEM_QUARTZ_HANDBELL_HIT, 1.5F),
    LAPIS_LAZULI(15, "lapis_lazuli", Items.LAPIS_LAZULI, ColourfulClocksSounds.ITEM_LAPIS_LAZULI_HANDBELL_RING, ColourfulClocksSounds.ITEM_LAPIS_LAZULI_HANDBELL_HIT, 1.5F),
    REDSTONE(16, "redstone", Items.REDSTONE, ColourfulClocksSounds.ITEM_REDSTONE_HANDBELL_RING, ColourfulClocksSounds.ITEM_REDSTONE_HANDBELL_HIT, 1.5F)
    ;

    public static final Codec<HandbellTypes> CODEC = StringRepresentable.fromEnum(HandbellTypes::values);
    public static final StreamCodec<ByteBuf, HandbellTypes> STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(HandbellTypes::parse, HandbellTypes::getSerializedName);

    private final int id;
    private final String name;
    private final Item craftingIngredient;
    private final Supplier<SoundEvent> ringSound;
    private final Supplier<SoundEvent> hitSound;
    private final float pitch;

    HandbellTypes(int id, String name, Item craftingIngredient, Supplier<SoundEvent> ringSound, Supplier<SoundEvent> hitSound, float pitch) {
        this.id = id;
        this.name = name;
        this.craftingIngredient = craftingIngredient;
        this.ringSound = ringSound;
        this.hitSound = hitSound;
        this.pitch = pitch;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public Item getCraftingIngredient() {
        return craftingIngredient;
    }

    public Supplier<SoundEvent> getRingSound() {
        return ringSound;
    }

    public Supplier<SoundEvent> getHitSound() {
        return hitSound;
    }

    public float getPitch() {
        return pitch;
    }

    public static HandbellTypes parse(String name) {
        for (HandbellTypes type : values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return null;
    }
}
