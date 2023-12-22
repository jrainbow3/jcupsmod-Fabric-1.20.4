package net.jcup.testmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class BreniumStaffItem extends Item {
    public BreniumStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if(!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            BlockState state = context.getWorld().getBlockState(positionClicked);
            outputText(state, player);

        }

        context.getStack().damage(1, player,
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }

    private void outputText(BlockState state, PlayerEntity player) {
        boolean isDiamond = state.isOf(Blocks.DIAMOND_BLOCK)
                || state.isOf(Blocks.DIAMOND_ORE)
                || state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE);
        boolean isLapis = state.isOf(Blocks.LAPIS_BLOCK)
                || state.isOf(Blocks.LAPIS_ORE)
                || state.isOf(Blocks.DEEPSLATE_LAPIS_ORE);

        if(isDiamond) player.sendMessage(Text.literal("Bren: *sniff* Oh yeah that's a diamond."), false);
        else if(isLapis) player.sendMessage(Text.literal("Bren: *sniff* Ooh, getting closer."), false);
        else player.sendMessage(Text.literal("Bren: *sniff* Hmmm... nope, not a diamond."), false);
    }
}
