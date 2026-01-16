package com.chefmooon.colourfulclocks.common.block.entity.base;

import com.chefmooon.colourfulclocks.common.block.TallMantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.ClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.CopperWeatheringUtil;
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
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BaseDataClockBlockEntity extends BlockEntity {
    private ItemStack pocketWatchItem = ItemStack.EMPTY;
    private ItemStack pendulumItem = ItemStack.EMPTY;
    private static boolean hasChimed = false;
    private ClockComponent clockData;
    public BaseDataClockBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.clockData = ClockComponent.getDefaultValue();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.clockData = ClockComponent.load(tag);

        if (tag.contains("pocket_watch")) {
            CompoundTag clockHandsItemTag = tag.getCompound("pocket_watch");
            setPocketWatchType(ItemStack.parse(provider, clockHandsItemTag).orElse(ItemStack.EMPTY));
        }
        if (tag.contains("pendulum")) {
            CompoundTag pendulumItemTag = tag.getCompound("pendulum");
            setPendulumType(ItemStack.parse(provider, pendulumItemTag).orElse(ItemStack.EMPTY));
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.clockData.save(tag);
        super.saveAdditional(tag, provider);

        if (!pocketWatchItem.isEmpty()) {
            tag.put("pocket_watch", pocketWatchItem.save(provider, new CompoundTag()));
        }
        if (!pendulumItem.isEmpty()) {
            tag.put("pendulum", pendulumItem.save(provider, new CompoundTag()));
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
        components.set(ColourfulClocksDataComponentTypes.getClockData(), this.clockData);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.clockData = componentInput.getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), this.clockData);
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.clockData.getPocketWatchType().isPresent()) {
            if (this.clockData.getPocketWatchType().get().getId() != 0) {
                drops.add(new ItemStack(ColourfulClocksTypeUtil.getPocketWatchItemFromType(this.clockData.getPocketWatchType().get())));
            }
        }
        if (this.clockData.getPendulumType().isPresent()) {
            if (this.clockData.getPendulumType().get().getId() != 0) {
                drops.add(new ItemStack(ColourfulClocksTypeUtil.getPendulumItemFromType(this.clockData.getPendulumType().get())));
            }
        }
        return drops;
    }

    public boolean isEmpty() {
        return this.clockData.getPocketWatchType().orElse(PocketWatchTypes.EMPTY).getId() == 0 || this.clockData.getPendulumType().orElse(PendulumTypes.EMPTY).getId() == 0;
    }

    public void setPocketWatchType(ItemStack itemStack) {
        pocketWatchItem = itemStack;
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), ColourfulClocksTypeUtil.getPocketWatchTypeFromItem(itemStack.getItem()), this.clockData.getPendulumType().orElse(PendulumTypes.EMPTY), this.clockData.getTicking().orElse(Boolean.FALSE));
    }

    public ItemStack removePocketWatchType() {
        ItemStack stored = pocketWatchItem;
        pocketWatchItem = ItemStack.EMPTY;
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), PocketWatchTypes.EMPTY, this.clockData.getPendulumType().orElse(PendulumTypes.EMPTY), this.clockData.getTicking().orElse(Boolean.FALSE));
        return stored;
    }

    public void setPendulumType(ItemStack itemStack) {
        pendulumItem = itemStack;
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), this.clockData.getPocketWatchType().orElse(PocketWatchTypes.EMPTY), ColourfulClocksTypeUtil.getPendulumTypeFromItem(itemStack.getItem()), this.clockData.getTicking().orElse(Boolean.FALSE));
    }

    public ItemStack removePendulumType() {
        ItemStack stored = pendulumItem;
        pendulumItem = ItemStack.EMPTY;
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), this.clockData.getPocketWatchType().orElse(PocketWatchTypes.EMPTY), PendulumTypes.EMPTY, this.clockData.getTicking().orElse(Boolean.FALSE));
        return stored;
    }

    public void setGlassType(BornholmTopGlassTypes glassType) {
        setData(glassType, this.clockData.getPocketWatchType().orElse(PocketWatchTypes.EMPTY), this.clockData.getPendulumType().orElse(PendulumTypes.EMPTY), this.clockData.getTicking().orElse(Boolean.FALSE));
    }

    public void setTicking(boolean ticking) {
        setData(this.clockData.getGlassType().orElse(BornholmTopGlassTypes.GLASS), this.clockData.getPocketWatchType().orElse(PocketWatchTypes.EMPTY), this.clockData.getPendulumType().orElse(PendulumTypes.EMPTY), ticking);
    }

    public void setData(@Nullable BornholmTopGlassTypes glassType, @Nullable PocketWatchTypes pocketWatchType, @Nullable PendulumTypes pendulumType, @Nullable Boolean ticking) {
        this.clockData = new ClockComponent(
                glassType != null ? Optional.of(glassType) : Optional.empty(),
                pocketWatchType != null ? Optional.of(pocketWatchType) : Optional.empty(),
                pendulumType != null ? Optional.of(pendulumType) : Optional.empty(),
                ticking != null ? Optional.of(ticking) : Optional.empty());
        setChanged();
    }

    public ClockComponent getData() {
        return this.clockData;
    }

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, BaseDataClockBlockEntity baseDataClockBlockEntity) {
        if (blockState.getValue(TallMantelClockBlock.ACTIVATED)) {
            weatherItem(level, blockPos, baseDataClockBlockEntity);
            if (!baseDataClockBlockEntity.pendulumItem.isEmpty()) sound(level, blockPos, baseDataClockBlockEntity);
        }
        if (baseDataClockBlockEntity.getData().pocketWatchType().orElse(PocketWatchTypes.EMPTY).getId() != 0 && blockState.getValue(ColourfulClocksBlockStateProperties.TICKING)
                && blockState.getValue(ColourfulClocksBlockStateProperties.ACTIVATED)) {
            tickSound(level, blockPos);
        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, BaseDataClockBlockEntity baseDataClockBlockEntity) {
        ItemStack pocketWatchStack = baseDataClockBlockEntity.pocketWatchItem;
        if (!pocketWatchStack.isEmpty()) {
            if (ColourfulClocksTypeUtil.isCopperClockHands(pocketWatchStack)) {
                if (pocketWatchStack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING)) != null) {
                    Integer weathering = pocketWatchStack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING));
                    if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                        advancePocketWatchWeathering(level, blockPos, pocketWatchStack, baseDataClockBlockEntity);
                    } else {
                        pocketWatchStack.set((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING), weathering + 1);
                    }
                }
            }
        }

        ItemStack pendulumStack = baseDataClockBlockEntity.pendulumItem;
        if (!pendulumStack.isEmpty()) {
            if (ColourfulClocksTypeUtil.isCopperPendulum(pendulumStack)) {
                if (pendulumStack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING)) != null) {
                    Integer weathering = pendulumStack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING));
                    if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                        advancePendulumWeathering(level, blockPos, pendulumStack, baseDataClockBlockEntity);
                    } else {
                        pendulumStack.set((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING), weathering + 1);
                    }
                }
            }
        }
    }

    protected static void advancePocketWatchWeathering(Level level, BlockPos blockPos, ItemStack itemStack, BaseDataClockBlockEntity baseDataClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperItem(itemStack).get());
        if (!weatheredItemStack.isEmpty()) {
            baseDataClockBlockEntity.setPocketWatchType(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            baseDataClockBlockEntity.setChanged();
        }
    }

    protected static void advancePendulumWeathering(Level level, BlockPos blockPos, ItemStack itemStack, BaseDataClockBlockEntity baseDataClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperPendulum(itemStack).get());
        if (!weatheredItemStack.isEmpty()) {
            baseDataClockBlockEntity.setPendulumType(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            baseDataClockBlockEntity.setChanged();
        }
    }

    protected static void sound(Level level, BlockPos blockPos, BaseDataClockBlockEntity baseDataClockBlockEntity) {
        if (level == null || level.isClientSide()) return;

        PendulumTypes pendulumType = ColourfulClocksTypeUtil.getPendulumTypeFromItem(baseDataClockBlockEntity.pendulumItem.getItem());

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
