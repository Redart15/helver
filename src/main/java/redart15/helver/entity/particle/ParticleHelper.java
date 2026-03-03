package redart15.helver.entity.particle;

import net.minecraft.core.net.packet.PacketAddParticle;
import net.minecraft.core.world.World;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.net.PlayerList;
import turniplabs.halplibe.helper.EnvironmentHelper;

public class ParticleHelper {
	private ParticleHelper(){/* no need to initiate*/}

	/**
	 * @implNote Automatically send packets on the server to spawn the particles on the client side. Not always needed.
	 * Some environment already send the package anyway but most others require the explicit sending of packages.
	 * */
	public static void spawnParticle(World world, String particleKey, double x, double y, double z, double motionX, double motionY, double motionZ, int data, double renderDistance) {
		if (EnvironmentHelper.isClientWorld()) return;
		if (EnvironmentHelper.isServerEnvironment()) {
			PlayerList playerList = MinecraftServer.getInstance().playerList;
			playerList.sendPacketToAllPlayersInDimension(new PacketAddParticle(particleKey, x, y, z, motionX, motionY, motionZ, data, renderDistance), world.dimension.id);
			return;
		}
		world.spawnParticle(particleKey, x, y, z, motionX, motionY, motionZ, data, renderDistance);
	}

	/**
	 * @implNote Automatically send packets on the server to spawn the particles on the client side. Not always needed.
	 * Some environment already send the package anyway but most others require the explicit sending of packages.
	 * */
	public static void spawnParticle(World world, String particleKey, double x, double y, double z, double motionX, double motionY, double motionZ, int data) {
		spawnParticle(world, particleKey, x, y, z, motionX, motionY, motionZ, data, 16D);
	}
}
