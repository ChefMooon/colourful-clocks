package com.chefmooon.colourfulclocks.client.event.fabric;

import com.chefmooon.colourfulclocks.client.renderer.fabric.BornholmMiddleBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.client.renderer.fabric.BornholmTopBlockEntityRendererImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlockEntitiesImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksBlocksImpl;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class ClientSetupEventsImpl {

    public static void onRegisterRenderers() {
        BlockEntityRenderers.register(ColourfulClocksBlockEntitiesImpl.BORNHOLM_MIDDLE_VARIANTS, BornholmMiddleBlockEntityRendererImpl::new);
        BlockEntityRenderers.register(ColourfulClocksBlockEntitiesImpl.BORNHOLM_TOP_VARIANTS, BornholmTopBlockEntityRendererImpl::new);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(),
                ColourfulClocksBlocksImpl.BORNHOLM_TOP_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(),
                ColourfulClocksBlocksImpl.BORNHOLM_MIDDLE_VARIANTS.values().stream().map(Supplier::get).toArray(Block[]::new)
        );
    }
}
