package com.chefmooon.colourfulclocks.common.tag;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ColourfulClocksTags {

    public static final TagKey<Item> CLOCK_PENDULUM = getItemTagKey("clock_pendulum");
    public static final TagKey<Item> CLOCK_DOOR = getItemTagKey("clock_door");
    public static final TagKey<Item> CLOCK_TOP_GLASS = getItemTagKey("clock_dial_glass");
    public static final TagKey<Item> CLOCK_HAND = getItemTagKey("clock_hand");

    public static TagKey<Item> getItemTagKey(String path) {
        return TagKey.create(Registries.ITEM, TextUtil.res(path));
    }
    public static TagKey<Block> getBlockTagKey(String path) {
        return TagKey.create(Registries.BLOCK, TextUtil.res(path));
    }
}
