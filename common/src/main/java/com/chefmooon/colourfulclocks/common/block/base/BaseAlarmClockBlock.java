package com.chefmooon.colourfulclocks.common.block.base;

import com.chefmooon.colourfulclocks.common.block.entity.base.BaseMantelClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.BornholmTopGlassTypeProperty;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class BaseAlarmClockBlock extends BaseEntityBlock {
    public static final MapCodec<BaseAlarmClockBlock> CODEC = simpleCodec(BaseAlarmClockBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty CAN_TICK = ColourfulClocksBlockStateProperties.CAN_TICK;
    public static final BooleanProperty TICKING = ColourfulClocksBlockStateProperties.TICKING;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BornholmTopGlassTypeProperty GLASS_TYPE = ColourfulClocksBlockStateProperties.BORNHOLM_TOP_GLASS_TYPE;
    public ClockTypes clockType;

    public static int FLAMMABILITY = 30;
    public static int FIRE_SPREAD = 60;

    public BaseAlarmClockBlock(Properties properties) {
        super(properties);
    }

    protected BaseAlarmClockBlock(ClockTypes clockType, Properties properties) {
        super(properties);
        this.clockType = clockType;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, Boolean.FALSE)
                .setValue(CAN_TICK, Boolean.FALSE)
                .setValue(TICKING, Boolean.FALSE));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState blockState = super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);

        if (!blockState.isAir()) {
            if (stateIn.getValue(WATERLOGGED)) {
                level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }
        }
        return blockState;
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, WATERLOGGED, CAN_TICK, TICKING, GLASS_TYPE);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!oldState.is(state.getBlock())) {
            this.checkPoweredState(level, pos, state);
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        this.checkPoweredState(level, pos, state);
    }

    protected void checkPoweredState(Level level, BlockPos pos, BlockState state) {
        boolean bl = !level.hasNeighborSignal(pos);
        if (state.getValue(CAN_TICK) && bl != state.getValue(TICKING)) {
            level.setBlock(pos, state.setValue(TICKING, bl), 2);
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BaseMantelClockBlockEntity baseMantelClockBlockEntity) {
                baseMantelClockBlockEntity.setTicking(bl);
            }
        }
    }
}
