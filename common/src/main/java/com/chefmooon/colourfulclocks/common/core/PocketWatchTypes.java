package com.chefmooon.colourfulclocks.common.core;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum PocketWatchTypes implements StringRepresentable {

    IRON(0, 1, "iron", "Iron", Items.IRON_INGOT, ModelResourceLocation.inventory(TextUtil.res("iron_pocket_watch_in_clock"))),
    COPPER(1, 2, "copper", "Copper", Items.COPPER_INGOT, ModelResourceLocation.inventory(TextUtil.res("copper_pocket_watch_in_clock"))),
    GOLD(2, 3, "gold", "Gold", Items.GOLD_INGOT, ModelResourceLocation.inventory(TextUtil.res("gold_pocket_watch_in_clock"))),
    DIAMOND(3, 4, "diamond", "Diamond", Items.DIAMOND, ModelResourceLocation.inventory(TextUtil.res("diamond_pocket_watch_in_clock"))),
    NETHERITE(4, 5, "netherite", "Netherite", Items.NETHERITE_INGOT, ModelResourceLocation.inventory(TextUtil.res("netherite_pocket_watch_in_clock")))

    ;

    private final int id;
    private final int effectRange;
    private final String name;
    private final String en_us;
    private final Item item;
    private final ModelResourceLocation inClockLocation;

    PocketWatchTypes(int id, int effectRange, String name, String en_us, Item item, ModelResourceLocation inClockLocation) {
        this.id = id;
        this.effectRange = effectRange;
        this.name = name;
        this.en_us = en_us;
        this.item = item;
        this.inClockLocation = inClockLocation;
    }

    public int getId() {
        return id;
    }

    public int getEffectRange() {
        return effectRange;
    }

    public String getName() {
        return name;
    }

    public String getBaseTranslation() {
        return " " + en_us;
    }

    public Item getItem() {
        return item;
    }

    public ModelResourceLocation getInClockLocation() {
        return inClockLocation;
    }

    @Override
    public String getSerializedName() {
        return "_" + name;
    }
}
