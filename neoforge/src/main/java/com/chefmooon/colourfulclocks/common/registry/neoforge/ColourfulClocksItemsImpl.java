package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.data.ClockComponent;
import com.chefmooon.colourfulclocks.common.data.GlassDialComponent;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.item.*;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.function.Supplier;

import static com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems.basicItem;

public class ColourfulClocksItemsImpl {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ColourfulClocks.MOD_ID);
    public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

    public static final HashMap<ClockTypes, Supplier<Item>> BORNHOLM_BASE_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> BORNHOLM_MIDDLE_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> BORNHOLM_TOP_VARIANTS = new HashMap<>();

    public static final HashMap<ClockTypes, Supplier<Item>> MANTEL_CLOCK_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> TALL_MANTEL_CLOCK_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> WALL_CLOCK_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> ALARM_CLOCK_VARIANTS = new HashMap<>();

    public static final Supplier<Item> IRON_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.IRON_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.IRON, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.COPPER_POCKET_WATCH,
            () -> new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_WEATHERING, 0)
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH,
            () -> new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_WEATHERING, 0)
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH,
            () -> new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_WEATHERING, 0)
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> GOLD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.GOLD_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.GOLD, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> DIAMOND_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.DIAMOND_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.DIAMOND, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> NETHERITE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.NETHERITE_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.NETHERITE, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));

    public static final Supplier<Item> QUARTZ_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.QUARTZ_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.QUARTZ, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> AMETHYST_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.AMETHYST_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.AMETHYST, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> LAPIS_LAZULI_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.LAPIS_LAZULI, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> REDSTONE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.REDSTONE_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.REDSTONE, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> EMERALD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EMERALD_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.EMERALD, basicItem().component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));

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

    public static final Supplier<Item> IRON_PENDULUM = registerItemWithTab(ColourfulClocksItems.IRON_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.COPPER_PENDULUM, () -> new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM_WEATHERING, 0)));
    public static final Supplier<Item> EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_PENDULUM, () -> new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM_WEATHERING, 0)));
    public static final Supplier<Item> WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_PENDULUM, () -> new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM_WEATHERING, 0)));
    public static final Supplier<Item> OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> WAXED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> GOLD_PENDULUM = registerItemWithTab(ColourfulClocksItems.GOLD_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> DIAMOND_PENDULUM = registerItemWithTab(ColourfulClocksItems.DIAMOND_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> NETHERITE_PENDULUM = registerItemWithTab(ColourfulClocksItems.NETHERITE_PENDULUM, () -> new Item(basicItem()));

    public static final Supplier<Item> QUARTZ_PENDULUM = registerItemWithTab(ColourfulClocksItems.QUARTZ_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> AMETHYST_PENDULUM = registerItemWithTab(ColourfulClocksItems.AMETHYST_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> LAPIS_LAZULI_PENDULUM = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> REDSTONE_PENDULUM = registerItemWithTab(ColourfulClocksItems.REDSTONE_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> EMERALD_PENDULUM = registerItemWithTab(ColourfulClocksItems.EMERALD_PENDULUM, () -> new Item(basicItem()));

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

    private static void registerClockItems() {
        for (ClockTypes clockTypes : ClockTypes.values()) {
            // Bornholm Base
            Supplier<Item> baseItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_BASE.withSuffix(clockTypes.getSerializedName()),
                    () -> new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(clockTypes).get(), basicItem()));
            BORNHOLM_BASE_VARIANTS.put(clockTypes, baseItem);

            // Bornholm Middle
            Supplier<Item> middleItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_MIDDLE.withSuffix(clockTypes.getSerializedName()),
                    () -> new BornholmMiddleBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.BORNHOLM_MIDDLE_GLASS_DATA, BornholmMiddleDoorComponent.getDefaultValue())));
            BORNHOLM_MIDDLE_VARIANTS.put(clockTypes, middleItem);

            // Bornholm Top
            Supplier<Item> topItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_TOP.withSuffix(clockTypes.getSerializedName()),
                    () -> new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.BORNHOLM_TOP_GLASS_DATA, BornholmTopGlassComponent.getDefaultValue())));
            BORNHOLM_TOP_VARIANTS.put(clockTypes, topItem);

            // Mantel Clock
            Supplier<Item> mantelClockItem = registerItemWithTab(ColourfulClocksItems.MANTEL_CLOCK.withSuffix(clockTypes.getSerializedName()),
                    () -> new MantelClockBlockItem(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.CLOCK_DATA, ClockComponent.getNoPendulumValue())));
            MANTEL_CLOCK_VARIANTS.put(clockTypes, mantelClockItem);

            // Tall Mantel Clock
            Supplier<Item> tallMantelClockItem = registerItemWithTab(ColourfulClocksItems.TALL_MANTEL_CLOCK.withSuffix(clockTypes.getSerializedName()),
                    () -> new TallMantelClockBlockItem(ColourfulClocksBlocksImpl.TALL_MANTEL_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.CLOCK_DATA, ClockComponent.getBasicClockValue())));
            TALL_MANTEL_CLOCK_VARIANTS.put(clockTypes, tallMantelClockItem);

            // Wall Clock
            Supplier<Item> wallClockItem = registerItemWithTab(ColourfulClocksItems.WALL_CLOCK.withSuffix(clockTypes.getSerializedName()),
                    () -> new ClockDataBlockItem(ColourfulClocksBlocksImpl.WALL_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.CLOCK_DATA, ClockComponent.getNoPendulumValue())
                            .component(ColourfulClocksDataComponentTypesImpl.GLASS_DIAL_DATA, GlassDialComponent.getDefaultValue())));
            WALL_CLOCK_VARIANTS.put(clockTypes, wallClockItem);

            // Alarm Clock
            Supplier<Item> alarmClockItem = registerItemWithTab(ColourfulClocksItems.ALARM_CLOCK.withSuffix(clockTypes.getSerializedName()),
                    () -> new ClockDataBlockItem(ColourfulClocksBlocksImpl.ALARM_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.CLOCK_DATA, ClockComponent.getNoPendulumValue())
                            .component(ColourfulClocksDataComponentTypesImpl.GLASS_DIAL_DATA, GlassDialComponent.getDefaultValue())));
            ALARM_CLOCK_VARIANTS.put(clockTypes, alarmClockItem);
        }
    }

    public static Supplier<Item> registerItemWithTab(final ResourceLocation location, final Supplier<Item> supplier) {
        Supplier<Item> item = ITEMS.register(location.getPath(), supplier);
        CREATIVE_TAB_ITEMS.add(item);
        return item;
    }

    public static Supplier<Item> registerItem(final ResourceLocation location, final Supplier<Item> supplier) {
        return ITEMS.register(location.getPath(), supplier);
    }

    public static void register(IEventBus eventBus) {
        registerClockItems();
        ITEMS.register(eventBus);
    }
}
