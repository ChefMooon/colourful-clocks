package com.chefmooon.colourfulclocks.common.block.fabric;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
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

public class BornholmMiddleBlockImpl extends BornholmMiddleBlock {
    public BornholmMiddleBlockImpl(WoodTypes woodTypes, BornholmDoorTypes bornholmDoorTypes, Properties properties) {
        super(woodTypes, bornholmDoorTypes, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS.get().create(pos, state);
    }

    public static Supplier<Block> getDoorType(WoodTypes woodTypes, BornholmDoorTypes bornholmDoorTypes) {
        return ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes));
    }
}
