package com.chefmooon.colourfulclocks.common.util.fabric;

import com.chefmooon.colourfulclocks.ColourfulClocks;
import com.chefmooon.colourfulclocks.common.data.BornholmMiddleDoorComponent;
import com.chefmooon.colourfulclocks.common.data.BornholmTopGlassComponent;
import com.chefmooon.colourfulclocks.common.data.ClockComponent;
import com.chefmooon.colourfulclocks.common.data.GlassDialComponent;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksDataComponentTypesImpl;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksItemProperties;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ColourfulClocksItemPropertiesImpl {

    public static void addCustomItemProperties() {
        ColourfulClocksItemsImpl.BORNHOLM_MIDDLE_VARIANTS.forEach((entry, supplier) ->
                registerBornholmMiddle(supplier.get()));
        ColourfulClocksItemsImpl.BORNHOLM_TOP_VARIANTS.forEach((entry, supplier) ->
                registerBornholmTop(supplier.get()));
        ColourfulClocksItemsImpl.MANTEL_CLOCK_VARIANTS.forEach((entry, supplier) ->
                registerMantelClock(supplier.get()));
        ColourfulClocksItemsImpl.TALL_MANTEL_CLOCK_VARIANTS.forEach((entry, supplier) ->
                registerTallMantelClock(supplier.get()));
        ColourfulClocksItemsImpl.ALARM_CLOCK_VARIANTS.forEach((entry, supplier) ->
                registerAlarmClock(supplier.get()));
        ColourfulClocksItemsImpl.POCKET_WATCH_VARIANTS.forEach(((pocketWatchTypes, itemSupplier) ->
                registerPocketWatch(itemSupplier.get())));
    }

    private static void registerBornholmMiddle(Item item) {
        ItemProperties.register(item, ColourfulClocksItemProperties.GLASS_TYPE,
                (itemStack, clientLevel, livingEntity, i) -> {
            // Convert the id to a float and return
            return itemStack.getOrDefault(ColourfulClocksDataComponentTypesImpl.BORNHOLM_MIDDLE_GLASS_DATA, BornholmMiddleDoorComponent.getDefaultValue()).getDoorType().getId() / 100.0f;
        });
    }

    private static void registerBornholmTop(Item item) {
        ItemProperties.register(item, ColourfulClocksItemProperties.GLASS_TYPE,
                (itemStack, clientLevel, livingEntity, i) -> {
            // Convert the id to a float and return
            return itemStack.getOrDefault(ColourfulClocksDataComponentTypesImpl.BORNHOLM_TOP_GLASS_DATA, BornholmTopGlassComponent.getDefaultValue()).getGlassType().getId() / 100.0f;
        });
    }

    private static void registerMantelClock(Item item) {
        ItemProperties.register(item, ColourfulClocksItemProperties.GLASS_TYPE,
                (itemStack, clientLevel, livingEntity, i) -> {
            // Convert the id to a float and return
            ClockComponent component = itemStack.getOrDefault(ColourfulClocksDataComponentTypesImpl.CLOCK_DATA, ClockComponent.getNoPendulumValue());
            return component.getGlassType().isPresent() ? component.getGlassType().get().getId() / 100.0f : 0.0f;
        });
    }

    private static void registerTallMantelClock(Item item) {
        ItemProperties.register(item, ColourfulClocksItemProperties.GLASS_TYPE,
                (itemStack, clientLevel, livingEntity, i) -> {
            // Convert the id to a float and return
            ClockComponent component = itemStack.getOrDefault(ColourfulClocksDataComponentTypesImpl.CLOCK_DATA, ClockComponent.getBasicClockValue());
            return component.getGlassType().isPresent() ? component.getGlassType().get().getId() / 100.0f : 0.0f;
        });
    }

    private static void registerAlarmClock(Item item) {
        ItemProperties.register(item, ColourfulClocksItemProperties.GLASS_TYPE,
                (itemStack, clientLevel, livingEntity, i) -> {
                    // Convert the id to a float and return
                    ClockComponent component = itemStack.getOrDefault(ColourfulClocksDataComponentTypesImpl.CLOCK_DATA, ClockComponent.getNoPendulumValue());
                    return component.getGlassType().isPresent() ? component.getGlassType().get().getId() / 100.0f : 0.0f;
                });
    }

    private static void registerPocketWatch(Item item) {
        ItemProperties.register(
                item, ResourceLocation.fromNamespaceAndPath(ColourfulClocks.MOD_ID, "closed"),
                (stack, level, entity, seed) -> Boolean.TRUE.equals(stack.get(ColourfulClocksDataComponentTypesImpl.POCKET_WATCH_CLOSED)) ? 1.0F : 0.0F
        );

        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("time"), new ClampedItemPropertyFunction() {
            private double rotation;
            private double rota;
            private long lastUpdateTick;

            @Override
            public float unclampedCall(ItemStack arg, @Nullable ClientLevel arg2, @Nullable LivingEntity arg3, int i) {
                Entity entity = (Entity)(arg3 != null ? arg3 : arg.getEntityRepresentation());
                if (entity == null) {
                    return 0.0F;
                } else {
                    if (arg2 == null && entity.level() instanceof ClientLevel) {
                        arg2 = (ClientLevel)entity.level();
                    }

                    if (arg2 == null) {
                        return 0.0F;
                    } else {
                        double d0;
                        if (arg2.dimensionType().natural()) {
                            d0 = (double)arg2.getTimeOfDay(1.0F);
                        } else {
                            d0 = Math.random();
                        }

                        d0 = this.wobble(arg2, d0);
                        return (float)d0;
                    }
                }
            }

            private double wobble(Level level, double rotation) {
                if (level.getGameTime() != this.lastUpdateTick) {
                    this.lastUpdateTick = level.getGameTime();
                    double d0 = rotation - this.rotation;
                    d0 = Mth.positiveModulo(d0 + 0.5, 1.0) - 0.5;
                    this.rota += d0 * 0.1;
                    this.rota *= 0.9;
                    this.rotation = Mth.positiveModulo(this.rotation + this.rota, 1.0);
                }

                return this.rotation;
            }
        });
    }

