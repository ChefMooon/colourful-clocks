package com.chefmooon.colourfulclocks.common.block.state.properties;

import com.chefmooon.colourfulclocks.common.core.BornholmTopGlassTypes;
import com.google.common.collect.Lists;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BornholmTopGlassTypeProperty extends EnumProperty<BornholmTopGlassTypes> {
    protected BornholmTopGlassTypeProperty(String name, Collection<BornholmTopGlassTypes> values) {
        super(name, BornholmTopGlassTypes.class, values);
    }

    public static BornholmTopGlassTypeProperty create(String name, Predicate<BornholmTopGlassTypes> filter) {
        return create(name, (Collection<BornholmTopGlassTypes>) Arrays.stream(BornholmTopGlassTypes.values()).filter(filter).collect(Collectors.toList()));
    }

    public static BornholmTopGlassTypeProperty create(String name, BornholmTopGlassTypes... values) {
        return create(name, Lists.<BornholmTopGlassTypes>newArrayList(values));
    }

    public static BornholmTopGlassTypeProperty create(String name, Collection<BornholmTopGlassTypes> values) {
        return new BornholmTopGlassTypeProperty(name, values);
    }
}
