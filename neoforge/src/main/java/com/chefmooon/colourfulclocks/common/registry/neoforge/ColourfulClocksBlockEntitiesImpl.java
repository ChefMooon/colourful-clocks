package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.block.entity.*;
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

    public static final Supplier<BlockEntityType<HandbellBlockEntity>> HANDBELL_VARIANTS = BLOCK_ENTITIES.register(ColourfulClocksBlockEntities.HANDBELL.getPath(),
            () -> BlockEntityType.Builder.of(HandbellBlockEntity::new,
                    ColourfulClocksBlocksImpl.HANDBELL_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)).build(null));

    public static final Supplier<BlockEntityType<BornholmMiddleBlockEntityImpl>> BORNHOLM_MIDDLE_VARIANTS = BLOCK_ENTITIES.register(ColourfulClocksBlockEntities.BORNHOLM_MIDDLE.getPath(),
            () -> BlockEntityType.Builder.of(BornholmMiddleBlockEntityImpl::new,
                    ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
            ).build(null));

    public static final Supplier<BlockEntityType<BornholmTopBlockEntityImpl>> BORNHOLM_TOP_VARIANTS = BLOCK_ENTITIES.register(ColourfulClocksBlockEntities.BORNHOLM_TOP.getPath(),
            () -> BlockEntityType.Builder.of(BornholmTopBlockEntityImpl::new,
                    ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
            ).build(null));

    public static final Supplier<BlockEntityType<MantelClockBlockEntity>> MANTEL_CLOCK_VARIANTS = BLOCK_ENTITIES.register(ColourfulClocksBlockEntities.MANTEL_CLOCK.getPath(),
            () -> BlockEntityType.Builder.of(MantelClockBlockEntity::new,
                    ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
            ).build(null));

    public static final Supplier<BlockEntityType<TallMantelClockBlockEntity>> TALL_MANTEL_CLOCK_VARIANTS = BLOCK_ENTITIES.register(ColourfulClocksBlockEntities.TALL_MANTEL_CLOCK.getPath(),
            () -> BlockEntityType.Builder.of(TallMantelClockBlockEntity::new,
                    ColourfulClocksBlocksImpl.TALL_MANTEL_CLOCK_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
            ).build(null));

    public static final Supplier<BlockEntityType<WallClockBlockEntity>> WALL_CLOCK_VARIANTS = BLOCK_ENTITIES.register(ColourfulClocksBlockEntities.WALL_CLOCK.getPath(),
            () -> BlockEntityType.Builder.of(WallClockBlockEntity::new,
                    ColourfulClocksBlocksImpl.WALL_CLOCK_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
            ).build(null));

    public static final Supplier<BlockEntityType<AlarmClockBlockEntity>> ALARM_CLOCK_VARIANTS = BLOCK_ENTITIES.register(ColourfulClocksBlockEntities.ALARM_CLOCK.getPath(),
            () -> BlockEntityType.Builder.of(AlarmClockBlockEntity::new,
                    ColourfulClocksBlocksImpl.ALARM_CLOCK_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
            ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
