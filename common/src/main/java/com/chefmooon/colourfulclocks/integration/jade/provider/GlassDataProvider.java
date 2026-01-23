package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.common.block.base.BaseDataClockBlock;
import com.chefmooon.colourfulclocks.common.data.MantelClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum GlassDataProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;
    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getServerData().contains("glass_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("glass_type")));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof BaseDataClockBlock) {
            MantelClockComponent component = blockAccessor.getBlockEntity().collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getMantelClockData(), MantelClockComponent.getDefaultValue());
            if (component != null && component.getGlassType().isPresent() && component.getGlassType().get() != BornholmTopGlassTypes.GLASS) compoundTag.putString(("glass_type"), component.getGlassType().get().getBlock().getDescriptionId());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("glass_type");
    }
}