package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import net.minecraft.world.item.Item;

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
}
