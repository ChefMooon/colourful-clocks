package com.chefmooon.colourfulclocks.client.fabric;

import com.chefmooon.colourfulclocks.client.event.fabric.ClientSetupEventsImpl;
import com.chefmooon.colourfulclocks.common.util.fabric.ColourfulClocksItemPropertiesImpl;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;

public class ColourfulClocksClientImpl implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingPlugin.register(ctx -> ClientSetupEventsImpl.onRegisterModels(ctx::addModels));
        ClientSetupEventsImpl.onRegisterRenderers();
        ColourfulClocksItemPropertiesImpl.addCustomItemProperties();
    }
}
