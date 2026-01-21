package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
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
        ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.forEach(this::registerBornholmBaseBlockTags);
        ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.forEach(this::registerBornholmTrunkBlockTags);
        ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.forEach(this::registerBornholmDialBlockTags);
        ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.forEach(this::registerMantelClockBlockTags);
        ColourfulClocksBlocksImpl.TALL_MANTEL_CLOCK_VARIANTS.forEach(this::registerTallMantelClockBlockTags);
        ColourfulClocksBlocksImpl.WALL_CLOCK_VARIANTS.forEach(this::registerWallClockBlockTags);
        ColourfulClocksBlocksImpl.ALARM_CLOCK_VARIANTS.forEach(this::registerAlarmClockBlockTags);
        ColourfulClocksBlocksImpl.HANDBELL_VARIANTS.forEach(this::registerHandbellBlockTags);
    }

    private void registerBornholmBaseBlockTags(ClockTypes clockType, Supplier<Block> supplier) {
        getOrCreateTagBuilder(ColourfulClocksTags.BLOCK_BORNHOLM_BASE).add(supplier.get());
        registerMineableClockBlockTags(clockType, supplier);
    }

    private void registerBornholmTrunkBlockTags(ClockTypes clockType, Supplier<Block> supplier) {
        getOrCreateTagBuilder(ColourfulClocksTags.BLOCK_BORNHOLM_TRUNK).add(supplier.get());
        registerMineableClockBlockTags(clockType, supplier);
    }

    private void registerBornholmDialBlockTags(ClockTypes clockType, Supplier<Block> supplier) {
        getOrCreateTagBuilder(ColourfulClocksTags.BLOCK_BORNHOLM_DIAL).add(supplier.get());
        registerMineableClockBlockTags(clockType, supplier);
    }

    private void registerMantelClockBlockTags(ClockTypes clockType, Supplier<Block> supplier) {
        getOrCreateTagBuilder(ColourfulClocksTags.BLOCK_MANTEL_CLOCK).add(supplier.get());
        registerMineableClockBlockTags(clockType, supplier);
    }

    private void registerTallMantelClockBlockTags(ClockTypes clockType, Supplier<Block> supplier) {
        getOrCreateTagBuilder(ColourfulClocksTags.BLOCK_TALL_MANTEL_CLOCK).add(supplier.get());
        registerMineableClockBlockTags(clockType, supplier);
    }

    private void registerWallClockBlockTags(ClockTypes clockType, Supplier<Block> supplier) {
        getOrCreateTagBuilder(ColourfulClocksTags.BLOCK_WALL_CLOCK).add(supplier.get());
        registerMineableClockBlockTags(clockType, supplier);
    }

    private void registerAlarmClockBlockTags(ClockTypes clockType, Supplier<Block> supplier) {
        getOrCreateTagBuilder(ColourfulClocksTags.BLOCK_ALARM_CLOCK).add(supplier.get());
        registerMineableClockBlockTags(clockType, supplier);
    }

    private void registerHandbellBlockTags(HandbellTypes type, Supplier<Block> supplier) {
        getOrCreateTagBuilder(ColourfulClocksTags.BLOCK_HANDBELL).add(supplier.get());
        registerMineableHandbellBlockTags(type, supplier);
    }

    private void registerMineableClockBlockTags(ClockTypes clockType, Supplier<Block> supplier) {
        if (clockType.isWooden()) {
            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
                    .add(supplier.get());
        } else {
            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(supplier.get());
        }
    }

    private void registerMineableHandbellBlockTags(HandbellTypes type, Supplier<Block> supplier) {
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(supplier.get());
    }
}
