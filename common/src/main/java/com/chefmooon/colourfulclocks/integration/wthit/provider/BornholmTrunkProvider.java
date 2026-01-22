package com.chefmooon.colourfulclocks.integration.wthit.provider;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.integration.wthit.ColourfulClocksCommonWailaPlugin;
import mcp.mobius.waila.api.IBlockAccessor;
import mcp.mobius.waila.api.IBlockComponentProvider;
import mcp.mobius.waila.api.IPluginConfig;
import mcp.mobius.waila.api.ITooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;

public class BornholmTrunkProvider implements IBlockComponentProvider {

    @Override
    public void appendBody(ITooltip tooltip, IBlockAccessor accessor, IPluginConfig config) {
        boolean trunkDoorType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.BORNHOLM_TRUNK_DOOR_TYPE);
        boolean pendulumType = config.getBoolean(ColourfulClocksCommonWailaPlugin.Options.PENDULUM_TYPE);
        if (trunkDoorType || pendulumType) {
            if (accessor.getBlock() instanceof BornholmMiddleBlock) {
                BornholmDoorTypes doorType = accessor.getBlockState().getValue(BornholmMiddleBlock.DOOR_TYPE);
                if (doorType != BornholmDoorTypes.BASE) {
                    addDoorTypeTooltip(tooltip, doorType);
                }
                BlockEntity blockEntity = accessor.getBlockEntity();
                if (blockEntity != null) {
                    BornholmMiddleDoorComponent component = accessor.getBlockEntity().collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), BornholmMiddleDoorComponent.getDefaultValue());
                    if (component != null) {
                        if (pendulumType && component.getPendulum().isPresent()) {
                            PendulumTypes pendulumTypeValue = component.getPendulum().get().getType();
                            if (pendulumTypeValue != PendulumTypes.EMPTY) {
                                addPendulumTypeTooltip(tooltip, pendulumTypeValue);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void addDoorTypeTooltip(ITooltip tooltip, BornholmDoorTypes doorType) {
        tooltip.addLine(Component.translatable(doorType.getBlock().getDescriptionId()));
    }

    private static void addPendulumTypeTooltip(ITooltip tooltip, PendulumTypes pendulumType) {
        tooltip.addLine(Component.translatable(ColourfulClocksTypeUtil.getPendulumItemFromType(pendulumType).getDescriptionId()));
    }
}

