package com.chefmooon.colourfulclocks.common.util;

import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ColourfulClocksTypeUtil {

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

    @ExpectPlatform
    public static Item getPocketWatchItemFromType(PocketWatchTypes pocketWatchTypes) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static PocketWatchTypes getPocketWatchTypeFromItem(Item item) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Item getPendulumItemFromType(PendulumTypes pendulumType) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static PendulumTypes getPendulumTypeFromItem(Item item) {
        throw new AssertionError();
    }
}
