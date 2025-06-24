package com.chefmooon.colourfulclocks.client.renderer.neoforge;

import com.chefmooon.colourfulclocks.client.model.ColourfulClocksModels;
import com.chefmooon.colourfulclocks.client.renderer.BornholmMiddleBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.block.entity.neoforge.BornholmMiddleBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.data.types.BornholmDoorTypes;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.chefmooon.colourfulclocks.common.util.TextUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


public class BornholmMiddleBlockEntityRendererImpl extends BornholmMiddleBlockEntityRenderer implements BlockEntityRenderer<BornholmMiddleBlockEntityImpl>  {
    public BornholmMiddleBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(BornholmMiddleBlockEntityImpl blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (level == null) return;

        BlockState state = level.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof BornholmMiddleBlock)) return;

        PendulumTypes pendulumType = blockEntity.getTrunkData().getPendulumType();
        if (pendulumType != PendulumTypes.EMPTY) {
            poseStack.pushPose();
            ItemStack pendulum = new ItemStack(ColourfulClocksTypeUtil.getPendulumItemFromType(pendulumType));
            BakedModel model = minecraft.getItemRenderer().getModel(pendulum, level, null, 0);
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

        BornholmDoorTypes doorType = blockEntity.getTrunkData().getDoorType();
        if (doorType != BornholmDoorTypes.BASE) {
            poseStack.pushPose();
            poseStack.rotateAround(getRotation(state.getValue(BornholmMiddleBlock.FACING)), 0.5F, 0.5F, 0.5F);
            BakedModel doorModel = minecraft.getModelManager().getModel(new ModelResourceLocation(TextUtil.res(ColourfulClocksModels.BORNHOLM_DOOR_PATH.formatted(doorType.getName())), "standalone"));
            if (state.getValue(BornholmMiddleBlock.OPEN)) poseStack.rotateAround(Axis.YN.rotationDegrees(135), 0.3125F, 0.125F, 0.75F);
            minecraft.getBlockRenderer().getModelRenderer().tesselateBlock(
                    level, doorModel, state,
                    blockEntity.getBlockPos(), poseStack, bufferSource.getBuffer(RenderType.translucent()),
                false, level.getRandom(),
                    packedLight, packedOverlay);
            poseStack.popPose();
        }
    }
}
