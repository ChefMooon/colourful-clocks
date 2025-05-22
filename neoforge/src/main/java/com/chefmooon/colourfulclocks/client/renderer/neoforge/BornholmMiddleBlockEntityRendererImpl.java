package com.chefmooon.colourfulclocks.client.renderer.neoforge;

import com.chefmooon.colourfulclocks.client.renderer.BornholmMiddleBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.block.entity.neoforge.BornholmMiddleBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.data.types.PendulumTypes;
import com.chefmooon.colourfulclocks.common.util.ColourfulClocksTypeUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;


public class BornholmMiddleBlockEntityRendererImpl extends BornholmMiddleBlockEntityRenderer implements BlockEntityRenderer<BornholmMiddleBlockEntityImpl>  {
    public BornholmMiddleBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(BornholmMiddleBlockEntityImpl blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (level == null || blockEntity.isEmpty()) return;

        BlockState state = level.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof BornholmMiddleBlock)) return;

        PendulumTypes pendulumType = blockEntity.getTrunkData().getPendulumType();
        if (pendulumType != PendulumTypes.EMPTY) {
            ItemStack pendulum = new ItemStack(ColourfulClocksTypeUtil.getPendulumItemFromType(pendulumType));
            BakedModel model = minecraft.getItemRenderer().getModel(pendulum, level, null, 0);
            renderPendulum(poseStack, partialTick, state, pendulumType.getSwingSpeedModifier());
            minecraft.getItemRenderer().render(pendulum, ItemDisplayContext.FIXED, false, poseStack, bufferSource, packedLight, packedOverlay, model);
            poseStack.popPose();
        }
    }
}
