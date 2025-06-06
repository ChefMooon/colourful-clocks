package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.common.block.MantelClockBlock;
import com.chefmooon.colourfulclocks.common.data.GlassDialComponent;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum PocketWatchDataProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;
    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getServerData().contains("pocket_watch_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("pocket_watch_type")));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof MantelClockBlock) {
            GlassDialComponent glassDialData = blockAccessor.getBlockEntity().collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getGlassDialData(), GlassDialComponent.getDefaultValue());
            if (glassDialData != null && glassDialData.getPocketWatchType() != PocketWatchTypes.EMPTY) compoundTag.putString("pocket_watch_type", ColourfulClocksTypeUtil.getPocketWatchItemFromType(glassDialData.getPocketWatchType()).getDescriptionId());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("pocket_watch_type");
    }
}
