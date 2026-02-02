package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.entity.base.BaseWallClockBlockEntity;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.BlockState;

public class WallClockBlockEntity extends BaseWallClockBlockEntity {
    public WallClockBlockEntity(BlockPos pos, BlockState blockState) {
        super(BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.WALL_CLOCK), pos, blockState);
    }
}
