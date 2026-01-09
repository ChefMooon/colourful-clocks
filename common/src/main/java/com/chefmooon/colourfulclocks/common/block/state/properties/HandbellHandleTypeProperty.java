package com.chefmooon.colourfulclocks.common.block.state.properties;

import com.chefmooon.colourfulclocks.common.data.types.HandbellHandleTypes;
import com.google.common.collect.Lists;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HandbellHandleTypeProperty extends EnumProperty<HandbellHandleTypes> {
    protected HandbellHandleTypeProperty(String name, Collection<HandbellHandleTypes> values) {
        super(name, HandbellHandleTypes.class, values);
    }

    public static HandbellHandleTypeProperty create(String name, Predicate<HandbellHandleTypes> filter) {
        return create(name, (Collection<HandbellHandleTypes>) Arrays.stream(HandbellHandleTypes.values()).filter(filter).collect(Collectors.toList()));
    }

    public static HandbellHandleTypeProperty create(String name, HandbellHandleTypes... values) {
        return create(name, Lists.<HandbellHandleTypes>newArrayList(values));
    }

    public static HandbellHandleTypeProperty create(String name, Collection<HandbellHandleTypes> values) {
        return new HandbellHandleTypeProperty(name, values);
    }
}
