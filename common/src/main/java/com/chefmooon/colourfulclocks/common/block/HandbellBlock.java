package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.block.entity.HandbellBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.HandbellHandleTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

public class HandbellBlock extends BaseEntityBlock implements SimpleWaterloggedBlock {
    public static final MapCodec<HandbellBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            HandbellTypes.CODEC.fieldOf("type").forGetter(HandbellBlock::getType), propertiesCodec()).apply(instance, HandbellBlock::new));

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<BellAttachType> ATTACHMENT = BlockStateProperties.BELL_ATTACHMENT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<HandbellHandleTypes> HANDLE_TYPE = ColourfulClocksBlockStateProperties.HANDBELL_HANDLE_TYPE;
    private static final VoxelShape NORTH_SOUTH_FLOOR_SHAPE = Block.box((double)0.0F, (double)0.0F, (double)5.0F, (double)16.0F, (double)16.0F, (double)11.0F);
    private static final VoxelShape EAST_WEST_FLOOR_SHAPE = Block.box((double)5.0F, (double)0.0F, (double)0.0F, (double)11.0F, (double)16.0F, (double)16.0F);
    private static final VoxelShape BELL_TOP_SHAPE = Block.box((double)6.0F, (double)8.0F, (double)6.0F, (double)10.0F, (double)13.0F, (double)10.0F);
    private static final VoxelShape BELL_BOTTOM_SHAPE = Block.box((double)5.0F, (double)7.0F, (double)5.0F, (double)11.0F, (double)8.0F, (double)11.0F);
    private static final VoxelShape BELL_SHAPE = Shapes.or(BELL_BOTTOM_SHAPE, BELL_TOP_SHAPE);
    private static final VoxelShape NORTH_SOUTH_BETWEEN = Shapes.or(BELL_SHAPE, Block.box((double)7.0F, (double)13.0F, (double)0.0F, (double)9.0F, (double)15.0F, (double)16.0F));
    private static final VoxelShape EAST_WEST_BETWEEN = Shapes.or(BELL_SHAPE, Block.box((double)0.0F, (double)13.0F, (double)7.0F, (double)16.0F, (double)15.0F, (double)9.0F));
    private static final VoxelShape TO_WEST = Shapes.or(BELL_SHAPE, Block.box((double)0.0F, (double)13.0F, (double)7.0F, (double)13.0F, (double)15.0F, (double)9.0F));
    private static final VoxelShape TO_EAST = Shapes.or(BELL_SHAPE, Block.box((double)3.0F, (double)13.0F, (double)7.0F, (double)16.0F, (double)15.0F, (double)9.0F));
    private static final VoxelShape TO_NORTH = Shapes.or(BELL_SHAPE, Block.box((double)7.0F, (double)13.0F, (double)0.0F, (double)9.0F, (double)15.0F, (double)13.0F));
    private static final VoxelShape TO_SOUTH = Shapes.or(BELL_SHAPE, Block.box((double)7.0F, (double)13.0F, (double)3.0F, (double)9.0F, (double)15.0F, (double)16.0F));
    private static final VoxelShape CEILING_SHAPE = Shapes.or(BELL_SHAPE, Block.box((double)7.0F, (double)13.0F, (double)7.0F, (double)9.0F, (double)16.0F, (double)9.0F));

    public HandbellTypes type;

    @Override
    public MapCodec<HandbellBlock> codec() {
        return CODEC;
    }

    public HandbellBlock(HandbellTypes type, Properties properties) {
        super(properties);
        this.type = type;
        this.registerDefaultState((BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any())
                .setValue(FACING, Direction.NORTH))
                .setValue(ATTACHMENT, BellAttachType.FLOOR))
                .setValue(POWERED, false)
                .setValue(WATERLOGGED, false)
                .setValue(HANDLE_TYPE, HandbellHandleTypes.OAK));
    }

    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        boolean bl = level.hasNeighborSignal(pos);
        if (bl != (Boolean)state.getValue(POWERED)) {
            if (bl) {
                this.attemptToRing(level, pos, (Direction)null);
            }

            level.setBlock(pos, (BlockState)state.setValue(POWERED, bl), 3);
        }

    }

    protected void onProjectileHit(Level level, BlockState state, BlockHitResult hit, Projectile projectile) {
        Entity entity = projectile.getOwner();
        Player player = entity instanceof Player ? (Player)entity : null;
        this.onHit(level, state, hit, player, true);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return this.onHit(level, state, hitResult, player, true) ? InteractionResult.sidedSuccess(level.isClientSide) : InteractionResult.PASS;
    }

    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack mainHandItem = player.getItemInHand(hand);

        if (mainHandItem.is(ItemTags.AXES)) {
            if (this.tryUseAxeItem(state, level, pos, player, mainHandItem).consumesAction()) {
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        } else if (mainHandItem.is(Items.HONEYCOMB)) {
            if (this.tryUseHoneycombItem(state, level, pos, player, mainHandItem).consumesAction()) {
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public InteractionResult tryUseAxeItem(BlockState state, Level level, BlockPos pos, Player player, ItemStack itemStack) {
        Optional<BlockState> unwaxedState = WeatheringCopperHandbell.getUnwaxed(state);
        if (unwaxedState.isPresent()) {
            BlockState newState = unwaxedState.get();
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
//                ModAdvancements.COPPER_DOUBLE_DOOR_WAX_OFF_TRIGGER.get().trigger(serverPlayer); // TODO : wax off advancement here
            }



            level.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            addParticle(level, pos, ParticleTypes.WAX_OFF);
            level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!player.getAbilities().instabuild) itemStack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(itemStack));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        Optional<BlockState> previousState = WeatheringCopperHandbell.getPrevious(state);
        if (previousState.isPresent()) {
            BlockState newState = previousState.get();
            if (player instanceof ServerPlayer serverPlayer) CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);

            level.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            addParticle(level, pos, ParticleTypes.SCRAPE);
            level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!player.getAbilities().instabuild) itemStack.hurtAndBreak(1, player, player.getEquipmentSlotForItem(itemStack));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    public InteractionResult tryUseHoneycombItem(BlockState state, Level level, BlockPos pos, Player player, ItemStack itemStack) {
        Optional<BlockState> waxedState = WeatheringCopperHandbell.getWaxed(state);
        if (waxedState.isPresent()) {
            BlockState newState = waxedState.get();
            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
//                ModAdvancements.COPPER_DOUBLE_DOOR_WAX_ON_TRIGGER.get().trigger(serverPlayer); // TODO : wax on advancement here
            }
            level.setBlock(pos, newState, Block.UPDATE_ALL_IMMEDIATE);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, newState));
            addParticle(level, pos, ParticleTypes.WAX_ON);
            level.playSound(player, pos, SoundEvents.HONEYCOMB_WAX_ON, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    private void addParticle(Level level, BlockPos pos, ParticleOptions particleData) {
        for (int i = 0; i < 12; i++) {
            double xBase = pos.getX() + 0.5;
            double yBase = pos.getY() + 0.6;
            double zBase = pos.getZ() + 0.5;

            double offsetX = (level.random.nextDouble() - 0.5) * 0.4;
            double offsetY = (level.random.nextDouble() - 0.5) * 0.5;
            double offsetZ = (level.random.nextDouble() - 0.5) * 0.4;

            // Random velocity
            double speed = 0.2 + level.random.nextDouble() * 0.6; // 0.2 to 0.8
            double theta = level.random.nextDouble() * 2 * Math.PI;
            double phi = level.random.nextDouble() * Math.PI;
            double dx = speed * Math.sin(phi) * Math.cos(theta);
            double dy = speed * Math.sin(phi) * Math.sin(theta);
            double dz = speed * Math.cos(phi);

            level.addParticle(particleData, xBase + offsetX, yBase + offsetY, zBase + offsetZ, dx, dy, dz);
        }
    }

    public boolean onHit(Level level, BlockState state, BlockHitResult result, @Nullable Player player, boolean canRingBell) {
        Direction direction = result.getDirection();
        BlockPos blockPos = result.getBlockPos();
        boolean bl = !canRingBell || this.isProperHit(state, direction, result.getLocation().y - (double)blockPos.getY());
        if (bl) {
            boolean bl2 = this.attemptToRing(player, level, blockPos, direction);
//            if (bl2 && player != null) {
//                player.awardStat(Stats.BELL_RING); // TODO: add a new stat?
//            }

            return true;
        } else {
            return false;
        }
    }

    private boolean isProperHit(BlockState pos, Direction direction, double distanceY) {
        if (direction.getAxis() != Direction.Axis.Y && !(distanceY > (double)0.8124F)) {
            Direction direction2 = (Direction)pos.getValue(FACING);
            BellAttachType bellAttachType = (BellAttachType)pos.getValue(ATTACHMENT);
            switch (bellAttachType) {
                case FLOOR:
                    return direction2.getAxis() == direction.getAxis();
                case SINGLE_WALL:
                case DOUBLE_WALL:
                    return direction2.getAxis() != direction.getAxis();
                case CEILING:
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    public HandbellTypes getType() {
        return this.type;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        HandbellComponent handbellComponent = context.getItemInHand().getOrDefault(ColourfulClocksDataComponentTypes.getHandbellData(), HandbellComponent.getDefaultValue());
        Direction direction = context.getClickedFace();
        BlockPos blockPos = context.getClickedPos();
        Level level = context.getLevel();
        Direction.Axis axis = direction.getAxis();
        if (axis == Direction.Axis.Y) {
            BlockState blockState = (BlockState)((BlockState)this.defaultBlockState()
                    .setValue(ATTACHMENT, direction == Direction.DOWN ? BellAttachType.CEILING : BellAttachType.FLOOR)
                    .setValue(FACING, context.getHorizontalDirection())
                    .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER))
                    .setValue(HANDLE_TYPE, handbellComponent.materialType());
            if (blockState.canSurvive(context.getLevel(), blockPos)) {
                return blockState;
            }
        } else {
            boolean bl = axis == Direction.Axis.X && level.getBlockState(blockPos.west()).isFaceSturdy(level, blockPos.west(), Direction.EAST) && level.getBlockState(blockPos.east()).isFaceSturdy(level, blockPos.east(), Direction.WEST) || axis == Direction.Axis.Z && level.getBlockState(blockPos.north()).isFaceSturdy(level, blockPos.north(), Direction.SOUTH) && level.getBlockState(blockPos.south()).isFaceSturdy(level, blockPos.south(), Direction.NORTH);
            BlockState blockState = (BlockState)((BlockState)this.defaultBlockState()
                    .setValue(FACING, direction.getOpposite())).setValue(ATTACHMENT, bl ? BellAttachType.DOUBLE_WALL : BellAttachType.SINGLE_WALL)
                    .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                    .setValue(HANDLE_TYPE, handbellComponent.materialType());
            if (blockState.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockState;
            }

            boolean bl2 = level.getBlockState(blockPos.below()).isFaceSturdy(level, blockPos.below(), Direction.UP);
            blockState = (BlockState)blockState
                    .setValue(ATTACHMENT, bl2 ? BellAttachType.FLOOR : BellAttachType.CEILING)
                    .setValue(WATERLOGGED, fluid.getType() == Fluids.WATER)
                    .setValue(HANDLE_TYPE, handbellComponent.materialType());
            if (blockState.canSurvive(context.getLevel(), context.getClickedPos())) {
                return blockState;
            }
        }

        return null;
    }

    @Override
    protected void onExplosionHit(BlockState state, Level level, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> dropConsumer) {
        if (explosion.canTriggerBlocks()) {
            this.attemptToRing(level, pos, (Direction)null);
        }

        super.onExplosionHit(state, level, pos, explosion, dropConsumer);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        BellAttachType bellAttachType = (BellAttachType)state.getValue(ATTACHMENT);
        Direction direction2 = getConnectedDirection(state).getOpposite();
        if (direction2 == direction && !state.canSurvive(level, pos) && bellAttachType != BellAttachType.DOUBLE_WALL) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (direction.getAxis() == ((Direction)state.getValue(FACING)).getAxis()) {
                if (bellAttachType == BellAttachType.DOUBLE_WALL && !neighborState.isFaceSturdy(level, neighborPos, direction)) {
                    return (BlockState)((BlockState)state.setValue(ATTACHMENT, BellAttachType.SINGLE_WALL)).setValue(FACING, direction.getOpposite());
                }

                if (bellAttachType == BellAttachType.SINGLE_WALL && direction2.getOpposite() == direction && neighborState.isFaceSturdy(level, neighborPos, (Direction)state.getValue(FACING))) {
                    return (BlockState)state.setValue(ATTACHMENT, BellAttachType.DOUBLE_WALL);
                }
            }

            return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        Direction direction = getConnectedDirection(state).getOpposite();
        return direction == Direction.UP ? Block.canSupportCenter(level, pos.above(), Direction.DOWN) : FaceAttachedHorizontalDirectionalBlock.canAttach(level, pos, direction);
    }

    private static Direction getConnectedDirection(BlockState state) {
        switch ((BellAttachType)state.getValue(ATTACHMENT)) {
            case FLOOR -> {
                return Direction.UP;
            }
            case CEILING -> {
                return Direction.DOWN;
            }
            default -> {
                return ((Direction)state.getValue(FACING)).getOpposite();
            }
        }
    }

    public boolean attemptToRing(Level level, BlockPos pos, @Nullable Direction direction) {
        return this.attemptToRing((Entity)null, level, pos, direction);
    }

    public boolean attemptToRing(@Nullable Entity entity, Level level, BlockPos pos, @Nullable Direction direction) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (!level.isClientSide && blockEntity instanceof HandbellBlockEntity) {
            if (direction == null) {
                direction = (Direction)level.getBlockState(pos).getValue(FACING);
            }

            ((HandbellBlockEntity)blockEntity).onHit(direction);
            SoundEvent ringSound = this.type.getRingSound().get();
            level.playSound((Player)null, pos, ringSound, SoundSource.BLOCKS, 2.0F, this.type.getPitch());
            level.gameEvent(entity, GameEvent.BLOCK_CHANGE, pos);
            return true;
        } else {
            return false;
        }
    }

    private VoxelShape getVoxelShape(BlockState state) {
        Direction direction = (Direction)state.getValue(FACING);
        BellAttachType bellAttachType = (BellAttachType)state.getValue(ATTACHMENT);
        if (bellAttachType == BellAttachType.FLOOR) {
            return direction != Direction.NORTH && direction != Direction.SOUTH ? EAST_WEST_FLOOR_SHAPE : NORTH_SOUTH_FLOOR_SHAPE;
        } else if (bellAttachType == BellAttachType.CEILING) {
            return CEILING_SHAPE;
        } else if (bellAttachType == BellAttachType.DOUBLE_WALL) {
            return direction != Direction.NORTH && direction != Direction.SOUTH ? EAST_WEST_BETWEEN : NORTH_SOUTH_BETWEEN;
        } else if (direction == Direction.NORTH) {
            return TO_NORTH;
        } else if (direction == Direction.SOUTH) {
            return TO_SOUTH;
        } else {
            return direction == Direction.EAST ? TO_EAST : TO_WEST;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getVoxelShape(state);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getVoxelShape(state);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ATTACHMENT, POWERED, WATERLOGGED, HANDLE_TYPE);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.HANDBELL).create(pos, state);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        BlockEntityType<HandbellBlockEntity> handbellType =
                (BlockEntityType<HandbellBlockEntity>) Objects.requireNonNull(
                        BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.HANDBELL)
                );
        return createTickerHelper(blockEntityType, handbellType, level.isClientSide ? HandbellBlockEntity::clientTick : HandbellBlockEntity::serverTick);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof HandbellBlockEntity handbellBlockEntity) {
            return handbellBlockEntity.getBlockAsItem(this.type);
        } else {
            return super.getCloneItemStack(level, pos, state);
        }
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof HandbellBlockEntity handbellBlockEntity) {
            handbellBlockEntity.setHandbellComponent(stack.getOrDefault(ColourfulClocksDataComponentTypes.getHandbellData(), HandbellComponent.getDefaultValue()));
        }
    }

    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    public BlockState rotate(BlockState state, Rotation rotation) {
        return (BlockState)state.setValue(FACING, rotation.rotate((Direction)state.getValue(FACING)));
    }

    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.getValue(FACING)));
    }
}
