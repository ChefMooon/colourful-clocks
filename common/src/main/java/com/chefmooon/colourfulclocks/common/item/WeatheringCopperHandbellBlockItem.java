package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class WeatheringCopperHandbellBlockItem extends HandbellBlockItem {
    public WeatheringCopperHandbellBlockItem(Block block, Properties properties, HandbellTypes type) {
        super(block, properties, type);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (stack.has(ColourfulClocksDataComponentTypes.getHandbellData())) {
            HandbellComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getHandbellData(), HandbellComponent.getDefaultValue());
            if (component.getWeathering().isPresent()) {
                int weathering = component.getWeathering().get();
                if (weathering > 0) {
                    tooltipComponents.add(TextUtil.getTranslatable("tooltip.weathering").append(": ").append(TextUtil.getWeatheringPercentage(weathering)));
                }
            }
        }
    }
}
