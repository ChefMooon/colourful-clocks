package com.chefmooon.colourfulclocks.data.builder.fabric;

import com.chefmooon.colourfulclocks.common.crafting.MantelClockShapedRecipe;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MantelClockShapedRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final List<String> rows = Lists.newArrayList();
    private final Map<Character, Ingredient> key = Maps.newLinkedHashMap();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap();
    @Nullable
    private String group;
    private boolean showNotification = true;

    public MantelClockShapedRecipeBuilder(RecipeCategory category, ItemLike result, int count) {
        this.category = category;
        this.result = result.asItem();
        this.count = count;
    }

    public static MantelClockShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result) {
        return shaped(category, result, 1);
    }

    public static MantelClockShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result, int count) {
        return new MantelClockShapedRecipeBuilder(category, result, count);
    }

    public MantelClockShapedRecipeBuilder define(Character symbol, TagKey<Item> tag) {
        return this.define(symbol, Ingredient.of(tag));
    }

    public MantelClockShapedRecipeBuilder define(Character symbol, ItemLike item) {
        return this.define(symbol, Ingredient.of(new ItemLike[]{item}));
    }

    public MantelClockShapedRecipeBuilder define(Character symbol, Ingredient ingredient) {
        if (this.key.containsKey(symbol)) {
            throw new IllegalArgumentException("Symbol '" + symbol + "' is already defined!");
        } else if (symbol == ' ') {
            throw new IllegalArgumentException("Symbol ' ' (whitespace) is reserved and cannot be defined");
        } else {
            this.key.put(symbol, ingredient);
            return this;
        }
    }

    public MantelClockShapedRecipeBuilder pattern(String pattern) {
        if (!this.rows.isEmpty() && pattern.length() != ((String)this.rows.get(0)).length()) {
            throw new IllegalArgumentException("Pattern must be the same width on every line!");
        } else {
            this.rows.add(pattern);
            return this;
        }
    }

    public MantelClockShapedRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    public MantelClockShapedRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    public MantelClockShapedRecipeBuilder showNotification(boolean showNotification) {
        this.showNotification = showNotification;
        return this;
    }

    public Item getResult() {
        return this.result;
    }

    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        ShapedRecipePattern shapedRecipePattern = this.ensureValid(id);
        Advancement.Builder builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id)).rewards(AdvancementRewards.Builder.recipe(id)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(builder);
        this.criteria.forEach(builder::addCriterion);
        MantelClockShapedRecipe shapedRecipe = new MantelClockShapedRecipe((String)Objects.requireNonNullElse(this.group, ""), RecipeBuilder.determineBookCategory(this.category), shapedRecipePattern, new ItemStack(this.result, this.count), this.showNotification);
        recipeOutput.accept(id, shapedRecipe, builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private ShapedRecipePattern ensureValid(ResourceLocation loaction) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + String.valueOf(loaction));
        } else {
            return ShapedRecipePattern.of(this.key, this.rows);
        }
    }
}
