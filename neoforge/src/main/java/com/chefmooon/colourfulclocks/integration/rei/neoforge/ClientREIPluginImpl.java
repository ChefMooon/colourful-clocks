package com.chefmooon.colourfulclocks.integration.rei.neoforge;

import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.forge.REIPluginClient;
import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
import net.minecraft.network.chat.Component;

import java.util.List;

@REIPluginClient
public class ClientREIPluginImpl implements REIClientPlugin {
    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(
                ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get(),
                ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get(),
                ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get(),
                ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get(),
                ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get(),
                ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get(),
                ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get(),
                ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get(),
                ColourfulClocksItemsImpl.COPPER_PENDULUM.get(),
                ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get(),
                ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get(),
                ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get(),
                ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get(),
                ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get(),
                ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get(),
                ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get()
        )), Component.translatable("item.colourfulclocks.netherite_pocket_watch")).lines(TextUtil.getTranslatable("rei.info.copper_info")));
    }
}
