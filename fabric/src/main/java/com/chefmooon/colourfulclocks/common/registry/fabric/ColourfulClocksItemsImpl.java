package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.item.BornholmTopBlockItem;
import com.chefmooon.colourfulclocks.common.item.PocketWatchItem;
import com.chefmooon.colourfulclocks.common.item.WeatheringPendulumItem;
import com.chefmooon.colourfulclocks.common.item.WeatheringPocketWatchItem;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.function.Supplier;

import static com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems.basicItem;
import static com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems.noStack;

public class ColourfulClocksItemsImpl {

    public static final HashMap<WoodTypes, Supplier<Item>> BORNHOLM_BASE_VARIANTS = new HashMap<>();
    public static final HashMap<WoodTypes, Supplier<Item>> BORNHOLM_MIDDLE_VARIANTS = new HashMap<>();
    public static final HashMap<WoodTypes, Supplier<Item>> BORNHOLM_TOP_VARIANTS = new HashMap<>();

    public static final Supplier<Item> IRON_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.IRON_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.IRON, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.COPPER_POCKET_WATCH,
            new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, noStack()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_WEATHERING, 0)
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH,
            new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, noStack()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_WEATHERING, 0)
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH,
            new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, noStack()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_WEATHERING, 0)
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> GOLD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.GOLD_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.GOLD, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> DIAMOND_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.DIAMOND_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.DIAMOND, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> NETHERITE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.NETHERITE_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.NETHERITE, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));

    public static final Supplier<Item> QUARTZ_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.QUARTZ_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.QUARTZ, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> AMETHYST_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.AMETHYST_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.AMETHYST, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> LAPIS_LAZULI_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.LAPIS_LAZULI, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> REDSTONE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.REDSTONE_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.REDSTONE, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> EMERALD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EMERALD_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.EMERALD, noStack().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));

    public static final Supplier<Item> IRON_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.IRON_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.IRON, noStack()));
    public static final Supplier<Item> COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> GOLD_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.GOLD_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.GOLD, noStack()));
    public static final Supplier<Item> DIAMOND_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.DIAMOND_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.DIAMOND, noStack()));
    public static final Supplier<Item> NETHERITE_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.NETHERITE_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.NETHERITE, noStack()));

    public static final Supplier<Item> QUARTZ_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.QUARTZ_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.QUARTZ, noStack()));
    public static final Supplier<Item> AMETHYST_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.AMETHYST_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.AMETHYST, noStack()));
    public static final Supplier<Item> LAPIS_LAZULI_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.LAPIS_LAZULI_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.LAPIS_LAZULI, noStack()));
    public static final Supplier<Item> REDSTONE_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.REDSTONE_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.REDSTONE, noStack()));
    public static final Supplier<Item> EMERALD_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.EMERALD_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItem(PocketWatchTypes.EMERALD, noStack()));

    public static final Supplier<Item> IRON_PENDULUM = registerItemWithTab(ColourfulClocksItems.IRON_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.COPPER_PENDULUM, new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM_WEATHERING, 0)));
    public static final Supplier<Item> EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_PENDULUM, new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM_WEATHERING, 0)));
    public static final Supplier<Item> WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_PENDULUM, new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM_WEATHERING, 0)));
    public static final Supplier<Item> OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> WAXED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> GOLD_PENDULUM = registerItemWithTab(ColourfulClocksItems.GOLD_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> DIAMOND_PENDULUM = registerItemWithTab(ColourfulClocksItems.DIAMOND_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> NETHERITE_PENDULUM = registerItemWithTab(ColourfulClocksItems.NETHERITE_PENDULUM, new Item(basicItem()));

    public static final Supplier<Item> QUARTZ_PENDULUM = registerItemWithTab(ColourfulClocksItems.QUARTZ_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> AMETHYST_PENDULUM = registerItemWithTab(ColourfulClocksItems.AMETHYST_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> LAPIS_LAZULI_PENDULUM = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> REDSTONE_PENDULUM = registerItemWithTab(ColourfulClocksItems.REDSTONE_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> EMERALD_PENDULUM = registerItemWithTab(ColourfulClocksItems.EMERALD_PENDULUM, new Item(basicItem()));

    private static void registerBornholmItems() {
        for (WoodTypes woodTypes : WoodTypes.values()) {
            // Base
            Supplier<Item> baseItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_BASE.withSuffix(woodTypes.getSerializedName()),
                    new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(woodTypes).get(), basicItem()));
            BORNHOLM_BASE_VARIANTS.put(woodTypes, baseItem);

            // Middle
            Supplier<Item> middleItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_MIDDLE.withSuffix(woodTypes.getSerializedName()),
                    new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(woodTypes).get(), basicItem()));
            BORNHOLM_MIDDLE_VARIANTS.put(woodTypes, middleItem);

            // Top
            Supplier<Item> topItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_TOP.withSuffix(woodTypes.getSerializedName()),
                    new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(woodTypes).get(), basicItem()));
            BORNHOLM_TOP_VARIANTS.put(woodTypes, topItem);
        }
    }

    public static Supplier<Item> registerItemWithTab(final ResourceLocation location, final Item item) {
        Registry.register(BuiltInRegistries.ITEM, location, item);
        ItemGroupEvents.modifyEntriesEvent(ColourfulClocksCreativeTabsImpl.ITEM_GROUP).register(entries -> entries.accept(item));
        return () -> item;
    }

    public static Supplier<Item> registerItem(final ResourceLocation location, final Item item) {
        Registry.register(BuiltInRegistries.ITEM, location, item);
        return () -> item;
    }

    public static void register() {
        registerBornholmItems();
    }
}
