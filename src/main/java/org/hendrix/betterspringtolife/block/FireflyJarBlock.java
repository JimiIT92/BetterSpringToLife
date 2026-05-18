package org.hendrix.betterspringtolife.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.hendrix.betterspringtolife.core.BSTLDataComponentTypes;
import org.hendrix.betterspringtolife.core.BSTLItems;
import org.hendrix.betterspringtolife.item.component.FirefliesComponent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class FireflyJarBlock extends LanternBlock {

    /**
     * The {@link Integer maximum amount of fireflies the Block can hold}
     */
    public static final int MAX_FIREFLIES = 5;
    /**
     * The {@link IntegerProperty Fireflies Block property}
     */
    public static final IntegerProperty FIREFLIES = IntegerProperty.create("fireflies", 1, MAX_FIREFLIES);

    public FireflyJarBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HANGING, false).setValue(WATERLOGGED, false).setValue(FIREFLIES, 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NonNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FIREFLIES);
    }

    @Override
    protected VoxelShape getVisualShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected float getShadeBrightness(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return 1.0F;
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState neighborState, Direction direction) {
        return state.is(this) || super.skipRendering(state, state, direction);
    }

    @Override
    protected @Nullable ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData) {
        FirefliesComponent firefliesComponent = new FirefliesComponent(state.getValue(FIREFLIES));
        ItemStack fireflyJar = BSTLItems.FIREFLY_JAR.getDefaultInstance();
        fireflyJar.set(BSTLDataComponentTypes.FIREFLIES, firefliesComponent);
        return fireflyJar;
    }

    public static int getFireflies(final ItemStack itemStack) {
        if(itemStack.is(BSTLItems.FIREFLY_JAR)) {
            final FirefliesComponent firefliesComponent = itemStack.get(BSTLDataComponentTypes.FIREFLIES);
            return firefliesComponent == null ? 0 : firefliesComponent.fireflies();
        }
        return 0;
    }
}
