package com.chefmooon.colourfulclocks.common.block.entity.neoforge;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmMiddleBlockEntity;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksItemsImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

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
}
