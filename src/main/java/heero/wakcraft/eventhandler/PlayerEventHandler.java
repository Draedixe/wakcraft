package heero.wakcraft.eventhandler;

import heero.wakcraft.Wakcraft;
import heero.wakcraft.entity.property.XpProfessionProperty;
import heero.wakcraft.network.packet.ProfessionPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class PlayerEventHandler {
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			player.registerExtendedProperties(XpProfessionProperty.IDENTIFIER, new XpProfessionProperty());
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		if (!event.player.worldObj.isRemote && event.player instanceof EntityPlayerMP) {
			Wakcraft.packetPipeline.sendTo(new ProfessionPacket(event.player), (EntityPlayerMP) event.player);
		}
	}
}