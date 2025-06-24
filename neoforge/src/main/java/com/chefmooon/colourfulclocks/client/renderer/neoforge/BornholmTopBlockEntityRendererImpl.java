package com.chefmooon.colourfulclocks.client.renderer.neoforge;

import com.chefmooon.colourfulclocks.client.model.ColourfulClocksModels;
import com.chefmooon.colourfulclocks.client.renderer.BornholmTopBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.entity.neoforge.BornholmTopBlockEntityImpl;
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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BornholmTopBlockEntityRendererImpl extends BornholmTopBlockEntityRenderer implements BlockEntityRenderer<BornholmTopBlockEntityImpl> {
    public BornholmTopBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(BornholmTopBlockEntityImpl blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level world = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (world == null) return;

        BlockState state = world.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof BornholmTopBlock)) return;

        PocketWatchTypes pocketWatchType = blockEntity.getDialData().getPocketWatchType();
        if (pocketWatchType != PocketWatchTypes.EMPTY) {
            poseStack.pushPose();
            renderMinuteHand(poseStack, partialTick, state);

            ModelResourceLocation minuteHandLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.MINUTE_HAND_PATH.formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath())), "standalone");
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

        poseStack.pushPose();
        poseStack.rotateAround(getRotation(state.getValue(BornholmTopBlock.FACING)), 0.5F, 0.5F, 0.5F);
        BornholmTopGlassTypes glassType = blockEntity.getDialData().getGlassType();
        BakedModel glassModel = minecraft.getModelManager().getModel(new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.BORNHOLM_DIAL_PATH.formatted(glassType.getName())), "standalone"));
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
