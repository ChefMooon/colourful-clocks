package com.chefmooon.colourfulclocks.client.renderer.neoforge;

import com.chefmooon.colourfulclocks.client.model.ColourfulClocksModels;
import com.chefmooon.colourfulclocks.client.renderer.TallMantelClockBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.MantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.TallMantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.TallMantelClockBlockEntity;
import com.chefmooon.colourfulclocks.common.data.PendulumComponent;
import com.chefmooon.colourfulclocks.common.data.PocketWatchComponent;
import com.chefmooon.colourfulclocks.common.data.types.BornholmTopGlassTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TallMantelClockBlockEntityRendererImpl extends TallMantelClockBlockEntityRenderer implements BlockEntityRenderer<TallMantelClockBlockEntity> {
    public TallMantelClockBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(TallMantelClockBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (level == null) return;

        BlockState state = level.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof TallMantelClockBlock)) return;

        PocketWatchTypes pocketWatchType = blockEntity.getData().pocketWatch().orElse(PocketWatchComponent.getDefaultValue()).getType();
        if (pocketWatchType != PocketWatchTypes.EMPTY) {
            renderPocketWatch(minecraft, pocketWatchType, state, blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
        }

        PendulumTypes pendulumType = blockEntity.getData().pendulum().orElse(PendulumComponent.getDefaultValue()).getType();
        if (pendulumType != PendulumTypes.EMPTY) {
            renderPendulum(minecraft, pendulumType, state, blockEntity, partialTick, poseStack, bufferSource, packedLight, packedOverlay);
        }

        if (blockEntity.getData().getGlassType().isPresent()) {
            poseStack.pushPose();
            poseStack.rotateAround(getRotation(state.getValue(MantelClockBlock.FACING)), 0.5F, 0.5F, 0.5F);
            poseStack.translate(0, 0.435F, 0.1875F);
            if (state.getValue(TallMantelClockBlock.WALL)) poseStack.translate(0, 0, -0.315F);
            BornholmTopGlassTypes glassType = blockEntity.getData().getGlassType().get();
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

    private void renderPocketWatch(Minecraft minecraft, PocketWatchTypes pocketWatchType, BlockState state, BlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
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

    private void renderPendulum(Minecraft minecraft, PendulumTypes pendulumType, BlockState state, BlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        poseStack.pushPose();
        ItemStack pendulum = new ItemStack(ColourfulClocksTypeUtil.getPendulumItemFromType(pendulumType));
        BakedModel model = minecraft.getItemRenderer().getModel(pendulum, blockEntity.getLevel(), null, 0);
        renderPendulum(poseStack, partialTick, state, pendulumType.getSwingSpeedModifier());
        minecraft.getBlockRenderer().getModelRenderer().renderModel(
                poseStack.last(),
                bufferSource.getBuffer(RenderType.cutout()),
                state,
                model,
                1f, 1f, 1f,
                packedLight, packedOverlay);
        poseStack.popPose();
    }
}
