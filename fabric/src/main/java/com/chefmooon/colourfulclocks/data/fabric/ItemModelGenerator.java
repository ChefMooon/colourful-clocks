package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTemplates;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTextureSlots;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class ItemModelGenerator {
    private static ItemModelGenerators GENERATOR;
    public static void generateItemModels(ItemModelGenerators itemModelGenerators) {
        GENERATOR = itemModelGenerators;

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

        generateBornholmMiddleItems();
        generateBornholmTopItems();
        generateMantelClockItems();
        generateTallMantelClockItems();
    }

    private static void generateBornholmMiddleItems() {
        for (ClockTypes clockTypes : ClockTypes.values()) {
            ResourceLocation itemLocation = ModelLocationUtils.getModelLocation(ColourfulClocksItemsImpl.BORNHOLM_MIDDLE_VARIANTS.get(clockTypes).get());
            for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
                if (bornholmDoorTypes == BornholmDoorTypes.BASE) continue;
                ResourceLocation doorLocation = bornholmDoorTypes == BornholmDoorTypes.GLASS ?
                        bornholmDoorTypes.getBornholmDoorTexture() :
                        TextUtil.res(bornholmDoorTypes.getBornholmDoorTexture().getPath() + "_transparent");
                TextureMapping variantMapping = TextureMapping.singleSlot(TextureSlot.SIDE, ModelLocationUtils.getModelLocation(clockTypes.getBlock()))
                        .put(TextureSlot.INSIDE, BlockModelGenerator.getStrippedBlockModelLocation(clockTypes))
                        .put(ColourfulClocksTextureSlots.DOOR, doorLocation);

                ColourfulClocksTemplates.BORNHOLM_MIDDLE_ITEM.create(itemLocation.withSuffix(bornholmDoorTypes.getSerializedName()), variantMapping, GENERATOR.output);
            }
            TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, ModelLocationUtils.getModelLocation(clockTypes.getBlock()))
                    .put(TextureSlot.INSIDE, ModelLocationUtils.getModelLocation(clockTypes.getStrippedBlock()))
                    .put(ColourfulClocksTextureSlots.DOOR, ModelLocationUtils.getModelLocation(clockTypes.getBlock()));

            ColourfulClocksTemplates.BORNHOLM_MIDDLE_ITEM.create(itemLocation, mapping, GENERATOR.output, ItemModelGenerator::generateBornholmMiddleItemJson);
        }
    }

    private static JsonObject generateBornholmMiddleItemJson(ResourceLocation modelLocation, Map<TextureSlot, ResourceLocation> modelGetter) {
        JsonObject jsonObject = ColourfulClocksTemplates.BORNHOLM_MIDDLE_ITEM.createBaseTemplate(modelLocation, modelGetter);
        JsonArray jsonArray = new JsonArray();

        for (BornholmDoorTypes doorType: BornholmDoorTypes.values()) {
            if (doorType == BornholmDoorTypes.BASE) continue;
            JsonObject modelObject = new JsonObject();
            JsonObject predicateObject = new JsonObject();
            predicateObject.addProperty(TextUtil.res("glass").toString(), doorType.getId() / 100.0F);
            modelObject.add("predicate", predicateObject);
            modelObject.addProperty("model", modelLocation.withSuffix("_" + doorType.getName()).toString());
            jsonArray.add(modelObject);
        }

        jsonObject.add("overrides", jsonArray);
        return jsonObject;
    }

    private static void generateBornholmTopItems() {
        for (ClockTypes clockTypes : ClockTypes.values()) {
            ResourceLocation itemLocation = ModelLocationUtils.getModelLocation(ColourfulClocksItemsImpl.BORNHOLM_TOP_VARIANTS.get(clockTypes).get());
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                ResourceLocation glassLocation = bornholmTopGlassTypes == BornholmTopGlassTypes.GLASS ?
                        bornholmTopGlassTypes.getBornholmGlassTexture() :
                        TextUtil.res(bornholmTopGlassTypes.getBornholmGlassTexture().getPath() + "_transparent");
                TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, BlockModelGenerator.getBlockModelLocation(clockTypes))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/quartz_bornholm_clockface"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, glassLocation)
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));

                ColourfulClocksTemplates.BORNHOLM_TOP_ITEM.create(itemLocation.withSuffix(bornholmTopGlassTypes.getSerializedName()), mapping, GENERATOR.output);
            }
            TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, BlockModelGenerator.getBlockModelLocation(clockTypes))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/quartz_bornholm_clockface"))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, TextUtil.res("block/glass_bornholm_dial"))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));
            ColourfulClocksTemplates.BORNHOLM_TOP_ITEM.create(itemLocation, mapping, GENERATOR.output, ItemModelGenerator::generateBornholmTopItemJson);
        }
    }

    private static JsonObject generateBornholmTopItemJson(ResourceLocation modelLocation, Map<TextureSlot, ResourceLocation> modelGetter) {
        JsonObject jsonObject = ColourfulClocksTemplates.BORNHOLM_TOP_ITEM.createBaseTemplate(modelLocation, modelGetter);
        JsonArray jsonArray = new JsonArray();

        for (BornholmTopGlassTypes glassType : BornholmTopGlassTypes.values()) {
            if (glassType == BornholmTopGlassTypes.GLASS) continue;
            JsonObject modelObject = new JsonObject();
            JsonObject predicateObject = new JsonObject();
            predicateObject.addProperty(TextUtil.res("glass").toString(), glassType.getId() / 100.0F);
            modelObject.add("predicate", predicateObject);
            modelObject.addProperty("model", modelLocation.withSuffix("_" + glassType.getName()).toString());
            jsonArray.add(modelObject);
        }

        jsonObject.add("overrides", jsonArray);
        return jsonObject;
    }

    private static void generateMantelClockItems() {
        for (ClockTypes clockTypes : ClockTypes.values()) {
            ResourceLocation itemLocation = ModelLocationUtils.getModelLocation(ColourfulClocksItemsImpl.MANTEL_CLOCK_VARIANTS.get(clockTypes).get());
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                ResourceLocation glassLocation = bornholmTopGlassTypes == BornholmTopGlassTypes.GLASS ?
                        TextUtil.res("block/" + bornholmTopGlassTypes.getName() + "_dial_small") :
                        TextUtil.res("block/" + bornholmTopGlassTypes.getName() + "_dial_small_transparent");

                TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, BlockModelGenerator.getBlockModelLocation(clockTypes))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/small_quartz_clockface"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, glassLocation)
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));

                ColourfulClocksTemplates.MANTEL_CLOCK_ITEM.create(itemLocation.withSuffix(bornholmTopGlassTypes.getSerializedName()), mapping, GENERATOR.output);
            }
            TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, BlockModelGenerator.getBlockModelLocation(clockTypes))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/small_quartz_clockface"))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, TextUtil.res("block/" + BornholmTopGlassTypes.GLASS.getName() + "_dial_small"))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));
            ColourfulClocksTemplates.MANTEL_CLOCK_ITEM.create(itemLocation, mapping, GENERATOR.output, ItemModelGenerator::generateMantelClockItemJson);
        }
    }

    private static JsonObject generateMantelClockItemJson(ResourceLocation modelLocation, Map<TextureSlot, ResourceLocation> modelGetter) {
        JsonObject jsonObject = ColourfulClocksTemplates.MANTEL_CLOCK_ITEM.createBaseTemplate(modelLocation, modelGetter);
        JsonArray jsonArray = new JsonArray();

        for (BornholmTopGlassTypes glassType : BornholmTopGlassTypes.values()) {
            if (glassType == BornholmTopGlassTypes.GLASS) continue;
            JsonObject modelObject = new JsonObject();
            JsonObject predicateObject = new JsonObject();
            predicateObject.addProperty(TextUtil.res("glass").toString(), glassType.getId() / 100.0F);
            modelObject.add("predicate", predicateObject);
            modelObject.addProperty("model", modelLocation.withSuffix("_" + glassType.getName()).toString());
            jsonArray.add(modelObject);
        }

        jsonObject.add("overrides", jsonArray);
        return jsonObject;
    }

    private static void generateTallMantelClockItems() {
        for (ClockTypes clockTypes : ClockTypes.values()) {
            ResourceLocation itemLocation = ModelLocationUtils.getModelLocation(ColourfulClocksItemsImpl.TALL_MANTEL_CLOCK_VARIANTS.get(clockTypes).get());
            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
                ResourceLocation glassLocation = bornholmTopGlassTypes == BornholmTopGlassTypes.GLASS ?
                        TextUtil.res("block/" + bornholmTopGlassTypes.getName() + "_dial_small") :
                        TextUtil.res("block/" + bornholmTopGlassTypes.getName() + "_dial_small_transparent");

                TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, BlockModelGenerator.getBlockModelLocation(clockTypes))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/small_quartz_clockface"))
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, glassLocation)
                        .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));

                ColourfulClocksTemplates.TALL_MANTEL_CLOCK_ITEM.create(itemLocation.withSuffix(bornholmTopGlassTypes.getSerializedName()), mapping, GENERATOR.output);
            }
            TextureMapping mapping = TextureMapping.singleSlot(TextureSlot.SIDE, BlockModelGenerator.getBlockModelLocation(clockTypes))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL, TextUtil.res("block/small_quartz_clockface"))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, TextUtil.res("block/" + BornholmTopGlassTypes.GLASS.getName() + "_dial_small"))
                    .put(ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS, ModelLocationUtils.getModelLocation(Blocks.COAL_BLOCK));
            ColourfulClocksTemplates.TALL_MANTEL_CLOCK_ITEM.create(itemLocation, mapping, GENERATOR.output, ItemModelGenerator::generateTallMantelClockItemJson);
        }
    }

    private static JsonObject generateTallMantelClockItemJson(ResourceLocation modelLocation, Map<TextureSlot, ResourceLocation> modelGetter) {
        JsonObject jsonObject = ColourfulClocksTemplates.TALL_MANTEL_CLOCK_ITEM.createBaseTemplate(modelLocation, modelGetter);
        JsonArray jsonArray = new JsonArray();

        for (BornholmTopGlassTypes glassType : BornholmTopGlassTypes.values()) {
            if (glassType == BornholmTopGlassTypes.GLASS) continue;
            JsonObject modelObject = new JsonObject();
            JsonObject predicateObject = new JsonObject();
            predicateObject.addProperty(TextUtil.res("glass").toString(), glassType.getId() / 100.0F);
            modelObject.add("predicate", predicateObject);
            modelObject.addProperty("model", modelLocation.withSuffix("_" + glassType.getName()).toString());
            jsonArray.add(modelObject);
        }

        jsonObject.add("overrides", jsonArray);
        return jsonObject;
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
        ColourfulClocksTemplates.POCKET_WATCH_MINUTE_HAND.create((ModelLocationUtils.getModelLocation(item, "_minute_hand")),
                TextureMapping.singleSlot(TextureSlot.ALL, ModelLocationUtils.getModelLocation(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_HOUR_HAND.create((ModelLocationUtils.getModelLocation(item, "_hour_hand")),
                TextureMapping.singleSlot(TextureSlot.ALL, ModelLocationUtils.getModelLocation(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_MINUTE_HAND_SMALL.create((ModelLocationUtils.getModelLocation(item, "_minute_hand_small")),
                TextureMapping.singleSlot(TextureSlot.ALL, ModelLocationUtils.getModelLocation(item)), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_HOUR_HAND_SMALL.create((ModelLocationUtils.getModelLocation(item, "_hour_hand_small")),
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
        ColourfulClocksTemplates.POCKET_WATCH_MINUTE_HAND.create((ModelLocationUtils.getModelLocation(item, "_minute_hand")),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_HOUR_HAND.create((ModelLocationUtils.getModelLocation(item, "_hour_hand")),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_MINUTE_HAND_SMALL.create((ModelLocationUtils.getModelLocation(item, "_minute_hand_small")),
                TextureMapping.singleSlot(TextureSlot.ALL, TextUtil.res(ModelLocationUtils.getModelLocation(item).getPath().replace("waxed_", ""))), itemModelGenerators.output);
        ColourfulClocksTemplates.POCKET_WATCH_HOUR_HAND_SMALL.create((ModelLocationUtils.getModelLocation(item, "_hour_hand_small")),
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
}
