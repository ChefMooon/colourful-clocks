package com.chefmooon.colourfulclocks.integration.jade;

import com.chefmooon.colourfulclocks.common.block.*;
import com.chefmooon.colourfulclocks.common.block.entity.BornholmMiddleBlockEntity;
import com.chefmooon.colourfulclocks.common.block.entity.BornholmTopBlockEntity;
import com.chefmooon.colourfulclocks.integration.jade.provider.*;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
@SuppressWarnings("unused")
public class JadePlugin implements IWailaPlugin {
    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(BornholmDialProvider.INSTANCE, BornholmTopBlock.class);
        registration.registerBlockComponent(BornholmTrunkProvider.INSTANCE, BornholmMiddleBlock.class);

        registration.registerBlockComponent(MantelClockDataProvider.INSTANCE, MantelClockBlock.class);
        registration.registerBlockComponent(MantelClockDataProvider.INSTANCE, TallMantelClockBlock.class);

        registration.registerBlockComponent(WallClockDataProvider.INSTANCE, WallClockBlock.class);

        registration.registerBlockComponent(AlarmClockDataProvider.INSTANCE, AlarmClockBlock.class);
    }
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(BornholmDialProvider.INSTANCE, BornholmTopBlockEntity.class);
        registration.registerBlockDataProvider(BornholmTrunkProvider.INSTANCE, BornholmMiddleBlockEntity.class);

        registration.registerBlockDataProvider(MantelClockDataProvider.INSTANCE, MantelClockBlock.class);
        registration.registerBlockDataProvider(MantelClockDataProvider.INSTANCE, TallMantelClockBlock.class);

        registration.registerBlockDataProvider(WallClockDataProvider.INSTANCE, WallClockBlock.class);

        registration.registerBlockDataProvider(AlarmClockDataProvider.INSTANCE, AlarmClockBlock.class);
    }
}
