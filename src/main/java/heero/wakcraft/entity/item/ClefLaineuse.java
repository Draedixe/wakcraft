package heero.wakcraft.entity.item;

import heero.wakcraft.creativetab.WakcraftCreativeTabs;
import heero.wakcraft.reference.References;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ClefLaineuse extends Item {
	public ClefLaineuse() {
		super();

		setCreativeTab(WakcraftCreativeTabs.tabMisc);
		setUnlocalizedName("WoollyKey");
		setTextureName(References.MODID.toLowerCase() + ":woollykey");
	}
}