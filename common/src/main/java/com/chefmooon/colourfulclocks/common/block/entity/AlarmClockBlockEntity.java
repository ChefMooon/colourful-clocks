package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.AlarmClockComponent;
import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
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
import net.minecraft.core.component.DataComponentType;
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
    private ItemStack pocketWatchItem = ItemStack.EMPTY;
    private AlarmClockComponent alarmClockData;
    public AlarmClockBlockEntity(BlockPos pos, BlockState blockState) {
        super(BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.ALARM_CLOCK), pos, blockState);
        this.alarmClockData = AlarmClockComponent.getDefaultValue();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.alarmClockData = AlarmClockComponent.load(tag);

        if (tag.contains("pocket_watch")) {
            CompoundTag clockHandsItemTag = tag.getCompound("pocket_watch");
            setPocketWatchType(ItemStack.parse(provider, clockHandsItemTag).orElse(ItemStack.EMPTY));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.alarmClockData.save(tag);
        super.saveAdditional(tag, provider);

        if (!pocketWatchItem.isEmpty()) {
            tag.put("pocket_watch", pocketWatchItem.save(provider, new CompoundTag()));
        }
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
        if (this.alarmClockData.pocketWatchType().isPresent()) {
            if (this.alarmClockData.pocketWatchType().get().getId() != 0) {
                drops.add(getPocketWatchItem());
            }
        }
        return drops;
    }

    public ItemStack getPocketWatchItem() {
        return pocketWatchItem;
    }

    public boolean isEmpty() {
        return this.alarmClockData.leftBell().isPresent() || this.alarmClockData.rightBell().isPresent() || this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY).getId() == 0;
    }

    public void setLeftHandbellType(ItemStack itemStack) {
        setData(itemStack.get(ColourfulClocksDataComponentTypes.getHandbellData()), this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public void setLeftHandbellType(HandbellComponent component) {
        setData(component, this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public HandbellComponent removeLeftHandbellType() {
        HandbellComponent removed = this.alarmClockData.leftBell().orElse(HandbellComponent.getDefaultValue());
        setData(null, this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
        return removed;
    }

    public void setRightHandbellType(HandbellComponent component) {
        setData(this.alarmClockData.leftBell().orElse(null), component, this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public void setRightHandbellType(ItemStack itemStack) {
        setData(this.alarmClockData.leftBell().orElse(null), itemStack.get(ColourfulClocksDataComponentTypes.getHandbellData()), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public HandbellComponent removeRightHandbellType() {
        HandbellComponent removed = this.alarmClockData.rightBell().orElse(HandbellComponent.getDefaultValue());
        setData(this.alarmClockData.leftBell().orElse(null), null, this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
        return removed;
    }

    public void setGlassType(BornholmTopGlassTypes glassType) {
        setData(this.alarmClockData.leftBell().orElse(null), this.alarmClockData.rightBell().orElse(null), glassType, this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public void setPocketWatchType(ItemStack itemStack) {
        pocketWatchItem = itemStack;
        setData(this.alarmClockData.leftBell().orElse(null), this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), ColourfulClocksTypeUtil.getPocketWatchTypeFromItem(itemStack.getItem()), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public ItemStack removePocketWatchType() {
        ItemStack stored = pocketWatchItem;
        pocketWatchItem = ItemStack.EMPTY;
        setData(this.alarmClockData.leftBell().orElse(null), this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), PocketWatchTypes.EMPTY, this.alarmClockData.ticking().orElse(Boolean.FALSE));
        return stored;
    }

    public void setTicking(boolean ticking) {
        setData(this.alarmClockData.leftBell().orElse(null), this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), ticking);
    }

    public void setData(@Nullable HandbellComponent leftBell, @Nullable HandbellComponent rightBell, @Nullable BornholmTopGlassTypes glassType, @Nullable PocketWatchTypes pocketWatchType, @Nullable Boolean ticking) {
        this.alarmClockData = new AlarmClockComponent(
                leftBell != null ? Optional.of(leftBell) : Optional.empty(),
                rightBell != null ? Optional.of(rightBell) : Optional.empty(),
                glassType != null ? Optional.of(glassType) : Optional.empty(),
                pocketWatchType != null ? Optional.of(pocketWatchType) : Optional.empty(),
                ticking != null ? Optional.of(ticking) : Optional.empty());
        setChanged();
    }

    public AlarmClockComponent getData() {
        return this.alarmClockData;
    }

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, AlarmClockBlockEntity alarmClockBlockEntity) {
        weatherItem(level, blockPos, alarmClockBlockEntity); // TODO : fix copper weathering
        if (alarmClockBlockEntity.getData().pocketWatchType().orElse(PocketWatchTypes.EMPTY).getId() != 0 && blockState.getValue(ColourfulClocksBlockStateProperties.TICKING)) {
            tickSound(level, blockPos);
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

        ItemStack pocketWatchStack = alarmClockBlockEntity.getPocketWatchItem();
        if (!pocketWatchStack.isEmpty()) {
            if (ColourfulClocksTypeUtil.isCopperClockHands(pocketWatchStack)) {
                if (pocketWatchStack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING)) != null) {
                    Integer weathering = pocketWatchStack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING));
                    if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                        advancePocketWatchWeathering(level, blockPos, pocketWatchStack, alarmClockBlockEntity);
                    } else {
                        pocketWatchStack.set((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING), weathering + 1);
                    }
                }
            }
        }
    }

    protected static void advancePocketWatchWeathering(Level level, BlockPos blockPos, ItemStack itemStack, AlarmClockBlockEntity alarmClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperItem(itemStack).get());
        if (!weatheredItemStack.isEmpty()) {
            alarmClockBlockEntity.setPocketWatchType(weatheredItemStack);
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

    public ItemStack getBlockAsItem(ClockTypes clockType) {
        ItemStack itemStack = BuiltInRegistries.ITEM.get(ColourfulClocksBlocks.ALARM_CLOCK.withSuffix(clockType.getSerializedName())).getDefaultInstance();
        itemStack.applyComponents(this.collectComponents());
        return itemStack;
    }
}
