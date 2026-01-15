package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.base.BaseClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.AlarmClockBlockEntity;
import com.chefmooon.colourfulclocks.common.data.AlarmClockComponent;
import com.chefmooon.colourfulclocks.common.data.ClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksAdvancements;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.chefmooon.colourfulclocks.common.util.VoxelShapeUtil;
import com.mojang.datafixers.util.Pair;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

public class AlarmClockBlock extends BaseClockBlock implements SimpleWaterloggedBlock {
    private static final VoxelShape SHAPE_AXIS_Z = Shapes.or(
            Block.box(5, 0, 5.5, 11, 1, 10.5),
            Block.box(5, 1, 5, 11, 2, 11),
            Block.box(4, 2, 5, 12, 8, 11),
            Block.box(5, 8, 5, 11, 9, 11));
    private final VoxelShape SHAPE_AXIS_X;
    public AlarmClockBlock(ClockTypes clockType, Properties properties) {
        super(clockType, properties);
        this.SHAPE_AXIS_X = VoxelShapeUtil.rotateVoxelShape(SHAPE_AXIS_Z, Direction.EAST);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        ClockComponent component = context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), ClockComponent.getNoPendulumValue());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
                .setValue(GLASS_TYPE, component.getGlassType().orElse(BornholmTopGlassTypes.GLASS))
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                .setValue(CAN_TICK,  component.getTicking().get())
                .setValue(TICKING, Boolean.FALSE);
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(FACING).getAxis() == Direction.Axis.X) {
            return SHAPE_AXIS_X;
        }
        return SHAPE_AXIS_Z;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof AlarmClockBlockEntity alarmClockBlockEntity) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (!mainHandItem.isEmpty()) {
                if (mainHandItem.is(ColourfulClocksTags.ITEM_HANDBELL)) {
                    return setBellType(level, state, pos, player, mainHandItem, alarmClockBlockEntity, hit.getLocation());
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_HAND)) {
                    return setPocketWatchType(level, pos, player, mainHandItem, alarmClockBlockEntity);
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_TOP_GLASS)) {
                    return setGlassType(level, state, pos, player, mainHandItem, alarmClockBlockEntity);
                } else if (mainHandItem.is(Items.HONEYCOMB)) {
                    return tryHoneycomb(level, state, pos, player, mainHandItem, alarmClockBlockEntity, hit.getLocation());
                } else if (mainHandItem.is(ItemTags.AXES)) {
                    return tryScrape(level, state, pos, player, mainHandItem, alarmClockBlockEntity, hit.getLocation());
                } else if (!state.getValue(TICKING) && mainHandItem.is(Items.REDSTONE)) {
                    return setTicking(level, pos, player, mainHandItem, alarmClockBlockEntity, true);
                } else if (state.getValue(TICKING) && mainHandItem.is(ItemTags.PICKAXES)) {
                    return setTicking(level, pos, player, mainHandItem, alarmClockBlockEntity, false);
                }
            } else {
                if (player.isShiftKeyDown()) {
                    return removeContents(level, state, pos, player, hit.getLocation(), alarmClockBlockEntity);
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.ALARM_CLOCK).create(pos, state);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        BlockEntityType<AlarmClockBlockEntity> mantelClockType =
                (BlockEntityType<AlarmClockBlockEntity>) Objects.requireNonNull(
                        BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.ALARM_CLOCK)
                );
        return createTickerHelper(blockEntity, mantelClockType, AlarmClockBlockEntity::weatherTick);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof AlarmClockBlockEntity alarmClockBlockEntity) {
            return alarmClockBlockEntity.getBlockAsItem(this.clockType);
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
            if (blockEntity instanceof AlarmClockBlockEntity alarmClockBlockEntity) {
                drops.addAll(alarmClockBlockEntity.getDroppableInventory());
            }
        }
        return drops;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AlarmClockBlockEntity alarmClockBlockEntity) {
                AlarmClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getAlarmClockData(), AlarmClockComponent.getDefaultValue());
                if (component != null) {
                    if (component.leftBell().isPresent()) {
                        alarmClockBlockEntity.setLeftHandbellType(BuiltInRegistries.ITEM.get(TextUtil.res(component.leftBell().get().getSerializedName() + "_handbell")).getDefaultInstance());
                    }
                    if (component.rightBell().isPresent()) {
                        alarmClockBlockEntity.setRightHandbellType(BuiltInRegistries.ITEM.get(TextUtil.res(component.rightBell().get().getSerializedName() + "_handbell")).getDefaultInstance());
                    }
                    if (component.pocketWatchType().get() != PocketWatchTypes.EMPTY) {
                        alarmClockBlockEntity.setPocketWatchType(BuiltInRegistries.ITEM.get(TextUtil.res(component.pocketWatchType().get().getSerializedName() + "_pocket_watch")).getDefaultInstance());
                    }
                }
            }
        }
    }

    private ItemInteractionResult setBellType(Level level, BlockState state, BlockPos pos, Player player, ItemStack mainHandItem, AlarmClockBlockEntity alarmClockBlockEntity, Vec3 location) {
        Direction facing = state.getValue(FACING);
        boolean left = isLeftSide(facing, location, pos);

        if (left) {
            if (alarmClockBlockEntity.getData().leftBell().isPresent()) {
                if (mainHandItem.is(alarmClockBlockEntity.getLeftBellItem().getItem())) {
                    return ItemInteractionResult.CONSUME;
                } else if (!player.getAbilities().instabuild) {
                    ItemStack oldLeftBellItemStack = alarmClockBlockEntity.removeLeftHandbellType();
                    if (!player.getInventory().add(oldLeftBellItemStack)) {
                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), oldLeftBellItemStack);
                    }
                }
            }
            alarmClockBlockEntity.setLeftHandbellType(mainHandItem.copy());
            // TODO : advancement
