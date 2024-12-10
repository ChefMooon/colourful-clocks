package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.PendulumTypes;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.core.WoodTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get(), PocketWatchTypes.IRON, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get(), PocketWatchTypes.COPPER, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.GOLD_POCKET_WATCH.get(), PocketWatchTypes.GOLD, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get(), PocketWatchTypes.DIAMOND, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.NETHERITE_POCKET_WATCH.get(), PocketWatchTypes.NETHERITE, recipeOutput);

        buildPocketWatchRecipe(ColourfulClocksItemsImpl.QUARTZ_POCKET_WATCH.get(), PocketWatchTypes.QUARTZ, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.AMETHYST_POCKET_WATCH.get(), PocketWatchTypes.AMETHYST, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.LAPIS_LAZULI_POCKET_WATCH.get(), PocketWatchTypes.LAPIS_LAZULI, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.REDSTONE_POCKET_WATCH.get(), PocketWatchTypes.REDSTONE, recipeOutput);
        buildPocketWatchRecipe(ColourfulClocksItemsImpl.EMERALD_POCKET_WATCH.get(), PocketWatchTypes.EMERALD, recipeOutput);

        buildPendulumRecipe(ColourfulClocksItemsImpl.IRON_PENDULUM.get(), PendulumTypes.IRON, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.COPPER_PENDULUM.get(), PendulumTypes.COPPER, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.GOLD_PENDULUM.get(), PendulumTypes.GOLD, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get(), PendulumTypes.DIAMOND, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get(), PendulumTypes.NETHERITE, recipeOutput);

        buildPendulumRecipe(ColourfulClocksItemsImpl.QUARTZ_PENDULUM.get(), PendulumTypes.QUARTZ, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.AMETHYST_PENDULUM.get(), PendulumTypes.AMETHYST, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.LAPIS_LAZULI_PENDULUM.get(), PendulumTypes.LAPIS_LAZULI, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.REDSTONE_PENDULUM.get(), PendulumTypes.REDSTONE, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.EMERALD_PENDULUM.get(), PendulumTypes.EMERALD, recipeOutput);

        buildBornholmRecipes(recipeOutput);
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
                    .unlockedBy(RecipeProvider.getHasName(woodTypes.getCraftingIngredient()), RecipeProvider.has(woodTypes.getCraftingIngredient()))
                    .save(recipeOutput, RecipeProvider.getSimpleRecipeName(middleResult));

            ItemLike topResult = ColourfulClocksItemsImpl.BORNHOLM_TOP_VARIANTS.get(woodTypes).get();
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, topResult)
                    .pattern("AAA")
                    .pattern("BCA")
                    .pattern("AAA")
                    .define('A', woodTypes.getCraftingIngredient())
                    .define('B', BornholmTopGlassTypes.GLASS.getItem())
                    .define('C', Items.QUARTZ)
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                            woodTypes.getCraftingIngredient(),
                            BornholmTopGlassTypes.GLASS.getItem(),
                            Items.QUARTZ)))
                    .save(recipeOutput, RecipeProvider.getSimpleRecipeName(topResult));

            // todo - figure out how to make recipes for all glass variants TRUNK and DIAL
        }
    }
}
