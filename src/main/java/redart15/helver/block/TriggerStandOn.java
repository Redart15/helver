package redart15.helver.block;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.World;

public interface TriggerStandOn { /* exist to fix a bta bug*/
	default void onEntityStandOn(World world, int x, int y, int z, Entity entity) {}
}
