package com.chefmooon.colourfulclocks.integration.wthit.provider;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.integration.wthit.ColourfulClocksCommonWailaPlugin;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BornholmDialProvider implements IBlockComponentProvider {

    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        boolean glassType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.BORNHOLM_DIAL_GLASS_TYPE);
        boolean pocketWatchType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.POCKET_WATCH_TYPE);
        if (glassType || pocketWatchType) {
            if (accessor.getBlock() instanceof BornholmTopBlock) {
                BornholmTopGlassTypes glassTypeValue = accessor.getBlockState().getValue(BornholmTopBlock.GLASS_TYPE);
                if (glassTypeValue != BornholmTopGlassTypes.GLASS) {
                    addGlassTypeTooltip(tooltip, glassTypeValue);
                }
                BlockEntity blockEntity = accessor.getBlockEntity();
                if (blockEntity != null) {
                    BornholmTopGlassComponent component = blockEntity.collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getBornholmTopGlassData(), BornholmTopGlassComponent.getDefaultValue());
                    if (component != null) {
                        if (pocketWatchType && component.getPocketWatch().isPresent()) {
                            PocketWatchTypes pocketWatchTypeValue = component.getPocketWatch().get().getType();
                            if (pocketWatchTypeValue != PocketWatchTypes.EMPTY) {
                                addPocketWatchTypeTooltip(tooltip, pocketWatchTypeValue);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void addGlassTypeTooltip(ITooltip tooltip, BornholmTopGlassTypes glassType) {
        tooltip.addLine(Component.translatable(glassType.getBlock().getDescriptionId()));
    }

    private static void addPocketWatchTypeTooltip(ITooltip tooltip, PocketWatchTypes pocketWatchType) {
        tooltip.addLine(Component.translatable(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType).getDescriptionId()));
    }
}
