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
import org.jetbrains.annotations.NotNull;

public enum HandbellHandleTypes implements StringRepresentable {
    OAK(0, "oak", Items.OAK_PLANKS, TextUtil.getTranslatable("item.oak_handbell_handle"), Blocks.OAK_PLANKS),
    SPRUCE(1, "spruce", Items.SPRUCE_PLANKS, TextUtil.getTranslatable("item.spruce_handbell_handle"), Blocks.SPRUCE_PLANKS),
    BIRCH(2, "birch", Items.BIRCH_PLANKS, TextUtil.getTranslatable("item.birch_handbell_handle"), Blocks.BIRCH_PLANKS),
    JUNGLE(3, "jungle", Items.JUNGLE_PLANKS, TextUtil.getTranslatable("item.jungle_handbell_handle"), Blocks.JUNGLE_PLANKS),
    ACACIA(4, "acacia", Items.ACACIA_PLANKS, TextUtil.getTranslatable("item.acacia_handbell_handle"), Blocks.ACACIA_PLANKS),
    DARK_OAK(5, "dark_oak", Items.DARK_OAK_PLANKS, TextUtil.getTranslatable("item.dark_oak_handbell_handle"), Blocks.DARK_OAK_PLANKS),
    MANGROVE(6, "mangrove", Items.MANGROVE_PLANKS, TextUtil.getTranslatable("item.mangrove_handbell_handle"), Blocks.MANGROVE_PLANKS),
    CHERRY(7, "cherry", Items.CHERRY_PLANKS, TextUtil.getTranslatable("item.cherry_handbell_handle"), Blocks.CHERRY_PLANKS),
    BAMBOO(8, "bamboo", Items.BAMBOO_PLANKS, TextUtil.getTranslatable("item.bamboo_handbell_handle"), Blocks.BAMBOO_PLANKS),
    CRIMSON(9, "crimson", Items.CRIMSON_PLANKS, TextUtil.getTranslatable("item.crimson_handbell_handle"), Blocks.CRIMSON_PLANKS),
    WARPED(10, "warped", Items.WARPED_PLANKS, TextUtil.getTranslatable("item.warped_handbell_handle"), Blocks.WARPED_PLANKS),

    STONE_BRICKS(11, "stone_bricks", Items.STONE_BRICKS, TextUtil.getTranslatable("item.stone_bricks_handbell_handle"), Blocks.STONE_BRICKS),
    MOSSY_STONE_BRICKS(12, "mossy_stone_bricks", Items.MOSSY_STONE_BRICKS, TextUtil.getTranslatable("item.mossy_stone_bricks_handbell_handle"), Blocks.MOSSY_STONE_BRICKS),
    POLISHED_GRANITE(13, "polished_granite", Items.POLISHED_GRANITE, TextUtil.getTranslatable("item.polished_granite_handbell_handle"), Blocks.POLISHED_GRANITE),
    POLISHED_DIORITE(14, "polished_diorite", Items.POLISHED_DIORITE, TextUtil.getTranslatable("item.polished_diorite_handbell_handle"), Blocks.POLISHED_DIORITE),
    POLISHED_ANDESITE(15, "polished_andesite", Items.POLISHED_ANDESITE, TextUtil.getTranslatable("item.polished_andesite_handbell_handle"), Blocks.POLISHED_ANDESITE),
    POLISHED_DEEPSLATE(16, "polished_deepslate", Items.POLISHED_DEEPSLATE, TextUtil.getTranslatable("item.polished_deepslate_handbell_handle"), Blocks.POLISHED_DEEPSLATE),
    DEEPSLATE_BRICKS(17, "deepslate_bricks", Items.DEEPSLATE_BRICKS, TextUtil.getTranslatable("item.deepslate_bricks_handbell_handle"), Blocks.DEEPSLATE_BRICKS),
    POLISHED_TUFF(18, "polished_tuff", Items.POLISHED_TUFF, TextUtil.getTranslatable("item.polished_tuff_handbell_handle"), Blocks.POLISHED_TUFF),
    TUFF_BRICKS(19, "tuff_bricks", Items.TUFF_BRICKS, TextUtil.getTranslatable("item.tuff_bricks_handbell_handle"), Blocks.TUFF_BRICKS),
    BRICKS(20, "bricks", Items.BRICKS, TextUtil.getTranslatable("item.bricks_handbell_handle"), Blocks.BRICKS),
    MUD_BRICKS(21, "mud_bricks", Items.MUD_BRICKS, TextUtil.getTranslatable("item.mud_bricks_handbell_handle"), Blocks.MUD_BRICKS),
    CUT_SANDSTONE(22, "cut_sandstone", Items.CUT_SANDSTONE, TextUtil.getTranslatable("item.cut_sandstone_handbell_handle"), Blocks.CUT_SANDSTONE),
    CUT_RED_SANDSTONE(23, "cut_red_sandstone", Items.CUT_RED_SANDSTONE, TextUtil.getTranslatable("item.cut_red_sandstone_handbell_handle"), Blocks.CUT_RED_SANDSTONE),
    PRISMARINE_BRICKS(24, "prismarine_bricks", Items.PRISMARINE_BRICKS, TextUtil.getTranslatable("item.prismarine_bricks_handbell_handle"), Blocks.PRISMARINE_BRICKS),
    DARK_PRISMARINE(25, "dark_prismarine", Items.DARK_PRISMARINE, TextUtil.getTranslatable("item.dark_prismarine_handbell_handle"), Blocks.DARK_PRISMARINE),
    NETHER_BRICKS(26, "nether_bricks", Items.NETHER_BRICKS, TextUtil.getTranslatable("item.nether_bricks_handbell_handle"), Blocks.NETHER_BRICKS),
    RED_NETHER_BRICKS(27, "red_nether_bricks", Items.RED_NETHER_BRICKS, TextUtil.getTranslatable("item.red_nether_bricks_handbell_handle"), Blocks.RED_NETHER_BRICKS),
    POLISHED_BLACKSTONE(28, "polished_blackstone", Items.POLISHED_BLACKSTONE, TextUtil.getTranslatable("item.polished_blackstone_handbell_handle"), Blocks.POLISHED_BLACKSTONE),
    POLISHED_BLACKSTONE_BRICKS(29, "polished_blackstone_bricks", Items.POLISHED_BLACKSTONE_BRICKS, TextUtil.getTranslatable("item.polished_blackstone_bricks_handbell_handle"), Blocks.POLISHED_BLACKSTONE_BRICKS),

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

}
