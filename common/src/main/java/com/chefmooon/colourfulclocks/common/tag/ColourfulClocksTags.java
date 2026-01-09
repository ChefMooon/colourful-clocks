package com.chefmooon.colourfulclocks.common.tag;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ColourfulClocksTags {

    public static final TagKey<Item> ITEM_HANDBELL = getItemTagKey("handbell");
    public static final TagKey<Item> CLOCK_PENDULUM = getItemTagKey("clock_pendulum");
    public static final TagKey<Item> CLOCK_DOOR = getItemTagKey("clock_door");
    public static final TagKey<Item> CLOCK_TOP_GLASS = getItemTagKey("clock_dial_glass");
    public static final TagKey<Item> CLOCK_HAND = getItemTagKey("clock_hand");

    public static final TagKey<Item> BORNHOLM_BASE = getItemTagKey("bornholm_base");
    public static final TagKey<Item> BORNHOLM_TRUNK = getItemTagKey("bornholm_trunk");
    public static final TagKey<Item> BORNHOLM_DIAL = getItemTagKey("bornholm_dial");
    public static final TagKey<Item> MANTEL_CLOCK = getItemTagKey("mantel_clock");
    public static final TagKey<Item> TALL_MANTEL_CLOCK = getItemTagKey("tall_mantel_clock");
    public static final TagKey<Item> WALL_CLOCK = getItemTagKey("wall_clock");
    public static final TagKey<Item> ALARM_CLOCK = getItemTagKey("alarm_clock");

    public static TagKey<Item> getItemTagKey(String path) {
        return TagKey.create(Registries.ITEM, TextUtil.res(path));
    }
    public static TagKey<Block> getBlockTagKey(String path) {
        return TagKey.create(Registries.BLOCK, TextUtil.res(path));
    }
}
