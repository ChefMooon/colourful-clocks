package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
            iTooltip.add((Component.translatable(blockAccessor.getServerData().getString("bornholm_trunk_door_type"))));
        }
        if (blockAccessor.getServerData().contains("pendulum_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("pendulum_type")));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof BornholmMiddleBlock) {
            BornholmDoorTypes doorType = blockAccessor.getBlockState().getValue(BornholmMiddleBlock.DOOR_TYPE);
            if (doorType != BornholmDoorTypes.BASE) compoundTag.putString("bornholm_trunk_door_type", doorType.getBlock().getDescriptionId());
            BornholmMiddleDoorComponent component = blockAccessor.getBlockEntity().collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getBornholmMiddleGlassData(), BornholmMiddleDoorComponent.getDefaultValue());
            if (component != null && component.getPendulum().isPresent()) {
                Item item = ColourfulClocksTypeUtil.getPendulumItemFromType(component.getPendulum().get().getType());
                if (component.getPendulum().get().getType() != PendulumTypes.EMPTY && item != Items.AIR) compoundTag.putString("pendulum_type", item.getDescriptionId());
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("bornholm_trunk_door_type");
    }
}
