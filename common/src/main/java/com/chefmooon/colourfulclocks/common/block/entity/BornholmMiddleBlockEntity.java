package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.CopperWeatheringUtil;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BornholmMiddleBlockEntity extends BlockEntity implements Container {
    private ItemStack pendelumItem = ItemStack.EMPTY;
    private BornholmMiddleDoorComponent trunkData;
    private static boolean hasChimed = false;

    public BornholmMiddleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
        this.trunkData = BornholmMiddleDoorComponent.getDefaultValue();
    }

    @Override
    public int getContainerSize() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return getItem(0).isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        if (slot == 0) {
            return pendelumItem;
        }
        return ItemStack.EMPTY;
    }

    public void setPendelumItem(ItemStack stack) {
        setItem(0, stack.split(1));
    }

    public ItemStack removePendulumItem() {
        ItemStack stack = pendelumItem;
        removeItem(0, 1);
        setTrunkData(this.trunkData.getDoorType(), PendulumTypes.EMPTY);
        setPendulumType(ItemStack.EMPTY);
        return stack;
    }

    public ItemStack getPendelumItem() {
        return pendelumItem;
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        drops.add(getPendelumItem());
        return drops;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return removeItemNoUpdate(slot);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack stored = ItemStack.EMPTY;
        if (slot == 0) {
            stored = pendelumItem;
        }
        clearContent();
        return stored;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot == 0) {
            pendelumItem = stack.split(1);
        }
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        pendelumItem = ItemStack.EMPTY;
        setChanged();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);

        this.trunkData = BornholmMiddleDoorComponent.load(tag);
        if (tag.contains("pendelum_item")) { // Legacy data support
            CompoundTag pendelumItemTag = tag.getCompound("pendelum_item");
            pendelumItem = ItemStack.parse(provider, pendelumItemTag).orElse(ItemStack.EMPTY);
            setPendelumItem(pendelumItem);
        } else {
            if (trunkData.pendulumType() != PendulumTypes.EMPTY) {
                setPendelumItem(new ItemStack(ColourfulClocksTypeUtil.getPendulumItemFromType(trunkData.pendulumType())));
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
            if (!bornholmMiddleBlockEntity.getPendelumItem().isEmpty()) sound(level, blockPos, bornholmMiddleBlockEntity);
        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
        ItemStack itemStack = bornholmMiddleBlockEntity.getPendelumItem();
        if (!itemStack.isEmpty()) {
            if (isCopperPendulum(itemStack)) {
                if (itemStack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING)) != null) {
                    Integer weathering = itemStack.get(ColourfulClocksDataComponentTypes.getPendulumWeatheringData());
                    if (weathering >= CopperWeatheringUtil.WEATHERED_THRESHOLD) {
                        advanceWeathering(level, blockPos, itemStack, bornholmMiddleBlockEntity);
                    } else {
                        itemStack.set(ColourfulClocksDataComponentTypes.getPendulumWeatheringData(), weathering + 1);
                    }
                }
            }
        }
    }

    private static void advanceWeathering(Level level, BlockPos blockPos, ItemStack itemStack, BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(getNextWeatheredCopperItem(itemStack).get());
        if (!weatheredItemStack.isEmpty()) {
            bornholmMiddleBlockEntity.setPendulumType(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            bornholmMiddleBlockEntity.setChanged();
            if (!level.isClientSide()) {
                BlockState state = level.getBlockState(blockPos);
                level.sendBlockUpdated(blockPos, state, state, 3);
            }
        }
    }

    private static void sound(Level level, BlockPos blockPos, BornholmMiddleBlockEntity bornholmTopBlockEntity) {
        if (level == null || level.isClientSide()) return;

        PendulumTypes pendulumType = ColourfulClocksTypeUtil.getPendulumTypeFromItem(bornholmTopBlockEntity.getPendelumItem().getItem());

        long timeOfDay = level.getDayTime() % 24000;

        if ((timeOfDay == 6000 || timeOfDay == 18000) && !hasChimed) {
            level.playSound(null, blockPos, pendulumType.getChimeSound().get(), SoundSource.BLOCKS, 1.0F, pendulumType.getPitchModifier());
            hasChimed = true;
        } else if (timeOfDay == 6001 || timeOfDay == 18001) {
            hasChimed = false;
        }
    }

    public void setPendulumType(ItemStack pendulumItem) {
        setTrunkData(this.trunkData.getDoorType(), ColourfulClocksTypeUtil.getPendulumTypeFromItem(pendulumItem.getItem()));
        setPendelumItem(pendulumItem);
    }

    public void setDoorType(BornholmDoorTypes doorType) {
        setTrunkData(doorType, this.trunkData.getPendulumType());
    }

    public void setTrunkData(BornholmDoorTypes doorType, PendulumTypes pendulumType) {
        this.trunkData = new BornholmMiddleDoorComponent(doorType, pendulumType);
        setChanged();
    }

    public BornholmMiddleDoorComponent getTrunkData() {
        return this.trunkData;
    }

    public ItemStack getBlockAsItem(ClockTypes clockType) {
        ItemStack itemStack = getItemStack(clockType).getDefaultInstance();
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

    @ExpectPlatform
    public static Item getItemStack(ClockTypes clockTypes) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isCopperPendulum(ItemStack itemStack) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Supplier<Item> getNextWeatheredCopperItem(ItemStack itemStack) {
        throw new AssertionError();
    }
}
