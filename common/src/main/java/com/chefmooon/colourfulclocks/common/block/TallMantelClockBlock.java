package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.block.base.BaseClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.TallMantelClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.BornholmTopGlassTypeProperty;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.GlassDialComponent;
import com.chefmooon.colourfulclocks.common.data.TallMantelClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksAdvancements;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.VoxelShapeUtil;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class TallMantelClockBlock extends BaseClockBlock {
    public static final BornholmTopGlassTypeProperty GLASS_TYPE = ColourfulClocksBlockStateProperties.BORNHOLM_TOP_GLASS_TYPE;
    public static final BooleanProperty WALL = BooleanProperty.create("wall");
    private static final VoxelShape SHAPE_AXIS_Z = Shapes.or(
            Block.box(3, 0, 4, 13, 1, 12),
            Block.box(3, 15, 4, 13, 16, 12),
            Block.box(4, 1, 5, 12, 15, 11)
    );

    private final VoxelShape SHAPE_AXIS_X;

    private static final VoxelShape SHAPE_WALL_NORTH = Shapes.or(
            Block.box(3, 0, 0, 13, 1, 7),
            Block.box(3, 15, 0, 13, 16, 7),
            Block.box(4, 1, 0, 12, 15, 6)
    );
    private final ConcurrentHashMap<Direction, VoxelShape> SHAPE_WALL;
    public TallMantelClockBlock(ClockTypes clockType, Properties properties) {
        super(clockType, properties);
        this.SHAPE_AXIS_X = VoxelShapeUtil.rotateVoxelShape(SHAPE_AXIS_Z, Direction.EAST);
        this.SHAPE_WALL = VoxelShapeUtil.getRotatedShapesMap(SHAPE_WALL_NORTH);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(GLASS_TYPE, BornholmTopGlassTypes.GLASS)
                .setValue(WALL, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        Direction facing = context.getClickedFace().getAxis().isHorizontal() ? context.getClickedFace().getOpposite() : context.getHorizontalDirection();
        boolean isWall = context.getClickedFace().getAxis().isHorizontal();
        TallMantelClockComponent glassDialComponent = context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getTallMantelClockData(), TallMantelClockComponent.getDefaultValue());
        return this.defaultBlockState().setValue(FACING, facing)
                .setValue(GLASS_TYPE, glassDialComponent.getGlassType())
                .setValue(WALL, isWall)
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                .setValue(CAN_TICK,  glassDialComponent.getTicking())
                .setValue(TICKING, Boolean.FALSE);
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(GLASS_TYPE, WALL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        if (state.getValue(WALL)) {
            return SHAPE_WALL.get(facing);
        } else {
            if (facing.getAxis() == Direction.Axis.X) {
                return SHAPE_AXIS_X;
            }
            return SHAPE_AXIS_Z;
        }
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof TallMantelClockBlockEntity tallMantelClockBlockEntity) {
            ItemStack mainHandItem = player.getMainHandItem();
            boolean interactUpper = hit.getLocation().y - pos.getY() > 0.5;
            if (!mainHandItem.isEmpty()) {
                if (mainHandItem.is(ColourfulClocksTags.CLOCK_HAND)) {
                    return setPocketWatchType(level, pos, player, mainHandItem, tallMantelClockBlockEntity);
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_TOP_GLASS)) {
                    return setGlassType(level, state, pos, player, mainHandItem, tallMantelClockBlockEntity);
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_PENDULUM)) {
                    return setPendulumType(level, pos, player, mainHandItem, tallMantelClockBlockEntity);
                } else if (mainHandItem.is(Items.HONEYCOMB)) {
                    if (interactUpper) {
                        return setWaxedState(level, pos, player, mainHandItem, tallMantelClockBlockEntity, true);
                    } else {
                        return setPendulumWaxedState(level, pos, player, mainHandItem, tallMantelClockBlockEntity, true);
                    }
                } else if (mainHandItem.is(ItemTags.AXES)) {
                    if (interactUpper) {
                        return setWaxedState(level, pos, player, mainHandItem, tallMantelClockBlockEntity, false);
                    } else {
                        return setPendulumWaxedState(level, pos, player, mainHandItem, tallMantelClockBlockEntity, false);
                    }
                } else if (!state.getValue(TICKING) && mainHandItem.is(Items.REDSTONE)) {
                    return setTicking(level, pos, player, mainHandItem, tallMantelClockBlockEntity, true);
                } else if (state.getValue(TICKING) && mainHandItem.is(ItemTags.PICKAXES)) {
                    return setTicking(level, pos, player, mainHandItem, tallMantelClockBlockEntity, false);
                }
            } else {
                if (player.isShiftKeyDown()) {
                    if (interactUpper) {
                        return removePocketWatch(level, pos, player, tallMantelClockBlockEntity);
                    } else {
                        return removePendulum(level, pos, player, tallMantelClockBlockEntity);
                    }
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.TALL_MANTEL_CLOCK).create(pos, state);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        BlockEntityType<TallMantelClockBlockEntity> tallMantelClockType = (BlockEntityType<TallMantelClockBlockEntity>) Objects.requireNonNull(
                BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.TALL_MANTEL_CLOCK)
        );
        return createTickerHelper(blockEntity, tallMantelClockType, TallMantelClockBlockEntity::weatherTick);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof TallMantelClockBlockEntity tallMantelClockBlockEntity) {
            return tallMantelClockBlockEntity.getBlockAsItem(this.clockType);
        } else {
            return super.getCloneItemStack(level, pos, state);
        }
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
            if (blockEntity instanceof TallMantelClockBlockEntity tallMantelClockBlockEntity) {
                ItemStack pocketWatchItem = ColourfulClocksTypeUtil.getPocketWatchItemFromType(tallMantelClockBlockEntity.getData().pocketWatchType()).getDefaultInstance();
                if (!pocketWatchItem.isEmpty()) {
                    drops.add(pocketWatchItem);
                }
                ItemStack pendulumItem = ColourfulClocksTypeUtil.getPendulumItemFromType(tallMantelClockBlockEntity.getData().pendulumType()).getDefaultInstance();
                if (!pendulumItem.isEmpty()) {
                    drops.add(pendulumItem);
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
            if (blockEntity instanceof TallMantelClockBlockEntity tallMantelClockBlockEntity) {
                TallMantelClockComponent dialData = stack.get(ColourfulClocksDataComponentTypes.getTallMantelClockData());
                if (dialData != null) {
                    tallMantelClockBlockEntity.setData(dialData.getGlassType(), dialData.getPocketWatchType(), dialData.getPendulumType(), dialData.getTicking());
                    if (dialData.getPocketWatchType() != PocketWatchTypes.EMPTY) {
                        tallMantelClockBlockEntity.setPocketWatchType(ColourfulClocksTypeUtil.getPocketWatchItemFromType(dialData.getPocketWatchType()).getDefaultInstance());
                    }
                    if (dialData.getPendulumType() != PendulumTypes.EMPTY) {
                        tallMantelClockBlockEntity.setPendulumType(ColourfulClocksTypeUtil.getPendulumItemFromType(dialData.getPendulumType()).getDefaultInstance());
                    }
                    tallMantelClockBlockEntity.setGlassType(dialData.getGlassType());
                }
            }
        }
    }

    protected ItemInteractionResult setGlassType(Level level, BlockState state, BlockPos pos, Player player, ItemStack itemStack, TallMantelClockBlockEntity tallMantelClockBlockEntity) {
        if (itemStack.is(ColourfulClocksTags.CLOCK_TOP_GLASS)) {
            if (itemStack.is(state.getValue(GLASS_TYPE).getItem())) return ItemInteractionResult.CONSUME;
            BornholmTopGlassTypes newBornholmTopGlassTypes = ColourfulClocksTypeUtil.getBornholmTopGlassTypeFromItem(itemStack.getItem());
            tallMantelClockBlockEntity.setGlassType(newBornholmTopGlassTypes);
            level.setBlockAndUpdate(pos, state.setValue(GLASS_TYPE, newBornholmTopGlassTypes));
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHANGE_GLASS.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.GLASS_CHANGE_TRIGGER.get().trigger(serverPlayer);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setPendulumType(Level level, BlockPos pos, Player player, ItemStack itemStack, TallMantelClockBlockEntity tallMantelClockBlockEntity) {
        if (itemStack.is(ColourfulClocksTags.CLOCK_PENDULUM)) {
            PendulumTypes pendulumType = ColourfulClocksTypeUtil.getPendulumTypeFromItem(itemStack.getItem());
            if (pendulumType != tallMantelClockBlockEntity.getData().pendulumType()) {
                if (tallMantelClockBlockEntity.getData().pendulumType().getId() != 0 && !player.getAbilities().instabuild) {
                    if (!player.getInventory().add(tallMantelClockBlockEntity.removePendulumType())) {
                        Containers.dropContents(level, pos, tallMantelClockBlockEntity.getDroppableInventory());
                    }
                }
                ItemStack pendulumItem = player.getAbilities().instabuild ? itemStack.copy() : itemStack.split(1);
                tallMantelClockBlockEntity.setPendulumType(pendulumItem);
                level.blockEntityChanged(pos);
                level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_PENDULUM.get(), SoundSource.BLOCKS, 0.8F, 0.5F);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.INSERT_PENDULUM_TRIGGER.get().trigger(serverPlayer);

                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult removePocketWatch(Level level, BlockPos pos, Player player, TallMantelClockBlockEntity tallMantelClockBlockEntity) {
        if (tallMantelClockBlockEntity.getData().pocketWatchType().getId() != 0) {
            if (player.isCreative()) {
                tallMantelClockBlockEntity.removePocketWatchType();
            } else if (!player.getInventory().add(tallMantelClockBlockEntity.removePocketWatchType())) {
                Containers.dropContents(level, pos, tallMantelClockBlockEntity.getDroppableInventory());
            }
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
            level.updateNeighborsAt(pos, this);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult removePendulum(Level level, BlockPos pos, Player player, TallMantelClockBlockEntity tallMantelClockBlockEntity) {
        if (tallMantelClockBlockEntity.getData().pendulumType().getId() != 0) {
            if (player.isCreative()) {
                tallMantelClockBlockEntity.removePendulumType();
            } else if (!player.getInventory().add(tallMantelClockBlockEntity.removePendulumType())) {
                Containers.dropContents(level, pos, tallMantelClockBlockEntity.getDroppableInventory());
            }
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_PENDULUM.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
            level.updateNeighborsAt(pos, this);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setPendulumWaxedState(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity, boolean tryWax) {
        if (blockEntity instanceof TallMantelClockBlockEntity tallMantelClockBlockEntity) {
            if (tryWax) {
                ItemStack waxedPendulum = ColourfulClocksTypeUtil.getWaxedCopperPendulum(ColourfulClocksTypeUtil.getPendulumItemFromType(tallMantelClockBlockEntity.getData().getPendulumType()).getDefaultInstance()).get().getDefaultInstance();
                if (!waxedPendulum.isEmpty()) {
                    tallMantelClockBlockEntity.setPendulumType(waxedPendulum);
                    level.blockEntityChanged(pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                    if (!player.getAbilities().instabuild) itemStack.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            } else {
                Pair<Supplier<Item>, Supplier<SoundEvent>> pendulumInfo = ColourfulClocksTypeUtil.getScrapedCopperPendulum(ColourfulClocksTypeUtil.getPendulumItemFromType(tallMantelClockBlockEntity.getData().getPendulumType()).getDefaultInstance());
                ItemStack scrapedPendulum = new ItemStack(pendulumInfo.getFirst().get());
                if (!scrapedPendulum.isEmpty()) {
                    tallMantelClockBlockEntity.setPendulumType(scrapedPendulum);
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

    @Override
    protected ItemInteractionResult setPocketWatchType(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity) {
        if (blockEntity instanceof TallMantelClockBlockEntity tallMantelClockBlockEntity) {
            PocketWatchTypes pocketWatchType = ColourfulClocksTypeUtil.getPocketWatchTypeFromItem(itemStack.getItem());
            if (pocketWatchType != tallMantelClockBlockEntity.getData().pocketWatchType()) {
                if (tallMantelClockBlockEntity.getData().getPocketWatchType().getId() != 0 && !player.getAbilities().instabuild) {
                    if (!player.getInventory().add(tallMantelClockBlockEntity.removePocketWatchType())) {
                        Containers.dropContents(level, pos, tallMantelClockBlockEntity.getDroppableInventory());
                    }
                }
                tallMantelClockBlockEntity.setPocketWatchType(player.getAbilities().instabuild ? itemStack.copy() : itemStack.split(1));
                level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.6F);
                level.updateNeighborsAt(pos, this);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.INSERT_POCKET_WATCH_TRIGGER.get().trigger(serverPlayer);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected ItemInteractionResult setWaxedState(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity, boolean tryWax) {
        if (blockEntity instanceof TallMantelClockBlockEntity tallMantelClockBlockEntity) {
            if (tryWax) {
                ItemStack waxedClockHands = ColourfulClocksTypeUtil.getWaxedClockHands(ColourfulClocksTypeUtil.getPocketWatchItemFromType(tallMantelClockBlockEntity.getData().getPocketWatchType()).getDefaultInstance()).get().getDefaultInstance();
                if (!waxedClockHands.isEmpty()) {
                    tallMantelClockBlockEntity.setPocketWatchType(waxedClockHands);
                    level.blockEntityChanged(pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                    if (!player.getAbilities().instabuild) itemStack.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            } else {
                Pair<Supplier<Item>, Supplier<SoundEvent>> clockHandInfo = ColourfulClocksTypeUtil.getScrapedClockHands(ColourfulClocksTypeUtil.getPocketWatchItemFromType(tallMantelClockBlockEntity.getData().getPocketWatchType()).getDefaultInstance());
                ItemStack scrapedClockHands = new ItemStack(clockHandInfo.getFirst().get());
                if (!scrapedClockHands.isEmpty()) {
                    tallMantelClockBlockEntity.setPocketWatchType(scrapedClockHands);
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

    @Override
    protected ItemInteractionResult setTicking(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity, boolean tryTicking) {
        if (blockEntity instanceof TallMantelClockBlockEntity tallMantelClockBlockEntity) {
            if (tryTicking) {
                if (!tallMantelClockBlockEntity.getData().ticking()) {
                    tallMantelClockBlockEntity.setTicking(true);
                    level.setBlock(pos, level.getBlockState(pos).setValue(CAN_TICK, true).setValue(TICKING, true), 3);
                    this.checkPoweredState(level, pos, level.getBlockState(pos));
                    level.blockEntityChanged(pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_ENABLE_TICKING.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                    if (!player.getAbilities().instabuild) itemStack.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.ENABLE_TICKING_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            } else  {
                if (tallMantelClockBlockEntity.getData().getTicking()) {
                    tallMantelClockBlockEntity.setTicking(false);
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
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

}
