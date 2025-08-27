package com.chefmooon.colourfulclocks.common.data.types;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.NotNull;

public enum ClockTypes implements StringRepresentable {
    OAK("oak", "Oak", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.OAK_WOOD, Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.OAK_WOOD),
    SPRUCE("spruce", "Spruce", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.SPRUCE_WOOD, Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG, Blocks.SPRUCE_WOOD),
    BIRCH("birch", "Birch", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.BIRCH_WOOD, Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG, Blocks.BIRCH_WOOD),
    JUNGLE("jungle", "Jungle", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.JUNGLE_WOOD, Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG, Blocks.JUNGLE_WOOD),
    ACACIA("acacia", "Acacia", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.ACACIA_WOOD, Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG, Blocks.ACACIA_WOOD),
    DARK_OAK("dark_oak", "Dark Oak", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.DARK_OAK_WOOD, Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.DARK_OAK_WOOD),
    MANGROVE("mangrove", "Mangrove", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.MANGROVE_WOOD, Blocks.MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG, Blocks.MANGROVE_WOOD),
    CHERRY("cherry", "Cherry", ClockMaterialTypes.WOOD, SoundType.CHERRY_WOOD, Items.CHERRY_WOOD, Blocks.CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG, Blocks.CHERRY_WOOD),
    BAMBOO("bamboo", "Bamboo", ClockMaterialTypes.WOOD, SoundType.BAMBOO_WOOD, Items.BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.BAMBOO_BLOCK),
    CRIMSON("crimson", "Crimson Stem", ClockMaterialTypes.WOOD, SoundType.NETHER_WOOD, Items.CRIMSON_HYPHAE, Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM, Blocks.CRIMSON_HYPHAE),
    WARPED("warped", "Warped Stem", ClockMaterialTypes.WOOD, SoundType.NETHER_WOOD, Items.WARPED_HYPHAE, Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM, Blocks.WARPED_HYPHAE),

    STRIPPED_OAK("stripped_oak", "Stripped Oak", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.STRIPPED_OAK_WOOD, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD),
    STRIPPED_SPRUCE("stripped_spruce", "Stripped Spruce", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.STRIPPED_SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_WOOD),
    STRIPPED_BIRCH("stripped_birch", "Stripped Birch", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_WOOD),
    STRIPPED_JUNGLE("stripped_jungle", "Stripped Jungle", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_WOOD),
    STRIPPED_ACACIA("stripped_acacia", "Stripped Acacia", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD),
    STRIPPED_DARK_OAK("stripped_dark_oak", "Stripped Dark Oak", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD),
    STRIPPED_MANGROVE("stripped_mangrove", "Stripped Mangrove", ClockMaterialTypes.WOOD, SoundType.WOOD, Items.STRIPPED_MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_WOOD),
    STRIPPED_CHERRY("stripped_cherry", "Stripped Cherry", ClockMaterialTypes.WOOD, SoundType.CHERRY_WOOD, Items.STRIPPED_CHERRY_WOOD, Blocks.STRIPPED_CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG, Blocks.STRIPPED_CHERRY_WOOD),
    STRIPPED_BAMBOO("stripped_bamboo", "Stripped Bamboo", ClockMaterialTypes.WOOD, SoundType.BAMBOO_WOOD, Items.STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK, Blocks.STRIPPED_BAMBOO_BLOCK),
    STRIPPED_CRIMSON("stripped_crimson", "Stripped Crimson Stem", ClockMaterialTypes.WOOD, SoundType.NETHER_WOOD, Items.STRIPPED_CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_HYPHAE),
    STRIPPED_WARPED("stripped_warped", "Stripped Warped Stem", ClockMaterialTypes.WOOD, SoundType.NETHER_WOOD, Items.STRIPPED_WARPED_HYPHAE, Blocks.STRIPPED_WARPED_STEM, Blocks.STRIPPED_WARPED_STEM, Blocks.STRIPPED_WARPED_HYPHAE),

