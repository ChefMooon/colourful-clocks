package com.chefmooon.colourfulclocks.common.util.fabric;

import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ColourfulClocksTypeUtilImpl {
    public static Item getPocketWatchItemFromType(PocketWatchTypes pocketWatchTypes) {
        return ColourfulClocksItemsImpl.POCKET_WATCH_VARIANTS.getOrDefault(pocketWatchTypes, () -> Items.AIR).get();
    }

    public static Item getPendulumItemFromType(PendulumTypes pendulumType) {
        return ColourfulClocksItemsImpl.PENDULUM_VARIANTS.getOrDefault(pendulumType, () -> Items.AIR).get();
    }
}