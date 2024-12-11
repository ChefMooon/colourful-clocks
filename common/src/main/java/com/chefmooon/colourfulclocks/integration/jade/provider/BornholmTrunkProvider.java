package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum BornholmTrunkProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getServerData().contains("bornholm_trunk_door_type")) {
            iTooltip.add((Component.literal(blockAccessor.getServerData().getString("bornholm_trunk_door_type"))));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof BornholmMiddleBlock) {
            BornholmDoorTypes doorType = blockAccessor.getBlockState().getValue(BornholmMiddleBlock.DOOR_TYPE);
            if (doorType != BornholmDoorTypes.BASE) compoundTag.putString("bornholm_trunk_door_type", doorType.getTooltip());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("bornholm_trunk_door_type");
    }
}
