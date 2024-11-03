package com.chefmooon.colourfulclocks.common.block.entity.fabric;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmMiddleBlockEntity;
import com.chefmooon.colourfulclocks.common.core.PendulumTypes;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class BornholmMiddleBlockEntityImpl extends BornholmMiddleBlockEntity {
    public BornholmMiddleBlockEntityImpl(BlockPos pos, BlockState blockState) {
        super(ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS.get(), pos, blockState);
    }

    public static int getRangeFromClockHandsItem(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get())) {
            return PocketWatchTypes.IRON.getEffectRange();
        } else if (itemStack.is(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get())) {
            return PocketWatchTypes.COPPER.getEffectRange();
        } else if (itemStack.is(ColourfulClocksItemsImpl.GOLD_POCKET_WATCH.get())) {
            return PocketWatchTypes.GOLD.getEffectRange();
        } else if (itemStack.is(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get())) {
            return PocketWatchTypes.DIAMOND.getEffectRange();
        } else if (itemStack.is(ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get())) {
            return PocketWatchTypes.NETHERITE.getEffectRange();
        } else {
            return baseEffectRange;
        }
    }

    public static boolean isCopperPendulum(ItemStack itemStack) {
        return itemStack.is(ColourfulClocksItemsImpl.COPPER_PENDULUM.get())
                || itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get())
                || itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get());
    }

    public static Supplier<Item> getNextWeatheredCopperItem(ItemStack itemStack) {
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

    public static float getClockHandPitchModifier(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.IRON_PENDULUM.get())) {
            return PendulumTypes.IRON.getPitchModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.COPPER_PENDULUM.get()) || itemStack.is(ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get())) {
            return PendulumTypes.COPPER.getPitchModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get()) || itemStack.is(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get())) {
            return PendulumTypes.EXPOSED_COPPER.getPitchModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get()) || itemStack.is(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get())) {
            return PendulumTypes.WEATHERED_COPPER.getPitchModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get()) || itemStack.is(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get())) {
            return PendulumTypes.OXIDIZED_COPPER.getPitchModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.GOLD_PENDULUM.get())) {
            return PendulumTypes.GOLD.getPitchModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get())) {
            return PendulumTypes.DIAMOND.getPitchModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get())) {
            return PendulumTypes.NETHERITE.getPitchModifier();
        } else {
            return 1.0F;
        }
    }
}
