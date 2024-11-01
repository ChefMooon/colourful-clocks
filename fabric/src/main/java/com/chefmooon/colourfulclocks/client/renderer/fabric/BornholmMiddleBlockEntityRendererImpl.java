package com.chefmooon.colourfulclocks.client.renderer.fabric;

import com.chefmooon.colourfulclocks.client.renderer.BornholmMiddleBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.BornholmMiddleBlock;
import com.chefmooon.colourfulclocks.common.block.entity.fabric.BornholmMiddleBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.core.PendulumTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
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

public class BornholmMiddleBlockEntityRendererImpl<T extends BornholmMiddleBlockEntityImpl> extends BornholmMiddleBlockEntityRenderer implements BlockEntityRenderer<T> {

    public BornholmMiddleBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(BornholmMiddleBlockEntityImpl blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (level == null || blockEntity.isEmpty()) return;

        BlockState state = level.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof BornholmMiddleBlock)) return;

        ItemStack pendulum = blockEntity.getPendelumItem();

        float swingSpeedModifier = getSwingSpeedFromPendulumItem(pendulum);

        if (!pendulum.isEmpty()) {
            BakedModel model = minecraft.getItemRenderer().getModel(pendulum, level, null, 0);
            renderPendelum(poseStack, partialTick, state, swingSpeedModifier);
            minecraft.getItemRenderer().render(pendulum, ItemDisplayContext.FIXED, false, poseStack, bufferSource, packedLight, packedOverlay, model);
            poseStack.popPose();
        }
    }

    public static float getSwingSpeedFromPendulumItem(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.IRON_PENDULUM.get())) {
            return PendulumTypes.IRON.getSwingSpeedModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.COPPER_PENDULUM.get())) {
            return PendulumTypes.COPPER.getSwingSpeedModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.GOLD_PENDULUM.get())) {
            return PendulumTypes.GOLD.getSwingSpeedModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.DIAMOND_PENDULUM.get())) {
            return PendulumTypes.DIAMOND.getSwingSpeedModifier();
        } else if (itemStack.is(ColourfulClocksItemsImpl.NETHERITE_PENDULUM.get())) {
            return PendulumTypes.NETHERITE.getSwingSpeedModifier();
        } else {
            return 1.0F;
        }
    }
}
