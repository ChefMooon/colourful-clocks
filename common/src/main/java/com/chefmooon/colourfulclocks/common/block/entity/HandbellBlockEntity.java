package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.apache.commons.lang3.mutable.MutableInt;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HandbellBlockEntity extends BlockEntity {
    private long lastRingTimestamp;
    public int ticks;
    public boolean shaking;
    public Direction clickDirection;
    private List<LivingEntity> nearbyEntities;
    private boolean resonating;
    private int resonationTicks;
    private HandbellComponent handbellComponent;

    public HandbellBlockEntity(BlockPos pos, BlockState blockState) {
        super(BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.HANDBELL), pos, blockState);
        this.handbellComponent = HandbellComponent.getDefaultValue();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.handbellComponent = HandbellComponent.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.handbellComponent.save(tag);
        super.saveAdditional(tag, provider);
    }

    public ItemStack getBlockAsItem(HandbellTypes type) {
        ItemStack itemStack = BuiltInRegistries.ITEM.get(TextUtil.res("handbell").withPrefix( type.getSerializedName() + "_")).getDefaultInstance();
        itemStack.applyComponents(this.collectComponents());
        return itemStack;
    }

    public boolean triggerEvent(int id, int type) {
        if (id == 1) {
            this.updateEntities();
            this.resonationTicks = 0;
            this.clickDirection = Direction.from3DDataValue(type);
            this.ticks = 0;
            this.shaking = true;
            return true;
        } else {
            return super.triggerEvent(id, type);
        }
    }

    private static void tick(Level level, BlockPos pos, BlockState state, HandbellBlockEntity blockEntity, ResonationEndAction resonationEndAction) {
        if (blockEntity.shaking) {
            ++blockEntity.ticks;
        }

        if (blockEntity.ticks >= 50) {
            blockEntity.shaking = false;
            blockEntity.ticks = 0;
        }

        if (blockEntity.ticks >= 5 && blockEntity.resonationTicks == 0 && areRaidersNearby(pos, blockEntity.nearbyEntities)) {
            blockEntity.resonating = true;
            level.playSound((Player)null, pos, SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 1.0F, 2.0F);
        }

        if (blockEntity.resonating) {
            if (blockEntity.resonationTicks < 40) {
                ++blockEntity.resonationTicks;
            } else {
                resonationEndAction.run(level, pos, blockEntity.nearbyEntities);
                blockEntity.resonating = false;
            }
        }

    }

    private void updateEntities() {
        BlockPos blockPos = this.getBlockPos();
        if (this.level.getGameTime() > this.lastRingTimestamp + 60L || this.nearbyEntities == null) {
            this.lastRingTimestamp = this.level.getGameTime();
            AABB aABB = (new AABB(blockPos)).inflate((double)48.0F);
            this.nearbyEntities = this.level.getEntitiesOfClass(LivingEntity.class, aABB);
        }

        if (!this.level.isClientSide) {
            for(LivingEntity livingEntity : this.nearbyEntities) {
                if (livingEntity.isAlive() && !livingEntity.isRemoved() && blockPos.closerToCenterThan(livingEntity.position(), (double)32.0F)) {
                    livingEntity.getBrain().setMemory(MemoryModuleType.HEARD_BELL_TIME, this.level.getGameTime());
                }
            }
        }

    }

    private static boolean areRaidersNearby(BlockPos pos, List<LivingEntity> raiders) {
        for(LivingEntity livingEntity : raiders) {
            if (livingEntity.isAlive() && !livingEntity.isRemoved() && pos.closerToCenterThan(livingEntity.position(), (double)32.0F) && livingEntity.getType().is(EntityTypeTags.RAIDERS)) {
                return true;
            }
        }

        return false;
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, HandbellBlockEntity blockEntity) {
        tick(level, pos, state, blockEntity, HandbellBlockEntity::showBellParticles);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, HandbellBlockEntity blockEntity) {
        tick(level, pos, state, blockEntity, HandbellBlockEntity::makeRaidersGlow);
    }

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, HandbellBlockEntity handbellBlockEntity) {

    }

    public void onHit(Direction direction) {
        BlockPos blockPos = this.getBlockPos();
        this.clickDirection = direction;
        if (this.shaking) {
            this.ticks = 0;
        } else {
            this.shaking = true;
        }

        this.level.blockEvent(blockPos, this.getBlockState().getBlock(), 1, direction.get3DDataValue());
    }

    private static void makeRaidersGlow(Level level, BlockPos pos, List<LivingEntity> raiders) {
        raiders.stream().filter((livingEntity) -> isRaiderWithinRange(pos, livingEntity)).forEach(HandbellBlockEntity::glow);
    }

    private static void showBellParticles(Level level, BlockPos pos, List<LivingEntity> raiders) {
        MutableInt mutableInt = new MutableInt(16700985);
        int i = (int)raiders.stream().filter((livingEntity) -> pos.closerToCenterThan(livingEntity.position(), (double)48.0F)).count();
        raiders.stream().filter((livingEntity) -> isRaiderWithinRange(pos, livingEntity)).forEach((livingEntity) -> {
            float f = 1.0F;
            double d = Math.sqrt((livingEntity.getX() - (double)pos.getX()) * (livingEntity.getX() - (double)pos.getX()) + (livingEntity.getZ() - (double)pos.getZ()) * (livingEntity.getZ() - (double)pos.getZ()));
            double e = (double)((float)pos.getX() + 0.5F) + (double)1.0F / d * (livingEntity.getX() - (double)pos.getX());
            double g = (double)((float)pos.getZ() + 0.5F) + (double)1.0F / d * (livingEntity.getZ() - (double)pos.getZ());
            int j = Mth.clamp((i - 21) / -2, 3, 15);

            for(int k = 0; k < j; ++k) {
                int l = mutableInt.addAndGet(5);
                level.addParticle(ColorParticleOption.create(ParticleTypes.ENTITY_EFFECT, l), e, (double)((float)pos.getY() + 0.5F), g, (double)0.0F, (double)0.0F, (double)0.0F);
            }

        });
    }

    private static boolean isRaiderWithinRange(BlockPos pos, LivingEntity raider) {
        return raider.isAlive() && !raider.isRemoved() && pos.closerToCenterThan(raider.position(), (double)32.0F) && raider.getType().is(EntityTypeTags.RAIDERS);
    }

    private static void glow(LivingEntity entity) {
        entity.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60));
    }

    @FunctionalInterface
    interface ResonationEndAction {
        void run(Level level, BlockPos blockPos, List<LivingEntity> list);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return this.saveWithoutMetadata(provider);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(ColourfulClocksDataComponentTypes.getHandbellData(), this.handbellComponent);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.handbellComponent = componentInput.getOrDefault(ColourfulClocksDataComponentTypes.getHandbellData(), HandbellComponent.getDefaultValue());
    }

    public void setHandbellComponent(HandbellComponent handbellComponent) {
        this.handbellComponent = handbellComponent;
        this.setChanged();
    }

    public HandbellComponent getData() {
        return this.handbellComponent;
    }
}
