package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.common.block.base.BaseDataClockBlock;
import com.chefmooon.colourfulclocks.common.data.ClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
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

public enum PendulumDataProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;
    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getServerData().contains("pendulum_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("pendulum_type")));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof BaseDataClockBlock) {
            ClockComponent component = blockAccessor.getBlockEntity().collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), ClockComponent.getDefaultValue());
            if (component != null && component.getPendulum().isPresent()) {
                Item item = ColourfulClocksTypeUtil.getPendulumItemFromType(component.getPendulum().get().getType());
                if (component.getPendulum().get().getType() != PendulumTypes.EMPTY && item != Items.AIR) compoundTag.putString("pendulum_type", item.getDescriptionId());
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("pendulum_type");
    }
}
