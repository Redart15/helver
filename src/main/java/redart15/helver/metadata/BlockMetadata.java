package redart15.helver.metadata;

public class BlockMetadata {
	public static final int BLOCK_LENGTH = 8;
	private static final int MAX_METAVALUE = 0b1111_1111;
	private static final int UPPER_MASK = 0b1111_0000;
	private static final int LOWER_MASK = 0b0000_1111;
	private static final int NIBBLE_LENGTH = 4;
	private static final int NOT_METADATA = -1;

	private BlockMetadata(){/* no need to initiate*/}

	public static boolean isSet(int metadata, int index){
		return (BlockMetadata.getBit(metadata, index) & 1) == 1;
	}
	public static int getBit(int metadata, int index){
		if(index >= BLOCK_LENGTH || index < 0) {
			return NOT_METADATA;
		}
		return ((metadata & MAX_METAVALUE) >>> index) & 1;
	}
	public static int getBitBlock(int metadata, int startIndex, int endIndex){
		if(startIndex >= BLOCK_LENGTH || startIndex < 0 || endIndex >= BLOCK_LENGTH || endIndex < 0) {
			return NOT_METADATA;
		}
		int len = endIndex - startIndex;
		if(len < 0) {
			return NOT_METADATA;
		}
		if(len == 0) {
			return Metadata.rawGetBit(metadata & MAX_METAVALUE, startIndex);
		}
		return BlockMetadata.rawGetBitBlock(metadata & MAX_METAVALUE, startIndex, len + 1);
	}
	public static int getUpperBlock(int metadata){
		return BlockMetadata.rawGetUpperBlock(metadata & MAX_METAVALUE);
	}
	public static int getLowerBlock(int metadata){
		return BlockMetadata.rawGetLowerBlock(metadata);
	}
	public static int setBit(int metadata, int index){
		return BlockMetadata.setBit(metadata, index, 1);
	}
	public static int setBit(int metadata, int index, int value){
		if(index >= BLOCK_LENGTH || index < 0){
			return NOT_METADATA;
		}
		return Metadata.rawSetBit(metadata & MAX_METAVALUE, index, value & 1);
	}
	public static int setBitBlock(int metadata, int startIndex, int endIndex, int value){
		if(value > (MAX_METAVALUE >>> startIndex)|| startIndex >= BLOCK_LENGTH || startIndex < 0 || startIndex > endIndex || endIndex > BLOCK_LENGTH) {
			return NOT_METADATA;
		}
		return Metadata.rawSetBitBlock(metadata & MAX_METAVALUE, startIndex, endIndex - startIndex + 1, value);
	}
	public static int flipBit(int metadata, int index){
		if(index >= BLOCK_LENGTH || index < 0){
			return NOT_METADATA;
		}
		return Metadata.rawFlipBit(metadata & MAX_METAVALUE, index);
	}

	protected static int rawGetBitBlock(int metadata, int startIndex, int len){
		int mask = (1 << len) - 1;
		return ((metadata & MAX_METAVALUE) >>> startIndex) & mask;
	}
	protected static int rawGetUpperBlock(int metadata){
		return (metadata & UPPER_MASK) >> NIBBLE_LENGTH;
	}
	protected static int rawGetLowerBlock(int metadata){
		return metadata & LOWER_MASK;
	}
}
