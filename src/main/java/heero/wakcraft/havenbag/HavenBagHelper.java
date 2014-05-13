package heero.wakcraft.havenbag;

import heero.wakcraft.Wakcraft;
import heero.wakcraft.WakcraftConfig;
import heero.wakcraft.entity.property.HavenBagProperty;
import heero.wakcraft.network.packet.PacketHavenBagProperties;
import heero.wakcraft.world.TeleporterHavenBag;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLLog;

public class HavenBagHelper {
	public static final int R_GARDEN = 1;
	public static final int R_CRAFT = 2;
	public static final int R_DECO = 4;
	public static final int R_MERCHANT = 8;

	public static int[] getCoordFromUID(int uid) {
		return new int[] { (uid % 16) * 32, 20, (uid / 16) * 32 };
	}

	public static int getUIDFromCoord(int x, int y, int z) {
		return (z / 32) * 16 + (x / 32);
	}

	public static void teleportPlayerToHavenBag(EntityPlayerMP player, int havenBagUID) {
		HavenBagProperty properties = (HavenBagProperty) player.getExtendedProperties(HavenBagProperty.IDENTIFIER);
		if (properties == null) {
			FMLLog.warning("Error while loading havenbag player (%s) properties", player.getDisplayName());
			return;
		}

		properties.setEnterHavenBag(player.posX, player.posY, player.posZ, havenBagUID);

		player.mcServer.getConfigurationManager().transferPlayerToDimension(player, WakcraftConfig.havenBagDimensionId, new TeleporterHavenBag(MinecraftServer.getServer().worldServerForDimension(WakcraftConfig.havenBagDimensionId), havenBagUID));

		Wakcraft.packetPipeline.sendTo(new PacketHavenBagProperties(properties.getUID()), player);
	}

	public static void leaveHavenBag(EntityPlayerMP player) {
		HavenBagProperty properties = (HavenBagProperty) player.getExtendedProperties(HavenBagProperty.IDENTIFIER);
		if (properties == null) {
			FMLLog.warning("Error while loading havenbag player (%s) properties", player.getDisplayName());
			return;
		}

		player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterHavenBag(MinecraftServer.getServer().worldServerForDimension(0), properties));

		properties.setLeaveHavenBag();
	}
}
