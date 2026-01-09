package com.chefmooon.colourfulclocks.common.block;

import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksBlocks;
import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public interface WeatheringCopperHandbell extends ChangeOverTimeBlock<WeatheringCopper.WeatherState> {
    Supplier<ImmutableBiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> buildNextByBlock().build());
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> ((BiMap)NEXT_BY_BLOCK.get()).inverse());
    Supplier<ImmutableBiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> buildWaxables().build());
    Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> ((BiMap)WAXABLES.get()).inverse());

    private static ImmutableBiMap.Builder<Block, Block> buildNextByBlock() {
        ImmutableBiMap.Builder<Block, Block> builder = ImmutableBiMap.builder();
        builder.put(BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.COPPER_HANDBELL), BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.EXPOSED_COPPER_HANDBELL));
        builder.put(BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.EXPOSED_COPPER_HANDBELL), BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.WEATHERED_COPPER_HANDBELL));
        builder.put(BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.WEATHERED_COPPER_HANDBELL), BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.OXIDIZED_COPPER_HANDBELL));
        return  builder;
    }

    private static ImmutableBiMap.Builder<Block, Block> buildWaxables() {
        ImmutableBiMap.Builder<Block, Block> builder = ImmutableBiMap.builder();
        builder.put(BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.COPPER_HANDBELL), BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.WAXED_COPPER_HANDBELL));
        builder.put(BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.EXPOSED_COPPER_HANDBELL), BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.WAXED_EXPOSED_COPPER_HANDBELL));
        builder.put(BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.WEATHERED_COPPER_HANDBELL), BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.WAXED_WEATHERED_COPPER_HANDBELL));
        builder.put(BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.OXIDIZED_COPPER_HANDBELL), BuiltInRegistries.BLOCK.get(ColourfulClocksBlocks.WAXED_OXIDIZED_COPPER_HANDBELL));
        return builder;
    }

    static Optional<Block> getPrevious(Block block) {
        return Optional.ofNullable((Block)((BiMap)PREVIOUS_BY_BLOCK.get()).get(block));
    }

    static Block getFirst(Block block) {
        Block block2 = block;

        for(Block block3 = (Block)((BiMap)PREVIOUS_BY_BLOCK.get()).get(block); block3 != null; block3 = (Block)((BiMap)PREVIOUS_BY_BLOCK.get()).get(block3)) {
            block2 = block3;
        }

        return block2;
    }

    public static Optional<BlockState> getWaxed(BlockState blockState) {
        return Optional.ofNullable(WAXABLES.get().get(blockState.getBlock())).map((block) -> block.withPropertiesOf(blockState));
    }

    public static Optional<BlockState> getUnwaxed(BlockState blockState) {
        return Optional.ofNullable(WAX_OFF_BY_BLOCK.get().get(blockState.getBlock())).map((block) -> block.withPropertiesOf(blockState));
    }

    static Optional<BlockState> getPrevious(BlockState state) {
        return getPrevious(state.getBlock()).map((block) -> block.withPropertiesOf(state));
    }

    static Optional<Block> getNext(Block block) {
        return Optional.ofNullable((Block)((BiMap)NEXT_BY_BLOCK.get()).get(block));
    }

    static BlockState getFirst(BlockState state) {
        return getFirst(state.getBlock()).withPropertiesOf(state);
    }

    @Override
    default Optional<BlockState> getNext(BlockState state) {
        return getNext(state.getBlock()).map((block) -> block.withPropertiesOf(state));
    }

    default float getChanceModifier() {
        return this.getAge() == WeatheringCopper.WeatherState.UNAFFECTED ? 0.75F : 1.0F;
    }

    @Override
    default Optional<BlockState> getNextState(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int i = this.getAge().ordinal();
        int j = 0;
        int k = 0;

        for(BlockPos blockPos : BlockPos.withinManhattan(pos, 4, 4, 4)) {
            int l = blockPos.distManhattan(pos);
            if (l > 4) {
                break;
            }

            if (!blockPos.equals(pos)) {
                Block block = level.getBlockState(blockPos).getBlock();
                if (block instanceof ChangeOverTimeBlock<?> changeOverTimeBlock && !(block instanceof WeatheringCopperHandbellBlock)) {
                    Enum<?> enum_ = changeOverTimeBlock.getAge();
                    if (this.getAge().getClass() == enum_.getClass()) {
                        int m = enum_.ordinal();
                        if (m < i) {
                            return Optional.empty();
                        }

                        if (m > i) {
                            ++k;
                        } else {
                            ++j;
                        }
                    }
                }
            }
        }

        float f = (float)(k + 1) / (float)(k + j + 1);
        float g = f * f * this.getChanceModifier();
        boolean shouldWeather = random.nextFloat() < g;
        return shouldWeather ? this.getNext(state) : Optional.empty();
    }
}
