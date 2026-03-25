package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmMiddleBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.block.state.properties.DoorTypeProperty;
import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.PendulumComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksAdvancements;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.CopperWeatheringUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.level.*;
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
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class BornholmMiddleBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<BornholmMiddleBlock> CODEC = simpleCodec(BornholmMiddleBlock::new);
    public static final DoorTypeProperty DOOR_TYPE = ColourfulClocksBlockStateProperties.BORNHOLM_DOOR_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty ACTIVATED = ColourfulClocksBlockStateProperties.ACTIVATED;
    public ClockTypes clockType;

    public static int FLAMMABILITY = 30;
    public static int FIRE_SPREAD = 60;

    private static final VoxelShape SHAPE  = Shapes.or(
            Block.box(3, 0, 3, 13, 1, 13),
            Block.box(4, 1, 4, 12, 15, 12),
            Block.box(3, 15, 3, 13, 16, 13)
    );

    private static final VoxelShape[] DOOR_SHAPES = new VoxelShape[] {
            Block.box(5, 2, 3, 11, 14, 4),
            Block.box(12, 2, 5, 13, 14, 11),
            Block.box(5, 2, 12, 11, 14, 13),
            Block.box(3, 2, 5, 4, 14, 11)
    };

    @Override
    public MapCodec<? extends BornholmMiddleBlock> codec() {
        return CODEC;
    }

    public BornholmMiddleBlock(Properties properties) {
        this(ClockTypes.OAK, properties);
    }

    public BornholmMiddleBlock(ClockTypes clockType, Properties properties) {
        super(properties);
        this.clockType = clockType;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, Boolean.FALSE)
                .setValue(DOOR_TYPE, BornholmDoorTypes.BASE)
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        boolean isActivated = isActivated(context.getLevel().getBlockState(context.getClickedPos().above()), context.getLevel().getBlockState(context.getClickedPos().below()));
        if (isActivated && context.getPlayer() instanceof ServerPlayer serverPlayer) {
            ColourfulClocksAdvancements.BORNHOLM_ACTIVATED_TRIGGER.get().trigger(serverPlayer);
        }
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
                .setValue(DOOR_TYPE, context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), BornholmMiddleDoorComponent.getDefaultValue()).getDoorType())
                .setValue(ACTIVATED, isActivated)
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        BlockState blockState = super.updateShape(stateIn, facing, facingState, level, currentPos, facingPos);

        if (!blockState.isAir()) {
            if (stateIn.getValue(WATERLOGGED)) {
                level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
            }
            return blockState.setValue(ACTIVATED, isActivated(level.getBlockState(currentPos.above()), level.getBlockState(currentPos.below())));
        }
        return blockState;
    }

    public boolean isActivated(BlockState blockStateAbove, BlockState blockStateBelow) {
        boolean bl = blockStateAbove.getBlock() instanceof BornholmTopBlock;
        boolean bl2 = blockStateBelow.getBlock() instanceof BornholmBaseBlock && blockStateBelow.getValue(BornholmBaseBlock.FACING) == Direction.DOWN;

        return bl && bl2;
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING, OPEN, DOOR_TYPE, ACTIVATED, WATERLOGGED);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);

        VoxelShape DOOR_SHAPE = DOOR_SHAPES[facing.get2DDataValue()];
        if (!state.getValue(OPEN)) {
            return Shapes.join(SHAPE, DOOR_SHAPE, BooleanOp.OR);
        }

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
        if (blockEntity instanceof BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();
            if (mainHandItem.isEmpty() && !offHandItem.isEmpty()) {
                mainHandItem = offHandItem;
            }

            if (!mainHandItem.isEmpty()) {
                Direction facing = state.getValue(FACING);
                if (mainHandItem.is(clockType.getItem())) {
                    if (state.getValue(DOOR_TYPE) == BornholmDoorTypes.BASE) return toggleDoor(level, state, pos, player);
                    bornholmMiddleBlockEntity.setDoorType(BornholmDoorTypes.BASE);
                    level.setBlockAndUpdate(pos, state.setValue(DOOR_TYPE, BornholmDoorTypes.BASE));
                    syncBlockEntity(level, pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHANGE_WOOD.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
                    if (!player.getAbilities().instabuild) mainHandItem.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.BORNHOLM_TRUNK_GLASS_CHANGE.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_DOOR)) {
                    if (mainHandItem.is(state.getValue(DOOR_TYPE).getItem())) return toggleDoor(level, state, pos, player);
                    BornholmDoorTypes newBornholmDoorTypes = ColourfulClocksTypeUtil.getTypeFromItem(mainHandItem.getItem());
                    bornholmMiddleBlockEntity.setDoorType(newBornholmDoorTypes);
                    level.setBlockAndUpdate(pos, state.setValue(DOOR_TYPE, newBornholmDoorTypes));
                    syncBlockEntity(level, pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHANGE_GLASS.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
                    if (!player.getAbilities().instabuild) mainHandItem.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.BORNHOLM_TRUNK_GLASS_CHANGE.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_PENDULUM) && state.getValue(OPEN)) {
                    if (bornholmMiddleBlockEntity.getData().getPendulum().isPresent()) {
                        PendulumTypes pendulumType = mainHandItem.get(ColourfulClocksDataComponentTypes.getPendulumData()).getType();
                        if (pendulumType.equals(bornholmMiddleBlockEntity.getData().getPendulum().get().getType())) {
                            return toggleDoor(level, state, pos, player);
                        } else if (!player.getAbilities().instabuild) {
                            ItemStack oldPendulumItemStack = BuiltInRegistries.ITEM.get(TextUtil.res(bornholmMiddleBlockEntity.getData().getPendulum().get().getType().getSerializedName() + "_pendulum")).getDefaultInstance();
                            PendulumComponent component = bornholmMiddleBlockEntity.removePendulumItem();
                            oldPendulumItemStack.set(ColourfulClocksDataComponentTypes.getPendulumData(), component);
                            if (!player.getInventory().add(oldPendulumItemStack)) {
                                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), oldPendulumItemStack);
                            }
                        }
                    }

                    bornholmMiddleBlockEntity.setPendulum(player.getAbilities().instabuild ? mainHandItem.copy() : mainHandItem);
                    syncBlockEntity(level, pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_PENDULUM.get(), SoundSource.BLOCKS, 0.8F, 0.5F);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.INSERT_PENDULUM_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(Items.HONEYCOMB)) {
                    ItemStack waxedPendulum = ColourfulClocksTypeUtil.getWaxedPendulum(bornholmMiddleBlockEntity.getData().getPendulum().get());
                    if (!waxedPendulum.isEmpty()) {
                        bornholmMiddleBlockEntity.setPendulum(waxedPendulum);
                        syncBlockEntity(level, pos);
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                        CopperWeatheringUtil.spawnPendulumUpdateParticles(level, pos, facing, ParticleTypes.WAX_ON, CopperWeatheringUtil.ClockPendulumParticleType.BORNHOLM_MIDDLE);
                        if (!player.getAbilities().instabuild) mainHandItem.shrink(1);
                        if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(serverPlayer);

                        return ItemInteractionResult.SUCCESS;
                    } else {
                        return toggleDoor(level, state, pos, player);
                    }
                } else if (mainHandItem.is(ItemTags.AXES)) {
                    Pair<Item, Supplier<SoundEvent>> unwaxedPendulumInfo = ColourfulClocksTypeUtil.getUnwaxedPendulum(bornholmMiddleBlockEntity.getData().getPendulum().orElse(PendulumComponent.getDefaultValue()));
                    ItemStack unwaxedPendulum = new ItemStack(unwaxedPendulumInfo.getFirst());
                    if (!unwaxedPendulum.isEmpty()) {
                        bornholmMiddleBlockEntity.setPendulum(unwaxedPendulum);
                        syncBlockEntity(level, pos);
                        level.playSound(player, pos, unwaxedPendulumInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                        CopperWeatheringUtil.spawnPendulumUpdateParticles(level, pos, facing, ParticleTypes.WAX_OFF, CopperWeatheringUtil.ClockPendulumParticleType.BORNHOLM_MIDDLE);
                        if (!player.getAbilities().instabuild)
                            mainHandItem.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                        if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(serverPlayer);

                        return ItemInteractionResult.SUCCESS;
                    }

                    Pair<Item, Supplier<SoundEvent>> scrapedPendulumInfo = ColourfulClocksTypeUtil.getScrapedPendulum(bornholmMiddleBlockEntity.getData().getPendulum().orElse(PendulumComponent.getDefaultValue()));
                    ItemStack scrapedPendulum = new ItemStack(scrapedPendulumInfo.getFirst());
                    if (!scrapedPendulum.isEmpty()) {
                        bornholmMiddleBlockEntity.setPendulum(scrapedPendulum);
                        syncBlockEntity(level, pos);
                        level.playSound(player, pos, scrapedPendulumInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                        CopperWeatheringUtil.spawnPendulumUpdateParticles(level, pos, facing, ParticleTypes.SCRAPE, CopperWeatheringUtil.ClockPendulumParticleType.BORNHOLM_MIDDLE);
                        if (!player.getAbilities().instabuild)
                            mainHandItem.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                        if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(serverPlayer);

                        return ItemInteractionResult.SUCCESS;
                    }
                    return toggleDoor(level, state, pos, player);
                } else if (mainHandItem.is(ColourfulClocksTags.BORNHOLM_DIAL) && hit.getDirection() == Direction.UP) {
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                }else {
                    return toggleDoor(level, state, pos, player);
                }
            } else {
                if (player.isShiftKeyDown() && state.getValue(OPEN)) {
                    if (bornholmMiddleBlockEntity.getData().getPendulum().isPresent() && bornholmMiddleBlockEntity.getData().getPendulum().get().getType() != PendulumTypes.EMPTY) {
                        ItemStack oldPendulum = BuiltInRegistries.ITEM.get(TextUtil.res(bornholmMiddleBlockEntity.getData().getPendulum().get().getType().getSerializedName() + "_pendulum")).getDefaultInstance();
                        PendulumComponent component = bornholmMiddleBlockEntity.removePendulumItem();
                        oldPendulum.set(ColourfulClocksDataComponentTypes.getPendulumData(), component);
                        if (!player.getAbilities().instabuild && !player.getInventory().add(oldPendulum)) {
                            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), oldPendulum);
                        }
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_PENDULUM.get(), SoundSource.BLOCKS, 0.8F, 0.7F);
                        syncBlockEntity(level, pos);

                        return ItemInteractionResult.SUCCESS;
                    }
                } else {
                     return toggleDoor(level, state, pos, player);
                }
            }
        }
        return result;
    }

    private ItemInteractionResult toggleDoor(Level level, BlockState state, BlockPos pos, Player player) {
        if (state.getValue(OPEN)) {
            level.setBlockAndUpdate(pos, state.setValue(OPEN, Boolean.FALSE));
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_DOOR_CLOSE.get(), SoundSource.BLOCKS, 0.5F, 0.9F);
        } else {
            level.setBlockAndUpdate(pos, state.setValue(OPEN, Boolean.TRUE));
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_DOOR_OPEN.get(), SoundSource.BLOCKS, 0.5F, 0.9F);
        }
        return ItemInteractionResult.SUCCESS;
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
            if (blockEntity instanceof BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
                drops.addAll(bornholmMiddleBlockEntity.getDroppableInventory());
            }
        }
        return drops;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
                BornholmMiddleDoorComponent trunkData = stack.get(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData());
                if (trunkData != null) {
                    bornholmMiddleBlockEntity.setData(trunkData);
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
    protected void onExplosionHit(BlockState blockState, Level level, BlockPos blockPos, Explosion explosion, BiConsumer<ItemStack, BlockPos> biConsumer) {
        if (explosion.canTriggerBlocks()) {
            level.setBlock(blockPos, blockState.setValue(OPEN, blockState.getValue(OPEN) ? Boolean.FALSE : Boolean.TRUE), 3);
            if (explosion.getIndirectSourceEntity() instanceof ServerPlayer serverPlayer) {
                ColourfulClocksAdvancements.BORNHOLM_TRUNK_WIND_CHARGE.get().trigger(serverPlayer);
            }
        }
        super.onExplosionHit(blockState, level, blockPos, explosion, biConsumer);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
            return bornholmMiddleBlockEntity.getBlockAsItem(this.clockType);
        } else {
            return super.getCloneItemStack(level, pos, state);
        }
    }

    private static void syncBlockEntity(Level level, BlockPos pos) {
        if (!level.isClientSide()) {
            BlockState state = level.getBlockState(pos);
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }
}
