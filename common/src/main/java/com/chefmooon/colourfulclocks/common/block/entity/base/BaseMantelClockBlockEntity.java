package com.chefmooon.colourfulclocks.common.block.entity.base;

import com.chefmooon.colourfulclocks.common.block.TallMantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.MantelClockComponent;
import com.chefmooon.colourfulclocks.common.data.PendulumComponent;
import com.chefmooon.colourfulclocks.common.data.PocketWatchComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.CopperWeatheringUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BaseMantelClockBlockEntity extends BlockEntity {
    private boolean hasChimed = false;
    private MantelClockComponent clockData;
    public BaseMantelClockBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.clockData = MantelClockComponent.getDefaultValue();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.clockData = MantelClockComponent.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.clockData.save(tag);
        super.saveAdditional(tag, provider);
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
        components.set(ColourfulClocksDataComponentTypes.getMantelClockData(), this.clockData);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.clockData = componentInput.getOrDefault(ColourfulClocksDataComponentTypes.getMantelClockData(), this.clockData);
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.clockData.getPocketWatch().isPresent() && this.clockData.getPocketWatch().get().getType().getId() != 0) {
            ItemStack pocketWatch = BuiltInRegistries.ITEM.get(TextUtil.res(this.clockData.getPocketWatch().get().getType().getSerializedName() + "_pocket_watch")).getDefaultInstance();
            pocketWatch.set(ColourfulClocksDataComponentTypes.getPocketWatchData(), this.clockData.getPocketWatch().get());
            drops.add(pocketWatch);
        }
        if (this.clockData.getPendulum().isPresent() && this.clockData.getPendulum().get().getType().getId() != 0) {
            ItemStack pendulum = BuiltInRegistries.ITEM.get(TextUtil.res(this.clockData.getPendulum().get().getType().getSerializedName() + "_pendulum")).getDefaultInstance();
            pendulum.set(ColourfulClocksDataComponentTypes.getPendulumData(), this.clockData.getPendulum().get());
            drops.add(pendulum);
        }
        return drops;
    }

    public boolean isEmpty() {
        return this.clockData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()).getType().getId() == 0 || this.clockData.getPendulum().orElse(PendulumComponent.getDefaultValue()).getType().getId() == 0;
    }

    public void setPocketWatch(ItemStack itemStack) {
        setPocketWatch(itemStack.get(ColourfulClocksDataComponentTypes.getPocketWatchData()));
    }

    public void setPocketWatch(PocketWatchComponent component) {
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), component, this.clockData.getPendulum().orElse(PendulumComponent.getDefaultValue()), this.clockData.getTicking().orElse(Boolean.FALSE));
    }

    public PocketWatchComponent removePocketWatch() {
        PocketWatchComponent removed = this.clockData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue());
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), PocketWatchComponent.getDefaultValue(), this.clockData.getPendulum().orElse(PendulumComponent.getDefaultValue()), this.clockData.getTicking().orElse(Boolean.FALSE));
        return removed;
    }

    public void setPendulum(ItemStack itemStack) {
        setPendulum(itemStack.get(ColourfulClocksDataComponentTypes.getPendulumData()));
    }

    public void setPendulum(PendulumComponent component) {
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), this.clockData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()), component, this.clockData.getTicking().orElse(Boolean.FALSE));
    }

    public PendulumComponent removePendulum() {
        PendulumComponent removed = this.clockData.getPendulum().orElse(PendulumComponent.getDefaultValue());
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), this.clockData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()), PendulumComponent.getDefaultValue(), this.clockData.getTicking().orElse(Boolean.FALSE));
        return removed;
    }

    public void setGlassType(BornholmTopGlassTypes glassType) {
        setData(glassType, this.clockData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.clockData.getPendulum().orElse(PendulumComponent.getDefaultValue()), this.clockData.getTicking().orElse(Boolean.FALSE));
    }

    public void setTicking(boolean ticking) {
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), this.clockData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.clockData.getPendulum().orElse(PendulumComponent.getDefaultValue()), ticking);
    }

    public void setData(@Nullable BornholmTopGlassTypes glassType, @Nullable PocketWatchComponent pocketWatchType, @Nullable PendulumComponent pendulumType, @Nullable Boolean ticking) {
        this.clockData = new MantelClockComponent(
                glassType != null ? Optional.of(glassType) : Optional.empty(),
                pocketWatchType != null ? Optional.of(pocketWatchType) : Optional.empty(),
                pendulumType != null ? Optional.of(pendulumType) : Optional.empty(),
                ticking != null ? Optional.of(ticking) : Optional.empty());
        setChanged();
    }

    public void setData(MantelClockComponent component) {
        this.clockData = component;
        setChanged();
    }

    public MantelClockComponent getData() {
        return this.clockData;
    }

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, BaseMantelClockBlockEntity baseMantelClockBlockEntity) {
        if (blockState.getValue(TallMantelClockBlock.ACTIVATED)) {
            weatherItem(level, blockPos, baseMantelClockBlockEntity);
            if (baseMantelClockBlockEntity.getData().getPendulum().isPresent() && baseMantelClockBlockEntity.getData().getPendulum().get().getType().getId() != 0) baseMantelClockBlockEntity.sound(level, blockPos, baseMantelClockBlockEntity);
        }
        if (baseMantelClockBlockEntity.getData().pocketWatch().orElse(PocketWatchComponent.getDefaultValue()).getType().getId() != 0 && blockState.getValue(ColourfulClocksBlockStateProperties.TICKING)
                && blockState.getValue(ColourfulClocksBlockStateProperties.ACTIVATED)) {
            tickSound(level, blockPos);
        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, BaseMantelClockBlockEntity baseMantelClockBlockEntity) {
        if (baseMantelClockBlockEntity.getData().getPocketWatch().isPresent()) {
            PocketWatchComponent pocketWatchComponent = baseMantelClockBlockEntity.getData().getPocketWatch().get();
            if (pocketWatchComponent.getWeathering().isPresent() && ColourfulClocksTypeUtil.pocketWatchCanWeather(pocketWatchComponent)) {
                int weathering = pocketWatchComponent.getWeathering().get();
                if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                    advancePocketWatchWeathering(level, blockPos, pocketWatchComponent, baseMantelClockBlockEntity);
                } else {
                    baseMantelClockBlockEntity.setPocketWatch(new PocketWatchComponent(pocketWatchComponent.getType(), Optional.of(weathering + 1)));
                }
            }
        }

        if (baseMantelClockBlockEntity.getData().getPendulum().isPresent()) {
            PendulumComponent pendulumComponent = baseMantelClockBlockEntity.getData().getPendulum().get();
            if (pendulumComponent.getWeathering().isPresent() && ColourfulClocksTypeUtil.pendulumCanWeather(pendulumComponent)) {
                int weathering = pendulumComponent.getWeathering().get();
                if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                    advancePendulumWeathering(level, blockPos, pendulumComponent, baseMantelClockBlockEntity);
                } else {
                    baseMantelClockBlockEntity.setPendulum(new PendulumComponent(pendulumComponent.getType(), Optional.of(weathering + 1)));
                }
            }
        }
    }

    protected static void advancePocketWatchWeathering(Level level, BlockPos blockPos, PocketWatchComponent pocketWatchComponent, BaseMantelClockBlockEntity baseMantelClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperPocketWatch(pocketWatchComponent));
        if (!weatheredItemStack.isEmpty()) {
            baseMantelClockBlockEntity.setPocketWatch(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            baseMantelClockBlockEntity.setChanged();
            if (!level.isClientSide()) {
                BlockState state = level.getBlockState(blockPos);
                level.sendBlockUpdated(blockPos, state, state, 3);
            }
        }
    }

    protected static void advancePendulumWeathering(Level level, BlockPos blockPos, PendulumComponent pendulumComponent, BaseMantelClockBlockEntity baseMantelClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperPendulum(pendulumComponent));
        if (!weatheredItemStack.isEmpty()) {
            baseMantelClockBlockEntity.setPendulum(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            baseMantelClockBlockEntity.setChanged();
            if (!level.isClientSide()) {
                BlockState state = level.getBlockState(blockPos);
                level.sendBlockUpdated(blockPos, state, state, 3);
            }
        }
    }

    protected void sound(Level level, BlockPos blockPos, BaseMantelClockBlockEntity baseMantelClockBlockEntity) {
        if (level == null || level.isClientSide()) return;

        PendulumTypes pendulumType = baseMantelClockBlockEntity.getData().getPendulum().orElse(PendulumComponent.getDefaultValue()).getType();

        long timeOfDay = level.getDayTime() % 24000;

        if ((timeOfDay == 6000 || timeOfDay == 18000) && !hasChimed) {
            level.playSound(null, blockPos, pendulumType.getChimeSound().get(), SoundSource.BLOCKS, 0.6F, pendulumType.getPitchModifier()); // TODO: decide volume original from grandfather clock 1.0F
            hasChimed = true;
        } else if (timeOfDay == 6001 || timeOfDay == 18001) {
            hasChimed = false;
        }
    }

    protected static void tickSound(Level level, BlockPos blockPos) {
        if (level == null || level.isClientSide()) return;

        float timeOfDay = (level.getDayTime()) % 24000;
        float segmentTime = timeOfDay % 750.0F;
        float stepLength = 750.0F / 16.0F;
        if (Math.abs(segmentTime % stepLength) < 1.0F) {
            level.playSound(null, blockPos, ColourfulClocksSounds.BLOCK_CLOCK_TICK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }
}
