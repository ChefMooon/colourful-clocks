package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.block.base.BaseDataClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.WallClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.properties.WallClockPartProperty;
import com.chefmooon.colourfulclocks.common.data.ClockComponent;
import com.chefmooon.colourfulclocks.common.data.OffsetRecord;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
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

public class WallClockBlock extends BaseDataClockBlock {
    public static final BooleanProperty XL = BooleanProperty.create("xl");
    public static final EnumProperty<WallClockPartProperty> PART = EnumProperty.create("part", WallClockPartProperty.class);
    private static final VoxelShape SHAPE_NORTH = Shapes.or(Block.box(0, 0, 0, 16, 16, 2));
    private final ConcurrentHashMap<Direction, VoxelShape> SHAPE;
    public WallClockBlock(ClockTypes clockTypes, Properties properties) {
        super(clockTypes, properties);
        this.SHAPE = VoxelShapeUtil.getRotatedShapesMap(SHAPE_NORTH);
    }

    // TODO: decide what happens if a clock is placed with pocket watch or ticking data. is it ignored or applied to multiblock?

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        Direction facing = context.getClickedFace().getAxis().isHorizontal() ? context.getClickedFace().getOpposite() : context.getHorizontalDirection();
        ClockComponent component = context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), ClockComponent.getBasicClockValue());
