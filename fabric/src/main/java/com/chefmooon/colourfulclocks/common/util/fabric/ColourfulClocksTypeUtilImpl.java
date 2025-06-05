package com.chefmooon.colourfulclocks.common.util.fabric;

import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.AbstractMap;
import java.util.Map;

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

    private static final Map<PendulumTypes, Item> PENDULUM_BY_TYPE = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(PendulumTypes.IRON, ColourfulClocksItemsImpl.IRON_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.COPPER, ColourfulClocksItemsImpl.COPPER_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.EXPOSED_COPPER, ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.WEATHERED_COPPER, ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.OXIDIZED_COPPER, ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.WAXED_COPPER, ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.WAXED_EXPOSED_COPPER, ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.WAXED_WEATHERED_COPPER, ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.WAXED_OXIDIZED_COPPER, ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.GOLD, ColourfulClocksItemsImpl.GOLD_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.DIAMOND, ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.NETHERITE, ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.EMERALD, ColourfulClocksItemsImpl.EMERALD_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.AMETHYST, ColourfulClocksItemsImpl.AMETHYST_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.QUARTZ, ColourfulClocksItemsImpl.QUARTZ_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.LAPIS_LAZULI, ColourfulClocksItemsImpl.LAPIS_LAZULI_PENDULUM.get()),
            new AbstractMap.SimpleEntry<>(PendulumTypes.REDSTONE, ColourfulClocksItemsImpl.REDSTONE_PENDULUM.get())
    );

    public static Item getPocketWatchItemFromType(PocketWatchTypes pocketWatchTypes) {
        return ColourfulClocksItemsImpl.POCKET_WATCH_VARIANTS.getOrDefault(pocketWatchTypes, () -> Items.AIR).get();
    }

    public static PocketWatchTypes getPocketWatchTypeFromItem(Item item) {
        return POCKET_WATCH_BY_TYPE.getOrDefault(item, PocketWatchTypes.EMPTY);
    }

    public static Item getPendulumItemFromType(PendulumTypes pendulumType) {
        return PENDULUM_BY_TYPE.getOrDefault(pendulumType, Items.AIR);
    }

    public static PendulumTypes getPendulumTypeFromItem(Item item) {
        return PENDULUM_BY_ITEM.getOrDefault(item, PendulumTypes.EMPTY);
    }
}
