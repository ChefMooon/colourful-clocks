package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.data.types.HandbellTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

public class WeatheringCopperHandbellBlock extends HandbellBlock implements WeatheringCopperHandbell {
    private final WeatheringCopper.WeatherState weatherState;
    public WeatheringCopperHandbellBlock(WeatheringCopper.WeatherState weatherState, HandbellTypes type, Properties properties) {
        super(type, properties);
        this.weatherState = weatherState;
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.changeOverTime(state, level, pos, random);
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return WeatheringCopperHandbell.getNext(state.getBlock()).isPresent();
    }

    @Override
    public WeatheringCopper.WeatherState getAge() {
        return this.weatherState;
    }
}
