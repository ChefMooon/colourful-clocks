package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.data.types.ClockMaterialTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ColourfulClocksBlocks {

    public static ResourceLocation IRON_HANDBELL = block("iron_handbell");
    public static ResourceLocation COPPER_HANDBELL = block("copper_handbell");
    public static ResourceLocation EXPOSED_COPPER_HANDBELL = block("exposed_copper_handbell");
    public static ResourceLocation WEATHERED_COPPER_HANDBELL = block("weathered_copper_handbell");
    public static ResourceLocation OXIDIZED_COPPER_HANDBELL = block("oxidized_copper_handbell");
    public static ResourceLocation WAXED_COPPER_HANDBELL = block("waxed_copper_handbell");
    public static ResourceLocation WAXED_EXPOSED_COPPER_HANDBELL = block("waxed_exposed_copper_handbell");
    public static ResourceLocation WAXED_WEATHERED_COPPER_HANDBELL = block("waxed_weathered_copper_handbell");
    public static ResourceLocation WAXED_OXIDIZED_COPPER_HANDBELL = block("waxed_oxidized_copper_handbell");
    public static ResourceLocation GOLD_HANDBELL = block("gold_handbell");
    public static ResourceLocation DIAMOND_HANDBELL = block("diamond_handbell");
    public static ResourceLocation NETHERITE_HANDBELL = block("netherite_handbell");
    public static ResourceLocation QUARTZ_HANDBELL = block("quartz_handbell");
    public static ResourceLocation AMETHYST_HANDBELL = block("amethyst_handbell");
    public static ResourceLocation LAPIS_LAZULI_HANDBELL = block("lapis_lazuli_handbell");
    public static ResourceLocation REDSTONE_HANDBELL = block("redstone_handbell");
    public static ResourceLocation EMERALD_HANDBELL = block("emerald_handbell");

    public static ResourceLocation BORNHOLM_BASE = block("bornholm_base");
    public static ResourceLocation BORNHOLM_MIDDLE = block("bornholm_middle");
    public static ResourceLocation BORNHOLM_TOP = block("bornholm_top");

    public static ResourceLocation MANTEL_CLOCK = block("mantel_clock");
    public static ResourceLocation TALL_MANTEL_CLOCK = block("tall_mantel_clock");
    public static ResourceLocation WALL_CLOCK = block("wall_clock");
    public static ResourceLocation ALARM_CLOCK = block("alarm_clock");

    public static BlockBehaviour.Properties getProperties(ClockTypes clockTypes) {
//        return BlockBehaviour.Properties.ofFullCopy(clockTypes.getBlock());
        // TODO: improve this. Also does color map matter?
        if (clockTypes.getClockTypes() == ClockMaterialTypes.WOOD) {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).sound(clockTypes.getSoundType()).ignitedByLava();
        } else if (clockTypes.getClockTypes() == ClockMaterialTypes.STONE) {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(clockTypes.getSoundType());
        } else if (clockTypes.getClockTypes() == ClockMaterialTypes.DEEPSLATE) {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).sound(clockTypes.getSoundType());
        } else if (clockTypes.getClockTypes() == ClockMaterialTypes.TUFF_BRICK) {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.TUFF_BRICKS).sound(clockTypes.getSoundType());
        } else if (clockTypes.getClockTypes() == ClockMaterialTypes.BRICK) {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.BRICKS).sound(clockTypes.getSoundType());
        } else if (clockTypes.getClockTypes() == ClockMaterialTypes.MUD_BRICK) {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.MUD).sound(clockTypes.getSoundType());
        } else if (clockTypes.getClockTypes() == ClockMaterialTypes.NETHER_BRICK) {
            return BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS).sound(clockTypes.getSoundType());
        } else {
            throw new IllegalArgumentException("Unknown clock type: " + clockTypes);
        }
    }

    public static BlockBehaviour.Properties getHandbellProperties(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block).strength(4.0F).sound(SoundType.ANVIL);
    }

    private static ResourceLocation block(String string) {
        return TextUtil.res(string);
    }

    public static void init() {
    }
}
