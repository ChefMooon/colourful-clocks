package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.item.BornholmTopBlockItem;
import com.chefmooon.colourfulclocks.common.item.fabric.PocketWatchItemImpl;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems.basicItem;
import static com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems.noStack;

public class ColourfulClocksItemsImpl {

    public static final HashMap<WoodTypes, Supplier<Item>> BORNHOLM_BASE_VARIANTS = new HashMap<>();
    public static final HashMap<Map.Entry<WoodTypes, BornholmDoorTypes>, Supplier<Item>> BORNHOLM_MIDDLE_VARIANTS = new HashMap<>();
    public static final HashMap<Map.Entry<WoodTypes, BornholmTopGlassTypes>, Supplier<Item>> BORNHOLM_TOP_VARIANTS = new HashMap<>();

    public static final Supplier<Item> IRON_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.IRON_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.IRON, noStack()));
    public static final Supplier<Item> COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.COPPER_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> GOLD_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.GOLD_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.GOLD, noStack()));
    public static final Supplier<Item> DIAMOND_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.DIAMOND_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.DIAMOND, noStack()));
    public static final Supplier<Item> NETHERITE_POCKET_WATCH = registerItemWithTab(ColourfulClocksItems.NETHERITE_POCKET_WATCH,
            new PocketWatchItemImpl(PocketWatchTypes.NETHERITE, noStack()));

    public static final Supplier<Item> IRON_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.IRON_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.IRON, noStack()));
    public static final Supplier<Item> COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_EXPOSED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_WEATHERED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.COPPER, noStack()));
    public static final Supplier<Item> GOLD_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.GOLD_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.GOLD, noStack()));
    public static final Supplier<Item> DIAMOND_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.DIAMOND_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.DIAMOND, noStack()));
    public static final Supplier<Item> NETHERITE_POCKET_WATCH_IN_CLOCK = registerItem(ColourfulClocksItems.NETHERITE_POCKET_WATCH_IN_CLOCK,
            new PocketWatchItemImpl(PocketWatchTypes.NETHERITE, noStack()));

    public static final Supplier<Item> IRON_PENDULUM = registerItemWithTab(ColourfulClocksItems.IRON_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.EXPOSED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WEATHERED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.OXIDIZED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> WAXED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> WAXED_EXPOSED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_EXPOSED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> WAXED_WEATHERED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_WEATHERED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> WAXED_OXIDIZED_COPPER_PENDULUM = registerItemWithTab(ColourfulClocksItems.WAXED_OXIDIZED_COPPER_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> GOLD_PENDULUM = registerItemWithTab(ColourfulClocksItems.GOLD_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> DIAMOND_PENDULUM = registerItemWithTab(ColourfulClocksItems.DIAMOND_PENDULUM, new Item(basicItem()));
    public static final Supplier<Item> NETHERITE_PENDULUM = registerItemWithTab(ColourfulClocksItems.NETHERITE_PENDULUM, new Item(basicItem()));

//    public static final Supplier<Item> MOVEMENT_BASIC = registerItemWithTab(TemporalTimepiecesItems.MOVEMENT_BASIC, new Item(basicItem()));

//    public static final HashMap<WoodTypes, Supplier<Item>> BORNHOLM_BASE_VARIANTS = registerBaseItemVariantsAll(TemporalTimepiecesItems.BORNHOLM_BASE, basicItem());
//    public static final HashMap<Map.Entry<WoodTypes, BornholmDoorTypes>, Supplier<Item>> BORNHOLM_MIDDLE_VARIANTS = registerMiddleItemVariantsAll(TemporalTimepiecesItems.BORNHOLM_MIDDLE, basicItem());
//    public static final HashMap<Map.Entry<WoodTypes, BornholmTopGlassTypes>, Supplier<Item>> BORNHOLM_TOP_VARIANTS = registerTopItemVariantsAll(TemporalTimepiecesItems.BORNHOLM_TOP, basicItem());


    private static void registerBornholmItems() {
        for (WoodTypes woodTypes : WoodTypes.values()) {

            // Base
            Supplier<Item> baseItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_BASE.withSuffix(woodTypes.getSerializedName()),
                    new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(woodTypes).get(), basicItem()));
            BORNHOLM_BASE_VARIANTS.put(woodTypes, baseItem);

            // Middle
            for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
                Supplier<Item> middleItem;
                if (bornholmDoorTypes == BornholmDoorTypes.BASE) {
                    middleItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_MIDDLE.withSuffix(woodTypes.getSerializedName() + bornholmDoorTypes.getSerializedName()),
                            new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes)).get(), basicItem()));
                } else {
                    middleItem = registerItem(ColourfulClocksItems.BORNHOLM_MIDDLE.withSuffix(woodTypes.getSerializedName() + bornholmDoorTypes.getSerializedName()),
                            new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes)).get(), basicItem()));
                }
                BORNHOLM_MIDDLE_VARIANTS.put(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes), middleItem);
            }

            // Top
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                Supplier<Item> topItem;
                if (bornholmTopGlassTypes == BornholmTopGlassTypes.BASE) {
                    topItem = registerItemWithTab(ColourfulClocksItems.BORNHOLM_TOP.withSuffix(woodTypes.getSerializedName() + bornholmTopGlassTypes.getSerializedName()),
                            new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes)).get(), basicItem()));
                } else {
                    topItem = registerItem(ColourfulClocksItems.BORNHOLM_TOP.withSuffix(woodTypes.getSerializedName() + bornholmTopGlassTypes.getSerializedName()),
                            new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes)).get(), basicItem()));
                }
                BORNHOLM_TOP_VARIANTS.put(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes), topItem);
            }
        }
    }

    private static HashMap<WoodTypes, Supplier<Item>> registerBaseItemVariantsAll(ResourceLocation location, Item.Properties properties) {
        HashMap<WoodTypes, Supplier<Item>> hashMap = new HashMap<>();
        ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.forEach((woodTypes, supplier) -> {
            Supplier<Item> item;
            item = registerItemWithTab(location.withSuffix(woodTypes.getSerializedName()),
                    new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(woodTypes).get(), properties));
            hashMap.put(woodTypes, item);
        });
        return hashMap;
    }

    private static HashMap<Map.Entry<WoodTypes, BornholmDoorTypes>, Supplier<Item>> registerMiddleItemVariantsAll(ResourceLocation location, Item.Properties properties) {
        HashMap<Map.Entry<WoodTypes, BornholmDoorTypes>, Supplier<Item>> hashMap = new HashMap<>();
        ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.forEach((entry, supplier) -> {
            WoodTypes woodTypes = entry.getKey();
            BornholmDoorTypes bornholmDoorTypes = entry.getValue();
            Supplier<Item> item;
            if (bornholmDoorTypes == BornholmDoorTypes.BASE) {
                item = registerItemWithTab(location.withSuffix(woodTypes.getSerializedName() + bornholmDoorTypes.getSerializedName()),
                        new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes)).get(), properties));
            } else {
                item = registerItem(location.withSuffix(woodTypes.getSerializedName() + bornholmDoorTypes.getSerializedName()),
                        new BlockItem(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes)).get(), properties));
            }
            hashMap.put(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes), item);
        });
        return hashMap;
    }

    private static HashMap<Map.Entry<WoodTypes, BornholmTopGlassTypes>, Supplier<Item>> registerTopItemVariantsAll(ResourceLocation location, Item.Properties properties) {
        HashMap<Map.Entry<WoodTypes, BornholmTopGlassTypes>, Supplier<Item>> hashMap = new HashMap<>();
        ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.forEach((entry, supplier) -> {
            WoodTypes woodTypes = entry.getKey();
            BornholmTopGlassTypes bornholmTopGlassTypes = entry.getValue();
            Supplier<Item> item;
            if (bornholmTopGlassTypes == BornholmTopGlassTypes.BASE) {
                item = registerItemWithTab(location.withSuffix(woodTypes.getSerializedName() + bornholmTopGlassTypes.getSerializedName()),
                        new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes)).get(), properties));
            } else {
                item = registerItem(location.withSuffix(woodTypes.getSerializedName() + bornholmTopGlassTypes.getSerializedName()),
                        new BornholmTopBlockItem(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes)).get(), properties));
            }
            hashMap.put(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes), item);
        });
        return hashMap;
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
