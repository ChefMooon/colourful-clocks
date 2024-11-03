package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.item.BornholmTopBlockItem;
import com.chefmooon.colourfulclocks.common.item.neoforge.PocketWatchItemImpl;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.function.Supplier;

import static com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems.basicItem;
import static com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems.noStack;

public class ColourfulClocksItemsImpl {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ColourfulClocks.MOD_ID);
    public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();


    public static final HashMap<WoodTypes, Supplier<Item>> BORNHOLM_BASE_VARIANTS = new HashMap<>();
    public static final HashMap<Map.Entry<WoodTypes, BornholmDoorTypes>, Supplier<Item>> BORNHOLM_MIDDLE_VARIANTS = new HashMap<>();
    public static final HashMap<Map.Entry<WoodTypes, BornholmTopGlassTypes>, Supplier<Item>> BORNHOLM_TOP_VARIANTS = new HashMap<>();


    public static final Supplier<Item> IRON_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.IRON_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.IRON, noStack()));
    public static final Supplier<Item> COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.COPPER_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> GOLD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.GOLD_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.GOLD, noStack()));
    public static final Supplier<Item> DIAMOND_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.DIAMOND_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.DIAMOND, noStack()));
    public static final Supplier<Item> NETHERITE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.NETHERITE_POCKET_WATCH,
            () -> new PocketWatchItemImpl(PocketWatchTypes.NETHERITE, noStack()));

    public static final Supplier<Item> IRON_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.IRON_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.IRON, noStack()));
    public static final Supplier<Item> COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.COPPER_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> GOLD_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.GOLD_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.GOLD, noStack()));
    public static final Supplier<Item> DIAMOND_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.DIAMOND_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.DIAMOND, noStack()));
    public static final Supplier<Item> NETHERITE_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.NETHERITE_POCKET_WATCH_IN_CLOCK,
            () -> new PocketWatchItemImpl(PocketWatchTypes.NETHERITE, noStack()));

    public static final Supplier<Item> IRON_PENDULUM = registerItemWithTab(ColourfulClocksItems.IRON_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> WAXED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> GOLD_PENDULUM = registerItemWithTab(ColourfulClocksItems.GOLD_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> DIAMOND_PENDULUM = registerItemWithTab(ColourfulClocksItems.DIAMOND_PENDULUM, () -> new Item(basicItem()));
    public static final Supplier<Item> NETHERITE_PENDULUM = registerItemWithTab(ColourfulClocksItems.NETHERITE_PENDULUM, () -> new Item(basicItem()));

    private static void registerBornholmItems() {
        for (WoodTypes woodTypes : WoodTypes.values()) {
            Supplier<Item> baseItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_BASE.withSuffix(woodTypes.getSerializedName()),
                    () -> new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(woodTypes).get(), basicItem()));
            BORNHOLM_BASE_VARIANTS.put(woodTypes, baseItem);

            for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
                Supplier<Item> middleItem;
                if (bornholmDoorTypes == BornholmDoorTypes.BASE) {
                    middleItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_MIDDLE.withSuffix(woodTypes.getSerializedName() + bornholmDoorTypes.getSerializedName()),
                            () -> new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes)).get(), basicItem()));
                } else {
                    middleItem = registerItem(ColourfulClocksItems.BORNHOLM_MIDDLE.withSuffix(woodTypes.getSerializedName() + bornholmDoorTypes.getSerializedName()),
                            () -> new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes)).get(), basicItem()));
                }
                BORNHOLM_MIDDLE_VARIANTS.put(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes), middleItem);
            }

            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                Supplier<Item> topItem;
                if (bornholmTopGlassTypes == BornholmTopGlassTypes.BASE) {
                    topItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_TOP.withSuffix(woodTypes.getSerializedName() + bornholmTopGlassTypes.getSerializedName()),
                            () -> new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes)).get(), basicItem()));
                } else {
                    topItem = registerItem(ColourfulClocksItems.BORNHOLM_TOP.withSuffix(woodTypes.getSerializedName() + bornholmTopGlassTypes.getSerializedName()),
                            () -> new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes)).get(), basicItem()));
                }
                BORNHOLM_TOP_VARIANTS.put(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes), topItem);
            }
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
        registerBornholmItems();
        ITEMS.register(eventBus);
    }
}
