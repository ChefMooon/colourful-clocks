package com.chefmooon.colourfulclocks.common.core;

import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public enum PocketWatchTypes implements StringRepresentable {

    IRON(0, BuiltInRegistries.ITEM.get(ColourfulClocksItems.IRON_POCKET_WATCH), "iron", "Iron", Items.IRON_INGOT, TextUtil.res("iron_pocket_watch_in_clock")),
    COPPER(1, BuiltInRegistries.ITEM.get(ColourfulClocksItems.COPPER_POCKET_WATCH), "copper", "Copper", Items.COPPER_INGOT, TextUtil.res("copper_pocket_watch_in_clock")),
    EXPOSED_COPPER(1, BuiltInRegistries.ITEM.get(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH), "exposed_copper", "Exposed Copper", Items.AIR, TextUtil.res("exposed_copper_pocket_watch_in_clock")),
    WEATHERED_COPPER(2, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH), "weathered_copper", "Weathered Copper", Items.AIR, TextUtil.res("weathered_copper_pocket_watch_in_clock")),
    OXIDIZED_COPPER(3, BuiltInRegistries.ITEM.get(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH), "oxidized_copper", "Oxidized Copper", Items.AIR, TextUtil.res("oxidized_copper_pocket_watch_in_clock")),
    WAXED_COPPER(4, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH), "waxed_copper", "Waxed Copper", Items.COPPER_INGOT, TextUtil.res("copper_pocket_watch_in_clock")),
    WAXED_EXPOSED_COPPER(5, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH), "waxed_exposed_copper", "Waxed Exposed Copper", Items.AIR, TextUtil.res("exposed_copper_pocket_watch_in_clock")),
    WAXED_WEATHERED_COPPER(6, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH), "waxed_weathered_copper", "Waxed Weathered Copper", Items.AIR, TextUtil.res("weathered_copper_pocket_watch_in_clock")),
    WAXED_OXIDIZED_COPPER(7, BuiltInRegistries.ITEM.get(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH), "waxed_oxidized_copper", "Waxed Oxidized Copper", Items.AIR, TextUtil.res("oxidized_copper_pocket_watch_in_clock")),
    GOLD(8, BuiltInRegistries.ITEM.get(ColourfulClocksItems.GOLD_POCKET_WATCH), "gold", "Gold", Items.GOLD_INGOT, TextUtil.res("gold_pocket_watch_in_clock")),
    DIAMOND(9, BuiltInRegistries.ITEM.get(ColourfulClocksItems.DIAMOND_POCKET_WATCH), "diamond", "Diamond", Items.DIAMOND, TextUtil.res("diamond_pocket_watch_in_clock")),
    NETHERITE(10, BuiltInRegistries.ITEM.get(ColourfulClocksItems.NETHERITE_POCKET_WATCH), "netherite", "Netherite", Items.NETHERITE_INGOT, TextUtil.res("netherite_pocket_watch_in_clock")),

    EMERALD(11, BuiltInRegistries.ITEM.get(ColourfulClocksItems.EMERALD_POCKET_WATCH), "emerald", "Emerald", Items.EMERALD, TextUtil.res("emerald_pocket_watch_in_clock")),
    AMETHYST(12, BuiltInRegistries.ITEM.get(ColourfulClocksItems.AMETHYST_POCKET_WATCH), "amethyst", "Amethyst", Items.AMETHYST_SHARD, TextUtil.res("amethyst_pocket_watch_in_clock")),
    QUARTZ(13, BuiltInRegistries.ITEM.get(ColourfulClocksItems.QUARTZ_POCKET_WATCH), "quartz", "Quartz", Items.QUARTZ, TextUtil.res("quartz_pocket_watch_in_clock")),
    LAPIS_LAZULI(14, BuiltInRegistries.ITEM.get(ColourfulClocksItems.LAPIS_LAZULI_POCKET_WATCH), "lapis_lazuli", "Lapis Lazuli", Items.LAPIS_LAZULI, TextUtil.res("lapis_lazuli_pocket_watch_in_clock")),
    REDSTONE(15, BuiltInRegistries.ITEM.get(ColourfulClocksItems.REDSTONE_POCKET_WATCH), "redstone", "Redstone", Items.REDSTONE, TextUtil.res("redstone_pocket_watch_in_clock"))

    ;

    private final int id;
    private final Item item; // todo - item gets air
    private final String name;
    private final String en_us;
    private final Item craftingIngredient;
    private final ResourceLocation inClockLocation;

    PocketWatchTypes(int id, Item item, String name, String en_us, Item craftingIngredient, ResourceLocation inClockLocation) {
        this.id = id;
        this.item = item;
        this.name = name;
        this.en_us = en_us;
        this.craftingIngredient = craftingIngredient;
        this.inClockLocation = inClockLocation;
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

    public String getBaseTranslation() {
        return " " + en_us;
    }

    public Item getCraftingIngredient() {
        return craftingIngredient;
    }

    public ResourceLocation getInClockLocation() {
        return inClockLocation;
    }

    public ModelResourceLocation getModelResourceLocation() {
        return ModelResourceLocation.inventory(inClockLocation);
    }

    @Override
    public @NotNull String getSerializedName() {
        return "_" + name;
    }
}
