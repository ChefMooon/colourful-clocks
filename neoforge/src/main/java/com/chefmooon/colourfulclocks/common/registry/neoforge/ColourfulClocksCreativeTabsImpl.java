package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ColourfulClocksCreativeTabsImpl {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ColourfulClocks.MOD_ID);
    public static final Supplier<CreativeModeTab> TAB_COLOURFUL_CLOCKS = CREATIVE_MODE_TAB.register(ColourfulClocks.MOD_ID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + ColourfulClocks.MOD_ID))
                    .icon(() -> new ItemStack(ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get()))
                    .displayItems((parameters, output) -> ColourfulClocksItemsImpl.CREATIVE_TAB_ITEMS.forEach((item) -> output.accept(item.get())))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
