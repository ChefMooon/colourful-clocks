package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class TranslationGenerator extends FabricLanguageProvider {
    public TranslationGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder translationBuilder) {

        String MOD_ID = ColourfulClocks.MOD_ID;
        String FORMATTED_MOD_ID = "Colourful Clocks";
        String SUBTITLE = MOD_ID + ".subtitles.";

        translationBuilder.add("itemGroup." + MOD_ID, FORMATTED_MOD_ID);

        translationBuilder.add(ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get(), "Iron Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get(), "Copper Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get(), "Exposed Copper Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get(), "Weathered Copper Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get(), "Oxidized Copper Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get(), "Waxed Copper Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get(), "Waxed Exposed Copper Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get(), "Waxed Weathered Copper Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get(), "Waxed Oxidized Copper Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.GOLD_POCKET_WATCH.get(), "Gold Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get(), "Diamond Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get(), "Netherite Pocket Watch");

        translationBuilder.add(ColourfulClocksItemsImpl.IRON_PENDULUM.get(), "Iron Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.COPPER_PENDULUM.get(), "Copper Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get(), "Exposed Copper Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get(), "Weathered Copper Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get(), "Oxidized Copper Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get(), "Waxed Copper Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get(), "Waxed Exposed Copper Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get(), "Waxed Weathered Copper Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get(), "Waxed Oxidized Copper Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.GOLD_PENDULUM.get(), "Gold Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get(), "Diamond Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get(), "Netherite Pendulum");

        generateBornholmBaseTranslations(translationBuilder);
        generateBornholmMiddleTranslations(translationBuilder);
        generateBonrholmTopTranslations(translationBuilder);

        translationBuilder.add(ColourfulClocksTags.CLOCK_PENDULUM, "Clock Pendelum");
        translationBuilder.add(ColourfulClocksTags.CLOCK_DOOR, "Clock Door");
        translationBuilder.add(ColourfulClocksTags.CLOCK_TOP_GLASS, "Clock Dial Glass");
        translationBuilder.add(ColourfulClocksTags.CLOCK_HAND, "Clock Hand");

        translationBuilder.add(SUBTITLE + "block.bornholm.chime", "Bornholm Chime");
        translationBuilder.add(SUBTITLE + "block.bornholm.door_open", "Door Opened");
        translationBuilder.add(SUBTITLE + "block.bornholm.door_close", "Door Closed");
        translationBuilder.add(SUBTITLE + "block.bornholm.remove_pendulum", "Pendulum Removed");
        translationBuilder.add(SUBTITLE + "block.bornholm.insert_pendulum", "Pendulum Inserted");
        translationBuilder.add(SUBTITLE + "block.bornholm.change_wood", "Changed Wood");
        translationBuilder.add(SUBTITLE + "block.bornholm.change_glass", "Changed Glass");
        translationBuilder.add(SUBTITLE + "block.bornholm.wax_off", "Wax Off");
        translationBuilder.add(SUBTITLE + "block.bornholm.wax_on", "Wax On");
        translationBuilder.add(SUBTITLE + "block.bornholm.axe_scrapes", "Axe Scrapes");
        translationBuilder.add(SUBTITLE + "block.bornholm.remove_pocket_watch", "Pocket Watch Removed");
        translationBuilder.add(SUBTITLE + "block.bornholm.insert_pocket_watch", "Pocket Watch Inserted");
    }

    private static void generateBornholmBaseTranslations(TranslationBuilder translationBuilder) {
        ColourfulClocksItemsImpl.BORNHOLM_BASE_VARIANTS.forEach((entry, supplier) -> {
            String translation = "Bornholm Base" + entry.getBaseTranslation();
            translationBuilder.add(supplier.get(), translation);
        });
    }

    private static void generateBornholmMiddleTranslations(TranslationBuilder translationBuilder) {
        ColourfulClocksItemsImpl.BORNHOLM_MIDDLE_VARIANTS.forEach((entry, supplier) -> {
            String translation = "Bornholm Trunk" + entry.getKey().getBaseTranslation() + entry.getValue().getBaseTranslation();
            translationBuilder.add(supplier.get(), translation);
        });
    }

    private static void generateBonrholmTopTranslations(TranslationBuilder translationBuilder) {
        ColourfulClocksItemsImpl.BORNHOLM_TOP_VARIANTS.forEach(((entry, supplier) -> {
            String translation = "Bornholm Dial" + entry.getKey().getBaseTranslation() + entry.getValue().getBaseTranslation();
            translationBuilder.add(supplier.get(), translation);
        }));
    }
}
