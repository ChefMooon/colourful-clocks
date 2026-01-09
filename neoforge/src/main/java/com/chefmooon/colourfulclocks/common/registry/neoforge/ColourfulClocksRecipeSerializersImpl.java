package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.crafting.WaxedCopperHandbellShapelessRecipe;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksRecipeSerializers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ColourfulClocksRecipeSerializersImpl {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ColourfulClocks.MOD_ID);

    public static final Supplier<RecipeSerializer<?>> WAXED_COPPER_HANDBELL = RECIPE_SERIALIZERS.register(ColourfulClocksRecipeSerializers.WAXED_COPPER_HANDBELL.getPath(), WaxedCopperHandbellShapelessRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
