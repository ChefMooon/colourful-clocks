package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.common.advancement.*;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class AdvancementGenerator extends FabricAdvancementProvider {
    protected AdvancementGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        AdvancementHolder root = Advancement.Builder.advancement()
                .display(new DisplayInfo(
                        new ItemStack(ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get()),
                        TextUtil.getTranslatable("advancement.root"),
                        TextUtil.getTranslatable("advancement.root.desc"),
                        Optional.of(ResourceLocation.withDefaultNamespace("textures/block/stripped_spruce_log.png")),
                        AdvancementType.TASK,
                        true, true, false
                        ))
                .addCriterion(RecipeProvider.getHasName(Items.REDSTONE), InventoryChangeTrigger.TriggerInstance.hasItems(Items.REDSTONE))
                .build(getAdvancementName("root"));
        consumer.accept(root);

        // Bornholm clock advancements
        AdvancementHolder bornholmClock = getAdvancement(root, ColourfulClocksItemsImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_OAK).get(), "bornholm_clock", AdvancementType.TASK, true, false, false)
                .addCriterion("has_bornholm_base", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ColourfulClocksTags.BORNHOLM_BASE)))
                .addCriterion("has_bornholm_trunk", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ColourfulClocksTags.BORNHOLM_TRUNK)))
                .addCriterion("has_bornholm_dial", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ColourfulClocksTags.BORNHOLM_DIAL)))
                .addCriterion("has_mantel_clock", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ColourfulClocksTags.MANTEL_CLOCK)))
                .requirements(AdvancementRequirements.Strategy.OR)
                .build(getAdvancementName("bornholm_clock"));
        consumer.accept(bornholmClock);

        AdvancementHolder bornholmBase = getAdvancement(bornholmClock, ColourfulClocksItemsImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get(), "bornholm_base", AdvancementType.TASK, true, false, false)
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.SPRUCE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.SPRUCE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.BIRCH).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.BIRCH).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.JUNGLE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.JUNGLE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.ACACIA).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.ACACIA).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.DARK_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.DARK_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.MANGROVE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.MANGROVE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.CHERRY).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.CHERRY).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.BAMBOO).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.BAMBOO).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.CRIMSON).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.CRIMSON).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.WARPED).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.WARPED).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_SPRUCE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_SPRUCE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_BIRCH).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_BIRCH).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_JUNGLE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_JUNGLE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_ACACIA).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_ACACIA).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_DARK_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_DARK_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_MANGROVE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_MANGROVE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_BAMBOO).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_BAMBOO).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_CRIMSON).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_CRIMSON).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_WARPED).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STRIPPED_WARPED).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.STONE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.MOSSY_STONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.MOSSY_STONE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_GRANITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_GRANITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_DIORITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_DIORITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_ANDESITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_ANDESITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_DEEPSLATE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_DEEPSLATE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.DEEPSLATE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.DEEPSLATE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_TUFF).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_TUFF).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.TUFF_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.TUFF_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.MUD_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.MUD_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.CUT_SANDSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.CUT_SANDSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.CUT_RED_SANDSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.CUT_RED_SANDSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.PRISMARINE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.PRISMARINE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.DARK_PRISMARINE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.DARK_PRISMARINE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.NETHER_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.NETHER_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.RED_NETHER_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.RED_NETHER_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_BASE_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE_BRICKS).get()))
                .requirements(AdvancementRequirements.Strategy.OR)
                .build(getAdvancementName("bornholm_base"));
        consumer.accept(bornholmBase);

        AdvancementHolder bornholmTrunk = getAdvancement(bornholmClock, ColourfulClocksItemsImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get(), "bornholm_trunk", AdvancementType.TASK, true, false, false)
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.SPRUCE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.SPRUCE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.BIRCH).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.BIRCH).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.JUNGLE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.JUNGLE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.ACACIA).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.ACACIA).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.DARK_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.DARK_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.MANGROVE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.MANGROVE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.CHERRY).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.CHERRY).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.BAMBOO).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.BAMBOO).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.CRIMSON).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.CRIMSON).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.WARPED).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.WARPED).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_SPRUCE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_SPRUCE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_BIRCH).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_BIRCH).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_JUNGLE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_JUNGLE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_ACACIA).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_ACACIA).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_DARK_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_DARK_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_MANGROVE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_MANGROVE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_BAMBOO).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_BAMBOO).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_CRIMSON).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_CRIMSON).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_WARPED).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STRIPPED_WARPED).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.STONE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.MOSSY_STONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.MOSSY_STONE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_GRANITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_GRANITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_DIORITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_DIORITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_ANDESITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_ANDESITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_DEEPSLATE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_DEEPSLATE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.DEEPSLATE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.DEEPSLATE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_TUFF).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_TUFF).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.TUFF_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.TUFF_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.MUD_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.MUD_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.CUT_SANDSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.CUT_SANDSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.CUT_RED_SANDSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.CUT_RED_SANDSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.PRISMARINE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.PRISMARINE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.DARK_PRISMARINE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.DARK_PRISMARINE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.NETHER_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.NETHER_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.RED_NETHER_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.RED_NETHER_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE_BRICKS).get()))
                .requirements(AdvancementRequirements.Strategy.OR)
                .build(getAdvancementName("bornholm_trunk"));
        consumer.accept(bornholmTrunk);

        AdvancementHolder bornholmDial = getAdvancement(bornholmClock, ColourfulClocksItemsImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get(), "bornholm_dial", AdvancementType.TASK, true, false, false)
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.SPRUCE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.SPRUCE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.BIRCH).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.BIRCH).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.JUNGLE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.JUNGLE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.ACACIA).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.ACACIA).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.DARK_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.DARK_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.MANGROVE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.MANGROVE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.CHERRY).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.CHERRY).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.BAMBOO).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.BAMBOO).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.CRIMSON).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.CRIMSON).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.WARPED).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.WARPED).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_SPRUCE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_SPRUCE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_BIRCH).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_BIRCH).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_JUNGLE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_JUNGLE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_ACACIA).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_ACACIA).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_DARK_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_DARK_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_MANGROVE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_MANGROVE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_BAMBOO).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_BAMBOO).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_CRIMSON).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_CRIMSON).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_WARPED).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STRIPPED_WARPED).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.STONE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.MOSSY_STONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.MOSSY_STONE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_GRANITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_GRANITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_DIORITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_DIORITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_ANDESITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_ANDESITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_DEEPSLATE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_DEEPSLATE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.DEEPSLATE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.DEEPSLATE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_TUFF).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_TUFF).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.TUFF_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.TUFF_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.MUD_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.MUD_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.CUT_SANDSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.CUT_SANDSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.CUT_RED_SANDSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.CUT_RED_SANDSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.PRISMARINE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.PRISMARINE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.DARK_PRISMARINE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.DARK_PRISMARINE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.NETHER_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.NETHER_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.RED_NETHER_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.RED_NETHER_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE_BRICKS).get()))
                .requirements(AdvancementRequirements.Strategy.OR)
                .build(getAdvancementName("bornholm_dial"));
        consumer.accept(bornholmDial);

        AdvancementHolder mantelClock = getAdvancement(root, ColourfulClocksItemsImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_OAK).get(), "mantel_clock", AdvancementType.TASK, true, false, false)
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.SPRUCE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.SPRUCE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.BIRCH).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.BIRCH).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.JUNGLE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.JUNGLE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.ACACIA).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.ACACIA).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.DARK_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.DARK_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.MANGROVE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.MANGROVE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.CHERRY).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.CHERRY).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.BAMBOO).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.BAMBOO).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.CRIMSON).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.CRIMSON).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.WARPED).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.WARPED).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_SPRUCE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_SPRUCE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_BIRCH).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_BIRCH).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_JUNGLE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_JUNGLE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_ACACIA).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_ACACIA).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_DARK_OAK).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_DARK_OAK).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_MANGROVE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_MANGROVE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_CHERRY).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_BAMBOO).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_BAMBOO).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_CRIMSON).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_CRIMSON).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_WARPED).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STRIPPED_WARPED).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.STONE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.MOSSY_STONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.MOSSY_STONE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_GRANITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_GRANITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_DIORITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_DIORITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_ANDESITE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_ANDESITE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_DEEPSLATE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_DEEPSLATE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.DEEPSLATE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.DEEPSLATE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_TUFF).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_TUFF).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.TUFF_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.TUFF_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.MUD_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.MUD_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.CUT_SANDSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.CUT_SANDSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.CUT_RED_SANDSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.CUT_RED_SANDSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.PRISMARINE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.PRISMARINE_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.DARK_PRISMARINE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.DARK_PRISMARINE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.NETHER_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.NETHER_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.RED_NETHER_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.RED_NETHER_BRICKS).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE).get()))
                .addCriterion(RecipeProvider.getHasName(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE_BRICKS).get()), ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.get(ClockTypes.POLISHED_BLACKSTONE_BRICKS).get()))
                .requirements(AdvancementRequirements.Strategy.OR)
                .build(getAdvancementName("mantel_clock"));
        consumer.accept(mantelClock);

        AdvancementHolder bornholmTrunkDoor = getAdvancement(bornholmTrunk, Items.BLUE_STAINED_GLASS_PANE, "bornholm_trunk_glass_change", AdvancementType.TASK, true, false, false)
                .addCriterion(getHasName("bornholm_trunk_glass_change_trigger"), BornholmTrunkGlassChangeTrigger.TriggerInstance.simple())
                .build(getAdvancementName("bornholm_trunk_glass_change"));
        consumer.accept(bornholmTrunkDoor);

        AdvancementHolder bornholmTrunkWindCharge = getAdvancement(bornholmTrunk, Items.WIND_CHARGE, "bornholm_trunk_wind_charge", AdvancementType.TASK, true, true, true)
                .addCriterion(getHasName("bornholm_trunk_wind_charge_trigger"), BornholmTrunkWindChargeTrigger.TriggerInstance.simple())
                .build(getAdvancementName("bornholm_trunk_wind_charge"));
        consumer.accept(bornholmTrunkWindCharge);

        AdvancementHolder bornholmActivated = getAdvancement(bornholmClock, ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get(), "bornholm_activated", AdvancementType.GOAL, true, true, false)
                .addCriterion(getHasName("bornholm_activated_trigger"), BornholmActivatedTrigger.TriggerInstance.simple())
                .build(getAdvancementName("bornholm_activated"));
        consumer.accept(bornholmActivated);

        // Customization advancements
        AdvancementHolder insertPocketWatch = getAdvancement(root, ColourfulClocksItemsImpl.AMETHYST_POCKET_WATCH.get(), "insert_pocket_watch", AdvancementType.TASK, true, false, false)
                .addCriterion(getHasName("insert_pocket_watch_trigger"), InsertPocketWatchTrigger.TriggerInstance.simple())
                .build(getAdvancementName("insert_pocket_watch"));
        consumer.accept(insertPocketWatch);

        AdvancementHolder insertPendulum = getAdvancement(insertPocketWatch, ColourfulClocksItemsImpl.AMETHYST_PENDULUM.get(), "insert_pendulum", AdvancementType.TASK, true, false, false)
                .addCriterion(getHasName("insert_pendulum_trigger"), InsertPendulumTrigger.TriggerInstance.simple())
                .build(getAdvancementName("insert_pendulum"));
        consumer.accept(insertPendulum);

        AdvancementHolder glassChange = getAdvancement(insertPendulum, Items.PINK_STAINED_GLASS_PANE, "glass_change", AdvancementType.TASK, true, false, false)
                .addCriterion(getHasName("glass_change_trigger"), GlassChangeTrigger.TriggerInstance.simple())
                .build(getAdvancementName("glass_change"));
        consumer.accept(glassChange);

        AdvancementHolder enableTicking = getAdvancement(glassChange, Items.REDSTONE, "enable_ticking", AdvancementType.TASK, true, false, false)
                .addCriterion(getHasName("enable_ticking_trigger"), EnableTickingTrigger.TriggerInstance.simple())
                .build(getAdvancementName("enable_ticking"));
        consumer.accept(enableTicking);

        AdvancementHolder disableTicking = getAdvancement(enableTicking, Items.STONE_PICKAXE, "disable_ticking", AdvancementType.TASK, true, false, false)
                .addCriterion(getHasName("disable_ticking_trigger"), DisableTickingTrigger.TriggerInstance.simple())
                .build(getAdvancementName("disable_ticking"));
        consumer.accept(disableTicking);

        // Copper items advancements
        AdvancementHolder copperItems = getAdvancement(root, ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get(), "copper_items", AdvancementType.TASK, true, false, false)
                .addCriterion(getHasName("copper_pendulum"), InventoryChangeTrigger.TriggerInstance.hasItems(ColourfulClocksItemsImpl.COPPER_PENDULUM.get()))
                .addCriterion(getHasName("copper_pocket_watch"), InventoryChangeTrigger.TriggerInstance.hasItems(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get()))
                .requirements(AdvancementRequirements.Strategy.OR)
                .build(getAdvancementName("copper_items"));
        consumer.accept(copperItems);

        AdvancementHolder copperWaxOn = getAdvancement(copperItems, Items.HONEYCOMB, "copper_wax_on", AdvancementType.TASK, true, false, false)
                .addCriterion(getHasName("copper_wax_on_trigger"), CopperWaxOnTrigger.TriggerInstance.simple())
                .build(getAdvancementName("copper_wax_on"));
        consumer.accept(copperWaxOn);

        AdvancementHolder copperWaxOff = getAdvancement(copperWaxOn, Items.STONE_AXE, "copper_wax_off", AdvancementType.TASK, true, false, false)
                .addCriterion(getHasName("copper_wax_off_trigger"), CopperWaxOffTrigger.TriggerInstance.simple())
                .build(getAdvancementName("copper_wax_off"));
        consumer.accept(copperWaxOff);

    }

    private static Advancement.Builder getAdvancement(AdvancementHolder parent, ItemLike icon, String name, AdvancementType type, boolean showToast, boolean announceChat, boolean hidden) {
        return Advancement.Builder.advancement()
                .parent(parent)
                .display(new ItemStack(icon),
                        TextUtil.getTranslatable("advancement." + name),
                        TextUtil.getTranslatable("advancement." + name + ".desc"),
                        null, type, showToast, announceChat, hidden);
    }

    private static String getHasName(String string) {
        return "has_" + string;
    }

    private static ResourceLocation getAdvancementName(String string) {
        return TextUtil.res("main/" + string);
    }
}
