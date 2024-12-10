package com.chefmooon.colourfulclocks.common.util;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.core.PendulumTypes;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BornholmTypeUtil {

    public static BornholmDoorTypes getTypeFromItem(Item item) {
        for (BornholmDoorTypes bornholmDoorTypes : BornholmDoorTypes.values()) {
            if (bornholmDoorTypes.getItem() == item) {
                return bornholmDoorTypes;
            }
        }
        return BornholmDoorTypes.BASE;
    }

    public static BornholmTopGlassTypes getBornholmTopGlassTypeFromItem(Item item) {
        for (BornholmTopGlassTypes bornholmTopGlassTypes : BornholmTopGlassTypes.values()) {
            if (bornholmTopGlassTypes.getItem() == item) {
                return bornholmTopGlassTypes;
            }
        }
        return BornholmTopGlassTypes.GLASS;
    }

    public static PendulumTypes getPendulumTypeFromItem(Item item) {
        for (PendulumTypes pendulumTypes : PendulumTypes.values()) {
            if (pendulumTypes.getItem() == item) {
                return pendulumTypes;
            }
        }
        return PendulumTypes.IRON;
    }

    // todo - does not work, fix or remove
    public static PocketWatchTypes getPocketWatchTypeFromItem(Item item) {
        for (PocketWatchTypes pocketWatchTypes : PocketWatchTypes.values()) {
            if (pocketWatchTypes.getItem() == item) {
                return pocketWatchTypes;
            }
        }
        return PocketWatchTypes.EMERALD;
    }
}
