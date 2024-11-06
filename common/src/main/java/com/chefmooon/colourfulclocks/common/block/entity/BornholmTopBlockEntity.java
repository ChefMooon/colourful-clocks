package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
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

public class BornholmTopBlockEntity extends BlockEntity implements Container {
    private ItemStack clockHandsItem = ItemStack.EMPTY;

    public static final int WEATHERED_THRESHOLD = 6000; // 5 min to weather
    public BornholmTopBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return clockHandsItem.isEmpty();
    }

    @Override
    public ItemStack getItem(int slot) {
        return clockHandsItem;
    }

    public void setClockHandsItem(ItemStack stack) {
        setItem(0, stack.split(1));
    }

    public ItemStack getClockHandsItem() {
        return clockHandsItem;
    }

    public NonNullList<ItemStack> getDroppableInventory() {
        NonNullList<ItemStack> drops = NonNullList.create();
        drops.add(getClockHandsItem());
        return drops;
    }

    @Override
    public ItemStack removeItem(int slot, int amount) {
        return removeItemNoUpdate(0);
    }

    @Override
    public ItemStack removeItemNoUpdate(int slot) {
        ItemStack stored = clockHandsItem;
        clearContent();
        return stored;
    }

    @Override
    public void setItem(int slot, ItemStack stack) {
        clockHandsItem = stack.split(1);
        setChanged();
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void clearContent() {
        clockHandsItem = ItemStack.EMPTY;
        setChanged();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);

        if (tag.contains("clock_hands")) {
            CompoundTag clockHandsItemTag = tag.getCompound("clock_hands");
            clockHandsItem = ItemStack.parse(provider, clockHandsItemTag).orElse(ItemStack.EMPTY);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        if (!clockHandsItem.isEmpty()) {
            tag.put("clock_hands", clockHandsItem.save(provider, new CompoundTag()));
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

    public static void weatherTick(Level level, BlockPos blockPos, BlockState blockState, BornholmTopBlockEntity bornholmTopBlockEntity) {
        if (blockState.getValue(BornholmTopBlock.ACTIVATED)) {
            weatherItem(level, blockPos, bornholmTopBlockEntity);

        }
    }

    private static void weatherItem(Level level, BlockPos blockPos, BornholmTopBlockEntity bornholmTopBlockEntity) {
        ItemStack itemStack = bornholmTopBlockEntity.getClockHandsItem();
        if (!itemStack.isEmpty()) {
            if (isCopperClockHands(itemStack)) {
                if (itemStack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING)) != null) {
                    Integer weathering = itemStack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING));
                    if (weathering >= WEATHERED_THRESHOLD) {
                        advanceWeathering(level, blockPos, itemStack, bornholmTopBlockEntity);
                    } else {
                        itemStack.set((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_WEATHERING), weathering + 1);
                    }
                }
            }
        }
    }

    private static void advanceWeathering(Level level, BlockPos blockPos, ItemStack itemStack, BornholmTopBlockEntity bornholmTopBlockEntity) {
        ItemStack weatheredItemStack = new ItemStack(getNextWeatheredCopperItem(itemStack).get());
        if (!weatheredItemStack.isEmpty()) {
            bornholmTopBlockEntity.setClockHandsItem(weatheredItemStack);
            level.blockEntityChanged(blockPos);
            bornholmTopBlockEntity.setChanged();
        }
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
