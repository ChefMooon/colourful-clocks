package com.chefmooon.colourfulclocks.client.renderer;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;

public class BornholmMiddleBlockEntityRenderer {


    public static void renderPendelum(PoseStack poseStack, float partialTick, BlockState state, float swingSpeedModifier) {
        poseStack.pushPose();

        poseStack.translate(0.5F, 1.0F, 0.5F);
        poseStack.mulPose(getRotation(state.getValue(BornholmMiddleBlock.FACING)));

        if (state.getValue(BornholmMiddleBlock.ACTIVATED)) poseStack.mulPose(Axis.ZP.rotation(getPendelumRotation(partialTick, swingSpeedModifier)));

        poseStack.translate(0.0F, -0.5F, 0.0F);
    }

    // todo - fix rotation , still a small skip at the end of some animations
    public static float getPendelumRotation(float partialTick, float swingSpeedModifier) {
        float time = (getTime(swingSpeedModifier) + partialTick) / 25.0F;
        return (float) (Math.sin(time) * 0.05F);
    }

    public static int getTime(float swingSpeedModifier) {
        return (int) (Minecraft.getInstance().level.getGameTime() % (314 / swingSpeedModifier));
    }

    public static Quaternionf getRotation(Direction direction) {
        Quaternionf result = null;
        switch (direction) {
            case Direction.NORTH -> result = Axis.YN.rotationDegrees(0);
            case Direction.EAST -> result = Axis.YN.rotationDegrees(90);
            case Direction.SOUTH -> result = Axis.YN.rotationDegrees(180);
            case Direction.WEST -> result = Axis.YN.rotationDegrees(-90);
        }
        return result;
    }
}
