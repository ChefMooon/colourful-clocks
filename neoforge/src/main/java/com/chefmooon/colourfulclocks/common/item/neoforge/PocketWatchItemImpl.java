package com.chefmooon.colourfulclocks.common.item.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.item.PocketWatchItem;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksDataComponentTypesImpl;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PocketWatchItemImpl extends PocketWatchItem {
    public PocketWatchItemImpl(PocketWatchTypes type,Properties properties) {
        super(type, properties);
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
//
//        ItemStack item = player.getItemInHand(usedHand);
//        Boolean open = item.getOrDefault(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_OPEN, true);
//
//        if (player.isShiftKeyDown()) {
//            if (open) {
//                item.set(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_OPEN, false);
//            } else {
//                item.set(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_OPEN, true);
//            }
//        }
//        ClampedItemPropertyFunction itemPropertyFunction = (ClampedItemPropertyFunction) ItemProperties.getProperty(item, ResourceLocation.withDefaultNamespace("time"));
//
//
//
//        float test = 0;
//        if (level.isClientSide()) {
//            test = itemPropertyFunction.unclampedCall(item, (ClientLevel) level, player, 0);
//            ColourfulClocks.LOGGER.info("Status: " + item.getOrDefault(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_OPEN, true) + " | Time: " + test);
//        }
//
//        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
//    }
}
