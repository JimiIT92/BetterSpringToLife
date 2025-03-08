package org.hendrix.betterspringdrop.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.hendrix.betterspringdrop.core.BSDTags;

/**
 * Implementation class for a {@link PlantBlock Snowy Plant Block}
 */
public class SnowyVegetationBlock extends PlantBlock {

    /**
     * The {@link MapCodec Block Codec}
     */
    public static final MapCodec<SnowyVegetationBlock> CODEC = createCodec(SnowyVegetationBlock::new);

    /**
     * Constructor. Set the {@link Settings Block Settings}
     *
     * @param settings The {@link Settings Block Settings}
     */
    protected SnowyVegetationBlock(final Settings settings) {
        super(settings);
    }

    /**
     * Get the {@link VoxelShape Block Shape}
     *
     * @param state The {@link BlockState current Block State}
     * @param world The {@link BlockView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @param context The {@link ShapeContext Block Shape Context}
     * @return The {@link VoxelShape Block Shape}
     */
    @Override
    protected VoxelShape getOutlineShape(final BlockState state, final BlockView world, final BlockPos pos, final ShapeContext context) {
        return Block.createColumnShape(12.0D, 0.0D, 13.0D);
    }

    /**
     * Check if the {@link PlantBlock Block} can be placed on the provided {@link BlockState Block State}
     *
     * @param floor The {@link BlockState ground Block State}
     * @param world The {@link BlockView World reference}
     * @param pos The {@link BlockPos current Block Pos}
     * @return {@link Boolean True if the Block can be placed}
     */
    @Override
    protected boolean canPlantOnTop(final BlockState floor, final BlockView world, final BlockPos pos) {
        return floor.isIn(BSDTags.BlockTags.SNOWY_VEGETATION_MAY_PLACE_ON);
    }

    /**
     * Get the {@link MapCodec Block Codec}
     *
     * @return The {@link #CODEC Block Codec}
     */
    @Override
    public MapCodec<? extends SnowyVegetationBlock> getCodec() {
        return CODEC;
    }

}