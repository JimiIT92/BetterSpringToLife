package org.hendrix.betterspringtolife.core;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.hendrix.betterspringtolife.BetterSpringToLife;
import org.hendrix.betterspringtolife.block.FireflyJarBlock;
import org.hendrix.betterspringtolife.block.HollowBlock;
import org.hendrix.betterspringtolife.block.WallMushroomBlock;
import org.hendrix.betterspringtolife.item.component.FirefliesComponent;

/**
 * {@link BetterSpringToLife} events
 */
public final class BSTLEvents {

    //#region Events

    private static InteractionResult hollowLog(Player player, Level level, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        final ItemStack itemStack = player.getItemInHand(interactionHand);
        if(player.isShiftKeyDown() && itemStack.is(ItemTags.AXES)) {
            final BlockPos pos = blockHitResult.getBlockPos();
            final BlockState blockState = level.getBlockState(pos);
            return HollowBlock.getHollow(blockState).map(
                  hollowBlock -> setBlock(hollowBlock, player, interactionHand, level, pos, itemStack, SoundEvents.AXE_STRIP)
            ).orElse(InteractionResult.PASS);
        }
        return InteractionResult.PASS;
    }

    private static InteractionResult placeWallMushrooms(Player player, Level level, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        final BlockPos blockPos = blockHitResult.getBlockPos();
        final BlockState blockState = level.getBlockState(blockPos);
        final ItemStack itemStack = player.getItemInHand(interactionHand);
        final Direction direction = blockHitResult.getDirection();
        if(direction.getAxis().isHorizontal()) {
            if(blockState.is(BlockTags.LOGS)) {
                Block wallMushroomBlock = null;
                if(itemStack.is(Items.BROWN_MUSHROOM)) {
                    wallMushroomBlock = BSTLBlocks.BROWN_WALL_MUSHROOM;
                }
                if(itemStack.is(Items.RED_MUSHROOM)) {
                    wallMushroomBlock = BSTLBlocks.RED_WALL_MUSHROOM;
                }
                if(wallMushroomBlock != null) {
                    final BlockPos mushroomPos = blockPos.offset(direction.getUnitVec3i());
                    if(level.getBlockState(mushroomPos).isAir() || level.getBlockState(mushroomPos).canBeReplaced()) {
                        setBlock(wallMushroomBlock.defaultBlockState()
                                        .setValue(WallMushroomBlock.FACING, direction)
                                        .setValue(WallMushroomBlock.WATERLOGGED, level.getFluidState(mushroomPos).is(Fluids.WATER))
                                , player, interactionHand, level, blockPos.offset(blockHitResult.getDirection().getUnitVec3i()), itemStack, SoundEvents.GRASS_PLACE);
                    }
                }
            }
            else if(canIncreaseMushroom(BSTLBlocks.BROWN_WALL_MUSHROOM, Items.BROWN_MUSHROOM, blockState, itemStack) ||
                    canIncreaseMushroom(BSTLBlocks.RED_WALL_MUSHROOM, Items.RED_MUSHROOM, blockState, itemStack)) {
                setBlock(blockState
                                .setValue(WallMushroomBlock.MUSHROOMS, Math.min(WallMushroomBlock.MAX_MUSHROOMS, blockState.getValue(WallMushroomBlock.MUSHROOMS) + 1))
                        , player, interactionHand, level, blockPos, itemStack, SoundEvents.GRASS_PLACE);
            }
        }
        return InteractionResult.PASS;
    }

    private static InteractionResult fillFireflyJar(Player player, Level level, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        final BlockPos blockPos = blockHitResult.getBlockPos();
        final BlockState blockState = level.getBlockState(blockPos);
        if(blockState.is(Blocks.FIREFLY_BUSH)) {
            return pickFirefliesFromFireflyBush(player, interactionHand, player.getItemInHand(interactionHand), level, blockPos, blockState);
        }
        return InteractionResult.PASS;
    }

    //#endregion

    private static InteractionResult setBlock(final BlockState blockState, final Player player, final InteractionHand hand, final Level level, final BlockPos pos, final ItemStack itemStack, final SoundEvent soundEvent) {
        level.setBlockAndUpdate(pos, blockState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, blockState));
        if(player != null) {
            if(player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
            }
            if(itemStack != null && !itemStack.isEmpty()) {
                if(itemStack.isDamageableItem()) {
                    itemStack.hurtAndBreak(1, player, hand);
                } else {
                    itemStack.consume(1, player);
                }
            }
            final boolean isClient = level.isClientSide();
            if(isClient) {
                player.swing(hand);
                player.playSound(soundEvent);
            }
            return isClient ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER;
        }
        return InteractionResult.PASS;
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
        return blockState.is(block) && blockState.hasProperty(WallMushroomBlock.MUSHROOMS) && blockState.getValue(WallMushroomBlock.MUSHROOMS) < WallMushroomBlock.MAX_MUSHROOMS && itemStack.is(item);
    }

    private static InteractionResult pickFirefliesFromFireflyBush(final Player player, final InteractionHand hand, final ItemStack itemStack, final Level world, final BlockPos pos, final BlockState blockState) {
        final RandomSource random = world.getRandom();
        final int pickedFireflies = 1 + random.nextInt(2);
        if(itemStack.is(Items.GLASS_BOTTLE)) {
            itemStack.shrink(1);
            player.addItem(getFireflyJar(null, pickedFireflies));
            emptyFireflyBush(world, pos, blockState);
            return InteractionResult.SUCCESS;
        }
        if(itemStack.is(BSTLItems.FIREFLY_JAR)) {
            final int fireflies = FireflyJarBlock.getFireflies(itemStack);
            if(fireflies < FireflyJarBlock.MAX_FIREFLIES) {
                player.setItemInHand(hand, getFireflyJar(itemStack, fireflies + pickedFireflies));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    private static ItemStack getFireflyJar(ItemStack itemStack, final int fireflies) {
        if(itemStack == null) {
            itemStack = new ItemStack(BSTLBlocks.FIREFLY_JAR);
        }
        itemStack.set(BSTLDataComponentTypes.FIREFLIES, new FirefliesComponent(Math.min(FireflyJarBlock.MAX_FIREFLIES, fireflies)));
        return itemStack;
    }

    private static void emptyFireflyBush(final Level world, final BlockPos pos, final BlockState blockState) {
        if(world.getRandom().nextBoolean()) {
            setBlock(BSTLBlocks.EMPTY_FIREFLY_BUSH.withPropertiesOf(blockState), null, null, world, pos, null, null);
        }
    }
    public static void register() {
        UseBlockCallback.EVENT.register(BSTLEvents::hollowLog);
        UseBlockCallback.EVENT.register(BSTLEvents::placeWallMushrooms);
        UseBlockCallback.EVENT.register(BSTLEvents::fillFireflyJar);
    }

}