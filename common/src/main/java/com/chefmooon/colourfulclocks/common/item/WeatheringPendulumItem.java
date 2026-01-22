package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.PendulumComponent;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.CopperWeatheringUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class WeatheringPendulumItem extends Item {
    public WeatheringPendulumItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getPendulumData())) {
            PendulumComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getPendulumData(), PendulumComponent.getDefaultValue());
            if (component.getWeathering().isPresent()) {
                int weathering = component.getWeathering().get();
                if (weathering > 0) {
                    tooltipComponents.add(TextUtil.getTranslatable("tooltip.weathering").append(": ").append(TextUtil.getWeatheringPercentage(weathering)));
                }
            }
        }
    }
}
