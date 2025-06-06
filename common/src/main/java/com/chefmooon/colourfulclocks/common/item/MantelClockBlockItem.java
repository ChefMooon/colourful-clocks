package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.data.GlassDialComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class MantelClockBlockItem extends BlockItem {
    public MantelClockBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getGlassDialData())) {
            GlassDialComponent glassData = stack.getOrDefault(ColourfulClocksDataComponentTypes.getGlassDialData(), GlassDialComponent.getDefaultValue());
            Block glassType = glassData.getGlassType().getBlock();
            if (glassType != BornholmTopGlassTypes.GLASS.getBlock()) {
                tooltipComponents.add(Component.translatable(glassType.getDescriptionId()));
            }
            Item pocketWatchItem = ColourfulClocksTypeUtil.getPocketWatchItemFromType(glassData.getPocketWatchType());
            if (pocketWatchItem != PocketWatchTypes.EMPTY.getItem()) {
                tooltipComponents.add(Component.translatable(pocketWatchItem.getDescriptionId()));
            }
            if (glassData.getTicking()) {
                tooltipComponents.add(Component.translatable(ColourfulClocks.MOD_ID + ".tooltip.ticking"));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
