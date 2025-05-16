package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class BornholmMiddleBlockItem extends BlockItem {
    public BornholmMiddleBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData())) {
            String type = stack.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), BornholmMiddleDoorComponent.getDefaultValue()).getDoorType().getTooltip();
            if (!type.isEmpty()) {
                tooltipComponents.add(Component.literal(type));  // todo - this should be translatable
            }
            // TODO : figure out how to add pendulum from data on place
//            String pendulum = stack.getOrDefault(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), BornholmMiddleDoorComponent.getDefaultValue()).getPendulumType().getName();
//            if (!pendulum.isEmpty()) {
//                tooltipComponents.add(Component.translatable("Pendulum: " + pendulum));
//            }
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
