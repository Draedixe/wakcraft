package heero.wakcraft.havenbag;

import heero.wakcraft.entity.property.HavenBagProperty;
import heero.wakcraft.tileentity.TileEntityHavenBagProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLLog;

public class HavenBagHelper {
	public static int getNextAvailableUID(World world) {
		int uid = 0;

		while (true) {
			int[] coords = getCoordFromUID(++uid);
			if (world.getBlock(coords[0], coords[1] - 1, coords[2] + 1).equals(Blocks.air)) {
				break;
			}
		}

		return uid;
	}

	public static int[] getCoordFromUID(int uid) {
		return new int[] { 100000 + (uid % 10) * 30, 20, (uid / 10) * 30 };
	}

	public static int getUIDFromCoord(int x, int y, int z) {
		return ((z + 7) / 30) * 10 + ((x - 99999) / 30);
	}

	public static TileEntityHavenBagProperties getHavenBagProperties(World world, int uid) {
		int[] coords = getCoordFromUID(uid);

		TileEntity tileEntity = world.getTileEntity(coords[0], coords[1], coords[2] - 1);
		if (tileEntity == null || !(tileEntity instanceof TileEntityHavenBagProperties)) {
			FMLLog.warning("Error while loading tile entity haven bag properties (%d, %d, %d)", coords[0], coords[1], coords[2]);
			return null;
		}

		return (TileEntityHavenBagProperties) tileEntity;
	}

	public static void teleportPlayerToHavenBag(EntityPlayer player, int havenBagUID) {
		HavenBagProperty properties = (HavenBagProperty) player.getExtendedProperties(HavenBagProperty.IDENTIFIER);
		if (properties == null) {
			FMLLog.warning("Error while loading player (%s) properties", player.getDisplayName());
			return;
		}

		properties.setEnterHavenBag(player.posX, player.posY, player.posZ, havenBagUID);

		int[] coords = HavenBagHelper.getCoordFromUID(havenBagUID);
		player.rotationYaw = -90;
		player.rotationPitch = 0;
		player.setPositionAndUpdate(coords[0] + 0.5, coords[1], coords[2] + 1.5);
	}
}
