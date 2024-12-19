package com.chefmooon.colourfulclocks.common.core;

import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum PendulumTypes {

    IRON(0, BuiltInRegistries.ITEM.get(ColourfulClocksItems.IRON_PENDULUM), "iron", 2.0F, ColourfulClocksSounds.ITEM_IRON_PENDULUM_CHIME.get(), 1.0F, Items.IRON_INGOT),
    COPPER(1, BuiltInRegistries.ITEM.get(ColourfulClocksItems.COPPER_PENDULUM), "copper", 2.0F, ColourfulClocksSounds.ITEM_COPPER_PENDULUM_CHIME.get(), 0.9F, Items.COPPER_INGOT),
    EXPOSED_COPPER(2, BuiltInRegistries.ITEM.get(ColourfulClocksItems.EXPOSED_COPPER_PENDULUM), "exposed_copper", 2.0F, ColourfulClocksSounds.ITEM_EXPOSED_COPPER_PENDULUM_CHIME.get(), 0.8F, ItemStack.EMPTY.getItem()),
    WEATHERED_COPPER(3, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WEATHERED_COPPER_PENDULUM), "weathered_copper", 2.0F, ColourfulClocksSounds.ITEM_WEATHERED_COPPER_PENDULUM_CHIME.get(), 0.7F, ItemStack.EMPTY.getItem()),
    OXIDIZED_COPPER(4, BuiltInRegistries.ITEM.get(ColourfulClocksItems.OXIDIZED_COPPER_PENDULUM), "oxidized_copper", 2.0F, ColourfulClocksSounds.ITEM_OXIDIZED_COPPER_PENDULUM_CHIME.get(), 0.6F, ItemStack.EMPTY.getItem()),
    WAXED_COPPER(5, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_COPPER_PENDULUM), "copper", 2.0F, ColourfulClocksSounds.ITEM_COPPER_PENDULUM_CHIME.get(), 0.9F, Items.COPPER_INGOT),
    WAXED_EXPOSED_COPPER(6, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_EXPOSED_COPPER_PENDULUM), "exposed_copper", 2.0F, ColourfulClocksSounds.ITEM_EXPOSED_COPPER_PENDULUM_CHIME.get(), 0.8F, ItemStack.EMPTY.getItem()),
    WAXED_WEATHERED_COPPER(7, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_WEATHERED_COPPER_PENDULUM), "weathered_copper", 2.0F, ColourfulClocksSounds.ITEM_WEATHERED_COPPER_PENDULUM_CHIME.get(), 0.7F, ItemStack.EMPTY.getItem()),
    WAXED_OXIDIZED_COPPER(8, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_PENDULUM), "oxidized_copper", 2.0F, ColourfulClocksSounds.ITEM_OXIDIZED_COPPER_PENDULUM_CHIME.get(), 0.6F, ItemStack.EMPTY.getItem()),
    GOLD(5, BuiltInRegistries.ITEM.get(ColourfulClocksItems.GOLD_PENDULUM), "gold", 1.5F, ColourfulClocksSounds.ITEM_GOLD_PENDULUM_CHIME.get(), 0.5F, Items.GOLD_INGOT),
    DIAMOND(6, BuiltInRegistries.ITEM.get(ColourfulClocksItems.DIAMOND_PENDULUM), "diamond", 1.0F, ColourfulClocksSounds.ITEM_DIAMOND_PENDULUM_CHIME.get(), 0.4F, Items.DIAMOND),
    NETHERITE(7, BuiltInRegistries.ITEM.get(ColourfulClocksItems.NETHERITE_PENDULUM), "netherite", 0.5F, ColourfulClocksSounds.ITEM_NETHERITE_PENDULUM_CHIME.get(), 0.3F, Items.NETHERITE_INGOT),

    EMERALD(8, BuiltInRegistries.ITEM.get(ColourfulClocksItems.EMERALD_PENDULUM), "emerald", 1.5F, ColourfulClocksSounds.ITEM_EMERALD_PENDULUM_CHIME.get(), 0.8F, Items.EMERALD),
    AMETHYST(9, BuiltInRegistries.ITEM.get(ColourfulClocksItems.AMETHYST_PENDULUM), "amethyst", 1.5F, ColourfulClocksSounds.ITEM_AMETHYST_PENDULUM_CHIME.get(), 1.2F, Items.AMETHYST_SHARD),
    QUARTZ(10, BuiltInRegistries.ITEM.get(ColourfulClocksItems.QUARTZ_PENDULUM), "quartz", 1.5F, ColourfulClocksSounds.ITEM_QUARTZ_PENDULUM_CHIME.get(), 0.8F, Items.QUARTZ),
    LAPIS_LAZULI(11, BuiltInRegistries.ITEM.get(ColourfulClocksItems.LAPIS_LAZULI_PENDULUM), "lapis_lazuli", 1.5F, ColourfulClocksSounds.ITEM_LAPIS_LAZULI_PENDULUM_CHIME.get(), 0.8F, Items.LAPIS_LAZULI),
    REDSTONE(12, BuiltInRegistries.ITEM.get(ColourfulClocksItems.REDSTONE_PENDULUM), "redstone", 1.5F, ColourfulClocksSounds.ITEM_REDSTONE_PENDULUM_CHIME.get(), 0.8F, Items.REDSTONE)
    ;



    private final int id;
    private final Item item;
    private final String name;
    private final float swingSpeedModifier;
    private final SoundEvent chimeSound;
    private final float pitchModifier;
    private final Item craftingIngredient;

    PendulumTypes(int id, Item item, String name, float swingSpeedModifier, SoundEvent chimeSound, float pitchModifier, Item craftingIngredient) {
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

    public SoundEvent getChimeSound() {
        return chimeSound;
    }

    public float getPitchModifier() {
        return pitchModifier;
    }

    public Item getCraftingIngredient() {
        return craftingIngredient;
    }

}
