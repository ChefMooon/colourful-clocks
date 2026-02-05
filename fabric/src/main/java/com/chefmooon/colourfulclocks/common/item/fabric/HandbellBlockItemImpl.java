package com.chefmooon.colourfulclocks.common.item.fabric;

import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.item.HandbellBlockItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;

public class HandbellBlockItemImpl extends HandbellBlockItem {
    public HandbellBlockItemImpl(Block block, Properties properties, HandbellTypes type) {
        super(block, properties, type);
    }

    public static void playHandbellHitSound(DamageSource source) {
        Entity attacker = source.getDirectEntity();

        handbellHitSound(attacker);
    }
}
