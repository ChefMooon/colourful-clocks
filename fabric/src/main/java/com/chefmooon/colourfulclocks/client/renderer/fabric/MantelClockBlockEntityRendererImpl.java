package com.chefmooon.colourfulclocks.client.renderer.fabric;

import com.chefmooon.colourfulclocks.client.renderer.MantelClockBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.MantelClockBlock;
import com.chefmooon.colourfulclocks.common.block.entity.MantelClockBlockEntity;
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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MantelClockBlockEntityRendererImpl<T extends MantelClockBlockEntity> extends MantelClockBlockEntityRenderer implements BlockEntityRenderer<T> {
    public MantelClockBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (level == null) return;

        BlockState state = level.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof MantelClockBlock)) return;

        PocketWatchTypes pocketWatchType = blockEntity.getDialData().pocketWatchType();
        if (pocketWatchType != PocketWatchTypes.EMPTY) {
            renderMinuteHand(poseStack, partialTick, state);

            ResourceLocation minuteHandLocation = TextUtil.res("item/%s_minute_hand_small".formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath()));
            BakedModel minuteHandModel = minecraft.getModelManager().getModel(minuteHandLocation);
            minecraft.getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.cutout()),
                    blockEntity.getBlockState(),
                    minuteHandModel,
                    1f, 1f, 1f,
                    packedLight, packedOverlay);

            renderHourHand(poseStack, partialTick, state);

            ResourceLocation hourHandLocation = TextUtil.res("item/%s_hour_hand_small".formatted(BuiltInRegistries.ITEM.getKey(ColourfulClocksTypeUtil.getPocketWatchItemFromType(pocketWatchType)).getPath()));
            BakedModel hourHandModel = minecraft.getModelManager().getModel(hourHandLocation);
            minecraft.getBlockRenderer().getModelRenderer().renderModel(
                    poseStack.last(),
                    bufferSource.getBuffer(RenderType.cutout()),
                    blockEntity.getBlockState(),
                    hourHandModel,
                    1f, 1f, 1f,
                    packedLight, packedOverlay);
        }
    }
}
