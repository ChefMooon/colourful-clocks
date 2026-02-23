package com.chefmooon.colourfulclocks.data.builder.fabric;

import com.chefmooon.colourfulclocks.common.crafting.HandbellHandleShapelessRecipe;
import com.chefmooon.colourfulclocks.common.crafting.WaxedCopperHandbellShapelessRecipe;
import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class HandbellHandleShapelessRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    private HandbellComponent handbellComponent = HandbellComponent.getDefaultValue();
    @Nullable
    private String group;

    public HandbellHandleShapelessRecipeBuilder(RecipeCategory category, ItemLike result, int count) {
        this.category = category;
        this.result = result.asItem();
        this.count = count;
    }

    public static HandbellHandleShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result) {
        return new HandbellHandleShapelessRecipeBuilder(category, result, 1);
    }

    public static HandbellHandleShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int count) {
        return new HandbellHandleShapelessRecipeBuilder(category, result, count);
    }

    public HandbellHandleShapelessRecipeBuilder requires(TagKey<Item> tag) {
        return this.requires(Ingredient.of(tag));
    }

    public HandbellHandleShapelessRecipeBuilder requires(ItemLike item) {
        return this.requires((ItemLike)item, 1);
    }

    public HandbellHandleShapelessRecipeBuilder requires(ItemLike item, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.requires(Ingredient.of(new ItemLike[]{item}));
        }

        return this;
    }

    public HandbellHandleShapelessRecipeBuilder requires(Ingredient ingredient) {
        return this.requires((Ingredient)ingredient, 1);
    }

    public HandbellHandleShapelessRecipeBuilder requires(Ingredient ingredient, int quantity) {
        for(int i = 0; i < quantity; ++i) {
            this.ingredients.add(ingredient);
        }

        return this;
    }
    public HandbellHandleShapelessRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public HandbellHandleShapelessRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    public HandbellHandleShapelessRecipeBuilder setData(HandbellComponent handbellComponent) {
        this.handbellComponent = handbellComponent;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        this.ensureValid(id);
        Advancement.Builder builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(builder);
        this.criteria.forEach(builder::addCriterion);
        HandbellHandleShapelessRecipe shapelessRecipe = new HandbellHandleShapelessRecipe((String)Objects.requireNonNullElse(this.group, ""), RecipeBuilder.determineBookCategory(this.category), new ItemStack(this.result, this.count), this.ingredients);
        recipeOutput.accept(id, shapelessRecipe, builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceLocation location) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(location));
        }
    }
}
