package com.chefmooon.colourfulclocks.common.block.neoforge;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlocksImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractMap;
import java.util.function.Supplier;

public class BornholmTopBlockImpl extends BornholmTopBlock {
    public BornholmTopBlockImpl(WoodTypes woodTypes, BornholmTopGlassTypes bornholmTopGlassTypes, Properties properties) {
        super(woodTypes, bornholmTopGlassTypes, properties);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return FLAMMABILITY;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return FIRE_SPREAD;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS.get().create(pos, state);
    }

    public static Supplier<Block> getGlassType(WoodTypes woodTypes, BornholmTopGlassTypes bornholmTopGlassTypes) {
        return ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes));
    }
}
