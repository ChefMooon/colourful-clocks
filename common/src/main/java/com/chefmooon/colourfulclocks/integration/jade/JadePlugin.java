package com.chefmooon.colourfulclocks.integration.jade;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.MantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.TallMantelClockBlock;
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

        registration.registerBlockComponent(GlassDataProvider.INSTANCE, MantelClockBlock.class);
        registration.registerBlockComponent(PocketWatchDataProvider.INSTANCE, MantelClockBlock.class);
        registration.registerBlockComponent(TickingDataProvider.INSTANCE, MantelClockBlock.class);

        registration.registerBlockComponent(CommonGlassDataProvider.INSTANCE, TallMantelClockBlock.class);
        registration.registerBlockComponent(CommonPocketWatchDataProvider.INSTANCE, TallMantelClockBlock.class);
        registration.registerBlockComponent(PendulumDataProvider.INSTANCE, TallMantelClockBlock.class);
        registration.registerBlockComponent(CommonTickingDataProvider.INSTANCE, TallMantelClockBlock.class);
    }
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(BornholmDialProvider.INSTANCE, BornholmTopBlockEntity.class);
        registration.registerBlockDataProvider(BornholmTrunkProvider.INSTANCE, BornholmMiddleBlockEntity.class);

        registration.registerBlockDataProvider(GlassDataProvider.INSTANCE, MantelClockBlock.class);
        registration.registerBlockDataProvider(PocketWatchDataProvider.INSTANCE, MantelClockBlock.class);
        registration.registerBlockDataProvider(TickingDataProvider.INSTANCE, MantelClockBlock.class);

        registration.registerBlockDataProvider(CommonGlassDataProvider.INSTANCE, TallMantelClockBlock.class);
        registration.registerBlockDataProvider(CommonPocketWatchDataProvider.INSTANCE, TallMantelClockBlock.class);
        registration.registerBlockDataProvider(PendulumDataProvider.INSTANCE, TallMantelClockBlock.class);
        registration.registerBlockDataProvider(CommonTickingDataProvider.INSTANCE, TallMantelClockBlock.class);
    }
}
