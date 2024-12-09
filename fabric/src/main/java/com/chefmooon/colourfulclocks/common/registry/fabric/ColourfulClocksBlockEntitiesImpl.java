package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.block.entity.fabric.BornholmMiddleBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.block.entity.fabric.BornholmTopBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class ColourfulClocksBlockEntitiesImpl {

    public static final BlockEntityType<BornholmMiddleBlockEntityImpl> BORNHOLM_MIDDLE_VARIANTS = registerBlockEntity(ColourfulClocksBlockEntities.BORNHOLM_MIDDLE,
            BlockEntityType.Builder.of(BornholmMiddleBlockEntityImpl::new,
                    ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new))
            );

    public static final BlockEntityType<BornholmTopBlockEntityImpl> BORNHOLM_TOP_VARIANTS = registerBlockEntity(ColourfulClocksBlockEntities.BORNHOLM_TOP,
            BlockEntityType.Builder.of(BornholmTopBlockEntityImpl::new,
                    ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
            ));

    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(ResourceLocation location, BlockEntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, location, builder.build(null));
    }

//    public static final Supplier<BlockEntityType<BornholmMiddleBlockEntityImpl>> BORNHOLM_MIDDLE_VARIANTS = registerBlockEntity(ColourfulClocksBlockEntities.BORNHOLM_MIDDLE,
//            BlockEntityType.Builder.of(BornholmMiddleBlockEntityImpl::new,
//                    ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new))
//    );
//
//    public static final Supplier<BlockEntityType<BornholmTopBlockEntityImpl>> BORNHOLM_TOP_VARIANTS = registerBlockEntity(ColourfulClocksBlockEntities.BORNHOLM_TOP,
//            BlockEntityType.Builder.of(BornholmTopBlockEntityImpl::new,
//                    ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
//            ));

//    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntity(ResourceLocation location, BlockEntityType.Builder<T> builder) {
//        return Suppliers.memoize(() -> Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, location, builder.build(null)));
//    }

    public static void register() {
    }
}
