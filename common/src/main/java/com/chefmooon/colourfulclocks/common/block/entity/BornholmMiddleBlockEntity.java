package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.core.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.BornholmTypeUtil;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentType;
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
    private ItemStack doorItem = ItemStack.EMPTY; // TODO - remove this? door change implementation no longer requires this, use elsewhere?
    private static boolean hasChimed = false;

    public static final int WEATHERED_THRESHOLD = 6000; // 5 min to weather

    public BornholmMiddleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public int getContainerSize() {
        return 2;
    }

    @Override
    public boolean isEmpty() {
        return getItem(0).isEmpty() && getItem(1).isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        if (slot == 0) {
            return pendelumItem;
        } else if (slot == 1) {
            return doorItem;
        }
        return ItemStack.EMPTY;
    }

    public void setPendelumItem(ItemStack stack) {
        setItem(0, stack.split(1));
        setChanged();
    }

    public ItemStack getPendelumItem() {
        return pendelumItem;
    }

    public void setDoorItem(ItemStack stack) {
        setItem(1, stack.split(1));
        setChanged();
    }

    public ItemStack getDoorItem() {
        return doorItem;
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
        } else if (slot == 1) {
            stored =  doorItem;
        }
        clearContent();
        return stored;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        if (slot == 0) {
            pendelumItem = stack.split(1);
        } else if (slot == 1) {
            doorItem =  stack.split(1);
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
        doorItem = ItemStack.EMPTY;
        setChanged();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);

        if (tag.contains("pendelum_item")) {
            CompoundTag pendelumItemTag = tag.getCompound("pendelum_item");
            pendelumItem = ItemStack.parse(provider, pendelumItemTag).orElse(ItemStack.EMPTY);
        }
        if (tag.contains("door_item")) {
            CompoundTag doorItemTag = tag.getCompound("door_item");
            doorItem = ItemStack.parse(provider, doorItemTag).orElse(ItemStack.EMPTY);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        if (!pendelumItem.isEmpty()) {
            tag.put("pendelum_item", pendelumItem.save(provider, new CompoundTag()));
        }
        if (!doorItem.isEmpty()) {
            tag.put("door_item", doorItem.save(provider, new CompoundTag()));
        }

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
                    Integer weathering = itemStack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING));
                    if (weathering >= WEATHERED_THRESHOLD) {
                        advanceWeathering(level, blockPos, itemStack, bornholmMiddleBlockEntity);
                    } else {
                        itemStack.set((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING), weathering + 1);
                    }
                }
            }
        }
    }

    private static void advanceWeathering(Level level, BlockPos blockPos, ItemStack itemStack, BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(getNextWeatheredCopperItem(itemStack).get());
        if (!weatheredItemStack.isEmpty()) {
            bornholmMiddleBlockEntity.setPendelumItem(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            bornholmMiddleBlockEntity.setChanged();
        }
    }

    private static void sound(Level level, BlockPos blockPos, BornholmMiddleBlockEntity bornholmTopBlockEntity) {
        if (level == null || level.isClientSide()) return;

        PendulumTypes pendulumType = BornholmTypeUtil.getPendulumTypeFromItem(bornholmTopBlockEntity.getPendelumItem().getItem());

        long timeOfDay = level.getDayTime() % 24000;

        if ((timeOfDay == 6000 || timeOfDay == 18000) && !hasChimed) {
            level.playSound(null, blockPos, pendulumType.getChimeSound(), SoundSource.BLOCKS, 1.0F, pendulumType.getPitchModifier());
            hasChimed = true;
        } else if (timeOfDay == 6001 || timeOfDay == 18001) {
            hasChimed = false;
        }
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
