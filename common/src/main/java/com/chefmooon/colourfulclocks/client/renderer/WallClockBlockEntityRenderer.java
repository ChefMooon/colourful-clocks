package com.chefmooon.colourfulclocks.client.renderer;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.WallClockBlock;
import com.chefmooon.colourfulclocks.common.block.properties.WallClockPartProperty;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;

public class WallClockBlockEntityRenderer {
    public static void renderMinuteHand(PoseStack poseStack, float partialTick, BlockState state) {
        poseStack.rotateAround(getRotation(state.getValue(BornholmTopBlock.FACING)), 0.5F, 0.5F, 0.5F);
        poseStack.translate(0.5F, 0.5F, 0.07F);
//        if (state.getValue(WallClockBlock.PART) != WallClockPartProperty.BASE) {
//            float adjustment = state.getValue(WallClockBlock.XL) ? 1.0F : 0.5F;
//            poseStack.translate(adjustment, adjustment, 0);
//        }
        if (state.getValue(WallClockBlock.PART) == WallClockPartProperty.TOP_RIGHT) {
            poseStack.translate(-0.5, -0.5, 0);
        }
//        poseStack.translate(0, 0.438F, -0.315F);
        if (state.getValue(BornholmTopBlock.ACTIVATED)) poseStack.rotateAround(Axis.ZN.rotationDegrees(getMinuteHandRotation(partialTick)), 0, 0, 0);
    }

    public static void renderHourHand(PoseStack poseStack, float partialTick, BlockState state) {
        poseStack.translate(0, 0, -0.006F);
        if (state.getValue(BornholmTopBlock.ACTIVATED)) {
            poseStack.rotateAround(Axis.ZN.rotationDegrees(-getMinuteHandRotation(partialTick)), 0, 0, 0);
            poseStack.rotateAround(Axis.ZN.rotationDegrees(getHourHandRotation(partialTick)), 0, 0, 0);
        }
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
}
