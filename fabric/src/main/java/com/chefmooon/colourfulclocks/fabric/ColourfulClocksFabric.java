package com.chefmooon.colourfulclocks.fabric;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.registry.fabric.*;
import net.fabricmc.api.ModInitializer;

public class ColourfulClocksFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ColourfulClocks.init();

        ColourfulClocksBlocksImpl.register();
        ColourfulClocksItemsImpl.register();
        ColourfulClocksBlockEntitiesImpl.register();
        ColourfulClocksCreativeTabsImpl.register();
        ColourfulClocksSoundsImpl.register();

    }
}
