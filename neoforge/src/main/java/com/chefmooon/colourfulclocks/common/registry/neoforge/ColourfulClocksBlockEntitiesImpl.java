package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.block.entity.neoforge.BornholmMiddleBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.block.entity.neoforge.BornholmTopBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ColourfulClocksBlockEntitiesImpl {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ColourfulClocks.MOD_ID);

    public static final Supplier<BlockEntityType<BornholmMiddleBlockEntityImpl>> BORNHOLM_MIDDLE_VARIANTS = BLOCK_ENTITIES.register(ColourfulClocksBlockEntities.BORNHOLM_MIDDLE.getPath(),
            () -> BlockEntityType.Builder.of(BornholmMiddleBlockEntityImpl::new,
                    ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
            ).build(null));

    public static final Supplier<BlockEntityType<BornholmTopBlockEntityImpl>> BORNHOLM_TOP_VARIANTS = BLOCK_ENTITIES.register(ColourfulClocksBlockEntities.BORNHOLM_TOP.getPath(),
            () -> BlockEntityType.Builder.of(BornholmTopBlockEntityImpl::new,
                    ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
            ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
