package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.ColourfulClocks;
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
            BornholmTopGlassComponent bornholmTopGlassData = stack.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmTopGlassData(), BornholmTopGlassComponent.getDefaultValue());
            Block glassType = bornholmTopGlassData.getGlassType().getBlock();
            if (glassType != BornholmTopGlassTypes.GLASS.getBlock()) {
                tooltipComponents.add(Component.translatable(glassType.getDescriptionId()));
            }
            if (bornholmTopGlassData.getPocketWatch().isPresent()) {
                Item pocketWatchItem = ColourfulClocksTypeUtil.getPocketWatchItemFromType(bornholmTopGlassData.getPocketWatch().get().getType());
                if (pocketWatchItem != PocketWatchTypes.EMPTY.getItem()) {
                    tooltipComponents.add(Component.translatable(pocketWatchItem.getDescriptionId()));
                }
            }
            if (bornholmTopGlassData.getTicking()) {
                tooltipComponents.add(Component.translatable(ColourfulClocks.MOD_ID + ".tooltip.ticking"));
            }
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
