package com.chefmooon.colourfulclocks;

import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColourfulClocks {
    public static final String MOD_ID = "colourfulclocks";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    public static void init() {
        ColourfulClocksSounds.init();
    }
}
