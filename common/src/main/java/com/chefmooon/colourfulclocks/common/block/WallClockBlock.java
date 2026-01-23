package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.base.BaseDataClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.MantelClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.entity.WallClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.properties.WallClockPartProperty;
import com.chefmooon.colourfulclocks.common.data.MantelClockComponent;
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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
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
//    public static final BooleanProperty XL = BooleanProperty.create("xl");
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
        MantelClockComponent component = context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getMantelClockData(), MantelClockComponent.getBasicClockValue());
        WallClockPartProperty part = getPartForPlacement(context);
//        WallClockPartProperty part = WallClockPartProperty.BASE;
        return this.defaultBlockState().setValue(FACING, facing)
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

        BlockState aboveLeftBlockState = level.getBlockState(pos.above().relative(facing.getCounterClockWise()));
        BlockState aboveRightBlockState = level.getBlockState(pos.above().relative(facing.getClockWise()));
        BlockState belowLeftBlockState = level.getBlockState(pos.below().relative(facing.getCounterClockWise()));
        BlockState belowRightBlockState = level.getBlockState(pos.below().relative(facing.getClockWise()));

        WallClockPartProperty part = WallClockPartProperty.BASE;

        // Bottom Left XL
        if (((isWallClockBlock(facing, belowBlockState) && !isPart(facing, belowBlockState, WallClockPartProperty.BASE) && !isPart(facing, belowBlockState, WallClockPartProperty.BOTTOM_LEFT_XL))
                && isPart(facing, aboveBlockState, WallClockPartProperty.BASE) && isPart(facing, rightBlockState, WallClockPartProperty.BOTTOM_LEFT) && isPart(facing, aboveRightBlockState, WallClockPartProperty.TOP_LEFT))
                || (isPart(facing, aboveBlockState, WallClockPartProperty.BASE) && isPart(facing, rightBlockState, WallClockPartProperty.BASE) && isPart(facing, aboveRightBlockState, WallClockPartProperty.BOTTOM_LEFT))
                || ((isWallClockBlock(facing, leftBlockState) && !isPart(facing, leftBlockState, WallClockPartProperty.BASE) && !isPart(facing, leftBlockState, WallClockPartProperty.BOTTOM_LEFT_XL))
                    && isPart(facing, aboveBlockState, WallClockPartProperty.BOTTOM_LEFT) && isPart(facing, rightBlockState, WallClockPartProperty.BASE) && isPart(facing, aboveRightBlockState, WallClockPartProperty.BOTTOM_RIGHT))) {
            part = WallClockPartProperty.BOTTOM_LEFT_XL;
        }

        // Top Left XL
        if (((isWallClockBlock(facing, aboveBlockState) && !isPart(facing, aboveBlockState, WallClockPartProperty.BASE) && !isPart(facing, aboveBlockState, WallClockPartProperty.TOP_LEFT_XL))
                && isPart(facing, belowBlockState, WallClockPartProperty.BASE) && isPart(facing, rightBlockState, WallClockPartProperty.TOP_LEFT) && isPart(facing, belowRightBlockState, WallClockPartProperty.BOTTOM_LEFT))
                || (isPart(facing, belowBlockState, WallClockPartProperty.BASE) && isPart(facing, rightBlockState, WallClockPartProperty.BASE) && isPart(facing, belowRightBlockState, WallClockPartProperty.TOP_LEFT))
                || ((isWallClockBlock(facing, leftBlockState) && !isPart(facing, leftBlockState, WallClockPartProperty.BASE) && !isPart(facing, leftBlockState, WallClockPartProperty.TOP_LEFT_XL))
                    && isPart(facing, aboveBlockState, WallClockPartProperty.TOP_LEFT) && isPart(facing, rightBlockState, WallClockPartProperty.BASE) && isPart(facing, belowRightBlockState, WallClockPartProperty.TOP_RIGHT))) {
            part = WallClockPartProperty.TOP_LEFT_XL;
        }

        // Top Right XL
        if (((isWallClockBlock(facing, aboveBlockState) && !isPart(facing, aboveBlockState, WallClockPartProperty.BASE) && !isPart(facing, aboveBlockState, WallClockPartProperty.TOP_RIGHT_XL))
                && isPart(facing, belowBlockState, WallClockPartProperty.BASE) && isPart(facing, leftBlockState, WallClockPartProperty.TOP_RIGHT) && isPart(facing, belowLeftBlockState, WallClockPartProperty.BOTTOM_RIGHT))
                || (isPart(facing, belowBlockState, WallClockPartProperty.BASE) && isPart(facing, leftBlockState, WallClockPartProperty.BASE) && isPart(facing, belowLeftBlockState, WallClockPartProperty.TOP_RIGHT))
                || ((isWallClockBlock(facing, rightBlockState) && !isPart(facing, rightBlockState, WallClockPartProperty.BASE) && !isPart(facing, rightBlockState, WallClockPartProperty.TOP_RIGHT_XL))
                    && isPart(facing, aboveBlockState, WallClockPartProperty.TOP_RIGHT) && isPart(facing, leftBlockState, WallClockPartProperty.BASE) && isPart(facing, belowLeftBlockState, WallClockPartProperty.TOP_LEFT))) {
            part = WallClockPartProperty.TOP_RIGHT_XL;
        }

        // Bottom Right XL
        if (((isWallClockBlock(facing, belowBlockState) && !isPart(facing, belowBlockState, WallClockPartProperty.BASE) && !isPart(facing, belowBlockState, WallClockPartProperty.BOTTOM_RIGHT_XL))
                && isPart(facing, aboveBlockState, WallClockPartProperty.BASE) && isPart(facing, leftBlockState, WallClockPartProperty.BOTTOM_RIGHT) && isPart(facing, aboveLeftBlockState, WallClockPartProperty.TOP_RIGHT))
                || (isPart(facing, aboveBlockState, WallClockPartProperty.BASE) && isPart(facing, leftBlockState, WallClockPartProperty.BASE) && isPart(facing, aboveLeftBlockState, WallClockPartProperty.BOTTOM_RIGHT))
                || ((isWallClockBlock(facing, rightBlockState) && !isPart(facing, rightBlockState, WallClockPartProperty.BASE) && !isPart(facing, rightBlockState, WallClockPartProperty.BOTTOM_RIGHT_XL))
                    && isPart(facing, belowBlockState, WallClockPartProperty.BOTTOM_RIGHT) && isPart(facing, leftBlockState, WallClockPartProperty.BASE) && isPart(facing, aboveLeftBlockState, WallClockPartProperty.BOTTOM_LEFT))) {
            part = WallClockPartProperty.BOTTOM_RIGHT_XL;
        }

