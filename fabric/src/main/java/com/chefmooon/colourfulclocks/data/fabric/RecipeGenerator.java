package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.data.types.*;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.data.builder.fabric.BornholmMiddleDataShapedRecipeBuilder;
import com.chefmooon.colourfulclocks.data.builder.fabric.BornholmTopDataShapedRecipeBuilder;
import com.chefmooon.colourfulclocks.data.builder.fabric.GlassDialDataShapedRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    private static RecipeOutput RECIPE_OUTPUT;
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        RECIPE_OUTPUT = recipeOutput;

        buildPocketWatchRecipe(ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get(), PocketWatchTypes.IRON, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get(), PocketWatchTypes.COPPER, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.GOLD_POCKET_WATCH.get(), PocketWatchTypes.GOLD, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get(), PocketWatchTypes.DIAMOND, recipeOutput);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.MISC,
                        ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get())
                .unlocks(RecipeProvider.getHasName(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get()), RecipeProvider.has(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get()))
                .save(recipeOutput, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get()) + "_smithing");

        buildPocketWatchRecipe(ColourfulClocksItemsImpl.QUARTZ_POCKET_WATCH.get(), PocketWatchTypes.QUARTZ, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.AMETHYST_POCKET_WATCH.get(), PocketWatchTypes.AMETHYST, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.LAPIS_LAZULI_POCKET_WATCH.get(), PocketWatchTypes.LAPIS_LAZULI, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.REDSTONE_POCKET_WATCH.get(), PocketWatchTypes.REDSTONE, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.EMERALD_POCKET_WATCH.get(), PocketWatchTypes.EMERALD, recipeOutput);

        buildPendulumRecipe(ColourfulClocksItemsImpl.IRON_PENDULUM.get(), PendulumTypes.IRON, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.COPPER_PENDULUM.get(), PendulumTypes.COPPER, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.GOLD_PENDULUM.get(), PendulumTypes.GOLD, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get(), PendulumTypes.DIAMOND, recipeOutput);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get()),
                Ingredient.of(Items.NETHERITE_INGOT),
                RecipeCategory.MISC,
                ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get())
                .unlocks(RecipeProvider.getHasName(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get()), RecipeProvider.has(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get()))
                .save(recipeOutput, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get()) + "_smithing");

        buildPendulumRecipe(ColourfulClocksItemsImpl.QUARTZ_PENDULUM.get(), PendulumTypes.QUARTZ, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.AMETHYST_PENDULUM.get(), PendulumTypes.AMETHYST, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.LAPIS_LAZULI_PENDULUM.get(), PendulumTypes.LAPIS_LAZULI, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.REDSTONE_PENDULUM.get(), PendulumTypes.REDSTONE, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.EMERALD_PENDULUM.get(), PendulumTypes.EMERALD, recipeOutput);

        buildBornholmRecipes(recipeOutput);
        buildMantelClockRecipes();
    }

    private static void buildPocketWatchRecipe(ItemLike item, PocketWatchTypes pocketWatchTypes, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item)
                .pattern(" C ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', pocketWatchTypes.getCraftingIngredient())
                .define('B', Items.REDSTONE)
                .define('C', Items.QUARTZ)
                .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                        pocketWatchTypes.getCraftingIngredient(),
                        Items.REDSTONE,
                        Items.QUARTZ)))
                .save(recipeOutput, RecipeProvider.getSimpleRecipeName(item));
    }

    private static void buildPendulumRecipe(ItemLike item, PendulumTypes pendulumTypes, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item)
                .pattern("  B")
                .pattern("AA ")
                .pattern("AA ")
                .define('A', pendulumTypes.getCraftingIngredient())
                .define('B', Items.CHAIN)
                .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                        pendulumTypes.getCraftingIngredient(),
                        Items.CHAIN)))
                .save(recipeOutput, RecipeProvider.getSimpleRecipeName(item));
    }

    private static void buildBornholmRecipes(RecipeOutput recipeOutput) {
        for (WoodTypes woodTypes : WoodTypes.values()) {
            ItemLike baseResult = ColourfulClocksItemsImpl.BORNHOLM_BASE_VARIANTS.get(woodTypes).get();
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, baseResult)
                    .pattern(" A ")
                    .pattern("AAA")
                    .define('A', woodTypes.getCraftingIngredient())
                    .unlockedBy(RecipeProvider.getHasName(woodTypes.getCraftingIngredient()), RecipeProvider.has(woodTypes.getCraftingIngredient()))
                    .save(recipeOutput, RecipeProvider.getSimpleRecipeName(baseResult));

            ItemLike middleResult = ColourfulClocksItemsImpl.BORNHOLM_MIDDLE_VARIANTS.get(woodTypes).get();
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, middleResult)
                    .pattern("AAA")
                    .pattern("A A")
                    .pattern("AAA")
                    .define('A', woodTypes.getCraftingIngredient())
                    .group("bornholm_middle_" + woodTypes.getName())
                    .unlockedBy(RecipeProvider.getHasName(woodTypes.getCraftingIngredient()), RecipeProvider.has(woodTypes.getCraftingIngredient()))
                    .save(recipeOutput, RecipeProvider.getSimpleRecipeName(middleResult));
            buildBornholmMiddleVariantRecipes(woodTypes, middleResult);

            ItemLike topResult = ColourfulClocksItemsImpl.BORNHOLM_TOP_VARIANTS.get(woodTypes).get();
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, topResult)
                    .pattern("AAA")
                    .pattern("BCA")
                    .pattern("AAA")
                    .define('A', woodTypes.getCraftingIngredient())
                    .define('B', BornholmTopGlassTypes.GLASS.getItem())
                    .define('C', Items.QUARTZ)
                    .group("bornholm_top_" + woodTypes.getName())
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                            woodTypes.getCraftingIngredient(),
                            BornholmTopGlassTypes.GLASS.getItem(),
                            Items.QUARTZ)))
                    .save(recipeOutput, RecipeProvider.getSimpleRecipeName(topResult));
            buildBornholmTopVariantRecipes(woodTypes, topResult);
        }
    }

    private static void buildBornholmMiddleVariantRecipes(WoodTypes woodType, ItemLike result) {
        for (BornholmDoorTypes doorType : BornholmDoorTypes.values()) {
            if (doorType == BornholmDoorTypes.BASE) continue;
            BornholmMiddleDataShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                    .pattern("AAA")
                    .pattern("A B")
                    .pattern("AAA")
                    .define('A', woodType.getCraftingIngredient())
                    .define('B', doorType.getItem())
                    .group("bornholm_middle_" + woodType.getName())
                    .setResultData(doorType)
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                            woodType.getCraftingIngredient(),
                            doorType.getItem())))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(result) + "_" + doorType.getName());
        }
    }

    private static void buildBornholmTopVariantRecipes(WoodTypes woodType, ItemLike result) {
        for (BornholmTopGlassTypes glassType : BornholmTopGlassTypes.values()) {
            if (glassType == BornholmTopGlassTypes.GLASS) continue;
            BornholmTopDataShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                    .pattern("AAA")
                    .pattern("BCA")
                    .pattern("AAA")
                    .define('A', woodType.getCraftingIngredient())
                    .define('B', glassType.getItem())
                    .define('C', Items.QUARTZ)
                    .group("bornholm_top_" + woodType.getName())
                    .setResultData(glassType)
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                            woodType.getCraftingIngredient(),
                            glassType.getItem(),
                            Items.QUARTZ)))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(result) + "_" + glassType.getName());
        }
    }

    private static void buildMantelClockRecipes() {
        for (WoodTypes woodType : WoodTypes.values()) {
            ItemLike result = ColourfulClocksItemsImpl.MANTEL_CLOCK_VARIANTS.get(woodType).get();
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                    .pattern(" A ")
                    .pattern("ACA")
                    .pattern("ABA")
                    .define('A', woodType.getCraftingIngredient())
                    .define('B', BornholmTopGlassTypes.GLASS.getItem())
                    .define('C', Items.QUARTZ)
                    .group("mantel_clock_" + woodType.getName())
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                            woodType.getCraftingIngredient(),
                            BornholmTopGlassTypes.GLASS.getItem(),
                            Items.QUARTZ)))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(result));
            buildMantelClockVariantRecipes(woodType, result);
        }
    }

    private static void buildMantelClockVariantRecipes(WoodTypes woodType, ItemLike result) {
        for (BornholmTopGlassTypes glassTypes : BornholmTopGlassTypes.values()) {
            if (glassTypes == BornholmTopGlassTypes.GLASS) continue;
            GlassDialDataShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                    .pattern(" A ")
                    .pattern("ACA")
                    .pattern("ABA")
                    .define('A', woodType.getCraftingIngredient())
                    .define('B', glassTypes.getItem())
                    .define('C', Items.QUARTZ)
                    .group("mantel_clock_" + woodType.getName())
                    .setResultData(glassTypes)
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                            woodType.getCraftingIngredient(),
                            glassTypes.getItem(),
                            Items.QUARTZ)))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(result) + "_" + glassTypes.getName());
        }
    }
}
