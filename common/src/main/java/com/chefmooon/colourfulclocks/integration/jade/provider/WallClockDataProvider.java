package com.chefmooon.colourfulclocks.integration.jade.provider;

import com.chefmooon.colourfulclocks.common.block.WallClockBlock;
import com.chefmooon.colourfulclocks.common.data.WallClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum WallClockDataProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if (blockAccessor.getServerData().contains("pocket_watch_type")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("pocket_watch_type")));
        }
        if (blockAccessor.getServerData().contains("ticking")) {
            iTooltip.add(Component.translatable(blockAccessor.getServerData().getString("ticking")));
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        if (blockAccessor.getBlock() instanceof WallClockBlock wallClockBlock) {
            BlockEntity controllerBlockEntity = wallClockBlock.getController(blockAccessor.getBlockState(), blockAccessor.getPosition(), blockAccessor.getLevel());
            if (controllerBlockEntity != null) {
                DataComponentMap dataComponentMap = controllerBlockEntity.collectComponents();
                if (dataComponentMap.has(ColourfulClocksDataComponentTypes.getWallClockData())) {
                    WallClockComponent component = dataComponentMap.getOrDefault(ColourfulClocksDataComponentTypes.getWallClockData(), WallClockComponent.getDefaultValue());
                    if (component != null) {
                        if (component.getPocketWatch().isPresent()) {
                            Item item = ColourfulClocksTypeUtil.getPocketWatchItemFromType(component.pocketWatch().get().type());
                            if (component.pocketWatch().get().getType() != PocketWatchTypes.EMPTY && item != Items.AIR) compoundTag.putString("pocket_watch_type", item.getDescriptionId());
                        }
                        if (component.ticking().isPresent() && component.ticking().get()) {
                            compoundTag.putString("ticking", TextUtil.getTranslatable("tooltip.ticking").getString());
                        }
                    }
                }
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return TextUtil.res("wall_clock_data");
    }
}
