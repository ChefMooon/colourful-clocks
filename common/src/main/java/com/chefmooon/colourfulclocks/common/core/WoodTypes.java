package com.chefmooon.colourfulclocks.common.core;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public enum WoodTypes implements StringRepresentable {
    OAK("oak", "Oak", Items.OAK_WOOD, Blocks.OAK_LOG, Blocks.OAK_WOOD),
    SPRUCE("spruce", "Spruce", Items.SPRUCE_WOOD, Blocks.SPRUCE_LOG, Blocks.SPRUCE_WOOD),
    BIRCH("birch", "Birch", Items.BIRCH_WOOD, Blocks.BIRCH_LOG, Blocks.BIRCH_WOOD),
    JUNGLE("jungle", "Jungle", Items.JUNGLE_WOOD, Blocks.JUNGLE_LOG,  Blocks.JUNGLE_WOOD),
    ACACIA("acacia", "Acacia", Items.ACACIA_WOOD, Blocks.ACACIA_LOG, Blocks.ACACIA_WOOD),
    DARK_OAK("dark_oak", "Dark Oak", Items.DARK_OAK_WOOD, Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_WOOD),
    MANGROVE("mangrove", "Mangrove", Items.MANGROVE_WOOD, Blocks.MANGROVE_LOG, Blocks.MANGROVE_WOOD),
    CHERRY("cherry", "Cherry", Items.CHERRY_WOOD, Blocks.CHERRY_LOG, Blocks.CHERRY_WOOD),
    BAMBOO("bamboo", "Bamboo", Items.BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK),
    CRIMSON("crimson", "Crimson Stem", Items.CRIMSON_HYPHAE, Blocks.CRIMSON_STEM, Blocks.CRIMSON_HYPHAE),
    WARPED("warped", "Warped Stem", Items.WARPED_HYPHAE, Blocks.WARPED_STEM, Blocks.WARPED_HYPHAE)

    ;

    private final String name;
    private final String en_us;
    private final Item item;
    private final Block block;
    private final Block craftingIngredient;

    WoodTypes(String name, String en_us, Item item, Block block, Block craftingIngredient) {
        this.name = name;
        this.en_us = en_us;
        this.item = item;
        this.block = block;
        this.craftingIngredient = craftingIngredient;
    }

    public String getName() {
        return name;
    }

    public String getBaseTranslation() {
        return (en_us.isEmpty()) ? "" : " " + en_us;
    }

    public Item getItem() {
        return item;
    }

    public Block getBlock() {
        return block;
    }

    public Block getCraftingIngredient() {
        return craftingIngredient;
    }

    @Override
    public @NotNull String getSerializedName() {
        return "_" + name;
    }
}
