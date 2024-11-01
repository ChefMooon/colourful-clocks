package com.chefmooon.colourfulclocks.common.block.state.properties;

import com.chefmooon.colourfulclocks.common.core.BornholmDoorTypes;
import com.google.common.collect.Lists;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DoorTypeProperty extends EnumProperty<BornholmDoorTypes> {
    protected DoorTypeProperty(String name, Collection<BornholmDoorTypes> values) {
        super(name, BornholmDoorTypes.class, values);
    }

    public static DoorTypeProperty create(String name, Predicate<BornholmDoorTypes> filter) {
        return create(name, (Collection<BornholmDoorTypes>) Arrays.stream(BornholmDoorTypes.values()).filter(filter).collect(Collectors.toList()));
    }

    public static DoorTypeProperty create(String name, BornholmDoorTypes... values) {
        return create(name, Lists.<BornholmDoorTypes>newArrayList(values));
    }

    public static DoorTypeProperty create(String name, Collection<BornholmDoorTypes> values) {
        return new DoorTypeProperty(name, values);
    }
}
