package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTemplates;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTextureSlots;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        registerBornholm(blockModelGenerators);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        generatePocketWatchItem(ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.GOLD_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get(), itemModelGenerators);

        generatePocketWatchItem(ColourfulClocksItemsImpl.QUARTZ_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.AMETHYST_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.LAPIS_LAZULI_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.REDSTONE_POCKET_WATCH.get(), itemModelGenerators);
        generatePocketWatchItem(ColourfulClocksItemsImpl.EMERALD_POCKET_WATCH.get(), itemModelGenerators);

        generateWaxedPocketWatchItem(ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get(), itemModelGenerators);
        generateWaxedPocketWatchItem(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get(), itemModelGenerators);
        generateWaxedPocketWatchItem(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get(), itemModelGenerators);
        generateWaxedPocketWatchItem(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get(), itemModelGenerators);

        generatePendulumItem(ColourfulClocksItemsImpl.IRON_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.COPPER_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.GOLD_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get(), itemModelGenerators);

        generatePendulumItem(ColourfulClocksItemsImpl.QUARTZ_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.AMETHYST_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.LAPIS_LAZULI_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.REDSTONE_PENDULUM.get(), itemModelGenerators);
        generatePendulumItem(ColourfulClocksItemsImpl.EMERALD_PENDULUM.get(), itemModelGenerators);

        generateWaxedPendulumItem(ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get(), itemModelGenerators);
        generateWaxedPendulumItem(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get(), itemModelGenerators);
        generateWaxedPendulumItem(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get(), itemModelGenerators);
        generateWaxedPendulumItem(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get(), itemModelGenerators);
    }

    private static void generatePendulumItem(Item item, ItemModelGenerators itemModelGenerators) {
        ColourfulClocksTemplates.PENDULUM_BLOCK.create(ModelLocationUtils.getModelLocation(item),
                TextureMapping.singleSlot(TextureSlot.ALL, ModelLocationUtils.getModelLocation(item)), itemModelGenerators.output);

    }

    private static void generateWaxedPendulumItem(Item item, ItemModelGenerators itemModelGenerators) {
        ColourfulClocksTemplates.PENDULUM_BLOCK.create(ModelLocationUtils.getModelLocation(item),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
    }

    private static void generatePocketWatchItem(Item item, ItemModelGenerators itemModelGenerators) {
        ColourfulClocksTemplates.POCKET_WATCH_CLOCK.create((ModelLocationUtils.getModelLocation(item, "_in_clock")),
                TextureMapping.singleSlot(TextureSlot.ALL, ModelLocationUtils.getModelLocation(item)), itemModelGenerators.output);

        ColourfulClocksTemplates.POCKET_WATCH_CLOSED.create(ModelLocationUtils.getModelLocation(item, "_closed"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);

        ColourfulClocksTemplates.POCKET_WATCH_00.create(ModelLocationUtils.getModelLocation(item, "_00"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);

        ColourfulClocksTemplates.POCKET_WATCH_OPEN_00.create(ModelLocationUtils.getModelLocation(item, "_open_00"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_01.create(ModelLocationUtils.getModelLocation(item, "_open_01"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_02.create(ModelLocationUtils.getModelLocation(item, "_open_02"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_03.create(ModelLocationUtils.getModelLocation(item, "_open_03"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_04.create(ModelLocationUtils.getModelLocation(item, "_open_04"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_05.create(ModelLocationUtils.getModelLocation(item, "_open_05"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_06.create(ModelLocationUtils.getModelLocation(item, "_open_06"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_07.create(ModelLocationUtils.getModelLocation(item, "_open_07"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_08.create(ModelLocationUtils.getModelLocation(item, "_open_08"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_09.create(ModelLocationUtils.getModelLocation(item, "_open_09"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_10.create(ModelLocationUtils.getModelLocation(item, "_open_10"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_11.create(ModelLocationUtils.getModelLocation(item, "_open_11"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_12.create(ModelLocationUtils.getModelLocation(item, "_open_12"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_13.create(ModelLocationUtils.getModelLocation(item, "_open_13"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_14.create(ModelLocationUtils.getModelLocation(item, "_open_14"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_15.create(ModelLocationUtils.getModelLocation(item, "_open_15"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextureMapping.getItemTexture(item)), itemModelGenerators.output);
    }

    private static void generateWaxedPocketWatchItem(Item item, ItemModelGenerators itemModelGenerators) {
        ColourfulClocksTemplates.POCKET_WATCH_CLOCK.create((ModelLocationUtils.getModelLocation(item, "_in_clock")),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);

        ColourfulClocksTemplates.POCKET_WATCH_CLOSED.create(ModelLocationUtils.getModelLocation(item, "_closed"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);

        ColourfulClocksTemplates.POCKET_WATCH_00.create(ModelLocationUtils.getModelLocation(item, "_00"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);

        ColourfulClocksTemplates.POCKET_WATCH_OPEN_00.create(ModelLocationUtils.getModelLocation(item, "_open_00"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_01.create(ModelLocationUtils.getModelLocation(item, "_open_01"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_02.create(ModelLocationUtils.getModelLocation(item, "_open_02"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_03.create(ModelLocationUtils.getModelLocation(item, "_open_03"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_04.create(ModelLocationUtils.getModelLocation(item, "_open_04"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_05.create(ModelLocationUtils.getModelLocation(item, "_open_05"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_06.create(ModelLocationUtils.getModelLocation(item, "_open_06"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_07.create(ModelLocationUtils.getModelLocation(item, "_open_07"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_08.create(ModelLocationUtils.getModelLocation(item, "_open_08"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_09.create(ModelLocationUtils.getModelLocation(item, "_open_09"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_10.create(ModelLocationUtils.getModelLocation(item, "_open_10"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_11.create(ModelLocationUtils.getModelLocation(item, "_open_11"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_12.create(ModelLocationUtils.getModelLocation(item, "_open_12"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_13.create(ModelLocationUtils.getModelLocation(item, "_open_13"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_14.create(ModelLocationUtils.getModelLocation(item, "_open_14"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_OPEN_15.create(ModelLocationUtils.getModelLocation(item, "_open_15"),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
    }

    private static void registerBornholm(BlockModelGenerators blockModelGenerators) {
        registerBornholmBaseBlockAll(blockModelGenerators);
        registerBornholmMiddleBlockAll(blockModelGenerators);
        registerBornholmTopBlockAll(blockModelGenerators);
    }

    private static void registerBornholmBaseBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.forEach(((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());
            ResourceLocation woodTypeLocation = ModelLocationUtils.getModelLocation(entry.getBlock());

            ResourceLocation BORNHOLM_BASE = ColourfulClocksTemplates.BORNHOLM_BASE.create(blockLocation,
                    TextureMapping.layer0(woodTypeLocation), blockModelGenerators.modelOutput
            );

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                            Variant.variant().with(VariantProperties.MODEL, BORNHOLM_BASE))
                    .with(BlockModelGenerators.createHorizontalFacingDispatch()));
        }));
    }

    private static void registerBornholmMiddleBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.forEach(((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());
            ResourceLocation woodTypeLocation = ModelLocationUtils.getModelLocation(entry.getBlock());
            ResourceLocation strippedBlockLocation = ModelLocationUtils.getModelLocation(entry.getStrippedBlock());

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

            TextureMapping variantBaseMapping = TextureMapping.singleSlot(TextureSlot.SIDE, woodTypeLocation)
                    .put(TextureSlot.INSIDE, strippedBlockLocation)
                    .put(ColourfulClocksTextureSlots.DOOR, woodTypeLocation);

            ResourceLocation BORNHOLM_MIDDLE_VARIANT_BASE = ColourfulClocksTemplates.BORNHOLM_MIDDLE.create(
                    blockLocation, variantBaseMapping, blockModelGenerators.modelOutput
            );
            ResourceLocation BORNHOLM_MIDDLE_VARIANT_BASE_OPEN = ColourfulClocksTemplates.BORNHOLM_MIDDLE_OPEN.create(
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

            blockModelGenerators.delegateItemModel(blockSupplier.get(), blockLocation);
        }));
    }

    private static void registerBornholmTopBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.forEach(((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());


            HashMap<BornholmTopGlassTypes, ResourceLocation> MODELS = new HashMap<>();
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, ModelLocationUtils.getModelLocation(entry.getBlock()))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/quartz_bornholm_clockface"))
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

            blockModelGenerators.delegateItemModel(blockSupplier.get(), MODELS.get(BornholmTopGlassTypes.GLASS));
        }));
    }

    private static void registerBasicRotationBlockState(Block block, BlockModelGenerators blockModelGenerators) {
        ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation(block);
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, resourceLocation))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }
}
