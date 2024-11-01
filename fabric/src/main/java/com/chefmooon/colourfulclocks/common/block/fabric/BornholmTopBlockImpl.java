package com.chefmooon.colourfulclocks.common.block.fabric;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import net.minecraft.core.BlockPos;
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
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
//        return null;
        return ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS.get().create(pos, state);
    }

    public static Supplier<Block> getGlassType(WoodTypes woodTypes, BornholmTopGlassTypes bornholmTopGlassTypes) {
        return ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes));
    }
}
