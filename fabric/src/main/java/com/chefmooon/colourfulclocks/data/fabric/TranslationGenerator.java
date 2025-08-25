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
        String TOOLTIP = MOD_ID + ".tooltip.";
        String ADVANCEMENT = MOD_ID + ".advancement.";
        String REI = MOD_ID + ".rei.";
        String WAILA = MOD_ID + ".tooltip.waila.";
        String WAILA_CONFIG = "config.waila.plugin_" + MOD_ID;
        String JADE_CONFIG = "config.jade.plugin_" + MOD_ID;

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

        translationBuilder.add(ColourfulClocksItemsImpl.QUARTZ_POCKET_WATCH.get(), "Quartz Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.AMETHYST_POCKET_WATCH.get(), "Amethyst Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.LAPIS_LAZULI_POCKET_WATCH.get(), "Lapis Lazuli Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.REDSTONE_POCKET_WATCH.get(), "Redstone Pocket Watch");
        translationBuilder.add(ColourfulClocksItemsImpl.EMERALD_POCKET_WATCH.get(), "Emerald Pocket Watch");

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

        translationBuilder.add(ColourfulClocksItemsImpl.QUARTZ_PENDULUM.get(), "Quartz Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.AMETHYST_PENDULUM.get(), "Amethyst Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.LAPIS_LAZULI_PENDULUM.get(), "Lapis Lazuli Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.REDSTONE_PENDULUM.get(), "Redstone Pendulum");
        translationBuilder.add(ColourfulClocksItemsImpl.EMERALD_PENDULUM.get(), "Emerald Pendulum");

        generateBornholmBaseTranslations(translationBuilder);
        generateBornholmMiddleTranslations(translationBuilder);
        generateBonrholmTopTranslations(translationBuilder);

        generateMantelClockTranslations(translationBuilder);
        generateTallMantelClockTranslations(translationBuilder);

        translationBuilder.add(ColourfulClocksTags.CLOCK_PENDULUM, "Clock Pendulum");
        translationBuilder.add(ColourfulClocksTags.CLOCK_DOOR, "Clock Door");
        translationBuilder.add(ColourfulClocksTags.CLOCK_TOP_GLASS, "Clock Dial Glass");
        translationBuilder.add(ColourfulClocksTags.CLOCK_HAND, "Clock Hand");
        translationBuilder.add(ColourfulClocksTags.BORNHOLM_BASE, "Bornholm Base");
        translationBuilder.add(ColourfulClocksTags.BORNHOLM_TRUNK, "Bornholm Trunk");
        translationBuilder.add(ColourfulClocksTags.BORNHOLM_DIAL, "Bornholm Dial");
        translationBuilder.add(ColourfulClocksTags.MANTEL_CLOCK, "Mantel Clock");
        translationBuilder.add(ColourfulClocksTags.TALL_MANTEL_CLOCK, "Tall Mantel Clock");

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
        translationBuilder.add(SUBTITLE + "block.bornholm.tick", "Bornholm Tick");
        translationBuilder.add(SUBTITLE + "block.clock.tick", "Clock Tick");
        translationBuilder.add(SUBTITLE + "block.enable_ticking", "Ticking Enabled");
        translationBuilder.add(SUBTITLE + "block.disable_ticking", "Ticking Disabled");

        translationBuilder.add(TOOLTIP + "weathering", "Weathering");
        translationBuilder.add(TOOLTIP + "ticking", "Ticking");

        translationBuilder.add(WAILA_CONFIG, FORMATTED_MOD_ID);
        translationBuilder.add(WAILA_CONFIG + ".bornholm_dial_glass_type", "Bornholm Dial Glass Type");
        translationBuilder.add(WAILA_CONFIG + ".bornholm_trunk_door_type", "Bornholm Trunk Door Type");

        translationBuilder.add(WAILA_CONFIG + ".glass_type", "Glass Type");
        translationBuilder.add(WAILA_CONFIG + ".pocket_watch_type", "Pocket Watch Type");
        translationBuilder.add(WAILA_CONFIG + ".ticking", "Ticking");

        translationBuilder.add(JADE_CONFIG + ".bornholm_dial_glass_type", "Bornholm Dial Glass Type");
        translationBuilder.add(JADE_CONFIG + ".bornholm_trunk_door_type", "Bornholm Dial Glass Type");

        translationBuilder.add(JADE_CONFIG + ".glass_type", "Glass Type");
        translationBuilder.add(JADE_CONFIG + ".pocket_watch_type", "Pocket Watch Type");
        translationBuilder.add(JADE_CONFIG + ".ticking", "Ticking");

        translationBuilder.add(REI + "info.copper_info", "This cannot be crafted. It can oxidize when placed in complete clocks and can be waxed to preserve the condition. An Axe can be used to remove wax.");

        translationBuilder.add(ADVANCEMENT + "root", FORMATTED_MOD_ID);
        translationBuilder.add(ADVANCEMENT + "root.desc", "Stylish Clocks!");

        translationBuilder.add(ADVANCEMENT + "bornholm_clock", "Bornholm Clock");
        translationBuilder.add(ADVANCEMENT + "bornholm_clock.desc", "This clock comes in 3 parts, place them from top to bottom: base, trunk, dial to activate it.");

        translationBuilder.add(ADVANCEMENT + "bornholm_base", "Solid Foundations");
        translationBuilder.add(ADVANCEMENT + "bornholm_base.desc", "Craft and place a Bornholm Base of any type. Every great clock starts with a sturdy base!");

        translationBuilder.add(ADVANCEMENT + "bornholm_trunk", "Time's Backbone");
        translationBuilder.add(ADVANCEMENT + "bornholm_trunk.desc", "Craft and place a Bornholm Trunk of any type. Building upward, one tick at a time!");

        translationBuilder.add(ADVANCEMENT + "bornholm_dial", "Face the Clock");
        translationBuilder.add(ADVANCEMENT + "bornholm_dial.desc", "Craft and Place a Bornholm Dial of any type. Give your clock its identity!");

        translationBuilder.add(ADVANCEMENT + "mantel_clock", "Mantel Clock");
        translationBuilder.add(ADVANCEMENT + "mantel_clock.desc", "This small clock can be placed on any surface. It can be crafted with any wood type and has a few customization options available.");

        translationBuilder.add(ADVANCEMENT + "bornholm_trunk_wind_charge", "How could you?!");
        translationBuilder.add(ADVANCEMENT + "bornholm_trunk_wind_charge.desc", "Trunk doors can be opened/closed by a wind charge. Do what you will with this information.");

        translationBuilder.add(ADVANCEMENT + "bornholm_activated", "Bornholm Activated");
        translationBuilder.add(ADVANCEMENT + "bornholm_activated.desc", "Copper Pocket Watches and Pendulums will only weather when the clock is fully assembled");

        translationBuilder.add(ADVANCEMENT + "insert_pocket_watch", "Clock Hands");
        translationBuilder.add(ADVANCEMENT + "insert_pocket_watch.desc", "Craft and insert a Pocket Watch into a Bornholm Dial or clock");

        translationBuilder.add(ADVANCEMENT + "insert_pendulum", "Pendulum");
        translationBuilder.add(ADVANCEMENT + "insert_pendulum.desc", "Craft and insert a Pendulum into a Bornholm Trunk or Clock");

        translationBuilder.add(ADVANCEMENT + "glass_change", "Custom Panes");
        translationBuilder.add(ADVANCEMENT + "glass_change.desc", "Use a Glass Pane to change the glass in a clock");

        translationBuilder.add(ADVANCEMENT + "enable_ticking", "Tick Tock");
        translationBuilder.add(ADVANCEMENT + "enable_ticking.desc", "Use Redstone to activate the ticking sound in your Bornholm Dial or clock");

        translationBuilder.add(ADVANCEMENT + "disable_ticking", "Silence");
        translationBuilder.add(ADVANCEMENT + "disable_ticking.desc", "Use a Pickaxe to stop the ticking sound");

        translationBuilder.add(ADVANCEMENT + "copper_items", "Oxidized");
        translationBuilder.add(ADVANCEMENT + "copper_items.desc", "Copper items only weather when placed in a clock. They can be waxed to preserve their condition.");

        translationBuilder.add(ADVANCEMENT + "copper_wax_on", "Wax On");
        translationBuilder.add(ADVANCEMENT + "copper_wax_on.desc", "Apply Honeycomb to a Copper Pocket Watch or Pendulum in a clock");

        translationBuilder.add(ADVANCEMENT + "copper_wax_off", "Wax Off");
        translationBuilder.add(ADVANCEMENT + "copper_wax_off.desc", "Scrape Oxidation or Wax off of a Copper Pocket Watch or Pendulum in a clock");

        translationBuilder.add(ADVANCEMENT + "bornholm_trunk_glass_change", "Custom Door");
        translationBuilder.add(ADVANCEMENT + "bornholm_trunk_glass_change.desc", "Use a Glass Pane to customize your Bornholm Trunk door. A block of wood can be used to restore the original door!");
    }

    private static void generateBornholmBaseTranslations(TranslationBuilder translationBuilder) {
        ColourfulClocksItemsImpl.BORNHOLM_BASE_VARIANTS.forEach((entry, supplier) -> {
            String translation = "Bornholm Base" + entry.getBaseTranslation();
            translationBuilder.add(supplier.get(), translation);
        });
    }

    private static void generateBornholmMiddleTranslations(TranslationBuilder translationBuilder) {
        ColourfulClocksItemsImpl.BORNHOLM_MIDDLE_VARIANTS.forEach((entry, supplier) -> {
            String translation = "Bornholm Trunk" + entry.getBaseTranslation();
            translationBuilder.add(supplier.get(), translation);
        });
    }

    private static void generateBonrholmTopTranslations(TranslationBuilder translationBuilder) {
        ColourfulClocksItemsImpl.BORNHOLM_TOP_VARIANTS.forEach(((entry, supplier) -> {
            String translation = "Bornholm Dial" + entry.getBaseTranslation();
            translationBuilder.add(supplier.get(), translation);
        }));
    }

    private static void generateMantelClockTranslations(TranslationBuilder translationBuilder) {
        ColourfulClocksItemsImpl.MANTEL_CLOCK_VARIANTS.forEach(((entry, supplier) -> {
            String translation = entry.getBaseTranslationNoSpace() + " Mantel Clock";
            translationBuilder.add(supplier.get(), translation);
        }));
    }

    private static void generateTallMantelClockTranslations(TranslationBuilder translationBuilder) {
        ColourfulClocksItemsImpl.TALL_MANTEL_CLOCK_VARIANTS.forEach((entry, supplier) -> {
            String translation = entry.getBaseTranslationNoSpace() + " Tall Mantel Clock";
            translationBuilder.add(supplier.get(), translation);
        });
    }
}
