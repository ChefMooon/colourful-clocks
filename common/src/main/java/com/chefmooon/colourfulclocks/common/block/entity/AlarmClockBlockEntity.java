package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.AlarmClockComponent;
import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.PocketWatchComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
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
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class AlarmClockBlockEntity extends BlockEntity {
    private AlarmClockComponent alarmClockData;
    private boolean hasChimed = false;
    public AlarmClockBlockEntity(BlockPos pos, BlockState blockState) {
        super(BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.ALARM_CLOCK), pos, blockState);
        this.alarmClockData = AlarmClockComponent.getDefaultValue();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.alarmClockData = AlarmClockComponent.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.alarmClockData.save(tag);
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
        components.set(ColourfulClocksDataComponentTypes.getAlarmClockData(), this.alarmClockData);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.alarmClockData = componentInput.getOrDefault(ColourfulClocksDataComponentTypes.getAlarmClockData(), this.alarmClockData);
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.alarmClockData.leftBell().isPresent()) {
            ItemStack leftBell = BuiltInRegistries.ITEM.get(TextUtil.res(this.alarmClockData.leftBell().get().getType().getSerializedName() + "_handbell")).getDefaultInstance();
            leftBell.set(ColourfulClocksDataComponentTypes.getHandbellData(), this.alarmClockData.leftBell().get());
            drops.add(leftBell);
        }
        if (this.alarmClockData.rightBell().isPresent()) {
            ItemStack rightBell = BuiltInRegistries.ITEM.get(TextUtil.res(this.alarmClockData.rightBell().get().getType().getSerializedName() + "_handbell")).getDefaultInstance();
            rightBell.set(ColourfulClocksDataComponentTypes.getHandbellData(), this.alarmClockData.rightBell().get());
            drops.add(rightBell);
        }
        if (this.alarmClockData.pocketWatch().isPresent()) {
            if (this.alarmClockData.pocketWatch().get().type().getId() != 0) {
                ItemStack pocketWatch = BuiltInRegistries.ITEM.get(TextUtil.res(this.alarmClockData.pocketWatch().get().type().getSerializedName())).getDefaultInstance();
                pocketWatch.set(ColourfulClocksDataComponentTypes.getPocketWatchData(), this.alarmClockData.pocketWatch().get());
                drops.add(pocketWatch);
            }
        }
        return drops;
    }

    public boolean isEmpty() {
        return this.alarmClockData.leftBell().isPresent() || this.alarmClockData.rightBell().isPresent() || this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()).type().getId() == 0;
    }

    public void setLeftHandbellType(ItemStack itemStack) {
        setData(itemStack.get(ColourfulClocksDataComponentTypes.getHandbellData()), this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public void setLeftHandbellType(HandbellComponent component) {
        setData(component, this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public HandbellComponent removeLeftHandbellType() {
        HandbellComponent removed = this.alarmClockData.leftBell().orElse(HandbellComponent.getDefaultValue());
        setData(null, this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.alarmClockData.ticking().orElse(Boolean.FALSE));
        return removed;
    }

    public void setRightHandbellType(HandbellComponent component) {
        setData(this.alarmClockData.leftBell().orElse(null), component, this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public void setRightHandbellType(ItemStack itemStack) {
        setData(this.alarmClockData.leftBell().orElse(null), itemStack.get(ColourfulClocksDataComponentTypes.getHandbellData()), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public HandbellComponent removeRightHandbellType() {
        HandbellComponent removed = this.alarmClockData.rightBell().orElse(HandbellComponent.getDefaultValue());
        setData(this.alarmClockData.leftBell().orElse(null), null, this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.alarmClockData.ticking().orElse(Boolean.FALSE));
        return removed;
    }

    public void setGlassType(BornholmTopGlassTypes glassType) {
        setData(this.alarmClockData.leftBell().orElse(null), this.alarmClockData.rightBell().orElse(null), glassType, this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public void setPocketWatch(ItemStack itemStack) {
        setPocketWatch(itemStack.getOrDefault(ColourfulClocksDataComponentTypes.getPocketWatchData(), PocketWatchComponent.getDefaultValue()));
    }

    public void setPocketWatch(PocketWatchComponent component) {
        setData(this.alarmClockData.leftBell().orElse(null), this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), component, this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public PocketWatchComponent removePocketWatch() {
        PocketWatchComponent removed = this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue());
        setData(this.alarmClockData.leftBell().orElse(null), this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), PocketWatchComponent.getDefaultValue(), this.alarmClockData.ticking().orElse(Boolean.FALSE));
        return removed;
    }

    public void setTicking(boolean ticking) {
        setData(this.alarmClockData.leftBell().orElse(null), this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()), ticking);
    }

    public void setData(@Nullable HandbellComponent leftBell, @Nullable HandbellComponent rightBell, @Nullable BornholmTopGlassTypes glassType, @Nullable PocketWatchComponent pocketWatchType, @Nullable Boolean ticking) {
        this.alarmClockData = new AlarmClockComponent(
                leftBell != null ? Optional.of(leftBell) : Optional.empty(),
                rightBell != null ? Optional.of(rightBell) : Optional.empty(),
                glassType != null ? Optional.of(glassType) : Optional.empty(),
                pocketWatchType != null ? Optional.of(pocketWatchType) : Optional.empty(),
                ticking != null ? Optional.of(ticking) : Optional.empty());
        setChanged();
    }

    public void setData(AlarmClockComponent component) {
        this.alarmClockData = component;
        setChanged();
    }

    public AlarmClockComponent getData() {
        return this.alarmClockData;
    }

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, AlarmClockBlockEntity alarmClockBlockEntity) {
        weatherItem(level, blockPos, alarmClockBlockEntity);
        AlarmClockComponent data = alarmClockBlockEntity.getData();
        if (data.pocketWatch().orElse(PocketWatchComponent.getDefaultValue()).getType().getId() != 0 && blockState.getValue(ColourfulClocksBlockStateProperties.TICKING)) {
            tickSound(level, blockPos);
        }
        if (data.pocketWatch().isPresent() && data.pocketWatch().get().getType() != PocketWatchTypes.EMPTY && (data.leftBell().isPresent() || data.rightBell().isPresent())) {
            alarmClockBlockEntity.sound(level, blockPos, alarmClockBlockEntity);
        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, AlarmClockBlockEntity alarmClockBlockEntity) {
        if (alarmClockBlockEntity.getData().leftBell().isPresent()) {
            HandbellComponent leftBellComponent = alarmClockBlockEntity.getData().leftBell().get();
            if (leftBellComponent.getWeathering().isPresent() && ColourfulClocksTypeUtil.bellCanWeather(leftBellComponent)) {
                int weathering = leftBellComponent.getWeathering().get();
                if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                    advanceBellWeathering(level, blockPos, leftBellComponent, alarmClockBlockEntity, true);
                } else {
                    HandbellComponent updatedLeftBellComponent = new HandbellComponent(leftBellComponent.getType(), leftBellComponent.getMaterialType(), Optional.of(weathering + 1));
                    alarmClockBlockEntity.setLeftHandbellType(updatedLeftBellComponent);
                }
            }
        }

        if (alarmClockBlockEntity.getData().rightBell().isPresent()) {
            HandbellComponent rightBellComponent = alarmClockBlockEntity.getData().rightBell().get();
            if (rightBellComponent.getWeathering().isPresent() && ColourfulClocksTypeUtil.bellCanWeather(rightBellComponent)) {
                int weathering = rightBellComponent.getWeathering().get();
                if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                    advanceBellWeathering(level, blockPos, rightBellComponent, alarmClockBlockEntity, false);
                } else {
                    HandbellComponent updatedRightBellComponent = new HandbellComponent(rightBellComponent.getType(), rightBellComponent.getMaterialType(), Optional.of(weathering + 1));
                    alarmClockBlockEntity.setRightHandbellType(updatedRightBellComponent);
                }
            }
        }

        if (alarmClockBlockEntity.getData().pocketWatch().isPresent()) {
            PocketWatchComponent pocketWatchComponent = alarmClockBlockEntity.getData().pocketWatch().get();
            if (pocketWatchComponent.weathering().isPresent() && ColourfulClocksTypeUtil.pocketWatchCanWeather(pocketWatchComponent)) {
                int weathering = pocketWatchComponent.weathering().get();
                if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                    advancePocketWatchWeathering(level, blockPos, pocketWatchComponent, alarmClockBlockEntity);
                } else {
                    alarmClockBlockEntity.setPocketWatch(new PocketWatchComponent(pocketWatchComponent.type(), Optional.of(weathering + 1)));
                }
            }
        }
    }

    protected static void advancePocketWatchWeathering(Level level, BlockPos blockPos, PocketWatchComponent pocketWatchComponent, AlarmClockBlockEntity alarmClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperPocketWatch(pocketWatchComponent));
        if (!weatheredItemStack.isEmpty()) {
            alarmClockBlockEntity.setPocketWatch(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            alarmClockBlockEntity.setChanged();
            if (!level.isClientSide()) {
                BlockState state = level.getBlockState(blockPos);
                level.sendBlockUpdated(blockPos, state, state, 3);
            }
        }
    }

    protected static void advanceBellWeathering(Level level, BlockPos blockPos, HandbellComponent handbellComponent, AlarmClockBlockEntity alarmClockBlockEntity, boolean isLeft) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperBell(handbellComponent));
        if (!weatheredItemStack.isEmpty()) {
            if (isLeft) {
                alarmClockBlockEntity.setLeftHandbellType(weatheredItemStack);
            } else {
                alarmClockBlockEntity.setRightHandbellType(weatheredItemStack);
            }
            level.blockEntityChanged(blockPos);
            alarmClockBlockEntity.setChanged();
            if (!level.isClientSide()) {
                BlockState state = level.getBlockState(blockPos);
                level.sendBlockUpdated(blockPos, state, state, 3);
            }
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

    private void sound(Level level, BlockPos blockPos, AlarmClockBlockEntity alarmClockBlockEntity) {
        if (level == null || level.isClientSide()) return;

        HandbellTypes leftBellType = alarmClockBlockEntity.getData().leftBell().isPresent() ? alarmClockBlockEntity.getData().leftBell().get().getType() : null;
        HandbellTypes rightBellType = alarmClockBlockEntity.getData().rightBell().isPresent() ? alarmClockBlockEntity.getData().rightBell().get().getType() : null;

        long timeOfDay = level.getDayTime() % 24000;

        long sunriseTime = 23000; // rings as the sun appears on the horizon
        if (!hasChimed && (timeOfDay == sunriseTime)) {
            if (leftBellType != null) level.playSound(null, blockPos, leftBellType.getRingSound().get(), SoundSource.BLOCKS, 0.3F, leftBellType.getPitch());
            if (rightBellType != null) level.playSound(null, blockPos, rightBellType.getRingSound().get(), SoundSource.BLOCKS, 0.3F, rightBellType.getPitch());
            hasChimed = true;
        } else if (timeOfDay == sunriseTime + 1) {
            hasChimed = false;
        }

        // Legacy (can be removed after release) implementation rings at noon and midnight
//        if ((timeOfDay == 6000 || timeOfDay == 18000) && !hasChimed) {
//            if (leftBellType != null) level.playSound(null, blockPos, leftBellType.getRingSound().get(), SoundSource.BLOCKS, 0.3F, leftBellType.getPitch());
//            if (rightBellType != null) level.playSound(null, blockPos, rightBellType.getRingSound().get(), SoundSource.BLOCKS, 0.3F, rightBellType.getPitch());
//            hasChimed = true;
//        } else if (timeOfDay == 6001 || timeOfDay == 18001) {
//            hasChimed = false;
//        }
    }

    public ItemStack getBlockAsItem(ClockTypes clockType) {
        ItemStack itemStack = BuiltInRegistries.ITEM.get(ColourfulClocksBlocks.ALARM_CLOCK.withSuffix(clockType.getSerializedName())).getDefaultInstance();
        itemStack.applyComponents(this.collectComponents());
        return itemStack;
    }
}
