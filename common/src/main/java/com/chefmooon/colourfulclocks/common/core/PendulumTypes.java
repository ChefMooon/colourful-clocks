package com.chefmooon.colourfulclocks.common.core;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum PendulumTypes {

    IRON(0, "iron", 2.0F, 1.0F, Items.IRON_INGOT),
    COPPER(1, "copper", 2.0F, 0.9F, Items.COPPER_INGOT),
    EXPOSED_COPPER(2, "exposed_copper", 2.0F, 0.8F, ItemStack.EMPTY.getItem()),
    WEATHERED_COPPER(3, "weathered_copper", 2.0F, 0.7F, ItemStack.EMPTY.getItem()),
    OXIDIZED_COPPER(4, "oxidized_copper", 2.0F, 0.6F, ItemStack.EMPTY.getItem()),
    WAXED_COPPER(5, "copper", 2.0F, 0.9F, Items.COPPER_INGOT),
    WAXED_EXPOSED_COPPER(6, "exposed_copper", 2.0F, 0.8F, ItemStack.EMPTY.getItem()),
    WAXED_WEATHERED_COPPER(7, "weathered_copper", 2.0F, 0.7F, ItemStack.EMPTY.getItem()),
    WAXED_OXIDIZED_COPPER(8, "oxidized_copper", 2.0F, 0.6F, ItemStack.EMPTY.getItem()),
    GOLD(5, "gold", 1.5F, 0.5F, Items.GOLD_INGOT),
    DIAMOND(6, "diamond", 1.0F, 0.4F, Items.DIAMOND),
    NETHERITE(7, "netherite", 0.5F, 0.3F, Items.NETHERITE_INGOT)

    ;



    private final int id;
    private final String name;
    private final float swingSpeedModifier;
    private final float pitchModifier;
    private final Item craftingIngredient;

    PendulumTypes(int id, String name, float swingSpeedModifier, float pitchModifier, Item craftingIngredient) {
        this.id = id;
        this.name = name;
        this.swingSpeedModifier = swingSpeedModifier;
        this.pitchModifier = pitchModifier;
        this.craftingIngredient = craftingIngredient;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getSwingSpeedModifier() {
        return swingSpeedModifier;
    }

    public float getPitchModifier() {
        return pitchModifier;
    }

    public Item getCraftingIngredient() {
        return craftingIngredient;
    }

}
