package com.chefmooon.colourfulclocks.common.block.state.properties;

import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.HandbellHandleTypes;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.Arrays;

public class ColourfulClocksBlockStateProperties {
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    public static final BooleanProperty CAN_TICK = BooleanProperty.create("can_tick");
    public static final BooleanProperty TICKING = BooleanProperty.create("ticking");
    public static final DoorTypeProperty BORNHOLM_DOOR_TYPE = DoorTypeProperty.create("door_type", Arrays.asList(BornholmDoorTypes.values()));
    public static final BornholmTopGlassTypeProperty BORNHOLM_TOP_GLASS_TYPE = BornholmTopGlassTypeProperty.create("glass_type", Arrays.asList(BornholmTopGlassTypes.values()));
    public static final HandbellHandleTypeProperty HANDBELL_HANDLE_TYPE = HandbellHandleTypeProperty.create("handle_type", Arrays.asList(HandbellHandleTypes.values()));
}
