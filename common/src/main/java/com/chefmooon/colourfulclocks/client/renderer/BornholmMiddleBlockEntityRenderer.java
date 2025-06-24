package com.chefmooon.colourfulclocks.client.renderer;

import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;

public class BornholmMiddleBlockEntityRenderer {
    private static final double MAX_SWING_ANGLE = 4.0;

    public static void renderPendulum(PoseStack poseStack, float partialTick, BlockState state, float swingSpeedModifier) {
        poseStack.rotateAround(getRotation(state.getValue(BornholmMiddleBlock.FACING)), 0.5F, 0.5F, 0.5F);

        if (state.getValue(BornholmMiddleBlock.ACTIVATED)) {
            poseStack.rotateAround(Axis.ZP.rotation((float) Math.toRadians(getPendulumRotation(partialTick, swingSpeedModifier))), 0.5F, 0.9F, 0.5F);
        }
    }

    public static float getPendulumRotation(float partialTick, float swingSpeedModifier) {
        long gameTime = Minecraft.getInstance().level.getGameTime();
        double timeInRadians = ((gameTime + partialTick) * swingSpeedModifier * Math.PI) / 100.0;

        return (float) (Math.sin(timeInRadians) * MAX_SWING_ANGLE);
    }

    public static Quaternionf getRotation(Direction direction) {
        return switch (direction) {
            case NORTH -> Axis.YN.rotationDegrees(0);
            case EAST -> Axis.YN.rotationDegrees(90);
            case SOUTH -> Axis.YN.rotationDegrees(180);
            case WEST -> Axis.YN.rotationDegrees(-90);
            default -> Axis.YN.rotationDegrees(0);
        };
    }
}
