package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Objects;

public class BornholmMiddleBlockItem extends BlockItem {
    public BornholmMiddleBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.get(DataComponents.BLOCK_STATE) != null) {
            String type = Objects.requireNonNull(stack.get(DataComponents.BLOCK_STATE).get(BornholmMiddleBlock.DOOR_TYPE)).getTooltip();
            if (!type.isEmpty()) {
                tooltipComponents.add(Component.literal(type));  // todo - this should be translatable
            }
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