//        WallClockPartProperty part = getPartForPlacement(context);
        WallClockPartProperty part = WallClockPartProperty.BASE;
        return this.defaultBlockState().setValue(FACING, facing)
                .setValue(XL, Boolean.FALSE)
                .setValue(PART, part)
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                .setValue(CAN_TICK,  component.getTicking().get())
                .setValue(TICKING, Boolean.FALSE);
    }

    // TODO: decide check part on placement or on update shape. Maybe both?
    private WallClockPartProperty getPartForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction facing = context.getClickedFace().getAxis().isHorizontal() ? context.getClickedFace().getOpposite() : context.getHorizontalDirection();

        BlockState aboveBlockState = level.getBlockState(pos.above());
        BlockState belowBlockState = level.getBlockState(pos.below());
        BlockState leftBlockState = level.getBlockState(pos.relative(facing.getCounterClockWise()));
        BlockState rightBlockState = level.getBlockState(pos.relative(facing.getClockWise()));

        if (aboveBlockState.getBlock() instanceof WallClockBlock) {
            if (leftBlockState.getBlock() instanceof WallClockBlock) {
                if (aboveBlockState.getValue(PART) == WallClockPartProperty.BASE && leftBlockState.getValue(PART) == WallClockPartProperty.BASE) {
                    return WallClockPartProperty.BOTTOM_RIGHT;
                }
            } else if (rightBlockState.getBlock() instanceof WallClockBlock) {
                if (aboveBlockState.getValue(PART) == WallClockPartProperty.BASE && rightBlockState.getValue(PART) == WallClockPartProperty.BASE) {
                    return WallClockPartProperty.BOTTOM_LEFT;
                }
            }
        } else if (belowBlockState.getBlock() instanceof WallClockBlock) {
            if (leftBlockState.getBlock() instanceof WallClockBlock) {
                if (belowBlockState.getValue(PART) == WallClockPartProperty.BASE && leftBlockState.getValue(PART) == WallClockPartProperty.BASE) {
                    return WallClockPartProperty.TOP_RIGHT;
                }
            } else if (rightBlockState.getBlock() instanceof WallClockBlock) {
                if (belowBlockState.getValue(PART) == WallClockPartProperty.BASE && rightBlockState.getValue(PART) == WallClockPartProperty.BASE) {
                    return WallClockPartProperty.TOP_LEFT;
                }
            }
        }
        return WallClockPartProperty.BASE;
    }

    // TODO: this needs refinement
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BlockState aboveBlockState = level.getBlockState(pos.above());
        BlockState belowBlockState = level.getBlockState(pos.below());
        BlockState leftBlockState = level.getBlockState(pos.relative(state.getValue(FACING).getCounterClockWise()));
        BlockState rightBlockState = level.getBlockState(pos.relative(state.getValue(FACING).getClockWise()));

        BlockState aboveLeftBlockState = level.getBlockState(pos.above().relative(state.getValue(FACING).getCounterClockWise()));
        BlockState aboveRightBlockState = level.getBlockState(pos.above().relative(state.getValue(FACING).getClockWise()));
        BlockState belowLeftBlockState = level.getBlockState(pos.below().relative(state.getValue(FACING).getCounterClockWise()));
        BlockState belowRightBlockState = level.getBlockState(pos.below().relative(state.getValue(FACING).getClockWise()));

        if (aboveBlockState.getBlock() instanceof WallClockBlock && belowBlockState.getBlock() instanceof WallClockBlock) {
            if (leftBlockState.getBlock() instanceof WallClockBlock && rightBlockState.getBlock() instanceof WallClockBlock) {
                if (aboveBlockState.getValue(PART) == WallClockPartProperty.TOP_MIDDLE
                        && belowBlockState.getValue(PART) == WallClockPartProperty.BOTTOM_MIDDLE
                        && leftBlockState.getValue(PART) == WallClockPartProperty.LEFT_MIDDLE
                        && rightBlockState.getValue(PART) == WallClockPartProperty.RIGHT_MIDDLE) {
                    return state.setValue(PART, WallClockPartProperty.CENTER);
                } else {
                    return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
                }
            } else if (leftBlockState.getBlock() instanceof WallClockBlock) {
                return state.setValue(PART, WallClockPartProperty.RIGHT_MIDDLE);
//                if (aboveBlockState.getValue(PART) == WallClockPartProperty.TOP_RIGHT && belowBlockState.getValue(PART) == WallClockPartProperty.BOTTOM_RIGHT) {
//                    return state.setValue(PART, WallClockPartProperty.RIGHT_MIDDLE);
//                } else {
//                    return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
//                }
            } else if (rightBlockState.getBlock() instanceof WallClockBlock) {
                return state.setValue(PART, WallClockPartProperty.LEFT_MIDDLE);
//                if (aboveBlockState.getValue(PART) == WallClockPartProperty.TOP_LEFT && belowBlockState.getValue(PART) == WallClockPartProperty.BOTTOM_LEFT) {
//                    return state.setValue(PART, WallClockPartProperty.LEFT_MIDDLE);
//                } else {
//                    return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
//                }
            }
        } else if (aboveBlockState.getBlock() instanceof WallClockBlock) {
            if (leftBlockState.getBlock() instanceof WallClockBlock && rightBlockState.getBlock() instanceof WallClockBlock) {
                return state.setValue(PART, WallClockPartProperty.BOTTOM_MIDDLE);
//                if (aboveBlockState.getBlock() instanceof WallClockBlock && leftBlockState.getValue(PART) == WallClockPartProperty.BOTTOM_LEFT && rightBlockState.getValue(PART) == WallClockPartProperty.BOTTOM_RIGHT) {
//                    return state.setValue(PART, WallClockPartProperty.BOTTOM_MIDDLE);
//                } else {
//                    return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
//                }
            } else if (leftBlockState.getBlock() instanceof WallClockBlock && aboveLeftBlockState.getBlock() instanceof WallClockBlock) {
                if (aboveBlockState.getValue(PART) == WallClockPartProperty.RIGHT_MIDDLE && leftBlockState.getValue(PART) == WallClockPartProperty.BOTTOM_MIDDLE) {
                    return state.setValue(PART, WallClockPartProperty.BOTTOM_RIGHT).setValue(XL, Boolean.TRUE);
                } else {
                    return state.setValue(PART, WallClockPartProperty.BOTTOM_RIGHT).setValue(XL, Boolean.FALSE);
                }
            } else if (rightBlockState.getBlock() instanceof WallClockBlock && aboveRightBlockState.getBlock() instanceof WallClockBlock) {
                if (aboveBlockState.getValue(PART) == WallClockPartProperty.LEFT_MIDDLE && rightBlockState.getValue(PART) == WallClockPartProperty.BOTTOM_MIDDLE) {
                    return state.setValue(PART, WallClockPartProperty.BOTTOM_LEFT).setValue(XL, Boolean.TRUE);
                } else {
                    return state.setValue(PART, WallClockPartProperty.BOTTOM_LEFT).setValue(XL, Boolean.FALSE);
                }
            }
        } else if (belowBlockState.getBlock() instanceof WallClockBlock) {
            if (leftBlockState.getBlock() instanceof WallClockBlock && rightBlockState.getBlock() instanceof WallClockBlock) {
                return state.setValue(PART, WallClockPartProperty.TOP_MIDDLE);
//                if (belowRightBlockState.getBlock() instanceof WallClockBlock && leftBlockState.getValue(PART) == WallClockPartProperty.TOP_LEFT && rightBlockState.getValue(PART) == WallClockPartProperty.TOP_RIGHT) {
//                    return state.setValue(PART, WallClockPartProperty.TOP_MIDDLE);
//                } else {
//                    return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
//                }
            } else if (leftBlockState.getBlock() instanceof WallClockBlock && belowLeftBlockState.getBlock() instanceof WallClockBlock) {
                if (belowBlockState.getValue(PART) == WallClockPartProperty.RIGHT_MIDDLE && leftBlockState.getValue(PART) == WallClockPartProperty.TOP_MIDDLE) {
                    return state.setValue(PART, WallClockPartProperty.TOP_RIGHT).setValue(XL, Boolean.TRUE);
                } else {
                    return state.setValue(PART, WallClockPartProperty.TOP_RIGHT).setValue(XL, Boolean.FALSE);
                }
            } else if (rightBlockState.getBlock() instanceof WallClockBlock && belowRightBlockState.getBlock() instanceof WallClockBlock) {
                if (belowBlockState.getValue(PART) == WallClockPartProperty.LEFT_MIDDLE && rightBlockState.getValue(PART) == WallClockPartProperty.TOP_MIDDLE) {
                    return state.setValue(PART, WallClockPartProperty.TOP_LEFT).setValue(XL, Boolean.TRUE);
                } else {
                    return state.setValue(PART, WallClockPartProperty.TOP_LEFT).setValue(XL, Boolean.FALSE);
                }
            }
        }

        return super.updateShape(state.setValue(PART, WallClockPartProperty.BASE), direction, neighborState, level, pos, neighborPos);
    }

    private boolean canConnect(BlockState state) {


        return false;
    }

    private boolean tryUpdateFullShape(WallClockPartProperty part, BlockState state, LevelAccessor level, BlockPos pos) {
        for (WallClockPartProperty partProperty : WallClockPartProperty.values()) {
            if (part == partProperty) continue;
            BlockPos controllerPos = pos.relative(state.getValue(FACING).getCounterClockWise()).offset(0, -partProperty.getOffset().first().yOffset(), 0);
            BlockPos partPos = controllerPos.relative(state.getValue(FACING).getClockWise(), partProperty.getOffset().first().xOffset()).offset(0, partProperty.getOffset().first().yOffset(), 0);
            BlockState blockState = level.getBlockState(partPos);
            if (blockState.getBlock() instanceof WallClockBlock) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(XL, PART);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.get(state.getValue(FACING));
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
//        BlockEntity blockEntity = level.getBlockEntity(pos);
        BlockEntity blockEntity = state.getValue(PART) == WallClockPartProperty.BASE || state.getValue(PART) == WallClockPartProperty.BOTTOM_LEFT ?
                level.getBlockEntity(pos) : getController(state, pos, level);
        if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
            ItemStack mainHandItem = player.getItemInHand(hand);
            if (!mainHandItem.isEmpty()) {
                if (mainHandItem.is(ColourfulClocksTags.CLOCK_HAND)) {
                    return setPocketWatchType(level, pos, player, mainHandItem, wallClockBlockEntity);
                } else if (mainHandItem.is(Items.HONEYCOMB)) {
                    return setWaxedState(level, pos, player, mainHandItem, wallClockBlockEntity, true);
                } else if (mainHandItem.is(ItemTags.AXES)) {
                    return setWaxedState(level, pos, player, mainHandItem, wallClockBlockEntity, false);
                } else if (!state.getValue(TICKING) && mainHandItem.is(Items.REDSTONE)) {
                    return setTicking(level, pos, player, mainHandItem, wallClockBlockEntity, true);
                } else if (state.getValue(TICKING) && mainHandItem.is(ItemTags.PICKAXES)) {
                    return setTicking(level, pos, player, mainHandItem, wallClockBlockEntity, false);
                }
            } else {
                if (player.isShiftKeyDown() && wallClockBlockEntity.getData().getPocketWatchType().isPresent() && wallClockBlockEntity.getData().getPocketWatchType().get().getId() != 0) {
                    return removePocketWatch(level, pos, player, wallClockBlockEntity);
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private BlockEntity getController(BlockState state, BlockPos pos, Level level) {
        Direction facing = state.getValue(FACING);
        WallClockPartProperty part = state.getValue(PART);
        OffsetRecord offset = state.getValue(XL) ? part.getOffset().second() : part.getOffset().first();
        BlockPos partPos = pos.relative(facing.getCounterClockWise(), offset.xOffset()).offset(0, -offset.yOffset(), 0);
        BlockEntity blockEntity = level.getBlockEntity(partPos);
        ColourfulClocks.LOGGER.info("Get controller at {} for part {} at {}: {}", partPos, part, pos, blockEntity);
        return blockEntity;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.WALL_CLOCK).create(pos, state);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        BlockEntityType<WallClockBlockEntity> wallClockType = (BlockEntityType<WallClockBlockEntity>) Objects.requireNonNull(
                BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.WALL_CLOCK));
        return createTickerHelper(blockEntity, wallClockType, WallClockBlockEntity::weatherTick);
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
            if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
                if (state.getValue(PART) == WallClockPartProperty.BASE || state.getValue(PART) == WallClockPartProperty.BOTTOM_LEFT) {
                    drops.addAll(wallClockBlockEntity.getDroppableInventory());
                }
            }
        }
        return drops;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
//            BlockEntity blockEntity = level.getBlockEntity(pos);
            BlockEntity blockEntity = state.getValue(PART) == WallClockPartProperty.BASE || state.getValue(PART) == WallClockPartProperty.BOTTOM_LEFT ?
                    level.getBlockEntity(pos) : getController(state, pos, level);
            if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
                ClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), ClockComponent.getBasicClockValue());
                if (component != null) {
                    wallClockBlockEntity.setData(null, component.getPocketWatchType().get(), null, component.getTicking().get());
                    if (wallClockBlockEntity.getData().getPocketWatchType().get().getId() != 0 && wallClockBlockEntity.getData().getPocketWatchType().get() != component.getPocketWatchType().get()) {
                        if (component.getPocketWatchType().get() != PocketWatchTypes.EMPTY) {
                            wallClockBlockEntity.setPocketWatchType(ColourfulClocksTypeUtil.getPocketWatchItemFromType(component.getPocketWatchType().get()).getDefaultInstance());
                        }
                    }
                }
            }
        }
    }
}
