package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.PendulumComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
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

import java.util.Optional;

public class BornholmMiddleBlockEntity extends BlockEntity {
    private BornholmMiddleDoorComponent trunkData;
    private static boolean hasChimed = false;

    public BornholmMiddleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.trunkData = BornholmMiddleDoorComponent.getDefaultValue();
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        if (this.trunkData.getPendulum().isPresent() && this.trunkData.getPendulum().get().getType() != PendulumTypes.EMPTY) {
            ItemStack pocketWatch = BuiltInRegistries.ITEM.get(TextUtil.res(this.trunkData.getPendulum().get().getType().getSerializedName() + "_pendulum")).getDefaultInstance();
            drops.add(pocketWatch);
        }
        return drops;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);

        this.trunkData = BornholmMiddleDoorComponent.load(tag);
        if (tag.contains("pendelum_item")) { // Legacy data support
            CompoundTag pendulumItemTag = tag.getCompound("pendelum_item");
            ItemStack legacyPendulum = ItemStack.parse(provider, pendulumItemTag).orElse(ItemStack.EMPTY);
            PendulumComponent component = legacyPendulum.get(ColourfulClocksDataComponentTypes.getPendulumData());
            setPendulum(component);
        } else {
            if (trunkData.getPendulum().isPresent() && trunkData.getPendulum().get().getType() != PendulumTypes.EMPTY) {
                setPendulum(trunkData.getPendulum().get());
            }
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        this.trunkData.save(tag);
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

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
        if (blockState.getValue(BornholmMiddleBlock.ACTIVATED)) {
            weatherItem(level, blockPos, bornholmMiddleBlockEntity);
            if (bornholmMiddleBlockEntity.getData().getPendulum().isPresent() && bornholmMiddleBlockEntity.getData().getPendulum().get().getType() != PendulumTypes.EMPTY) {
                sound(level, blockPos, bornholmMiddleBlockEntity);
            }
        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
        if (bornholmMiddleBlockEntity.trunkData.getPendulum().isPresent()) {
            PendulumComponent pendulumComponent = bornholmMiddleBlockEntity.trunkData.getPendulum().get();
            if (pendulumComponent.getWeathering().isPresent() && ColourfulClocksTypeUtil.pendulumCanWeather(pendulumComponent)) {
                int weathering = pendulumComponent.getWeathering().get();
                if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                    advanceWeathering(level, blockPos, pendulumComponent, bornholmMiddleBlockEntity);
                    return;
                } else {
                    PendulumComponent updatedPendulumComponent = new PendulumComponent(pendulumComponent.getType(), Optional.of(weathering + 1));
                    bornholmMiddleBlockEntity.setPendulum(updatedPendulumComponent);
                }
            }
        }
    }

    private static void advanceWeathering(Level level, BlockPos blockPos, PendulumComponent pendulumComponent, BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(ColourfulClocksTypeUtil.getNextWeatheredCopperPendulum(pendulumComponent));
        if (!weatheredItemStack.isEmpty()) {
            bornholmMiddleBlockEntity.setPendulum(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            bornholmMiddleBlockEntity.setChanged();
            if (!level.isClientSide()) {
                BlockState state = level.getBlockState(blockPos);
                level.sendBlockUpdated(blockPos, state, state, 3);
            }
        }
    }

    private static void sound(Level level, BlockPos blockPos, BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
        if (level == null || level.isClientSide()) return;

        PendulumTypes pendulumType = bornholmMiddleBlockEntity.getData().getPendulum().orElse(PendulumComponent.getDefaultValue()).getType();

        long timeOfDay = level.getDayTime() % 24000;

        if ((timeOfDay == 6000 || timeOfDay == 18000) && !hasChimed) {
            level.playSound(null, blockPos, pendulumType.getChimeSound().get(), SoundSource.BLOCKS, 1.0F, pendulumType.getPitchModifier());
            hasChimed = true;
        } else if (timeOfDay == 6001 || timeOfDay == 18001) {
            hasChimed = false;
        }
    }

    public void setPendulum(ItemStack pendulumItem) {
        PendulumComponent component = pendulumItem.get(ColourfulClocksDataComponentTypes.getPendulumData());
        setTrunkData(this.trunkData.getDoorType(), component);
    }

    public void setPendulum(PendulumComponent component) {
        setTrunkData(this.trunkData.getDoorType(), component);
    }

    public PendulumComponent removePendulumItem() {
        PendulumComponent removed = this.trunkData.getPendulum().orElse(PendulumComponent.getDefaultValue());
        setTrunkData(this.trunkData.getDoorType(), PendulumComponent.getDefaultValue());
        return removed;
    }

    public void setDoorType(BornholmDoorTypes doorType) {
        setTrunkData(doorType, this.trunkData.getPendulum().orElse(PendulumComponent.getDefaultValue()));
    }

    public void setData(BornholmMiddleDoorComponent data) {
        this.trunkData = data;
        setChanged();
    }

    public void setTrunkData(BornholmDoorTypes doorType, PendulumComponent pendulum) {
        this.trunkData = new BornholmMiddleDoorComponent(doorType, Optional.of(pendulum));
        setChanged();
    }

    public BornholmMiddleDoorComponent getData() {
        return this.trunkData;
    }

    public ItemStack getBlockAsItem(ClockTypes clockType) {
        ItemStack itemStack = BuiltInRegistries.ITEM.get(ColourfulClocksBlocks.BORNHOLM_MIDDLE.withSuffix(clockType.getSerializedName())).getDefaultInstance();
        itemStack.applyComponents(this.collectComponents());
        return itemStack;
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        super.collectImplicitComponents(components);
        components.set(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), this.trunkData);
    }

    @Override
    protected void applyImplicitComponents(BlockEntity.DataComponentInput componentInput) {
        super.applyImplicitComponents(componentInput);
        this.trunkData = componentInput.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), this.trunkData);
    }
}
