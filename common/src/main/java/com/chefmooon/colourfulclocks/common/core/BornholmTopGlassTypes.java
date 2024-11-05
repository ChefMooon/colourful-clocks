package com.chefmooon.colourfulclocks.common.core;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

public enum BornholmTopGlassTypes implements StringRepresentable {
    BASE(0, "base", "", Items.GLASS_PANE, Blocks.GLASS, TextUtil.res("block/glass_bornholm_dial")),
    GLASS_WHITE(1, "white_stained_glass", "White Stained Glass", Items.WHITE_STAINED_GLASS_PANE, Blocks.WHITE_STAINED_GLASS, TextUtil.res("block/white_stained_glass_bornholm_dial")),
    GLASS_ORANGE(3, "orange_stained_glass", "Orange Stained Glass", Items.ORANGE_STAINED_GLASS_PANE, Blocks.ORANGE_STAINED_GLASS, TextUtil.res("block/orange_stained_glass_bornholm_dial")),
    GLASS_MAGENTA(4, "magenta_stained_glass", "Magenta Stained Glass", Items.MAGENTA_STAINED_GLASS_PANE, Blocks.MAGENTA_STAINED_GLASS, TextUtil.res("block/magenta_stained_glass_bornholm_dial")),
    GLASS_LIGHT_BLUE(5, "light_blue_stained_glass", "Light Blue Stained Glass", Items.LIGHT_BLUE_STAINED_GLASS_PANE, Blocks.LIGHT_BLUE_STAINED_GLASS, TextUtil.res("block/light_blue_stained_glass_bornholm_dial")),
    GLASS_YELLOW(6, "yellow_stained_glass", "Yellow Stained Glass", Items.YELLOW_STAINED_GLASS_PANE, Blocks.YELLOW_STAINED_GLASS, TextUtil.res("block/yellow_stained_glass_bornholm_dial")),
    GLASS_LIME(7, "lime_stained_glass", "Lime Stained Glass", Items.LIME_STAINED_GLASS_PANE, Blocks.LIME_STAINED_GLASS, TextUtil.res("block/lime_stained_glass_bornholm_dial")),
    GLASS_PINK(8, "pink_stained_glass", "Pink Stained Glass", Items.PINK_STAINED_GLASS_PANE, Blocks.PINK_STAINED_GLASS, TextUtil.res("block/pink_stained_glass_bornholm_dial")),
    GLASS_GRAY(9, "gray_stained_glass", "Gray Stained Glass", Items.GRAY_STAINED_GLASS_PANE, Blocks.GRAY_STAINED_GLASS, TextUtil.res("block/gray_stained_glass_bornholm_dial")),
    GLASS_LIGHT_GRAY(10, "light_gray_stained_glass", "Light Gray Stained Glass", Items.LIGHT_GRAY_STAINED_GLASS_PANE, Blocks.LIGHT_GRAY_STAINED_GLASS, TextUtil.res("block/light_gray_stained_glass_bornholm_dial")),
    GLASS_CYAN(11, "cyan_stained_glass", "Cyan Stained Glass", Items.CYAN_STAINED_GLASS_PANE, Blocks.CYAN_STAINED_GLASS, TextUtil.res("block/cyan_stained_glass_bornholm_dial")),
    GLASS_PURPLE(12, "purple_stained_glass", "Purple Stained Glass", Items.PURPLE_STAINED_GLASS_PANE, Blocks.PURPLE_STAINED_GLASS, TextUtil.res("block/purple_stained_glass_bornholm_dial")),
    GLASS_BLUE(13, "blue_stained_glass", "Blue Stained Glass", Items.BLUE_STAINED_GLASS_PANE, Blocks.BLUE_STAINED_GLASS, TextUtil.res("block/blue_stained_glass_bornholm_dial")),
    GLASS_BROWN(14, "brown_stained_glass", "Brown Stained Glass", Items.BROWN_STAINED_GLASS_PANE, Blocks.BROWN_STAINED_GLASS, TextUtil.res("block/brown_stained_glass_bornholm_dial")),
    GLASS_GREEN(15, "green_stained_glass", "Green Stained Glass", Items.GREEN_STAINED_GLASS_PANE, Blocks.GREEN_STAINED_GLASS, TextUtil.res("block/green_stained_glass_bornholm_dial")),
    GLASS_RED(16, "red_stained_glass", "Red Stained Glass", Items.RED_STAINED_GLASS_PANE, Blocks.RED_STAINED_GLASS, TextUtil.res("block/red_stained_glass_bornholm_dial")),
    GLASS_BLACK(17, "black_stained_glass", "Black Stained Glass", Items.BLACK_STAINED_GLASS_PANE, Blocks.BLACK_STAINED_GLASS, TextUtil.res("block/black_stained_glass_bornholm_dial"))

    ;

    private final int id;
    private final String name;
    private final String en_us;
    private final Item item;
    private final Block block;
    private final ResourceLocation bornholmGlassTexture;

    BornholmTopGlassTypes(int id, String name, String en_us, Item item, Block block, ResourceLocation bornholmGlassTexture) {
        this.id = id;
        this.name = name;
        this.en_us = en_us;
        this.item = item;
        this.block = block;
        this.bornholmGlassTexture = bornholmGlassTexture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Item getItem() {
        return item;
    }

    public Block getBlock() {
        return block;
    }

    public ResourceLocation getBornholmGlassTexture() {
        return bornholmGlassTexture;
    }

    public String getBaseTranslation() {
        return (en_us.isEmpty()) ? "" : " " + en_us;
    }

    @Override
    public @NotNull String getSerializedName() {
        return "_" + name;
    }
}
