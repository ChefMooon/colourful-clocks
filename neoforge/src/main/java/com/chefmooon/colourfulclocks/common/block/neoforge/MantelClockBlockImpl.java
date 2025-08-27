package com.chefmooon.colourfulclocks.common.block.neoforge;

import com.chefmooon.colourfulclocks.common.block.MantelClockBlock;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public class MantelClockBlockImpl extends MantelClockBlock {
    public MantelClockBlockImpl(ClockTypes clockType, Properties properties) {
        super(clockType, properties);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return clockType.isWooden() ? FLAMMABILITY : 0;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return clockType.isWooden() ? FIRE_SPREAD : 0;
    }
}
