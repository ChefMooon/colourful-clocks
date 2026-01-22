package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.data.AlarmClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class AlarmClockBlockItem extends BlockItem {
    public AlarmClockBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getAlarmClockData())) {
            AlarmClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getAlarmClockData(), AlarmClockComponent.getDefaultValue());
            if (component.leftBell().isPresent()) {
                Item leftBellItem = BuiltInRegistries.ITEM.get(TextUtil.res(component.leftBell().get().getType().getSerializedName() + "_handbell"));
                if (leftBellItem != null) {
                    tooltipComponents.add(Component.translatable(leftBellItem.getDescriptionId()));
                }
            }
            if (component.rightBell().isPresent()) {
                Item rightBellItem = BuiltInRegistries.ITEM.get(TextUtil.res(component.rightBell().get().getType().getSerializedName() + "_handbell"));
                if (rightBellItem != null) {
                    tooltipComponents.add(Component.translatable(rightBellItem.getDescriptionId()));
                }
            }
            if (component.glassType().isPresent()) {
                BornholmTopGlassTypes glassType = component.glassType().get();
                if (glassType != BornholmTopGlassTypes.GLASS) {
                    tooltipComponents.add(Component.translatable(glassType.getBlock().getDescriptionId()));
                }
            }
            if (component.pocketWatch().isPresent()) {
                Item pocketWatchItem = BuiltInRegistries.ITEM.get(TextUtil.res(component.pocketWatch().get().type().getSerializedName() + "_pocket_watch"));
                if (pocketWatchItem != PocketWatchTypes.EMPTY.getItem()) {
                    tooltipComponents.add(Component.translatable(pocketWatchItem.getDescriptionId()));
                }
            }
            if (component.ticking().isPresent() && component.ticking().get()) {
                tooltipComponents.add(Component.translatable(ColourfulClocks.MOD_ID + ".tooltip.ticking"));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
