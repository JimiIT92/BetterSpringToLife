package org.hendrix.betterspringdrop.core;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.block.FireflyJarBlock;
import org.hendrix.betterspringdrop.block.HollowBlock;
import org.hendrix.betterspringdrop.component.type.FirefliesComponent;
import org.hendrix.betterspringdrop.utils.WorldUtils;

import java.util.List;

/**
 * {@link BetterSpringDrop Better Spring Drop} Events
 */
public final class BSDEvents {

    //#region Events

    /**
     * Hollow a {@link PillarBlock Log Block} when right-clicked with an {@link AxeItem Axe}
     *
     * @param player The {@link PlayerEntity Player}
     * @param world The {@link World World reference}
     * @param hand The {@link Hand Hand the Player is using}
     * @param blockHitResult The {@link BlockHitResult Block Hit Result}
     * @return The {@link ActionResult interaction Action Result}
     */
    private static ActionResult hollowLog(final PlayerEntity player, final World world, final Hand hand, final BlockHitResult blockHitResult) {
        final ItemStack itemStack = player.getStackInHand(hand);
        if(itemStack.getItem() instanceof AxeItem) {
            final BlockPos blockPos = blockHitResult.getBlockPos();
            final BlockState blockState = world.getBlockState(blockPos);
            if(player.isSneaking() || blockState.getBlock() instanceof HollowBlock) {
                return HollowBlock.getHollow(blockState).map(hollowBlockState -> WorldUtils.setBlock(hollowBlockState, player, hand, world, blockPos, itemStack, SoundEvents.ITEM_AXE_STRIP)).orElse(ActionResult.PASS);
            }
        }
        return ActionResult.PASS;
    }

    /**
     * Fill a {@link Items#GLASS_BOTTLE Glass Bottle} with a Firefly, or increase the Fireflies in a {@link BSDBlocks#FIREFLY_JAR Firefly Jar}
     *
     * @param player The {@link PlayerEntity Player}
     * @param world The {@link World World reference}
     * @param hand The {@link Hand Hand the Player is using}
     * @param blockHitResult The {@link BlockHitResult Block Hit Result}
     * @return The {@link ActionResult interaction Action Result}
     */
    private static ActionResult fillFireflyJar(final PlayerEntity player, final World world, final Hand hand, final BlockHitResult blockHitResult) {
        final BlockPos blockPos = blockHitResult.getBlockPos();
        final BlockState blockState = world.getBlockState(blockPos);
        if(blockState.isOf(Blocks.FIREFLY_BUSH)) {
            final ItemStack itemStack = player.getStackInHand(hand);
            if(itemStack.isOf(Items.GLASS_BOTTLE)) {
                player.setStackInHand(hand, new ItemStack(BSDBlocks.FIREFLY_JAR));
            }
            if(itemStack.isOf(BSDItems.FIREFLY_JAR)) {
                final int fireflies = FireflyJarBlock.getFireflies(itemStack);
                itemStack.set(BSDDataComponentTypes.FIREFLIES, new FirefliesComponent(Math.min(FireflyJarBlock.MAX_FIREFLIES, fireflies + 1)));
            }
        }
        return ActionResult.PASS;
    }

    /**
     * Append the modded Data Component Tooltips to Items
     *
     * @param itemStack The {@link ItemStack current Item Stack}
     * @param tooltipContext The {@link Item.TooltipContext Item Tooltip Context}
     * @param tooltipType The {@link TooltipType Tooltip Type}
     * @param tooltips The {@link List<Text> current Tooltips}
     */
    private static void appendComponentTooltips(final ItemStack itemStack, final Item.TooltipContext tooltipContext, final TooltipType tooltipType, final List<Text> tooltips) {
        final TooltipDisplayComponent tooltipDisplayComponent = itemStack.getOrDefault(DataComponentTypes.TOOLTIP_DISPLAY, TooltipDisplayComponent.DEFAULT);
        itemStack.appendComponentTooltip(BSDDataComponentTypes.FIREFLIES, tooltipContext, tooltipDisplayComponent, tooltips::add, tooltipType);
    }

    //#endregion

    /**
     * Register all Events
     */
    public static void register() {
        UseBlockCallback.EVENT.register(BSDEvents::hollowLog);
        UseBlockCallback.EVENT.register(BSDEvents::fillFireflyJar);
        ItemTooltipCallback.EVENT.register(BSDEvents::appendComponentTooltips);
    }

}