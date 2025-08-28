package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.data.ClockComponent;
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

public class ClockDataBlockItem extends BlockItem {
    public ClockDataBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getClockData())) {
            ClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), ClockComponent.getNoPendulumValue());
            if (component.getGlassType().isPresent()) {
                BornholmTopGlassTypes glassType = component.getGlassType().get();
                if (glassType != BornholmTopGlassTypes.GLASS) {
                    tooltipComponents.add(Component.translatable(glassType.getBlock().getDescriptionId()));
                }
            }
            if (component.getPocketWatchType().isPresent()) {
                Item pocketWatchItem = ColourfulClocksTypeUtil.getPocketWatchItemFromType(component.getPocketWatchType().get());
                if (pocketWatchItem != PocketWatchTypes.EMPTY.getItem()) {
                    tooltipComponents.add(Component.translatable(pocketWatchItem.getDescriptionId()));
                }
            }
            if (component.getPendulumType().isPresent()) {
                Item pendulumItem = ColourfulClocksTypeUtil.getPendulumItemFromType(component.getPendulumType().get());
                if (pendulumItem != PocketWatchTypes.EMPTY.getItem()) {
                    tooltipComponents.add(Component.translatable(pendulumItem.getDescriptionId()));
                }
            }
            if (component.getTicking().isPresent() && component.getTicking().get()) {
                tooltipComponents.add(Component.translatable(ColourfulClocks.MOD_ID + ".tooltip.ticking"));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
