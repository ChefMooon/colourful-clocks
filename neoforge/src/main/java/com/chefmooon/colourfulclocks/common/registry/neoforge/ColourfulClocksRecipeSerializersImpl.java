package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.crafting.*;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksRecipeSerializers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ColourfulClocksRecipeSerializersImpl {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ColourfulClocks.MOD_ID);

    public static final Supplier<RecipeSerializer<?>> WAXED_COPPER_HANDBELL = RECIPE_SERIALIZERS.register(ColourfulClocksRecipeSerializers.WAXED_COPPER_HANDBELL.getPath(), WaxedCopperHandbellShapelessRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> BORNHOLM_MIDDLE = RECIPE_SERIALIZERS.register(ColourfulClocksRecipeSerializers.BORNHOLM_MIDDLE.getPath(), BornholmMiddleShapedRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> BORNHOLM_TOP = RECIPE_SERIALIZERS.register(ColourfulClocksRecipeSerializers.BORNHOLM_TOP.getPath(), BornholmTopShapedRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> MANTEL_CLOCK = RECIPE_SERIALIZERS.register(ColourfulClocksRecipeSerializers.MANTEL_CLOCK.getPath(), MantelClockShapedRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> ALARM_CLOCK = RECIPE_SERIALIZERS.register(ColourfulClocksRecipeSerializers.ALARM_CLOCK.getPath(), AlarmClockShapedRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<?>> WALL_CLOCK = RECIPE_SERIALIZERS.register(ColourfulClocksRecipeSerializers.WALL_CLOCK.getPath(), WallClockShapedRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
