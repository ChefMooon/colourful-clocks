package com.chefmooon.colourfulclocks.integration.wthit.provider;

import com.chefmooon.colourfulclocks.common.block.WallClockBlock;
import com.chefmooon.colourfulclocks.common.data.WallClockComponent;
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

public class WallClockDataProvider implements IBlockComponentProvider {
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        boolean pocketWatchType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.POCKET_WATCH_TYPE);
        boolean ticking = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.TICKING);
        if (pocketWatchType || ticking) {
            if (accessor.getBlock() instanceof WallClockBlock wallClockBlock) {
                BlockEntity controllerBlockEntity = wallClockBlock.getController(accessor.getBlockState(), accessor.getPosition(), accessor.getWorld());
                if (controllerBlockEntity != null) {
                    WallClockComponent component = controllerBlockEntity.collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getWallClockData(), WallClockComponent.getDefaultValue());
                    if (component != null) {
                        if (pocketWatchType && component.getPocketWatch().isPresent() && component.getPocketWatch().get().getType() != PocketWatchTypes.EMPTY) addPocketWatchTypeTooltip(tooltip, component.getPocketWatch().get().getType());
                        if (ticking && component.isTicking().isPresent() && component.isTicking().get()) addTickingTooltip(tooltip);
                    }
                }
            }
        }
    }

    private static void addPocketWatchTypeTooltip(ITooltip tooltip, PocketWatchTypes pocketWatchType) {
        tooltip.addLine(Component.translatable(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType).getDescriptionId()));
    }

    private static void addTickingTooltip(ITooltip tooltip) {
        tooltip.addLine(TextUtil.getTranslatable("tooltip.ticking"));
    }
}
