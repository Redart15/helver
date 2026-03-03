package redart15.helver.block;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.world.World;

/**
 * @implNote interface to allow block underneath players to be triggered even when players are not moving.
 * */
public interface TriggerStandOn {
	default void onEntityStandOn(World world, int x, int y, int z, Entity entity) {}
}
