package com.chefmooon.colourfulclocks.common.block.entity;

import com.chefmooon.colourfulclocks.common.block.entity.base.BaseGlassClockBlockEntity;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlockEntities;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class MantelClockBlockEntity extends BaseGlassClockBlockEntity {
    public MantelClockBlockEntity(BlockPos pos, BlockState blockState) {
        super(BuiltInRegistries.BLOCK_ENTITY_TYPE.get(ColourfulClocksBlockEntities.MANTEL_CLOCK), pos, blockState);
    }

    public ItemStack getBlockAsItem(ClockTypes clockType) {
        ItemStack itemStack = BuiltInRegistries.ITEM.get(ColourfulClocksBlocks.MANTEL_CLOCK.withSuffix(clockType.getSerializedName())).getDefaultInstance();
        itemStack.applyComponents(this.collectComponents());
        return itemStack;
    }
}