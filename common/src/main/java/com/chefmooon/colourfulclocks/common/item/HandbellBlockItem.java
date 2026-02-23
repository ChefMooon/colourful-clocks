package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.data.HandbellComponent;
import com.chefmooon.colourfulclocks.common.data.types.HandbellHandleTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
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

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        if (context.getPlayer() != null && context.getPlayer().isCrouching()) {
            return super.place(context);
        }
        return InteractionResult.FAIL;
    }

    public HandbellTypes getType() {
        return type;
    }

    public static void handbellHitSound(Entity entity) {
        if (!(entity instanceof LivingEntity livingEntity)) return;
        if (!livingEntity.getItemInHand(InteractionHand.MAIN_HAND).is(ColourfulClocksTags.ITEM_HANDBELL)) return;

        HandbellTypes handbellType = livingEntity.getItemInHand(InteractionHand.MAIN_HAND).get(ColourfulClocksDataComponentTypes.getHandbellData()).getType();
        SoundEvent hitSound = handbellType.getHitSound() != null ? handbellType.getHitSound().get() : ColourfulClocksSounds.ITEM_BASE_HANDBELL_HIT.get();
        if (livingEntity instanceof Player player) {
            player.getCommandSenderWorld().playSound(null, player.getX(), player.getY(), player.getZ(), hitSound, SoundSource.PLAYERS, 1.0F, 1.0F);
        } else {
            livingEntity.getCommandSenderWorld().playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), hitSound, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}