    STONE_BRICKS("stone_bricks", "Stone Bricks", ClockMaterialTypes.STONE, SoundType.STONE, Items.STONE_BRICKS, Blocks.STONE_BRICKS, Blocks.STONE_BRICKS, Blocks.STONE_BRICKS),
    MOSSY_STONE_BRICKS("mossy_stone_bricks", "Mossy Stone Bricks", ClockMaterialTypes.STONE, SoundType.STONE, Items.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS),
    POLISHED_GRANITE("polished_granite", "Polished Granite", ClockMaterialTypes.STONE, SoundType.STONE, Items.POLISHED_GRANITE, Blocks.POLISHED_GRANITE, Blocks.POLISHED_GRANITE, Blocks.POLISHED_GRANITE),
    POLISHED_DIORITE("polished_diorite", "Polished Diorite", ClockMaterialTypes.STONE, SoundType.STONE, Items.POLISHED_DIORITE, Blocks.POLISHED_DIORITE, Blocks.POLISHED_DIORITE, Blocks.POLISHED_DIORITE),
    POLISHED_ANDESITE("polished_andesite", "Polished Andesite", ClockMaterialTypes.STONE, SoundType.STONE, Items.POLISHED_ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.POLISHED_ANDESITE),
    POLISHED_DEEPSLATE("polished_deepslate", "Polished Deepslate", ClockMaterialTypes.STONE, SoundType.POLISHED_DEEPSLATE, Items.POLISHED_DEEPSLATE, Blocks.POLISHED_DEEPSLATE, Blocks.POLISHED_DEEPSLATE, Blocks.POLISHED_DEEPSLATE),
    DEEPSLATE_BRICKS("deepslate_bricks", "Deepslate Bricks", ClockMaterialTypes.STONE, SoundType.DEEPSLATE_BRICKS, Items.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICKS),
    POLISHED_TUFF("polished_tuff", "Polished Tuff", ClockMaterialTypes.TUFF_BRICK, SoundType.POLISHED_TUFF, Items.POLISHED_TUFF, Blocks.POLISHED_TUFF, Blocks.POLISHED_TUFF, Blocks.POLISHED_TUFF),
    TUFF_BRICKS("tuff_bricks", "Tuff Bricks", ClockMaterialTypes.TUFF_BRICK, SoundType.TUFF_BRICKS, Items.TUFF_BRICKS, Blocks.TUFF_BRICKS, Blocks.TUFF_BRICKS, Blocks.TUFF_BRICKS),
    BRICKS("bricks", "Bricks", ClockMaterialTypes.BRICK, SoundType.STONE, Items.BRICKS, Blocks.BRICKS, Blocks.BRICKS, Blocks.BRICKS),
    MUD_BRICKS("mud_bricks", "Mud Bricks", ClockMaterialTypes.MUD_BRICK, SoundType.MUD_BRICKS, Items.MUD_BRICKS, Blocks.MUD_BRICKS, Blocks.MUD_BRICKS, Blocks.MUD_BRICKS),
    CUT_SANDSTONE("cut_sandstone", "Cut Sandstone", ClockMaterialTypes.STONE, SoundType.STONE, Items.CUT_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CUT_SANDSTONE),
    CUT_RED_SANDSTONE("cut_red_sandstone", "Cut Red Sandstone", ClockMaterialTypes.STONE, SoundType.STONE, Items.CUT_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE),
    PRISMARINE_BRICKS("prismarine_bricks", "Prismarine Bricks", ClockMaterialTypes.STONE, SoundType.STONE, Items.PRISMARINE_BRICKS, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE_BRICKS),
    DARK_PRISMARINE("dark_prismarine", "Dark Prismarine", ClockMaterialTypes.STONE, SoundType.STONE, Items.DARK_PRISMARINE, Blocks.DARK_PRISMARINE, Blocks.DARK_PRISMARINE, Blocks.DARK_PRISMARINE),
    NETHER_BRICKS("nether_bricks", "Nether Bricks", ClockMaterialTypes.NETHER_BRICK, SoundType.NETHER_BRICKS, Items.NETHER_BRICKS, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICKS),
    RED_NETHER_BRICKS("red_nether_bricks", "Red Nether Bricks", ClockMaterialTypes.NETHER_BRICK, SoundType.NETHER_BRICKS, Items.RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS, Blocks.RED_NETHER_BRICKS),
    POLISHED_BLACKSTONE("polished_blackstone", "Polished Blackstone", ClockMaterialTypes.STONE, SoundType.STONE, Items.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE),
    POLISHED_BLACKSTONE_BRICKS("polished_blackstone_bricks", "Polished Blackstone Bricks", ClockMaterialTypes.STONE, SoundType.STONE, Items.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS),

