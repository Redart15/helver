package redart15.helver.metadata;

import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.DyeColor;

import static net.minecraft.core.block.BlockLogicTrapDoor.DIRECTION_EAST;
import static net.minecraft.core.block.BlockLogicTrapDoor.DIRECTION_NORTH;
import static net.minecraft.core.block.BlockLogicTrapDoor.DIRECTION_SOUTH;
import static net.minecraft.core.block.BlockLogicTrapDoor.DIRECTION_WEST;
import static net.minecraft.core.util.helper.Direction.*;


/**
 * @implNote If you think this class is unnecessary, then you have not worked with metadata
 * long enough.
 */
// java:S2094
@SuppressWarnings("java:S2094")
public class MetadataBlockHelper {
	private MetadataBlockHelper() {}

	private static DyeColor getDyeColor(int metadata){
		return DyeColor.values()[Metadata.rawGetBitBlock(metadata, 4, 7)];
	}

	public static class Slab{
		private Slab(){}
		public enum SlabState{
			LOWER, FULLBLOCK, UPPER;
		}

		/**
		 * @param metadata  sets the color of slab
		 * @param state 	determines whether the slab state, lower half, upper half or fullblock
		 * @return 			returns the metadata for the slab
		 */
		public static int setMetadata(int metadata, SlabState state){
			return Metadata.rawSetBitBlock(metadata, 0, 1, state.ordinal());
		}

		/**
		 * @param state 	determines whether the slab state, lower half, upper half or fullblock
		 * @return			returns the metadata for the slab
		 */
		public static int setMetadata(SlabState state){
			return Metadata.rawSetBitBlock(0, 0, 1, state.ordinal());
		}

		/**
		 * @param metadata  sets the color of slab
		 * @param state 	determines whether the slab state, lower half, upper half or fullblock
		 * @param color		set the color of the slab
		 * @return returns the metadata for the slab
		 */
		public static int setMetadata(int metadata, SlabState state, DyeColor color){
			metadata =  Metadata.rawSetBitBlock(metadata, 0, 1, state.ordinal());
			return Metadata.rawSetBitBlock(metadata, 4, 7, color.blockMeta);
		}

		/**
		 * @param state 	determines whether the slab state, lower half, upper half or fullblock
		 * @param color		set the color of the slab
		 * @return 			returns the metadata for the slab
		 */
		public static int setMetadata(SlabState state, DyeColor color){
			return Slab.setMetadata(0, state, color);
		}


		/**
		 * @param metadata	the slab's metadata
		 * @return 			returns the slab state
		 */
		public static SlabState getState(int metadata){
			int state = Metadata.rawGetBitBlock(metadata, 0, 1);
			return SlabState.values()[state];
		}

		/**
		 * @param metadata	the slab's metadata
		 * @return 			returns the DyeColor of the slab
		 */
		public static DyeColor getColor(int metadata){
			return MetadataBlockHelper.getDyeColor(metadata);
		}
	}
	public static class Stairs {
		private Stairs(){}

		/**
		 * @param metadata  	current metadata
		 * @param direction 	determines the direction the stair are ascending
		 * @param isUpper  		determines whether the stair is placed facing upwards or downwards
		 * @return 				returns the metadata for the stairs in the direction ascendingn
		 */
		public static int setMetadata(int metadata, boolean isUpper, Direction direction) {
			metadata = Metadata.rawSetBitBlock(metadata, 0, 1, getMetadataFromDirection(direction));
			return Metadata.rawSetBit(metadata, 3, isUpper ? 1 : 0);
		}

		/**
		 * @param isUpper   determines whether the stair is placed facing upwards or downwards
		 * @param direction determines the direction the stair are ascending
		 * @return			returns the metadata for the stairs in the direction ascending
		 */
		public static int setMetadata(boolean isUpper, Direction direction) {
			int metadata = Metadata.rawSetBitBlock(0, 0, 1, getMetadataFromDirection(direction));
			return Metadata.rawSetBit(metadata, 3, isUpper ? 1 : 0);
		}

		/**
		 * @param dyeColor  sets the color of stairs
		 * @param isUpper   determines whether the stair is placed facing upwards or downwards
		 * @param direction determines the direction the stair are ascending
		 * @return 			returns the metadata for the stairs in the direction ascending
		 */
		public static int setMetadata(boolean isUpper, Direction direction, DyeColor dyeColor) {
			int metadata = Metadata.rawSetBitBlock(0, 0, 1, getMetadataFromDirection(direction));
			metadata = Metadata.rawSetBit(metadata, 3, isUpper ? 1 : 0);
			return Metadata.rawSetBitBlock(metadata, 4, 7, dyeColor.blockMeta);
		}

