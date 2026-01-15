package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.AlarmClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
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
    public static final int WEATHERED_THRESHOLD = 6000; // 5 min to weather
    private ItemStack leftBellItem = ItemStack.EMPTY;
    private ItemStack rightBellItem = ItemStack.EMPTY;
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

        if (tag.contains("left_bell")) {
            CompoundTag leftBellItemTag = tag.getCompound("left_bell");
            setLeftHandbellType(ItemStack.parse(provider, leftBellItemTag).orElse(ItemStack.EMPTY));
        }

        if (tag.contains("right_bell")) {
            CompoundTag rightBellItemTag = tag.getCompound("right_bell");
            setRightHandbellType(ItemStack.parse(provider, rightBellItemTag).orElse(ItemStack.EMPTY));
        }

        if (tag.contains("pocket_watch")) {
            CompoundTag clockHandsItemTag = tag.getCompound("pocket_watch");
            setPocketWatchType(ItemStack.parse(provider, clockHandsItemTag).orElse(ItemStack.EMPTY));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.alarmClockData.save(tag);
        super.saveAdditional(tag, provider);

        if (!leftBellItem.isEmpty()) {
            tag.put("left_bell", leftBellItem.save(provider, new CompoundTag()));
        }

        if (!rightBellItem.isEmpty()) {
            tag.put("right_bell", rightBellItem.save(provider, new CompoundTag()));
        }

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
            drops.add(getLeftBellItem());
        }
        if (this.alarmClockData.rightBell().isPresent()) {
            drops.add(getRightBellItem());
        }
        if (this.alarmClockData.pocketWatchType().isPresent()) {
            if (this.alarmClockData.pocketWatchType().get().getId() != 0) {
                drops.add(getPocketWatchItem());
            }
        }
        return drops;
    }

    public ItemStack getLeftBellItem() {
        return leftBellItem;
    }

    public ItemStack getRightBellItem() {
        return rightBellItem;
    }

    public ItemStack getPocketWatchItem() {
        return pocketWatchItem;
    }

    public boolean isEmpty() {
        return this.alarmClockData.leftBell().isPresent() || this.alarmClockData.rightBell().isPresent() || this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY).getId() == 0;
    }

    public void setLeftHandbellType(ItemStack itemStack) {
        leftBellItem = itemStack;
        setData(ColourfulClocksTypeUtil.getHandbellTypeFromItem(itemStack.getItem()), this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public ItemStack removeLeftHandbellType() {
        ItemStack stored = leftBellItem;
        leftBellItem = ItemStack.EMPTY;
        setData(null, this.alarmClockData.rightBell().orElse(null), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
        return stored;
    }

    public void setRightHandbellType(ItemStack itemStack) {
        rightBellItem = itemStack;
        setData(this.alarmClockData.leftBell().orElse(null), ColourfulClocksTypeUtil.getHandbellTypeFromItem(itemStack.getItem()), this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
    }

    public ItemStack removeRightHandbellType() {
        ItemStack stored = rightBellItem;
        rightBellItem = ItemStack.EMPTY;
        setData(this.alarmClockData.leftBell().orElse(null), null, this.alarmClockData.glassType().orElse(BornholmTopGlassTypes.GLASS), this.alarmClockData.pocketWatchType().orElse(PocketWatchTypes.EMPTY), this.alarmClockData.ticking().orElse(Boolean.FALSE));
        return stored;
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

    public void setData(@Nullable HandbellTypes leftBell, @Nullable HandbellTypes rightBell, @Nullable BornholmTopGlassTypes glassType, @Nullable PocketWatchTypes pocketWatchType, @Nullable Boolean ticking) {
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
        ItemStack leftBellStack = alarmClockBlockEntity.getLeftBellItem();
        if (!leftBellStack.isEmpty()) {
            if (ColourfulClocksTypeUtil.isCopperBell(leftBellStack)) {
                if (leftBellStack.get(ColourfulClocksDataComponentTypes.getHandbellWeatheringData()) != null) {
                    Integer weathering = leftBellStack.get(ColourfulClocksDataComponentTypes.getHandbellWeatheringData());
                    if (weathering >= WEATHERED_THRESHOLD) {
                        advanceBellWeathering(level, blockPos, leftBellStack, alarmClockBlockEntity, true);
                    } else {
                        leftBellStack.set(ColourfulClocksDataComponentTypes.getHandbellWeatheringData(), weathering + 1);
                    }
                }
            }
        }

        ItemStack rightBellStack = alarmClockBlockEntity.getRightBellItem();
        if (!rightBellStack.isEmpty()) {
            if (ColourfulClocksTypeUtil.isCopperBell(rightBellStack)) {
                if (rightBellStack.get(ColourfulClocksDataComponentTypes.getHandbellWeatheringData()) != null) {
                    Integer weathering = rightBellStack.get(ColourfulClocksDataComponentTypes.getHandbellWeatheringData());
                    if (weathering >= WEATHERED_THRESHOLD) {
                        advanceBellWeathering(level, blockPos, rightBellStack, alarmClockBlockEntity, false);
                    } else {
                        rightBellStack.set(ColourfulClocksDataComponentTypes.getHandbellWeatheringData(), weathering + 1);
                    }
                }
            }
        }

        ItemStack pocketWatchStack = alarmClockBlockEntity.getPocketWatchItem();
        if (!pocketWatchStack.isEmpty()) {
            if (ColourfulClocksTypeUtil.isCopperClockHands(pocketWatchStack)) {
                if (pocketWatchStack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING)) != null) {
                    Integer weathering = pocketWatchStack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING));
                    if (weathering >= WEATHERED_THRESHOLD) {
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
        }
    }

    protected static void advanceBellWeathering(Level level, BlockPos blockPos, ItemStack itemStack, AlarmClockBlockEntity alarmClockBlockEntity, boolean isLeft) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperBell(itemStack));
        if (!weatheredItemStack.isEmpty()) {
            if (isLeft) {
                alarmClockBlockEntity.setLeftHandbellType(weatheredItemStack);
            } else {
                alarmClockBlockEntity.setRightHandbellType(weatheredItemStack);
            }
            level.blockEntityChanged(blockPos);
            alarmClockBlockEntity.setChanged();
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
