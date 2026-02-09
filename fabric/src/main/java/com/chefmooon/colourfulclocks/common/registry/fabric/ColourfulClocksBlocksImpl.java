package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.block.*;
import com.chefmooon.colourfulclocks.common.block.fabric.BornholmBaseBlockImpl;
import com.chefmooon.colourfulclocks.common.block.fabric.BornholmMiddleBlockImpl;
import com.chefmooon.colourfulclocks.common.block.fabric.BornholmTopBlockImpl;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;

import java.util.HashMap;
import java.util.function.Supplier;

public class ColourfulClocksBlocksImpl {
    public static final HashMap<ClockTypes, Supplier<Block>> BORNHOLM_BASE_VARIANTS = registerBaseVariantsAll(ColourfulClocksBlocks.BORNHOLM_BASE);
    public static final HashMap<ClockTypes, Supplier<Block>> BORNHOLM_MIDDLE_VARIANTS = registerMiddleVariantsAll(ColourfulClocksBlocks.BORNHOLM_MIDDLE);
    public static final HashMap<ClockTypes, Supplier<Block>> BORNHOLM_TOP_VARIANTS = registerTopVariantsAll(ColourfulClocksBlocks.BORNHOLM_TOP);

    public static final HashMap<ClockTypes, Supplier<Block>> MANTEL_CLOCK_VARIANTS = registerMantelClockVariants(ColourfulClocksBlocks.MANTEL_CLOCK);
    public static final HashMap<ClockTypes, Supplier<Block>> TALL_MANTEL_CLOCK_VARIANTS = registerTallMantelClockVariants(ColourfulClocksBlocks.TALL_MANTEL_CLOCK);
    public static final HashMap<ClockTypes, Supplier<Block>> WALL_CLOCK_VARIANTS = registerWallClockVariants(ColourfulClocksBlocks.WALL_CLOCK);
    public static final HashMap<ClockTypes, Supplier<Block>> ALARM_CLOCK_VARIANTS = registerAlarmClockVariants(ColourfulClocksBlocks.ALARM_CLOCK);

