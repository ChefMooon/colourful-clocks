package com.chefmooon.colourfulclocks.client.event.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.client.renderer.neoforge.BornholmMiddleBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.client.renderer.neoforge.BornholmTopBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.client.renderer.neoforge.MantelClockBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.chefmooon.colourfulclocks.common.util.neoforge.ColourfulClocksItemPropertiesImpl;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.function.Consumer;

@EventBusSubscriber(modid = ColourfulClocks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetupEventsImpl {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS.get(), BornholmMiddleBlockEntityRendererImpl::new);
        event.registerBlockEntityRenderer(ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS.get(), BornholmTopBlockEntityRendererImpl::new);
        event.registerBlockEntityRenderer(ColourfulClocksBlockEntitiesImpl.MANTEL_CLOCK_VARIANTS.get(), MantelClockBlockEntityRendererImpl::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ColourfulClocksItemPropertiesImpl.addCustomItemProperties();
        });
    }

    @SubscribeEvent
    public static void modelLoading(ModelEvent.RegisterAdditional event) {
        onRegisterModels(event::register);
    }

    public static void onRegisterModels(Consumer<ModelResourceLocation> consumer) {
        ColourfulClocksItemsImpl.POCKET_WATCH_VARIANTS.forEach(((pocketWatchTypes, itemSupplier) -> {
            ModelResourceLocation minuteHandLocation = new ModelResourceLocation(TextUtil.res("item/%s_minute_hand".formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(minuteHandLocation);
            ModelResourceLocation hourHandLocation = new ModelResourceLocation(TextUtil.res("item/%s_hour_hand".formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(hourHandLocation);
            ModelResourceLocation minuteHandSmallLocation = new ModelResourceLocation(TextUtil.res("item/%s_minute_hand_small".formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(minuteHandSmallLocation);
            ModelResourceLocation hourHandSmallLocation = new ModelResourceLocation(TextUtil.res("item/%s_hour_hand_small".formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(hourHandSmallLocation);
        }));
    }
}
