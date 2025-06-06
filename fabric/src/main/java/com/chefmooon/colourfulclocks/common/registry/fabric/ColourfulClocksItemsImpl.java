package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.data.GlassDialComponent;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.data.types.WoodTypes;
import com.chefmooon.colourfulclocks.common.item.*;
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

    public static final HashMap<WoodTypes, Supplier<Item>> MANTEL_CLOCK_VARIANTS = new HashMap<>();

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

    public static final HashMap<PocketWatchTypes, Supplier<Item>> POCKET_WATCH_VARIANTS = new HashMap<>();
    static {
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.IRON, IRON_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.COPPER, COPPER_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.EXPOSED_COPPER, EXPOSED_COPPER_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.WEATHERED_COPPER, WEATHERED_COPPER_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.OXIDIZED_COPPER, OXIDIZED_COPPER_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.WAXED_COPPER, WAXED_COPPER_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.WAXED_EXPOSED_COPPER, WAXED_EXPOSED_COPPER_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.WAXED_WEATHERED_COPPER, WAXED_WEATHERED_COPPER_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.WAXED_OXIDIZED_COPPER, WAXED_OXIDIZED_COPPER_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.GOLD, GOLD_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.DIAMOND, DIAMOND_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.NETHERITE, NETHERITE_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.QUARTZ, QUARTZ_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.AMETHYST, AMETHYST_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.LAPIS_LAZULI, LAPIS_LAZULI_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.REDSTONE, REDSTONE_POCKET_WATCH);
        POCKET_WATCH_VARIANTS.put(PocketWatchTypes.EMERALD, EMERALD_POCKET_WATCH);
    }

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

    public static final HashMap<PendulumTypes, Supplier<Item>> PENDULUM_VARIANTS = new HashMap<>();
    static {
        PENDULUM_VARIANTS.put(PendulumTypes.IRON, IRON_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.COPPER, COPPER_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.EXPOSED_COPPER, EXPOSED_COPPER_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.WEATHERED_COPPER, WEATHERED_COPPER_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.OXIDIZED_COPPER, OXIDIZED_COPPER_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.WAXED_COPPER, WAXED_COPPER_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.WAXED_EXPOSED_COPPER, WAXED_EXPOSED_COPPER_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.WAXED_WEATHERED_COPPER, WAXED_WEATHERED_COPPER_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.WAXED_OXIDIZED_COPPER, WAXED_OXIDIZED_COPPER_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.GOLD, GOLD_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.DIAMOND, DIAMOND_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.NETHERITE, NETHERITE_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.QUARTZ, QUARTZ_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.AMETHYST, AMETHYST_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.LAPIS_LAZULI, LAPIS_LAZULI_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.REDSTONE, REDSTONE_PENDULUM);
        PENDULUM_VARIANTS.put(PendulumTypes.EMERALD, EMERALD_PENDULUM);
    }

    private static void registerBornholmItems() {
        for (WoodTypes woodTypes : WoodTypes.values()) {
            // Base
            Supplier<Item> baseItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_BASE.withSuffix(woodTypes.getSerializedName()),
                    new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(woodTypes).get(), basicItem()));
            BORNHOLM_BASE_VARIANTS.put(woodTypes, baseItem);

            // Middle
            Supplier<Item> middleItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_MIDDLE.withSuffix(woodTypes.getSerializedName()),
                    new BornholmMiddleBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(woodTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.BORNHOLM_MIDDLE_GLASS_DATA, BornholmMiddleDoorComponent.getDefaultValue())));
            BORNHOLM_MIDDLE_VARIANTS.put(woodTypes, middleItem);

            // Top
            Supplier<Item> topItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_TOP.withSuffix(woodTypes.getSerializedName()),
                    new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(woodTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.BORNHOLM_TOP_GLASS_DATA, BornholmTopGlassComponent.getDefaultValue())));
            BORNHOLM_TOP_VARIANTS.put(woodTypes, topItem);
        }
    }

    private static void registerMantelClockItems() {
        for (WoodTypes woodTypes : WoodTypes.values()) {
            // Mantel Clock
            Supplier<Item> mantelClockItem = registerItemWithTab(ColourfulClocksItems.MANTEL_CLOCK.withSuffix(woodTypes.getSerializedName()),
                    new MantelClockBlockItem(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(woodTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.GLASS_DIAL_DATA, GlassDialComponent.getDefaultValue())));
            MANTEL_CLOCK_VARIANTS.put(woodTypes, mantelClockItem);
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
        registerMantelClockItems();
    }
}
