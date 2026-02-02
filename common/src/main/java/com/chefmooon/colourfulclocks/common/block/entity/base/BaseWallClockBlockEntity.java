package com.chefmooon.colourfulclocks.common.block.entity.base;

import com.chefmooon.colourfulclocks.common.block.TallMantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.PocketWatchComponent;
import com.chefmooon.colourfulclocks.common.data.WallClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.WallClockType;
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

public class BaseWallClockBlockEntity extends BlockEntity {
    private WallClockComponent wallClockData;
    public BaseWallClockBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.wallClockData = WallClockComponent.getDefaultValue();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        this.wallClockData = WallClockComponent.load(tag);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.wallClockData.save(tag);
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
        components.set(ColourfulClocksDataComponentTypes.getWallClockData(), this.wallClockData);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.wallClockData = componentInput.getOrDefault(ColourfulClocksDataComponentTypes.getWallClockData(), this.wallClockData);
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.wallClockData.getPocketWatch().isPresent() && this.wallClockData.getPocketWatch().get().getType().getId() != 0) {
            ItemStack pocketWatch = BuiltInRegistries.ITEM.get(TextUtil.res(this.wallClockData.getPocketWatch().get().getType().getSerializedName() + "_pocket_watch")).getDefaultInstance();
            pocketWatch.set(ColourfulClocksDataComponentTypes.getPocketWatchData(), this.wallClockData.getPocketWatch().get());
            drops.add(pocketWatch);
        }
        return drops;
    }

    public boolean isEmpty() {
        return this.wallClockData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()).getType().getId() == 0;
    }

    public void setPocketWatch(ItemStack itemStack) {
        setPocketWatch(itemStack.get(ColourfulClocksDataComponentTypes.getPocketWatchData()));
    }

    public void setPocketWatch(PocketWatchComponent component) {
        setData(this.wallClockData.getType(), component, this.wallClockData.isTicking().orElse(Boolean.FALSE));
    }

    public PocketWatchComponent removePocketWatch() {
        PocketWatchComponent removed = this.wallClockData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue());
        setData(this.wallClockData.getType(), PocketWatchComponent.getDefaultValue(), this.wallClockData.isTicking().orElse(Boolean.FALSE));
        return removed;
    }

    public void setTicking(boolean ticking) {
        setData(this.wallClockData.getType(), this.wallClockData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()), ticking);
    }

    public void setSupportingClockData(WallClockComponent component) {
        setData(component.getType(), PocketWatchComponent.getDefaultValue(), false);
    }

    public void setData(WallClockType wallClockType, @Nullable PocketWatchComponent pocketWatchType, @Nullable Boolean ticking) {
        this.wallClockData = new WallClockComponent(
                wallClockType,
                pocketWatchType != null ? Optional.of(pocketWatchType) : Optional.empty(),
                ticking != null ? Optional.of(ticking) : Optional.empty());
        setChanged();
    }

    public void setData(WallClockComponent component) {
        this.wallClockData = component;
        setChanged();
    }

    public WallClockComponent getData() {
        return this.wallClockData;
    }

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, BaseWallClockBlockEntity BaseWallClockBlockEntity) {
        if (blockState.getValue(TallMantelClockBlock.ACTIVATED)) {
            weatherItem(level, blockPos, BaseWallClockBlockEntity);
        }
        if (BaseWallClockBlockEntity.getData().getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()).getType().getId() != 0 && blockState.getValue(ColourfulClocksBlockStateProperties.TICKING)
                && blockState.getValue(ColourfulClocksBlockStateProperties.ACTIVATED)) {
            tickSound(level, blockPos);
        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, BaseWallClockBlockEntity BaseWallClockBlockEntity) {
        if (BaseWallClockBlockEntity.getData().getPocketWatch().isPresent()) {
            PocketWatchComponent pocketWatchComponent = BaseWallClockBlockEntity.getData().getPocketWatch().get();
            if (pocketWatchComponent.getWeathering().isPresent() && ColourfulClocksTypeUtil.pocketWatchCanWeather(pocketWatchComponent)) {
                int weathering = pocketWatchComponent.getWeathering().get();
                if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                    advancePocketWatchWeathering(level, blockPos, pocketWatchComponent, BaseWallClockBlockEntity);
                } else {
                    BaseWallClockBlockEntity.setPocketWatch(new PocketWatchComponent(pocketWatchComponent.getType(), Optional.of(weathering + 1)));
                }
            }
        }
    }

    protected static void advancePocketWatchWeathering(Level level, BlockPos blockPos, PocketWatchComponent pocketWatchComponent, BaseWallClockBlockEntity BaseWallClockBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperPocketWatch(pocketWatchComponent));
        if (!weatheredItemStack.isEmpty()) {
            BaseWallClockBlockEntity.setPocketWatch(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            BaseWallClockBlockEntity.setChanged();
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
}
