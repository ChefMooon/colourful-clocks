package com.chefmooon.colourfulclocks.common.util;

import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;

import java.util.Optional;

public class ColourfulClocksTemplates {

    public static final ModelTemplate HANDHELD_POCKET_WATCH = item("template_pocket_watch_open", TextureSlot.LAYER0);

    public static final ModelTemplate BORNHOLM_BASE = block("template_bornholm_base", TextureSlot.LAYER0);
    public static final ModelTemplate BORNHOLM_MIDDLE = block("template_bornholm_middle",
            TextureSlot.SIDE, TextureSlot.INSIDE, ColourfulClocksTextureSlots.DOOR);
    public static final ModelTemplate BORNHOLM_MIDDLE_OPEN = block("template_bornholm_middle_open",
            TextureSlot.SIDE, TextureSlot.INSIDE, ColourfulClocksTextureSlots.DOOR);
    public static final ModelTemplate BORNHOLM_MIDDLE_BASE = block("template_bornholm_middle_base",
            TextureSlot.SIDE, TextureSlot.INSIDE);
    public static final ModelTemplate BORNHOLM_MIDDLE_BASE_OPEN = block("template_bornholm_middle_base_open",
            TextureSlot.SIDE, TextureSlot.INSIDE);
    public static final ModelTemplate BORNHOLM_MIDDLE_ITEM = item("template_bornholm_middle",
            TextureSlot.SIDE, TextureSlot.INSIDE, ColourfulClocksTextureSlots.DOOR);
    public static final ModelTemplate BORNHOLM_MIDDLE_NO_DOOR = block("template_bornholm_middle_no_door",
            TextureSlot.SIDE, TextureSlot.INSIDE, ColourfulClocksTextureSlots.DOOR);
    public static final ModelTemplate BORNHOLM_TOP = block("template_bornholm_top",
        TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_COVER,
            ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate BORNHOLM_TOP_ITEM = item("template_bornholm_top", TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL,
        ColourfulClocksTextureSlots.CLOCK_DIAL_COVER, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);


    public static final ModelTemplate MANTEL_CLOCK = block("template_mantel_clock",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_COVER,
            ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate MANTEL_CLOCK_ITEM = item("template_mantel_clock",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_COVER,
            ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);

    public static final ModelTemplate TALL_MANTEL_CLOCK = block("template_tall_mantel_clock",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_COVER,
            ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate TALL_MANTEL_CLOCK_WALL = block("template_tall_mantel_clock_wall",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_COVER,
            ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate TALL_MANTEL_CLOCK_ITEM = item("template_tall_mantel_clock",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_COVER,
            ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);

    public static final ModelTemplate WALL_CLOCK_BASE = block("template_wall_clock_base",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_BOTTOM_LEFT = block("template_wall_clock_bottom_left",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_BOTTOM_RIGHT = block("template_wall_clock_bottom_right",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_TOP_LEFT = block("template_wall_clock_top_left",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_TOP_RIGHT = block("template_wall_clock_top_right",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_BOTTOM_MIDDLE = block("template_wall_clock_bottom_middle",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_LEFT_MIDDLE = block("template_wall_clock_left_middle",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_TOP_MIDDLE = block("template_wall_clock_top_middle",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_RIGHT_MIDDLE = block("template_wall_clock_right_middle",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_CENTER = block("template_wall_clock_center",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_BOTTOM_LEFT_XL = block("template_wall_clock_bottom_left_xl",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_BOTTOM_RIGHT_XL = block("template_wall_clock_bottom", // TODO: check usage of this
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_TOP_LEFT_XL = block("template_wall_clock_top_left_xl",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_TOP_RIGHT_XL = block("template_wall_clock_top_right_xl",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate WALL_CLOCK_ITEM = item("template_wall_clock",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);

    public static final ModelTemplate ALARM_CLOCK = block("template_alarm_clock",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL,
            ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate ALARM_CLOCK_ITEM = item("template_alarm_clock",
            TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_COVER,
            ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);

    public static final ModelTemplate POCKET_WATCH_CLOCK = block("template_pocket_watch_in_clock", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_MINUTE_HAND_XL = block("template_pocket_watch_minute_hand_xl", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_HOUR_HAND_XL = block("template_pocket_watch_hour_hand_xl", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_MINUTE_HAND_LARGE = block("template_pocket_watch_minute_hand_large", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_HOUR_HAND_LARGE = block("template_pocket_watch_hour_hand_large", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_MINUTE_HAND = block("template_pocket_watch_minute_hand", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_HOUR_HAND = block("template_pocket_watch_hour_hand", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_MINUTE_HAND_SMALL = block("template_pocket_watch_minute_hand_small", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_HOUR_HAND_SMALL = block("template_pocket_watch_hour_hand_small", TextureSlot.ALL);

    public static final ModelTemplate BORNHOLM_TOP_GLASS = block("template_bornholm_top_glass", TextureSlot.ALL);
    public static final ModelTemplate BORNHOLM_DOOR = block("template_bornholm_door", TextureSlot.ALL);
    public static final ModelTemplate GLASS_DIAL_SMALL = block("template_glass_dial_small", TextureSlot.ALL);

    public static final ModelTemplate PENDULUM_BLOCK = item("template_pendulum", TextureSlot.ALL);

    public static final ModelTemplate POCKET_WATCH_CLOSED = item("template_pocket_watch_closed", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_00 = item("template_pocket_watch_00", TextureSlot.ALL);

    public static final ModelTemplate POCKET_WATCH_OPEN_00 = item("template_pocket_watch_open_00", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_01 = item("template_pocket_watch_open_01", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_02 = item("template_pocket_watch_open_02", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_03 = item("template_pocket_watch_open_03", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_04 = item("template_pocket_watch_open_04", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_05 = item("template_pocket_watch_open_05", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_06 = item("template_pocket_watch_open_06", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_07 = item("template_pocket_watch_open_07", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_08 = item("template_pocket_watch_open_08", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_09 = item("template_pocket_watch_open_09", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_10 = item("template_pocket_watch_open_10", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_11 = item("template_pocket_watch_open_11", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_12 = item("template_pocket_watch_open_12", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_13 = item("template_pocket_watch_open_13", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_14 = item("template_pocket_watch_open_14", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_15 = item("template_pocket_watch_open_15", TextureSlot.ALL);

    private static ModelTemplate item(String itemModelLocation, TextureSlot... requiredSlots) {
        return new ModelTemplate(Optional.of(TextUtil.res("item/" + itemModelLocation)), Optional.empty(), requiredSlots);
    }

    private static ModelTemplate block(String parent, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(TextUtil.res("block/" + parent)), Optional.empty(), textureSlots);
    }
}
