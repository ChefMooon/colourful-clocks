package com.chefmooon.colourfulclocks.common.core;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public enum PendulumTypes {

    IRON(0, "iron", 1.0F, Items.IRON_INGOT),
    COPPER(1, "copper", 0.9F, Items.COPPER_INGOT),
    EXPOSED_COPPER(2, "exposed_copper", 0.9F, ItemStack.EMPTY.getItem()),
    WEATHERED_COPPER(3, "weathered_copper", 0.9F, ItemStack.EMPTY.getItem()),
    OXIDIZED_COPPER(4, "oxidized_copper", 0.9F, ItemStack.EMPTY.getItem()),
    GOLD(5, "gold", 0.8F, Items.GOLD_INGOT),
    DIAMOND(6, "diamond", 0.7F, Items.DIAMOND),
    NETHERITE(7, "netherite", 0.6F, Items.NETHERITE_INGOT)

    ;



    private final int id;
    private final String name;
    private final float swingSpeedModifier;
    private final Item craftingIngredient;

    PendulumTypes(int id, String name, float swingSpeedModifier, Item craftingIngredient) {
        this.id = id;
        this.name = name;
        this.swingSpeedModifier = swingSpeedModifier;
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

    public Item getCraftingIngredient() {
        return craftingIngredient;
    }

}
