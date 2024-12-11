package com.chefmooon.colourfulclocks.integration.jade;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.entity.BornholmMiddleBlockEntity;
import com.chefmooon.colourfulclocks.common.block.entity.BornholmTopBlockEntity;
import com.chefmooon.colourfulclocks.integration.jade.provider.BornholmDialProvider;
import com.chefmooon.colourfulclocks.integration.jade.provider.BornholmTrunkProvider;
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
    }
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(BornholmDialProvider.INSTANCE, BornholmTopBlockEntity.class);
        registration.registerBlockDataProvider(BornholmTrunkProvider.INSTANCE, BornholmMiddleBlockEntity.class);
    }
}
