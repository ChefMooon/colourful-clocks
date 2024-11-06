package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.CopyBlockState;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

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
            this.add(supplier.get(), createBornholmMiddleEntityDrop(supplier.get()));
//            dropSelf(supplier.get());
        });

        ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.forEach((entry, supplier) -> {
            this.add(supplier.get(), createBornholmTopEntityDrop(supplier.get()));
//            createBornholmTopEntityDrop(supplier.get());
//            dropSelf(supplier.get());
        });
    }

    public LootTable.Builder createBornholmMiddleEntityDrop(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(((LootPoolSingletonContainer.Builder)LootItem.lootTableItem(block).when(this.hasSilkTouch())).apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)).apply(CopyBlockState.copyState(block).copy(BornholmMiddleBlock.DOOR_TYPE)).otherwise(LootItem.lootTableItem(block))));
    }

    public LootTable.Builder createBornholmTopEntityDrop(Block block) {
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(((LootPoolSingletonContainer.Builder)LootItem.lootTableItem(block).when(this.hasSilkTouch())).apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)).apply(CopyBlockState.copyState(block).copy(BornholmTopBlock.GLASS_TYPE)).otherwise(LootItem.lootTableItem(block))));
    }
}
