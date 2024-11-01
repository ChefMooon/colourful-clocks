package com.chefmooon.colourfulclocks.client.renderer;

import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.ColourfulClocksItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;

import java.util.Map;

public class BornholmTopBlockEntityRenderer {

    protected static Map<PocketWatchTypes, ModelResourceLocation> CLOCK_HANDS = Map.of(
//            PocketWatchTypes.IRON, ModelResourceLocation.inventory(TextUtil.res("movement_basic"))
            PocketWatchTypes.IRON, ModelResourceLocation.inventory(ColourfulClocksItems.IRON_POCKET_WATCH_IN_CLOCK)
    );

    public static void renderClockHands(PoseStack poseStack, float partialTick, BlockState state) {
        poseStack.pushPose();

        poseStack.translate(0.5F, 0.5F, 0.5F);

        poseStack.mulPose(getRotation(state.getValue(BornholmTopBlock.FACING)));

        if (state.getValue(BornholmTopBlock.ACTIVATED)) poseStack.mulPose(Axis.ZN.rotationDegrees(getClockHourHandRotation(partialTick)));
    }

    public static float getClockHourHandRotation(float partialTick) {
        float timeOfDay = (Minecraft.getInstance().level.getDayTime() + partialTick) % 24000;

        float twelveHourTime = (timeOfDay + 18000) % 24000;

        return (twelveHourTime / 12000.0F) * 360.0F;
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
