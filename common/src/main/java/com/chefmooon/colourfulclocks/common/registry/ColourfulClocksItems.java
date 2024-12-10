package com.chefmooon.colourfulclocks.common.registry;


import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ColourfulClocksItems {

    public static Item.Properties basicItem() {
        return new Item.Properties();
    }

    public static Item.Properties noStack() {
        return basicItem().stacksTo(1);
    }

    public static final ResourceLocation IRON_POCKET_WATCH = item("iron_pocket_watch");
    public static final ResourceLocation COPPER_POCKET_WATCH = item("copper_pocket_watch");
    public static final ResourceLocation EXPOSED_COPPER_POCKET_WATCH = item("exposed_copper_pocket_watch");
    public static final ResourceLocation WEATHERED_COPPER_POCKET_WATCH = item("weathered_copper_pocket_watch");
    public static final ResourceLocation OXIDIZED_COPPER_POCKET_WATCH = item("oxidized_copper_pocket_watch");
    public static final ResourceLocation WAXED_COPPER_POCKET_WATCH = item("waxed_copper_pocket_watch");
    public static final ResourceLocation WAXED_EXPOSED_COPPER_POCKET_WATCH = item("waxed_exposed_copper_pocket_watch");
    public static final ResourceLocation WAXED_WEATHERED_COPPER_POCKET_WATCH = item("waxed_weathered_copper_pocket_watch");
    public static final ResourceLocation WAXED_OXIDIZED_COPPER_POCKET_WATCH = item("waxed_oxidized_copper_pocket_watch");
    public static final ResourceLocation GOLD_POCKET_WATCH = item("gold_pocket_watch");
    public static final ResourceLocation DIAMOND_POCKET_WATCH = item("diamond_pocket_watch");
    public static final ResourceLocation NETHERITE_POCKET_WATCH = item("netherite_pocket_watch");

    public static final ResourceLocation QUARTZ_POCKET_WATCH = item("quartz_pocket_watch");
    public static final ResourceLocation AMETHYST_POCKET_WATCH = item("amethyst_pocket_watch");
    public static final ResourceLocation LAPIS_LAZULI_POCKET_WATCH = item("lapis_lazuli_pocket_watch");
    public static final ResourceLocation REDSTONE_POCKET_WATCH = item("redstone_pocket_watch");
    public static final ResourceLocation EMERALD_POCKET_WATCH = item("emerald_pocket_watch");

    public static final ResourceLocation IRON_POCKET_WATCH_IN_CLOCK = item("iron_pocket_watch_in_clock");
    public static final ResourceLocation COPPER_POCKET_WATCH_IN_CLOCK = item("copper_pocket_watch_in_clock");
    public static final ResourceLocation EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK = item("exposed_copper_pocket_watch_in_clock");
    public static final ResourceLocation WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK = item("weathered_copper_pocket_watch_in_clock");
    public static final ResourceLocation OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK = item("oxidized_copper_pocket_watch_in_clock");
    public static final ResourceLocation WAXED_COPPER_POCKET_WATCH_IN_CLOCK = item("waxed_copper_pocket_watch_in_clock");
    public static final ResourceLocation WAXED_EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK = item("waxed_exposed_copper_pocket_watch_in_clock");
    public static final ResourceLocation WAXED_WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK = item("waxed_weathered_copper_pocket_watch_in_clock");
    public static final ResourceLocation WAXED_OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK = item("waxed_oxidized_copper_pocket_watch_in_clock");
    public static final ResourceLocation GOLD_POCKET_WATCH_IN_CLOCK = item("gold_pocket_watch_in_clock");
    public static final ResourceLocation DIAMOND_POCKET_WATCH_IN_CLOCK = item("diamond_pocket_watch_in_clock");
    public static final ResourceLocation NETHERITE_POCKET_WATCH_IN_CLOCK = item("netherite_pocket_watch_in_clock");

    public static final ResourceLocation QUARTZ_POCKET_WATCH_IN_CLOCK = item("quartz_pocket_watch_in_clock");
    public static final ResourceLocation AMETHYST_POCKET_WATCH_IN_CLOCK = item("amethyst_pocket_watch_in_clock");
    public static final ResourceLocation LAPIS_LAZULI_POCKET_WATCH_IN_CLOCK = item("lapis_lazuli_pocket_watch_in_clock");
    public static final ResourceLocation REDSTONE_POCKET_WATCH_IN_CLOCK = item("redstone_pocket_watch_in_clock");
    public static final ResourceLocation EMERALD_POCKET_WATCH_IN_CLOCK = item("emerald_pocket_watch_in_clock");

    public static final ResourceLocation IRON_PENDULUM = item("iron_pendulum");
    public static final ResourceLocation COPPER_PENDULUM = item("copper_pendulum");
    public static final ResourceLocation EXPOSED_COPPER_PENDULUM = item("exposed_copper_pendulum");
    public static final ResourceLocation WEATHERED_COPPER_PENDULUM = item("weathered_copper_pendulum");
    public static final ResourceLocation OXIDIZED_COPPER_PENDULUM = item("oxidized_copper_pendulum");
    public static final ResourceLocation WAXED_COPPER_PENDULUM = item("waxed_copper_pendulum");
    public static final ResourceLocation WAXED_EXPOSED_COPPER_PENDULUM = item("waxed_exposed_copper_pendulum");
    public static final ResourceLocation WAXED_WEATHERED_COPPER_PENDULUM = item("waxed_weathered_copper_pendulum");
    public static final ResourceLocation WAXED_OXIDIZED_COPPER_PENDULUM = item("waxed_oxidized_copper_pendulum");
    public static final ResourceLocation GOLD_PENDULUM = item("gold_pendulum");
    public static final ResourceLocation DIAMOND_PENDULUM = item("diamond_pendulum");
    public static final ResourceLocation NETHERITE_PENDULUM = item("netherite_pendulum");

    public static final ResourceLocation QUARTZ_PENDULUM = item("quartz_pendulum");
    public static final ResourceLocation AMETHYST_PENDULUM = item("amethyst_pendulum");
    public static final ResourceLocation LAPIS_LAZULI_PENDULUM = item("lapis_lazuli_pendulum");
    public static final ResourceLocation REDSTONE_PENDULUM = item("redstone_pendulum");
    public static final ResourceLocation EMERALD_PENDULUM = item("emerald_pendulum");

    public static final ResourceLocation BORNHOLM_BASE = item("bornholm_base");
    public static final ResourceLocation BORNHOLM_MIDDLE = item("bornholm_middle");
    public static final ResourceLocation BORNHOLM_TOP = item("bornholm_top");

    private static ResourceLocation item(String string) {
        return TextUtil.res(string);
    }

    public static void register() {
    }
}
