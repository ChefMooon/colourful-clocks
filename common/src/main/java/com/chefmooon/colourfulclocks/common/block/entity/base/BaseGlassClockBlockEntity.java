package com.chefmooon.colourfulclocks.common.block.entity.base;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.GlassDialComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BaseGlassClockBlockEntity extends BaseClockBlockEntity {
    private GlassDialComponent glassDialData;

    public BaseGlassClockBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.glassDialData = GlassDialComponent.getDefaultValue();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.glassDialData = GlassDialComponent.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.glassDialData.save(tag);
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
        components.set(ColourfulClocksDataComponentTypes.getGlassDialData(), this.glassDialData);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.glassDialData = componentInput.getOrDefault(ColourfulClocksDataComponentTypes.getGlassDialData(), this.glassDialData);
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.glassDialData.getPocketWatchType().getId() != 0) {
            drops.add(new ItemStack(ColourfulClocksTypeUtil.getPocketWatchItemFromType(this.getDialData().pocketWatchType())));
        }
        return drops;
    }

    public boolean isEmpty() {
        return this.glassDialData.getPocketWatchType().getId() == 0;
    }

    public void setPocketWatchType(ItemStack itemStack) {
        setDialData(this.glassDialData.getGlassType(), ColourfulClocksTypeUtil.getPocketWatchTypeFromItem(itemStack.getItem()), this.glassDialData.getTicking());
    }

    public ItemStack removePocketWatchType() {
        ItemStack stored = ColourfulClocksTypeUtil.getPocketWatchItemFromType(this.glassDialData.pocketWatchType()).getDefaultInstance();
        setDialData(this.glassDialData.getGlassType(), PocketWatchTypes.EMPTY, this.glassDialData.getTicking());
        return stored;
    }

    public void setGlassType(BornholmTopGlassTypes glassType) {
        setDialData(glassType, this.glassDialData.getPocketWatchType(), this.glassDialData.getTicking());
    }

    public void setTicking(boolean ticking) {
        setDialData(this.glassDialData.getGlassType(), this.glassDialData.getPocketWatchType(), ticking);
    }

    public void setDialData(BornholmTopGlassTypes glassType, PocketWatchTypes pocketWatchType, boolean ticking) {
        this.glassDialData = new GlassDialComponent(glassType, pocketWatchType, ticking);
        setChanged();
    }

    public GlassDialComponent getDialData() {
        return this.glassDialData;
    }

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, BaseGlassClockBlockEntity baseGlassClockBlockEntity) {
        if (blockState.getValue(BornholmTopBlock.ACTIVATED)) {
            weatherItem(level, blockPos, baseGlassClockBlockEntity);
        }
        if (!baseGlassClockBlockEntity.isEmpty() && blockState.getValue(ColourfulClocksBlockStateProperties.TICKING) && blockState.getValue(ColourfulClocksBlockStateProperties.ACTIVATED)) {
            tickSound(level, blockPos);
        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, BaseGlassClockBlockEntity baseGlassClockBlockEntity) {
        ItemStack itemStack = new ItemStack(baseGlassClockBlockEntity.glassDialData.getPocketWatchType().getItem());
        if (!itemStack.isEmpty()) {
            if (ColourfulClocksTypeUtil.isCopperClockHands(itemStack)) {
                if (itemStack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING)) != null) {
                    Integer weathering = itemStack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING));
                    if (weathering >= WEATHERED_THRESHOLD) {
                        advanceWeathering(level, blockPos, itemStack, baseGlassClockBlockEntity);
                    } else {
                        itemStack.set((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING), weathering + 1);
                    }
                }
            }
        }
    }

    private static void advanceWeathering(Level level, BlockPos blockPos, ItemStack itemStack, BaseGlassClockBlockEntity baseGlassClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperItem(itemStack).get());
        if (!weatheredItemStack.isEmpty()) {
            baseGlassClockBlockEntity.setPocketWatchType(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            baseGlassClockBlockEntity.setChanged();
        }
    }
}
