package com.chefmooon.colourfulclocks.client.renderer.neoforge;

import com.chefmooon.colourfulclocks.client.model.ColourfulClocksModels;
import com.chefmooon.colourfulclocks.client.renderer.WallClockBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.WallClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.WallClockBlockEntity;
import com.chefmooon.colourfulclocks.common.block.properties.WallClockPartProperty;
import com.chefmooon.colourfulclocks.common.data.PocketWatchComponent;
import com.chefmooon.colourfulclocks.common.data.types.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WallClockBlockEntityRendererImpl<T extends WallClockBlockEntity> extends WallClockBlockEntityRenderer implements BlockEntityRenderer<T> {
    public WallClockBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }
    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (level == null) return;

        BlockState state = level.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof WallClockBlock)) return;

        PocketWatchTypes pocketWatchType = blockEntity.getData().pocketWatch().orElse(PocketWatchComponent.getDefaultValue()).getType();
        if (pocketWatchType != PocketWatchTypes.EMPTY) {
            poseStack.pushPose();
            renderMinuteHand(poseStack, partialTick, state);

            ModelResourceLocation minuteHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.MINUTE_HAND_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath())), "standalone");
            if (state.getValue(WallClockBlock.PART) == WallClockPartProperty.TOP_RIGHT) {
                minuteHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.MINUTE_HAND_LARGE_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath())), "standalone");
            } else if (state.getValue(WallClockBlock.PART) == WallClockPartProperty.CENTER) {
                minuteHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.MINUTE_HAND_XL_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath())), "standalone");
            }
            BakedModel minuteHandModel = minecraft.getModelManager().getModel(minuteHandLocation);
            minecraft.getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.cutout()),
                    blockEntity.getBlockState(),
                    minuteHandModel,
                    1f, 1f, 1f,
                    packedLight, packedOverlay);

            renderHourHand(poseStack, partialTick, state);

            ModelResourceLocation hourHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.HOUR_HAND_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath())), "standalone");
            if (state.getValue(WallClockBlock.PART) == WallClockPartProperty.BOTTOM_LEFT) {
                hourHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.HOUR_HAND_LARGE_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath())), "standalone");
            } else if (state.getValue(WallClockBlock.PART) == WallClockPartProperty.CENTER) {
                hourHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.HOUR_HAND_XL_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath())), "standalone");
            }
            BakedModel hourHandModel = minecraft.getModelManager().getModel(hourHandLocation);
            minecraft.getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.cutout()),
                    blockEntity.getBlockState(),
                    hourHandModel,
                    1f, 1f, 1f,
                    packedLight, packedOverlay);
            poseStack.popPose();
        }
    }
}
