package com.chefmooon.colourfulclocks.common.data.types;

import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum PendulumTypes implements StringRepresentable {

    EMPTY(0, Items.AIR, "empty", 0.0F, null, 0.0F, null),
    IRON(1, BuiltInRegistries.ITEM.get(ColourfulClocksItems.IRON_PENDULUM), "iron", 2.0F, ColourfulClocksSounds.ITEM_IRON_PENDULUM_CHIME, 1.0F, Items.IRON_INGOT),
    COPPER(2, BuiltInRegistries.ITEM.get(ColourfulClocksItems.COPPER_PENDULUM), "copper", 2.0F, ColourfulClocksSounds.ITEM_COPPER_PENDULUM_CHIME, 0.9F, Items.COPPER_INGOT),
    EXPOSED_COPPER(3, BuiltInRegistries.ITEM.get(ColourfulClocksItems.EXPOSED_COPPER_PENDULUM), "exposed_copper", 1.5F, ColourfulClocksSounds.ITEM_EXPOSED_COPPER_PENDULUM_CHIME, 0.8F, ItemStack.EMPTY.getItem()),
    WEATHERED_COPPER(4, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WEATHERED_COPPER_PENDULUM), "weathered_copper", 1.0F, ColourfulClocksSounds.ITEM_WEATHERED_COPPER_PENDULUM_CHIME, 0.7F, ItemStack.EMPTY.getItem()),
    OXIDIZED_COPPER(5, BuiltInRegistries.ITEM.get(ColourfulClocksItems.OXIDIZED_COPPER_PENDULUM), "oxidized_copper", 0.5F, ColourfulClocksSounds.ITEM_OXIDIZED_COPPER_PENDULUM_CHIME, 0.6F, ItemStack.EMPTY.getItem()),
    WAXED_COPPER(6, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_COPPER_PENDULUM), "waxed_copper", 2.0F, ColourfulClocksSounds.ITEM_COPPER_PENDULUM_CHIME, 0.9F, Items.COPPER_INGOT),
    WAXED_EXPOSED_COPPER(7, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_EXPOSED_COPPER_PENDULUM), "waxed_exposed_copper", 1.5F, ColourfulClocksSounds.ITEM_EXPOSED_COPPER_PENDULUM_CHIME, 0.8F, ItemStack.EMPTY.getItem()),
    WAXED_WEATHERED_COPPER(8, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_WEATHERED_COPPER_PENDULUM), "waxed_weathered_copper", 1.0F, ColourfulClocksSounds.ITEM_WEATHERED_COPPER_PENDULUM_CHIME, 0.7F, ItemStack.EMPTY.getItem()),
    WAXED_OXIDIZED_COPPER(9, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_PENDULUM), "waxed_oxidized_copper", 0.5F, ColourfulClocksSounds.ITEM_OXIDIZED_COPPER_PENDULUM_CHIME, 0.6F, ItemStack.EMPTY.getItem()),
    GOLD(10, BuiltInRegistries.ITEM.get(ColourfulClocksItems.GOLD_PENDULUM), "gold", 1.5F, ColourfulClocksSounds.ITEM_GOLD_PENDULUM_CHIME, 0.5F, Items.GOLD_INGOT),
    DIAMOND(11, BuiltInRegistries.ITEM.get(ColourfulClocksItems.DIAMOND_PENDULUM), "diamond", 1.0F, ColourfulClocksSounds.ITEM_DIAMOND_PENDULUM_CHIME, 0.4F, Items.DIAMOND),
    NETHERITE(12, BuiltInRegistries.ITEM.get(ColourfulClocksItems.NETHERITE_PENDULUM), "netherite", 0.5F, ColourfulClocksSounds.ITEM_NETHERITE_PENDULUM_CHIME, 0.3F, Items.NETHERITE_INGOT),

    EMERALD(13, BuiltInRegistries.ITEM.get(ColourfulClocksItems.EMERALD_PENDULUM), "emerald", 1.5F, ColourfulClocksSounds.ITEM_EMERALD_PENDULUM_CHIME, 0.8F, Items.EMERALD),
    AMETHYST(14, BuiltInRegistries.ITEM.get(ColourfulClocksItems.AMETHYST_PENDULUM), "amethyst", 1.5F, ColourfulClocksSounds.ITEM_AMETHYST_PENDULUM_CHIME, 1.2F, Items.AMETHYST_SHARD),
    QUARTZ(15, BuiltInRegistries.ITEM.get(ColourfulClocksItems.QUARTZ_PENDULUM), "quartz", 1.5F, ColourfulClocksSounds.ITEM_QUARTZ_PENDULUM_CHIME, 0.8F, Items.QUARTZ),
    LAPIS_LAZULI(16, BuiltInRegistries.ITEM.get(ColourfulClocksItems.LAPIS_LAZULI_PENDULUM), "lapis_lazuli", 1.5F, ColourfulClocksSounds.ITEM_LAPIS_LAZULI_PENDULUM_CHIME, 0.8F, Items.LAPIS_LAZULI),
    REDSTONE(17, BuiltInRegistries.ITEM.get(ColourfulClocksItems.REDSTONE_PENDULUM), "redstone", 1.5F, ColourfulClocksSounds.ITEM_REDSTONE_PENDULUM_CHIME, 0.8F, Items.REDSTONE)
    ;

    public static final Codec<PendulumTypes> CODEC = StringRepresentable.fromEnum(PendulumTypes::values);
    public static final StreamCodec<ByteBuf, PendulumTypes> STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(PendulumTypes::parse, PendulumTypes::getName);

    private final int id;
    private final Item item;
    private final String name;
    private final float swingSpeedModifier;
    private final Supplier<SoundEvent> chimeSound;
    private final float pitchModifier;
    private final Item craftingIngredient;

    PendulumTypes(int id, Item item, String name, float swingSpeedModifier, Supplier<SoundEvent> chimeSound, float pitchModifier, Item craftingIngredient) {
        this.id = id;
        this.item = item;
        this.name = name;
        this.swingSpeedModifier = swingSpeedModifier;
        this.chimeSound = chimeSound;
        this.pitchModifier = pitchModifier;
        this.craftingIngredient = craftingIngredient;
    }

    public int getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public float getSwingSpeedModifier() {
        return swingSpeedModifier;
    }

    public Supplier<SoundEvent> getChimeSound() {
        return chimeSound;
    }

    public float getPitchModifier() {
        return pitchModifier;
    }

    public Item getCraftingIngredient() {
        return craftingIngredient;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }

    public static PendulumTypes parse(String name) {
        for (PendulumTypes type : values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return EMPTY;
    }

}
