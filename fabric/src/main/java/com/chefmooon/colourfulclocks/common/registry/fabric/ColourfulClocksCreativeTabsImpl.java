package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ColourfulClocksCreativeTabsImpl {
    public static final ResourceKey<CreativeModeTab> ITEM_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, TextUtil.res(ColourfulClocks.MOD_ID));

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ITEM_GROUP, FabricItemGroup.builder()
                .title(Component.translatable("itemGroup." + ColourfulClocks.MOD_ID))
                .icon(() -> new ItemStack(ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get()))
                .build());
    }
}