		/**
		 * @implNote The metadata of the stairs ascending direction.
		 * Importantly this differs from how BlockLogicStairs implements direction, this due to BTA placement setting and many layers of abstraction.
		 */
		public static int getMetadataFromDirection(Direction direction) {
			switch (direction) {
				case NORTH:
					return 3;
				case SOUTH:
					return 2;
				case WEST:
					return 1;
				case EAST:
				default:
					return 0;
			}
		}

		/**
		 * @implNote The metadata of the stairs ascending direction.
		 * Importantly this differs from how BlockLogicStairs implements direction, this due to BTA placement setting and many layers of abstraction.
		 */
		public static Direction getDirectionFromMetadata(int metadata) {
			int direction = Metadata.rawGetBitBlock(metadata, 0, 1);
			switch (direction) {
				case 3:
					return Direction.NORTH;
				case 2:
					return Direction.SOUTH;
				case 1:
					return Direction.WEST;
				case 0:
				default:
					return Direction.EAST;
			}
		}

		/**
		 * @param metadata  	current metadatas
		 * @return 				returns if the stair is facing upward or downwards
		 */
		public static boolean isUpper(int metadata) {
			return Metadata.isSet(metadata, 3);
		}

		/**
		 * @param metadata	the stair's metadata
		 * @return 			returns the DyeColor of the slab
		 */
		public static DyeColor getColor(int metadata){
			return MetadataBlockHelper.getDyeColor(metadata);
		}
	}
	public static class Trapdoor {
		private Trapdoor(){}
		/**
		 * @param metadata	metadata of the block
		 * @param isUpper   determines whether the trapdoor is placed on the upper or lower part of the block
		 * @param isOpen    sets the trapdoor as open or not
		 * @param direction determines the direction the stair are ascending
		 * @return 			returns the metadata for the stairs in the direction ascending
		 */
		public static int setMetadata(int metadata, boolean isUpper, boolean isOpen, Direction direction) {
			metadata = Metadata.rawSetBitBlock(metadata, 0, 1, getTrapDoorMetaForDirection(direction));
			metadata = Metadata.rawSetBit(metadata, 2, isUpper ? 1 : 0);
			metadata = Metadata.rawSetBit(metadata, 3, isOpen ? 1 : 0);
			return metadata;
		}

		/**
		 * @param isUpper   determines whether the trapdoor is placed on the upper or lower part of the block
		 * @param isOpen    sets the trapdoor as open or not
		 * @param direction determines the direction the stair are ascending
		 * @return 			returns the metadata for the stairs in the direction ascending
		 */
		public static int setMetadata(boolean isUpper, boolean isOpen, Direction direction) {
			return setMetadata(0, isUpper, isOpen, direction.getOpposite());
		}

		/**
		 * @param dyeColor  sets the color of trapdoor
		 * @param isUpper   determines whether the trapdoor is placed on the upper or lower part of the block
		 * @param isOpen    sets the trapdoor as open or not
		 * @param direction determines the direction the stair are ascending
		 * @return 			returns the metadata for the stairs in the direction ascending
		 */
		public static int setMetadata(boolean isUpper, boolean isOpen, Direction direction, DyeColor dyeColor) {
			int metadata = setMetadata(0, isUpper, isOpen, direction.getOpposite());
			return Metadata.rawSetBitBlock(metadata, 4, 7, dyeColor.blockMeta);
		}

		/**
		 * @implNote The direction is the ascending direction of the trapdoors.
		 */
		public static int getTrapDoorMetaForDirection(Direction direction) {
			switch (direction) {
				case EAST:
					return DIRECTION_EAST;
				case WEST:
					return DIRECTION_WEST;
				case SOUTH:
					return DIRECTION_SOUTH;
				case NORTH:
				default:
					return DIRECTION_NORTH;
			}
		}

		/**
		 * @implNote The direction is the ascending direction of the trapdoors.
		 */
		public static Direction getTrapDoorDirectionForMeta(int metadata) {
			switch (metadata) {
				case DIRECTION_SOUTH:
					return SOUTH;
				case DIRECTION_NORTH:
					return NORTH;
				case DIRECTION_EAST:
					return EAST;
				case DIRECTION_WEST:
					return WEST;
				default:
					return NONE;
			}
		}

		/**
		 * @param metadata	the trapdoor's metadata
		 * @return 			returns the metadata for the stairs in the direction ascending
		 */
		public static DyeColor getDyeColor(int metadata) {
			return MetadataBlockHelper.getDyeColor(metadata);
		}

		/**
		 * @param metadata	the trapdoor's metadata
		 * @return 			returns the metadata for the stairs in the direction ascending
		 */
		public static boolean isUpper(int metadata) {
			return Metadata.isSet(metadata, 2);
		}

		/**
		 * @param metadata	the trapdoor's metadata
		 * @return 			returns the metadata for the stairs in the direction ascending
		 */
		public static boolean isOpen(int metadata) {
			return Metadata.isSet(metadata, 3);
		}


	}
}
