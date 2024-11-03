package com.chefmooon.colourfulclocks.common.block.neoforge;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.entity.neoforge.BornholmTopBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlocksImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksItemsImpl;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractMap;
import java.util.function.Supplier;

public class BornholmTopBlockImpl extends BornholmTopBlock {
    public BornholmTopBlockImpl(WoodTypes woodTypes, BornholmTopGlassTypes bornholmTopGlassTypes, Properties properties) {
        super(woodTypes, bornholmTopGlassTypes, properties);
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return FLAMMABILITY;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return FIRE_SPREAD;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS.get().create(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return createTickerHelper(blockEntity, ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS.get(), BornholmTopBlockEntityImpl::weatherTick);
    }

    public static Supplier<Block> getGlassType(WoodTypes woodTypes, BornholmTopGlassTypes bornholmTopGlassTypes) {
        return ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes));
    }

    public static Supplier<Item> getWaxedClockHands(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get())) {
            return ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH;
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get())) {
            return ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH;
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get())) {
            return ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH;
        } else if (itemStack.is(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get())) {
            return ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH;
        } else {
            return ItemStack.EMPTY::getItem;
        }
    }

    public static Pair<Supplier<Item>, Supplier<SoundEvent>> getScrapedClockHands(ItemStack itemStack) {
        // wax -> no wax
        if (itemStack.is(ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
            // previous weathered state
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH, ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH, ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        } else if (itemStack.is(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get())) {
            return new Pair<>(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH, ColourfulClocksSounds.BLOCK_BORNHOLM_AXE_SCRAPES);
        }else {
            return new Pair<>(ItemStack.EMPTY::getItem, ColourfulClocksSounds.BLOCK_BORNHOLM_WAX_OFF);
        }
    }
}
