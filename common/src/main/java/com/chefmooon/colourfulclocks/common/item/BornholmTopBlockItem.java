package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class BornholmTopBlockItem extends BlockItem {

    public BornholmTopBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getBornholmTopGlassData())) {
            Item glassItem = stack.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmTopGlassData(), BornholmTopGlassComponent.getDefaultValue()).getGlassType().getItem();
            if (glassItem != Items.AIR) {
                tooltipComponents.add(Component.translatable(glassItem.getDescriptionId()));
            }
            // TODO : figure out how to add pocket watch from data on place
//            String dial = stack.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmTopGlassData(), BornholmTopGlassComponent.getDefaultValue()).getPocketWatchType().getBaseTranslation();
//            if (!dial.isEmpty()) {
//                tooltipComponents.add(Component.translatable("Dial: " + dial));
//            }
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
