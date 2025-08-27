package com.chefmooon.colourfulclocks.common.block.base;

import com.chefmooon.colourfulclocks.common.block.entity.base.BaseDataClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksAdvancements;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

import java.util.function.Supplier;

public class BaseDataClockBlock extends BaseEntityBlock {
    public static final MapCodec<BaseDataClockBlock> CODEC = simpleCodec(BaseDataClockBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty CAN_TICK = ColourfulClocksBlockStateProperties.CAN_TICK;
    public static final BooleanProperty TICKING = ColourfulClocksBlockStateProperties.TICKING;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVATED = ColourfulClocksBlockStateProperties.ACTIVATED;
    public ClockTypes clockType;

    public static int FLAMMABILITY = 30;
    public static int FIRE_SPREAD = 60;
    public BaseDataClockBlock(Properties properties) {
        super(properties);
    }

    protected BaseDataClockBlock(ClockTypes clockType, Properties properties) {
        super(properties);
        this.clockType = clockType;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(ACTIVATED, Boolean.TRUE)
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
        builder.add(FACING, ACTIVATED, WATERLOGGED, CAN_TICK, TICKING);
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
            if (blockEntity instanceof BaseDataClockBlockEntity baseDataClockBlockEntity) {
                baseDataClockBlockEntity.setTicking(bl);
                level.blockEntityChanged(pos);
            }
        }
    }

