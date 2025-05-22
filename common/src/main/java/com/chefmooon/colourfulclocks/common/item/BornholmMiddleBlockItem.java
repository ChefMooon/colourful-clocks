package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class BornholmMiddleBlockItem extends BlockItem {
    public BornholmMiddleBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData())) {
            Block glassType = stack.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), BornholmMiddleDoorComponent.getDefaultValue()).getDoorType().getBlock();
            if (glassType != Blocks.AIR) {
                tooltipComponents.add(Component.translatable(glassType.getDescriptionId()));
            }
            Item pendulumItem = ColourfulClocksTypeUtil.getPendulumItemFromType(stack.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), BornholmMiddleDoorComponent.getDefaultValue()).getPendulumType());
            if (pendulumItem != PendulumTypes.EMPTY.getItem()) {
                tooltipComponents.add(Component.translatable(pendulumItem.getDescriptionId()));
            }
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
