package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.entity.BornholmTopBlockEntity;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum BornholmDialProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;
    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getServerData().contains("bornholm_dial_glass_type")) {
            iTooltip.add(Component.literal(blockAccessor.getServerData().getString("bornholm_dial_glass_type")));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof BornholmTopBlock) {
            BornholmTopGlassTypes glassType = blockAccessor.getBlockState().getValue(BornholmTopBlock.GLASS_TYPE);
            if (glassType != BornholmTopGlassTypes.GLASS) compoundTag.putString("bornholm_dial_glass_type", glassType.getTooltip());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("bornholm_dial_glass_type");
    }



}
