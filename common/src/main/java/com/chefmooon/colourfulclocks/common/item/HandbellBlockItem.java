package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.HandbellHandleTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class HandbellBlockItem extends BlockItem {
    HandbellTypes type;
    public HandbellBlockItem(Block block, Properties properties, HandbellTypes type) {
        super(block, properties);
        this.type = type;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getHandbellData())) {
            HandbellComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getHandbellData(), HandbellComponent.getDefaultValue());
            if (component != null) {
                HandbellHandleTypes handbellType = component.getMaterialType();
                tooltipComponents.add(handbellType.getBaseTranslation());
                if (component.getWeathering().isPresent()) {
                    int weathering = component.getWeathering().get();
                    if (weathering > 0) {
                        tooltipComponents.add(TextUtil.getTranslatable("tooltip.weathering").append(": ").append(TextUtil.getWeatheringPercentage(weathering)));
                    }
                }
            }

            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        level.playLocalSound(player, type.getRingSound().get(), player.getSoundSource(), 1.0F, type.getPitch());
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }
}
