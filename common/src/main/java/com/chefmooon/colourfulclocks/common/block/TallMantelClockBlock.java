package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.base.BaseDataGlassClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.TallMantelClockBlockEntity;
import com.chefmooon.colourfulclocks.common.data.ClockComponent;
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

public class TallMantelClockBlock extends BaseDataGlassClockBlock {
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
                .setValue(WALL, false));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        Direction facing = context.getClickedFace().getAxis().isHorizontal() ? context.getClickedFace().getOpposite() : context.getHorizontalDirection();
        boolean isWall = context.getClickedFace().getAxis().isHorizontal();
        ClockComponent component = context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), ClockComponent.getBasicClockValue());
        return this.defaultBlockState().setValue(FACING, facing)
                .setValue(GLASS_TYPE, component.getGlassType().get())
                .setValue(WALL, isWall)
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                .setValue(CAN_TICK,  component.getTicking().get())
                .setValue(TICKING, Boolean.FALSE);
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WALL);
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
                drops.addAll(tallMantelClockBlockEntity.getDroppableInventory());
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
                ClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), ClockComponent.getBasicClockValue());
                if (component != null) {
                    tallMantelClockBlockEntity.setData(component);
                }
            }
        }
    }
}
