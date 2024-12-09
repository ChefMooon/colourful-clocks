package com.chefmooon.colourfulclocks.common.block.entity.fabric;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmTopBlockEntity;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class BornholmTopBlockEntityImpl extends BornholmTopBlockEntity {
    public BornholmTopBlockEntityImpl(BlockPos pos, BlockState blockState) {
        super(ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS, pos, blockState);
    }

    public static boolean isCopperClockHands(ItemStack itemStack) {
        return itemStack.is(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get())
                || itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get())
                || itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get());
    }

    public static Supplier<Item> getNextWeatheredCopperItem(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get())) {
            return ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH;
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get())) {
            return ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH;
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get())) {
            return ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH;
        } else {
            return ItemStack.EMPTY::getItem;
        }
    }
}
