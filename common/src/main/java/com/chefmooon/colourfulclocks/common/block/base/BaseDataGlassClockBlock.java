package com.chefmooon.colourfulclocks.common.block.base;

import com.chefmooon.colourfulclocks.common.block.entity.base.BaseDataClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.state.properties.BornholmTopGlassTypeProperty;
import com.chefmooon.colourfulclocks.common.block.state.properties.ColourfulClocksBlockStateProperties;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.ClockTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksAdvancements;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksSounds;
import com.chefmooon.colourfulclocks.common.tag.ColourfulClocksTags;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class BaseDataGlassClockBlock extends BaseDataClockBlock{
    public static final BornholmTopGlassTypeProperty GLASS_TYPE = ColourfulClocksBlockStateProperties.BORNHOLM_TOP_GLASS_TYPE;
    public BaseDataGlassClockBlock(Properties properties) {
        super(properties);
    }

    protected BaseDataGlassClockBlock(ClockTypes clockType, Properties properties) {
        super(clockType, properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(GLASS_TYPE, BornholmTopGlassTypes.GLASS));
    }

    @Override
    protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(GLASS_TYPE);
    }

    protected ItemInteractionResult setGlassType(Level level, BlockState state, BlockPos pos, Player player, ItemStack itemStack, BaseDataClockBlockEntity baseDataClockBlockEntity) {
        if (itemStack.is(ColourfulClocksTags.CLOCK_TOP_GLASS)) {
            if (itemStack.is(state.getValue(GLASS_TYPE).getItem())) return ItemInteractionResult.CONSUME;
            BornholmTopGlassTypes newBornholmTopGlassTypes = ColourfulClocksTypeUtil.getBornholmTopGlassTypeFromItem(itemStack.getItem());
            baseDataClockBlockEntity.setGlassType(newBornholmTopGlassTypes);
            level.setBlockAndUpdate(pos, state.setValue(GLASS_TYPE, newBornholmTopGlassTypes));
            level.playSound(player, pos, ColourfulClocksSounds.BLOCK_BORNHOLM_CHANGE_GLASS.get(), SoundSource.BLOCKS, 1.0F, 0.8F);
            if (!player.getAbilities().instabuild) itemStack.shrink(1);
            if (player instanceof ServerPlayer serverPlayer) ColourfulClocksAdvancements.GLASS_CHANGE_TRIGGER.get().trigger(serverPlayer);

            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
