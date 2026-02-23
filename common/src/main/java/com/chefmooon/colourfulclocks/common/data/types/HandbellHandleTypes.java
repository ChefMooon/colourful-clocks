package com.chefmooon.colourfulclocks.common.data.types;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import org.jetbrains.annotations.NotNull;

public enum HandbellHandleTypes implements StringRepresentable {
    OAK(0, "oak", Items.OAK_WOOD, TextUtil.getTranslatable("item.oak_handbell_handle"), Blocks.OAK_LOG),
    SPRUCE(1, "spruce", Items.SPRUCE_WOOD, TextUtil.getTranslatable("item.spruce_handbell_handle"), Blocks.SPRUCE_LOG),
    BIRCH(2, "birch", Items.BIRCH_WOOD, TextUtil.getTranslatable("item.birch_handbell_handle"), Blocks.BIRCH_LOG),
    JUNGLE(3, "jungle", Items.JUNGLE_WOOD, TextUtil.getTranslatable("item.jungle_handbell_handle"), Blocks.JUNGLE_LOG),
    ACACIA(4, "acacia", Items.ACACIA_WOOD, TextUtil.getTranslatable("item.acacia_handbell_handle"), Blocks.ACACIA_LOG),
    DARK_OAK(5, "dark_oak", Items.DARK_OAK_WOOD, TextUtil.getTranslatable("item.dark_oak_handbell_handle"), Blocks.DARK_OAK_LOG),
    MANGROVE(6, "mangrove", Items.MANGROVE_WOOD, TextUtil.getTranslatable("item.mangrove_handbell_handle"), Blocks.MANGROVE_LOG),
    CHERRY(7, "cherry", Items.CHERRY_WOOD, TextUtil.getTranslatable("item.cherry_handbell_handle"), Blocks.CHERRY_LOG),
    BAMBOO(8, "bamboo", Items.BAMBOO_BLOCK, TextUtil.getTranslatable("item.bamboo_handbell_handle"), Blocks.BAMBOO_BLOCK),
    CRIMSON(9, "crimson", Items.CRIMSON_HYPHAE, TextUtil.getTranslatable("item.crimson_handbell_handle"), Blocks.CRIMSON_STEM),
    WARPED(10, "warped", Items.WARPED_HYPHAE, TextUtil.getTranslatable("item.warped_handbell_handle"), Blocks.WARPED_STEM),

    STRIPPED_OAK(11, "stripped_oak", Items.STRIPPED_OAK_WOOD, TextUtil.getTranslatable("item.stripped_oak_handbell_handle"), Blocks.STRIPPED_OAK_LOG),
    STRIPPED_SPRUCE(12, "stripped_spruce", Items.STRIPPED_SPRUCE_WOOD, TextUtil.getTranslatable("item.stripped_spruce_handbell_handle"), Blocks.STRIPPED_SPRUCE_LOG),
    STRIPPED_BIRCH(13, "stripped_birch", Items.STRIPPED_BIRCH_WOOD, TextUtil.getTranslatable("item.stripped_birch_handbell_handle"), Blocks.STRIPPED_BIRCH_LOG),
    STRIPPED_JUNGLE(14, "stripped_jungle", Items.STRIPPED_JUNGLE_WOOD, TextUtil.getTranslatable("item.stripped_jungle_handbell_handle"), Blocks.STRIPPED_JUNGLE_LOG),
    STRIPPED_ACACIA( 15, "stripped_acacia", Items.STRIPPED_ACACIA_WOOD, TextUtil.getTranslatable("item.stripped_acacia_handbell_handle"), Blocks.STRIPPED_ACACIA_LOG),
    STRIPPED_DARK_OAK(16, "stripped_dark_oak", Items.STRIPPED_DARK_OAK_WOOD, TextUtil.getTranslatable("item.stripped_dark_oak_handbell_handle"), Blocks.STRIPPED_DARK_OAK_LOG),
    STRIPPED_MANGROVE(17, "stripped_mangrove", Items.STRIPPED_MANGROVE_WOOD, TextUtil.getTranslatable("item.stripped_mangrove_handbell_handle"), Blocks.STRIPPED_MANGROVE_LOG),
    STRIPPED_CHERRY(18, "stripped_cherry", Items.STRIPPED_CHERRY_WOOD, TextUtil.getTranslatable("item.stripped_cherry_handbell_handle"), Blocks.STRIPPED_CHERRY_LOG),
    STRIPPED_BAMBOO(19, "stripped_bamboo", Items.STRIPPED_BAMBOO_BLOCK, TextUtil.getTranslatable("item.stripped_bamboo_handbell_handle"), Blocks.STRIPPED_BAMBOO_BLOCK),
    STRIPPED_CRIMSON(20, "stripped_crimson", Items.STRIPPED_CRIMSON_HYPHAE, TextUtil.getTranslatable("item.stripped_crimson_handbell_handle"), Blocks.STRIPPED_CRIMSON_STEM),
    STRIPPED_WARPED(21, "stripped_warped", Items.STRIPPED_WARPED_HYPHAE, TextUtil.getTranslatable("item.stripped_warped_handbell_handle"), Blocks.STRIPPED_WARPED_STEM),

