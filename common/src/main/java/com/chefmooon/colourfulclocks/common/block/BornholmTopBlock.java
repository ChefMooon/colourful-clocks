package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmTopBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.BornholmTopGlassTypeProperty;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksAdvancements;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
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
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class BornholmTopBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<BornholmTopBlock> CODEC = simpleCodec(BornholmTopBlock::new);
    public static final BornholmTopGlassTypeProperty GLASS_TYPE = ColourfulClocksBlockStateProperties.BORNHOLM_TOP_GLASS_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty CAN_TICK = ColourfulClocksBlockStateProperties.CAN_TICK;
    public static final BooleanProperty TICKING = ColourfulClocksBlockStateProperties.TICKING;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty ACTIVATED = ColourfulClocksBlockStateProperties.ACTIVATED;
    public ClockTypes clockType;

    public static int FLAMMABILITY = 30;
    public static int FIRE_SPREAD = 60;

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
        this(ClockTypes.OAK, properties);
    }

    public BornholmTopBlock(ClockTypes clockType, Properties properties) {
        super(properties);
        this.clockType = clockType;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(GLASS_TYPE, BornholmTopGlassTypes.GLASS)
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, Boolean.FALSE)
                .setValue(CAN_TICK, Boolean.FALSE)
                .setValue(TICKING, Boolean.FALSE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        BornholmTopGlassComponent bornholmTopGlassComponent = context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getBornholmTopGlassData(), BornholmTopGlassComponent.getDefaultValue());
        if (isActivatedAdvancement(context.getLevel(), context.getClickedPos()) && context.getPlayer() instanceof ServerPlayer serverPlayer) {
            ColourfulClocksAdvancements.BORNHOLM_ACTIVATED_TRIGGER.get().trigger(serverPlayer);
        }
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
                .setValue(GLASS_TYPE, bornholmTopGlassComponent.getGlassType())
                .setValue(ACTIVATED, isActivated(context.getLevel().getBlockState(context.getClickedPos().below())))
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                .setValue(CAN_TICK, bornholmTopGlassComponent.getTicking())
                .setValue(TICKING, Boolean.FALSE);
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

    public boolean isActivatedAdvancement(Level level, BlockPos pos) {
        BlockState trunkState = level.getBlockState(pos.below());
        BlockState baseState = level.getBlockState(pos.below(2));
        return trunkState.getBlock() instanceof BornholmMiddleBlock && baseState.getBlock() instanceof BornholmBaseBlock;
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, GLASS_TYPE, ACTIVATED, WATERLOGGED, CAN_TICK, TICKING);
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
                    block.setPocketWatchType(player.getAbilities().instabuild ? mainHandItem.copy() : mainHandItem);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.6F);
                    level.updateNeighborsAt(pos, this);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.INSERT_POCKET_WATCH_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_TOP_GLASS)) {
                    if (mainHandItem.is(state.getValue(GLASS_TYPE).getItem())) return ItemInteractionResult.CONSUME;
                    BornholmTopGlassTypes newBornholmTopGlassTypes = ColourfulClocksTypeUtil.getBornholmTopGlassTypeFromItem(mainHandItem.getItem());
                    block.setGlassType(newBornholmTopGlassTypes);
                    level.setBlockAndUpdate(pos, state.setValue(GLASS_TYPE, newBornholmTopGlassTypes));
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHANGE_GLASS.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
                    if (!player.getAbilities().instabuild) mainHandItem.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.GLASS_CHANGE_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(Items.HONEYCOMB)) {
                    ItemStack waxedClockHands = new ItemStack(getWaxedClockHands(block.getClockHandsItem()).get());
                    if (!waxedClockHands.isEmpty()) {
                        block.setPocketWatchType(waxedClockHands);
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                        if (!player.getAbilities().instabuild) mainHandItem.shrink(1);
                        if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(serverPlayer);

                        return ItemInteractionResult.SUCCESS;
                    }
                } else if (mainHandItem.is(ItemTags.AXES)) {
                    Pair<Supplier<Item>, Supplier<SoundEvent>> clockHandInfo = getScrapedClockHands(block.getClockHandsItem());
                    ItemStack scrapedClockHands = new ItemStack(clockHandInfo.getFirst().get());
                    if (!scrapedClockHands.isEmpty()) {
                        block.setPocketWatchType(scrapedClockHands);
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, clockHandInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                        if (!player.getAbilities().instabuild) mainHandItem.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                        if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(serverPlayer);

                        return ItemInteractionResult.SUCCESS;
                    }
                } else if (!state.getValue(TICKING) && mainHandItem.is(Items.REDSTONE)) {
                    block.setTicking(true);
                    level.setBlock(pos, state.setValue(TICKING, Boolean.TRUE), 3);
                    level.playSound(null, pos, SoundEvents.CHAIN_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F); // TODO decide sound
                    if (!player.getAbilities().instabuild) mainHandItem.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.ENABLE_TICKING_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                } else if (state.getValue(TICKING) && mainHandItem.is(ItemTags.PICKAXES)) {
                    block.setTicking(false);
                    level.setBlock(pos, state.setValue(TICKING, Boolean.FALSE), 3);
                    level.playSound(null, pos, SoundEvents.CHAIN_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F); // TODO decide sound
                    if (!player.getAbilities().instabuild) mainHandItem.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.DISABLE_TICKING_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            } else {
                if (player.isShiftKeyDown() && !block.getClockHandsItem().isEmpty()) {
                    if (player.isCreative()) {
                        block.removeClockHandsItem();
                    } else if (!player.getInventory().add(block.removeClockHandsItem())) {
                        Containers.dropContents(level, pos, block.getDroppableInventory());
                    }
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
                    level.updateNeighborsAt(pos, this);

                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return result;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder params) {
        List<ItemStack> drops = super.getDrops(state, params);

        LootParams context = params.withParameter(LootContextParams.BLOCK_STATE, state).create(LootContextParamSets.BLOCK);
        ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
        ServerLevel serverLevel = context.getLevel();
        boolean hasSilkTouch = tool != null && tool.isEnchanted() && EnchantmentHelper.getItemEnchantmentLevel(serverLevel.registryAccess().registry(Registries.ENCHANTMENT).get().getHolderOrThrow(Enchantments.SILK_TOUCH), tool) > 0;

        if (!hasSilkTouch) {
            BlockEntity blockEntity = context.getParamOrNull(LootContextParams.BLOCK_ENTITY);
            if (blockEntity instanceof BornholmTopBlockEntity bornholmTopBlockEntity) {
                ItemStack pocketWatchItem = bornholmTopBlockEntity.getClockHandsItem();
                if (!pocketWatchItem.isEmpty()) {
                    drops.add(pocketWatchItem);
                }
            }
        }

        return drops;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BornholmTopBlockEntity bornholmTopBlockEntity) {
                BornholmTopGlassComponent dialData = stack.get(ColourfulClocksDataComponentTypes.getBornholmTopGlassData());
                if (dialData != null) {
                    bornholmTopBlockEntity.setDialData(dialData.getGlassType(), dialData.getPocketWatchType(), dialData.getTicking());
                    if (dialData.getPocketWatchType() != PocketWatchTypes.EMPTY) {
                        bornholmTopBlockEntity.setClockHandsItem(new ItemStack(ColourfulClocksTypeUtil.getPocketWatchItemFromType(dialData.getPocketWatchType())));
                    }
                }
            }
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof BornholmTopBlockEntity bornholmTopBlockEntity) {
            return bornholmTopBlockEntity.getBlockAsItem(this.clockType);
        } else {
            return super.getCloneItemStack(level, pos, state);
        }
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
        if (state.getValue(ACTIVATED) && state.getValue(CAN_TICK) && bl != state.getValue(TICKING)) {
            level.setBlock(pos, state.setValue(TICKING, bl), 2);
        }
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
