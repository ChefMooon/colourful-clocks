package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.data.*;
import com.chefmooon.colourfulclocks.common.data.types.*;
import com.chefmooon.colourfulclocks.common.item.*;
import com.chefmooon.colourfulclocks.common.item.fabric.HandbellBlockItemImpl;
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

public class ColourfulClocksItemsImpl {

    public static final HashMap<ClockTypes, Supplier<Item>> BORNHOLM_BASE_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> BORNHOLM_MIDDLE_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> BORNHOLM_TOP_VARIANTS = new HashMap<>();

    public static final HashMap<ClockTypes, Supplier<Item>> MANTEL_CLOCK_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> TALL_MANTEL_CLOCK_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> WALL_CLOCK_VARIANTS = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> WALL_CLOCK_VARIANTS_MEDIUM = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> WALL_CLOCK_VARIANTS_LARGE = new HashMap<>();
    public static final HashMap<ClockTypes, Supplier<Item>> ALARM_CLOCK_VARIANTS = new HashMap<>();

    public static final Supplier<Item> IRON_HANDBELL = registerItemWithTab(ColourfulClocksItems.IRON_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.IRON_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.IRON)), HandbellTypes.IRON));
    public static final Supplier<Item> COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.COPPER_HANDBELL,
            new WeatheringCopperHandbellBlockItem(ColourfulClocksBlocksImpl.COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getWeatheringCopperValue(HandbellTypes.COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> EXPOSED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_HANDBELL,
            new WeatheringCopperHandbellBlockItem(ColourfulClocksBlocksImpl.EXPOSED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getWeatheringCopperValue(HandbellTypes.EXPOSED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WEATHERED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_HANDBELL,
            new WeatheringCopperHandbellBlockItem(ColourfulClocksBlocksImpl.WEATHERED_COPPER_HANDBELL.get(), basicItem()
                .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getWeatheringCopperValue(HandbellTypes.WEATHERED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> OXIDIZED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.OXIDIZED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.OXIDIZED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WAXED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.WAXED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.WAXED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.WAXED_EXPOSED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.WAXED_EXPOSED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.WAXED_WEATHERED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.WAXED_WEATHERED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.WAXED_OXIDIZED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.WAXED_OXIDIZED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> GOLD_HANDBELL = registerItemWithTab(ColourfulClocksItems.GOLD_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.GOLD_HANDBELL.get(), basicItem()
                .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.GOLD)), HandbellTypes.GOLD));
    public static final Supplier<Item> DIAMOND_HANDBELL = registerItemWithTab(ColourfulClocksItems.DIAMOND_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.DIAMOND_HANDBELL.get(), basicItem()
                .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.DIAMOND)), HandbellTypes.DIAMOND));
    public static final Supplier<Item> NETHERITE_HANDBELL = registerItemWithTab(ColourfulClocksItems.NETHERITE_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.NETHERITE_HANDBELL.get(), basicItem()
                .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.NETHERITE)), HandbellTypes.NETHERITE));
    public static final Supplier<Item> QUARTZ_HANDBELL = registerItemWithTab(ColourfulClocksItems.QUARTZ_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.QUARTZ_HANDBELL.get(), basicItem()
                .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.QUARTZ)), HandbellTypes.QUARTZ));
    public static final Supplier<Item> AMETHYST_HANDBELL = registerItemWithTab(ColourfulClocksItems.AMETHYST_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.AMETHYST_HANDBELL.get(), basicItem()
                .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.AMETHYST)), HandbellTypes.AMETHYST));
    public static final Supplier<Item> LAPIS_LAZULI_HANDBELL = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.LAPIS_LAZULI_HANDBELL.get(), basicItem()
                .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.LAPIS_LAZULI)), HandbellTypes.LAPIS_LAZULI));
    public static final Supplier<Item> REDSTONE_HANDBELL = registerItemWithTab(ColourfulClocksItems.REDSTONE_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.REDSTONE_HANDBELL.get(), basicItem()
                .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.REDSTONE)), HandbellTypes.REDSTONE));
    public static final Supplier<Item> EMERALD_HANDBELL = registerItemWithTab(ColourfulClocksItems.EMERALD_HANDBELL,
            new HandbellBlockItemImpl(ColourfulClocksBlocksImpl.EMERALD_HANDBELL.get(), basicItem()
                .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.EMERALD)), HandbellTypes.EMERALD));

    public static final HashMap<HandbellTypes, Supplier<Item>> HANDBELL_VARIANTS = new HashMap<>();
    static {
        HANDBELL_VARIANTS.put(HandbellTypes.IRON, IRON_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.COPPER, COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.EXPOSED_COPPER, EXPOSED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WEATHERED_COPPER, WEATHERED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.OXIDIZED_COPPER, OXIDIZED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WAXED_COPPER, WAXED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WAXED_EXPOSED_COPPER, WAXED_EXPOSED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WAXED_WEATHERED_COPPER, WAXED_WEATHERED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WAXED_OXIDIZED_COPPER, WAXED_OXIDIZED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.GOLD, GOLD_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.DIAMOND, DIAMOND_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.NETHERITE, NETHERITE_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.QUARTZ, QUARTZ_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.AMETHYST, AMETHYST_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.LAPIS_LAZULI, LAPIS_LAZULI_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.REDSTONE, REDSTONE_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.EMERALD, EMERALD_HANDBELL);
    }

    public static final Supplier<Item> IRON_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.IRON_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.IRON, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.IRON))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.COPPER_POCKET_WATCH,
            new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getWeatheredValue(PocketWatchTypes.COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH,
            new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getWeatheredValue(PocketWatchTypes.EXPOSED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH,
            new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getWeatheredValue(PocketWatchTypes.WEATHERED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));

    public static final Supplier<Item> OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.OXIDIZED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.WAXED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.WAXED_EXPOSED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.WAXED_WEATHERED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.WAXED_OXIDIZED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> GOLD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.GOLD_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.GOLD, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.GOLD))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> DIAMOND_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.DIAMOND_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.DIAMOND, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.DIAMOND))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> NETHERITE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.NETHERITE_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.NETHERITE, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.NETHERITE))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));

    public static final Supplier<Item> QUARTZ_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.QUARTZ_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.QUARTZ, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.QUARTZ))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> AMETHYST_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.AMETHYST_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.AMETHYST, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.AMETHYST))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> LAPIS_LAZULI_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.LAPIS_LAZULI, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.LAPIS_LAZULI))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> REDSTONE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.REDSTONE_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.REDSTONE, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.REDSTONE))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> EMERALD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EMERALD_POCKET_WATCH,
            new PocketWatchItem(PocketWatchTypes.EMERALD, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.EMERALD))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));

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

    public static final Supplier<Item> IRON_PENDULUM = registerItemWithTab(ColourfulClocksItems.IRON_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.IRON))));
    public static final Supplier<Item> COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.COPPER_PENDULUM, new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getWeatheringCopperValue(PendulumTypes.COPPER))));
    public static final Supplier<Item> EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_PENDULUM, new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getWeatheringCopperValue(PendulumTypes.EXPOSED_COPPER))));
    public static final Supplier<Item> WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_PENDULUM, new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getWeatheringCopperValue(PendulumTypes.WEATHERED_COPPER))));
    public static final Supplier<Item> OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.OXIDIZED_COPPER))));
    public static final Supplier<Item> WAXED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.WAXED_COPPER))));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.WAXED_EXPOSED_COPPER))));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.WAXED_WEATHERED_COPPER))));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.WAXED_OXIDIZED_COPPER))));
    public static final Supplier<Item> GOLD_PENDULUM = registerItemWithTab(ColourfulClocksItems.GOLD_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.GOLD))));
    public static final Supplier<Item> DIAMOND_PENDULUM = registerItemWithTab(ColourfulClocksItems.DIAMOND_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.DIAMOND))));
    public static final Supplier<Item> NETHERITE_PENDULUM = registerItemWithTab(ColourfulClocksItems.NETHERITE_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.NETHERITE))));

    public static final Supplier<Item> QUARTZ_PENDULUM = registerItemWithTab(ColourfulClocksItems.QUARTZ_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.QUARTZ))));
    public static final Supplier<Item> AMETHYST_PENDULUM = registerItemWithTab(ColourfulClocksItems.AMETHYST_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.AMETHYST))));
    public static final Supplier<Item> LAPIS_LAZULI_PENDULUM = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.LAPIS_LAZULI))));
    public static final Supplier<Item> REDSTONE_PENDULUM = registerItemWithTab(ColourfulClocksItems.REDSTONE_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.REDSTONE))));
    public static final Supplier<Item> EMERALD_PENDULUM = registerItemWithTab(ColourfulClocksItems.EMERALD_PENDULUM, new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.EMERALD))));

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
                    new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(clockTypes).get(), basicItem()));
            BORNHOLM_BASE_VARIANTS.put(clockTypes, baseItem);

            // Bornholm Middle
            Supplier<Item> middleItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_MIDDLE.withSuffix(clockTypes.getSerializedName()),
                    new BornholmMiddleBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.BORNHOLM_MIDDLE_GLASS_DATA, BornholmMiddleDoorComponent.getDefaultValue())));
            BORNHOLM_MIDDLE_VARIANTS.put(clockTypes, middleItem);

            // Bornholm Top
            Supplier<Item> topItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_TOP.withSuffix(clockTypes.getSerializedName()),
                    new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.BORNHOLM_TOP_GLASS_DATA, BornholmTopGlassComponent.getDefaultValue())));
            BORNHOLM_TOP_VARIANTS.put(clockTypes, topItem);

            // Mantel Clock
            Supplier<Item> mantelClockItem = registerItemWithTab(ColourfulClocksItems.MANTEL_CLOCK.withSuffix(clockTypes.getSerializedName()),
                    new MantelClockBlockItem(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.MANTEL_CLOCK_DATA, MantelClockComponent.getNoPendulumValue())));
            MANTEL_CLOCK_VARIANTS.put(clockTypes, mantelClockItem);

            // Tall Mantel Clock
            Supplier<Item> tallMantelClockItem = registerItemWithTab(ColourfulClocksItems.TALL_MANTEL_CLOCK.withSuffix(clockTypes.getSerializedName()),
                    new TallMantelClockBlockItem(ColourfulClocksBlocksImpl.TALL_MANTEL_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.MANTEL_CLOCK_DATA, MantelClockComponent.getBasicClockValue())));
            TALL_MANTEL_CLOCK_VARIANTS.put(clockTypes, tallMantelClockItem);

            // Wall Clock
            Supplier<Item> wallClockItem = registerItemWithTab(ColourfulClocksItems.WALL_CLOCK.withSuffix(clockTypes.getSerializedName()),
                    new WallClockBlockItem(ColourfulClocksBlocksImpl.WALL_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.WALL_CLOCK_DATA, WallClockComponent.getDefaultValue())));
            WALL_CLOCK_VARIANTS.put(clockTypes, wallClockItem);

            // Wall Clock Medium
            Supplier<Item> wallClockMediumItem = registerItemWithTab(ColourfulClocksItems.WALL_CLOCK.withSuffix(clockTypes.getSerializedName() + "_medium"),
                    new WallClockBlockItem(ColourfulClocksBlocksImpl.WALL_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.WALL_CLOCK_DATA, WallClockComponent.getValue(WallClockType.MEDIUM))));
            WALL_CLOCK_VARIANTS_MEDIUM.put(clockTypes, wallClockMediumItem);

            // Wall Clock Large
            Supplier<Item> wallClockLargeItem = registerItemWithTab(ColourfulClocksItems.WALL_CLOCK.withSuffix(clockTypes.getSerializedName() + "_large"),
                    new WallClockBlockItem(ColourfulClocksBlocksImpl.WALL_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.WALL_CLOCK_DATA, WallClockComponent.getValue(WallClockType.LARGE))));
            WALL_CLOCK_VARIANTS_LARGE.put(clockTypes, wallClockLargeItem);

            // Alarm Clock
            Supplier<Item> alarmClockItem = registerItemWithTab(ColourfulClocksItems.ALARM_CLOCK.withSuffix(clockTypes.getSerializedName()),
                    new AlarmClockBlockItem(ColourfulClocksBlocksImpl.ALARM_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.ALARM_CLOCK_DATA, AlarmClockComponent.getDefaultValue())));
            ALARM_CLOCK_VARIANTS.put(clockTypes, alarmClockItem);
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
        registerClockItems();
    }
}
