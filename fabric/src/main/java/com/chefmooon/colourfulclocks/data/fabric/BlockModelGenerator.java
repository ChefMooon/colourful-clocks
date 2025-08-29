package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.block.*;
import com.chefmooon.colourfulclocks.common.block.properties.WallClockPartProperty;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTemplates;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTextureSlots;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.core.Direction;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class BlockModelGenerator {
    private static BlockModelGenerators GENERATOR;
    public static void generateBlockModels(BlockModelGenerators blockModelGenerators) {
        GENERATOR = blockModelGenerators;

        registerBornholm(blockModelGenerators);
        registerMantelClockBlockAll(blockModelGenerators);
        registerTallMantelClockBlockAll(blockModelGenerators);
        registerWallClockBlockAll(blockModelGenerators);
        registerAlarmClockBlockAll(blockModelGenerators);

        generateBornholmTopDialGlass();
        generateBornholmDoorTypes();
        generateSmallGlassDial();
    }

    private static void generateBornholmTopDialGlass() {
        for (BornholmTopGlassTypes type : BornholmTopGlassTypes.values()) {
            ColourfulClocksTemplates.BORNHOLM_TOP_GLASS.create(type.getBornholmGlassTexture(),
                    TextureMapping.singleSlot(TextureSlot.ALL, type.getBornholmGlassTexture()), GENERATOR.modelOutput);
        }
    }

    private static void generateBornholmDoorTypes() {
        for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
            if (bornholmDoorTypes == BornholmDoorTypes.BASE) continue; // Skip the base type as it is handled separately
            ColourfulClocksTemplates.BORNHOLM_DOOR.create(bornholmDoorTypes.getBornholmDoorTexture(),
                    TextureMapping.singleSlot(TextureSlot.ALL, bornholmDoorTypes.getBornholmDoorTexture()), GENERATOR.modelOutput);
        }
    }

    private static void generateSmallGlassDial() {
        for (BornholmTopGlassTypes type : BornholmTopGlassTypes.values()) {
            ColourfulClocksTemplates.GLASS_DIAL_SMALL.create(TextUtil.res("block/" + type.getName() + "_dial_small"),
                    TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res("block/" + type.getName() + "_dial_small")), GENERATOR.modelOutput);
        }
    }

    private static void registerBornholm(BlockModelGenerators blockModelGenerators) {
        registerBornholmBaseBlockAll(blockModelGenerators);
        registerBornholmMiddleBlockAll(blockModelGenerators);
        registerBornholmTopBlockAll(blockModelGenerators);
    }

    private static void registerBornholmBaseBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.forEach(((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());
            ResourceLocation woodTypeLocation = getBlockModelLocation(entry);

            ResourceLocation BORNHOLM_BASE = ColourfulClocksTemplates.BORNHOLM_BASE.create(blockLocation,
                    TextureMapping.layer0(woodTypeLocation), blockModelGenerators.modelOutput
            );

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                            Variant.variant().with(VariantProperties.MODEL, BORNHOLM_BASE))
                    .with(PropertyDispatch.property(BlockStateProperties.FACING)
                            .select(Direction.DOWN, Variant.variant())
                            .select(Direction.UP, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R180))
                            .select(Direction.NORTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R270))
                            .select(Direction.SOUTH, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
                            .select(Direction.WEST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
                            .select(Direction.EAST, Variant.variant().with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)))
            );
        }));
    }

    private static void registerBornholmMiddleBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.forEach(((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());
            ResourceLocation woodTypeLocation = getBlockModelLocation(entry);
            ResourceLocation strippedBlockLocation = getStrippedBlockModelLocation(entry);

            HashMap<BornholmDoorTypes, Map.Entry<ResourceLocation, ResourceLocation>> MODELS = new HashMap<>();
            for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
                if (bornholmDoorTypes != BornholmDoorTypes.BASE) {
                    TextureMapping variantMapping = TextureMapping.singleSlot(TextureSlot.SIDE, woodTypeLocation)
                            .put(TextureSlot.INSIDE, strippedBlockLocation)
                            .put(ColourfulClocksTextureSlots.DOOR, bornholmDoorTypes.getBornholmDoorTexture());

                    ResourceLocation BORNHOLM_MIDDLE_VARIANT = ColourfulClocksTemplates.BORNHOLM_MIDDLE.create(
                            blockLocation.withSuffix(bornholmDoorTypes.getSerializedName()), variantMapping, blockModelGenerators.modelOutput
                    );
                    ResourceLocation BORNHOLM_MIDDLE_VARIANT_BASE = ColourfulClocksTemplates.BORNHOLM_MIDDLE_OPEN.create(
                            blockLocation.withSuffix(bornholmDoorTypes.getSerializedName() + "_open"), variantMapping, blockModelGenerators.modelOutput
                    );
                    MODELS.put(bornholmDoorTypes, new AbstractMap.SimpleEntry<>(BORNHOLM_MIDDLE_VARIANT, BORNHOLM_MIDDLE_VARIANT_BASE));
                }
            }

//            TextureMapping variantBaseMapping = TextureMapping.singleSlot(TextureSlot.SIDE, woodTypeLocation)
//                    .put(TextureSlot.INSIDE, strippedBlockLocation)
//                    .put(ColourfulClocksTextureSlots.DOOR, woodTypeLocation);
//
//            ResourceLocation BORNHOLM_MIDDLE_VARIANT_BASE = ColourfulClocksTemplates.BORNHOLM_MIDDLE.create(
//                    blockLocation, variantBaseMapping, blockModelGenerators.modelOutput
//            );
//            ResourceLocation BORNHOLM_MIDDLE_VARIANT_BASE_OPEN = ColourfulClocksTemplates.BORNHOLM_MIDDLE_OPEN.create(
//                    blockLocation.withSuffix( "_open"), variantBaseMapping, blockModelGenerators.modelOutput
//            );
            TextureMapping variantBaseMapping = TextureMapping.singleSlot(TextureSlot.SIDE, woodTypeLocation)
                    .put(TextureSlot.INSIDE, strippedBlockLocation);

            ResourceLocation BORNHOLM_MIDDLE_VARIANT_BASE = ColourfulClocksTemplates.BORNHOLM_MIDDLE_BASE.create(
                    blockLocation, variantBaseMapping, blockModelGenerators.modelOutput
            );
            ResourceLocation BORNHOLM_MIDDLE_VARIANT_BASE_OPEN = ColourfulClocksTemplates.BORNHOLM_MIDDLE_BASE_OPEN.create(
                    blockLocation.withSuffix( "_open"), variantBaseMapping, blockModelGenerators.modelOutput
            );

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                            Variant.variant().with(VariantProperties.MODEL, BORNHOLM_MIDDLE_VARIANT_BASE))
                    .with(BlockModelGenerators.createHorizontalFacingDispatch())
                    .with(PropertyDispatch.properties(BornholmMiddleBlock.DOOR_TYPE, BornholmMiddleBlock.OPEN)
                            .select(BornholmDoorTypes.BASE, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, BORNHOLM_MIDDLE_VARIANT_BASE))
                            .select(BornholmDoorTypes.BASE, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, BORNHOLM_MIDDLE_VARIANT_BASE_OPEN))

                            .select(BornholmDoorTypes.GLASS, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS).getKey()))
                            .select(BornholmDoorTypes.GLASS, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS).getValue()))

                            .select(BornholmDoorTypes.GLASS_WHITE, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_WHITE).getKey()))
                            .select(BornholmDoorTypes.GLASS_WHITE, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_WHITE).getValue()))

                            .select(BornholmDoorTypes.GLASS_ORANGE, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_ORANGE).getKey()))
                            .select(BornholmDoorTypes.GLASS_ORANGE, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_ORANGE).getValue()))

                            .select(BornholmDoorTypes.GLASS_MAGENTA, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_MAGENTA).getKey()))
                            .select(BornholmDoorTypes.GLASS_MAGENTA, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_MAGENTA).getValue()))

                            .select(BornholmDoorTypes.GLASS_LIGHT_BLUE, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_LIGHT_BLUE).getKey()))
                            .select(BornholmDoorTypes.GLASS_LIGHT_BLUE, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_LIGHT_BLUE).getValue()))

                            .select(BornholmDoorTypes.GLASS_YELLOW, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_YELLOW).getKey()))
                            .select(BornholmDoorTypes.GLASS_YELLOW, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_YELLOW).getValue()))

                            .select(BornholmDoorTypes.GLASS_LIME, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_LIME).getKey()))
                            .select(BornholmDoorTypes.GLASS_LIME, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_LIME).getValue()))

                            .select(BornholmDoorTypes.GLASS_PINK, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_PINK).getKey()))
                            .select(BornholmDoorTypes.GLASS_PINK, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_PINK).getValue()))

                            .select(BornholmDoorTypes.GLASS_GRAY, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_GRAY).getKey()))
                            .select(BornholmDoorTypes.GLASS_GRAY, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_GRAY).getValue()))

                            .select(BornholmDoorTypes.GLASS_LIGHT_GRAY, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_LIGHT_GRAY).getKey()))
                            .select(BornholmDoorTypes.GLASS_LIGHT_GRAY, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_LIGHT_GRAY).getValue()))

                            .select(BornholmDoorTypes.GLASS_CYAN, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_CYAN).getKey()))
                            .select(BornholmDoorTypes.GLASS_CYAN, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_CYAN).getValue()))

                            .select(BornholmDoorTypes.GLASS_PURPLE, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_PURPLE).getKey()))
                            .select(BornholmDoorTypes.GLASS_PURPLE, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_PURPLE).getValue()))

                            .select(BornholmDoorTypes.GLASS_BLUE, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_BLUE).getKey()))
                            .select(BornholmDoorTypes.GLASS_BLUE, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_BLUE).getValue()))

                            .select(BornholmDoorTypes.GLASS_BROWN, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_BROWN).getKey()))
                            .select(BornholmDoorTypes.GLASS_BROWN, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_BROWN).getValue()))

                            .select(BornholmDoorTypes.GLASS_GREEN, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_GREEN).getKey()))
                            .select(BornholmDoorTypes.GLASS_GREEN, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_GREEN).getValue()))

                            .select(BornholmDoorTypes.GLASS_RED, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_RED).getKey()))
                            .select(BornholmDoorTypes.GLASS_RED, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_RED).getValue()))

                            .select(BornholmDoorTypes.GLASS_BLACK, Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_BLACK).getKey()))
                            .select(BornholmDoorTypes.GLASS_BLACK, Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmDoorTypes.GLASS_BLACK).getValue()))
                    )
            );

            blockModelGenerators.skipAutoItemBlock(blockSupplier.get());
        }));
    }

    private static void registerBornholmTopBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.forEach(((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());

            HashMap<BornholmTopGlassTypes, ResourceLocation> MODELS = new HashMap<>();
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, getBlockModelLocation(entry))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/medium_quartz_clock_face"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, bornholmTopGlassTypes.getBornholmGlassTexture())
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));

                ResourceLocation BORNHOLM_TOP = ColourfulClocksTemplates.BORNHOLM_TOP.create(
                        blockLocation.withSuffix(bornholmTopGlassTypes.getSerializedName()), mapping, blockModelGenerators.modelOutput);
                MODELS.put(bornholmTopGlassTypes, BORNHOLM_TOP);
            }

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                            Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS)))
                    .with(BlockModelGenerators.createHorizontalFacingDispatch())
                    .with(PropertyDispatch.property(BornholmTopBlock.GLASS_TYPE)
                            .select(BornholmTopGlassTypes.GLASS, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS)))
                            .select(BornholmTopGlassTypes.GLASS_WHITE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_WHITE)))
                            .select(BornholmTopGlassTypes.GLASS_ORANGE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_ORANGE)))
                            .select(BornholmTopGlassTypes.GLASS_MAGENTA, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_MAGENTA)))
                            .select(BornholmTopGlassTypes.GLASS_LIGHT_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_BLUE)))
                            .select(BornholmTopGlassTypes.GLASS_YELLOW, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_YELLOW)))
                            .select(BornholmTopGlassTypes.GLASS_LIME, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIME)))
                            .select(BornholmTopGlassTypes.GLASS_PINK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PINK)))
                            .select(BornholmTopGlassTypes.GLASS_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GRAY)))
                            .select(BornholmTopGlassTypes.GLASS_LIGHT_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_GRAY)))
                            .select(BornholmTopGlassTypes.GLASS_CYAN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_CYAN)))
                            .select(BornholmTopGlassTypes.GLASS_PURPLE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PURPLE)))
                            .select(BornholmTopGlassTypes.GLASS_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLUE)))
                            .select(BornholmTopGlassTypes.GLASS_BROWN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BROWN)))
                            .select(BornholmTopGlassTypes.GLASS_GREEN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GREEN)))
                            .select(BornholmTopGlassTypes.GLASS_RED, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_RED)))
                            .select(BornholmTopGlassTypes.GLASS_BLACK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLACK)))
                    )
            );

            blockModelGenerators.skipAutoItemBlock(blockSupplier.get());
        }));
    }

    private static void registerMantelClockBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.forEach((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());

            HashMap<BornholmTopGlassTypes, ResourceLocation> MODELS = new HashMap<>();
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, getBlockModelLocation(entry))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/small_quartz_clock_face"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, TextUtil.res("block/" + bornholmTopGlassTypes.getName() + "_dial_small"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));

                ResourceLocation MANTEL_CLOCK = ColourfulClocksTemplates.MANTEL_CLOCK.create(
                        blockLocation.withSuffix(bornholmTopGlassTypes.getSerializedName()), mapping, blockModelGenerators.modelOutput);
                MODELS.put(bornholmTopGlassTypes, MANTEL_CLOCK);
            }

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                            Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS)))
                    .with(BlockModelGenerators.createHorizontalFacingDispatch())
                    .with(PropertyDispatch.property(MantelClockBlock.GLASS_TYPE)
                            .select(BornholmTopGlassTypes.GLASS, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS)))
                            .select(BornholmTopGlassTypes.GLASS_WHITE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_WHITE)))
                            .select(BornholmTopGlassTypes.GLASS_ORANGE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_ORANGE)))
                            .select(BornholmTopGlassTypes.GLASS_MAGENTA, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_MAGENTA)))
                            .select(BornholmTopGlassTypes.GLASS_LIGHT_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_BLUE)))
                            .select(BornholmTopGlassTypes.GLASS_YELLOW, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_YELLOW)))
                            .select(BornholmTopGlassTypes.GLASS_LIME, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIME)))
                            .select(BornholmTopGlassTypes.GLASS_PINK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PINK)))
                            .select(BornholmTopGlassTypes.GLASS_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GRAY)))
                            .select(BornholmTopGlassTypes.GLASS_LIGHT_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_GRAY)))
                            .select(BornholmTopGlassTypes.GLASS_CYAN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_CYAN)))
                            .select(BornholmTopGlassTypes.GLASS_PURPLE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PURPLE)))
                            .select(BornholmTopGlassTypes.GLASS_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLUE)))
                            .select(BornholmTopGlassTypes.GLASS_BROWN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BROWN)))
                            .select(BornholmTopGlassTypes.GLASS_GREEN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GREEN)))
                            .select(BornholmTopGlassTypes.GLASS_RED, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_RED)))
                            .select(BornholmTopGlassTypes.GLASS_BLACK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLACK)))
                    )
            );
            blockModelGenerators.skipAutoItemBlock(blockSupplier.get());
        });
    }

    private static void registerTallMantelClockBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.TALL_MANTEL_CLOCK_VARIANTS.forEach((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());

            HashMap<BornholmTopGlassTypes, Map.Entry<ResourceLocation, ResourceLocation>> MODELS = new HashMap<>();
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, getBlockModelLocation(entry))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/small_quartz_clock_face"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, TextUtil.res("block/" + bornholmTopGlassTypes.getName() + "_dial_small"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));

                ResourceLocation TALL_MANTEL_CLOCK = ColourfulClocksTemplates.TALL_MANTEL_CLOCK.create(
                        blockLocation.withSuffix(bornholmTopGlassTypes.getSerializedName()), mapping, blockModelGenerators.modelOutput);
                ResourceLocation TALL_MANTEL_CLOCK_WALL = ColourfulClocksTemplates.TALL_MANTEL_CLOCK_WALL.create(
                        blockLocation.withSuffix(bornholmTopGlassTypes.getSerializedName() + "_wall"), mapping, blockModelGenerators.modelOutput);
                MODELS.put(bornholmTopGlassTypes, new AbstractMap.SimpleEntry<>(TALL_MANTEL_CLOCK, TALL_MANTEL_CLOCK_WALL));
            }

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                            Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS).getKey()))
                    .with(BlockModelGenerators.createHorizontalFacingDispatch())
                    .with(PropertyDispatch.properties(TallMantelClockBlock.WALL, TallMantelClockBlock.GLASS_TYPE)
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_WHITE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_WHITE).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_ORANGE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_ORANGE).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_MAGENTA, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_MAGENTA).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_LIGHT_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_BLUE).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_YELLOW, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_YELLOW).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_LIME, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIME).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_PINK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PINK).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GRAY).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_LIGHT_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_GRAY).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_CYAN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_CYAN).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_PURPLE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PURPLE).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLUE).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_BROWN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BROWN).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_GREEN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GREEN).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_RED, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_RED).getKey()))
                            .select(Boolean.FALSE, BornholmTopGlassTypes.GLASS_BLACK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLACK).getKey()))

                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_WHITE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_WHITE).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_ORANGE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_ORANGE).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_MAGENTA, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_MAGENTA).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_LIGHT_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_BLUE).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_YELLOW, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_YELLOW).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_LIME, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIME).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_PINK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PINK).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GRAY).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_LIGHT_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_GRAY).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_CYAN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_CYAN).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_PURPLE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PURPLE).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLUE).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_BROWN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BROWN).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_GREEN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GREEN).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_RED, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_RED).getValue()))
                            .select(Boolean.TRUE, BornholmTopGlassTypes.GLASS_BLACK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLACK).getValue()))
                    )
            );
            blockModelGenerators.skipAutoItemBlock(blockSupplier.get());
        });
    }

    private static void registerWallClockBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.WALL_CLOCK_VARIANTS.forEach((entry, blockSupplier) -> {
            TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, getBlockModelLocation(entry))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/large_quartz_clock_face"))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));
            ResourceLocation WALL_CLOCK_BASE = ColourfulClocksTemplates.WALL_CLOCK_BASE.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()), mapping, blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_BOTTOM_LEFT = ColourfulClocksTemplates.WALL_CLOCK_BOTTOM_LEFT.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_bottom_left"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/large_bottom_left_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_BOTTOM_RIGHT = ColourfulClocksTemplates.WALL_CLOCK_BOTTOM_RIGHT.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_bottom_right"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/large_bottom_right_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_TOP_LEFT = ColourfulClocksTemplates.WALL_CLOCK_TOP_LEFT.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_top_left"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/large_top_left_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_TOP_RIGHT = ColourfulClocksTemplates.WALL_CLOCK_TOP_RIGHT.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_top_right"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/large_top_right_quartz_clock_face")), blockModelGenerators.modelOutput);

            ResourceLocation WALL_CLOCK_BOTTOM_LEFT_XL = ColourfulClocksTemplates.WALL_CLOCK_BOTTOM_LEFT_XL.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_bottom_left_xl"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/xl_bottom_left_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_BOTTOM_RIGHT_XL = ColourfulClocksTemplates.WALL_CLOCK_BOTTOM_RIGHT.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_bottom_right_xl"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/xl_bottom_right_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_TOP_LEFT_XL = ColourfulClocksTemplates.WALL_CLOCK_TOP_LEFT.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_top_left_xl"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/xl_top_left_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_TOP_RIGHT_XL = ColourfulClocksTemplates.WALL_CLOCK_TOP_RIGHT_XL.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_top_right_xl"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/xl_top_right_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_BOTTOM_MIDDLE = ColourfulClocksTemplates.WALL_CLOCK_BOTTOM_MIDDLE.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_bottom_middle"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/xl_bottom_middle_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_LEFT_MIDDLE = ColourfulClocksTemplates.WALL_CLOCK_LEFT_MIDDLE.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_left_middle"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/xl_left_middle_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_TOP_MIDDLE = ColourfulClocksTemplates.WALL_CLOCK_TOP_MIDDLE.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_top_middle"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/xl_top_middle_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_RIGHT_MIDDLE = ColourfulClocksTemplates.WALL_CLOCK_RIGHT_MIDDLE.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_right_middle"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/xl_right_middle_quartz_clock_face")), blockModelGenerators.modelOutput);
            ResourceLocation WALL_CLOCK_CENTER = ColourfulClocksTemplates.WALL_CLOCK_CENTER.create(
                    ModelLocationUtils.getModelLocation(blockSupplier.get()).withSuffix("_center"),
                    mapping.put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/xl_center_quartz_clock_face")), blockModelGenerators.modelOutput);

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                    Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_BASE))
                            .with(BlockModelGenerators.createHorizontalFacingDispatch())
                            .with(PropertyDispatch.property(WallClockBlock.PART)
                                    .select(WallClockPartProperty.BASE, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_BASE))
                                    .select(WallClockPartProperty.BOTTOM_LEFT, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_BOTTOM_LEFT))
                                    .select(WallClockPartProperty.BOTTOM_RIGHT, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_BOTTOM_RIGHT))
                                    .select(WallClockPartProperty.TOP_LEFT, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_TOP_LEFT))
                                    .select(WallClockPartProperty.TOP_RIGHT, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_TOP_RIGHT))

                                    .select(WallClockPartProperty.BOTTOM_MIDDLE, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_BOTTOM_MIDDLE))
                                    .select(WallClockPartProperty.LEFT_MIDDLE, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_LEFT_MIDDLE))
                                    .select(WallClockPartProperty.TOP_MIDDLE, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_TOP_MIDDLE))
                                    .select(WallClockPartProperty.RIGHT_MIDDLE, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_RIGHT_MIDDLE))
                                    .select(WallClockPartProperty.CENTER, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_CENTER))

                                    .select(WallClockPartProperty.BOTTOM_LEFT_XL, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_BOTTOM_LEFT_XL))
                                    .select(WallClockPartProperty.BOTTOM_RIGHT_XL, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_BOTTOM_RIGHT_XL))
                                    .select(WallClockPartProperty.TOP_LEFT_XL, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_TOP_LEFT_XL))
                                    .select(WallClockPartProperty.TOP_RIGHT_XL, Variant.variant().with(VariantProperties.MODEL, WALL_CLOCK_TOP_RIGHT_XL))
                            )
            );
        });
    }

    private static void registerAlarmClockBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.ALARM_CLOCK_VARIANTS.forEach((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());

            HashMap<BornholmTopGlassTypes, ResourceLocation> MODELS = new HashMap<>();
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, getBlockModelLocation(entry))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/small_quartz_clock_face"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, TextUtil.res("block/" + bornholmTopGlassTypes.getName() + "_dial_small"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));

                ResourceLocation ALARM_CLOCK = ColourfulClocksTemplates.ALARM_CLOCK.create(
                        blockLocation.withSuffix(bornholmTopGlassTypes.getSerializedName()), mapping, blockModelGenerators.modelOutput);
                MODELS.put(bornholmTopGlassTypes, ALARM_CLOCK);
            }

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                            Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS)))
                    .with(BlockModelGenerators.createHorizontalFacingDispatch())
                    .with(PropertyDispatch.property(MantelClockBlock.GLASS_TYPE)
                            .select(BornholmTopGlassTypes.GLASS, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS)))
                            .select(BornholmTopGlassTypes.GLASS_WHITE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_WHITE)))
                            .select(BornholmTopGlassTypes.GLASS_ORANGE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_ORANGE)))
                            .select(BornholmTopGlassTypes.GLASS_MAGENTA, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_MAGENTA)))
                            .select(BornholmTopGlassTypes.GLASS_LIGHT_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_BLUE)))
                            .select(BornholmTopGlassTypes.GLASS_YELLOW, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_YELLOW)))
                            .select(BornholmTopGlassTypes.GLASS_LIME, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIME)))
                            .select(BornholmTopGlassTypes.GLASS_PINK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PINK)))
                            .select(BornholmTopGlassTypes.GLASS_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GRAY)))
                            .select(BornholmTopGlassTypes.GLASS_LIGHT_GRAY, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_LIGHT_GRAY)))
                            .select(BornholmTopGlassTypes.GLASS_CYAN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_CYAN)))
                            .select(BornholmTopGlassTypes.GLASS_PURPLE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_PURPLE)))
                            .select(BornholmTopGlassTypes.GLASS_BLUE, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLUE)))
                            .select(BornholmTopGlassTypes.GLASS_BROWN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BROWN)))
                            .select(BornholmTopGlassTypes.GLASS_GREEN, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_GREEN)))
                            .select(BornholmTopGlassTypes.GLASS_RED, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_RED)))
                            .select(BornholmTopGlassTypes.GLASS_BLACK, Variant.variant().with(VariantProperties.MODEL, MODELS.get(BornholmTopGlassTypes.GLASS_BLACK)))
                    )
            );
            blockModelGenerators.skipAutoItemBlock(blockSupplier.get());
        });
    }

    public static ResourceLocation getBlockModelLocation(ClockTypes clockTypes) {
        return ModelLocationUtils.getModelLocation(clockTypes.getBlock());
//        if (clockTypes == ClockTypes.SMOOTH_SANDSTONE) { // Can be removed when final stone types are chosen
//            return ResourceLocation.withDefaultNamespace("block/sandstone_top");
//        } else if (clockTypes == ClockTypes.SMOOTH_RED_SANDSTONE) {
//            return ResourceLocation.withDefaultNamespace("block/red_sandstone_top");
//        } else {
//            return ModelLocationUtils.getModelLocation(clockTypes.getBlock());
//        }
    }

    public static ResourceLocation getStrippedBlockModelLocation(ClockTypes clockTypes) {
        return ModelLocationUtils.getModelLocation(clockTypes.getStrippedBlock());
//        if (clockTypes == ClockTypes.SMOOTH_SANDSTONE) { // Can be removed when final stone types are chosen
//            return ResourceLocation.withDefaultNamespace("block/sandstone_top");
//        } else if (clockTypes == ClockTypes.SMOOTH_RED_SANDSTONE) {
//            return ResourceLocation.withDefaultNamespace("block/red_sandstone_top");
//        } else {
//            return ModelLocationUtils.getModelLocation(clockTypes.getStrippedBlock());
//        }
    }

    private static void registerBasicRotationBlockState(Block block, BlockModelGenerators blockModelGenerators) {
        ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation(block);
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, resourceLocation))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }
}
