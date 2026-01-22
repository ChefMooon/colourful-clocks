package com.chefmooon.colourfulclocks.client.renderer.fabric;

import com.chefmooon.colourfulclocks.client.model.ColourfulClocksModels;
import com.chefmooon.colourfulclocks.client.renderer.MantelClockBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.MantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.MantelClockBlockEntity;
import com.chefmooon.colourfulclocks.common.data.PocketWatchComponent;
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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MantelClockBlockEntityRendererImpl<T extends MantelClockBlockEntity> extends MantelClockBlockEntityRenderer implements BlockEntityRenderer<T> {
    public MantelClockBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (level == null) return;

        BlockState state = level.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof MantelClockBlock)) return;

        PocketWatchTypes pocketWatchType = blockEntity.getData().pocketWatch().orElse(PocketWatchComponent.getDefaultValue()).getType();
        if (pocketWatchType != PocketWatchTypes.EMPTY) {
            poseStack.pushPose();
            renderMinuteHand(poseStack, partialTick, state);

            ResourceLocation minuteHandLocation = TextUtil.res(ColourfulClocksModels.MINUTE_HAND_SMALL_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath()));
            BakedModel minuteHandModel = minecraft.getModelManager().getModel(minuteHandLocation);
            minecraft.getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.cutout()),
                    blockEntity.getBlockState(),
                    minuteHandModel,
                    1f, 1f, 1f,
                    packedLight, packedOverlay);

            renderHourHand(poseStack, partialTick, state);

            ResourceLocation hourHandLocation = TextUtil.res(ColourfulClocksModels.HOUR_HAND_SMALL_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath()));
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

        if (blockEntity.getData().getGlassType().isPresent()) {
            poseStack.pushPose();
            poseStack.rotateAround(getRotation(state.getValue(MantelClockBlock.FACING)), 0.5F, 0.5F, 0.5F);
            poseStack.translate(0, 0, 0.1875F);
            BornholmTopGlassTypes glassType = blockEntity.getData().getGlassType().get();
            BakedModel glassModel = minecraft.getModelManager().getModel(TextUtil.res(ColourfulClocksModels.DIAL_SMALL_PATH.formatted(glassType.getName())));
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
