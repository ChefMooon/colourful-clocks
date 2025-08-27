package com.chefmooon.colourfulclocks.common.fabric;

import com.chefmooon.colourfulclocks.common.block.*;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.world.level.block.Block;

public class CommonSetup {

    public static void init() {
        registerFlammableBlocks();
    }

    public static void registerFlammableBlocks() {
        ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.forEach((entry, supplier) -> {
            if (entry.isWooden()) addFlammableBlock(supplier.get(), BornholmBaseBlock.FLAMMABILITY, BornholmBaseBlock.FIRE_SPREAD);
        });

        ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.forEach((entry, supplier) -> {
            if (entry.isWooden()) addFlammableBlock(supplier.get(), BornholmMiddleBlock.FLAMMABILITY, BornholmMiddleBlock.FIRE_SPREAD);
        });

        ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.forEach((entry, supplier) -> {
            if (entry.isWooden()) addFlammableBlock(supplier.get(), BornholmTopBlock.FLAMMABILITY, BornholmTopBlock.FIRE_SPREAD);
        });

        ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.forEach((entry, supplier) -> {
            if (entry.isWooden()) addFlammableBlock(supplier.get(), MantelClockBlock.FLAMMABILITY, MantelClockBlock.FIRE_SPREAD);
        });

        ColourfulClocksBlocksImpl.TALL_MANTEL_CLOCK_VARIANTS.forEach((entry, supplier) -> {
            if (entry.isWooden()) addFlammableBlock(supplier.get(), TallMantelClockBlock.FLAMMABILITY, TallMantelClockBlock.FIRE_SPREAD);
        });
    }

    private static void addFlammableBlock(Block block, int burn, int spread) {
        FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread);
    }
}
