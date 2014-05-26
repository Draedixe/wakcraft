package heero.mc.mod.wakcraft.eventhandler;

import heero.mc.mod.wakcraft.Wakcraft;
import heero.mc.mod.wakcraft.client.setting.KeyBindings;
import heero.mc.mod.wakcraft.network.packet.PacketHavenBagTeleportation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputHandler {
	private long havenBagTimer = 0;

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (KeyBindings.havenBag.isPressed()) {
			long time = System.currentTimeMillis();

			if (time < havenBagTimer + 1000) {
				return;
			}

			Wakcraft.packetPipeline.sendToServer(new PacketHavenBagTeleportation());
		}
	}
}