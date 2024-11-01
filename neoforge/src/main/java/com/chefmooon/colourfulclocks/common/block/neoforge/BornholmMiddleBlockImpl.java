package com.chefmooon.colourfulclocks.common.block.neoforge;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.block.entity.neoforge.BornholmMiddleBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlocksImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractMap;
import java.util.function.Supplier;

public class BornholmMiddleBlockImpl extends BornholmMiddleBlock {
    public BornholmMiddleBlockImpl(WoodTypes woodTypes, BornholmDoorTypes bornholmDoorTypes, Properties properties) {
        super(woodTypes, bornholmDoorTypes, properties);
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
        return ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS.get().create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return createTickerHelper(blockEntity, ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS.get(), BornholmMiddleBlockEntityImpl::temporalTimeTick);
    }

    public static Supplier<Block> getDoorType(WoodTypes woodTypes, BornholmDoorTypes bornholmDoorTypes) {
        return ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes));
    }
}
