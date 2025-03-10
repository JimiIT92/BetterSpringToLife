package org.hendrix.betterspringdrop.core;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.hendrix.betterspringdrop.BetterSpringDrop;
import org.hendrix.betterspringdrop.block.EmptyFireflyBush;
import org.hendrix.betterspringdrop.block.FireflyJarBlock;
import org.hendrix.betterspringdrop.block.HollowBlock;
import org.hendrix.betterspringdrop.block.WallMushroomBlock;
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
            return pickFirefliesFromFireflyBush(player, hand, player.getStackInHand(hand), world, blockPos, blockState);
        }
        return ActionResult.PASS;
    }

    /**
     * Pick some Fireflies from a {@link FireflyBushBlock Firefly Bush}
     *
     * @param player The {@link PlayerEntity Player picking the Fireflies}
     * @param hand The {@link Hand Hand the Player is using}
     * @param itemStack The {@link ItemStack current Item Stack}
     * @param world The {@link World World reference}
     * @param pos The {@link BlockPos Firefly Bush Block Pos}
     * @param blockState The {@link BlockState Firefly Bush Block State}
     */
    private static ActionResult pickFirefliesFromFireflyBush(final PlayerEntity player, final Hand hand, final ItemStack itemStack, final World world, final BlockPos pos, final BlockState blockState) {
        final Random random = world.getRandom();
        final int pickedFireflies = 1 + random.nextInt(2);
        if(itemStack.isOf(Items.GLASS_BOTTLE)) {
            itemStack.decrement(1);
            player.giveItemStack(getFireflyJar(null, pickedFireflies));
            emptyFireflyBush(world, pos, blockState);
            return ActionResult.SUCCESS;
        }
        if(itemStack.isOf(BSDItems.FIREFLY_JAR)) {
            final int fireflies = FireflyJarBlock.getFireflies(itemStack);
            if(fireflies < FireflyJarBlock.MAX_FIREFLIES) {
                player.setStackInHand(hand, getFireflyJar(itemStack, fireflies + pickedFireflies));
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    /**
     * Get the {@link ItemStack Firefly Jar Item Stack}
     *
     * @param itemStack The {@link ItemStack current Item Stack}
     * @param fireflies The {@link Integer Fireflies amount}
     * @return The {@link ItemStack Firefly Jar Item Stack}
     */
    private static ItemStack getFireflyJar(ItemStack itemStack, final int fireflies) {
        if(itemStack == null) {
            itemStack = new ItemStack(BSDBlocks.FIREFLY_JAR);
        }
        itemStack.set(BSDDataComponentTypes.FIREFLIES, new FirefliesComponent(Math.min(FireflyJarBlock.MAX_FIREFLIES, fireflies)));
        return itemStack;
    }

    /**
     * Replace a {@link FireflyBushBlock Firefly Bush} with an {@link EmptyFireflyBush Empty Firefly Bush}
     *
     * @param world The {@link World World reference}
     * @param pos The {@link BlockPos Firefly Bush Block Pos}
     * @param blockState The {@link BlockState Firefly Bush Block State}
     */
    private static void emptyFireflyBush(final World world, final BlockPos pos, final BlockState blockState) {
        if(world.getRandom().nextBoolean()) {
            WorldUtils.setBlock(BSDBlocks.EMPTY_FIREFLY_BUSH.getStateWithProperties(blockState), null, null, world, pos, null, null);
        }
    }

    /**
     * Place a {@link WallMushroomBlock Wall Mushroom Block} when right-clicking a {@link PillarBlock Log} with a mushroom
     *
     * @param player The {@link PlayerEntity Player}
     * @param world The {@link World World reference}
     * @param hand The {@link Hand Hand the Player is using}
     * @param blockHitResult The {@link BlockHitResult Block Hit Result}
     * @return The {@link ActionResult interaction Action Result}
     */
    private static ActionResult placeWallMushrooms(final PlayerEntity player, final World world, final Hand hand, final BlockHitResult blockHitResult) {
        final BlockPos blockPos = blockHitResult.getBlockPos();
        final BlockState blockState = world.getBlockState(blockPos);
        final ItemStack itemStack = player.getStackInHand(hand);
        final Direction direction = blockHitResult.getSide();
        if(direction.getAxis().isHorizontal()) {
            if(blockState.isIn(BlockTags.LOGS)) {
                Block wallMushroomBlock = null;
                if(itemStack.isOf(Items.BROWN_MUSHROOM)) {
                    wallMushroomBlock = BSDBlocks.BROWN_WALL_MUSHROOM;
                }
                if(itemStack.isOf(Items.RED_MUSHROOM)) {
                    wallMushroomBlock = BSDBlocks.RED_WALL_MUSHROOM;
                }
                if(wallMushroomBlock != null) {
                    final BlockPos mushroomPos = blockPos.offset(direction);
                    if(world.getBlockState(mushroomPos).isAir() || world.getBlockState(mushroomPos).isReplaceable()) {
                        WorldUtils.setBlock(wallMushroomBlock.getDefaultState()
                                .with(WallMushroomBlock.FACING, direction)
                                .with(WallMushroomBlock.WATERLOGGED, Fluids.WATER.equals(world.getFluidState(mushroomPos).getFluid()))
                            , player, hand, world, blockPos.offset(blockHitResult.getSide()), itemStack, SoundEvents.BLOCK_GRASS_PLACE);
                    }
                }
            }
            else if(canIncreaseMushroom(BSDBlocks.BROWN_WALL_MUSHROOM, Items.BROWN_MUSHROOM, blockState, itemStack) ||
                    canIncreaseMushroom(BSDBlocks.RED_WALL_MUSHROOM, Items.RED_MUSHROOM, blockState, itemStack)) {
                WorldUtils.setBlock(blockState
                        .with(WallMushroomBlock.MUSHROOMS, Math.min(WallMushroomBlock.MAX_MUSHROOMS, blockState.get(WallMushroomBlock.MUSHROOMS) + 1))
                    , player, hand, world, blockPos, itemStack, SoundEvents.BLOCK_GRASS_PLACE);
            }
        }
        return ActionResult.PASS;
    }

    /**
     * Check if an already placed {@link WallMushroomBlock Wall Mushroom} can be increased
     *
     * @param block The {@link Block Block to check}
     * @param item The {@link Item Item to check}
     * @param blockState The {@link BlockState current Block State}
     * @param itemStack The {@link ItemStack held Item Stack}
     * @return {@link Boolean True if Mushrooms can be increased}
     */
    private static boolean canIncreaseMushroom(final Block block, final Item item, final BlockState blockState, final ItemStack itemStack) {
        return blockState.isOf(block) && blockState.contains(WallMushroomBlock.MUSHROOMS) && blockState.get(WallMushroomBlock.MUSHROOMS) < WallMushroomBlock.MAX_MUSHROOMS && itemStack.isOf(item);
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
        UseBlockCallback.EVENT.register(BSDEvents::placeWallMushrooms);
        ItemTooltipCallback.EVENT.register(BSDEvents::appendComponentTooltips);
    }

}