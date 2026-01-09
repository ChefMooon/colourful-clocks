package com.chefmooon.colourfulclocks.client.renderer.neoforge;

import com.chefmooon.colourfulclocks.client.model.ColourfulClocksModels;
import com.chefmooon.colourfulclocks.client.renderer.HandbellBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.HandbellBlock;
import com.chefmooon.colourfulclocks.common.block.entity.HandbellBlockEntity;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class HandbellBlockEntityRendererImpl<T extends HandbellBlockEntity> extends HandbellBlockEntityRenderer implements BlockEntityRenderer<T> {
    public HandbellBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (level == null) return;

        BlockState state = level.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof HandbellBlock)) return;

        poseStack.pushPose();
        renderHandbell(blockEntity, poseStack, partialTick, state);

        ModelResourceLocation handbellLocation = new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.HANDBELL_PATH.formatted(level.getBlockState(blockEntity.getBlockPos()).getBlock().getDescriptionId().replace("block.colourfulclocks.", "").replace("_handbell", ""))), "standalone");
        BakedModel handbellModel = minecraft.getModelManager().getModel(handbellLocation);
        minecraft.getBlockRenderer().getModelRenderer().renderModel(
                poseStack.last(),
                bufferSource.getBuffer(RenderType.solid()),
                blockEntity.getBlockState(),
                handbellModel,
                1f, 1f, 1f,
                packedLight, packedOverlay);

        poseStack.popPose();
    }
}
