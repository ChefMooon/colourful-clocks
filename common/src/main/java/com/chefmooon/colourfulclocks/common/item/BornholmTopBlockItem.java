package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
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
            if (glassItem != BornholmTopGlassTypes.GLASS.getItem()) {
                tooltipComponents.add(Component.translatable(glassItem.getDescriptionId()));
            }
            Item pocketWatchItem = ColourfulClocksTypeUtil.getPocketWatchItemFromType(stack.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmTopGlassData(), BornholmTopGlassComponent.getDefaultValue()).getPocketWatchType());
            if (pocketWatchItem != PocketWatchTypes.EMPTY.getItem()) {
                tooltipComponents.add(Component.translatable(pocketWatchItem.getDescriptionId()));
            }
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
