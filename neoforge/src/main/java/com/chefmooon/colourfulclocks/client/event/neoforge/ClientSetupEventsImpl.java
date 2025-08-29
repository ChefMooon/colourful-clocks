package com.chefmooon.colourfulclocks.client.event.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.client.ColourfulClocksClient;
import com.chefmooon.colourfulclocks.client.model.ColourfulClocksModels;
import com.chefmooon.colourfulclocks.client.renderer.neoforge.*;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.chefmooon.colourfulclocks.common.util.neoforge.ColourfulClocksItemPropertiesImpl;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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
        event.registerBlockEntityRenderer(ColourfulClocksBlockEntitiesImpl.TALL_MANTEL_CLOCK_VARIANTS.get(), TallMantelClockBlockEntityRendererImpl::new);
        event.registerBlockEntityRenderer(ColourfulClocksBlockEntitiesImpl.WALL_CLOCK_VARIANTS.get(), WallClockBlockEntityRendererImpl::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ColourfulClocksClient.init();
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
            ModelResourceLocation minuteHandXLLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.MINUTE_HAND_XL_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(minuteHandXLLocation);
            ModelResourceLocation hourHandXLLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.HOUR_HAND_XL_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(hourHandXLLocation);

            ModelResourceLocation minuteHandLargeLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.MINUTE_HAND_LARGE_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(minuteHandLargeLocation);
            ModelResourceLocation hourHandLargeLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.HOUR_HAND_LARGE_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(hourHandLargeLocation);

            ModelResourceLocation minuteHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.MINUTE_HAND_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(minuteHandLocation);
            ModelResourceLocation hourHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.HOUR_HAND_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(hourHandLocation);

            ModelResourceLocation minuteHandSmallLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.MINUTE_HAND_SMALL_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(minuteHandSmallLocation);
            ModelResourceLocation hourHandSmallLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.HOUR_HAND_SMALL_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath())), "standalone");
            consumer.accept(hourHandSmallLocation);
        }));
        for (BornholmTopGlassTypes type : BornholmTopGlassTypes.values()) {
            ModelResourceLocation location = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.BORNHOLM_DIAL_PATH.formatted(type.getName())), "standalone");
            consumer.accept(location);
        }
        for (BornholmTopGlassTypes type : BornholmTopGlassTypes.values()) {
            ModelResourceLocation location = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.DIAL_SMALL_PATH.formatted(type.getName())), "standalone");
            consumer.accept(location);
        }
        for (BornholmDoorTypes doorTypes : BornholmDoorTypes.values()) {
            if (doorTypes == BornholmDoorTypes.BASE) continue; // Skip empty door type
            ModelResourceLocation location = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.BORNHOLM_DOOR_PATH.formatted(doorTypes.getName())), "standalone");
            consumer.accept(location);
        }
    }
}