    // The following are potential future additions, basic stone types for now
//    STONE("stone", "Stone", ClockMaterialTypes.STONE, SoundType.STONE, Items.STONE, Blocks.STONE, Blocks.STONE, Blocks.STONE),
//    COBBLESTONE("cobblestone", "Cobblestone", ClockMaterialTypes.STONE, SoundType.STONE, Items.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE),
//    MOSSY_COBBLESTONE("mossy_cobblestone", "Mossy Cobblestone", ClockMaterialTypes.STONE, SoundType.STONE, Items.MOSSY_COBBLESTONE, Blocks.MOSSY_COBBLESTONE, Blocks.MOSSY_COBBLESTONE, Blocks.MOSSY_COBBLESTONE),
//    GRANITE("granite", "Granite", ClockMaterialTypes.STONE, SoundType.STONE, Items.GRANITE, Blocks.GRANITE, Blocks.GRANITE, Blocks.GRANITE),
//    DIORITE("diorite", "Diorite", ClockMaterialTypes.STONE, SoundType.STONE, Items.DIORITE, Blocks.DIORITE, Blocks.DIORITE, Blocks.DIORITE),
//    ANDESITE("andesite", "Andesite", ClockMaterialTypes.STONE, SoundType.STONE, Items.ANDESITE, Blocks.ANDESITE, Blocks.ANDESITE, Blocks.ANDESITE),
//    DEEPSLATE("deepslate", "Deepslate", ClockMaterialTypes.STONE, SoundType.DEEPSLATE, Items.DEEPSLATE, Blocks.DEEPSLATE, Blocks.DEEPSLATE, Blocks.DEEPSLATE),
//    COBBLED_DEEPSLATE("cobbled_deepslate", "Cobbled Deepslate", ClockMaterialTypes.STONE, SoundType.DEEPSLATE, Items.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE),
//    TUFF("tuff", "Tuff", ClockMaterialTypes.TUFF_BRICK, SoundType.TUFF, Items.TUFF, Blocks.TUFF, Blocks.TUFF, Blocks.TUFF),
//    //    CALCITE("calcite", "Calcite", ClockMaterialTypes.STONE, Items.CALCITE, Blocks.CALCITE, Blocks.CALCITE, Blocks.CALCITE),
//    PACKED_MUD("packed_mud", "Packed Mud", ClockMaterialTypes.STONE, SoundType.PACKED_MUD, Items.PACKED_MUD, Blocks.PACKED_MUD, Blocks.PACKED_MUD, Blocks.PACKED_MUD),
//    SANDSTONE("sandstone", "Sandstone", ClockMaterialTypes.STONE, SoundType.STONE, Items.SANDSTONE, Blocks.SANDSTONE, Blocks.SANDSTONE, Blocks.SANDSTONE),
//    SMOOTH_SANDSTONE("smooth_sandstone", "Smooth Sandstone", ClockMaterialTypes.STONE, SoundType.STONE, Items.SMOOTH_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_SANDSTONE),
//    RED_SANDSTONE("red_sandstone", "Red Sandstone", ClockMaterialTypes.STONE, SoundType.STONE, Items.RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.RED_SANDSTONE),
//    SMOOTH_RED_SANDSTONE("smooth_red_sandstone", "Smooth Red Sandstone", ClockMaterialTypes.STONE, SoundType.STONE, Items.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE),
//    PRISMARINE("prismarine", "Prismarine", ClockMaterialTypes.STONE, SoundType.STONE, Items.PRISMARINE, Blocks.PRISMARINE, Blocks.PRISMARINE, Blocks.PRISMARINE),
//    NETHERRACK("netherrack", "Netherrack", ClockMaterialTypes.STONE, SoundType.NETHERRACK, Items.NETHERRACK, Blocks.NETHERRACK, Blocks.NETHERRACK, Blocks.NETHERRACK),
//    BLACKSTONE("blackstone", "Blackstone", ClockMaterialTypes.STONE, SoundType.STONE, Items.BLACKSTONE, Blocks.BLACKSTONE, Blocks.BLACKSTONE, Blocks.BLACKSTONE),
    ;

    private final String name;
    private final String en_us;
    private final ClockMaterialTypes clockMaterialTypes;
    private final SoundType soundType;
    private final Item item;
    private final Block block;
    private final Block strippedBlock;
    private final Block craftingIngredient;

    ClockTypes(String name, String en_us, ClockMaterialTypes clockMaterialTypes, SoundType soundType, Item item, Block block, Block strippedBlock, Block craftingIngredient) {
        this.name = name;
        this.en_us = en_us;
        this.clockMaterialTypes = clockMaterialTypes;
        this.soundType = soundType;
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

    public String getBaseTranslationNoSpace() { // temp fix?
        return (en_us.isEmpty()) ? "" : en_us;
    }

    public ClockMaterialTypes getClockTypes() {
        return clockMaterialTypes;
    }

    public SoundType getSoundType() {
        return soundType;
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

    public boolean isWooden() {
        return clockMaterialTypes == ClockMaterialTypes.WOOD;
    }
}
