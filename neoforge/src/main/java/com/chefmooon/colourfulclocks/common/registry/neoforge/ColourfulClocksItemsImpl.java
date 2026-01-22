package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.data.*;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
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

    public static final Supplier<Item> IRON_HANDBELL = registerItemWithTab(ColourfulClocksItems.IRON_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.IRON_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getBaseValue(HandbellTypes.IRON)), HandbellTypes.IRON));
    public static final Supplier<Item> COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.COPPER_HANDBELL,
            () -> new WeatheringCopperHandbellBlockItem(ColourfulClocksBlocksImpl.COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getWeatheringCopperValue(HandbellTypes.COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> EXPOSED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_HANDBELL,
            () -> new WeatheringCopperHandbellBlockItem(ColourfulClocksBlocksImpl.EXPOSED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getWeatheringCopperValue(HandbellTypes.EXPOSED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WEATHERED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_HANDBELL,
            () -> new WeatheringCopperHandbellBlockItem(ColourfulClocksBlocksImpl.WEATHERED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getWeatheringCopperValue(HandbellTypes.WEATHERED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> OXIDIZED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.OXIDIZED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.OXIDIZED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WAXED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.WAXED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.WAXED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.WAXED_EXPOSED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.WAXED_EXPOSED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.WAXED_WEATHERED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.WAXED_WEATHERED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_HANDBELL = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.WAXED_OXIDIZED_COPPER_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getCopperValue(HandbellTypes.WAXED_WEATHERED_COPPER)), HandbellTypes.COPPER));
    public static final Supplier<Item> GOLD_HANDBELL = registerItemWithTab(ColourfulClocksItems.GOLD_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.GOLD_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getDefaultValue()), HandbellTypes.GOLD));
    public static final Supplier<Item> DIAMOND_HANDBELL = registerItemWithTab(ColourfulClocksItems.DIAMOND_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.DIAMOND_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getDefaultValue()), HandbellTypes.DIAMOND));
    public static final Supplier<Item> NETHERITE_HANDBELL = registerItemWithTab(ColourfulClocksItems.NETHERITE_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.NETHERITE_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getDefaultValue()), HandbellTypes.NETHERITE));
    public static final Supplier<Item> QUARTZ_HANDBELL = registerItemWithTab(ColourfulClocksItems.QUARTZ_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.QUARTZ_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getDefaultValue()), HandbellTypes.QUARTZ));
    public static final Supplier<Item> AMETHYST_HANDBELL = registerItemWithTab(ColourfulClocksItems.AMETHYST_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.AMETHYST_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getDefaultValue()), HandbellTypes.AMETHYST));
    public static final Supplier<Item> LAPIS_LAZULI_HANDBELL = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.LAPIS_LAZULI_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getDefaultValue()), HandbellTypes.LAPIS_LAZULI));
    public static final Supplier<Item> REDSTONE_HANDBELL = registerItemWithTab(ColourfulClocksItems.REDSTONE_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.REDSTONE_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getDefaultValue()), HandbellTypes.REDSTONE));
    public static final Supplier<Item> EMERALD_HANDBELL = registerItemWithTab(ColourfulClocksItems.EMERALD_HANDBELL,
            () -> new HandbellBlockItem(ColourfulClocksBlocksImpl.EMERALD_HANDBELL.get(), basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.HANDBELL_DATA, HandbellComponent.getDefaultValue()), HandbellTypes.EMERALD));

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
            () -> new PocketWatchItem(PocketWatchTypes.IRON, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.IRON))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.COPPER_POCKET_WATCH,
            () -> new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getWeatheredValue(PocketWatchTypes.COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH,
            () -> new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getWeatheredValue(PocketWatchTypes.EXPOSED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH,
            () -> new WeatheringPocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getWeatheredValue(PocketWatchTypes.WEATHERED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.OXIDIZED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.WAXED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.WAXED_EXPOSED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.WAXED_WEATHERED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.COPPER, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.WAXED_OXIDIZED_COPPER))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> GOLD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.GOLD_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.GOLD, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.GOLD))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> DIAMOND_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.DIAMOND_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.DIAMOND, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.DIAMOND))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> NETHERITE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.NETHERITE_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.NETHERITE, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.NETHERITE))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));

    public static final Supplier<Item> QUARTZ_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.QUARTZ_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.QUARTZ, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.QUARTZ))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> AMETHYST_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.AMETHYST_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.AMETHYST, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.AMETHYST))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> LAPIS_LAZULI_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.LAPIS_LAZULI, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.LAPIS_LAZULI))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> REDSTONE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.REDSTONE_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.REDSTONE, basicItem()
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH, PocketWatchComponent.getBaseValue(PocketWatchTypes.REDSTONE))
                    .component(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED, Boolean.FALSE)));
    public static final Supplier<Item> EMERALD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EMERALD_POCKET_WATCH,
            () -> new PocketWatchItem(PocketWatchTypes.EMERALD, basicItem()
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

    public static final Supplier<Item> IRON_PENDULUM = registerItemWithTab(ColourfulClocksItems.IRON_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.IRON))));
    public static final Supplier<Item> COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.COPPER_PENDULUM, () -> new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getWeatheringCopperValue(PendulumTypes.COPPER))
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM_WEATHERING, 0)));
    public static final Supplier<Item> EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_PENDULUM, () -> new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getWeatheringCopperValue(PendulumTypes.EXPOSED_COPPER))
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM_WEATHERING, 0)));
    public static final Supplier<Item> WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_PENDULUM, () -> new WeatheringPendulumItem(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getWeatheringCopperValue(PendulumTypes.WEATHERED_COPPER))
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM_WEATHERING, 0)));
    public static final Supplier<Item> OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.OXIDIZED_COPPER))));
    public static final Supplier<Item> WAXED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.WAXED_COPPER))));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.WAXED_EXPOSED_COPPER))));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.WAXED_WEATHERED_COPPER))));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.WAXED_OXIDIZED_COPPER))));
    public static final Supplier<Item> GOLD_PENDULUM = registerItemWithTab(ColourfulClocksItems.GOLD_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.GOLD))));
    public static final Supplier<Item> DIAMOND_PENDULUM = registerItemWithTab(ColourfulClocksItems.DIAMOND_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.DIAMOND))));
    public static final Supplier<Item> NETHERITE_PENDULUM = registerItemWithTab(ColourfulClocksItems.NETHERITE_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.NETHERITE))));

    public static final Supplier<Item> QUARTZ_PENDULUM = registerItemWithTab(ColourfulClocksItems.QUARTZ_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.QUARTZ))));
    public static final Supplier<Item> AMETHYST_PENDULUM = registerItemWithTab(ColourfulClocksItems.AMETHYST_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.AMETHYST))));
    public static final Supplier<Item> LAPIS_LAZULI_PENDULUM = registerItemWithTab(ColourfulClocksItems.LAPIS_LAZULI_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.LAPIS_LAZULI))));
    public static final Supplier<Item> REDSTONE_PENDULUM = registerItemWithTab(ColourfulClocksItems.REDSTONE_PENDULUM, () -> new Item(basicItem()
            .component(ColourfulClocksDataComponentTypesImpl.PENDULUM, PendulumComponent.getBaseValue(PendulumTypes.REDSTONE))));
    public static final Supplier<Item> EMERALD_PENDULUM = registerItemWithTab(ColourfulClocksItems.EMERALD_PENDULUM, () -> new Item(basicItem()
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
                    () -> new AlarmClockBlockItem(ColourfulClocksBlocksImpl.ALARM_CLOCK_VARIANTS.get(clockTypes).get(), basicItem()
                            .component(ColourfulClocksDataComponentTypesImpl.ALARM_CLOCK_DATA, AlarmClockComponent.getDefaultValue())));
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
