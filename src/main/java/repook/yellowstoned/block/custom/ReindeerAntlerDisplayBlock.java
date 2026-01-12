package repook.yellowstoned.block.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class ReindeerAntlerDisplayBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(1, 1, 14, 15, 15, 16);
    protected static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0, 1, 1, 2, 15, 15);
    protected static final VoxelShape WEST_SHAPE = Block.createCuboidShape(14, 1, 1, 16, 15, 15);
    protected static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(1, 1, 0, 15, 15, 2);

    public ReindeerAntlerDisplayBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch ((Direction) state.get(FACING)) {
            case NORTH -> NORTH_SHAPE;
            default -> EAST_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
        };
    }
    private boolean canPlaceOn(BlockView world, BlockPos pos, Direction side) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.isSideSolidFullSquare(world, pos, side);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction facing = state.get(FACING);
        BlockPos oppositePos = pos.offset(facing.getOpposite());

        // Check if it can be placed on the block behind
        if (!this.canPlaceOn(world, oppositePos, facing)) return false;

        // Determine left and right directions relative to facing
        Direction left = facing.rotateYCounterclockwise();
        Direction right = facing.rotateYClockwise();

        // Check that there is no same block to the left or right
        if (world.getBlockState(pos.offset(left)).isOf(this) || world.getBlockState(pos.offset(right)).isOf(this)) {
            return false;
        }

        return true;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!ctx.canReplaceExisting()) {
            BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(ctx.getSide().getOpposite()));
            if (blockState.isOf(this) && blockState.get(FACING) == ctx.getSide()) {
                return null;
            }
        }

        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());

        for (Direction direction : ctx.getPlacementDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockState = blockState.with(FACING, direction.getOpposite());
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState.with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
                }
            }
        }

        return null;
    }



    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }


}
