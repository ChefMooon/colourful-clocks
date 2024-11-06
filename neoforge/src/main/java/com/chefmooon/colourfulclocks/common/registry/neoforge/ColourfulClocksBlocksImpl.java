package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.block.neoforge.BornholmBaseBlockImpl;
import com.chefmooon.colourfulclocks.common.block.neoforge.BornholmMiddleBlockImpl;
import com.chefmooon.colourfulclocks.common.block.neoforge.BornholmTopBlockImpl;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.function.Supplier;

public class ColourfulClocksBlocksImpl {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Registries.BLOCK, ColourfulClocks.MOD_ID);
    public static final HashMap<WoodTypes, Supplier<Block>> BORNHOLM_BASE_VARIANTS = registerBaseVariantsAll(ColourfulClocksBlocks.BORNHOLM_BASE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F).ignitedByLava());
    public static final HashMap<WoodTypes, Supplier<Block>> BORNHOLM_MIDDLE_VARIANTS = registerMiddleVariantsAll(ColourfulClocksBlocks.BORNHOLM_MIDDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F).ignitedByLava());
    public static final HashMap<WoodTypes, Supplier<Block>> BORNHOLM_TOP_VARIANTS = registerTopVariantsAll(ColourfulClocksBlocks.BORNHOLM_TOP, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F).ignitedByLava());

    private static HashMap<WoodTypes, Supplier<Block>> registerBaseVariantsAll(ResourceLocation baseLocation, BlockBehaviour.Properties properties) {
        HashMap<WoodTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (WoodTypes woodTypes : WoodTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(woodTypes.getSerializedName()), () -> new BornholmBaseBlockImpl(properties));
            hashMap.put(woodTypes, block);
        }
        return hashMap;
    }

    private static HashMap<WoodTypes, Supplier<Block>> registerMiddleVariantsAll(ResourceLocation baseLocation, BlockBehaviour.Properties properties) {
        HashMap<WoodTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (WoodTypes woodTypes : WoodTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(woodTypes.getSerializedName()),
                    () -> new BornholmMiddleBlockImpl(woodTypes, properties));
            hashMap.put(woodTypes, block);
        }
        return hashMap;
    }

    private static HashMap<WoodTypes, Supplier<Block>> registerTopVariantsAll(ResourceLocation baseLocation, BlockBehaviour.Properties properties) {
        HashMap<WoodTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (WoodTypes woodTypes : WoodTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(woodTypes.getSerializedName()),
                    () -> new BornholmTopBlockImpl(woodTypes, properties));
            hashMap.put(woodTypes, block);
        }
        return hashMap;
    }

    public static Supplier<Block> registerBlock(final ResourceLocation location, final Supplier<Block> block) {
        return BLOCKS.register(location.getPath(), block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
