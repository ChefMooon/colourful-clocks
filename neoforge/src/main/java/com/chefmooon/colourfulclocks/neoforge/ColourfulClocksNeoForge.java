package com.chefmooon.colourfulclocks.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.registry.neoforge.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(ColourfulClocks.MOD_ID)
public class ColourfulClocksNeoForge {
    public ColourfulClocksNeoForge(IEventBus modEventBus, ModContainer modContainer) {

        ColourfulClocks.init();

        ColourfulClocksBlocksImpl.register(modEventBus);
        ColourfulClocksItemsImpl.register(modEventBus);
        ColourfulClocksBlockEntitiesImpl.register(modEventBus);
        ColourfulClocksCreativeTabsImpl.register(modEventBus);
        ColourfulClocksDataComponentTypesImpl.register(modEventBus);
    }
}
