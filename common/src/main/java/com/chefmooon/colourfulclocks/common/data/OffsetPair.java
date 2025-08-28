package com.chefmooon.colourfulclocks.common.data;

public class OffsetPair {
    private final OffsetRecord first;
    private final OffsetRecord second;

    public OffsetPair(OffsetRecord first, OffsetRecord second) {
        this.first = first;
        this.second = second;
    }

    public OffsetRecord first() {
        return first;
    }

    public OffsetRecord second() {
        return second;
    }
}
