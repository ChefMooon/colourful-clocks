package com.chefmooon.colourfulclocks.common.block.entity.fabric;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmTopBlockEntity;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlockEntitiesImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BornholmTopBlockEntityImpl extends BornholmTopBlockEntity {
    public BornholmTopBlockEntityImpl(BlockPos pos, BlockState blockState) {
        super(ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS.get(), pos, blockState);
    }
}
