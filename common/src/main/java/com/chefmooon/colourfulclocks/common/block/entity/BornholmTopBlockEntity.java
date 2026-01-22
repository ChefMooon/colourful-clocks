package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.data.PocketWatchComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.CopperWeatheringUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public class BornholmTopBlockEntity extends BlockEntity {
    private BornholmTopGlassComponent dialData;
    public BornholmTopBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.dialData = BornholmTopGlassComponent.getDefaultValue();
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.dialData.getPocketWatch().isPresent() && this.dialData.getPocketWatch().get().getType() != PocketWatchTypes.EMPTY) {
            ItemStack pocketWatch = BuiltInRegistries.ITEM.get(TextUtil.res(this.dialData.getPocketWatch().get().getType().getSerializedName() + "_pocket_watch")).getDefaultInstance();
            drops.add(pocketWatch);
        }
        return drops;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);

        this.dialData = BornholmTopGlassComponent.load(tag);
        if (tag.contains("clock_hands")) { // Legacy data support
            CompoundTag clockHandsItemTag = tag.getCompound("clock_hands");
            ItemStack legacyClockHandsItem = ItemStack.parse(provider, clockHandsItemTag).orElse(ItemStack.EMPTY);
            PocketWatchComponent component = legacyClockHandsItem.get(ColourfulClocksDataComponentTypes.getPocketWatchData());
            setPocketWatch(component);
        } else {
            if (dialData.getPocketWatch().isPresent() && dialData.getPocketWatch().get().getType() != PocketWatchTypes.EMPTY) {
                setPocketWatch(dialData.getPocketWatch().get());
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.dialData.save(tag);
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

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, BornholmTopBlockEntity bornholmTopBlockEntity) {
        if (blockState.getValue(BornholmTopBlock.ACTIVATED)) {
            weatherItem(level, blockPos, bornholmTopBlockEntity);
        }
        if (bornholmTopBlockEntity.getData().getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()).getType() != PocketWatchTypes.EMPTY && blockState.getValue(BornholmTopBlock.TICKING)
                && blockState.getValue(BornholmTopBlock.ACTIVATED)) {
            tickSound(level, blockPos);
        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, BornholmTopBlockEntity bornholmTopBlockEntity) {
        if (bornholmTopBlockEntity.getData().getPocketWatch().isPresent()) {
            PocketWatchComponent pocketWatchComponent = bornholmTopBlockEntity.getData().getPocketWatch().get();
            if (pocketWatchComponent.getWeathering().isPresent() && ColourfulClocksTypeUtil.pocketWatchCanWeather(pocketWatchComponent)) {
                int weathering = pocketWatchComponent.getWeathering().get();
                if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                    advanceWeathering(level, blockPos, pocketWatchComponent, bornholmTopBlockEntity);
                } else {
                    bornholmTopBlockEntity.setPocketWatch(new PocketWatchComponent(pocketWatchComponent.getType(), Optional.of(weathering + 1)));
                }
            }
        }
    }

    private static void advanceWeathering(Level level, BlockPos blockPos, PocketWatchComponent pocketWatchComponent, BornholmTopBlockEntity bornholmTopBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperPocketWatch(pocketWatchComponent));
        if (!weatheredItemStack.isEmpty()) {
            bornholmTopBlockEntity.setPocketWatch(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            bornholmTopBlockEntity.setChanged();
            if (!level.isClientSide()) {
                BlockState state = level.getBlockState(blockPos);
                level.sendBlockUpdated(blockPos, state, state, 3);
            }
        }
    }

    private static void tickSound(Level level, BlockPos blockPos) {
        if (level == null || level.isClientSide()) return;

        float timeOfDay = (level.getDayTime() + 0) % 24000;
        float segmentTime = timeOfDay % 750.0F;
        float stepLength = 750.0F / 16.0F;
        if (Math.abs(segmentTime % stepLength) < 1.0F) {
            level.playSound(null, blockPos, ColourfulClocksSounds.BLOCK_BORNHOLM_TICK.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    public void setPocketWatch(ItemStack itemStack) {
        PocketWatchComponent component = itemStack.get(ColourfulClocksDataComponentTypes.getPocketWatchData());
        setDialData(this.dialData.getGlassType(), component, this.dialData.getTicking());
    }

    public void setPocketWatch(PocketWatchComponent component) {
        setDialData(this.dialData.getGlassType(), component, this.dialData.getTicking());
    }

    public PocketWatchComponent removePocketWatch() {
        PocketWatchComponent removed = this.dialData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue());
        setDialData(this.dialData.getGlassType(), PocketWatchComponent.getDefaultValue(), this.dialData.getTicking());
        return removed;
    }

    public void setGlassType(BornholmTopGlassTypes glassType) {
        setDialData(glassType, this.dialData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()), this.dialData.getTicking());
    }

    public void setTicking(boolean ticking) {
        setDialData(this.dialData.getGlassType(), this.dialData.getPocketWatch().orElse(PocketWatchComponent.getDefaultValue()), ticking);
    }

    public void setData(BornholmTopGlassComponent data) {
        this.dialData = data;
        setChanged();
    }

    public void setDialData(BornholmTopGlassTypes glassType, PocketWatchComponent pocketWatchComponent, boolean ticking) {
        this.dialData = new BornholmTopGlassComponent(glassType, Optional.of(pocketWatchComponent), ticking);
        setChanged();
    }

    public BornholmTopGlassComponent getData() {
        return this.dialData;
    }

    public ItemStack getBlockAsItem(ClockTypes clockType) {
        ItemStack itemStack = BuiltInRegistries.ITEM.get(ColourfulClocksBlocks.BORNHOLM_TOP.withSuffix(clockType.getSerializedName())).getDefaultInstance();
        itemStack.applyComponents(this.collectComponents());
        return itemStack;
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(ColourfulClocksDataComponentTypes.getBornholmTopGlassData(), this.dialData);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.dialData = componentInput.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmTopGlassData(), this.dialData);
    }

    @ExpectPlatform
    public static Item getItemStack(ClockTypes clockTypes) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isCopperClockHands(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Item> getNextWeatheredCopperItem(ItemStack itemStack) {
        throw new AssertionError();
    }
}
