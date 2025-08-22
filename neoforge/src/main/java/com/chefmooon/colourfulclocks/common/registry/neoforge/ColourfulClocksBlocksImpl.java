package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.block.neoforge.BornholmBaseBlockImpl;
import com.chefmooon.colourfulclocks.common.block.neoforge.BornholmMiddleBlockImpl;
import com.chefmooon.colourfulclocks.common.block.neoforge.BornholmTopBlockImpl;
import com.chefmooon.colourfulclocks.common.block.neoforge.MantelClockBlockImpl;
import com.chefmooon.colourfulclocks.common.data.types.ClockMaterialTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
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
    public static final HashMap<ClockTypes, Supplier<Block>> BORNHOLM_BASE_VARIANTS = registerBaseVariantsAll(ColourfulClocksBlocks.BORNHOLM_BASE);
    public static final HashMap<ClockTypes, Supplier<Block>> BORNHOLM_MIDDLE_VARIANTS = registerMiddleVariantsAll(ColourfulClocksBlocks.BORNHOLM_MIDDLE);
    public static final HashMap<ClockTypes, Supplier<Block>> BORNHOLM_TOP_VARIANTS = registerTopVariantsAll(ColourfulClocksBlocks.BORNHOLM_TOP);

    public static final HashMap<ClockTypes, Supplier<Block>> MANTEL_CLOCK_VARIANTS = registerMantelClockVariants(ColourfulClocksBlocks.MANTEL_CLOCK);

    private static HashMap<ClockTypes, Supplier<Block>> registerBaseVariantsAll(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    () -> new BornholmBaseBlockImpl(ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
        }
        return hashMap;
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerMiddleVariantsAll(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    () -> new BornholmMiddleBlockImpl(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
        }
        return hashMap;
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerTopVariantsAll(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    () -> new BornholmTopBlockImpl(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
        }
        return hashMap;
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerMantelClockVariants(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    () -> new MantelClockBlockImpl(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
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
