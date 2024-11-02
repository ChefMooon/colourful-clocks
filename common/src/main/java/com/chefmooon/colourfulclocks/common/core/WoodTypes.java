package com.chefmooon.colourfulclocks.common.core;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public enum WoodTypes implements StringRepresentable {
    OAK("oak", "Oak", Items.OAK_WOOD, Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.OAK_WOOD),
    SPRUCE("spruce", "Spruce", Items.SPRUCE_WOOD, Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_WOOD),
    BIRCH("birch", "Birch", Items.BIRCH_WOOD, Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_WOOD),
    JUNGLE("jungle", "Jungle", Items.JUNGLE_WOOD, Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_WOOD),
    ACACIA("acacia", "Acacia", Items.ACACIA_WOOD, Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_WOOD),
    DARK_OAK("dark_oak", "Dark Oak", Items.DARK_OAK_WOOD, Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_WOOD),
    MANGROVE("mangrove", "Mangrove", Items.MANGROVE_WOOD, Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_WOOD),
    CHERRY("cherry", "Cherry", Items.CHERRY_WOOD, Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_WOOD),
    BAMBOO("bamboo", "Bamboo", Items.BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK),
    CRIMSON("crimson", "Crimson Stem", Items.CRIMSON_HYPHAE, Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_HYPHAE),
    WARPED("warped", "Warped Stem", Items.WARPED_HYPHAE, Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_HYPHAE),

    STRIPPED_OAK("stripped_oak", "Stripped Oak", Items.OAK_WOOD, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD),
    STRIPPED_SPRUCE("stripped_spruce", "Stripped Spruce", Items.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_WOOD),
    STRIPPED_BIRCH("stripped_birch", "Stripped Birch", Items.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_WOOD),
    STRIPPED_JUNGLE("stripped_jungle", "Stripped Jungle", Items.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_WOOD),
    STRIPPED_ACACIA("stripped_acacia", "Stripped Acacia", Items.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD),
    STRIPPED_DARK_OAK("stripped_dark_oak", "Stripped Dark Oak", Items.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD),
    STRIPPED_MANGROVE("stripped_mangrove", "Stripped Mangrove", Items.MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_WOOD),
    STRIPPED_CHERRY("stripped_cherry", "Stripped Cherry", Items.CHERRY_WOOD, Blocks.STRIPPED_CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG, Blocks.STRIPPED_CHERRY_WOOD),
    STRIPPED_BAMBOO("stripped_bamboo", "Stripped Bamboo", Items.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK),
    STRIPPED_CRIMSON("stripped_crimson", "Stripped Crimson Stem", Items.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_HYPHAE),
    STRIPPED_WARPED("stripped_warped", "Stripped Warped Stem", Items.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_STEM, Blocks.STRIPPED_WARPED_STEM, Blocks.STRIPPED_WARPED_HYPHAE)

    ;

    private final String name;
    private final String en_us;
    private final Item item;
    private final Block block;
    private final Block strippedBlock;
    private final Block craftingIngredient;

    WoodTypes(String name, String en_us, Item item, Block block, Block strippedBlock, Block craftingIngredient) {
        this.name = name;
        this.en_us = en_us;
        this.item = item;
        this.block = block;
        this.strippedBlock = strippedBlock;
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

    public Block getStrippedBlock() {
        return strippedBlock;
    }

    public Block getCraftingIngredient() {
        return craftingIngredient;
    }

    @Override
    public @NotNull String getSerializedName() {
        return "_" + name;
    }
}
