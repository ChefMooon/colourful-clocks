package com.chefmooon.colourfulclocks.common.core;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public enum PocketWatchTypes implements StringRepresentable {

    IRON(0, 1, "iron", "Iron", Items.IRON_INGOT, ModelResourceLocation.inventory(TextUtil.res("iron_pocket_watch_in_clock"))),
    COPPER(1, 2, "copper", "Copper", Items.COPPER_INGOT, ModelResourceLocation.inventory(TextUtil.res("copper_pocket_watch_in_clock"))),
    EXPOSED_COPPER(1, 2, "exposed_copper", "Exposed Copper", Items.AIR, ModelResourceLocation.inventory(TextUtil.res("exposed_copper_pocket_watch_in_clock"))),
    WEATHERED_COPPER(2, 2, "weathered_copper", "Weathered Copper", Items.AIR, ModelResourceLocation.inventory(TextUtil.res("weathered_copper_pocket_watch_in_clock"))),
    OXIDIZED_COPPER(3, 2, "oxidized_copper", "Oxidized Copper", Items.AIR, ModelResourceLocation.inventory(TextUtil.res("oxidized_copper_pocket_watch_in_clock"))),
    WAXED_COPPER(4, 2, "waxed_copper", "Waxed Copper", Items.COPPER_INGOT, ModelResourceLocation.inventory(TextUtil.res("copper_pocket_watch_in_clock"))),
    WAXED_EXPOSED_COPPER(5, 2, "waxed_exposed_copper", "Waxed Exposed Copper", Items.AIR, ModelResourceLocation.inventory(TextUtil.res("exposed_copper_pocket_watch_in_clock"))),
    WAXED_WEATHERED_COPPER(6, 2, "waxed_weathered_copper", "Waxed Weathered Copper", Items.AIR, ModelResourceLocation.inventory(TextUtil.res("weathered_copper_pocket_watch_in_clock"))),
    WAXED_OXIDIZED_COPPER(7, 2, "waxed_oxidized_copper", "Waxed Oxidized Copper", Items.AIR, ModelResourceLocation.inventory(TextUtil.res("oxidized_copper_pocket_watch_in_clock"))),
    GOLD(8, 3, "gold", "Gold", Items.GOLD_INGOT, ModelResourceLocation.inventory(TextUtil.res("gold_pocket_watch_in_clock"))),
    DIAMOND(9, 4, "diamond", "Diamond", Items.DIAMOND, ModelResourceLocation.inventory(TextUtil.res("diamond_pocket_watch_in_clock"))),
    NETHERITE(10, 5, "netherite", "Netherite", Items.NETHERITE_INGOT, ModelResourceLocation.inventory(TextUtil.res("netherite_pocket_watch_in_clock")))

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
