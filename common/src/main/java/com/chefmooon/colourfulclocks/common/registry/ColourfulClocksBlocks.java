package com.chefmooon.colourfulclocks.common.registry;

import com.chefmooon.colourfulclocks.common.data.types.ClockMaterialTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class ColourfulClocksBlocks {
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

    private static ResourceLocation block(String string) {
        return TextUtil.res(string);
    }

    public static void init() {
    }
}
