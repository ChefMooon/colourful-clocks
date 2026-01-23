package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.crafting.*;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksRecipeSerializers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

import java.util.function.Supplier;

public class ColourfulClocksRecipeSerializersImpl {

    public static final Supplier<RecipeSerializer<?>> WAXED_COPPER_HANDBELL = registerRecipeSerializer(ColourfulClocksRecipeSerializers.WAXED_COPPER_HANDBELL, WaxedCopperHandbellShapelessRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> BORNHOLM_MIDDLE = registerRecipeSerializer(ColourfulClocksRecipeSerializers.BORNHOLM_MIDDLE, BornholmMiddleShapedRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> BORNHOLM_TOP = registerRecipeSerializer(ColourfulClocksRecipeSerializers.BORNHOLM_TOP, BornholmTopShapedRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> CLOCK = registerRecipeSerializer(ColourfulClocksRecipeSerializers.CLOCK, ClockDataShapedRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> ALARM_CLOCK = registerRecipeSerializer(ColourfulClocksRecipeSerializers.ALARM_CLOCK, AlarmClockShapedRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> WALL_CLOCK = registerRecipeSerializer(ColourfulClocksRecipeSerializers.WALL_CLOCK, WallClockShapedRecipe.Serializer::new);
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
