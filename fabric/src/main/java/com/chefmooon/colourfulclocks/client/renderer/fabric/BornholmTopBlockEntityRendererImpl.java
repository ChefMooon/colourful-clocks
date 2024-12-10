package com.chefmooon.colourfulclocks.client.renderer.fabric;

import com.chefmooon.colourfulclocks.client.renderer.BornholmTopBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.entity.fabric.BornholmTopBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.registry.fabric.ColourfulClocksItemsImpl;
import com.chefmooon.colourfulclocks.common.util.BornholmTypeUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BornholmTopBlockEntityRendererImpl<T extends BornholmTopBlockEntityImpl> extends BornholmTopBlockEntityRenderer implements BlockEntityRenderer<T> {
    public BornholmTopBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level world = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (world == null || blockEntity.isEmpty()) return;

        BlockState state = world.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof BornholmTopBlock)) return;

        ItemStack clockHands = blockEntity.getClockHandsItem();
//        PocketWatchTypes pocketWatchType = BornholmTypeUtil.getPocketWatchTypeFromItem(blockEntity.getClockHandsItem().getItem()); // todo - fix this

        if (!clockHands.isEmpty()) {
//            BakedModel model = minecraft.getItemRenderer().getModel(clockHands, world, null, 0); // see neoforge class for notes, to be updated...
            BakedModel model = minecraft.getModelManager().getModel(getHandModelResourceLocation(clockHands));

            renderClockHands(poseStack, partialTick, state);

            minecraft.getItemRenderer().render(clockHands, ItemDisplayContext.FIXED, false, poseStack, bufferSource, packedLight, packedOverlay, model);
            poseStack.popPose();
        }
    }

    protected static ModelResourceLocation getHandModelResourceLocation(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.IRON.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.COPPER.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.EXPOSED_COPPER_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.EXPOSED_COPPER.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.WEATHERED_COPPER_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.WEATHERED_COPPER.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.OXIDIZED_COPPER_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.OXIDIZED_COPPER.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_COPPER_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.WAXED_COPPER.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_EXPOSED_COPPER_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.WAXED_EXPOSED_COPPER.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_WEATHERED_COPPER_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.WAXED_WEATHERED_COPPER.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.WAXED_OXIDIZED_COPPER_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.WAXED_OXIDIZED_COPPER.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.GOLD_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.GOLD.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.DIAMOND.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.EMERALD_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.EMERALD.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.AMETHYST_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.AMETHYST.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.QUARTZ_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.QUARTZ.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.LAPIS_LAZULI_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.LAPIS_LAZULI.getInClockLocation());
        } else if (itemStack.is(ColourfulClocksItemsImpl.REDSTONE_POCKET_WATCH.get())) {
            return ModelResourceLocation.inventory(PocketWatchTypes.REDSTONE.getInClockLocation());
        } else {
            return ModelResourceLocation.inventory(PocketWatchTypes.NETHERITE.getInClockLocation());
        }
    }
}
