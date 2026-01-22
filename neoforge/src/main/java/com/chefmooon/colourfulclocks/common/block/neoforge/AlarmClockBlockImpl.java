package com.chefmooon.colourfulclocks.common.block.neoforge;

import com.chefmooon.colourfulclocks.common.block.AlarmClockBlock;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class AlarmClockBlockImpl extends AlarmClockBlock {
    public AlarmClockBlockImpl(ClockTypes clockType, Properties properties) {
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

    public static boolean isLeftSide(Direction facing, Vec3 hit, BlockPos pos) {
        double localX = hit.x - pos.getX(); // 0..1 inside the block
        double localZ = hit.z - pos.getZ();

        return switch (facing) {
            case NORTH -> localX > 0.5;
            case SOUTH -> localX < 0.5;
            case WEST -> localZ < 0.5;
            case EAST -> localZ > 0.5;
            default -> false;
        };
    }

    public static boolean loaderParticleSide(boolean left) {
        return !left;
    }
}
