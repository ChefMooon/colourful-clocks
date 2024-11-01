package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmTopBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.BornholmTopGlassTypeProperty;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.BornholmTypeUtil;
import com.mojang.serialization.MapCodec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BornholmTopBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<BornholmTopBlock> CODEC = simpleCodec(BornholmTopBlock::new);
    public static final BornholmTopGlassTypeProperty GLASS_TYPE = ColourfulClocksBlockStateProperties.BORNHOLM_TOP_GLASS_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVATED = ColourfulClocksBlockStateProperties.ACTIVATED;
    public BornholmTopGlassTypes glassType;
    public WoodTypes woodType;

    public int FLAMMABILITY = 30;
    public int FIRE_SPREAD = 60;

    private static final VoxelShape SHAPE  = Shapes.or(
            Block.box(2, 0, 2, 14, 2, 14),
            Block.box(2, 14, 2, 14, 16, 14),
            Block.box(3, 2, 3, 13, 14, 13)
    );

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    public BornholmTopBlock(Properties properties) {
        this(WoodTypes.OAK, BornholmTopGlassTypes.BASE, properties);
    }

    public BornholmTopBlock(WoodTypes woodTypes, BornholmTopGlassTypes bornholmTopGlassTypes, Properties properties) {
        super(properties);
        this.glassType = bornholmTopGlassTypes;
        this.woodType = woodTypes;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(GLASS_TYPE, BornholmTopGlassTypes.BASE)
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
                .setValue(ACTIVATED, isActivated(context.getLevel().getBlockState(context.getClickedPos().below())))
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState blockState = super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);

        if (!blockState.isAir()) {
            if (stateIn.getValue(WATERLOGGED)) {
                level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }
            return blockState.setValue(ACTIVATED, isActivated(level.getBlockState(currentPos.below())));
        }
        return blockState;
    }

    public boolean isActivated(BlockState blockState) {
        return blockState.getBlock() instanceof BornholmMiddleBlock && blockState.getValue(ACTIVATED);
    }


    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, GLASS_TYPE, ACTIVATED, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemInteractionResult result = ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof BornholmTopBlockEntity block) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (!mainHandItem.isEmpty()) {
                if (mainHandItem.is(ColourfulClocksTags.CLOCK_HAND) && !mainHandItem.is(block.getClockHandsItem().getItem())) {
                    block.setClockHandsItem(player.getAbilities().instabuild ? mainHandItem.copy() : mainHandItem);
                    level.playSound(player, pos, SoundEvents.COPPER_PLACE, SoundSource.BLOCKS, 1.0F, 0.6F);
                    return ItemInteractionResult.SUCCESS;
                }
                if (mainHandItem.is(ColourfulClocksTags.CLOCK_TOP_GLASS)) {
                    BornholmTopGlassTypes newBornholmTopGlassTypes = BornholmTypeUtil.getBornholmTopGlassTypeFromItem(mainHandItem.getItem());
                    Block newBlock = getGlassType(woodType, newBornholmTopGlassTypes).get();
                    ItemStack clockHandsTemp = block.removeItem(0, 1); // temporary
                    level.setBlockAndUpdate(pos, newBlock.defaultBlockState().setValue(FACING, state.getValue(FACING)).setValue(GLASS_TYPE, newBornholmTopGlassTypes).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
                    if (level.getBlockEntity(pos) instanceof BornholmTopBlockEntity newBlockEntity) { // temporary
                        newBlockEntity.setItem(0, clockHandsTemp);
                    }
                    level.playSound(player, pos, SoundEvents.GLASS_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);
                    return ItemInteractionResult.SUCCESS;
                }
            } else {
                if (player.isShiftKeyDown() && !block.getClockHandsItem().isEmpty()) {
                    if (player.isCreative()) {
                        block.removeItem(0, 1);
                    } else if (!player.getInventory().add(block.removeItem(0, 1))) {
                        Containers.dropContents(level, pos, block.getDroppableInventory());
                    }
                    level.playSound(player, pos, SoundEvents.COPPER_PLACE, SoundSource.BLOCKS, 1.0F, 0.8F);
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return result;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BornholmTopBlockEntity bornholmTopBlockEntity) {
                ItemStack item = bornholmTopBlockEntity.getClockHandsItem();
                if (!item.isEmpty()) {
                    Containers.dropContents(level, pos, bornholmTopBlockEntity.getDroppableInventory());
                }
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @ExpectPlatform
    public static Supplier<Block> getGlassType(WoodTypes woodTypes, BornholmTopGlassTypes bornholmTopGlassTypes) {
        throw new AssertionError();
    }

}
