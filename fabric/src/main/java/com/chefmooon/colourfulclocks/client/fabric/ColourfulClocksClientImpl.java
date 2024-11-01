package com.chefmooon.colourfulclocks.client.fabric;

import com.chefmooon.colourfulclocks.client.event.fabric.ClientSetupEventsImpl;
import net.fabricmc.api.ClientModInitializer;

public class ColourfulClocksClientImpl implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        ClientSetupEventsImpl.onRegisterRenderers();
    }
}
