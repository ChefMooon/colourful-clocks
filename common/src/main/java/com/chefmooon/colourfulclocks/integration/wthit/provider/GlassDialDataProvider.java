package com.chefmooon.colourfulclocks.integration.wthit.provider;

import com.chefmooon.colourfulclocks.common.block.MantelClockBlock;
import com.chefmooon.colourfulclocks.common.data.GlassDialComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.chefmooon.colourfulclocks.integration.wthit.ColourfulClocksCommonWailaPlugin;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.network.chat.Component;

public class GlassDialDataProvider implements IBlockComponentProvider {
    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        boolean glassType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.GLASS_TYPE);
        boolean pocketWatchType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.POCKET_WATCH_TYPE);
        boolean ticking = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.TICKING);
        if (glassType || pocketWatchType || ticking) {
            if (accessor.getBlock() instanceof MantelClockBlock) {
                GlassDialComponent glassDialComponent = accessor.getBlockEntity().collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getGlassDialData(), GlassDialComponent.getDefaultValue());
                if (glassDialComponent != null) {
                    if (glassType && glassDialComponent.getGlassType() != BornholmTopGlassTypes.GLASS) addGlassTypeTooltip(tooltip, glassDialComponent.getGlassType());
                    if (pocketWatchType && glassDialComponent.getPocketWatchType() != PocketWatchTypes.EMPTY) addPocketWatchTypeTooltip(tooltip, glassDialComponent.getPocketWatchType());
                    if (ticking && glassDialComponent.getTicking()) addTickingTooltip(tooltip);
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

    private static void addTickingTooltip(ITooltip tooltip) {
        tooltip.addLine(TextUtil.getTranslatable("tooltip.ticking"));
    }
}
