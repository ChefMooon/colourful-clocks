package com.chefmooon.colourfulclocks.data.fabric;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

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
                .addCriterion(getHasName("clock"), InventoryChangeTrigger.TriggerInstance.hasItems(Items.CLOCK))
                .build(getAdvancementName("root"));
        consumer.accept(root);
    }

    private static String getHasName(String string) {
        return "has_" + string;
    }

    private static ResourceLocation getAdvancementName(String string) {
        return TextUtil.res("main/" + string);
    }
}
