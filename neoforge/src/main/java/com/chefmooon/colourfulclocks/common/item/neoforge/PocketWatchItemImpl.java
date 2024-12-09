package com.chefmooon.colourfulclocks.common.item.neoforge;

import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.item.PocketWatchItem;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksDataComponentTypesImpl;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PocketWatchItemImpl extends PocketWatchItem {
    public PocketWatchItemImpl(PocketWatchTypes type, Properties properties) {
        super(type, properties);
    }
}