//          if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.BELL_CHANGE_TRIGGER.get().trigger(serverPlayer);
        } else {
            if (alarmClockBlockEntity.getData().rightBell().isPresent()) {
                if (mainHandItem.is(alarmClockBlockEntity.getRightBellItem().getItem())) {
                    return ItemInteractionResult.CONSUME;
                } else if (!player.getAbilities().instabuild) {
                    ItemStack oldRightBellItemStack = alarmClockBlockEntity.removeRightHandbellType();
                    if (!player.getInventory().add(oldRightBellItemStack)) {
                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), oldRightBellItemStack);
                    }
                }
            }
            alarmClockBlockEntity.setRightHandbellType(mainHandItem.copyWithCount(1));
            // TODO : advancement
        }
        if (!player.getAbilities().instabuild) mainHandItem.shrink(1);
        level.playSound(player, pos, SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 1.0F, 0.8F);
        return ItemInteractionResult.SUCCESS;
    }

    @ExpectPlatform
    public static boolean isLeftSide(Direction facing, Vec3 hit, BlockPos pos) {
        throw new AssertionError();
    }

    protected ItemInteractionResult setGlassType(Level level, BlockState state, BlockPos pos, Player player, ItemStack itemStack, AlarmClockBlockEntity alarmClockBlockEntity) {
        if (itemStack.is(ColourfulClocksTags.CLOCK_TOP_GLASS)) {
            if (itemStack.is(state.getValue(GLASS_TYPE).getItem())) return ItemInteractionResult.CONSUME;
            BornholmTopGlassTypes newBornholmTopGlassTypes = ColourfulClocksTypeUtil.getBornholmTopGlassTypeFromItem(itemStack.getItem());
            alarmClockBlockEntity.setGlassType(newBornholmTopGlassTypes);
            level.setBlockAndUpdate(pos, state.setValue(GLASS_TYPE, newBornholmTopGlassTypes));
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHANGE_GLASS.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.GLASS_CHANGE_TRIGGER.get().trigger(serverPlayer);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setPocketWatchType(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity) {
        if (blockEntity instanceof AlarmClockBlockEntity alarmClockBlockEntity) {
            PocketWatchTypes pocketWatchType = ColourfulClocksTypeUtil.getPocketWatchTypeFromItem(itemStack.getItem());
            if (alarmClockBlockEntity.getData().pocketWatchType().isPresent() && pocketWatchType != alarmClockBlockEntity.getData().pocketWatchType().get()) {
                if (alarmClockBlockEntity.getData().pocketWatchType().get().getId() != 0 && !player.getAbilities().instabuild) {
                    ItemStack oldPocketWatchItemStack = alarmClockBlockEntity.removePocketWatchType();
                    if (!player.getInventory().add(oldPocketWatchItemStack)) {
                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), oldPocketWatchItemStack);
                    }
                }
                alarmClockBlockEntity.setPocketWatchType(player.getAbilities().instabuild ? itemStack.copy() : itemStack.split(1));
                level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.6F);
                level.updateNeighborsAt(pos, this);
                if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.INSERT_POCKET_WATCH_TRIGGER.get().trigger(serverPlayer);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult removeContents(Level level, BlockState blockState, BlockPos pos, Player player, Vec3 hit, AlarmClockBlockEntity alarmClockBlockEntity) {
        if (hit.y - pos.getY() < 0.495) {
            return removePocketWatch(level, pos, player, alarmClockBlockEntity);
        } else {
            Direction facing = blockState.getValue(FACING);
            boolean left = isLeftSide(facing, hit, pos);
            if (left) {
                return removeBell(level, pos, player, alarmClockBlockEntity, true);
            } else {
                return removeBell(level, pos, player, alarmClockBlockEntity, false);
            }
        }
    }

    protected ItemInteractionResult removeBell(Level level, BlockPos pos, Player player, AlarmClockBlockEntity alarmClockBlockEntity, boolean left) {
        if (left) {
            if (alarmClockBlockEntity.getData().leftBell().isPresent()) {
                ItemStack oldLeftBellItemStack = BuiltInRegistries.ITEM.get(TextUtil.res(alarmClockBlockEntity.getData().leftBell().get().getSerializedName() + "_handbell")).getDefaultInstance();
                alarmClockBlockEntity.removeLeftHandbellType();
                if (!player.getAbilities().instabuild && !player.getInventory().add(oldLeftBellItemStack)) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), oldLeftBellItemStack);
                }
                level.playSound(player, pos, SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 1.0F, 0.8F);
                return ItemInteractionResult.SUCCESS;
            }
        } else {
            if (alarmClockBlockEntity.getData().rightBell().isPresent()) {
                ItemStack oldRightBellItemStack = BuiltInRegistries.ITEM.get(TextUtil.res(alarmClockBlockEntity.getData().rightBell().get().getSerializedName() + "_handbell")).getDefaultInstance();
                alarmClockBlockEntity.removeRightHandbellType();
                if (!player.getAbilities().instabuild && !player.getInventory().add(oldRightBellItemStack)) {
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), oldRightBellItemStack);
                }
                level.playSound(player, pos, SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 1.0F, 0.8F);
                return ItemInteractionResult.SUCCESS;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult removePocketWatch(Level level, BlockPos pos, Player player, AlarmClockBlockEntity alarmClockBlockEntity) {
        if (alarmClockBlockEntity.getData().pocketWatchType().isPresent() && alarmClockBlockEntity.getData().pocketWatchType().get().getId() != 0) {
            if (player.isCreative()) {
                alarmClockBlockEntity.removePocketWatchType();
            } else if (!player.getInventory().add(alarmClockBlockEntity.removePocketWatchType())) {
                Containers.dropContents(level, pos, alarmClockBlockEntity.getDroppableInventory());
            }
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_POCKET_WATCH.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
            level.updateNeighborsAt(pos, this);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult tryHoneycomb(Level level, BlockState blockState, BlockPos pos, Player player, ItemStack itemStack, AlarmClockBlockEntity alarmClockBlockEntity, Vec3 hit) {
        Direction facing = blockState.getValue(FACING);
        if (hit.y - pos.getY() < 0.495) {
            return setPocketWatchWaxedState(level, pos, player,itemStack , alarmClockBlockEntity, facing, true);
        } else {
            boolean left = isLeftSide(facing, hit, pos);
            if (left) {
                return setBellWaxedState(level, pos, player, itemStack, alarmClockBlockEntity, facing, true, true);
            } else {
                return setBellWaxedState(level, pos, player, itemStack, alarmClockBlockEntity, facing, true, false);
            }
        }
    }

    protected ItemInteractionResult tryScrape(Level level, BlockState blockState, BlockPos pos, Player player, ItemStack itemStack, AlarmClockBlockEntity alarmClockBlockEntity, Vec3 hit) {
        Direction facing = blockState.getValue(FACING);
        if (hit.y - pos.getY() < 0.495) {
            return setPocketWatchWaxedState(level, pos, player, itemStack ,alarmClockBlockEntity, facing, false);
        } else {
            boolean left = isLeftSide(facing, hit, pos);
            if (left) {
                return setBellWaxedState(level, pos, player, itemStack, alarmClockBlockEntity, facing, false, true);
            } else {
                return setBellWaxedState(level, pos, player, itemStack, alarmClockBlockEntity, facing, false, false);
            }
        }
    }

    protected ItemInteractionResult setBellWaxedState(Level level, BlockPos pos, Player player, ItemStack itemStack, AlarmClockBlockEntity alarmClockBlockEntity, Direction facing, boolean tryWax, boolean left) {
        AlarmClockComponent data = alarmClockBlockEntity.getData();
        Optional<HandbellTypes> bellOpt = left ? data.leftBell() : data.rightBell();
        if (bellOpt.isEmpty()) return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        // current item in the bell slot and setter for left/right
        ItemStack currentBellItem = left ? alarmClockBlockEntity.getLeftBellItem() : alarmClockBlockEntity.getRightBellItem();
        java.util.function.Consumer<ItemStack> setBell = bs -> {
            if (left) alarmClockBlockEntity.setLeftHandbellType(bs);
            else alarmClockBlockEntity.setRightHandbellType(bs);
        };

        if (tryWax) {
            ItemStack waxed = BuiltInRegistries.ITEM.get(TextUtil.res("waxed_" + bellOpt.get().getSerializedName() + "_handbell")).getDefaultInstance();
            if (!waxed.isEmpty()) {
                setBell.accept(waxed);
                level.blockEntityChanged(pos);
                addBellParticle(level, pos, facing, left, ParticleTypes.WAX_ON);
                level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                if (!player.getAbilities().instabuild) itemStack.shrink(1);
                if (player instanceof ServerPlayer sp) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(sp);
                return ItemInteractionResult.SUCCESS;
            }
        } else {
            Pair<Item, Supplier<SoundEvent>> unwaxed = ColourfulClocksTypeUtil.getUnwaxedBell(currentBellItem);
            if (!unwaxed.getFirst().equals(ItemStack.EMPTY.getItem())) {
                setBell.accept(unwaxed.getFirst().getDefaultInstance());
                level.blockEntityChanged(pos);
                addBellParticle(level, pos, facing, left, ParticleTypes.WAX_OFF);
                level.playSound(player, pos, unwaxed.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                if (!player.getAbilities().instabuild) itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                if (player instanceof ServerPlayer sp) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(sp);
                return ItemInteractionResult.SUCCESS;
            }

            Pair<Item, Supplier<SoundEvent>> scraped = ColourfulClocksTypeUtil.getScrapedBell(currentBellItem);
            if (!scraped.getFirst().equals(ItemStack.EMPTY.getItem())) {
                setBell.accept(scraped.getFirst().getDefaultInstance());
                level.blockEntityChanged(pos);
                addBellParticle(level, pos, facing, left, ParticleTypes.SCRAPE);
                level.playSound(player, pos, scraped.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                if (!player.getAbilities().instabuild) itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
                if (player instanceof ServerPlayer sp) ColourfulClocksAdvancements.COPPER_WAX_OFF_TRIGGER.get().trigger(sp);
                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    protected ItemInteractionResult setPocketWatchWaxedState(Level level, BlockPos pos, Player player, ItemStack itemStack, BlockEntity blockEntity, Direction facing, boolean tryWax) {
        if (blockEntity instanceof AlarmClockBlockEntity alarmClockBlockEntity) {
            if (tryWax) {
                ItemStack waxedClockHands = ColourfulClocksTypeUtil.getWaxedClockHands(ColourfulClocksTypeUtil.getPocketWatchItemFromType(alarmClockBlockEntity.getData().pocketWatchType().orElse(PocketWatchTypes.EMPTY)).getDefaultInstance()).get().getDefaultInstance();
                if (!waxedClockHands.isEmpty()) {
                    alarmClockBlockEntity.setPocketWatchType(waxedClockHands);
                    level.blockEntityChanged(pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                    if (!player.getAbilities().instabuild) itemStack.shrink(1);
                    if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.COPPER_WAX_ON_TRIGGER.get().trigger(serverPlayer);

                    return ItemInteractionResult.SUCCESS;
                }
            } else {
                Pair<Supplier<Item>, Supplier<SoundEvent>> clockHandInfo = ColourfulClocksTypeUtil.getScrapedClockHands(ColourfulClocksTypeUtil.getPocketWatchItemFromType(alarmClockBlockEntity.getData().pocketWatchType().orElse(PocketWatchTypes.EMPTY)).getDefaultInstance());
                ItemStack scrapedClockHands = new ItemStack(clockHandInfo.getFirst().get());
                if (!scrapedClockHands.isEmpty()) {
                    alarmClockBlockEntity.setPocketWatchType(scrapedClockHands);
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
        if (blockEntity instanceof AlarmClockBlockEntity alarmClockBlockEntity) {
            if (alarmClockBlockEntity.getData().ticking().isPresent()) {
                if (tryTicking) {
                    if (!alarmClockBlockEntity.getData().ticking().get()) {
                        alarmClockBlockEntity.setTicking(true);
                        level.setBlock(pos, level.getBlockState(pos).setValue(CAN_TICK, true).setValue(TICKING, true), 3);
                        this.checkPoweredState(level, pos, level.getBlockState(pos));
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_ENABLE_TICKING.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
                        if (!player.getAbilities().instabuild) itemStack.shrink(1);
                        if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.ENABLE_TICKING_TRIGGER.get().trigger(serverPlayer);

                        return ItemInteractionResult.SUCCESS;
                    }
                } else  {
                    if (alarmClockBlockEntity.getData().ticking().get()) {
                        alarmClockBlockEntity.setTicking(false);
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

    private void addBellParticle(Level level, BlockPos pos, Direction facing, boolean left, ParticleOptions particleData) {
        for (int i = 0; i < 4; i++) {
            double xBase = pos.getX() + 0.5;
            double yBase = pos.getY() + 0.7;
            double zBase = pos.getZ() + 0.5;

            // Offset particle position based on facing and left/right bell
            double leftRightOffset = 0.18;
            switch (facing) {
                case NORTH:
                    xBase += left ? -leftRightOffset : leftRightOffset;
                    break;
                case SOUTH:
                    xBase += left ? leftRightOffset : -leftRightOffset;
                    break;
                case WEST:
                    zBase += left ? leftRightOffset : -leftRightOffset;
                    break;
                case EAST:
                    zBase += left ? -leftRightOffset : leftRightOffset;
                    break;
            }

            double offsetX = (level.random.nextDouble() - 0.5) * 0.2;
            double offsetY = (level.random.nextDouble() - 0.5) * 0.1;
            double offsetZ = (level.random.nextDouble() - 0.5) * 0.2;

            // Random velocity
            double speed = 0.2 + level.random.nextDouble() * 0.4; // 0.2 to 0.8
            double theta = level.random.nextDouble() * 2 * Math.PI;
            double phi = level.random.nextDouble() * Math.PI;
            double dx = speed * Math.sin(phi) * Math.cos(theta);
            double dy = speed * Math.sin(phi) * Math.sin(theta);
            double dz = speed * Math.cos(phi);

            level.addParticle(particleData, xBase + offsetX, yBase + offsetY, zBase + offsetZ, dx, dy, dz);
        }
    }
}
