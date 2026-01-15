package com.chefmooon.colourfulclocks.client.renderer.neoforge;

import com.chefmooon.colourfulclocks.client.model.ColourfulClocksModels;
import com.chefmooon.colourfulclocks.client.renderer.AlarmClockBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.AlarmClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.AlarmClockBlockEntity;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
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
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AlarmClockBlockEntityRendererImpl<T extends AlarmClockBlockEntity> extends AlarmClockBlockEntityRenderer implements BlockEntityRenderer<T> {
    public AlarmClockBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (level == null) return;

        BlockState state = level.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof AlarmClockBlock)) return;

        PocketWatchTypes pocketWatchType = blockEntity.getData().pocketWatchType().orElse(PocketWatchTypes.EMPTY);
        if (pocketWatchType != PocketWatchTypes.EMPTY) {
            poseStack.pushPose();
            renderMinuteHand(poseStack, partialTick, state);

            ModelResourceLocation minuteHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.MINUTE_HAND_SMALL_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath())), "standalone");
            BakedModel minuteHandModel = minecraft.getModelManager().getModel(minuteHandLocation);
            minecraft.getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.cutout()),
                    blockEntity.getBlockState(),
                    minuteHandModel,
                    1f, 1f, 1f,
                    packedLight, packedOverlay);

            renderHourHand(poseStack, partialTick, state);

            ModelResourceLocation hourHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.HOUR_HAND_SMALL_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath())), "standalone");
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

        if (blockEntity.getData().leftBell().isPresent()) {
            Direction facing = state.getValue(AlarmClockBlock.FACING);
            poseStack.pushPose();
            translateBell(poseStack, facing, false);

            ModelResourceLocation rightBellLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.ALARM_CLOCK_BELL_PATH.formatted(blockEntity.getData().leftBell().get().getSerializedName())), "standalone");
            BakedModel leftBellModel = minecraft.getModelManager().getModel(rightBellLocation);
            minecraft.getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.translucent()),
                    blockEntity.getBlockState(),
                    leftBellModel,
                    1f, 1f, 1f,
                    packedLight, packedOverlay);
            poseStack.popPose();
        }

        if (blockEntity.getData().rightBell().isPresent()) {
            Direction facing = state.getValue(AlarmClockBlock.FACING);
            poseStack.pushPose();
            translateBell(poseStack, facing, true);

            ModelResourceLocation rightBellLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.ALARM_CLOCK_BELL_PATH.formatted(blockEntity.getData().rightBell().get().getSerializedName())), "standalone");
            BakedModel rightBellModel = minecraft.getModelManager().getModel(rightBellLocation);
            minecraft.getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.translucent()),
                    blockEntity.getBlockState(),
                    rightBellModel,
                    1f, 1f, 1f,
                    packedLight, packedOverlay);
            poseStack.popPose();
        }

        if (blockEntity.getData().glassType().isPresent()) {
            poseStack.pushPose();
            poseStack.rotateAround(getRotation(state.getValue(AlarmClockBlock.FACING)), 0.5F, 0.5F, 0.5F);
            poseStack.translate(0, 0, 0.1875F);
            BornholmTopGlassTypes glassType = blockEntity.getData().glassType().get();
            BakedModel glassModel = minecraft.getModelManager().getModel(new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.DIAL_SMALL_PATH.formatted(glassType.getName())), "standalone"));
            minecraft.getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.translucent()),
                    blockEntity.getBlockState(),
                    glassModel,
                    1f, 1f, 1f,
                    packedLight, packedOverlay);
            poseStack.popPose();
        }
    }
}
