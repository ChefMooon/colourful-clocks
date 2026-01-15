package com.chefmooon.colourfulclocks.client.renderer;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;

public class AlarmClockBlockEntityRenderer {
    public static void renderMinuteHand(PoseStack poseStack, float partialTick, BlockState state) {
        poseStack.rotateAround(getRotation(state.getValue(BornholmTopBlock.FACING)), 0.5F, 0.5F, 0.5F);
        poseStack.translate(0.5F, 0.312F, 0.64F);
        poseStack.mulPose(Axis.ZN.rotationDegrees(getMinuteHandRotation(partialTick)));
    }

    public static void renderHourHand(PoseStack poseStack, float partialTick, BlockState state) {
        poseStack.translate(0, 0, -0.006F);
        poseStack.mulPose(Axis.ZN.rotationDegrees(-getMinuteHandRotation(partialTick)));
        poseStack.mulPose(Axis.ZN.rotationDegrees(getHourHandRotation(partialTick)));
    }

    public static float getMinuteHandRotation(float partialTick) {
        float timeOfDay = (Minecraft.getInstance().level.getDayTime() + partialTick) % 24000;
        float segmentTime = timeOfDay % 750.0F;
        int step = (int)(segmentTime / (750.0F / 16.0F));
        return step * 22.5F;
    }

    public static float getHourHandRotation(float partialTick) {
        float timeOfDay = (Minecraft.getInstance().level.getDayTime() + partialTick) % 24000;
        float twelveHourTime = (timeOfDay + 18000) % 24000;
        float segmentTime = twelveHourTime % 12000.0F;
        int step = (int)(segmentTime / 750.0F);
        return step * 22.5F;
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

    public static void translateBell(PoseStack poseStack, Direction direction, boolean left) {
        float offset = left ? -0.1F : 0.1F;
        switch (direction) {
            case NORTH -> poseStack.translate(offset, -0.005F, 0.0F);
            case SOUTH -> poseStack.translate(-offset, -0.005F, 0.0F);
            case EAST -> poseStack.translate(0.0F, -0.005F, offset);
            case WEST -> poseStack.translate(0.0F, -0.005F, -offset);
        }
        poseStack.rotateAround(getBellRotation(direction, left), 0.5F, 0.5F, 0.5F);
    }

    public static Quaternionf getBellRotation(Direction direction, boolean left) {
        float angle = left ? -22.5F : 22.5F;
        Quaternionf result = null;
        switch (direction) {
            case Direction.NORTH -> result = Axis.ZN.rotationDegrees(angle);
            case Direction.SOUTH -> result = Axis.ZN.rotationDegrees(-angle);
            case Direction.EAST -> result = Axis.XN.rotationDegrees(-angle);
            case Direction.WEST -> result = Axis.XN.rotationDegrees(angle);
        }
        return result;
    }
}
