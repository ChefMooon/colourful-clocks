package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.Supplier;

public class ColourfulClocksAdvancementsImpl {
    public static <T extends CriterionTrigger<?>> Supplier<T> registerTrigger(String name, Supplier<T> triggerSupplier) {
        return register(name, triggerSupplier, BuiltInRegistries.TRIGGER_TYPES);
    }

    public static <R, T extends R> Supplier<T> register(String name, Supplier<T> supplier, Registry<R> reg) {
        T object = supplier.get();
        Registry.register(reg, TextUtil.res(name), object);
        return () -> object;
    }

    public static void register() {
    }
}
