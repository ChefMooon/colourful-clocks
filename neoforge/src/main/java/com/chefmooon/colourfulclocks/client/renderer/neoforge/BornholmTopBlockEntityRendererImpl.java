package com.chefmooon.colourfulclocks.client.renderer.neoforge;

import com.chefmooon.colourfulclocks.client.renderer.BornholmTopBlockEntityRenderer;
import com.chefmooon.colourfulclocks.common.block.BornholmTopBlock;
import com.chefmooon.colourfulclocks.common.block.entity.neoforge.BornholmTopBlockEntityImpl;
import com.chefmooon.colourfulclocks.common.core.PocketWatchTypes;
import com.chefmooon.colourfulclocks.common.item.PocketWatchItem;
import com.chefmooon.colourfulclocks.common.registry.neoforge.ColourfulClocksItemsImpl;
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

public class BornholmTopBlockEntityRendererImpl extends BornholmTopBlockEntityRenderer implements BlockEntityRenderer<BornholmTopBlockEntityImpl> {
    public BornholmTopBlockEntityRendererImpl(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(BornholmTopBlockEntityImpl blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level world = blockEntity.getLevel();
        Minecraft minecraft = Minecraft.getInstance();
        if (world == null || blockEntity.isEmpty()) return;

        BlockState state = world.getBlockState(blockEntity.getBlockPos());
        if (!(state.getBlock() instanceof BornholmTopBlock)) return;

        ItemStack clockHands = blockEntity.getClockHandsItem();

        if (clockHands.getItem() instanceof PocketWatchItem pocketWatchItem) {
            if (!clockHands.isEmpty()) {
                // todo - figure out a way to get a different model from an item movement item is used but hand model is rendered
//            BakedModel model = minecraft.getItemRenderer().getModel(clockHands, world, null, 0); // Original, grabs the same item model
//            BakedModel model = minecraft.getModelManager().getModel(ModelResourceLocation.inventory(ModelLocationUtils.getModelLocation(clockHands.getItem()).withSuffix("_in_clock"))); // an Advanced attempt need to remove the "item/"

//                PocketWatchTypes type = pocketWatchItem.getType();
//                BakedModel model = minecraft.getModelManager().getModel(type.getInClockLocation());
                // todo - figure out a dynamic way to find this instead of this way? (hate it)
                BakedModel model = minecraft.getModelManager().getModel(getHandModelResourceLocation(clockHands));

                renderClockHands(poseStack, partialTick, state);

                minecraft.getItemRenderer().render(clockHands, ItemDisplayContext.FIXED, false, poseStack, bufferSource, packedLight, packedOverlay, model);
                poseStack.popPose();
            }
        }

    }

    protected static ModelResourceLocation getHandModelResourceLocation(ItemStack itemStack) {
        if (itemStack.is(ColourfulClocksItemsImpl.IRON_POCKET_WATCH.get())) {
            return PocketWatchTypes.IRON.getInClockLocation();
        } else if (itemStack.is(ColourfulClocksItemsImpl.COPPER_POCKET_WATCH.get())) {
            return PocketWatchTypes.COPPER.getInClockLocation();
        } else if (itemStack.is(ColourfulClocksItemsImpl.GOLD_POCKET_WATCH.get())) {
            return PocketWatchTypes.GOLD.getInClockLocation();
        } else if (itemStack.is(ColourfulClocksItemsImpl.DIAMOND_POCKET_WATCH.get())) {
            return PocketWatchTypes.DIAMOND.getInClockLocation();
        } else {
            return PocketWatchTypes.NETHERITE.getInClockLocation();
        }
    }
}
