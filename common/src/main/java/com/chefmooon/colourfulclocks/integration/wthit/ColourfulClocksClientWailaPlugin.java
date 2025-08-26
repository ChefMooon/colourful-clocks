package com.chefmooon.colourfulclocks.integration.wthit;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.MantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.TallMantelClockBlock;
import com.chefmooon.colourfulclocks.integration.wthit.provider.BornholmDialProvider;
import com.chefmooon.colourfulclocks.integration.wthit.provider.BornholmTrunkProvider;
import com.chefmooon.colourfulclocks.integration.wthit.provider.ClockDataProvider;
import com.chefmooon.colourfulclocks.integration.wthit.provider.GlassDialDataProvider;
import mcp.mobius.waila.api.IClientRegistrar;
import mcp.mobius.waila.api.IWailaClientPlugin;

public class ColourfulClocksClientWailaPlugin implements IWailaClientPlugin {
    @Override
    public void register(IClientRegistrar registrar) {
        registrar.body(new BornholmTrunkProvider(), BornholmMiddleBlock.class);
        registrar.body(new BornholmDialProvider(), BornholmTopBlock.class);

        registrar.body(new GlassDialDataProvider(), MantelClockBlock.class);
        registrar.body(new ClockDataProvider(), TallMantelClockBlock.class);
    }
}
