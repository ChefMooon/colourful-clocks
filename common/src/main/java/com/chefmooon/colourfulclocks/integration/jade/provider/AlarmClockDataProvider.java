package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.common.block.AlarmClockBlock;
import com.chefmooon.colourfulclocks.common.data.AlarmClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
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

public enum AlarmClockDataProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getServerData().contains("left_handbell")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("left_handbell")));
        }
        if (blockAccessor.getServerData().contains("right_handbell")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("right_handbell")));
        }
        if (blockAccessor.getServerData().contains("pocket_watch_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("pocket_watch_type")));
        }
        if (blockAccessor.getServerData().contains("glass_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("glass_type")));
        }
        if (blockAccessor.getServerData().contains("ticking")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("ticking")));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof AlarmClockBlock) {
            AlarmClockComponent component = blockAccessor.getBlockEntity().collectComponents().getOrDefault(ColourfulClocksDataComponentTypes.getAlarmClockData(), AlarmClockComponent.getDefaultValue());
            if (component != null) {
                if (component.leftBell().isPresent()) {
                    Item item = ColourfulClocksTypeUtil.getHandbellItemFromType(component.leftBell().get().type());
                    if (item != Items.AIR) compoundTag.putString("left_handbell", item.getDescriptionId());
                }
                if (component.rightBell().isPresent()) {
                    Item item = ColourfulClocksTypeUtil.getHandbellItemFromType(component.rightBell().get().type());
                    if (item != Items.AIR) compoundTag.putString("right_handbell", item.getDescriptionId());
                }
                if (component.pocketWatch().isPresent()) {
                    Item item = ColourfulClocksTypeUtil.getPocketWatchItemFromType(component.pocketWatch().get().type());
                    if (component.pocketWatch().get().getType() != PocketWatchTypes.EMPTY && item != Items.AIR) compoundTag.putString("pocket_watch_type", item.getDescriptionId());
                }
                if (component.glassType().isPresent()) {
                    if (component.glassType().get() != BornholmTopGlassTypes.GLASS) {
                        compoundTag.putString("glass_type", component.glassType().get().getBlock().getDescriptionId());
                    }
                }
                if (component.ticking().isPresent() && component.ticking().get()) {
                    compoundTag.putString("ticking", TextUtil.getTranslatable("tooltip.ticking").getString());
                }
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("alarm_clock_data");
    }
}
