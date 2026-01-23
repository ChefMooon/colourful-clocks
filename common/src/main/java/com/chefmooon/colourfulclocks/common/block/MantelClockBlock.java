package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.base.BaseDataGlassClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.MantelClockBlockEntity;
import com.chefmooon.colourfulclocks.common.data.MantelClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.VoxelShapeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
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

public class MantelClockBlock extends BaseDataGlassClockBlock {
    private static final VoxelShape SHAPE_AXIS_Z = Shapes.or(
            Block.box(0, 0, 5, 16, 1, 11),
            Block.box(1, 1, 5, 15, 2, 11),
            Block.box(4, 2, 5, 12, 8, 11),
            Block.box(5, 8, 5, 11, 9, 11)
    );
    private final VoxelShape SHAPE_AXIS_X;
    public MantelClockBlock(ClockTypes clockType, Properties properties) {
        super(clockType, properties);
        this.SHAPE_AXIS_X = VoxelShapeUtil.rotateVoxelShape(SHAPE_AXIS_Z, Direction.EAST);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        MantelClockComponent component = context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getMantelClockData(), MantelClockComponent.getNoPendulumValue());
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
                .setValue(GLASS_TYPE, component.getGlassType().orElse(BornholmTopGlassTypes.GLASS))
                .setValue(ACTIVATED, Boolean.TRUE)
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
        if (blockEntity instanceof MantelClockBlockEntity mantelClockBlockEntity) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (!mainHandItem.isEmpty()) {
                if (mainHandItem.is(ColourfulClocksTags.CLOCK_HAND)) {
                    return setPocketWatchType(level, pos, player, mainHandItem, mantelClockBlockEntity);
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_TOP_GLASS)) {
                    return setGlassType(level, state, pos, player, mainHandItem, mantelClockBlockEntity);
                } else if (mainHandItem.is(Items.HONEYCOMB)) {
                    return setWaxedState(level, pos, player, mainHandItem, mantelClockBlockEntity, true);
                } else if (mainHandItem.is(ItemTags.AXES)) {
                    return setWaxedState(level, pos, player, mainHandItem, mantelClockBlockEntity, false);
                } else if (!state.getValue(TICKING) && mainHandItem.is(Items.REDSTONE)) {
                    return setTicking(level, pos, player, mainHandItem, mantelClockBlockEntity, true);
                } else if (state.getValue(TICKING) && mainHandItem.is(ItemTags.PICKAXES)) {
                    return setTicking(level, pos, player, mainHandItem, mantelClockBlockEntity, false);
                }
            } else {
                if (player.isShiftKeyDown() && mantelClockBlockEntity.getData().getPocketWatch().isPresent() && mantelClockBlockEntity.getData().getPocketWatch().get().getType().getId() != 0) {
                    return removePocketWatch(level, pos, player, mantelClockBlockEntity);
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.MANTEL_CLOCK).create(pos, state);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        BlockEntityType<MantelClockBlockEntity> mantelClockType =
                (BlockEntityType<MantelClockBlockEntity>) Objects.requireNonNull(
                        BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.MANTEL_CLOCK)
                );
        return createTickerHelper(blockEntity, mantelClockType, MantelClockBlockEntity::weatherTick);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof MantelClockBlockEntity mantelClockBlockEntity) {
            return mantelClockBlockEntity.getBlockAsItem(this.clockType);
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
            if (blockEntity instanceof MantelClockBlockEntity mantelClockBlockEntity) {
                drops.addAll(mantelClockBlockEntity.getDroppableInventory());
            }
        }
        return drops;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof MantelClockBlockEntity mantelClockBlockEntity) {
                MantelClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getMantelClockData(), MantelClockComponent.getNoPendulumValue());
                if (component != null) {
                    mantelClockBlockEntity.setData(component);
                }
            }
        }
    }
}
