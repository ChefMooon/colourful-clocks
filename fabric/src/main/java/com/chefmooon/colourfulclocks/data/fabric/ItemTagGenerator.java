package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public ItemTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {


        getOrCreateTagBuilder(ColourfulClocksTags.CLOCK_DOOR)
                .add(
                        Items.GLASS_PANE,
                        Items.WHITE_STAINED_GLASS_PANE,
                        Items.ORANGE_STAINED_GLASS_PANE,
                        Items.MAGENTA_STAINED_GLASS_PANE,
                        Items.LIGHT_BLUE_STAINED_GLASS_PANE,
                        Items.YELLOW_STAINED_GLASS_PANE,
                        Items.LIME_STAINED_GLASS_PANE,
                        Items.PINK_STAINED_GLASS_PANE,
                        Items.GRAY_STAINED_GLASS_PANE,
                        Items.LIGHT_GRAY_STAINED_GLASS_PANE,
                        Items.CYAN_STAINED_GLASS_PANE,
                        Items.PURPLE_STAINED_GLASS_PANE,
                        Items.BLUE_STAINED_GLASS_PANE,
                        Items.BROWN_STAINED_GLASS_PANE,
                        Items.GREEN_STAINED_GLASS_PANE,
                        Items.RED_STAINED_GLASS_PANE,
                        Items.BLACK_STAINED_GLASS_PANE
                );

        getOrCreateTagBuilder(ColourfulClocksTags.CLOCK_TOP_GLASS)
                .add(
                        Items.GLASS_PANE,
                        Items.WHITE_STAINED_GLASS_PANE,
                        Items.ORANGE_STAINED_GLASS_PANE,
                        Items.MAGENTA_STAINED_GLASS_PANE,
                        Items.LIGHT_BLUE_STAINED_GLASS_PANE,
                        Items.YELLOW_STAINED_GLASS_PANE,
                        Items.LIME_STAINED_GLASS_PANE,
                        Items.PINK_STAINED_GLASS_PANE,
                        Items.GRAY_STAINED_GLASS_PANE,
                        Items.LIGHT_GRAY_STAINED_GLASS_PANE,
                        Items.CYAN_STAINED_GLASS_PANE,
                        Items.PURPLE_STAINED_GLASS_PANE,
                        Items.BLUE_STAINED_GLASS_PANE,
                        Items.BROWN_STAINED_GLASS_PANE,
                        Items.GREEN_STAINED_GLASS_PANE,
                        Items.RED_STAINED_GLASS_PANE,
                        Items.BLACK_STAINED_GLASS_PANE
                );

        getOrCreateTagBuilder(ColourfulClocksTags.CLOCK_PENDULUM)
                .add(
                        ColourfulClocksItemsImpl.IRON_PENDULUM.get(),
                        ColourfulClocksItemsImpl.COPPER_PENDULUM.get(),
                        ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get(),
                        ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get(),
                        ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get(),
                        ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get(),
                        ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get(),
                        ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get(),
                        ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get(),
                        ColourfulClocksItemsImpl.GOLD_PENDULUM.get(),
                        ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get(),
                        ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get(),
                        ColourfulClocksItemsImpl.QUARTZ_PENDULUM.get(),
                        ColourfulClocksItemsImpl.AMETHYST_PENDULUM.get(),
                        ColourfulClocksItemsImpl.LAPIS_LAZULI_PENDULUM.get(),
                        ColourfulClocksItemsImpl.REDSTONE_PENDULUM.get(),
                        ColourfulClocksItemsImpl.EMERALD_PENDULUM.get()
                        );

        getOrCreateTagBuilder(ColourfulClocksTags.CLOCK_HAND)
                .add(
                        ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.GOLD_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.QUARTZ_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.AMETHYST_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.LAPIS_LAZULI_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.REDSTONE_POCKET_WATCH.get(),
                        ColourfulClocksItemsImpl.EMERALD_POCKET_WATCH.get()
                        );
    }
}
