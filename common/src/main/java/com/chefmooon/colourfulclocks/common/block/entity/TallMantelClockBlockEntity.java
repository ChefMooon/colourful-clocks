package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.TallMantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.base.BaseClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.TallMantelClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
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

public class TallMantelClockBlockEntity extends BaseClockBlockEntity {
    private ItemStack pocketWatchItem = ItemStack.EMPTY;
    private ItemStack pendulumItem = ItemStack.EMPTY;
    private static boolean hasChimed = false;
    private TallMantelClockComponent dataComponent;
    public TallMantelClockBlockEntity(BlockPos pos, BlockState blockState) {
        super(BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.TALL_MANTEL_CLOCK), pos, blockState);
    }

    public ItemStack getBlockAsItem(ClockTypes clockType) {
        ItemStack itemStack = BuiltInRegistries.ITEM.get(ColourfulClocksBlocks.TALL_MANTEL_CLOCK.withSuffix(clockType.getSerializedName())).getDefaultInstance();
        itemStack.applyComponents(this.collectComponents());
        return itemStack;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.dataComponent = TallMantelClockComponent.load(tag);

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
        this.dataComponent.save(tag);
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
        components.set(ColourfulClocksDataComponentTypes.getTallMantelClockData(), this.dataComponent);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.dataComponent = componentInput.getOrDefault(ColourfulClocksDataComponentTypes.getTallMantelClockData(), this.dataComponent);
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.dataComponent.getPocketWatchType().getId() != 0) {
            drops.add(new ItemStack(ColourfulClocksTypeUtil.getPocketWatchItemFromType(this.getData().pocketWatchType())));
        }
        if (this.dataComponent.getPendulumType().getId() != 0) {
            drops.add(new ItemStack(ColourfulClocksTypeUtil.getPendulumItemFromType(this.getData().pendulumType())));
        }
        return drops;
    }

    public boolean isEmpty() {
        return this.dataComponent.getPocketWatchType().getId() == 0 || this.dataComponent.getPendulumType().getId() == 0;
    }

    public void setPocketWatchType(ItemStack itemStack) {
        pocketWatchItem = itemStack;
        setData(this.dataComponent.getGlassType(), ColourfulClocksTypeUtil.getPocketWatchTypeFromItem(itemStack.getItem()), this.dataComponent.getPendulumType(), this.dataComponent.getTicking());
    }

    public ItemStack removePocketWatchType() {
        ItemStack stored = pocketWatchItem;
        pocketWatchItem = ItemStack.EMPTY;
        setData(this.dataComponent.getGlassType(), PocketWatchTypes.EMPTY, this.dataComponent.getPendulumType(), this.dataComponent.getTicking());
        return stored;
    }

    public void setPendulumType(ItemStack itemStack) {
        pendulumItem = itemStack;
        setData(this.dataComponent.getGlassType(), this.dataComponent.getPocketWatchType(), ColourfulClocksTypeUtil.getPendulumTypeFromItem(itemStack.getItem()), this.dataComponent.getTicking());
    }

    public ItemStack removePendulumType() {
        ItemStack stored = pendulumItem;
        pendulumItem = ItemStack.EMPTY;
        setData(this.dataComponent.getGlassType(), this.dataComponent.getPocketWatchType(), PendulumTypes.EMPTY, this.dataComponent.getTicking());
        return stored;
    }

    public void setGlassType(BornholmTopGlassTypes glassType) {
        setData(glassType, this.dataComponent.getPocketWatchType(), this.dataComponent.getPendulumType(), this.dataComponent.getTicking());
    }

    public void setTicking(boolean ticking) {
        setData(this.dataComponent.getGlassType(), this.dataComponent.getPocketWatchType(), this.dataComponent.getPendulumType(), ticking);
    }

    public void setData(BornholmTopGlassTypes glassType, PocketWatchTypes pocketWatchType, PendulumTypes pendulumType, boolean ticking) {
        this.dataComponent = new TallMantelClockComponent(glassType, pocketWatchType, pendulumType, ticking);
        setChanged();
    }

    public TallMantelClockComponent getData() {
        return this.dataComponent;
    }

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, TallMantelClockBlockEntity tallMantelClockBlockEntity) {
        if (blockState.getValue(TallMantelClockBlock.ACTIVATED)) {
            weatherItem(level, blockPos, tallMantelClockBlockEntity);
            if (!tallMantelClockBlockEntity.pendulumItem.isEmpty()) sound(level, blockPos, tallMantelClockBlockEntity);
        }
        if (tallMantelClockBlockEntity.getData().pocketWatchType().getId() != 0 && blockState.getValue(ColourfulClocksBlockStateProperties.TICKING)
                && blockState.getValue(ColourfulClocksBlockStateProperties.ACTIVATED)) {
            tickSound(level, blockPos);
        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, TallMantelClockBlockEntity tallMantelClockBlockEntity) {
        ItemStack pocketWatchStack = tallMantelClockBlockEntity.pocketWatchItem;
        if (!pocketWatchStack.isEmpty()) {
            if (ColourfulClocksTypeUtil.isCopperClockHands(pocketWatchStack)) {
                if (pocketWatchStack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING)) != null) {
                    Integer weathering = pocketWatchStack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING));
                    if (weathering >= WEATHERED_THRESHOLD) {
                        advancePocketWatchWeathering(level, blockPos, pocketWatchStack, tallMantelClockBlockEntity);
                    } else {
                        pocketWatchStack.set((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING), weathering + 1);
                    }
                }
            }
        }

        ItemStack pendulumStack = tallMantelClockBlockEntity.pendulumItem;
        if (!pendulumStack.isEmpty()) {
            if (ColourfulClocksTypeUtil.isCopperPendulum(pendulumStack)) {
                if (pendulumStack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING)) != null) {
                    Integer weathering = pendulumStack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING));
                    if (weathering >= WEATHERED_THRESHOLD) {
                        advancePendulumWeathering(level, blockPos, pendulumStack, tallMantelClockBlockEntity);
                    } else {
                        pendulumStack.set((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING), weathering + 1);
                    }
                }
            }
        }
    }

    private static void advancePocketWatchWeathering(Level level, BlockPos blockPos, ItemStack itemStack, TallMantelClockBlockEntity tallMantelClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperItem(itemStack).get());
        if (!weatheredItemStack.isEmpty()) {
            tallMantelClockBlockEntity.setPocketWatchType(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            tallMantelClockBlockEntity.setChanged();
        }
    }

    private static void advancePendulumWeathering(Level level, BlockPos blockPos, ItemStack itemStack, TallMantelClockBlockEntity tallMantelClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperPendulum(itemStack).get());
        if (!weatheredItemStack.isEmpty()) {
            tallMantelClockBlockEntity.setPendulumType(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            tallMantelClockBlockEntity.setChanged();
        }
    }

    private static void sound(Level level, BlockPos blockPos, TallMantelClockBlockEntity tallMantelClockBlockEntity) {
        if (level == null || level.isClientSide()) return;

        PendulumTypes pendulumType = ColourfulClocksTypeUtil.getPendulumTypeFromItem(tallMantelClockBlockEntity.pendulumItem.getItem());

        long timeOfDay = level.getDayTime() % 24000;

        if ((timeOfDay == 6000 || timeOfDay == 18000) && !hasChimed) {
            level.playSound(null, blockPos, pendulumType.getChimeSound().get(), SoundSource.BLOCKS, 0.6F, pendulumType.getPitchModifier()); // TODO: decide volume original from grandfather clock 1.0F
            hasChimed = true;
        } else if (timeOfDay == 6001 || timeOfDay == 18001) {
            hasChimed = false;
        }
    }
}
