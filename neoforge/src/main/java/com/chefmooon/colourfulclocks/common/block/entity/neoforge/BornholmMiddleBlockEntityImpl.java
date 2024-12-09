package com.chefmooon.colourfulclocks.common.block.entity.neoforge;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmMiddleBlockEntity;
import com.chefmooon.colourfulclocks.common.core.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksItemsImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class BornholmMiddleBlockEntityImpl extends BornholmMiddleBlockEntity {
    public BornholmMiddleBlockEntityImpl(BlockPos pos, BlockState blockState) {
        super(ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS.get(), pos, blockState);
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
}
