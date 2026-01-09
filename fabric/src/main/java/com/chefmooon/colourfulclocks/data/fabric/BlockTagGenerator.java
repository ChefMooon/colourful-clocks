package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider{
    public BlockTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.forEach(this::registerMineableClockBlocktags);
        ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.forEach(this::registerMineableClockBlocktags);
        ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.forEach(this::registerMineableClockBlocktags);
        ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.forEach(this::registerMineableClockBlocktags);
        ColourfulClocksBlocksImpl.TALL_MANTEL_CLOCK_VARIANTS.forEach(this::registerMineableClockBlocktags);
        ColourfulClocksBlocksImpl.WALL_CLOCK_VARIANTS.forEach(this::registerMineableClockBlocktags);
        ColourfulClocksBlocksImpl.ALARM_CLOCK_VARIANTS.forEach(this::registerMineableClockBlocktags);
        ColourfulClocksBlocksImpl.HANDBELL_VARIANTS.forEach(this::registerMineableHandbellBlocktags);
    }

    private void registerMineableClockBlocktags(ClockTypes clockType, Supplier<Block> supplier) {
        if (clockType.isWooden()) {
            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                    .add(supplier.get());
        } else {
            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(supplier.get());
        }
    }

    private void registerMineableHandbellBlocktags(HandbellTypes type, Supplier<Block> supplier) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(supplier.get());
    }
}
