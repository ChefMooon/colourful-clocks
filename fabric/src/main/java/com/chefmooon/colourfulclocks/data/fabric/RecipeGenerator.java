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

        buildPendulumRecipe(ColourfulClocksItemsImpl.IRON_PENDULUM.get(), PendulumTypes.IRON, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.COPPER_PENDULUM.get(), PendulumTypes.COPPER, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.GOLD_PENDULUM.get(), PendulumTypes.GOLD, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get(), PendulumTypes.DIAMOND, recipeOutput);
        buildPendulumRecipe(ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get(), PendulumTypes.NETHERITE, recipeOutput);

        buildBornholmRecipes(recipeOutput);
    }


    private static void buildPocketWatchRecipe(ItemLike item, PocketWatchTypes pocketWatchTypes, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item)
                .pattern(" C ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', pocketWatchTypes.getItem())
                .define('B', Items.REDSTONE)
                .define('C', Items.QUARTZ)
                .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                        pocketWatchTypes.getItem(),
                        Items.REDSTONE,
                        Items.QUARTZ)))
                .save(recipeOutput, RecipeProvider.getSimpleRecipeName(item));
    }

    private static void buildPendulumRecipe(ItemLike item, PendulumTypes pendulumTypes, RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item)
                .pattern("B")
                .pattern("A")
                .pattern("A")
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

//            for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
//                ItemLike middleResult = ColourfulClocksItemsImpl.BORNHOLM_MIDDLE_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmDoorTypes.BASE)).get();
//                if (bornholmDoorTypes == BornholmDoorTypes.BASE) {
//                    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, middleResult)
//                            .pattern("AAA")
//                            .pattern("A A")
//                            .pattern("AAA")
//                            .define('A', woodTypes.getCraftingIngredient())
//                            .unlockedBy(RecipeProvider.getHasName(woodTypes.getCraftingIngredient()), RecipeProvider.has(woodTypes.getCraftingIngredient()))
//                            .save(recipeOutput, RecipeProvider.getSimpleRecipeName(middleResult));
//                } else {
//                    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, middleResult)
//                            .pattern("AAA")
//                            .pattern("B A")
//                            .pattern("AAA")
//                            .define('A', woodTypes.getCraftingIngredient())
//                            .define('B', bornholmDoorTypes.getItem())
//                            .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
//                                    woodTypes.getCraftingIngredient(),
//                                    bornholmDoorTypes.getItem())))
//                            .save(recipeOutput, RecipeProvider.getSimpleRecipeName(middleResult));
//                }
//            }

//            for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
//                if (woodTypes == WoodTypes.OAK && bornholmDoorTypes == BornholmDoorTypes.GLASS) {
//                    Block block = ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(woodTypes).get();
//                    ItemStack stack = new ItemStack(block.defaultBlockState().setValue(BornholmMiddleBlock.DOOR_TYPE, BornholmDoorTypes.GLASS).getBlock().asItem());
//                    ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, stack.getItem())
//                            .pattern("AAA")
//                            .pattern("B A")
//                            .pattern("AAA")
//                            .define('A', woodTypes.getCraftingIngredient())
//                            .define('B', bornholmDoorTypes.getItem())
//                            .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
//                                    woodTypes.getCraftingIngredient(),
//                                    bornholmDoorTypes.getItem())))
//                            .save(recipeOutput, RecipeProvider.getSimpleRecipeName(stack.getItem()) + "_test");
//                }
//            }

//            for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
//                ItemLike topResult = ColourfulClocksItemsImpl.BORNHOLM_TOP_VARIANTS.get(new AbstractMap.SimpleEntry<>(woodTypes, bornholmTopGlassTypes)).get();
//                ShapedRecipeBuilder.shaped(RecipeCategory.MISC, topResult)
//                        .pattern("AAA")
//                        .pattern("BCA")
//                        .pattern("AAA")
//                        .define('A', woodTypes.getCraftingIngredient())
//                        .define('B', bornholmTopGlassTypes.getItem())
//                        .define('C', Items.QUARTZ)
//                        .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
//                                woodTypes.getCraftingIngredient(),
//                                bornholmTopGlassTypes.getItem(),
//                                Items.QUARTZ)))
//                        .save(recipeOutput, RecipeProvider.getSimpleRecipeName(topResult));
//            }
        }
    }
}
