package com.chefmooon.colourfulclocks.client.event.fabric;

import com.chefmooon.colourfulclocks.client.renderer.fabric.BornholmMiddleBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.client.renderer.fabric.BornholmTopBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.client.renderer.fabric.MantelClockBlockEntityRendererImpl;
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

public class ClientSetupEventsImpl {

    public static void onRegisterRenderers() {
        BlockEntityRenderers.register(ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS, BornholmMiddleBlockEntityRendererImpl::new);
        BlockEntityRenderers.register(ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS, BornholmTopBlockEntityRendererImpl::new);
        BlockEntityRenderers.register(ColourfulClocksBlockEntitiesImpl.MANTEL_CLOCK_VARIANTS, MantelClockBlockEntityRendererImpl::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(),
                ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(),
                ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(),
                ColourfulClocksBlocksImpl.MANTEL_CLOCK_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
        );
    }

    public static void onRegisterModels(Consumer<ResourceLocation> consumer) {
        ColourfulClocksItemsImpl.POCKET_WATCH_VARIANTS.forEach(((pocketWatchTypes, itemSupplier) -> {
            ResourceLocation minuteHandLocation = TextUtil.res("item/%s_minute_hand".formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath()));
            consumer.accept(minuteHandLocation);
            ResourceLocation hourHandLocation = TextUtil.res("item/%s_hour_hand".formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath()));
            consumer.accept(hourHandLocation);

            ResourceLocation minuteHandSmallLocation = TextUtil.res("item/%s_minute_hand_small".formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath()));
            consumer.accept(minuteHandSmallLocation);
            ResourceLocation hourHandSmallLocation = TextUtil.res("item/%s_hour_hand_small".formatted(BuiltInRegistries.ITEM.getKey(itemSupplier.get()).getPath()));
            consumer.accept(hourHandSmallLocation);
        }));
    }
}
