package heero.wakcraft.eventhandler;

import heero.wakcraft.Wakcraft;
import heero.wakcraft.network.packet.PacketOpenWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GUIEventHandler {
	@SubscribeEvent
	public void onGuiOpen(GuiOpenEvent event) {
		if (event.gui != null && event.gui instanceof GuiInventory) {
			Minecraft mc = Minecraft.getMinecraft();

			if (mc.playerController.isInCreativeMode()) {
				return;
			}

			Wakcraft.packetPipeline.sendToServer(new PacketOpenWindow(PacketOpenWindow.WINDOW_INVENTORY));

			event.setCanceled(true);
		}
	}
}
