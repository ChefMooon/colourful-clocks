package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassRecord;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Objects;

public class BornholmTopBlockItem extends BlockItem {

    public BornholmTopBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.get(DataComponents.BLOCK_STATE) != null) {
            String type = Objects.requireNonNull(stack.get(DataComponents.BLOCK_STATE).get(BornholmTopBlock.GLASS_TYPE)).getTooltip();
            if (!type.isEmpty()) {
                tooltipComponents.add(Component.literal(type)); // todo - this should be translatable
            }
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
