package heero.mc.mod.wakcraft.network.handler;

import heero.mc.mod.wakcraft.manager.ProfessionManager;
import heero.mc.mod.wakcraft.manager.ProfessionManager.PROFESSION;
import heero.mc.mod.wakcraft.network.packet.PacketProfession;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class HandlerClientProfession implements IMessageHandler<PacketProfession, IMessage> {
	@Override
	public IMessage onMessage(PacketProfession message, MessageContext ctx) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;

		for (PROFESSION profession : message.xps.keySet()) {
			ProfessionManager.setXp(player, profession, message.xps.get(profession));
		}

		return null;
	}
}