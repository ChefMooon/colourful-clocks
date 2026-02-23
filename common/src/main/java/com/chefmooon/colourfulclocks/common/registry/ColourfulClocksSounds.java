package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ColourfulClocksSounds {

    public static final Supplier<SoundEvent> ITEM_BASE_HANDBELL_RING = registerSound("item.base_handbell.ring");
    public static final Supplier<SoundEvent> ITEM_DIAMOND_HANDBELL_RING = registerSound("item.diamond_handbell.ring");

    public static final Supplier<SoundEvent> ITEM_EMERALD_HANDBELL_RING = registerSound("item.emerald_handbell.ring");
    public static final Supplier<SoundEvent> ITEM_AMETHYST_HANDBELL_RING = registerSound("item.amethyst_handbell.ring");
    public static final Supplier<SoundEvent> ITEM_QUARTZ_HANDBELL_RING = registerSound("item.quartz_handbell.ring");
    public static final Supplier<SoundEvent> ITEM_LAPIS_LAZULI_HANDBELL_RING = registerSound("item.lapis_lazuli_handbell.ring");
    public static final Supplier<SoundEvent> ITEM_REDSTONE_HANDBELL_RING = registerSound("item.redstone_handbell.ring");

    public static final Supplier<SoundEvent> ITEM_BASE_HANDBELL_HIT = registerSound("item.base_handbell.hit");
    public static final Supplier<SoundEvent> ITEM_DIAMOND_HANDBELL_HIT = registerSound("item.diamond_handbell.hit");

    public static final Supplier<SoundEvent> ITEM_EMERALD_HANDBELL_HIT = registerSound("item.emerald_handbell.hit");
    public static final Supplier<SoundEvent> ITEM_AMETHYST_HANDBELL_HIT = registerSound("item.amethyst_handbell.hit");
    public static final Supplier<SoundEvent> ITEM_QUARTZ_HANDBELL_HIT = registerSound("item.quartz_handbell.hit");
    public static final Supplier<SoundEvent> ITEM_LAPIS_LAZULI_HANDBELL_HIT = registerSound("item.lapis_lazuli_handbell.hit");
    public static final Supplier<SoundEvent> ITEM_REDSTONE_HANDBELL_HIT = registerSound("item.redstone_handbell.hit");

    public static final Supplier<SoundEvent> ITEM_IRON_PENDULUM_CHIME = registerSound("item.iron_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_COPPER_PENDULUM_CHIME = registerSound("item.copper_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_EXPOSED_COPPER_PENDULUM_CHIME = registerSound("item.exposed_copper_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_WEATHERED_COPPER_PENDULUM_CHIME = registerSound("item.weathered_copper_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_OXIDIZED_COPPER_PENDULUM_CHIME = registerSound("item.oxidized_copper_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_GOLD_PENDULUM_CHIME = registerSound("item.gold_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_DIAMOND_PENDULUM_CHIME = registerSound("item.diamond_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_NETHERITE_PENDULUM_CHIME = registerSound("item.netherite_pendulum.chime");

    public static final Supplier<SoundEvent> ITEM_EMERALD_PENDULUM_CHIME = registerSound("item.emerald_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_AMETHYST_PENDULUM_CHIME = registerSound("item.amethyst_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_QUARTZ_PENDULUM_CHIME = registerSound("item.quartz_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_LAPIS_LAZULI_PENDULUM_CHIME = registerSound("item.lapis_lazuli_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_REDSTONE_PENDULUM_CHIME = registerSound("item.redstone_pendulum.chime");

    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_TICK = registerSound("block.bornholm.tick");
    public static final Supplier<SoundEvent> BLOCK_CLOCK_TICK = registerSound("block.clock.tick");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_DOOR_OPEN = registerSound("block.bornholm.door_open");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_DOOR_CLOSE = registerSound("block.bornholm.door_close");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_REMOVE_PENDULUM = registerSound("block.bornholm.remove_pendulum");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_INSERT_PENDULUM = registerSound("block.bornholm.insert_pendulum");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_CHANGE_WOOD = registerSound("block.bornholm.change_wood");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_CHANGE_GLASS = registerSound("block.bornholm.change_glass");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_WAX_OFF = registerSound("block.bornholm.wax_off");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_WAX_ON = registerSound("block.bornholm.wax_on");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_AXE_SCRAPES = registerSound("block.bornholm.axe_scrapes");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_REMOVE_POCKET_WATCH = registerSound("block.bornholm.remove_pocket_watch");
    public static final Supplier<SoundEvent> BLOCK_BORNHOLM_INSERT_POCKET_WATCH = registerSound("block.bornholm.insert_pocket_watch");
    public static final Supplier<SoundEvent> BLOCK_ENABLE_TICKING = registerSound("block.enable_ticking");
    public static final Supplier<SoundEvent> BLOCK_DISABLE_TICKING = registerSound("block.disable_ticking");

    private static Supplier<SoundEvent> registerSound(String string) {
        return registerSound(TextUtil.res(string), () -> SoundEvent.createVariableRangeEvent(TextUtil.res(string)));
    }

    @ExpectPlatform
    public static <T extends SoundEvent> Supplier<T> registerSound(ResourceLocation id, Supplier<T> supplier) {
        throw new AssertionError();
    }

    public static void init() {
    }
}
