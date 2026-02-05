package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.HandbellHandleTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class HandbellBlockItem extends BlockItem {
    public HandbellTypes type;
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
            }

            super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        level.playSound(player, player, type.getRingSound().get(), player.getSoundSource(), 1.0F, type.getPitch());
        player.getCooldowns().addCooldown(this, 20); // TODO: review handbell ring cooldown. Seems responsible but maybe not fun.
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }

    public static void handbellHitSound(Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        if (!livingEntity.getItemInHand(InteractionHand.MAIN_HAND).is(ColourfulClocksTags.ITEM_HANDBELL)) return;

        if (livingEntity instanceof Player player) {
            player.getCommandSenderWorld().playSound(null, player.getX(), player.getY(), player.getZ(), ColourfulClocksSounds.ITEM_IRON_HANDBELL_HIT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        } else {
            livingEntity.getCommandSenderWorld().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), ColourfulClocksSounds.ITEM_IRON_HANDBELL_HIT.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}
