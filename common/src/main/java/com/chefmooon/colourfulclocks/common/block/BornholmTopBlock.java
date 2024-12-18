package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmTopBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.BornholmTopGlassTypeProperty;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.BornholmTypeUtil;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
        this(WoodTypes.OAK, properties);
    }

    public BornholmTopBlock(WoodTypes woodTypes, Properties properties) {
        super(properties);
        this.woodType = woodTypes;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(GLASS_TYPE, BornholmTopGlassTypes.GLASS)
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
                    if (!block.getClockHandsItem().isEmpty() && !player.getAbilities().instabuild) {
                        if (!player.getInventory().add(block.removeItem(0, 1))) {
                            Containers.dropContents(level, pos, block.getDroppableInventory());
                        }
                    }
                    block.setClockHandsItem(player.getAbilities().instabuild ? mainHandItem.copy() : mainHandItem);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.6F);
                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_TOP_GLASS)) {
                    if (mainHandItem.is(state.getValue(GLASS_TYPE).getItem())) return ItemInteractionResult.CONSUME;
                    BornholmTopGlassTypes newBornholmTopGlassTypes = BornholmTypeUtil.getBornholmTopGlassTypeFromItem(mainHandItem.getItem());
                    level.setBlockAndUpdate(pos, state.setValue(GLASS_TYPE, newBornholmTopGlassTypes));
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHANGE_GLASS.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
                    if (!player.getAbilities().instabuild) mainHandItem.shrink(1);

                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(Items.HONEYCOMB)) {
                    ItemStack waxedClockHands = new ItemStack(getWaxedClockHands(block.getClockHandsItem()).get());
                    if (!waxedClockHands.isEmpty()) {
                        block.setClockHandsItem(waxedClockHands);
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                        if (!player.getAbilities().instabuild) mainHandItem.shrink(1);

                        return ItemInteractionResult.SUCCESS;
                    }
                } else if (mainHandItem.is(ItemTags.AXES)) {
                    Pair<Supplier<Item>, Supplier<SoundEvent>> clockHandInfo = getScrapedClockHands(block.getClockHandsItem());
                    ItemStack scrapedClockHands = new ItemStack(clockHandInfo.getFirst().get());
                    if (!scrapedClockHands.isEmpty()) {
                        block.setClockHandsItem(scrapedClockHands);
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, clockHandInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                        if (!player.getAbilities().instabuild) mainHandItem.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

                        return ItemInteractionResult.SUCCESS;
                    }
                }
            } else {
                if (player.isShiftKeyDown() && !block.getClockHandsItem().isEmpty()) {
                    if (player.isCreative()) {
                        block.removeItem(0, 1);
                    } else if (!player.getInventory().add(block.removeItem(0, 1))) {
                        Containers.dropContents(level, pos, block.getDroppableInventory());
                    }
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.8F);

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
    public static Supplier<Item> getWaxedClockHands(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Pair<Supplier<Item>, Supplier<SoundEvent>> getScrapedClockHands(ItemStack itemStack) {
        throw new AssertionError();
    }
}
