package com.chefmooon.colourfulclocks.integration.wthit.provider;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.chefmooon.colourfulclocks.integration.wthit.ColourfulClocksCommonWailaPlugin;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.network.chat.Component;

public class BornholmDialProvider implements IBlockComponentProvider {

    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.BORNHOLM_TRUNK_DOOR_TYPE)) {
            if (accessor.getBlock() instanceof BornholmTopBlock) {
                BornholmTopGlassTypes glassType = accessor.getBlockState().getValue(BornholmTopBlock.GLASS_TYPE);
                if (glassType != BornholmTopGlassTypes.GLASS) {
                    addGlassTypeTooltip(tooltip, glassType);
                }
            }
        }
    }

    private static void addGlassTypeTooltip(ITooltip tooltip, BornholmTopGlassTypes glassType) {
//        tooltip.addLine(TextUtil.getTranslatable("tooltip.waila.bornholm_dial_glass_type").append(": ")
//                .append(glassType.getTooltip()));
        tooltip.addLine(Component.literal(glassType.getTooltip()));
    }
}
