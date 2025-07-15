package com.chefmooon.colourfulclocks.common.registry.neoforge;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ColourfulClocksAdvancementsImpl {
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS = DeferredRegister.create(Registries.TRIGGER_TYPE, ColourfulClocks.MOD_ID);

    public static <T extends CriterionTrigger<?>> Supplier<T> registerTrigger(String name, Supplier<T> triggerSupplier) {
        return TRIGGERS.register(name, triggerSupplier);
    }

    public static void register(IEventBus eventBus) {
        TRIGGERS.register(eventBus);
    }
}