    protected ItemInteractionResult setPendulumType(Level level, BlockPos pos, Player player, ItemStack itemStack, BaseDataClockBlockEntity baseDataClockBlockEntity) {
        if (itemStack.is(ColourfulClocksTags.CLOCK_PENDULUM)) {
            PendulumTypes pendulumType = ColourfulClocksTypeUtil.getPendulumTypeFromItem(itemStack.getItem());
            if (pendulumType != baseDataClockBlockEntity.getData().pendulumType().orElse(PendulumTypes.EMPTY)) {
                if (baseDataClockBlockEntity.getData().pendulumType().get().getId()!= 0 && !player.getAbilities().instabuild) {
                    if (!player.getInventory().add(baseDataClockBlockEntity.removePendulumType())) {
                        Containers.dropContents(level, pos, baseDataClockBlockEntity.getDroppableInventory());
                    }
                }
                ItemStack pendulumItem = player.getAbilities().instabuild ? itemStack.copy() : itemStack.split(1);
                baseDataClockBlockEntity.setPendulumType(pendulumItem);
                level.blockEntityChanged(pos);
                level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_PENDULUM.get(), SoundSource.BLOCKS, 0.8F, 0.5F);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.INSERT_PENDULUM_TRIGGER.get().trigger(serverPlayer);

                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult removePocketWatch(Level level, BlockPos pos, Player player, BaseDataClockBlockEntity baseDataClockBlockEntity) {
        if (baseDataClockBlockEntity.getData().pocketWatchType().isPresent() && baseDataClockBlockEntity.getData().pocketWatchType().get().getId() != 0) {
            if (player.isCreative()) {
                baseDataClockBlockEntity.removePocketWatchType();
            } else if (!player.getInventory().add(baseDataClockBlockEntity.removePocketWatchType())) {
                Containers.dropContents(level, pos, baseDataClockBlockEntity.getDroppableInventory());
            }
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
            level.updateNeighborsAt(pos, this);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult removePendulum(Level level, BlockPos pos, Player player, BaseDataClockBlockEntity baseDataClockBlockEntity) {
        if (baseDataClockBlockEntity.getData().pendulumType().isPresent() && baseDataClockBlockEntity.getData().pendulumType().get().getId() != 0) {
            if (player.isCreative()) {
                baseDataClockBlockEntity.removePendulumType();
            } else if (!player.getInventory().add(baseDataClockBlockEntity.removePendulumType())) {
                Containers.dropContents(level, pos, baseDataClockBlockEntity.getDroppableInventory());
            }
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_PENDULUM.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
            level.updateNeighborsAt(pos, this);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setPendulumWaxedState(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity, boolean tryWax) {
        if (blockEntity instanceof BaseDataClockBlockEntity baseDataClockBlockEntity) {
            if (tryWax) {
                ItemStack waxedPendulum = ColourfulClocksTypeUtil.getWaxedCopperPendulum(ColourfulClocksTypeUtil.getPendulumItemFromType(baseDataClockBlockEntity.getData().getPendulumType().orElse(PendulumTypes.EMPTY)).getDefaultInstance()).get().getDefaultInstance();
                if (!waxedPendulum.isEmpty()) {
                    baseDataClockBlockEntity.setPendulumType(waxedPendulum);
                    level.blockEntityChanged(pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                    if (!player.getAbilities().instabuild) itemStack.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            } else {
                Pair<Supplier<Item>, Supplier<SoundEvent>> pendulumInfo = ColourfulClocksTypeUtil.getScrapedCopperPendulum(ColourfulClocksTypeUtil.getPendulumItemFromType(baseDataClockBlockEntity.getData().getPendulumType().orElse(PendulumTypes.EMPTY)).getDefaultInstance());
                ItemStack scrapedPendulum = new ItemStack(pendulumInfo.getFirst().get());
                if (!scrapedPendulum.isEmpty()) {
                    baseDataClockBlockEntity.setPendulumType(scrapedPendulum);
                    level.blockEntityChanged(pos);
                    level.playSound(player, pos, pendulumInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                    if (!player.getAbilities().instabuild) itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setPocketWatchType(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity) {
        if (blockEntity instanceof BaseDataClockBlockEntity baseDataClockBlockEntity) {
            PocketWatchTypes pocketWatchType = ColourfulClocksTypeUtil.getPocketWatchTypeFromItem(itemStack.getItem());
            if (baseDataClockBlockEntity.getData().pocketWatchType().isPresent() && pocketWatchType != baseDataClockBlockEntity.getData().pocketWatchType().get()) {
                if (baseDataClockBlockEntity.getData().getPocketWatchType().get().getId() != 0 && !player.getAbilities().instabuild) {
                    if (!player.getInventory().add(baseDataClockBlockEntity.removePocketWatchType())) {
                        Containers.dropContents(level, pos, baseDataClockBlockEntity.getDroppableInventory());
                    }
                }
                baseDataClockBlockEntity.setPocketWatchType(player.getAbilities().instabuild ? itemStack.copy() : itemStack.split(1));
                level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.6F);
                level.updateNeighborsAt(pos, this);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.INSERT_POCKET_WATCH_TRIGGER.get().trigger(serverPlayer);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setWaxedState(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity, boolean tryWax) {
        if (blockEntity instanceof BaseDataClockBlockEntity baseDataClockBlockEntity) {
            if (tryWax) {
                ItemStack waxedClockHands = ColourfulClocksTypeUtil.getWaxedClockHands(ColourfulClocksTypeUtil.getPocketWatchItemFromType(baseDataClockBlockEntity.getData().getPocketWatchType().orElse(PocketWatchTypes.EMPTY)).getDefaultInstance()).get().getDefaultInstance();
                if (!waxedClockHands.isEmpty()) {
                    baseDataClockBlockEntity.setPocketWatchType(waxedClockHands);
                    level.blockEntityChanged(pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                    if (!player.getAbilities().instabuild) itemStack.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            } else {
                Pair<Supplier<Item>, Supplier<SoundEvent>> clockHandInfo = ColourfulClocksTypeUtil.getScrapedClockHands(ColourfulClocksTypeUtil.getPocketWatchItemFromType(baseDataClockBlockEntity.getData().getPocketWatchType().orElse(PocketWatchTypes.EMPTY)).getDefaultInstance());
                ItemStack scrapedClockHands = new ItemStack(clockHandInfo.getFirst().get());
                if (!scrapedClockHands.isEmpty()) {
                    baseDataClockBlockEntity.setPocketWatchType(scrapedClockHands);
                    level.blockEntityChanged(pos);
                    level.playSound(player, pos, clockHandInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                    if (!player.getAbilities().instabuild) itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setTicking(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity, boolean tryTicking) {
        if (blockEntity instanceof BaseDataClockBlockEntity baseDataClockBlockEntity) {
            if (baseDataClockBlockEntity.getData().getTicking().isPresent()) {
                if (tryTicking) {
                    if (!baseDataClockBlockEntity.getData().getTicking().get()) {
                        baseDataClockBlockEntity.setTicking(true);
                        level.setBlock(pos, level.getBlockState(pos).setValue(CAN_TICK, true).setValue(TICKING, true), 3);
                        this.checkPoweredState(level, pos, level.getBlockState(pos));
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_ENABLE_TICKING.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        if (!player.getAbilities().instabuild) itemStack.shrink(1);
                        if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.ENABLE_TICKING_TRIGGER.get().trigger(serverPlayer);

                        return ItemInteractionResult.SUCCESS;
                    }
                } else  {
                    if (baseDataClockBlockEntity.getData().getTicking().get()) {
                        baseDataClockBlockEntity.setTicking(false);
                        level.setBlock(pos, level.getBlockState(pos).setValue(CAN_TICK, false).setValue(TICKING, false), 3);
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_DISABLE_TICKING.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        if (!player.getAbilities().instabuild) {
                            itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                            if (!player.getInventory().add(Items.REDSTONE.getDefaultInstance())) {
                                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), Items.REDSTONE.getDefaultInstance());
                            }
                        }
                        if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.DISABLE_TICKING_TRIGGER.get().trigger(serverPlayer);

                        return ItemInteractionResult.SUCCESS;
                    }
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
