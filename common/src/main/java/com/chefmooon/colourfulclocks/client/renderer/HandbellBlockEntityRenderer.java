package com.chefmooon.colourfulclocks.client.renderer;

import com.chefmooon.colourfulclocks.common.block.entity.HandbellBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

public class HandbellBlockEntityRenderer {
    public static void renderHandbell(HandbellBlockEntity blockEntity, PoseStack poseStack, float partialTick, BlockState state) {
        float f = (float)blockEntity.ticks + partialTick;
        float g = 0.0F;
        float h = 0.0F;
        if (blockEntity.shaking) {
            float i = Mth.sin(f / (float)Math.PI) / (4.0F + f / 3.0F);
            if (blockEntity.clickDirection == Direction.NORTH) {
                g = -i;
            } else if (blockEntity.clickDirection == Direction.SOUTH) {
                g = i;
            } else if (blockEntity.clickDirection == Direction.EAST) {
                h = -i;
            } else if (blockEntity.clickDirection == Direction.WEST) {
                h = i;
            }
        }

        poseStack.rotateAround(Axis.XP.rotation(g), 0.5F, 0.8F, 0.5F);
        poseStack.rotateAround(Axis.ZP.rotation(h), 0.5F, 0.8F, 0.5F);
    }
}
