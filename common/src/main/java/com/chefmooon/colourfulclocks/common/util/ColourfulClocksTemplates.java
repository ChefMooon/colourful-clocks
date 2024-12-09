package com.chefmooon.colourfulclocks.common.util;

import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;

import java.util.Optional;

public class ColourfulClocksTemplates {

    public static final ModelTemplate HANDHELD_POCKET_WATCH = createItem("template_pocket_watch_open", TextureSlot.LAYER0);

    public static final ModelTemplate BORNHOLM_BASE = block("template_bornholm_base", TextureSlot.LAYER0);
    public static final ModelTemplate BORNHOLM_MIDDLE = block("template_bornholm_middle",
            TextureSlot.SIDE, TextureSlot.INSIDE, ColourfulClocksTextureSlots.DOOR);
    public static final ModelTemplate BORNHOLM_MIDDLE_OPEN = block("template_bornholm_middle_open",
            TextureSlot.SIDE, TextureSlot.INSIDE, ColourfulClocksTextureSlots.DOOR);
    public static final ModelTemplate BORNHOLM_MIDDLE_NO_DOOR = block("template_bornholm_middle_no_door",
            TextureSlot.SIDE, TextureSlot.INSIDE, ColourfulClocksTextureSlots.DOOR);
    public static final ModelTemplate BORNHOLM_TOP = block("template_bornholm_top",
        TextureSlot.SIDE, ColourfulClocksTextureSlots.CLOCK_DIAL, ColourfulClocksTextureSlots.CLOCK_DIAL_COVER,
            ColourfulClocksTextureSlots.CLOCK_DIAL_MARKS);
    public static final ModelTemplate BORNHOLM_CLOCK_DIAL_HOUR = block("template_bornholm_clock_dial_hour", TextureSlot.ALL);

    public static final ModelTemplate POCKET_WATCH_CLOCK = block("template_pocket_watch_in_clock", TextureSlot.ALL);

    public static final ModelTemplate PENDULUM_BLOCK = createItem("template_pendulum", TextureSlot.ALL);

    public static final ModelTemplate POCKET_WATCH_CLOSED = createItem("template_pocket_watch_closed", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_00 = createItem("template_pocket_watch_00", TextureSlot.ALL);

    public static final ModelTemplate POCKET_WATCH_OPEN_00 = createItem("template_pocket_watch_open_00", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_01 = createItem("template_pocket_watch_open_01", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_02 = createItem("template_pocket_watch_open_02", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_03 = createItem("template_pocket_watch_open_03", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_04 = createItem("template_pocket_watch_open_04", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_05 = createItem("template_pocket_watch_open_05", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_06 = createItem("template_pocket_watch_open_06", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_07 = createItem("template_pocket_watch_open_07", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_08 = createItem("template_pocket_watch_open_08", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_09 = createItem("template_pocket_watch_open_09", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_10 = createItem("template_pocket_watch_open_10", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_11 = createItem("template_pocket_watch_open_11", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_12 = createItem("template_pocket_watch_open_12", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_13 = createItem("template_pocket_watch_open_13", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_14 = createItem("template_pocket_watch_open_14", TextureSlot.ALL);
    public static final ModelTemplate POCKET_WATCH_OPEN_15 = createItem("template_pocket_watch_open_15", TextureSlot.ALL);


    private static ModelTemplate createItem(String itemModelLocation, TextureSlot... requiredSlots) {
        return new ModelTemplate(Optional.of(TextUtil.res("item/" + itemModelLocation)), Optional.empty(), requiredSlots);
    }

    private static ModelTemplate block(String parent, TextureSlot... textureSlots) {
        return new ModelTemplate(Optional.of(TextUtil.res("block/" + parent)), Optional.empty(), textureSlots);
    }
}
