package com.chefmooon.colourfulclocks.integration.jei.neoforge;

import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.chefmooon.colourfulclocks.integration.jei.JEIPlugin;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPluginImpl implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return JEIPlugin.ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addIngredientInfo(List.of(
                new ItemStack(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get()),
                new ItemStack(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get()),
                new ItemStack(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get()),
                new ItemStack(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get()),
                new ItemStack(ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get()),
                new ItemStack(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get()),
                new ItemStack(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get()),
                new ItemStack(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get()),
                new ItemStack(ColourfulClocksItemsImpl.COPPER_PENDULUM.get()),
                new ItemStack(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get()),
                new ItemStack(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get()),
                new ItemStack(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get()),
                new ItemStack(ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get()),
                new ItemStack(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get()),
                new ItemStack(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get()),
                new ItemStack(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get()),
                new ItemStack(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get())),
        VanillaTypes.ITEM_STACK, TextUtil.getTranslatable("rei.info.copper_info"));
    }
}
