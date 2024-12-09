package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.block.entity.BornholmMiddleBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.DoorTypeProperty;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.BornholmTypeUtil;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class BornholmMiddleBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<BornholmMiddleBlock> CODEC = simpleCodec(BornholmMiddleBlock::new);
    public static final DoorTypeProperty DOOR_TYPE = ColourfulClocksBlockStateProperties.BORNHOLM_DOOR_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final BooleanProperty ACTIVATED = ColourfulClocksBlockStateProperties.ACTIVATED;
    public WoodTypes woodType;

    public int FLAMMABILITY = 30;
    public int FIRE_SPREAD = 60;

    private static final VoxelShape SHAPE  = Shapes.or(
            Block.box(3, 0, 3, 13, 1, 13),
            Block.box(4, 1, 4, 12, 15, 12),
            Block.box(3, 15, 3, 13, 16, 13)
    );

//    private static final VoxelShape[] DOOR_SHAPES = new VoxelShape[] {
//            Block.box(5, 2, 12, 11, 14, 13),
//            Block.box(3, 2, 5, 4, 14, 11),
//            Block.box(5, 2, 3, 11, 14, 4),
//            Block.box(12, 2, 5, 13, 14, 11)
//    };

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
        this(WoodTypes.OAK, properties);
    }

    public BornholmMiddleBlock(WoodTypes woodTypes, Properties properties) {
        super(properties);
        this.woodType = woodTypes;
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
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection())
                .setValue(ACTIVATED, isActivated(context.getLevel().getBlockState(context.getClickedPos().above()), context.getLevel().getBlockState(context.getClickedPos().below())))
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
        boolean bl2 = blockStateBelow.getBlock() instanceof BornholmBaseBlock;

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
        if (blockEntity instanceof BornholmMiddleBlockEntity block) {
            ItemStack mainHandItem = player.getMainHandItem();
            ItemStack offHandItem = player.getOffhandItem();
            if (mainHandItem.isEmpty() && !offHandItem.isEmpty()) {
                mainHandItem = offHandItem;
            }

            if (!mainHandItem.isEmpty()) {
                if (mainHandItem.is(woodType.getItem())) {
                    if (state.getValue(DOOR_TYPE) == BornholmDoorTypes.BASE) return toggleDoor(level, state, pos, player);
                    level.setBlockAndUpdate(pos, state.setValue(DOOR_TYPE, BornholmDoorTypes.BASE));
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHANGE_WOOD.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
                    if (!player.getAbilities().instabuild) mainHandItem.shrink(1);

                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_DOOR)) {
                    if (mainHandItem.is(state.getValue(DOOR_TYPE).getItem())) return toggleDoor(level, state, pos, player);
                    BornholmDoorTypes newBornholmDoorTypes = BornholmTypeUtil.getTypeFromItem(mainHandItem.getItem());
                    level.setBlockAndUpdate(pos, state.setValue(DOOR_TYPE, newBornholmDoorTypes));
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHANGE_GLASS.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
                    if (!player.getAbilities().instabuild) mainHandItem.shrink(1);

                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(ColourfulClocksTags.CLOCK_PENDULUM) && state.getValue(OPEN)) {
                    if (!block.getPendelumItem().isEmpty()) {
                        if (mainHandItem.is(block.getPendelumItem().getItem())) {
                            return toggleDoor(level, state, pos, player);
                        } else if (!player.getAbilities().instabuild) {
                            if (!player.getInventory().add(block.removeItem(0, 1))) {
                                Containers.dropContents(level, pos, block.getDroppableInventory());
                            }
                        }
                    }

                    block.setPendelumItem(player.getAbilities().instabuild ? mainHandItem.copy() : mainHandItem);
                    level.blockEntityChanged(pos);
                    level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_INSERT_PENDULUM.get(), SoundSource.BLOCKS, 0.8F, 0.5F);

                    return ItemInteractionResult.SUCCESS;
                } else if (mainHandItem.is(Items.HONEYCOMB)) {
                    ItemStack waxedPendulum = new ItemStack(getWaxedCopperPendulum(block.getPendelumItem()).get());
                    if (!waxedPendulum.isEmpty()) {
                        block.setPendelumItem(waxedPendulum);
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_ON.get(), SoundSource.BLOCKS, 1.0F, 0.9F);
                        if (!player.getAbilities().instabuild) mainHandItem.shrink(1);

                        return ItemInteractionResult.SUCCESS;
                    } else {
                        return toggleDoor(level, state, pos, player);
                    }
                } else if (mainHandItem.is(ItemTags.AXES)) {
                    Pair<Supplier<Item>, Supplier<SoundEvent>> pendulumInfo = getScrapedCopperPendulum(block.getPendelumItem());
                    ItemStack scrapedPendulum = new ItemStack(pendulumInfo.getFirst().get());
                    if (!scrapedPendulum.isEmpty()) {
                        block.setPendelumItem(scrapedPendulum);
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, pendulumInfo.getSecond().get(), SoundSource.BLOCKS, 0.8F, 0.9F);
                        if (!player.getAbilities().instabuild) mainHandItem.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

                        return ItemInteractionResult.SUCCESS;
                    } else {
                        return toggleDoor(level, state, pos, player);
                    }
                } else {
                    return toggleDoor(level, state, pos, player);
                }
            } else {
                if (player.isShiftKeyDown() && state.getValue(OPEN)) {
                    ItemStack pendulum = block.getPendelumItem();
                    if (!pendulum.isEmpty()) {
                        if (player.isCreative()) {
                            block.removeItem(0, 1);
                        } else if (!player.getInventory().add(block.removeItem(0, 1))) {
                            Containers.dropContents(level, pos, block.getDroppableInventory());
                        }
                        level.blockEntityChanged(pos);
                        level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_REMOVE_PENDULUM.get(), SoundSource.BLOCKS, 0.8F, 0.7F);

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
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
                ItemStack item = bornholmMiddleBlockEntity.getPendelumItem();
                if (!item.isEmpty()) {
                    Containers.dropContents(level, pos, bornholmMiddleBlockEntity.getDroppableInventory());
                }
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
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
        }
        super.onExplosionHit(blockState, level, blockPos, explosion, biConsumer);
    }

    @ExpectPlatform
    public static Supplier<Item> getWaxedCopperPendulum(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Pair<Supplier<Item>, Supplier<SoundEvent>> getScrapedCopperPendulum(ItemStack itemStack) {
        throw new AssertionError();
    }
}
