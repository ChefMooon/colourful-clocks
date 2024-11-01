package com.chefmooon.colourfulclocks.common.block.state.properties;

import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Arrays;

public class ColourfulClocksBlockStateProperties {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public static final DoorTypeProperty BORNHOLM_DOOR_TYPE = DoorTypeProperty.create("door_type", Arrays.asList(BornholmDoorTypes.values()));
    public static final BornholmTopGlassTypeProperty BORNHOLM_TOP_GLASS_TYPE = BornholmTopGlassTypeProperty.create("door_type", Arrays.asList(BornholmTopGlassTypes.values()));
}
