package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;

public class BornholmBaseBlock extends Block implements SimpleWaterloggedBlock {
    public static final MapCodec<BornholmBaseBlock> CODEC = simpleCodec(BornholmBaseBlock::new);
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty ACTIVATED = ColourfulClocksBlockStateProperties.ACTIVATED;
    public WoodTypes woodType;  // todo - decide if used
    public int FLAMMABILITY = 30;
    public int FIRE_SPREAD = 60;

    private static HashMap<Direction, VoxelShape> SHAPES;

    @Override
    protected MapCodec<? extends BornholmBaseBlock> codec() {
        return CODEC;
    }

    public BornholmBaseBlock(Properties properties) {
        super(properties);
        SHAPES = buildShapes();
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.DOWN) // todo - can blocks placed NORTH be avoided?
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, Boolean.FALSE));
    }

    private static HashMap<Direction, VoxelShape> buildShapes() {
        HashMap<Direction, VoxelShape> result = new HashMap<>();
        for (Direction direction : Direction.values()) {
            result.put(direction, Shapes.or(Block.box(2, 2, 2, 14, 14, 14), baseShape(direction), extraShape(direction)));
        }

        return result;
    }

    private static VoxelShape baseShape(Direction direction) {
        return switch (direction) {
            case DOWN -> Block.box(1, 0, 1, 15, 2, 15);
            case UP -> Block.box(1, 14, 1, 15, 16, 15);
            case SOUTH -> Block.box(1, 1, 14, 15, 15, 16);
            case NORTH -> Block.box(1, 1, 0, 15, 15, 2);
            case EAST -> Block.box(14, 1, 1, 16, 15, 15);
            case WEST -> Block.box(0, 1, 1, 2, 15, 15);
        };
    }

    private static VoxelShape extraShape(Direction direction) {
        return switch (direction) {
            case DOWN -> Block.box(3, 14, 3, 13, 16, 13);
            case UP -> Block.box(3, 0, 3, 13, 2, 13);
            case SOUTH -> Block.box(3, 3, 0, 13, 13, 2);
            case NORTH -> Block.box(3, 3, 14, 13, 13, 16);
            case EAST -> Block.box(0, 3, 3, 2, 13, 13);
            case WEST -> Block.box(14, 3, 3, 16, 13, 13);
        };
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState()
                .setValue(FACING, context.getClickedFace().getOpposite())
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, ACTIVATED, WATERLOGGED);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(FACING));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

}
