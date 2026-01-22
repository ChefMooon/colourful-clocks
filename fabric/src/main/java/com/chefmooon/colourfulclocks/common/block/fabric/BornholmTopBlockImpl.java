package com.chefmooon.colourfulclocks.common.block.fabric;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.entity.fabric.BornholmTopBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlockEntitiesImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BornholmTopBlockImpl extends BornholmTopBlock {
    public BornholmTopBlockImpl(ClockTypes clockTypes, Properties properties) {
        super(clockTypes, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS.create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return createTickerHelper(blockEntity, ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS, BornholmTopBlockEntityImpl::weatherTick);
    }
}
