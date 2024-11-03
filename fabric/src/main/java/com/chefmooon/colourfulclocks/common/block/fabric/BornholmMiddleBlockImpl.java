package com.chefmooon.colourfulclocks.common.block.fabric;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractMap;
import java.util.function.Supplier;

public class BornholmMiddleBlockImpl extends BornholmMiddleBlock {
    public BornholmMiddleBlockImpl(WoodTypes woodTypes, BornholmDoorTypes bornholmDoorTypes, Properties properties) {
        super(woodTypes, bornholmDoorTypes, properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS.get().create(pos, state);
    }

    public static Supplier<Block> getDoorType(WoodTypes woodTypes, BornholmDoorTypes bornholmDoorTypes) {
        return ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes));
    }

    public static Supplier<Item> getWaxedCopperPendulum(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM;
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM;
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM;
        } else if (itemStack.is(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get())) {
            return ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM;
        } else {
            return ItemStack.EMPTY::getItem;
        }
    }

    public static Pair<Supplier<Item>, Supplier<SoundEvent>> getScrapedCopperPendulum(ItemStack itemStack) {
        // wax -> no wax
        if (itemStack.is(ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
            // previous weathered state
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        } else if (itemStack.is(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM, ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        }else {
            return new Pair<>(ItemStack.EMPTY::getItem, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        }
    }
}
