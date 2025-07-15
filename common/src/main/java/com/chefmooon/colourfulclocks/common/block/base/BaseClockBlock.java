package com.chefmooon.colourfulclocks.common.block.base;

import com.chefmooon.colourfulclocks.common.block.entity.base.BaseGlassClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.data.types.WoodTypes;
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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
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

public class BaseClockBlock extends BaseEntityBlock {

    public static final MapCodec<BaseClockBlock> CODEC = simpleCodec(BaseClockBlock::new);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty CAN_TICK = ColourfulClocksBlockStateProperties.CAN_TICK;
    public static final BooleanProperty TICKING = ColourfulClocksBlockStateProperties.TICKING;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVATED = ColourfulClocksBlockStateProperties.ACTIVATED;
    public WoodTypes woodType;

    public static int FLAMMABILITY = 30;
    public static int FIRE_SPREAD = 60;

    public BaseClockBlock(Properties properties) {
        this(WoodTypes.OAK, properties);
    }

    protected BaseClockBlock(WoodTypes woodType, Properties properties) {
        super(properties);
        this.woodType = woodType;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, Boolean.FALSE)
                .setValue(CAN_TICK, Boolean.FALSE)
                .setValue(TICKING, Boolean.FALSE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                .setValue(CAN_TICK, Boolean.FALSE)
                .setValue(TICKING, Boolean.FALSE);
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

    private void checkPoweredState(Level level, BlockPos pos, BlockState state) {
        boolean bl = !level.hasNeighborSignal(pos);
        if (state.getValue(CAN_TICK) && bl != state.getValue(TICKING)) {
            level.setBlock(pos, state.setValue(TICKING, bl), 2);
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BaseGlassClockBlockEntity baseGlassClockBlockEntity) {
                baseGlassClockBlockEntity.setTicking(bl);
                level.blockEntityChanged(pos);
            }
        }
    }

    protected ItemInteractionResult setPocketWatchType(Level level, BlockPos pos, Player player, ItemStack itemStack, BaseGlassClockBlockEntity baseGlassClockBlockEntity) {
        if (itemStack.is(ColourfulClocksTags.CLOCK_HAND)) {
            PocketWatchTypes pocketWatchType = ColourfulClocksTypeUtil.getPocketWatchTypeFromItem(itemStack.getItem());
            if (pocketWatchType != baseGlassClockBlockEntity.getDialData().pocketWatchType()) {
                if (baseGlassClockBlockEntity.getDialData().getPocketWatchType().getId() != 0 && !player.getAbilities().instabuild) {
                    if (!player.getInventory().add(baseGlassClockBlockEntity.removePocketWatchType())) {
                        Containers.dropContents(level, pos, baseGlassClockBlockEntity.getDroppableInventory());
                    }
                }
                baseGlassClockBlockEntity.setPocketWatchType(player.getAbilities().instabuild ? itemStack.copy() : itemStack.split(1));
                level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.6F);
                level.updateNeighborsAt(pos, this);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.INSERT_POCKET_WATCH_TRIGGER.get().trigger(serverPlayer);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setWaxedState(Level level, BlockPos pos, Player player, ItemStack itemStack, BaseGlassClockBlockEntity baseGlassClockBlockEntity, boolean tryWax) {
        if (tryWax) {
            ItemStack waxedClockHands = ColourfulClocksTypeUtil.getWaxedClockHands(ColourfulClocksTypeUtil.getPocketWatchItemFromType(baseGlassClockBlockEntity.getDialData().getPocketWatchType()).getDefaultInstance()).get().getDefaultInstance();
            if (!waxedClockHands.isEmpty()) {
                baseGlassClockBlockEntity.setPocketWatchType(waxedClockHands);
                level.blockEntityChanged(pos);
                level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                if (!player.getAbilities().instabuild) itemStack.shrink(1);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(serverPlayer);

                return ItemInteractionResult.SUCCESS;
            }
        } else {
            Pair<Supplier<Item>, Supplier<SoundEvent>> clockHandInfo = ColourfulClocksTypeUtil.getScrapedClockHands(ColourfulClocksTypeUtil.getPocketWatchItemFromType(baseGlassClockBlockEntity.getDialData().getPocketWatchType()).getDefaultInstance());
            ItemStack scrapedClockHands = new ItemStack(clockHandInfo.getFirst().get());
            if (!scrapedClockHands.isEmpty()) {
                baseGlassClockBlockEntity.setPocketWatchType(scrapedClockHands);
                level.blockEntityChanged(pos);
                level.playSound(player, pos, clockHandInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                if (!player.getAbilities().instabuild) itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(serverPlayer);

                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setTicking(Level level, BlockPos pos, Player player, ItemStack itemStack, BaseGlassClockBlockEntity baseGlassClockBlockEntity, boolean tryTicking) {
        if (tryTicking) {
            if (!baseGlassClockBlockEntity.getDialData().ticking()) {
                baseGlassClockBlockEntity.setTicking(true);
                level.setBlock(pos, level.getBlockState(pos).setValue(CAN_TICK, true).setValue(TICKING, true), 3);
                this.checkPoweredState(level, pos, level.getBlockState(pos));
                level.blockEntityChanged(pos);
                level.playSound(player, pos, SoundEvents.CHAIN_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!player.getAbilities().instabuild) itemStack.shrink(1);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.ENABLE_TICKING_TRIGGER.get().trigger(serverPlayer);

                return ItemInteractionResult.SUCCESS;
            }
        } else  {
            if (baseGlassClockBlockEntity.getDialData().getTicking()) {
                baseGlassClockBlockEntity.setTicking(false);
                level.setBlock(pos, level.getBlockState(pos).setValue(CAN_TICK, false).setValue(TICKING, false), 3);
                level.blockEntityChanged(pos);
                level.playSound(player, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
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
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
