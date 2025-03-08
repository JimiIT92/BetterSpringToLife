package org.hendrix.betterspringdrop.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.hendrix.betterspringdrop.component.type.FirefliesComponent;
import org.hendrix.betterspringdrop.core.BSDDataComponentTypes;
import org.hendrix.betterspringdrop.core.BSDItems;

/**
 * Implementation class for an {@link LanternBlock Firefly Jar Block}
 */
public class FireflyJarBlock extends LanternBlock {

    /**
     * The {@link MapCodec<FireflyJarBlock> Firefly Jar Block Codec}
     */
    public static final MapCodec<FireflyJarBlock> CODEC = createCodec(FireflyJarBlock::new);
    /**
     * The {@link Integer maximum amount of fireflies the Block can hold}
     */
    public static final int MAX_FIREFLIES = 5;
    /**
     * The {@link IntProperty Fireflies Block property}
     */
    public static final IntProperty FIREFLIES = IntProperty.of("fireflies", 1, MAX_FIREFLIES);

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param settings The {@link Settings Block Settings}
     */
    public FireflyJarBlock(final Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HANGING, false).with(WATERLOGGED, false).with(FIREFLIES, 1));
    }

    /**
     * Register the {@link Block Block} Properties
     *
     * @param builder The {@link StateManager.Builder State Builder}
     */
    @Override
    protected void appendProperties(final StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FIREFLIES);
    }

    /**
     * Get the {@link VoxelShape Camera Collision Shape}
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link BlockView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param context The {@link ShapeContext Block Shape Context}
     * @return The {@link VoxelShape Camera Collision Shape}
     */
    @Override
    protected VoxelShape getCameraCollisionShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
        return VoxelShapes.empty();
    }

    /**
     * Get the {@link Float Block ambient occlusion light level value}
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link BlockView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @return {@link Float 1.0F}
     */
    @Override
    protected float getAmbientOcclusionLightLevel(final BlockState state, final BlockView world, final BlockPos pos) {
        return 1.0F;
    }

    /**
     * Check if the {@link BlockState current Block State} is transparent
     *
     * @param state The {@link BlockState current Block State}
     * @return {@link Boolean#TRUE True}
     */
    @Override
    protected boolean isTransparent(final BlockState state) {
        return true;
    }

    /**
     * Check if a side should be rendered or not
     *
     * @param state The {@link BlockState current Block State}
     * @param stateFrom The {@link BlockState adjacent Block State}
     * @param direction The {@link Direction Block direction}
     * @return {@link Boolean True if the side should not be rendered}
     */
    @Override
    protected boolean isSideInvisible(final BlockState state, final BlockState stateFrom, final Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }

    /**
     * Get the amount of Fireflies inside the {@link ItemStack Item Stack}
     *
     * @param itemStack The {@link ItemStack Item Stack to check}
     * @return {@link Integer The Fireflies amount}
     */
    public static int getFireflies(final ItemStack itemStack) {
        if(itemStack.isOf(BSDItems.FIREFLY_JAR)) {
            final FirefliesComponent fireflies = itemStack.get(BSDDataComponentTypes.FIREFLIES);
            return fireflies == null ? 0 : fireflies.fireflies();
        }
        return 0;
    }
}