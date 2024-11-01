package com.chefmooon.colourfulclocks.fabric;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksCreativeTabsImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import net.fabricmc.api.ModInitializer;

public class ColourfulClocksFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ColourfulClocks.init();

        ColourfulClocksBlocksImpl.register();
        ColourfulClocksItemsImpl.register();
        ColourfulClocksBlockEntitiesImpl.register();
        ColourfulClocksCreativeTabsImpl.register();

    }
}
