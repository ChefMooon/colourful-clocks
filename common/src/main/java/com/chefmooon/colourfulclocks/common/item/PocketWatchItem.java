package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PocketWatchItem extends Item {
    public static PocketWatchTypes type;
    public PocketWatchItem(Properties properties) {
        super(properties);
    }

    public PocketWatchItem(PocketWatchTypes type, Properties properties) {
        super(properties);
        this.type = type;
    }

    public PocketWatchTypes getType() {
        return type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(usedHand);

        if (player.isShiftKeyDown()) {
            Boolean open = item.getOrDefault((DataComponentType<Boolean>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_CLOSED), false);
            if (open) {
                item.set((DataComponentType<Boolean>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_CLOSED), false);
            } else {
                item.set((DataComponentType<Boolean>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ColourfulClocksDataComponentTypes.POCKET_WATCH_CLOSED), true);
            }
            return InteractionResultHolder.consume(player.getItemInHand(usedHand));
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }
}
