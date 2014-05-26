package heero.mc.mod.wakcraft.client.gui;

import heero.mc.mod.wakcraft.client.gui.inventory.GUIInventory;
import heero.mc.mod.wakcraft.manager.ProfessionManager.PROFESSION;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GUIWakcraft extends GUITabs {

	public GUIWakcraft(Container container, EntityPlayer player) {
		super(new GuiScreen[] { new GUIInventory(container),
				new GUIAbilities(player),
				new GUIProfession(player, PROFESSION.CHEF) });
	}
}
