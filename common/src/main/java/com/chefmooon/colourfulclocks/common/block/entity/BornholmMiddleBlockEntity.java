package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class BornholmMiddleBlockEntity extends BlockEntity implements Container {
    private ItemStack pendelumItem = ItemStack.EMPTY;
    private ItemStack doorItem = ItemStack.EMPTY; // TODO - remove this? door change implementation no longer requires this, use elsewhere?
    // todo - should the base frequency be configurable? clock hand type changes frequency?
    private int baseFrequency = 6000;
    private int baseFrequencyMax = 6000;  // 6000 ticks = effect chance every 5 minutes
    protected static int baseEffectRange = 1;

    private static final int WEATHERED_THRESHOLD = 6000; // 5 min to weather
    private int weatheringProgress = 0;

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

        weatheringProgress = tag.getInt("weatheringProgress");
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
        tag.putInt("weatheringProgress", weatheringProgress);
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
        // todo - finish effects and enable them
        //tryTemporalTimeEffect(level, blockPos, blockState, bornholmMiddleBlockEntity);

        if (blockState.getValue(BornholmMiddleBlock.ACTIVATED)) {
            weather(level, blockPos, bornholmMiddleBlockEntity);
            if (!bornholmMiddleBlockEntity.getPendelumItem().isEmpty()) sound(level, blockPos, bornholmMiddleBlockEntity);
        }
    }

    private static void weather(Level level, BlockPos blockPos, BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
        ItemStack itemStack = bornholmMiddleBlockEntity.getPendelumItem();
        if (!itemStack.isEmpty()) {
            if (isCopperPendulum(itemStack)) {
                bornholmMiddleBlockEntity.weatheringProgress++;
                if (bornholmMiddleBlockEntity.weatheringProgress >= WEATHERED_THRESHOLD) {
                    advanceWeathering(level, blockPos, itemStack, bornholmMiddleBlockEntity);
                    bornholmMiddleBlockEntity.weatheringProgress = 0;
                }
            }
        } else {
            bornholmMiddleBlockEntity.weatheringProgress = 0;
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

        ItemStack pendulum = bornholmTopBlockEntity.getPendelumItem();

        float pitch = getClockHandPitchModifier(pendulum);

        long timeOfDay = level.getGameTime() % 24000;

        if (timeOfDay == 6000 || timeOfDay == 18000) {
            level.playSound(null, blockPos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHIME.get(), SoundSource.BLOCKS, 1.0F, pitch);
        }
    }

    public static void tryTemporalTimeEffect(Level level, BlockPos blockPos, BlockState blockState, BornholmMiddleBlockEntity bornholmMiddleBlockEntity) {
        // todo - add a config option to turn off Temporal Effects?
        if (level.isClientSide()) {
            return;
        }
        if (blockState.getBlock() instanceof BornholmMiddleBlock) {
            if (!blockState.getValue(BornholmMiddleBlock.ACTIVATED)) {
                return;
            }
        }
        if (bornholmMiddleBlockEntity.baseFrequency > 0) {
            bornholmMiddleBlockEntity.baseFrequency--;
            return;
        }
        int range = baseEffectRange;
        if (level.getBlockEntity(blockPos.above()) instanceof BornholmTopBlockEntity bornholmTopBlockEntity) {
            range = getRangeFromClockHandsItem(bornholmTopBlockEntity.getClockHandsItem());
        }

        // todo only performs 1 effect setup more types of effects and the pendulum will change the type of effect?
        tryPerformBonemealEffect(level, blockPos, range);

        bornholmMiddleBlockEntity.baseFrequency = bornholmMiddleBlockEntity.baseFrequencyMax;
    }


    // todo - once more effects are added move these methods to a new interface AbstractTemporalEffects
    public static void tryPerformBonemealEffect(Level level, BlockPos blockPos, int range) {
        for (BlockPos effectBlockPos : BlockPos.betweenClosed(blockPos.offset(-range, -1, -range), blockPos.offset(range, 0, range))) {
            BlockState effectBlockState = level.getBlockState(effectBlockPos);
            if (effectBlockState.getBlock() instanceof CropBlock cropBlock) {
                // todo - make this a chance to effect the block not every time
                cropBlock.performBonemeal((ServerLevel) level, level.random, effectBlockPos, effectBlockState);
            }
        }
    }

    @ExpectPlatform
    public static int getRangeFromClockHandsItem(ItemStack itemStack) {
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

    @ExpectPlatform
    public static float getClockHandPitchModifier(ItemStack itemStack) {
        throw new AssertionError();
    }
}
