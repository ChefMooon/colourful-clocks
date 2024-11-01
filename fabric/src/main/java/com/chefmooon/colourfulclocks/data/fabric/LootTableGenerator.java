package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class LootTableGenerator extends FabricBlockLootTableProvider {
    public LootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.forEach((entry, supplier) -> {
            dropSelf(supplier.get());
        });

        ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.forEach((entry, supplier) -> {
            dropSelf(supplier.get());
        });

        ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.forEach((entry, supplier) -> {
            dropSelf(supplier.get());
        });
    }
}
