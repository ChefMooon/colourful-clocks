package com.chefmooon.colourfulclocks.common.block.fabric;

import com.chefmooon.colourfulclocks.common.block.AlarmClockBlock;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class AlarmClockBlockImpl extends AlarmClockBlock {
    public AlarmClockBlockImpl(ClockTypes clockType, Properties properties) {
        super(clockType, properties);
    }

    public static boolean isLeftSide(Direction facing, Vec3 hit, BlockPos pos) {
        double localX = hit.x - pos.getX(); // 0..1 inside the block
        double localZ = hit.z - pos.getZ();

        return switch (facing) {
            case NORTH -> localX < 0.5;
            case SOUTH -> localX > 0.5;
            case WEST -> localZ > 0.5;
            case EAST -> localZ < 0.5;
            default -> false;
        };
    }

    public static boolean loaderParticleSide(boolean left) {
        return left;
    }
}
