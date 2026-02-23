package com.chefmooon.colourfulclocks.common.crafting;

import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.HandbellHandleTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksRecipeSerializers;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.Objects;
import java.util.Optional;

public class HandbellHandleShapelessRecipe implements CraftingRecipe {
    final String group;
    final CraftingBookCategory category;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;

    public HandbellHandleShapelessRecipe(String group, CraftingBookCategory category, ItemStack result, NonNullList<Ingredient> ingredients) {
        this.group = group;
        this.category = category;
        this.result = result;
        this.ingredients = ingredients;
    }

    public RecipeSerializer<?> getSerializer() {
        return Objects.requireNonNull(BuiltInRegistries.RECIPE_SERIALIZER.get(ColourfulClocksRecipeSerializers.HANDBELL_HANDLE));
    }

    public String getGroup() {
        return this.group;
    }

    public CraftingBookCategory category() {
        return this.category;
    }

    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean matches(CraftingInput input, Level level) {
        if (input.ingredientCount() != this.ingredients.size()) {
            return false;
        } else {
            return input.stackedContents().canCraft(this, (IntList) null);
        }
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        int handleSlot = 0;
        int handbellSlot = 1;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.has(ColourfulClocksDataComponentTypes.getHandbellData())) {
                handbellSlot = i;
            } else if (stack.is(ColourfulClocksTags.ITEM_HANDBELL_HANDLE)) {
                handleSlot = i;
            }
        }
        ItemStack handleStack = input.getItem(handleSlot);
        HandbellHandleTypes resultHandbellType = HandbellHandleTypes.fromItem(handleStack.getItem());
        HandbellComponent inputHandbellComponent = input.getItem(handbellSlot).getOrDefault(ColourfulClocksDataComponentTypes.getHandbellData(), HandbellComponent.getDefaultValue());
        Optional<Integer> weathering = inputHandbellComponent.getWeathering().isPresent() && inputHandbellComponent.getWeathering().get() > 0 ? inputHandbellComponent.getWeathering() : Optional.empty();
        ItemStack resultStack = this.result.copy();
        resultStack.set(ColourfulClocksDataComponentTypes.getHandbellData(), new HandbellComponent(inputHandbellComponent.getType(), resultHandbellType, weathering));
        return resultStack;
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.ingredients.size();
    }

    public static class Serializer implements RecipeSerializer<HandbellHandleShapelessRecipe> {
        private static final MapCodec<HandbellHandleShapelessRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Codec.STRING.optionalFieldOf("group", "").forGetter((shapelessRecipe) -> shapelessRecipe.group), CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter((shapelessRecipe) -> shapelessRecipe.category), ItemStack.STRICT_CODEC.fieldOf("result").forGetter((shapelessRecipe) -> shapelessRecipe.result), Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap((list) -> {
            Ingredient[] ingredients = (Ingredient[])list.stream().filter((ingredient) -> !ingredient.isEmpty()).toArray((i) -> new Ingredient[i]);
            if (ingredients.length == 0) {
                return DataResult.error(() -> "No ingredients for shapeless recipe");
            } else {
                return ingredients.length > 9 ? DataResult.error(() -> "Too many ingredients for shapeless recipe") : DataResult.success(NonNullList.of(Ingredient.EMPTY, ingredients));
            }
        }, DataResult::success).forGetter((slideToSwingShapelessRecipe) -> slideToSwingShapelessRecipe.ingredients)).apply(instance, HandbellHandleShapelessRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, HandbellHandleShapelessRecipe> STREAM_CODEC = StreamCodec.of(HandbellHandleShapelessRecipe.Serializer::toNetwork, HandbellHandleShapelessRecipe.Serializer::fromNetwork);

        public Serializer() {
        }

        public MapCodec<HandbellHandleShapelessRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, HandbellHandleShapelessRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static HandbellHandleShapelessRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String string = buffer.readUtf();
            CraftingBookCategory craftingBookCategory = (CraftingBookCategory)buffer.readEnum(CraftingBookCategory.class);
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonNullList = NonNullList.withSize(i, Ingredient.EMPTY);
            nonNullList.replaceAll((ingredient) -> (Ingredient)Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
            ItemStack itemStack = (ItemStack)ItemStack.STREAM_CODEC.decode(buffer);
            return new HandbellHandleShapelessRecipe(string, craftingBookCategory, itemStack, nonNullList);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, HandbellHandleShapelessRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            buffer.writeVarInt(recipe.ingredients.size());

            for(Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
            }

            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
        }
    }
}
