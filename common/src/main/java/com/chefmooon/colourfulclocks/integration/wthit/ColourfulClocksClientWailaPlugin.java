package com.chefmooon.colourfulclocks.integration.wthit;

import com.chefmooon.colourfulclocks.common.block.*;
import com.chefmooon.colourfulclocks.integration.wthit.provider.*;
import mcp.mobius.waila.api.IClientRegistrar;
import mcp.mobius.waila.api.IWailaClientPlugin;

public class ColourfulClocksClientWailaPlugin implements IWailaClientPlugin {
    @Override
    public void register(IClientRegistrar registrar) {
        registrar.body(new BornholmTrunkProvider(), BornholmMiddleBlock.class);
        registrar.body(new BornholmDialProvider(), BornholmTopBlock.class);

        registrar.body(new MantelClockDataProvider(), MantelClockBlock.class);
        registrar.body(new MantelClockDataProvider(), TallMantelClockBlock.class);

        registrar.body(new WallClockDataProvider(), WallClockBlock.class);

        registrar.body(new AlarmClockDataProvider(), AlarmAlarmClockBlock.class);
    }
}
