package com.chefmooon.colourfulclocks.client.event.fabric;

import com.chefmooon.colourfulclocks.client.model.ColourfulClocksModels;
import com.chefmooon.colourfulclocks.client.renderer.fabric.BornholmMiddleBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.client.renderer.fabric.BornholmTopBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.client.renderer.fabric.MantelClockBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.client.renderer.fabric.TallMantelClockBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings(("deprecation"))
public class ClientSetupEventsImpl {

    public static void onRegisterRenderers() {
        BlockEntityRenderers.register(ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS, BornholmMiddleBlockEntityRendererImpl::new);
        BlockEntityRenderers.register(ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS, BornholmTopBlockEntityRendererImpl::new);
        BlockEntityRenderers.register(ColourfulClocksBlockEntitiesImpl.MANTEL_CLOCK_VARIANTS, MantelClockBlockEntityRendererImpl::new);
        BlockEntityRenderers.register(ColourfulClocksBlockEntitiesImpl.TALL_MANTEL_CLOCK_VARIANTS, TallMantelClockBlockEntityRendererImpl::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
        );
    }

    public static void onRegisterModels(Consumer<ResourceLocation> consumer) {
        ColourfulClocksItemsImpl.POCKET_WATCH_VARIANTS.forEach(((pocketWatchTypes, itemSupplier) -> {
            ResourceLocation minuteHandLocation = TextUtil.res(ColourfulClocksModels.MINUTE_HAND_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath()));
            consumer.accept(minuteHandLocation);
            ResourceLocation hourHandLocation = TextUtil.res(ColourfulClocksModels.HOUR_HAND_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath()));
            consumer.accept(hourHandLocation);

            ResourceLocation minuteHandSmallLocation = TextUtil.res(ColourfulClocksModels.MINUTE_HAND_SMALL_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath()));
            consumer.accept(minuteHandSmallLocation);
            ResourceLocation hourHandSmallLocation = TextUtil.res(ColourfulClocksModels.HOUR_HAND_SMALL_PATH.formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath()));
            consumer.accept(hourHandSmallLocation);
        }));
        for (BornholmTopGlassTypes type : BornholmTopGlassTypes.values()) {
            ResourceLocation location = TextUtil.res(ColourfulClocksModels.BORNHOLM_DIAL_PATH.formatted(type.getName()));
            consumer.accept(location);
        }
        for (BornholmTopGlassTypes type : BornholmTopGlassTypes.values()) {
            ResourceLocation location = TextUtil.res(ColourfulClocksModels.DIAL_SMALL_PATH.formatted(type.getName()));
            consumer.accept(location);
        }
        for (BornholmDoorTypes doorTypes : BornholmDoorTypes.values()) {
            if (doorTypes == BornholmDoorTypes.BASE) continue; // Skip empty door type
            ResourceLocation location = TextUtil.res(ColourfulClocksModels.BORNHOLM_DOOR_PATH.formatted(doorTypes.getName()));
            consumer.accept(location);
        }
    }
}
