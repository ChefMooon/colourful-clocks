package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.block.entity.BornholmMiddleBlockEntity;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
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
        if (stack.get(BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING)) != null) {
            int weathering = stack.get((DataComponentType<Integer>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.PENDULUM_WEATHERING));
            if (weathering > 0) {
                tooltipComponents.add(TextUtil.getTranslatable("tooltip.weathering").append(": ").append(getWeatheringPercentage(weathering)));
            }

        }
    }

    private static String getWeatheringPercentage(int weathering) {
        int percentage = (int) ((weathering / (double)(BornholmMiddleBlockEntity.WEATHERED_THRESHOLD)) * 100);
        return percentage + "%";
    }
}
