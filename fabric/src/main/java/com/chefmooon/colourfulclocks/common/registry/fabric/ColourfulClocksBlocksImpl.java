package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.block.fabric.BornholmBaseBlockImpl;
import com.chefmooon.colourfulclocks.common.block.fabric.BornholmMiddleBlockImpl;
import com.chefmooon.colourfulclocks.common.block.fabric.BornholmTopBlockImpl;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ColourfulClocksBlocksImpl {
    public static final HashMap<WoodTypes, Supplier<Block>> BORNHOLM_BASE_VARIANTS = registerBaseVariantsAll(ColourfulClocksItems.BORNHOLM_BASE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final HashMap<Map.Entry<WoodTypes, BornholmDoorTypes>, Supplier<Block>> BORNHOLM_MIDDLE_VARIANTS = registerMiddleVariantsAll(ColourfulClocksItems.BORNHOLM_MIDDLE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final HashMap<Map.Entry<WoodTypes, BornholmTopGlassTypes>, Supplier<Block>> BORNHOLM_TOP_VARIANTS = registerTopVariantsAll(ColourfulClocksItems.BORNHOLM_TOP, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));


    private static HashMap<WoodTypes, Supplier<Block>> registerBaseVariantsAll(ResourceLocation baseLocation, BlockBehaviour.Properties properties) {
        HashMap<WoodTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (WoodTypes woodTypes : WoodTypes.values()) {
            Block block = new BornholmBaseBlockImpl(properties);
            registerBlock(baseLocation.withSuffix(woodTypes.getSerializedName()), block);
            hashMap.put(woodTypes, () -> block);
        }
        return hashMap;
    }

    private static HashMap<Map.Entry<WoodTypes, BornholmDoorTypes>, Supplier<Block>> registerMiddleVariantsAll(ResourceLocation baseLocation, BlockBehaviour.Properties properties) {
        HashMap<Map.Entry<WoodTypes, BornholmDoorTypes>, Supplier<Block>> hashMap = new HashMap<>();
        for (WoodTypes woodTypes : WoodTypes.values()) {
            for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
                Block block = new BornholmMiddleBlockImpl(woodTypes, bornholmDoorTypes, properties);
                registerBlock(baseLocation.withSuffix(woodTypes.getSerializedName() + bornholmDoorTypes.getSerializedName()), block);
                hashMap.put(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes), () -> block);
            }
        }
        return hashMap;
    }

    private static HashMap<Map.Entry<WoodTypes, BornholmTopGlassTypes>, Supplier<Block>> registerTopVariantsAll(ResourceLocation baseLocation, BlockBehaviour.Properties properties) {
        HashMap<Map.Entry<WoodTypes, BornholmTopGlassTypes>, Supplier<Block>> hashMap = new HashMap<>();
        for (WoodTypes woodTypes : WoodTypes.values()) {
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                Block block = new BornholmTopBlockImpl(woodTypes, bornholmTopGlassTypes, properties);
                registerBlock(baseLocation.withSuffix(woodTypes.getSerializedName() + bornholmTopGlassTypes.getSerializedName()), block);
                hashMap.put(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes), () -> block);
            }
        }
        return hashMap;
    }

    public static Supplier<Block> registerBlock(final ResourceLocation location, final Block block) {
        Registry.register(BuiltInRegistries.BLOCK, location, block);
        return () -> block;
    }

    public static void register() {

    }
}
