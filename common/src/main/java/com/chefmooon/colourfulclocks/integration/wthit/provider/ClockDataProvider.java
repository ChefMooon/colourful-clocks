package com.chefmooon.colourfulclocks.integration.wthit.provider;

import com.chefmooon.colourfulclocks.common.block.base.BaseDataClockBlock;
import com.chefmooon.colourfulclocks.common.data.MantelClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
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
import net.minecraft.world.level.block.entity.BlockEntity;

public class ClockDataProvider implements IBlockComponentProvider {
    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        boolean glassType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.GLASS_TYPE);
        boolean pocketWatchType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.POCKET_WATCH_TYPE);
        boolean pendulumType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.PENDULUM_TYPE);
        boolean ticking = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.TICKING);
        if (glassType || pocketWatchType || pendulumType || ticking) {
            if (accessor.getBlock() instanceof BaseDataClockBlock) {
                BlockEntity blockEntity = accessor.getBlockEntity();
                if (blockEntity != null) {
                    MantelClockComponent component = blockEntity.collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getMantelClockData(), MantelClockComponent.getBasicClockValue());
                    if (component != null) {
                        if (glassType && component.getGlassType().isPresent() && component.getGlassType().get() != BornholmTopGlassTypes.GLASS) addGlassTypeTooltip(tooltip, component.getGlassType().get());
                        if (pocketWatchType && component.getPocketWatch().isPresent() && component.getPocketWatch().get().getType() != PocketWatchTypes.EMPTY) addPocketWatchTypeTooltip(tooltip, component.getPocketWatch().get().getType());
                        if (pendulumType && component.getPendulum().isPresent() && component.getPendulum().get().getType()!= PendulumTypes.EMPTY) addPendulumTypeTooltip(tooltip, component.getPendulum().get().getType());
                        if (ticking && component.getTicking().isPresent() && component.getTicking().get()) addTickingTooltip(tooltip);
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

    private static void addPendulumTypeTooltip(ITooltip tooltip, PendulumTypes pendulumType) {
        tooltip.addLine(Component.translatable(ColourfulClocksTypeUtil.getPendulumItemFromType(pendulumType).getDescriptionId()));
    }

    private static void addTickingTooltip(ITooltip tooltip) {
        tooltip.addLine(TextUtil.getTranslatable("tooltip.ticking"));
    }
}
