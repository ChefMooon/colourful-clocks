package com.chefmooon.colourfulclocks.common.item.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import com.chefmooon.colourfulclocks.common.item.HandbellBlockItem;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

public class HandbellBlockItemImpl extends HandbellBlockItem {
    public HandbellBlockItemImpl(Block block, Properties properties, HandbellTypes type) {
        super(block, properties, type);
    }

    @EventBusSubscriber(modid = ColourfulClocks.MOD_ID)
    public static class HandbellEvents {
        @SubscribeEvent
        public static void playHandbellHitSound(LivingDamageEvent.Pre event) {
            DamageSource damageSource = event.getSource();
            Entity attacker = damageSource.getEntity();

            handbellHitSound(attacker);
        }
    }
}
