package com.chefmooon.colourfulclocks.common.crafting;

import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksRecipeSerializers;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
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

public class BornholmMiddleShapedRecipe implements CraftingRecipe {
    final ShapedRecipePattern pattern;
    final ItemStack result;
    final String group;
    final CraftingBookCategory category;
    final boolean showNotification;

    public BornholmMiddleShapedRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result, boolean showNotification) {
        this.group = group;
        this.category = category;
        this.pattern = pattern;
        this.result = result;
        this.showNotification = showNotification;
    }

    public BornholmMiddleShapedRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack result) {
        this(group, category, pattern, result, true);
    }

    public RecipeSerializer<?> getSerializer() {
        return Objects.requireNonNull(BuiltInRegistries.RECIPE_SERIALIZER.get(ColourfulClocksRecipeSerializers.BORNHOLM_MIDDLE));
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
        return this.pattern.ingredients();
    }

    public boolean showNotification() {
        return this.showNotification;
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.pattern.width() && height >= this.pattern.height();
    }

    public boolean matches(CraftingInput input, Level level) {
        boolean hasGlass = false; // Should check if one of the ingredients is a valid door item
        for (int i = 0; i < input.ingredientCount(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.is(ColourfulClocksTags.CLOCK_DOOR)) {
                hasGlass = true;
                break;
            }
        }
        return this.pattern.matches(input) && hasGlass;
    }

    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        ItemStack result = this.getResultItem(registries).copy();
        for (int i = 0; i < input.ingredientCount(); i++) {
            ItemStack stack = input.getItem(i);
            if (stack.is(ColourfulClocksTags.CLOCK_DOOR)) {
                for (BornholmDoorTypes doorType : BornholmDoorTypes.values()) {
                    if (doorType.getItem().equals(stack.getItem())) {
                        result.set(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), new BornholmMiddleDoorComponent(doorType, Optional.empty()));
                    }
                }
            }
        }
        return result;
    }

    public int getWidth() {
        return this.pattern.width();
    }

    public int getHeight() {
        return this.pattern.height();
    }

    public boolean isIncomplete() {
        NonNullList<Ingredient> nonNullList = this.getIngredients();
        return nonNullList.isEmpty() || nonNullList.stream().filter((ingredient) -> !ingredient.isEmpty()).anyMatch((ingredient) -> ingredient.getItems().length == 0);
    }

    public static class Serializer implements RecipeSerializer<BornholmMiddleShapedRecipe> {
        public static final MapCodec<BornholmMiddleShapedRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Codec.STRING.optionalFieldOf("group", "").forGetter((shapedRecipe) -> shapedRecipe.group),
                CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter((shapedRecipe) -> shapedRecipe.category),
                ShapedRecipePattern.MAP_CODEC.forGetter((shapedRecipe) -> shapedRecipe.pattern),
                ItemStack.STRICT_CODEC.fieldOf("result").forGetter((shapedRecipe) -> shapedRecipe.result),
                Codec.BOOL.optionalFieldOf("show_notification", true).forGetter((shapedRecipe) -> shapedRecipe.showNotification)
        ).apply(instance, BornholmMiddleShapedRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, BornholmMiddleShapedRecipe> STREAM_CODEC = StreamCodec.of(BornholmMiddleShapedRecipe.Serializer::toNetwork, BornholmMiddleShapedRecipe.Serializer::fromNetwork);

        public MapCodec<BornholmMiddleShapedRecipe> codec() {
            return CODEC;
        }

        public StreamCodec<RegistryFriendlyByteBuf, BornholmMiddleShapedRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static BornholmMiddleShapedRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            String string = buffer.readUtf();
            CraftingBookCategory craftingBookCategory = (CraftingBookCategory)buffer.readEnum(CraftingBookCategory.class);
            ShapedRecipePattern shapedRecipePattern = (ShapedRecipePattern)ShapedRecipePattern.STREAM_CODEC.decode(buffer);
            ItemStack itemStack = (ItemStack)ItemStack.STREAM_CODEC.decode(buffer);
            boolean bl = buffer.readBoolean();
            return new BornholmMiddleShapedRecipe(string, craftingBookCategory, shapedRecipePattern, itemStack, bl);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, BornholmMiddleShapedRecipe recipe) {
            buffer.writeUtf(recipe.group);
            buffer.writeEnum(recipe.category);
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.result);
            buffer.writeBoolean(recipe.showNotification);
        }
    }
}
