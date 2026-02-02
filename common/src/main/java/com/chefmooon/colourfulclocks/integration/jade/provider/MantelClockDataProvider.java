package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.common.block.base.BaseMantelClockBlock;
import com.chefmooon.colourfulclocks.common.data.MantelClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
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

public enum MantelClockDataProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getServerData().contains("pocket_watch_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("pocket_watch_type")));
        }
        if (blockAccessor.getServerData().contains("glass_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("glass_type")));
        }
        if (blockAccessor.getServerData().contains("pendulum_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("pendulum_type")));
        }
        if (blockAccessor.getServerData().contains("ticking")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("ticking")));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof BaseMantelClockBlock) {
            MantelClockComponent component = blockAccessor.getBlockEntity().collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getMantelClockData(), MantelClockComponent.getDefaultValue());
            if (component != null) {
                if (component.getPocketWatch().isPresent() && component.getPocketWatch().get().getType() != PocketWatchTypes.EMPTY) {
                    Item item = ColourfulClocksTypeUtil.getPocketWatchItemFromType(component.getPocketWatch().get().getType());
                    if (item != Items.AIR) compoundTag.putString(("pocket_watch_type"), item.getDescriptionId());
                }
                if (component.getPendulum().isPresent() && component.getPendulum().get().getType() != PendulumTypes.EMPTY) {
                    Item item = ColourfulClocksTypeUtil.getPendulumItemFromType(component.getPendulum().get().getType());
                    if (item != Items.AIR) compoundTag.putString("pendulum_type", item.getDescriptionId());
                }
                if (component.getGlassType().isPresent() && component.getGlassType().get() != BornholmTopGlassTypes.GLASS) {
                    compoundTag.putString(("glass_type"), component.getGlassType().get().getBlock().getDescriptionId());
                }
                if (component.getTicking().isPresent() && component.getTicking().get()) {
                    compoundTag.putString("ticking", TextUtil.getTranslatable("tooltip.ticking").getString());
                }
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("mantel_clock_data");
    }
}
