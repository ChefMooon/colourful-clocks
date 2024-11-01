package com.chefmooon.colourfulclocks.common.item.fabric;

import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.item.PocketWatchItem;

public class PocketWatchItemImpl extends PocketWatchItem {
    public PocketWatchItemImpl(PocketWatchTypes type, Properties properties) {
        super(type, properties);
    }

    // todo - need to register DataComponentTypes first
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
//
//        ItemStack item = player.getItemInHand(usedHand);
//        Boolean open = item.getOrDefault(TemporalTimepiecesDataComponentTypesImpl.POCKET_WATCH_OPEN, true);
//
//        if (player.isShiftKeyDown()) {
//            if (open) {
//                item.set(TemporalTimepiecesDataComponentTypesImpl.POCKET_WATCH_OPEN, false);
//            } else {
//                item.set(TemporalTimepiecesDataComponentTypesImpl.POCKET_WATCH_OPEN, true);
//            }
//        }
//        ClampedItemPropertyFunction itemPropertyFunction = (ClampedItemPropertyFunction) ItemProperties.getProperty(item, ResourceLocation.withDefaultNamespace("time"));
//
//
//
//        float test = 0;
//        if (level.isClientSide()) {
//            test = itemPropertyFunction.unclampedCall(item, (ClientLevel) level, player, 0);
//            TemporalTimepieces.LOGGER.info("Status: " + item.getOrDefault(TemporalTimepiecesDataComponentTypesImpl.POCKET_WATCH_OPEN, true) + " | Time: " + test);
//        }
//
//        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
//    }
}
