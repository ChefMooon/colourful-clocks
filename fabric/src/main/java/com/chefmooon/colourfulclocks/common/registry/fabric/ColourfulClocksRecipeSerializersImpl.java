package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.crafting.WaxedCopperHandbellShapelessRecipe;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksRecipeSerializers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class ColourfulClocksRecipeSerializersImpl {

    public static final Supplier<RecipeSerializer<?>> WAXED_COPPER_HANDBELL = registerRecipeSerializer(ColourfulClocksRecipeSerializers.WAXED_COPPER_HANDBELL, WaxedCopperHandbellShapelessRecipe.Serializer::new);
    public static <B extends RecipeSerializer<?>> Supplier<B> registerRecipeSerializer(ResourceLocation resourceLocation, Supplier<B> supplier) {
        return registerRecipeSerializer(resourceLocation, supplier, BuiltInRegistries.RECIPE_SERIALIZER);
    }
    public static <R, T extends R> Supplier<T> registerRecipeSerializer(ResourceLocation resourceLocation, Supplier<T> supplier, Registry<R> registry) {
        T object = supplier.get();
        Registry.register(registry, resourceLocation, object);
        return () -> object;
    }

    public static void register() {
    }
}
