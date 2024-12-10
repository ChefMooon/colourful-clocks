package com.chefmooon.colourfulclocks.integration.wthit.provider;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.chefmooon.colourfulclocks.integration.wthit.ColourfulClocksCommonWailaPlugin;
import mcp.mobius.waila.api.*;
import net.minecraft.network.chat.Component;

public class BornholmTrunkProvider implements IBlockComponentProvider {

    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        if (config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.BORNHOLM_TRUNK_DOOR_TYPE)) {
            if (accessor.getBlock() instanceof BornholmMiddleBlock) {
                BornholmDoorTypes doorType = accessor.getBlockState().getValue(BornholmMiddleBlock.DOOR_TYPE);
                if (doorType != BornholmDoorTypes.BASE) {
                    addDoorTypeTooltip(tooltip, doorType);
                }
            }
        }
    }

    private static void addDoorTypeTooltip(ITooltip tooltip, BornholmDoorTypes doorType) {
//        tooltip.addLine(TextUtil.getTranslatable("tooltip.waila.bornholm_trunk_door_type").append(": ")
//                .append(doorType.getTooltip()));
        tooltip.addLine(Component.literal(doorType.getTooltip()));
    }
}

