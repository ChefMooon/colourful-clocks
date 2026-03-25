package com.chefmooon.colourfulclocks.common.block.base;

import com.chefmooon.colourfulclocks.common.block.entity.base.BaseWallClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.PocketWatchComponent;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.data.types.WallClockType;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksAdvancements;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.CopperWeatheringUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BaseWallClockBlock extends BaseEntityBlock {
    public static final MapCodec<BaseWallClockBlock> CODEC = simpleCodec(BaseWallClockBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty CAN_TICK = ColourfulClocksBlockStateProperties.CAN_TICK;
    public static final BooleanProperty TICKING = ColourfulClocksBlockStateProperties.TICKING;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVATED = ColourfulClocksBlockStateProperties.ACTIVATED;
    public ClockTypes clockType;

    public static int FLAMMABILITY = 30;
    public static int FIRE_SPREAD = 60;
    public BaseWallClockBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    protected BaseWallClockBlock(ClockTypes clockType, BlockBehaviour.Properties properties) {
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
            if (blockEntity instanceof BaseWallClockBlockEntity baseWallClockBlockEntity) {
                baseWallClockBlockEntity.setTicking(bl);
            }
        }
    }

    protected ItemInteractionResult removePocketWatch(Level level, BlockPos pos, Player player, BaseWallClockBlockEntity baseWallClockBlockEntity) {
        if (baseWallClockBlockEntity.getData().pocketWatch().isPresent() && baseWallClockBlockEntity.getData().pocketWatch().get().getType().getId() != 0) {
            ItemStack oldPocketWatchItemStack = BuiltInRegistries.ITEM.get(TextUtil.res(baseWallClockBlockEntity.getData().pocketWatch().get().type().getSerializedName() + "_pocket_watch")).getDefaultInstance();
            PocketWatchComponent component = baseWallClockBlockEntity.removePocketWatch();
            oldPocketWatchItemStack.set(ColourfulClocksDataComponentTypes.getPocketWatchData(), component);
            if (!player.getAbilities().instabuild && !player.getInventory().add(oldPocketWatchItemStack)) {
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), oldPocketWatchItemStack);
            }
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
            syncBlockEntity(level, pos);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setPocketWatchType(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity) {
        if (blockEntity instanceof BaseWallClockBlockEntity baseWallClockBlockEntity) {
            PocketWatchTypes pocketWatchType = itemStack.get(ColourfulClocksDataComponentTypes.getPocketWatchData()).getType();
            if (baseWallClockBlockEntity.getData().pocketWatch().isPresent() && pocketWatchType != baseWallClockBlockEntity.getData().pocketWatch().get().getType()) {
                if (baseWallClockBlockEntity.getData().getPocketWatch().get().getType().getId() != 0 && !player.getAbilities().instabuild) {
                    ItemStack oldPocketWatchItemStack = BuiltInRegistries.ITEM.get(TextUtil.res(baseWallClockBlockEntity.getData().pocketWatch().get().type().getSerializedName() + "_pocket_watch")).getDefaultInstance();
                    PocketWatchComponent component = baseWallClockBlockEntity.removePocketWatch();
                    oldPocketWatchItemStack.set(ColourfulClocksDataComponentTypes.getPocketWatchData(), component);
                    if (!player.getInventory().add(oldPocketWatchItemStack)) {
                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), oldPocketWatchItemStack);
                    }
                }
                baseWallClockBlockEntity.setPocketWatch(player.getAbilities().instabuild ? itemStack.copy() : itemStack.split(1));
                level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.6F);
                syncBlockEntity(level, pos);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.INSERT_POCKET_WATCH_TRIGGER.get().trigger(serverPlayer);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setWaxedState(BlockState state, Level level, BlockPos controllerPos, Player player, ItemStack itemStack, BlockEntity blockEntity, boolean tryWax) {
        if (blockEntity instanceof BaseWallClockBlockEntity baseWallClockBlockEntity) {
            Direction facing = state.getValue(FACING);
            if (tryWax) {
                ItemStack waxedClockHands = ColourfulClocksTypeUtil.getWaxedPocketWatch(baseWallClockBlockEntity.getData().getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()));
                if (!waxedClockHands.isEmpty()) {
                    baseWallClockBlockEntity.setPocketWatch(waxedClockHands);
                    syncBlockEntity(level, controllerPos);
                    level.playSound(player, controllerPos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                    CopperWeatheringUtil.spawnPocketWatchUpdateParticles(level, controllerPos, facing, ParticleTypes.WAX_ON, getClockParticleType(baseWallClockBlockEntity.getData().getType()));
                    if (!player.getAbilities().instabuild) itemStack.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            } else {
                Pair<Item, Supplier<SoundEvent>> unwaxedClockHandInfo = ColourfulClocksTypeUtil.getUnwaxedPocketWatch(baseWallClockBlockEntity.getData().getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()));
                ItemStack unwaxedClockHands = new ItemStack(unwaxedClockHandInfo.getFirst());
                if (!unwaxedClockHands.isEmpty()) {
                    baseWallClockBlockEntity.setPocketWatch(unwaxedClockHands);
                    syncBlockEntity(level, controllerPos);
                    level.playSound(player, controllerPos, unwaxedClockHandInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                    CopperWeatheringUtil.spawnPocketWatchUpdateParticles(level, controllerPos, facing, ParticleTypes.WAX_OFF, getClockParticleType(baseWallClockBlockEntity.getData().getType()));
                    if (!player.getAbilities().instabuild)
                        itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }

                Pair<Item, Supplier<SoundEvent>> scrapedClockHandInfo = ColourfulClocksTypeUtil.getScrapedPocketWatch(baseWallClockBlockEntity.getData().getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()));
                ItemStack scrapedClockHands = new ItemStack(scrapedClockHandInfo.getFirst());
                if (!scrapedClockHands.isEmpty()) {
                    baseWallClockBlockEntity.setPocketWatch(scrapedClockHands);
                    syncBlockEntity(level, controllerPos);
                    level.playSound(player, controllerPos, scrapedClockHandInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                    CopperWeatheringUtil.spawnPocketWatchUpdateParticles(level, controllerPos, facing, ParticleTypes.SCRAPE, getClockParticleType(baseWallClockBlockEntity.getData().getType()));
                    if (!player.getAbilities().instabuild) itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private CopperWeatheringUtil.ClockParticleType getClockParticleType(WallClockType wallClockType) {
        return switch (wallClockType) {
            case SMALL -> CopperWeatheringUtil.ClockParticleType.WALL_CLOCK_SMALL;
            case MEDIUM -> CopperWeatheringUtil.ClockParticleType.WALL_CLOCK_MEDIUM;
            case LARGE -> CopperWeatheringUtil.ClockParticleType.WALL_CLOCK_LARGE;
        };
    }

    protected ItemInteractionResult setTicking(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity, boolean tryTicking) {
        if (blockEntity instanceof BaseWallClockBlockEntity baseWallClockBlockEntity) {
            if (baseWallClockBlockEntity.getData().isTicking().isPresent()) {
                if (tryTicking) {
                    if (!baseWallClockBlockEntity.getData().isTicking().get()) {
                        baseWallClockBlockEntity.setTicking(true);
                        level.setBlock(pos, level.getBlockState(pos).setValue(CAN_TICK, true).setValue(TICKING, true), 3);
                        this.checkPoweredState(level, pos, level.getBlockState(pos));
                        syncBlockEntity(level, pos);
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_ENABLE_TICKING.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        if (!player.getAbilities().instabuild) itemStack.shrink(1);
                        if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.ENABLE_TICKING_TRIGGER.get().trigger(serverPlayer);

                        return ItemInteractionResult.SUCCESS;
                    }
                } else  {
                    if (baseWallClockBlockEntity.getData().isTicking().get()) {
                        baseWallClockBlockEntity.setTicking(false);
                        level.setBlock(pos, level.getBlockState(pos).setValue(CAN_TICK, false).setValue(TICKING, false), 3);
                        syncBlockEntity(level, pos);
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

    private static void syncBlockEntity(Level level, BlockPos pos) {
        if (!level.isClientSide()) {
            BlockState state = level.getBlockState(pos);
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }
}
