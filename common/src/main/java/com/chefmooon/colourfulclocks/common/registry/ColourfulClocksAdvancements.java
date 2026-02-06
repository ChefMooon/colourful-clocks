package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.advancement.*;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.advancements.CriterionTrigger;

import java.util.function.Supplier;

public class ColourfulClocksAdvancements {

    public static final Supplier<BornholmTrunkWindChargeTrigger> BORNHOLM_TRUNK_WIND_CHARGE = registerTrigger("bornholm_trunk_wind_charge", BornholmTrunkWindChargeTrigger::new);
    public static final Supplier<BornholmTrunkGlassChangeTrigger> BORNHOLM_TRUNK_GLASS_CHANGE = registerTrigger("bornholm_trunk_glass_change", BornholmTrunkGlassChangeTrigger::new);
    public static final Supplier<BornholmActivatedTrigger> BORNHOLM_ACTIVATED_TRIGGER = registerTrigger("bornholm_activated", BornholmActivatedTrigger::new);

    public static final Supplier<InsertPocketWatchTrigger> INSERT_POCKET_WATCH_TRIGGER = registerTrigger("insert_pocket_watch", InsertPocketWatchTrigger::new);
    public static final Supplier<InsertHandbellTrigger> INSERT_HANDBELL_TRIGGER = registerTrigger("insert_handbell", InsertHandbellTrigger::new);
    public static final Supplier<InsertPendulumTrigger> INSERT_PENDULUM_TRIGGER = registerTrigger("insert_pendulum", InsertPendulumTrigger::new);
    public static final Supplier<EnableTickingTrigger> ENABLE_TICKING_TRIGGER = registerTrigger("enable_ticking", EnableTickingTrigger::new);
    public static final Supplier<DisableTickingTrigger> DISABLE_TICKING_TRIGGER = registerTrigger("disable_ticking", DisableTickingTrigger::new);
    public static final Supplier<GlassChangeTrigger> GLASS_CHANGE_TRIGGER = registerTrigger("glass_change", GlassChangeTrigger::new);
    public static final Supplier<CopperWaxOnTrigger> COPPER_WAX_ON_TRIGGER = registerTrigger("copper_wax_on", CopperWaxOnTrigger::new);
    public static final Supplier<CopperWaxOffTrigger> COPPER_WAX_OFF_TRIGGER = registerTrigger("copper_wax_off", CopperWaxOffTrigger::new);
    @ExpectPlatform
    public static <T extends CriterionTrigger<?>> Supplier<T> registerTrigger(String name, Supplier<T> triggerSupplier) {
        throw new AssertionError();
    }

    public static void init() {
    }
}
