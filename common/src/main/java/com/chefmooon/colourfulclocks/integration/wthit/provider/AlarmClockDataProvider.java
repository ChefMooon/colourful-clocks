package com.chefmooon.colourfulclocks.integration.wthit.provider;

import com.chefmooon.colourfulclocks.common.block.AlarmAlarmClockBlock;
import com.chefmooon.colourfulclocks.common.data.AlarmClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.chefmooon.colourfulclocks.integration.wthit.ColourfulClocksCommonWailaPlugin;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AlarmClockDataProvider implements IBlockComponentProvider {
    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        boolean clockBell = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.CLOCK_BELL);
        boolean pocketWatchType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.POCKET_WATCH_TYPE);
        boolean glassType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.GLASS_TYPE);
        boolean ticking = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.TICKING);
        if (clockBell || pocketWatchType || glassType || ticking) {
            if (accessor.getBlock() instanceof AlarmAlarmClockBlock) {
                BlockEntity blockEntity = accessor.getBlockEntity();
                if (blockEntity != null) {
                    AlarmClockComponent component = blockEntity.collectComponents().getOrDefault(com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes.getAlarmClockData(), AlarmClockComponent.getDefaultValue());
                    if (component != null) {
                        if (clockBell) {
                            if (component.leftBell().isPresent()) {
                                addHandbellTooltip(tooltip, component.leftBell().get().type());
                            }
                            if (component.rightBell().isPresent()) {
                                addHandbellTooltip(tooltip, component.rightBell().get().type());
                            }
                        }
                        if (pocketWatchType && component.pocketWatch().isPresent() && component.pocketWatch().get().getType() != PocketWatchTypes.EMPTY) {
                            addPocketWatchTypeTooltip(tooltip, component.pocketWatch().get().type());
                        }
                        if (glassType && component.glassType().isPresent() && component.glassType().get() != BornholmTopGlassTypes.GLASS) {
                            addGlassTypeTooltip(tooltip, component.glassType().get());
                        }
                        if (ticking && component.ticking().isPresent() && component.ticking().get()) {
                            addTickingTooltip(tooltip);
                        }
                    }
                }
            }
        }
    }

    private static void addHandbellTooltip(ITooltip tooltip, HandbellTypes handbellType) {
        tooltip.addLine(Component.translatable(ColourfulClocksTypeUtil.getHandbellItemFromType(handbellType).getDescriptionId()));
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
