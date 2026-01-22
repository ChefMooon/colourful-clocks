package com.chefmooon.colourfulclocks.common.util.fabric;

import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.mojang.datafixers.util.Pair;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Supplier;

public class ColourfulClocksTypeUtilImpl {
    private static final Map<Item, PocketWatchTypes> POCKET_WATCH_BY_TYPE = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get(), PocketWatchTypes.IRON),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get(), PocketWatchTypes.COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get(), PocketWatchTypes.EXPOSED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get(), PocketWatchTypes.WEATHERED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get(), PocketWatchTypes.OXIDIZED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get(), PocketWatchTypes.WAXED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get(), PocketWatchTypes.WAXED_EXPOSED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get(), PocketWatchTypes.WAXED_WEATHERED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get(), PocketWatchTypes.WAXED_OXIDIZED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.GOLD_POCKET_WATCH.get(), PocketWatchTypes.GOLD),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get(), PocketWatchTypes.DIAMOND),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get(), PocketWatchTypes.NETHERITE),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.EMERALD_POCKET_WATCH.get(), PocketWatchTypes.EMERALD),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.AMETHYST_POCKET_WATCH.get(), PocketWatchTypes.AMETHYST),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.QUARTZ_POCKET_WATCH.get(), PocketWatchTypes.QUARTZ),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.LAPIS_LAZULI_POCKET_WATCH.get(), PocketWatchTypes.LAPIS_LAZULI),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.REDSTONE_POCKET_WATCH.get(), PocketWatchTypes.REDSTONE)
    );

    private static final Map<Item, PendulumTypes> PENDULUM_BY_ITEM = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.IRON_PENDULUM.get(), PendulumTypes.IRON),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.COPPER_PENDULUM.get(), PendulumTypes.COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get(), PendulumTypes.EXPOSED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get(), PendulumTypes.WEATHERED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get(), PendulumTypes.OXIDIZED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get(), PendulumTypes.WAXED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get(), PendulumTypes.WAXED_EXPOSED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get(), PendulumTypes.WAXED_WEATHERED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get(), PendulumTypes.WAXED_OXIDIZED_COPPER),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.GOLD_PENDULUM.get(), PendulumTypes.GOLD),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get(), PendulumTypes.DIAMOND),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get(), PendulumTypes.NETHERITE),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.EMERALD_PENDULUM.get(), PendulumTypes.EMERALD),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.AMETHYST_PENDULUM.get(), PendulumTypes.AMETHYST),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.QUARTZ_PENDULUM.get(), PendulumTypes.QUARTZ),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.LAPIS_LAZULI_PENDULUM.get(), PendulumTypes.LAPIS_LAZULI),
            new AbstractMap.SimpleEntry<>(ColourfulClocksItemsImpl.REDSTONE_PENDULUM.get(), PendulumTypes.REDSTONE)
    );

    public static Item getPocketWatchItemFromType(PocketWatchTypes pocketWatchTypes) {
        return ColourfulClocksItemsImpl.POCKET_WATCH_VARIANTS.getOrDefault(pocketWatchTypes, () -> Items.AIR).get();
    }

    public static PocketWatchTypes getPocketWatchTypeFromItem(Item item) {
        return POCKET_WATCH_BY_TYPE.getOrDefault(item, PocketWatchTypes.EMPTY);
    }

    public static Item getPendulumItemFromType(PendulumTypes pendulumType) {
        return ColourfulClocksItemsImpl.PENDULUM_VARIANTS.getOrDefault(pendulumType, () -> Items.AIR).get();
    }

    public static PendulumTypes getPendulumTypeFromItem(Item item) {
        return PENDULUM_BY_ITEM.getOrDefault(item, PendulumTypes.EMPTY);
    }

    public static boolean isCopperPendulum(ItemStack itemStack) {
        return itemStack.is(ColourfulClocksItemsImpl.COPPER_PENDULUM.get())
                || itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get())
                || itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get());
    }

    public static Supplier<Item> getNextWeatheredCopperPendulum(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM;
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM;
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM;
        } else {
            return ItemStack.EMPTY::getItem;
        }
    }

    public static Supplier<Item> getWaxedCopperPendulum(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM;
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM;
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM;
        } else if (itemStack.is(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM;
        } else {
            return ItemStack.EMPTY::getItem;
        }
    }

    public static Pair<Supplier<Item>, Supplier<SoundEvent>> getScrapedCopperPendulum(ItemStack itemStack) {
        // wax -> no wax
        if (itemStack.is(ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
            // previous weathered state
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        } else if (itemStack.is(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        }else {
            return new Pair<>(ItemStack.EMPTY::getItem, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        }
    }
}
