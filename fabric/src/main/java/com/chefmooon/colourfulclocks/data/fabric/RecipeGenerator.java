package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.*;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.data.builder.fabric.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    private static RecipeOutput RECIPE_OUTPUT;
    public RecipeGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        RECIPE_OUTPUT = recipeOutput;

        buildHandbellRecipe(ColourfulClocksItemsImpl.IRON_HANDBELL.get(), HandbellTypes.IRON);
        buildHandbellRecipe(ColourfulClocksItemsImpl.COPPER_HANDBELL.get(), HandbellTypes.COPPER);
        buildHandbellRecipe(ColourfulClocksItemsImpl.GOLD_HANDBELL.get(), HandbellTypes.GOLD);
        buildHandbellRecipe(ColourfulClocksItemsImpl.DIAMOND_HANDBELL.get(), HandbellTypes.DIAMOND);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(ColourfulClocksItemsImpl.DIAMOND_HANDBELL.get()),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        RecipeCategory.DECORATIONS,
                        ColourfulClocksItemsImpl.NETHERITE_HANDBELL.get())
                .unlocks(RecipeProvider.getHasName(ColourfulClocksItemsImpl.DIAMOND_HANDBELL.get()), RecipeProvider.has(ColourfulClocksItemsImpl.DIAMOND_HANDBELL.get()))
                .save(recipeOutput, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.NETHERITE_HANDBELL.get()) + "_smithing");

        buildHandbellRecipe(ColourfulClocksItemsImpl.QUARTZ_HANDBELL.get(), HandbellTypes.QUARTZ);
        buildHandbellRecipe(ColourfulClocksItemsImpl.AMETHYST_HANDBELL.get(), HandbellTypes.AMETHYST);
        buildHandbellRecipe(ColourfulClocksItemsImpl.LAPIS_LAZULI_HANDBELL.get(), HandbellTypes.LAPIS_LAZULI);
        buildHandbellRecipe(ColourfulClocksItemsImpl.REDSTONE_HANDBELL.get(), HandbellTypes.REDSTONE);
        buildHandbellRecipe(ColourfulClocksItemsImpl.EMERALD_HANDBELL.get(), HandbellTypes.EMERALD);

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
        buildTallMantelClockRecipes();
        buildWaxedCopperItemRecipes();
        buildWallClockRecipes();
        buildAlarmClockRecipes();
    }

    private static void buildHandbellRecipe(ItemLike item, HandbellTypes handbellTypes) {
        for (HandbellHandleTypes handleType : HandbellHandleTypes.values()) {
            HandbellShapedRecipeBuilder.shaped(RecipeCategory.MISC, item)
                    .pattern("A")
                    .pattern("B")
                    .define('A', handbellTypes.getCraftingIngredient())
                    .define('B', handleType.getCraftingIngredient())
                    .group(item.asItem().getDescriptionId().replace("block.colourfulclocks.", ""))
                    .setData(new HandbellComponent(handbellTypes, handleType, handbellTypes == HandbellTypes.COPPER ? Optional.of(0) : Optional.empty()))
                    .unlockedBy(RecipeProvider.getHasName(handbellTypes.getCraftingIngredient()), RecipeProvider.has(handbellTypes.getCraftingIngredient()))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(item) + "_" + handleType.getSerializedName());
        }
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
        for (ClockTypes clockTypes : ClockTypes.values()) {
            ItemLike baseResult = ColourfulClocksItemsImpl.BORNHOLM_BASE_VARIANTS.get(clockTypes).get();
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, baseResult)
                    .pattern(" A ")
                    .pattern("AAA")
                    .define('A', clockTypes.getCraftingIngredient())
                    .unlockedBy(RecipeProvider.getHasName(clockTypes.getCraftingIngredient()), RecipeProvider.has(clockTypes.getCraftingIngredient()))
                    .save(recipeOutput, RecipeProvider.getSimpleRecipeName(baseResult));

            ItemLike middleResult = ColourfulClocksItemsImpl.BORNHOLM_MIDDLE_VARIANTS.get(clockTypes).get();
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, middleResult)
                    .pattern("AAA")
                    .pattern("A A")
                    .pattern("AAA")
                    .define('A', clockTypes.getCraftingIngredient())
                    .group("bornholm_middle_" + clockTypes.getName())
                    .unlockedBy(RecipeProvider.getHasName(clockTypes.getCraftingIngredient()), RecipeProvider.has(clockTypes.getCraftingIngredient()))
                    .save(recipeOutput, RecipeProvider.getSimpleRecipeName(middleResult));
            buildBornholmMiddleVariantRecipes(clockTypes, middleResult);

            ItemLike topResult = ColourfulClocksItemsImpl.BORNHOLM_TOP_VARIANTS.get(clockTypes).get();
            BornholmTopDataShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, topResult)
                    .pattern("AAA")
                    .pattern("BCA")
                    .pattern("AAA")
                    .define('A', clockTypes.getCraftingIngredient())
                    .define('B', ColourfulClocksTags.CLOCK_TOP_GLASS)
                    .define('C', Items.QUARTZ)
                    .group("bornholm_top_" + clockTypes.getName())
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of( // TODO: add clock top glass tag?
                            clockTypes.getCraftingIngredient(),
                            Items.QUARTZ)))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(topResult));
        }
    }

    private static void buildBornholmMiddleVariantRecipes(ClockTypes clockType, ItemLike result) {
        BornholmMiddleDataShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .pattern("AAA")
                .pattern("A B")
                .pattern("AAA")
                .define('A', clockType.getCraftingIngredient())
                .define('B', ColourfulClocksTags.CLOCK_DOOR)
                .group("bornholm_middle_" + clockType.getName())
                .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(clockType.getCraftingIngredient()))) // TODO: add clock door tag?
                .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(result) + "_glass");
    }

    private static void buildMantelClockRecipes() {
        for (ClockTypes clockType : ClockTypes.values()) {
            ItemLike result = ColourfulClocksItemsImpl.MANTEL_CLOCK_VARIANTS.get(clockType).get();
            MantelClockShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                    .pattern(" A ")
                    .pattern("ACA")
                    .pattern("ABA")
                    .define('A', clockType.getCraftingIngredient())
                    .define('B', ColourfulClocksTags.CLOCK_TOP_GLASS)
                    .define('C', Items.QUARTZ)
                    .group("mantel_clock_" + clockType.getName())
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                            clockType.getCraftingIngredient(),
                            Items.QUARTZ)))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(result));
        }
    }

    private static void buildTallMantelClockRecipes() {
        for (ClockTypes clockType : ClockTypes.values()) {
            ItemLike result = ColourfulClocksItemsImpl.TALL_MANTEL_CLOCK_VARIANTS.get(clockType).get();
            MantelClockShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                    .pattern("ACA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', clockType.getCraftingIngredient())
                    .define('B', ColourfulClocksTags.CLOCK_TOP_GLASS)
                    .define('C', Items.QUARTZ)
                    .group("tall_mantel_clock_" + clockType.getName())
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                            clockType.getCraftingIngredient(),
                            Items.QUARTZ)))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(result));
        }
    }

    private static void buildWallClockRecipes() {
        for (ClockTypes clockType : ClockTypes.values()) {
            ItemLike result = ColourfulClocksItemsImpl.WALL_CLOCK_VARIANTS.get(clockType).get();
            ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                    .pattern("AAA")
                    .pattern("ABA")
                    .pattern("AAA")
                    .define('A', clockType.getCraftingIngredient())
                    .define('B', Items.QUARTZ)
                    .group("wall_clock_" + clockType.getName())
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(clockType.getCraftingIngredient(), Items.QUARTZ)))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(result));
        }
    }

    private static void buildAlarmClockRecipes() {
        for (ClockTypes clockType : ClockTypes.values()) {
            ItemLike result = ColourfulClocksItemsImpl.ALARM_CLOCK_VARIANTS.get(clockType).get();
            AlarmClockShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                    .pattern(" A ")
                    .pattern("BCA")
                    .pattern(" A ")
                    .define('A', clockType.getCraftingIngredient())
                    .define('B', ColourfulClocksTags.CLOCK_TOP_GLASS)
                    .define('C', Items.QUARTZ)
                    .group("alarm_clock_" + clockType.getName())
                    .unlockedBy("has_any_ingredient", RecipeProvider.inventoryTrigger(ItemPredicate.Builder.item().of(
                            clockType.getCraftingIngredient(),
                            Items.QUARTZ)))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(result));
        }
    }

    private static void buildWaxedCopperItemRecipes() {
        for (HandbellHandleTypes handleType : HandbellHandleTypes.values()) {
            WaxedCopperHandbellShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_COPPER_HANDBELL.get())
                    .requires(ColourfulClocksItemsImpl.COPPER_HANDBELL.get())
                    .requires(Items.HONEYCOMB)
                    .setData(new HandbellComponent(HandbellTypes.WAXED_COPPER, handleType, Optional.of(0)))
                    .unlockedBy(RecipeProvider.getHasName(ColourfulClocksItemsImpl.COPPER_HANDBELL.get()), RecipeProvider.has(ColourfulClocksItemsImpl.COPPER_HANDBELL.get()))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_COPPER_HANDBELL.get()) + "_" + handleType.getSerializedName());
            WaxedCopperHandbellShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_HANDBELL.get())
                    .requires(ColourfulClocksItemsImpl.EXPOSED_COPPER_HANDBELL.get())
                    .requires(Items.HONEYCOMB)
                    .setData(new HandbellComponent(HandbellTypes.WAXED_EXPOSED_COPPER, handleType, Optional.of(0)))
                    .unlockedBy(RecipeProvider.getHasName(ColourfulClocksItemsImpl.EXPOSED_COPPER_HANDBELL.get()), RecipeProvider.has(ColourfulClocksItemsImpl.EXPOSED_COPPER_HANDBELL.get()))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_HANDBELL.get()) + "_" + handleType.getSerializedName());
            WaxedCopperHandbellShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_HANDBELL.get())
                    .requires(ColourfulClocksItemsImpl.WEATHERED_COPPER_HANDBELL.get())
                    .requires(Items.HONEYCOMB)
                    .setData(new HandbellComponent(HandbellTypes.WAXED_WEATHERED_COPPER, handleType, Optional.of(0)))
                    .unlockedBy(RecipeProvider.getHasName(ColourfulClocksItemsImpl.WEATHERED_COPPER_HANDBELL.get()), RecipeProvider.has(ColourfulClocksItemsImpl.WEATHERED_COPPER_HANDBELL.get()))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_HANDBELL.get()) + "_" + handleType.getSerializedName());
            WaxedCopperHandbellShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_HANDBELL.get())
                    .requires(ColourfulClocksItemsImpl.OXIDIZED_COPPER_HANDBELL.get())
                    .requires(Items.HONEYCOMB)
                    .setData(new HandbellComponent(HandbellTypes.WAXED_OXIDIZED_COPPER, handleType, Optional.of(0)))
                    .unlockedBy(RecipeProvider.getHasName(ColourfulClocksItemsImpl.OXIDIZED_COPPER_HANDBELL.get()), RecipeProvider.has(ColourfulClocksItemsImpl.OXIDIZED_COPPER_HANDBELL.get()))
                    .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_HANDBELL.get()) + "_" + handleType.getSerializedName());
        }

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get())
                .requires(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_copper_pocket_watch", RecipeProvider.has(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get()))
                .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get())
                .requires(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_exposed_copper_pocket_watch", RecipeProvider.has(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get()))
                .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get())
                .requires(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_weathered_copper_pocket_watch", RecipeProvider.has(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get()))
                .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get())
                .requires(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_oxidized_copper_pocket_watch", RecipeProvider.has(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get()))
                .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get()));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get())
                .requires(ColourfulClocksItemsImpl.COPPER_PENDULUM.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_copper_pendulum", RecipeProvider.has(ColourfulClocksItemsImpl.COPPER_PENDULUM.get()))
                .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_COPPER_PENDULUM.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get())
                .requires(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_exposed_copper_pendulum", RecipeProvider.has(ColourfulClocksItemsImpl.EXPOSED_COPPER_PENDULUM.get()))
                .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_PENDULUM.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get())
                .requires(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_weathered_copper_pendulum", RecipeProvider.has(ColourfulClocksItemsImpl.WEATHERED_COPPER_PENDULUM.get()))
                .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_PENDULUM.get()));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get())
                .requires(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_oxidized_copper_pendulum", RecipeProvider.has(ColourfulClocksItemsImpl.OXIDIZED_COPPER_PENDULUM.get()))
                .save(RECIPE_OUTPUT, RecipeProvider.getSimpleRecipeName(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_PENDULUM.get()));
    }
}
