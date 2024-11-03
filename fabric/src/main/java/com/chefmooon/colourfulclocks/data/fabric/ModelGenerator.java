package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
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
            ResourceLocation woodTypeLocation = ModelLocationUtils.getModelLocation(entry.getKey().getBlock());
            ResourceLocation strippedBlockLocation = ModelLocationUtils.getModelLocation(entry.getKey().getStrippedBlock());

            ResourceLocation doorTypeLocation;
            if (entry.getValue() == BornholmDoorTypes.BASE) {
                doorTypeLocation = woodTypeLocation;
            } else {
                doorTypeLocation = entry.getValue().getBornholmDoorTexture();
            }

            TextureMapping variantMapping = TextureMapping.singleSlot(TextureSlot.SIDE, woodTypeLocation)
                    .put(TextureSlot.INSIDE, strippedBlockLocation)
                    .put(ColourfulClocksTextureSlots.DOOR, doorTypeLocation);

            ResourceLocation BORNHOLM_MIDDLE_VARIANT = ColourfulClocksTemplates.BORNHOLM_MIDDLE.create(
                    blockLocation, variantMapping, blockModelGenerators.modelOutput
            );
            ResourceLocation BORNHOLM_MIDDLE_VARIANT_OPEN = ColourfulClocksTemplates.BORNHOLM_MIDDLE_OPEN.create(
                    blockLocation.withSuffix( "_open"), variantMapping, blockModelGenerators.modelOutput
            );

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                            Variant.variant().with(VariantProperties.MODEL, BORNHOLM_MIDDLE_VARIANT))
                    .with(BlockModelGenerators.createHorizontalFacingDispatch())
                    .with(PropertyDispatch.property(BornholmMiddleBlock.OPEN)
                            .select(Boolean.FALSE, Variant.variant().with(VariantProperties.MODEL, BORNHOLM_MIDDLE_VARIANT))
                            .select(Boolean.TRUE, Variant.variant().with(VariantProperties.MODEL, BORNHOLM_MIDDLE_VARIANT_OPEN))));

            blockModelGenerators.delegateItemModel(blockSupplier.get(), blockLocation);
        }));
    }

    private static void registerBornholmTopBlockAll(BlockModelGenerators blockModelGenerators) {
        ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.forEach(((entry, blockSupplier) -> {
            ResourceLocation blockLocation = ModelLocationUtils.getModelLocation(blockSupplier.get());

            TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, ModelLocationUtils.getModelLocation(entry.getKey().getBlock()))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/quartz_bornholm_clockface"))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, entry.getValue().getBornholmGlassTexture())
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));

            ResourceLocation BORNHOLM_TOP = ColourfulClocksTemplates.BORNHOLM_TOP.create(
                    blockLocation, mapping, blockModelGenerators.modelOutput
            );

            blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(blockSupplier.get(),
                            Variant.variant().with(VariantProperties.MODEL, BORNHOLM_TOP))
                    .with(BlockModelGenerators.createHorizontalFacingDispatch()));

            blockModelGenerators.delegateItemModel(blockSupplier.get(), blockLocation);
        }));
    }

    private static void registerBasicRotationBlockState(Block block, BlockModelGenerators blockModelGenerators) {
        ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation(block);
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block,
                        Variant.variant().with(VariantProperties.MODEL, resourceLocation))
                .with(BlockModelGenerators.createHorizontalFacingDispatch()));
    }
}
