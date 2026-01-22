package com.chefmooon.colourfulclocks.integration.wthit;

import com.chefmooon.colourfulclocks.common.block.*;
import com.chefmooon.colourfulclocks.integration.wthit.provider.AlarmClockDataProvider;
import com.chefmooon.colourfulclocks.integration.wthit.provider.BornholmDialProvider;
import com.chefmooon.colourfulclocks.integration.wthit.provider.BornholmTrunkProvider;
import com.chefmooon.colourfulclocks.integration.wthit.provider.ClockDataProvider;
import mcp.mobius.waila.api.IClientRegistrar;
import mcp.mobius.waila.api.IWailaClientPlugin;

public class ColourfulClocksClientWailaPlugin implements IWailaClientPlugin {
    @Override
    public void register(IClientRegistrar registrar) {
        registrar.body(new BornholmTrunkProvider(), BornholmMiddleBlock.class);
        registrar.body(new BornholmDialProvider(), BornholmTopBlock.class);

        registrar.body(new ClockDataProvider(), MantelClockBlock.class);
        registrar.body(new ClockDataProvider(), TallMantelClockBlock.class);

        registrar.body(new ClockDataProvider(), WallClockBlock.class);

        registrar.body(new AlarmClockDataProvider(), AlarmClockBlock.class);
    }
}
