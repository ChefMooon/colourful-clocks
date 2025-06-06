package com.chefmooon.colourfulclocks.common.block.entity.base;

import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BaseClockBlockEntity extends BlockEntity {
    public static final int WEATHERED_THRESHOLD = 6000; // 5 min to weather
    public BaseClockBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    protected static void tickSound(Level level, BlockPos blockPos) {
        if (level == null || level.isClientSide()) return;

        float timeOfDay = (level.getDayTime() + 0) % 24000;
        float segmentTime = timeOfDay % 750.0F;
        float stepLength = 750.0F / 16.0F;
        if (Math.abs(segmentTime % stepLength) < 1.0F) {
            level.playSound(null, blockPos, ColourfulClocksSounds.BLOCK_BORNHOLM_TICK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
}