    STONE_BRICKS(22, "stone_bricks", Items.STONE_BRICKS, TextUtil.getTranslatable("item.stone_bricks_handbell_handle"), Blocks.STONE_BRICKS),
    MOSSY_STONE_BRICKS(23, "mossy_stone_bricks", Items.MOSSY_STONE_BRICKS, TextUtil.getTranslatable("item.mossy_stone_bricks_handbell_handle"), Blocks.MOSSY_STONE_BRICKS),
    POLISHED_GRANITE(24, "polished_granite", Items.POLISHED_GRANITE, TextUtil.getTranslatable("item.polished_granite_handbell_handle"), Blocks.POLISHED_GRANITE),
    POLISHED_DIORITE(25, "polished_diorite", Items.POLISHED_DIORITE, TextUtil.getTranslatable("item.polished_diorite_handbell_handle"), Blocks.POLISHED_DIORITE),
    POLISHED_ANDESITE(26, "polished_andesite", Items.POLISHED_ANDESITE, TextUtil.getTranslatable("item.polished_andesite_handbell_handle"), Blocks.POLISHED_ANDESITE),
    POLISHED_DEEPSLATE(27, "polished_deepslate", Items.POLISHED_DEEPSLATE, TextUtil.getTranslatable("item.polished_deepslate_handbell_handle"), Blocks.POLISHED_DEEPSLATE),
    DEEPSLATE_BRICKS(28, "deepslate_bricks", Items.DEEPSLATE_BRICKS, TextUtil.getTranslatable("item.deepslate_bricks_handbell_handle"), Blocks.DEEPSLATE_BRICKS),
    POLISHED_TUFF(29, "polished_tuff", Items.POLISHED_TUFF, TextUtil.getTranslatable("item.polished_tuff_handbell_handle"), Blocks.POLISHED_TUFF),
    TUFF_BRICKS(30, "tuff_bricks", Items.TUFF_BRICKS, TextUtil.getTranslatable("item.tuff_bricks_handbell_handle"), Blocks.TUFF_BRICKS),
    BRICKS(31, "bricks", Items.BRICKS, TextUtil.getTranslatable("item.bricks_handbell_handle"), Blocks.BRICKS),
    MUD_BRICKS(32, "mud_bricks", Items.MUD_BRICKS, TextUtil.getTranslatable("item.mud_bricks_handbell_handle"), Blocks.MUD_BRICKS),
    CUT_SANDSTONE(33, "cut_sandstone", Items.CUT_SANDSTONE, TextUtil.getTranslatable("item.cut_sandstone_handbell_handle"), Blocks.CUT_SANDSTONE),
    CUT_RED_SANDSTONE(34, "cut_red_sandstone", Items.CUT_RED_SANDSTONE, TextUtil.getTranslatable("item.cut_red_sandstone_handbell_handle"), Blocks.CUT_RED_SANDSTONE),
    PRISMARINE_BRICKS(35, "prismarine_bricks", Items.PRISMARINE_BRICKS, TextUtil.getTranslatable("item.prismarine_bricks_handbell_handle"), Blocks.PRISMARINE_BRICKS),
    DARK_PRISMARINE(36, "dark_prismarine", Items.DARK_PRISMARINE, TextUtil.getTranslatable("item.dark_prismarine_handbell_handle"), Blocks.DARK_PRISMARINE),
    NETHER_BRICKS(37, "nether_bricks", Items.NETHER_BRICKS, TextUtil.getTranslatable("item.nether_bricks_handbell_handle"), Blocks.NETHER_BRICKS),
    RED_NETHER_BRICKS(38, "red_nether_bricks", Items.RED_NETHER_BRICKS, TextUtil.getTranslatable("item.red_nether_bricks_handbell_handle"), Blocks.RED_NETHER_BRICKS),
    POLISHED_BLACKSTONE(39, "polished_blackstone", Items.POLISHED_BLACKSTONE, TextUtil.getTranslatable("item.polished_blackstone_handbell_handle"), Blocks.POLISHED_BLACKSTONE),
    POLISHED_BLACKSTONE_BRICKS(40, "polished_blackstone_bricks", Items.POLISHED_BLACKSTONE_BRICKS, TextUtil.getTranslatable("item.polished_blackstone_bricks_handbell_handle"), Blocks.POLISHED_BLACKSTONE_BRICKS),

    ;

    public static final Codec<HandbellHandleTypes> CODEC = StringRepresentable.fromEnum(HandbellHandleTypes::values);
    public static final StreamCodec<ByteBuf, HandbellHandleTypes> STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(HandbellHandleTypes::parse, HandbellHandleTypes::getSerializedName);

    private final int id;
    private final String name;
    private final Item craftingIngredient;
    private final MutableComponent en_us;
    private final Block block;

    HandbellHandleTypes(int id, String name, Item craftingIngredient, MutableComponent en_us, Block block) {
        this.id = id;
        this.name = name;
        this.craftingIngredient = craftingIngredient;
        this.en_us = en_us;
        this.block = block;
    }

    public int getId() {
        return id;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }

    public Item getCraftingIngredient() {
        return craftingIngredient;
    }

    public MutableComponent getBaseTranslation() {
        return en_us;
    }

    public Block getBlock() {
        return block;
    }

    public static HandbellHandleTypes parse(String name) {
        for (HandbellHandleTypes type : values()) {
            if (type.getSerializedName().equals(name)) {
                return type;
            }
        }
        return OAK;
    }

    public static HandbellHandleTypes fromItem(Item item) {
        for (HandbellHandleTypes type : values()) {
            if (type.getCraftingIngredient() == item) {
                return type;
            }
        }
        return null;
    }

}