//        ColourfulClocks.LOGGER.info("Placing part: {} at {}", part, pos);
        return part;
    }

    private void setPart(Level level, BlockPos pos, WallClockPartProperty part) {
        BlockState state = level.getBlockState(pos);
        if (state.getBlock() instanceof WallClockBlock) {
            level.setBlock(pos, state.setValue(PART, part), Block.UPDATE_ALL);
        }
    }

    // TODO: this needs refinement
    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        WallClockPartProperty part = state.getValue(PART);
        Direction facing = state.getValue(FACING);
        BlockState aboveBlockState = level.getBlockState(pos.above());
        BlockState belowBlockState = level.getBlockState(pos.below());
        BlockState leftBlockState = level.getBlockState(pos.relative(state.getValue(FACING).getCounterClockWise()));
        BlockState rightBlockState = level.getBlockState(pos.relative(state.getValue(FACING).getClockWise()));

        BlockState aboveLeftBlockState = level.getBlockState(pos.above().relative(state.getValue(FACING).getCounterClockWise()));
        BlockState aboveRightBlockState = level.getBlockState(pos.above().relative(state.getValue(FACING).getClockWise()));
        BlockState belowLeftBlockState = level.getBlockState(pos.below().relative(state.getValue(FACING).getCounterClockWise()));
        BlockState belowRightBlockState = level.getBlockState(pos.below().relative(state.getValue(FACING).getClockWise()));

        // Bottom Middle
        if (((isPart(facing, leftBlockState, WallClockPartProperty.BOTTOM_LEFT_XL))
                || (isPart(facing, rightBlockState, WallClockPartProperty.BOTTOM_RIGHT_XL)))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.BOTTOM_MIDDLE), direction, neighborState, level, pos, neighborPos);
        }

        // Top Middle
        if (((isPart(facing, leftBlockState, WallClockPartProperty.TOP_LEFT_XL))
                || (isPart(facing, rightBlockState, WallClockPartProperty.TOP_RIGHT_XL)))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.TOP_MIDDLE), direction, neighborState, level, pos, neighborPos);
        }

        // Left Middle
        if (((isPart(facing, aboveBlockState, WallClockPartProperty.TOP_LEFT_XL))
                || (isPart(facing, belowBlockState, WallClockPartProperty.BOTTOM_LEFT_XL)))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.LEFT_MIDDLE), direction, neighborState, level, pos, neighborPos);
        }

        // Right Middle
        if (((isPart(facing, aboveBlockState, WallClockPartProperty.TOP_RIGHT_XL))
                || (isPart(facing, belowBlockState, WallClockPartProperty.BOTTOM_RIGHT_XL)))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.RIGHT_MIDDLE), direction, neighborState, level, pos, neighborPos);
        }

        // Bottom Left
        if ((part != WallClockPartProperty.TOP_RIGHT && isPart(facing, aboveBlockState, WallClockPartProperty.BASE) && isPart(facing, rightBlockState, WallClockPartProperty.BASE) && isPart(facing, aboveRightBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, aboveBlockState, WallClockPartProperty.TOP_LEFT) && isPart(facing, rightBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, rightBlockState, WallClockPartProperty.BOTTOM_RIGHT) && isPart(facing, aboveBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, aboveBlockState, WallClockPartProperty.TOP_LEFT) && isPart(facing, rightBlockState, WallClockPartProperty.BOTTOM_RIGHT))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.BOTTOM_LEFT), direction, neighborState, level, pos, neighborPos);
        }

        // Bottom Right
        if ((part != WallClockPartProperty.TOP_LEFT && isPart(facing, aboveBlockState, WallClockPartProperty.BASE) && isPart(facing, leftBlockState, WallClockPartProperty.BASE) && isPart(facing, aboveLeftBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, aboveBlockState, WallClockPartProperty.TOP_RIGHT) && isPart(facing, leftBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, leftBlockState, WallClockPartProperty.BOTTOM_LEFT) && isPart(facing, aboveBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, aboveBlockState, WallClockPartProperty.TOP_RIGHT) && isPart(facing, leftBlockState, WallClockPartProperty.BOTTOM_LEFT))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.BOTTOM_RIGHT), direction, neighborState, level, pos, neighborPos);
        }

        // Top Left
        if ((part != WallClockPartProperty.BOTTOM_RIGHT && isPart(facing, belowBlockState, WallClockPartProperty.BASE) && isPart(facing, rightBlockState, WallClockPartProperty.BASE) && isPart(facing, belowRightBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, belowBlockState, WallClockPartProperty.BOTTOM_LEFT) && isPart(facing, rightBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, rightBlockState, WallClockPartProperty.TOP_RIGHT) && isPart(facing, belowBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, belowBlockState, WallClockPartProperty.BOTTOM_LEFT) && isPart(facing, rightBlockState, WallClockPartProperty.TOP_RIGHT))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.TOP_LEFT), direction, neighborState, level, pos, neighborPos);
        }

        // Top Right
        if ((part != WallClockPartProperty.BOTTOM_LEFT && isPart(facing, belowBlockState, WallClockPartProperty.BASE) && isPart(facing, leftBlockState, WallClockPartProperty.BASE) && isPart(facing, belowLeftBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, belowBlockState, WallClockPartProperty.BOTTOM_RIGHT) && isPart(facing, leftBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, leftBlockState, WallClockPartProperty.TOP_LEFT) && isPart(facing, belowBlockState, WallClockPartProperty.BASE))
                || (isPart(facing, belowBlockState, WallClockPartProperty.BOTTOM_RIGHT) && isPart(facing, leftBlockState, WallClockPartProperty.TOP_LEFT))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.TOP_RIGHT), direction, neighborState, level, pos, neighborPos);
        }

        // Bottom Left XL
        if ((isPart(facing, rightBlockState, WallClockPartProperty.BOTTOM_MIDDLE) && (isPart(facing, aboveBlockState, WallClockPartProperty.BOTTOM_LEFT) || isPart(facing, aboveBlockState, WallClockPartProperty.BASE)))
                || (isPart(aboveBlockState, WallClockPartProperty.LEFT_MIDDLE) && (isPart(facing, rightBlockState, WallClockPartProperty.BOTTOM_LEFT) || isPart(facing, rightBlockState, WallClockPartProperty.BASE)))
                || (isPart(rightBlockState, WallClockPartProperty.BOTTOM_MIDDLE) && isPart(aboveBlockState, WallClockPartProperty.LEFT_MIDDLE))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.BOTTOM_LEFT_XL), direction, neighborState, level, pos, neighborPos);
        }

        // Bottom Right XL
        if ((isPart(leftBlockState, WallClockPartProperty.BOTTOM_MIDDLE) && (isPart(facing, aboveBlockState, WallClockPartProperty.BOTTOM_RIGHT) || isPart(facing, aboveBlockState, WallClockPartProperty.BASE)))
                || (isPart(aboveBlockState, WallClockPartProperty.RIGHT_MIDDLE) && (isPart(facing, leftBlockState, WallClockPartProperty.BOTTOM_RIGHT) || isPart(facing, leftBlockState, WallClockPartProperty.BASE)))
                || (isPart(leftBlockState, WallClockPartProperty.BOTTOM_MIDDLE) && isPart(aboveBlockState, WallClockPartProperty.RIGHT_MIDDLE))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.BOTTOM_RIGHT_XL), direction, neighborState, level, pos, neighborPos);
        }

        // Top Left XL
        if ((isPart(rightBlockState, WallClockPartProperty.TOP_MIDDLE) && (isPart(facing, belowBlockState, WallClockPartProperty.TOP_LEFT) || isPart(facing, belowBlockState, WallClockPartProperty.BASE)))
                || (isPart(belowBlockState, WallClockPartProperty.LEFT_MIDDLE) && (isPart(facing, rightBlockState, WallClockPartProperty.TOP_LEFT) || isPart(facing, rightBlockState, WallClockPartProperty.BASE)))
                || (isPart(rightBlockState, WallClockPartProperty.TOP_MIDDLE) && isPart(belowBlockState, WallClockPartProperty.LEFT_MIDDLE))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.TOP_LEFT_XL), direction, neighborState, level, pos, neighborPos);
        }

        // Top Right XL
        if ((isPart(leftBlockState, WallClockPartProperty.TOP_MIDDLE) && (isPart(facing, belowBlockState, WallClockPartProperty.TOP_RIGHT) || isPart(facing, belowBlockState, WallClockPartProperty.BASE)))
                || (isPart(belowBlockState, WallClockPartProperty.RIGHT_MIDDLE) && (isPart(facing, leftBlockState, WallClockPartProperty.TOP_RIGHT) || isPart(facing, leftBlockState, WallClockPartProperty.BASE)))
                || (isPart(leftBlockState, WallClockPartProperty.TOP_MIDDLE) && isPart(belowBlockState, WallClockPartProperty.RIGHT_MIDDLE))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.TOP_RIGHT_XL), direction, neighborState, level, pos, neighborPos);
        }

        // Center
        if (hasParts(2,
                (isPart(aboveBlockState, WallClockPartProperty.TOP_MIDDLE)) && facing == aboveBlockState.getValue(FACING),
                (isPart(rightBlockState, WallClockPartProperty.RIGHT_MIDDLE)) && facing == rightBlockState.getValue(FACING),
                (isPart(belowBlockState, WallClockPartProperty.BOTTOM_MIDDLE)) && facing == belowBlockState.getValue(FACING),
                (isPart(leftBlockState, WallClockPartProperty.LEFT_MIDDLE)) && facing == leftBlockState.getValue(FACING))) {
            return super.updateShape(state.setValue(PART, WallClockPartProperty.CENTER), direction, neighborState, level, pos, neighborPos);
        }

