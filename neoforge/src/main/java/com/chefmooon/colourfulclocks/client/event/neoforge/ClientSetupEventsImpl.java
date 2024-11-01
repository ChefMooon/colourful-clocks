package com.chefmooon.colourfulclocks.client.event.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.client.renderer.neoforge.BornholmMiddleBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.client.renderer.neoforge.BornholmTopBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.util.neoforge.ColourfulClocksItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = ColourfulClocks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEventsImpl {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS.get(), BornholmMiddleBlockEntityRendererImpl::new);
        event.registerBlockEntityRenderer(ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS.get(), BornholmTopBlockEntityRendererImpl::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ColourfulClocksItemProperties.addCustomItemProperties();
        });
    }
}
