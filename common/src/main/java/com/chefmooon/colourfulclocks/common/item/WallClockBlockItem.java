package com.chefmooon.colourfulclocks.common.item;

import com.chefmooon.colourfulclocks.common.block.properties.WallClockPartProperty;
import com.chefmooon.colourfulclocks.common.data.WallClockComponent;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.data.types.WallClockType;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksDataComponentTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;

import java.util.List;

public class WallClockBlockItem extends BlockItem {
    public WallClockBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (stack.has(ColourfulClocksDataComponentTypes.getWallClockData())) {
            WallClockComponent component = stack.getOrDefault(ColourfulClocksDataComponentTypes.getWallClockData(), WallClockComponent.getDefaultValue());
            tooltipComponents.add(TextUtil.getTranslatable("tooltip.wall_clock_type." + component.type().getSerializedName()));
            if (component.getPocketWatch().isPresent()) {
                Item pocketWatchItem = ColourfulClocksTypeUtil.getPocketWatchItemFromType(component.getPocketWatch().get().getType());
                if (pocketWatchItem != PocketWatchTypes.EMPTY.getItem()) {
                    tooltipComponents.add(Component.translatable(pocketWatchItem.getDescriptionId()));
                }
            }
            if (component.isTicking().isPresent() && component.isTicking().get()) {
                tooltipComponents.add(TextUtil.getTranslatable("tooltip.ticking"));
            }
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public boolean canPlace(BlockPlaceContext context, BlockState state) {
        Player player = context.getPlayer();
        Direction facing = context.getHorizontalDirection();
        CollisionContext collisionContext = player == null ? CollisionContext.empty() : CollisionContext.of(player);
        ItemStack itemStack = context.getItemInHand();
        Level level = context.getLevel();

        if (itemStack.has(ColourfulClocksDataComponentTypes.getWallClockData())) {
            WallClockType type = itemStack.get(ColourfulClocksDataComponentTypes.getWallClockData()).type();

            BlockPos base = context.getClickedPos();
            java.util.Set<WallClockPartProperty> parts;

            if (type == WallClockType.LARGE) {
                parts = WallClockPartProperty.largeParts();
            } else if (type == WallClockType.MEDIUM) {
                base = base.above().relative(facing.getClockWise()); // medium controller is at top-right; adjust base accordingly
                parts = WallClockPartProperty.mediumParts();
            } else {
                return super.canPlace(context, state);
            }

            if (!canPlaceParts(level, base, facing, state, collisionContext, parts)) {
                sendInvalidPlaceMessage(player);
                return false;
            }
        }

        return (!this.mustSurvive() || state.canSurvive(context.getLevel(), context.getClickedPos()))
                && context.getLevel().isUnobstructed(state, context.getClickedPos(), collisionContext);
    }

    private boolean canPlaceParts(Level level, BlockPos base, Direction facing, BlockState state, CollisionContext collisionContext, java.util.Set<WallClockPartProperty> parts) {
        for (WallClockPartProperty part : parts) {
            BlockPos checkPos = offsetPosForPart(base, facing, part);

            BlockState existing = level.getBlockState(checkPos);
            boolean replaceable = level.isEmptyBlock(checkPos) || existing.canBeReplaced();

            boolean valid = replaceable
                    && (!this.mustSurvive() || state.canSurvive(level, checkPos))
                    && level.isUnobstructed(state, checkPos, collisionContext);

            if (!valid) {
                return false;
            }
        }
        return true;
    }

    private BlockPos offsetPosForPart(BlockPos base, Direction facing, WallClockPartProperty part) {
        int xOffset = part.getxOffset();
        int yOffset = part.getyOffset();

        BlockPos pos = base;
        if (xOffset != 0) {
            Direction horizontal = xOffset > 0 ? facing.getClockWise() : facing.getCounterClockWise();
            pos = pos.relative(horizontal, Math.abs(xOffset));
        }
        if (yOffset != 0) {
            pos = pos.above(yOffset);
        }
        return pos;
    }

    private void sendInvalidPlaceMessage(Player player) {
        if (player != null) {
            player.displayClientMessage(TextUtil.getTranslatable("tooltip.invalid_wall_clock_placement"), true);
        }
    }
}
