package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.base.BaseWallClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.WallClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.properties.WallClockPartProperty;
import com.chefmooon.colourfulclocks.common.data.WallClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.WallClockType;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.VoxelShapeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
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
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class WallClockBlock extends BaseWallClockBlock {
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
        WallClockComponent component = context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getWallClockData(), WallClockComponent.getDefaultValue());
        return this.defaultBlockState().setValue(FACING, facing)
                .setValue(PART, WallClockPartProperty.BASE)
                .setValue(ACTIVATED, Boolean.TRUE)
                .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                .setValue(CAN_TICK,  component.isTicking().get())
                .setValue(TICKING, Boolean.FALSE);
    }

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
        BlockPos controllerPos = getControllerPos(state, pos);
        BlockEntity blockEntity = level.getBlockEntity(controllerPos);
        if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
            ItemStack mainHandItem = player.getItemInHand(hand);
            if (!mainHandItem.isEmpty()) {
                if (mainHandItem.is(ColourfulClocksTags.CLOCK_HAND)) {
                    return setPocketWatchType(level, pos, player, mainHandItem, wallClockBlockEntity);
                } else if (mainHandItem.is(Items.HONEYCOMB)) {
                    return setWaxedState(state, level, pos, controllerPos, player, mainHandItem, wallClockBlockEntity, true);
                } else if (mainHandItem.is(ItemTags.AXES)) {
                    return setWaxedState(state, level, pos, controllerPos, player, mainHandItem, wallClockBlockEntity, false);
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

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return Objects.requireNonNull(BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.WALL_CLOCK)).create(pos, state);
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
        BlockEntity blockEntity = level.getBlockEntity(getControllerPos(state, pos));
        if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
            WallClockComponent component = wallClockBlockEntity.getData();
            ResourceLocation clockLocation = getClockLocation(component);
            ItemStack itemStack = BuiltInRegistries.ITEM.get(clockLocation).getDefaultInstance();
            itemStack.applyComponents(wallClockBlockEntity.collectComponents());
            return itemStack;
        } else {
            return super.getCloneItemStack(level, pos, state);
        }
    }

    @Override
    public void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        if (!state.isAir() && explosion.getBlockInteraction() != Explosion.BlockInteraction.TRIGGER_BLOCK) {
            Block block = state.getBlock();
            boolean bl = explosion.getIndirectSourceEntity() instanceof Player;
            if (block.dropFromExplosion(explosion) && level instanceof ServerLevel serverLevel) {
                BlockEntity blockEntity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
                LootParams.Builder builder = (new LootParams.Builder(serverLevel)).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos)).withParameter(LootContextParams.TOOL, ItemStack.EMPTY).withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockEntity).withOptionalParameter(LootContextParams.THIS_ENTITY, explosion.getDirectSourceEntity());
                if (explosion.getBlockInteraction() == Explosion.BlockInteraction.DESTROY_WITH_DECAY) {
                    builder.withParameter(LootContextParams.EXPLOSION_RADIUS, explosion.radius());
                }

                destroy(level, pos, state, true, null);
                state.spawnAfterBreak(serverLevel, pos, ItemStack.EMPTY, bl);
            }

            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            block.wasExploded(level, pos, explosion);
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (!level.isClientSide) {
            if (stack.has(ColourfulClocksDataComponentTypes.getWallClockData())) {
                WallClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getWallClockData(), WallClockComponent.getDefaultValue());
                WallClockType type = component.getType();
                if (type != null) {
                    if (type == WallClockType.SMALL) {
                        setPlacedBySmall(level, pos, component);
                    } else if (type == WallClockType.MEDIUM) {
                        setPlacedByMedium(level, pos, state, component);
                    } else if (type == WallClockType.LARGE) {
                        setPlacedByLarge(level, pos, state, component);
                    }
                }
            }
        }
        super.setPlacedBy(level, pos, state, placer, stack);
    }

    private void setPlacedBySmall(Level level, BlockPos pos, WallClockComponent component) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
            if (component != null) {
                wallClockBlockEntity.setData(component);
            }
        }
    }

    private void setPlacedByMedium(Level level, BlockPos pos, BlockState state, WallClockComponent component) {
        Direction facing = state.getValue(FACING);
        for (WallClockPartProperty part : WallClockPartProperty.mediumParts()) {
            BlockPos partPos = offsetPosForPart(pos.above().relative(facing.getClockWise()), facing, part);
            boolean isWaterlogged = level.getFluidState(partPos).getType() == Fluids.WATER;
            level.setBlock(partPos, state.setValue(PART, part).setValue(WATERLOGGED, isWaterlogged), Block.UPDATE_ALL);
            if (part.isController()) {
                setControllerData(level, partPos, component);
            } else {
                setSupportingClockData(level, partPos, component);
            }
        }
    }

    private void setPlacedByLarge(Level level, BlockPos pos, BlockState state, WallClockComponent component) {
        Direction facing = state.getValue(FACING);
        for (WallClockPartProperty part : WallClockPartProperty.largeParts()) {
            BlockPos partPos = offsetPosForPart(pos, facing, part);
            boolean isWaterlogged = level.getFluidState(partPos).getType() == Fluids.WATER;
            level.setBlock(partPos, state.setValue(PART, part).setValue(WATERLOGGED, isWaterlogged), Block.UPDATE_ALL);
            if (part.isController()) {
                setControllerData(level, partPos, component);
            } else {
                setSupportingClockData(level, partPos, component);
            }
        }
    }

    private static void setControllerData(Level level, BlockPos pos, WallClockComponent component) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
            if (component != null) {
                wallClockBlockEntity.setData(component);
            }
        }
    }

    private static void setSupportingClockData(Level level, BlockPos pos, WallClockComponent component) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
            if (component != null) {
                wallClockBlockEntity.setSupportingClockData(component);
            }
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        destroy(level, pos, state, !level.isClientSide && !player.getAbilities().instabuild, player);
        return super.playerWillDestroy(level, pos, state, player);
    }

    private void destroy(Level level, BlockPos pos, BlockState state, boolean dropBlock, @Nullable Player player) {
        if (!state.is(this)) return;

        BlockPos controllerPos = getControllerPos(state, pos);
        BlockState controllerState = level.getBlockState(controllerPos);

        WallClockComponent component = WallClockComponent.getDefaultValue();
        BlockEntity controllerEntity = level.getBlockEntity(controllerPos);
        if (controllerEntity instanceof WallClockBlockEntity controllerClockEntity) {
            component = controllerClockEntity.collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getWallClockData(), WallClockComponent.getDefaultValue());
        }

        if (dropBlock && component != null) {
            getWallClockDrops(level, controllerPos, controllerEntity, component, player);
        }

        if (controllerState.getBlock() instanceof WallClockBlock) {
            Direction facing = controllerState.getValue(FACING);

            Set<WallClockPartProperty> partsToClear = switch (component.type()) {
                case SMALL -> WallClockPartProperty.smallParts();
                case MEDIUM -> WallClockPartProperty.mediumParts();
                case LARGE -> WallClockPartProperty.largeParts();
            };

            for (WallClockPartProperty partProp : partsToClear) {
                BlockPos partPos = offsetPosForPart(controllerPos, facing, partProp);
                BlockState partState = level.getBlockState(partPos);
                if (partState.getBlock() instanceof WallClockBlock) {
                    if (partState.getValue(PART) == partProp) {
                        level.setBlock(partPos, partState.getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 35);
                        if (player != null) {
                            level.levelEvent(player, 2001, partPos, Block.getId(partState));
                        }
                    }
                }
            }
        } else {
            level.setBlock(controllerPos, state.getValue(WATERLOGGED) ? Blocks.WATER.defaultBlockState() : Blocks.AIR.defaultBlockState(), 35);
            if (player != null) level.levelEvent(player, 2001, controllerPos, Block.getId(controllerState));
        }
    }

    private void getWallClockDrops(Level level, BlockPos controllerPos, BlockEntity controllerEntity, WallClockComponent component, @Nullable Player player) {
        ResourceLocation clockLocation = getClockLocation(component);
        if (clockLocation != null && !level.isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) level;
            boolean hasSilkTouch = player != null && EnchantmentHelper.getItemEnchantmentLevel(serverLevel.registryAccess().registry(Registries.ENCHANTMENT).get().getHolderOrThrow(Enchantments.SILK_TOUCH), player.getMainHandItem()) > 0;
            ItemStack drop = BuiltInRegistries.ITEM.get(clockLocation).getDefaultInstance();
            if (hasSilkTouch) {
                drop.set(ColourfulClocksDataComponentTypes.getWallClockData(), component);
            } else if (controllerEntity instanceof WallClockBlockEntity wallClockBlockEntity) {
                Containers.dropContents(level, controllerPos, wallClockBlockEntity.getDroppableInventory());
            }
            Containers.dropItemStack(level, controllerPos.getX(), controllerPos.getY(), controllerPos.getZ(), drop);
        }
    }

    private ResourceLocation getClockLocation(WallClockComponent component) {
        ResourceLocation clockLocation = null;
        if (component.type() == WallClockType.SMALL) {
            clockLocation = ColourfulClocksBlocks.WALL_CLOCK.withSuffix(clockType.getSerializedName());
        } else if (component.type() == WallClockType.MEDIUM) {
            clockLocation = ColourfulClocksBlocks.WALL_CLOCK.withSuffix(clockType.getSerializedName() + "_medium");
        } else if (component.type() == WallClockType.LARGE) {
            clockLocation = ColourfulClocksBlocks.WALL_CLOCK.withSuffix(clockType.getSerializedName() + "_large");
        }
        return clockLocation;
    }

    private BlockPos offsetPosForPart(BlockPos controllerPos, Direction facing, WallClockPartProperty part) {
        int xOffset = part.getxOffset();
        int yOffset = part.getyOffset();

        BlockPos pos = controllerPos;
        if (xOffset != 0) {
            Direction horizontal = xOffset > 0 ? facing.getClockWise() : facing.getCounterClockWise();
            pos = pos.relative(horizontal, Math.abs(xOffset));
        }
        if (yOffset != 0) {
            pos = pos.above(yOffset);
        }
        return pos;
    }

    private BlockPos getControllerPos(BlockState state, BlockPos blockPos) {
        WallClockPartProperty part = state.getValue(PART);
        BlockPos controllerPos = blockPos;
        if (!part.isController()) {
            Direction facing = state.getValue(FACING);
            int xOffset = part.getxOffset();
            if (xOffset != 0) {
                Direction horizontal = xOffset > 0 ? facing.getCounterClockWise() : facing.getClockWise();
                controllerPos = controllerPos.relative(horizontal, Math.abs(xOffset));
            }
            controllerPos = controllerPos.offset(0, -part.getyOffset(), 0);
        }
        return controllerPos;
    }

    public BlockEntity getController(BlockState state, BlockPos blockPos, Level level) {
        return level.getBlockEntity(getControllerPos(state, blockPos));
    }
}
