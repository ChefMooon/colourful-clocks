package com.chefmooon.colourfulclocks.common.registry.fabric;

import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ColourfulClocksSoundsImpl {
    static final List<Supplier<?>> SOUNDS = new ArrayList<>();

    public static <T extends SoundEvent> Supplier<T> registerSound(ResourceLocation id, Supplier<T> supplier) {
        var v = Suppliers.memoize(() -> Registry.register(BuiltInRegistries.SOUND_EVENT, id, supplier.get()));
        SOUNDS.add(v);
        return v;
    }

    public static void register() {
        SOUNDS.forEach(Supplier::get);
        SOUNDS.clear();
    }
}