//        ColourfulClocks.LOGGER.info("Could not find valid multiblock for wall clock at {}, setting to BASE", pos);
        return super.updateShape(state.setValue(PART, WallClockPartProperty.BASE), direction, neighborState, level, pos, neighborPos);
//        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    private boolean hasParts(int count, Boolean... parts) {
        int found = 0;
        for (Boolean part : parts) {
            if (part != null && part) found++;
        }
        return found >= count;
    }

    private boolean isPart(Direction facing, BlockState blockState, WallClockPartProperty part) {
        return isWallClockBlock(blockState) && blockState.getValue(PART) == part && blockState.getValue(FACING) == facing;
    }

    private boolean isPart(BlockState blockState, WallClockPartProperty part) {
        return isWallClockBlock(blockState) && blockState.getValue(PART) == part;
    }

    private boolean isWallClockBlock(Direction facing, BlockState blockState) {
        return isWallClockBlock(blockState) && blockState.getValue(FACING) == facing;
    }

    private boolean isWallClockBlock(BlockState blockState) {
        return blockState.getBlock() instanceof WallClockBlock;
    }

    private boolean canConnect(BlockState state) {


        return false;
    }


//    private boolean tryUpdateFullShape(WallClockPartProperty part, BlockState state, LevelAccessor level, BlockPos pos) {
//        for (WallClockPartProperty partProperty : WallClockPartProperty.values()) {
//            if (part == partProperty) continue;
//            BlockPos controllerPos = pos.relative(state.getValue(FACING).getCounterClockWise()).offset(0, -partProperty.getOffset().first().yOffset(), 0);
//            BlockPos partPos = controllerPos.relative(state.getValue(FACING).getClockWise(), partProperty.getOffset().first().xOffset()).offset(0, partProperty.getOffset().first().yOffset(), 0);
//            BlockState blockState = level.getBlockState(partPos);
//            if (blockState.getBlock() instanceof WallClockBlock) {
//                return false;
//            }
//        }
//        return true;
//    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PART);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE.get(state.getValue(FACING));
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
//        BlockEntity blockEntity = level.getBlockEntity(pos);
//        BlockEntity blockEntity = state.getValue(PART) == WallClockPartProperty.BASE || state.getValue(PART) == WallClockPartProperty.BOTTOM_LEFT ?
//                level.getBlockEntity(pos) : getController(state, pos, level);
        BlockEntity blockEntity = getController(state, pos, level);
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
                if (player.isShiftKeyDown() && wallClockBlockEntity.getData().getPocketWatch().isPresent() && wallClockBlockEntity.getData().getPocketWatch().get().getType().getId() != 0) {
                    return removePocketWatch(level, pos, player, wallClockBlockEntity);
                }
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private BlockEntity getController(BlockState state, BlockPos pos, Level level) {
        WallClockPartProperty part = state.getValue(PART);
        if (part.isController()) return level.getBlockEntity(pos);
        Direction facing = state.getValue(FACING);
        int xOffset = part.getxOffset();
        BlockPos partPos = pos.offset(0, -part.getyOffset(), 0);
        if (xOffset != 0) {
            partPos = partPos.relative(xOffset < 0 ? facing.getClockWise() : facing.getCounterClockWise(), Math.abs(xOffset));
        }
        BlockEntity blockEntity = level.getBlockEntity(partPos);
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
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
            return wallClockBlockEntity.getBlockAsItem(this.clockType);
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
        // TODO: test placed implementation, decide data passing
        if (!level.isClientSide) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
                MantelClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getMantelClockData(), MantelClockComponent.getBasicClockValue());
                if (component != null) {
                    wallClockBlockEntity.setData(component);
                }
            }

//            BlockEntity blockEntity = level.getBlockEntity(pos);
//            BlockEntity blockEntity = state.getValue(PART) == WallClockPartProperty.BASE || state.getValue(PART) == WallClockPartProperty.BOTTOM_LEFT ?
//                    level.getBlockEntity(pos) : getController(state, pos, level);
            // the below will pass placed data to the controller, decide implementation
//            BlockEntity blockEntity = getController(state, pos, level);
//            if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
//                MantelClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), MantelClockComponent.getBasicClockValue());
//                if (component != null) {
//                    wallClockBlockEntity.setData(null, component.getPocketWatchType().get(), null, component.getTicking().get());
//                    if (wallClockBlockEntity.getData().getPocketWatchType().get().getId() != 0 && wallClockBlockEntity.getData().getPocketWatchType().get() != component.getPocketWatchType().get()) {
//                        if (component.getPocketWatchType().get() != PocketWatchTypes.EMPTY) {
//                            wallClockBlockEntity.setPocketWatchType(ColourfulClocksTypeUtil.getPocketWatchItemFromType(component.getPocketWatchType().get()).getDefaultInstance());
//                        }
//                    }
//                }
//            }
        }
    }
}