//    private static void registerPocketWatch(Item item) {
//        ItemProperties.register(
//                item, ResourceLocation.fromNamespaceAndPath(TemporalTimepieces.MOD_ID, "open"),
//                (stack, level, entity, seed) -> Boolean.TRUE.equals(stack.get(TemporalTimepiecesDataComponentTypesImpl.POCKET_WATCH_OPEN.get())) ? 1.0F : 0.0F
//        );
//
//        ItemProperties.register(item, ResourceLocation.withDefaultNamespace("time"), new ClampedItemPropertyFunction() {
//            private double rotation;
//            private double rota;
//            private long lastUpdateTick;
//
//            @Override
//            public float unclampedCall(ItemStack arg, @Nullable ClientLevel arg2, @Nullable LivingEntity arg3, int i) {
//                Entity entity = (Entity)(arg3 != null ? arg3 : arg.getEntityRepresentation());
//                if (entity == null) {
//                    return 0.0F;
//                } else {
//                    if (arg2 == null && entity.level() instanceof ClientLevel) {
//                        arg2 = (ClientLevel)entity.level();
//                    }
//
//                    if (arg2 == null) {
//                        return 0.0F;
//                    } else {
//                        double d0;
//                        if (arg2.dimensionType().natural()) {
//                            d0 = (double)arg2.getTimeOfDay(1.0F);
//                        } else {
//                            d0 = Math.random();
//                        }
//
//                        d0 = this.wobble(arg2, d0);
//                        return (float)d0;
//                    }
//                }
//            }
//
//            private double wobble(Level level, double rotation) {
//                if (level.getGameTime() != this.lastUpdateTick) {
//                    this.lastUpdateTick = level.getGameTime();
//                    double d0 = rotation - this.rotation;
//                    d0 = Mth.positiveModulo(d0 + 0.5, 1.0) - 0.5;
//                    this.rota += d0 * 0.1;
//                    this.rota *= 0.9;
//                    this.rotation = Mth.positiveModulo(this.rotation + this.rota, 1.0);
//                }
//
//                return this.rotation;
//            }
//        });
//    }
}
