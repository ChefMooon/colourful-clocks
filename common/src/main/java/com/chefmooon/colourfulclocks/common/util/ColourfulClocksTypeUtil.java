package com.chefmooon.colourfulclocks.common.util;

import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.*;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.mojang.datafixers.util.Pair;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ColourfulClocksTypeUtil {

    public static BornholmDoorTypes getTypeFromItem(Item item) {
        for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
            if (bornholmDoorTypes.getItem() == item) {
                return bornholmDoorTypes;
            }
        }
        return BornholmDoorTypes.BASE;
    }

    public static BornholmTopGlassTypes getBornholmTopGlassTypeFromItem(Item item) {
        for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
            if (bornholmTopGlassTypes.getItem() == item) {
                return bornholmTopGlassTypes;
            }
        }
        return BornholmTopGlassTypes.GLASS;
    }

    public static Pair<Item, Supplier<SoundEvent>> getUnwaxedBell(HandbellComponent component) {
        if (component.getType() == HandbellTypes.WAXED_COPPER) {
            return Pair.of(BuiltInRegistries.ITEM.get(ColourfulClocksItems.COPPER_HANDBELL), ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (component.getType() == HandbellTypes.WAXED_EXPOSED_COPPER) {
            return Pair.of(BuiltInRegistries.ITEM.get(ColourfulClocksItems.EXPOSED_COPPER_HANDBELL), ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (component.getType() == HandbellTypes.WAXED_WEATHERED_COPPER) {
            return Pair.of(BuiltInRegistries.ITEM.get(ColourfulClocksItems.WEATHERED_COPPER_HANDBELL), ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (component.getType() == HandbellTypes.WAXED_OXIDIZED_COPPER) {
            return Pair.of(BuiltInRegistries.ITEM.get(ColourfulClocksItems.OXIDIZED_COPPER_HANDBELL), ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else {
            return Pair.of(ItemStack.EMPTY.getItem(), ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        }
    }

    public static Pair<Item, Supplier<SoundEvent>> getScrapedBell(HandbellComponent component) {
        if (component.getType() == HandbellTypes.EXPOSED_COPPER) {
            return Pair.of(BuiltInRegistries.ITEM.get(ColourfulClocksItems.COPPER_HANDBELL), ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        } else if (component.getType() == HandbellTypes.WEATHERED_COPPER) {
            return Pair.of(BuiltInRegistries.ITEM.get(ColourfulClocksItems.EXPOSED_COPPER_HANDBELL), ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        } else if (component.getType() == HandbellTypes.OXIDIZED_COPPER) {
            return Pair.of(BuiltInRegistries.ITEM.get(ColourfulClocksItems.WEATHERED_COPPER_HANDBELL), ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        } else {
            return Pair.of(ItemStack.EMPTY.getItem(), ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        }
    }

    public static Item getNextWeatheredCopperBell(HandbellComponent component) {
        if (component.getType() == HandbellTypes.COPPER) {
            return BuiltInRegistries.ITEM.get(ColourfulClocksItems.EXPOSED_COPPER_HANDBELL);
        } else if (component.getType() == HandbellTypes.EXPOSED_COPPER) {
            return BuiltInRegistries.ITEM.get(ColourfulClocksItems.WEATHERED_COPPER_HANDBELL);
        } else if (component.getType() == HandbellTypes.WEATHERED_COPPER) {
            return BuiltInRegistries.ITEM.get(ColourfulClocksItems.OXIDIZED_COPPER_HANDBELL);
        } else {
            return ItemStack.EMPTY.getItem();
        }
    }

    public static boolean bellCanWeather(HandbellComponent component) {
        return component.getType() == HandbellTypes.COPPER ||
               component.getType() == HandbellTypes.EXPOSED_COPPER ||
               component.getType() == HandbellTypes.WEATHERED_COPPER;
    }

    @ExpectPlatform
    public static Item getPocketWatchItemFromType(PocketWatchTypes pocketWatchTypes) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static PocketWatchTypes getPocketWatchTypeFromItem(Item item) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item getPendulumItemFromType(PendulumTypes pendulumType) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static PendulumTypes getPendulumTypeFromItem(Item item) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isCopperClockHands(ItemStack itemStack) {
        throw new AssertionError();
    }

    // TODO: rename to getNextWeatheredCopperPocketWatch
    @ExpectPlatform
    public static Supplier<Item> getNextWeatheredCopperItem(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Item> getWaxedClockHands(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Pair<Supplier<Item>, Supplier<SoundEvent>> getScrapedClockHands(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isCopperPendulum(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Item> getNextWeatheredCopperPendulum(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Item> getWaxedCopperPendulum(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Pair<Supplier<Item>, Supplier<SoundEvent>> getScrapedCopperPendulum(ItemStack itemStack) {
        throw new AssertionError();
    }

    public static @Nullable HandbellTypes getHandbellTypeFromItem(Item item) {
        for (HandbellTypes handbellTypes : HandbellTypes.values()) {
            Item handbell = BuiltInRegistries.ITEM.get(TextUtil.res(handbellTypes.getSerializedName() + "_handbell"));
            if (handbell == item) {
                return handbellTypes;
            }
        }
        return null;
    }
}
