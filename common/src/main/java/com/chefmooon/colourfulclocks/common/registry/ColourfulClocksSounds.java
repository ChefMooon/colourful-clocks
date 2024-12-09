package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ColourfulClocksSounds {

    public static final Supplier<SoundEvent> ITEM_IRON_PENDULUM_CHIME = registerSound("item.iron_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_COPPER_PENDULUM_CHIME = registerSound("item.copper_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_EXPOSED_COPPER_PENDULUM_CHIME = registerSound("item.exposed_copper_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_WEATHERED_COPPER_PENDULUM_CHIME = registerSound("item.weathered_copper_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_OXIDIZED_COPPER_PENDULUM_CHIME = registerSound("item.oxidized_copper_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_GOLD_PENDULUM_CHIME = registerSound("item.gold_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_DIAMOND_PENDULUM_CHIME = registerSound("item.diamond_pendulum.chime");
    public static final Supplier<SoundEvent> ITEM_NETHERITE_PENDULUM_CHIME = registerSound("item.netherite_pendulum.chime");

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