    public static final Supplier<Block> IRON_HANDBELL = registerBlock(ColourfulClocksBlocks.IRON_HANDBELL,
            new HandbellBlock(HandbellTypes.IRON, ColourfulClocksBlocks.getHandbellProperties(Blocks.IRON_BLOCK)));
    public static final Supplier<Block> COPPER_HANDBELL = registerBlock(ColourfulClocksBlocks.COPPER_HANDBELL,
            new WeatheringCopperHandbellBlock(WeatheringCopper.WeatherState.UNAFFECTED, HandbellTypes.COPPER, ColourfulClocksBlocks.getHandbellProperties(Blocks.COPPER_BLOCK)));
    public static final Supplier<Block> EXPOSED_COPPER_HANDBELL = registerBlock(ColourfulClocksBlocks.EXPOSED_COPPER_HANDBELL,
            new WeatheringCopperHandbellBlock(WeatheringCopper.WeatherState.EXPOSED, HandbellTypes.EXPOSED_COPPER, ColourfulClocksBlocks.getHandbellProperties(Blocks.EXPOSED_COPPER)));
    public static final Supplier<Block> WEATHERED_COPPER_HANDBELL = registerBlock(ColourfulClocksBlocks.WEATHERED_COPPER_HANDBELL,
            new WeatheringCopperHandbellBlock(WeatheringCopper.WeatherState.WEATHERED, HandbellTypes.WEATHERED_COPPER, ColourfulClocksBlocks.getHandbellProperties(Blocks.WEATHERED_COPPER)));
    public static final Supplier<Block> OXIDIZED_COPPER_HANDBELL = registerBlock(ColourfulClocksBlocks.OXIDIZED_COPPER_HANDBELL,
            new WeatheringCopperHandbellBlock(WeatheringCopper.WeatherState.OXIDIZED, HandbellTypes.OXIDIZED_COPPER, ColourfulClocksBlocks.getHandbellProperties(Blocks.OXIDIZED_COPPER)));
    public static final Supplier<Block> WAXED_COPPER_HANDBELL = registerBlock(ColourfulClocksBlocks.WAXED_COPPER_HANDBELL,
            new HandbellBlock(HandbellTypes.COPPER, ColourfulClocksBlocks.getHandbellProperties(Blocks.WAXED_COPPER_BLOCK)));
    public static final Supplier<Block> WAXED_EXPOSED_COPPER_HANDBELL = registerBlock(ColourfulClocksBlocks.WAXED_EXPOSED_COPPER_HANDBELL,
            new HandbellBlock(HandbellTypes.EXPOSED_COPPER, ColourfulClocksBlocks.getHandbellProperties(Blocks.WAXED_EXPOSED_COPPER)));
    public static final Supplier<Block> WAXED_WEATHERED_COPPER_HANDBELL = registerBlock(ColourfulClocksBlocks.WAXED_WEATHERED_COPPER_HANDBELL,
            new HandbellBlock(HandbellTypes.WEATHERED_COPPER, ColourfulClocksBlocks.getHandbellProperties(Blocks.WAXED_WEATHERED_COPPER)));
    public static final Supplier<Block> WAXED_OXIDIZED_COPPER_HANDBELL = registerBlock(ColourfulClocksBlocks.WAXED_OXIDIZED_COPPER_HANDBELL,
            new HandbellBlock(HandbellTypes.OXIDIZED_COPPER, ColourfulClocksBlocks.getHandbellProperties(Blocks.WAXED_OXIDIZED_COPPER)));
    public static final Supplier<Block> GOLD_HANDBELL = registerBlock(ColourfulClocksBlocks.GOLD_HANDBELL,
            new HandbellBlock(HandbellTypes.GOLD, ColourfulClocksBlocks.getHandbellProperties(Blocks.GOLD_BLOCK)));
    public static final Supplier<Block> DIAMOND_HANDBELL = registerBlock(ColourfulClocksBlocks.DIAMOND_HANDBELL,
            new HandbellBlock(HandbellTypes.DIAMOND, ColourfulClocksBlocks.getHandbellProperties(Blocks.DIAMOND_BLOCK)));
    public static final Supplier<Block> NETHERITE_HANDBELL = registerBlock(ColourfulClocksBlocks.NETHERITE_HANDBELL,
            new HandbellBlock(HandbellTypes.NETHERITE, ColourfulClocksBlocks.getHandbellProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<Block> QUARTZ_HANDBELL = registerBlock(ColourfulClocksBlocks.QUARTZ_HANDBELL,
            new HandbellBlock(HandbellTypes.QUARTZ, ColourfulClocksBlocks.getHandbellProperties(Blocks.QUARTZ_BLOCK)));
    public static final Supplier<Block> AMETHYST_HANDBELL = registerBlock(ColourfulClocksBlocks.AMETHYST_HANDBELL,
            new HandbellBlock(HandbellTypes.AMETHYST, ColourfulClocksBlocks.getHandbellProperties(Blocks.AMETHYST_BLOCK)));
    public static final Supplier<Block> LAPIS_LAZULI_HANDBELL = registerBlock(ColourfulClocksBlocks.LAPIS_LAZULI_HANDBELL,
            new HandbellBlock(HandbellTypes.LAPIS_LAZULI, ColourfulClocksBlocks.getHandbellProperties(Blocks.LAPIS_BLOCK)));
    public static final Supplier<Block> REDSTONE_HANDBELL = registerBlock(ColourfulClocksBlocks.REDSTONE_HANDBELL,
            new HandbellBlock(HandbellTypes.REDSTONE, ColourfulClocksBlocks.getHandbellProperties(Blocks.REDSTONE_BLOCK)));
    public static final Supplier<Block> EMERALD_HANDBELL = registerBlock(ColourfulClocksBlocks.EMERALD_HANDBELL,
            new HandbellBlock(HandbellTypes.EMERALD, ColourfulClocksBlocks.getHandbellProperties(Blocks.EMERALD_BLOCK)));

    public static final HashMap<HandbellTypes, Supplier<Block>> HANDBELL_VARIANTS = new HashMap<>();
    static {
        HANDBELL_VARIANTS.put(HandbellTypes.IRON, IRON_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.COPPER, COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.EXPOSED_COPPER, EXPOSED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WEATHERED_COPPER, WEATHERED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.OXIDIZED_COPPER, OXIDIZED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WAXED_COPPER, WAXED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WAXED_EXPOSED_COPPER, WAXED_EXPOSED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WAXED_WEATHERED_COPPER, WAXED_WEATHERED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.WAXED_OXIDIZED_COPPER, WAXED_OXIDIZED_COPPER_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.GOLD, GOLD_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.DIAMOND, DIAMOND_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.NETHERITE, NETHERITE_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.QUARTZ, QUARTZ_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.AMETHYST, AMETHYST_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.LAPIS_LAZULI, LAPIS_LAZULI_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.REDSTONE, REDSTONE_HANDBELL);
        HANDBELL_VARIANTS.put(HandbellTypes.EMERALD, EMERALD_HANDBELL);
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerBaseVariantsAll(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                            new BornholmBaseBlockImpl(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
        }
        return hashMap;
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerMiddleVariantsAll(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    new BornholmMiddleBlockImpl(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
        }
        return hashMap;
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerTopVariantsAll(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    new BornholmTopBlockImpl(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
        }
        return hashMap;
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerMantelClockVariants(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    new MantelClockBlock(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
        }
        return hashMap;
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerTallMantelClockVariants(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    new TallMantelClockBlock(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes).noOcclusion()));
            hashMap.put(clockTypes, block);
        }
        return hashMap;
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerWallClockVariants(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    new WallClockBlock(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
        }
        return hashMap;
    }

    private static HashMap<ClockTypes, Supplier<Block>> registerAlarmClockVariants(ResourceLocation baseLocation) {
        HashMap<ClockTypes, Supplier<Block>> hashMap = new HashMap<>();
        for (ClockTypes clockTypes : ClockTypes.values()) {
            Supplier<Block> block = registerBlock(baseLocation.withSuffix(clockTypes.getSerializedName()),
                    new AlarmClockBlock(clockTypes, ColourfulClocksBlocks.getProperties(clockTypes)));
            hashMap.put(clockTypes, block);
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
