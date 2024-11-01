package com.chefmooon.colourfulclocks.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BornholmTopBlockEntity extends BlockEntity implements Container {
    private ItemStack clockHandsItem = ItemStack.EMPTY;
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
}
