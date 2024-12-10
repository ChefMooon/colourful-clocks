package com.chefmooon.colourfulclocks.integration.wthit;

import com.chefmooon.colourfulclocks.common.util.TextUtil;
import mcp.mobius.waila.api.ICommonRegistrar;
import mcp.mobius.waila.api.IWailaCommonPlugin;
import net.minecraft.resources.ResourceLocation;

public class ColourfulClocksCommonWailaPlugin implements IWailaCommonPlugin {

    public static class Options {
        public static final ResourceLocation BORNHOLM_TRUNK_DOOR_TYPE = TextUtil.res("bornholm_trunk_door_type");
        public static final ResourceLocation BORNHOLM_DIAL_GLASS_TYPE = TextUtil.res("bornholm_dial_glass_type");
    }

    @Override
    public void register(ICommonRegistrar registrar) {
        registrar.localConfig(Options.BORNHOLM_TRUNK_DOOR_TYPE, true);
        registrar.localConfig(Options.BORNHOLM_DIAL_GLASS_TYPE, true);
    }
}
