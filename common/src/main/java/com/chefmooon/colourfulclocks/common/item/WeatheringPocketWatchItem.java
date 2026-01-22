package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.PocketWatchComponent;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class WeatheringPocketWatchItem extends PocketWatchItem {
    public WeatheringPocketWatchItem(PocketWatchTypes type, Properties properties) {
        super(type, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getPocketWatchData())) {
            PocketWatchComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getPocketWatchData(), PocketWatchComponent.getDefaultValue());
            if (component.getWeathering().isPresent()) {
                int weathering = component.getWeathering().get();
                if (weathering > 0) {
                    tooltipComponents.add(TextUtil.getTranslatable("tooltip.weathering").append(": ").append(TextUtil.getWeatheringPercentage(weathering)));
                }
            }
        }
    }
}
