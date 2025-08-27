package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.common.block.base.BaseDataClockBlock;
import com.chefmooon.colourfulclocks.common.data.ClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
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

public enum PocketWatchDataProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;
    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getServerData().contains("pocket_watch_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("pocket_watch_type")));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof BaseDataClockBlock) {
            ClockComponent component = blockAccessor.getBlockEntity().collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getClockData(), ClockComponent.getDefaultValue());
            Item item = ColourfulClocksTypeUtil.getPocketWatchItemFromType(component.getPocketWatchType().get());
            if (component != null && component.getPocketWatchType().isPresent() && component.getPocketWatchType().get() != PocketWatchTypes.EMPTY && item != Items.AIR) compoundTag.putString(("pocket_watch_type"), item.getDescriptionId());

        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("pocket_watch_type");
    }
}
